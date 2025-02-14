<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="loginBean" class="bean.LoginBean" scope="session" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=windows-31j">
  <title>Login</title>
  
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="js/bootstrap.bundle.js"></script>
  
 </head>
 
 <script>

  $(function () {

	$("#btnLogin").on('click', function() {

    	$("#userId").css("background","");
    	$("#password").css("background","");
        
    	if($('#userId').val() == ""){
        	alert('ユーザIDが未入力です。');
        	$("#userId").css("background-color","pink");
        	return false;
        } else if($('#password').val() == ""){
        	alert('パスワードが未入力です。');
        	$("#password").css("background-color","pink");
        	return false;
        }

    	$("#frm").submit();
    });
  })

  </script>
 
  <body class="text-center bg-light mx-auto p-2">
    <% if(loginBean.isRoginFailureFlg()){ %>
	  <div class="alert alert-danger" role="alert">
	    <%= loginBean.getMessage() %>
	    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
	  </div>
    <% }%>

    <form id="frm" class="w-25 mx-auto" action="<%=request.getContextPath() %>/Login" method="post">
      <img src="img/login.jpeg" class="img-thumbnail" alt="">
      <h1 class="h3 mb-3 fw-normal">ログインして下さい</h1>
      <input class="form-control" id="userId" type="text" name="userId" placeholder="ユーザID" value="<%= loginBean.getUserId() %>" pattern="^[a-zA-Z0-9]+$" maxlength="8" autofocus/>
      <input class="form-control" id="password" type="password" name="password" placeholder="パスワード" value="<%= loginBean.getPassword() %>" maxlength="8"/>
      <input class="btn btn-outline-primary my-1" type="button" id="btnLogin" value="ログイン"/>
      <p class="mt-2 mb-3 text-muted">&copy; 2024 k.yamada</p>
    </form>

  </body>
</html>