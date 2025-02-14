<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="menuBean" class="bean.MenuBean" scope="session" />
<%@ page import="bean.MenuItemBean" %>
<%@ page import="bean.NewsApiBean" %>
<%@ page import="common.Common" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html class="h-100">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-31j">
    
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="js/bootstrap.bundle.js"></script>
    <title>All System</title>
    
    <style>
    .sidebar{
		max-width: 250px;
		min-width: 250px;
	}

    .sidebar .brand{
        background-color: #333132;
        color: white;
    }
    
    .sidebar .nav-link{
        background-color: rgba(255, 255, 255, 0.493);
        color: white;
    }

    main{
        transition: 0.3s all ease;
    }
    
    .table_box{
        overflow-y: auto;
        height: 600px;
    }
    </style>
   
  </head>

  <script type="text/javascript">
    // 初期処理
    window.onload = () => {
        // iFrame非表示
        mainFrameArea.style.display = 'none';
        
        // toggleボタンをセレクト
        let sidebarToggler = document.getElementById('sidebarToggler')
        // 全ページを囲む親要素をセレクト
        let page = document.getElementsByTagName('main')[0];
        // 表示状態用の変数
        let showSidebar = true;

        // イベント追加
        sidebarToggler.addEventListener('click', () => {
            
            // 表示状態判別
            if(showSidebar){
                page.style.cssText = 'margin-left: -250px'
                showSidebar = false;
            } else {
                page.style.cssText = 'margin-left: 0px'
                showSidebar = true;
            }
        })
        
        $(".trTableBox").css("cursor","pointer").click(function() {
        	window.open($(this).data("href"), '_blank');
        });

        // ログインボタン押下イベント
    	$("#A0000001").on('click', function() {
    		// iFrame表示
            mainFrameArea.style.display = 'block';
            tableBox.style.display = 'none';
    		$("#mainFrame").attr("src", "/Nero/UserRegist");
        });
        $("#A0000002").on('click', function() {
        	// iFrame表示
            mainFrameArea.style.display = 'block';
            tableBox.style.display = 'none';
    		$("#mainFrame").attr("src", "/Nero/UserSearch");
        });
        $("#A0000003").on('click', function() {
        	// iFrame表示
            mainFrameArea.style.display = 'block';
            tableBox.style.display = 'none';
    		$("#mainFrame").attr("src", "/Nero/UserBulkRegist");
        });
    }
  </script>

  <!-- BODY部 START -->
  <body class="h-100 bg-light">
    <!-- 全ページを囲む要素 START -->
    <main class="h-100">
      <div class="page-wrapper d-flex flex-row h-100">
        <!-- サイトバーメニュー START -->
        <nav class="sidebar bg-dark bg-gradient">
          <div class="brand p-4">
            <h4>
              <i class="bi bi-emoji-smile-upside-down-fill"></i> All System <i class="bi bi-emoji-smile-fill"></i>
            </h4>
          </div>
          <ul class="nav flex-column m-o p-3">
            <li class="nav-item">
			  <a class="nav-link" href="<%=request.getContextPath() %>/Menu" role="button" aria-expanded="false"><i class="bi bi-house"></i> トップ</a>
			</li>
          
           　　<% if(menuBean.getMenuItemList().size() != 0){ %>
            <li class="nav-item dropdown">
			  <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false"><i class="bi bi-person-fill"></i> ユーザ</a>
			  <ul class="dropdown-menu">
	            <% for(MenuItemBean menuItemBean : menuBean.getMenuItemList()) { %>
	            <li><a href="#" class="dropdown-item" id="<%= menuItemBean.getMenuId() %>"><%= menuItemBean.getMenuName() %></a></li>
	            <% } %>
			  </ul>
			</li>
			<% } %>
          </ul>
        </nav>
        <!-- サイトバーメニュー END -->

        <!-- 全ページの右のコンテンツ -->
        <div class="page-content w-100">
        
          <!-- トップバー START -->
          <nav class="navbar px-3 bg-body-tertiary border-bottom shadow-sm">
            <div>
              <i class="bi bi-list" id="sidebarToggler"></i>
            </div>
            <ul class="navbar-nav d-flex flex-row">
                <li class="nav-item mx-3 text-muted"><a class="nav-link text-secondary">ようこそ <%= menuBean.getUserName() %> さん</a></li>
                <li class="nav-item mx-3 text-muted"><a href="<%=request.getContextPath() %>/Login" class="nav-link text-secondary">ログアウト</a></li>
            </ul>
          </nav>
          <!-- トップバー END -->

          <!-- 下のコンテンツ START -->
          <div class="p-3" id="mainFrameArea">
            <iframe id="mainFrame" src="" width="100%" height="690"></iframe>
          </div>
          
          <div class="w-75 mt-2 table_box" id="tableBox">
            <table class="table table-hover">
              <tbody>
                <% for(NewsApiBean newsApiBean : menuBean.getNewsInfoList()) { %>
                <tr class="trTableBox" data-href="<%= newsApiBean.getUrl() %>">
                  <th scope="col" style="width: 10%">
                    <% if(!Common.checkStrEmpty(newsApiBean.getUrlToImage())) { %>
                    <img src="<%= newsApiBean.getUrlToImage() %>" class="img-thumbnail" width="70" height="70">
                    <% } %>
                  </th>
                  <th scope="col" style="width: 90%">
                    <a class="link-primary"><%= newsApiBean.getTitle() %></a>
                    </BR>
                    <%= newsApiBean.getPublishedAt() %>
                  </th>
                </tr>
                <% } %>
              </tbody>
            </table>
          </div>
          <!-- 下のコンテンツ END -->

        </div>
        <!-- 全ページの右のコンテンツ END -->

      </div>
    </main>
    <!-- 全ページを囲む要素 END -->
  </body>
  <!-- BODY部 END -->

</html>