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
    

  </head>
  
  <script>

  $(function () {

	  $("#btnLogin").on("click", function() {
		  $("#success").load("test2.jsp", function(){

			  const myModal = new bootstrap.Modal(document.getElementById('staticBackdrop1'), {})
			  const modalTitle = myModal.querySelector("#dvalue");
			  modalTitle.textContent = "test";
			  myModal.show();
			  
		  });
	  });

  })

  </script>

  <!-- BODY部 START -->
  <body class="bg-body-tertiary">
  
  <input class="btn btn-outline-primary w-100 py-2" type="button" id="btnLogin" value="ログイン"/>

  <div id="success"></div>





  </body>
  <!-- BODY部 END -->

</html>