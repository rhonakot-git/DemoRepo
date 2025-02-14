<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="userRegistBean" class="bean.UserRegistBean" scope="session" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-31j">
    <% if("UPDATE".equals(userRegistBean.getMode()) || "DELETE".equals(userRegistBean.getMode())) { %>
      <title>ユーザ更新</title>
    <% } else { %>
      <title>ユーザ登録</title>
    <% }%>
  
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="js/bootstrap.bundle.js"></script>
  </head>
  
  <script>

  $(function () {
	  $("#registBtn,#updateBtn,#deleteBtn").click(function() {

		  $("#userName").css("background","");
		  $("#birthDay").css("background","");
		  $("#password").css("background","");
		  $("#tel").css("background","");
		  $("#postCode").css("background","");
		  $("#address1").css("background","");
		  $("#memo").css("background","");
		  $("#mailAddress").css("background","");
		  $("#sex").css("background","");
		      
		  if($('#userName').val() == ""){
		      alert('ユーザ名が未入力です。');
		      $("#userName").css("background-color","pink");
		      return false;
		  } else if($('#birthDay').val() == ""){
		      alert('誕生日が未入力です。');
		      $("#birthDay").css("background-color","pink");
		      return false;
		  } else if($('#sex').val() == ""){
		      alert('性別が未選択です。');
		      $("#sex").css("background-color","pink");
		      return false;
		  } else if($('#password').val() == ""){
		      alert('パスワードが未入力です。');
		      $("#password").css("background-color","pink");
		      return false;
		  } else if($('#tel').val() == ""){
		      alert('電話番号が未入力です。');
		      $("#tel").css("background-color","pink");
		      return false;
		  } else if($('#mailAddress').val() == ""){
		      alert('メールアドレスが未入力です。');
		      $("#mailAddress").css("background-color","pink");
		      return false;
		  } else if($('#postCode').val() == ""){
		      alert('郵便番号が未入力です。');
		      $("#postCode").css("background-color","pink");
		      return false;
		  } else if($('#address1').val() == ""){
		      alert('住所1が未入力です。');
		      $("#address1").css("background-color","pink");
		      return false;
		  }

		  var id = $(this).attr('id');

		  if(id == "registBtn"){
			  $("#mode").val("REGIST")
		  } else if(id == "updateBtn"){
		      $("#mode").val("UPDATE")
		  } else {
		      $("#mode").val("DELETE")
		  }

		  $("#frm").submit();
	   });

	  $("#postCode").change(function() {
		  var postCode = $("#postCode").val();
		  if (postCode.length !== 7){
			  return;
		  }
		  // 入力値をセット
	      var param = {zipcode:postCode}
	      // APIのURL
	      var send_url = "http://zipcloud.ibsnet.co.jp/api/search";

	      $.ajax({
	              type: "GET",
	              cache: false,
	              data: param,
	              url: send_url,
	              dataType: "jsonp",
	              success: function (res) {
	                   if (res.status == 200 && res.results !== null) {
	                	   $("#address1").val(res.results[0].address1 + res.results[0].address2 + res.results[0].address3);
	                   } else {

	                   }
	            　　},
	            　　error: function (XMLHttpRequest, textStatus, errorThrown) {
	            　　}
	        　　　});
	  });
  });


  </script>

  <!-- BODY部 START -->
  <body class="p-2">
    <% if("1".equals(userRegistBean.getKbn())){ %>
	  <div class="alert alert-info alert-dismissible fade show" role="alert">
	    <%= userRegistBean.getMessage() %>
	    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
	  </div>
    <% } else if("2".equals(userRegistBean.getKbn())){ %>
      <div class="alert alert-danger alert-dismissible fade show" role="alert">
	    <%= userRegistBean.getMessage() %>
	    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
	  </div>
    <% }%>
    
    <!-- タイトル START -->
    <div class="page-title border-bottom pb-2">
      <% if("UPDATE".equals(userRegistBean.getMode()) || "DELETE".equals(userRegistBean.getMode())) { %>
        <h2>ユーザ更新</h2>
      <% } else { %>
        <h2>ユーザ登録</h2>
      <% }%>
    </div>
    <!-- タイトル END -->
    
    <!-- 入力フォーム START -->
    <div class="card w-50 mt-3">
	  <div class="card-header">
	    入力フォーム
	  </div>
	  <div class="card-body">
	    <form id="frm" action="<%=request.getContextPath() %>/UserRegist" method="post">
	      <% if("UPDATE".equals(userRegistBean.getMode())){ %>
	          <div class="row mb-3">
	            <label id="lblUserId" class="col-sm-2 col-form-label">ユーザID</label>
	            <div class="col-sm-7">
	              <input type="hidden" id="userId" name="userId" value="<%= userRegistBean.getUserId() %>" />
	              <label class="col-sm-5 col-form-label"><%= userRegistBean.getUserId() %></label>
	            </div>
	          </div>
          <% } %>
          <!-- ユーザ名 -->
          <div class="row mb-3">
            <label id="lblUserName" class="col-sm-2 col-form-label">ユーザ名</label>
            <div class="col-sm-6">
              <input type="text" class="form-control" id="userName" name="userName" maxlength="50" value="<%= userRegistBean.getUserName() %>" autofocus>
            </div>
          </div>
          <!-- 誕生日 -->
          <div class="row mb-3">
            <label id="lblBirthDay" class="col-sm-2 col-form-label">誕生日</label>
            <div class="col-sm-3">
              <input type="text" class="form-control" id="birthDay" name="birthDay" maxlength="8" value="<%= userRegistBean.getBirthDay() %>"/>
            </div>
          </div>
          <!-- 性別 -->
          <div class="row mb-3">
            <label id="lblBirthDay" class="col-sm-2 col-form-label">性別</label>
            <div class="col-sm-3">
              <select id="sex" name="sex" class="form-select" aria-label="Default select example">
                <% for(int i=0;i<userRegistBean.getCmbSex().size() ;i++ ){
                	if(userRegistBean.getSex().equals(userRegistBean.getCmbSex().get(i).getKbn())){
                %>
                  <option value="<%= userRegistBean.getCmbSex().get(i).getKbn() %>" selected><%= userRegistBean.getCmbSex().get(i).getKbnName() %></option>
                <% } else { %>
                  <option value="<%= userRegistBean.getCmbSex().get(i).getKbn() %>"><%= userRegistBean.getCmbSex().get(i).getKbnName() %></option>
                <% }} %>
              </select>
            </div>
          </div>
          <!-- パスワード -->
		  <div class="row mb-3">
		    <label id="lblPassword" class="col-sm-2 col-form-label">パスワード</label>
		    <div class="col-sm-3">
		      <input type="text" class="form-control" id="password" name="password" value="<%= userRegistBean.getPassword() %>" maxlength="8">
		    </div>
		  </div>
		  <!-- 電話番号 -->
		  <div class="row mb-3">
            <label id="lblTel" class="col-sm-2 col-form-label">電話番号</label>
            <div class="col-sm-3">
              <input type="text" class="form-control" id="tel" name="tel" maxlength="15" value="<%= userRegistBean.getTel() %>">
            </div>
          </div>
          <!-- メールアドレス -->
          <div class="row mb-3">
            <label id="lblMailAddress" class="col-sm-2 col-form-label">メールアドレス</label>
            <div class="col-sm-7">
              <input type="text" class="form-control" id="mailAddress" name="mailAddress" maxlength="50" value="<%= userRegistBean.getMailAddress() %>">
            </div>
          </div>
          <!-- 郵便番号 -->
		  <div class="row mb-3">
            <label id="lblPostCode" class="col-sm-2 col-form-label">郵便番号</label>
            <div class="col-sm-2">
              <input type="text" class="form-control" id="postCode" name="postCode" maxlength="7" value="<%= userRegistBean.getPostCode() %>">
            </div>
          </div>
          <!-- 住所1 -->
          <div class="row mb-3">
            <label id="lblAddress1" class="col-sm-2 col-form-label">住所1</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="address1" name="address1" maxlength="50" value="<%= userRegistBean.getAddress1() %>">
            </div>
          </div>
          <!-- 住所2 -->
          <div class="row mb-3">
            <label id="lblAddress2" class="col-sm-2 col-form-label">住所2<BR>(番地/<BR>マンション名 等)</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="address2" name="address2" maxlength="50" value="<%= userRegistBean.getAddress2() %>">
            </div>
          </div>
          <!-- メモ -->
          <div class="row mb-3">
            <label id="lblMemo" class="col-sm-2 col-form-label">メモ</label>
            <div class="col-sm-10">
              <textarea class="form-control" id="memo" name="memo" rows="3"><%= userRegistBean.getMemo() %></textarea>
            </div>
          </div>
		  <% if("UPDATE".equals(userRegistBean.getMode()) || "DELETE".equals(userRegistBean.getMode())) { %>
          	<button type="button" id="updateBtn" class="btn btn-primary">更新</button>
          	<button type="button" id="deleteBtn" class="btn btn-success" disabled>削除</button>
          <% } else { %>
          	<button type="button" id="registBtn" class="btn btn-primary">登録</button>
          <% }%>
          <!-- 隠し項目　-->
          <input type="hidden" id="mode" name="mode" value="<%= userRegistBean.getMode() %>" />
        </form>
	  </div>
	</div>
	<!-- 入力フォーム END -->

  </body>
  <!-- BODY部 END -->

</html>