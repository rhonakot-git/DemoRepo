<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="menuBean" class="bean.MenuBean" scope="session" />
<%@ page import="bean.MenuItemBean" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html class="h-100">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-31j">
      <title>MENU</title>
      
      <style media="screen">
          header{
              height: 50px;
              box-shadow: 0px 0px 5px 0px hsla(0, 0%, 7%, 0.3);
          }
          nav {
              width: 230px;
          }
          .nav-link {
              color: white;
              background-color: rgba(255, 255, 255, 0.493);
          }
          main {
              transition: 0.3s all ease;
          }
      </style>
        
      <link href="css/bootstrap.min.css" rel="stylesheet">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
      <script src="js/bootstrap.bundle.js"></script>
        
    </head>
    
    <script type="text/javascript">
        window.onload = () => {
            // toggleボタン
            let sidemenuToggle = document.getElementById('toggle')
            // メインコンテンツを囲むmain要素
            let page = document.getElementsByTagName('main')[0];
            // 表示状態 trueで表示中 falseで非表示
            let sidemenuStatus = true;
            // ボタンクリック時のイベント
            sidemenuToggle.addEventListener('click', () => {
                // 表示状態を判定
                if(sidemenuStatus){
                    page.style.cssText = 'margin-left: -230px'
                    sidemenuStatus = false;

                    $("#sideNavi").hide();
                    
                }else{
                    page.style.cssText = 'margin-left: 0px'
                    sidemenuStatus = true;

                    $("#sideNavi").show();
                    
                }
            })
            
        }
        
    </script>
    
    <body class="h-100">
      <header class="w-100 d-flex justify-content-between align-items-center bg-light px-3">
        <!— ヘッダーの内容 -->
        <h1 class="mx-2 fs-4 text-primary fw-bold">Nero</h1>
        <ul class="navbar-nav d-flex flex-row">
          <li class="nav-item mx-3 text-muted active">ようこそ <%= menuBean.getUserName() %> さん</li>
          <li class="nav-item mx-3 text-muted"><a href="<%=request.getContextPath() %>/Login" class="nav-link text-secondary">ログアウト</a></li>
        </ul>
      </header>
      <div class="d-flex flex-row w-100" style="height: calc(100% - 50px)">
        <!— サイドメニュー -->
        <nav class="bg-dark">
          <ul class="nav flex-column m-0 p-1" id="sideNavi">
            <% if(menuBean.getMenuItemList().size() != 0){ %>
              <div class="btn-group dropend">
                <button type="button" class="btn btn-secondary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
			      ユーザ
				</button>
			    <ul class="dropdown-menu">
                  <% for(MenuItemBean menuItemBean : menuBean.getMenuItemList()) { %>
                    <li><a href="<%=request.getContextPath() %><%= menuItemBean.getPath() %>" class="dropdown-item" id="<%= menuItemBean.getMenuId() %>"><%= menuItemBean.getMenuName() %></a></li>
                  <% } %>
                </ul>
		      </div>
            <% } %>
          </ul>
        </nav>
        <!— メインコンテンツ -->
        <main class="w-100 bg-light">
          <!— タイトルバー -->
          <div class="border shadow-sm d-flex flex-row align-items-center bg-light">
            <!— トグルボタン -->
            <div class="navbar-brand toggle-menu">
              <img src="img/toggle.png" class="img-thumbnail" alt="" id="toggle" width="30" height="30">
            </div>
            <!— タイトル名 -->
            <div class="fs-4 fw-bold" id="titleName">メニュー画面</div>
          </div>
          <!— コンテンツ -->
          <div class="p-3">
            <iframe src="<%=request.getContextPath() %>/UserSearch" class="mh-100"></iframe>
          </div>
        </main>
      </div>
    </body>
    
</html>