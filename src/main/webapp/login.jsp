<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="loginBean" class="bean.LoginBean" scope="session" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>ログインページ</title>
    
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="js/bootstrap.bundle.js"></script>
    
    <style>
    html,
    body {
        height: 100%;
    }

    .form-signin {
        max-width: 330px;
        padding: 1rem;
    }

    .form-signin .form-floating:focus-within {
      z-index: 2;
    }

    .form-signin input[type="text"] {
        margin-bottom: -1px;
        border-bottom-right-radius: 0;
        border-bottom-left-radius: 0;
    }

    .form-signin input[type="password"] {
        margin-bottom: 10px;
        border-top-left-radius: 0;
        border-top-right-radius: 0;
    }
    </style>
  </head>
  
  <script>

  $(function () {

	// ログインボタン押下イベント
	$("#btnLogin").on('click', function() {

		// 各項目の背景色をクリア
    	$("#userId").css("background","");
    	$("#password").css("background","");

        // 未入力チェック
    	if($('#userId').val() == ""){
        	alert('ユーザIDが未入力です。');
        	$("#userId").css("background-color","pink");
        	return false;
        } else if($('#password').val() == ""){
        	alert('パスワードが未入力です。');
        	$("#password").css("background-color","pink");
        	return false;
        }

        // 送信
    	$("#frm").submit();
    });
  })

  </script>

  <!-- BODY部 START -->
  <body class="bg-body-tertiary">
    <% if(loginBean.isRoginFailureFlg()){ %>
	  <div class="alert text-center alert-danger" role="alert">
	    <%= loginBean.getMessage() %>
	    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
	  </div>
    <% }%>
    <main class="form-signin w-100 m-auto">
      <form id="frm" class="text-center" action="<%=request.getContextPath() %>/Login" method="post">
        <!-- ログインアイコン -->
        <img class="p-3" src="img/login.jpeg" class="img-thumbnail" alt="">
        <h1 class="h3 mb-3 fw-normal">サインインして下さい</h1>
        <!-- ユーザID -->
        <div class="form-floating">
          <input type="text" class="form-control" id="userId" name="userId" placeholder="ユーザID" value="<%= loginBean.getUserId() %>" pattern="^[a-zA-Z0-9]+$" maxlength="8" autofocus/>
          <label for="userId">ユーザID</label>
        </div>
        <!-- パスワード -->
        <div class="form-floating">
          <input type="password" class="form-control" id="password" name="password" placeholder="パスワード" value="<%= loginBean.getPassword() %>" maxlength="8"/>
          <label for="password">パスワード</label>
        </div>
        <!-- ログイン -->
        <input class="btn btn-outline-primary w-100 py-2" type="button" id="btnLogin" value="ログイン"/>
        <!-- コピーライト -->
        <p class="mt-2 mb-3 text-muted">&copy; 2024 k.yamada</p>
      </form>
    </main>
  </body>
  <!-- BODY部 END -->

</html>