<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="userSearchBean" class="bean.UserSearchBean" scope="session" />
<%@ page import="bean.UserSearchDetailBean" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-31j">
    <title>ユーザ検索</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="js/bootstrap.bundle.js"></script>
  </head>
  
  <script>

  $(function () {
	  
	  $("#searchBtn").click(function() {
		  $("#currentPage").val("1");
		  $("#mode").val("SEARCH");
		  $("#frm").submit();
	  });

	  $("#csvBtn").click(function() {
		  $("#mode").val("CSV");
		  $("#frm").submit();
	  });

	  $("[id^=btnDetail_]").click(function() {
		  // ユーザID
		  var userId = $(this).closest('tr').children('td').eq(1).text();

		  $("#detailUserId").val(userId);

		  $("#mode").val("DETAIL_UPDATE")

		  $("#frm").submit();
	  });

	  $("[id^=page]").click(function() {

		  $("#mode").val("SEARCH");

		  var page = $(this).attr('id').replace("page","");

		  if(page == "Previous"){
			  var num = Number($("#currentPage").val()) - 1;
			  $("#currentPage").val(num);
		  } else if(page == "Next"){
			  var num = Number($("#currentPage").val()) + 1;
			  $("#currentPage").val(num);
		  } else {
			  $("#currentPage").val(page);
		  }
		  
		  $("#frm").submit();
	  });
  });

  </script>

  <!-- BODY部 START -->
  <body class="p-2">
    <% if("2".equals(userSearchBean.getKbn())){ %>
	  <div class="alert alert-danger alert-dismissible fade show" role="alert">
	    <%= userSearchBean.getMessage() %>
	    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
	  </div>
    <% } %>
    
    <!-- タイトル START -->
    <div class="page-title border-bottom pb-2">
      <h2>ユーザ検索</h2>
    </div>
    <!-- タイトル END -->
    
    <!-- 入力フォーム START -->
    <div class="card w-50 mt-3">
	  <div class="card-header">
	    入力フォーム
	  </div>
	  <div class="card-body">
	    <form id="frm" action="<%=request.getContextPath() %>/UserSearch" method="post">
	      <!-- ユーザID -->
	      <div class="row mb-3">
            <label id="lblUserId" class="col-sm-2 col-form-label">ユーザID</label>
            <div class="col-sm-3">
              <input type="text" class="form-control" id="userId" name="userId" value="<%= userSearchBean.getUserId() %>" pattern="^[a-zA-Z0-9]+$" maxlength="8" autofocus/>
            </div>
          </div>
          <!-- ユーザ名 -->
          <div class="row mb-3">
            <label id="lblUserName" class="col-sm-2 col-form-label">ユーザ名</label>
            <div class="col-sm-6">
              <input type="text" class="form-control" id="userName" name="userName" maxlength="50" value="<%= userSearchBean.getUserName() %>">
            </div>
          </div>
          <!-- 誕生日 -->
          <div class="row mb-3">
            <label id="lblBirthDay" class="col-sm-2 col-form-label">誕生日</label>
            <div class="col-sm-3">
              <input type="text" class="form-control" id="birthDayFrom" name="birthDayFrom" value="<%= userSearchBean.getBirthDayFrom() %>" maxlength="8"/>
            </div>
            <label class="col-sm-1 control-label">～</label>
            <div class="col-sm-3">
              <input type="text" class="form-control" id="birthDayTo" name="birthDayTo" value="<%= userSearchBean.getBirthDayTo() %>" maxlength="8"/>
            </div>
          </div>
          <!-- 電話番号 -->
          <div class="row mb-3">
            <label id="lblTel" class="col-sm-2 col-form-label">電話番号</label>
            <div class="col-sm-3">
              <input type="text" class="form-control" id="tel" name="tel" maxlength="15" value="<%= userSearchBean.getTel() %>">
            </div>
          </div>
          <!-- 性別 -->
          <div class="row mb-3">
            <label id="lblBirthDay" class="col-sm-2 col-form-label">性別</label>
            <div class="col-sm-3">
              <select id="sex" name="sex" class="form-select" aria-label="Default select example">
                <% for(int i=0;i<userSearchBean.getCmbSex().size() ;i++ ){
                	if(userSearchBean.getSex().equals(userSearchBean.getCmbSex().get(i).getKbn())){
                %>
                  <option value="<%= userSearchBean.getCmbSex().get(i).getKbn() %>" selected><%= userSearchBean.getCmbSex().get(i).getKbnName() %></option>
                <% } else { %>
                  <option value="<%= userSearchBean.getCmbSex().get(i).getKbn() %>"><%= userSearchBean.getCmbSex().get(i).getKbnName() %></option>
                <% }} %>
              </select>
            </div>
          </div>
          <!-- 住所 -->
          <div class="row mb-3">
            <label id="lblAddress1" class="col-sm-2 col-form-label">住所</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="address" name="address" maxlength="50" value="<%= userSearchBean.getAddress() %>">
            </div>
          </div>
          <!-- 検索 -->
          <button type="button" id="searchBtn" class="btn btn-warning">検索</button>
          <button type="button" id="csvBtn" class="btn btn-success">CSV</button>
          <!-- 隠し項目 -->
          <input type="hidden" id="mode" name="mode" value="<%= userSearchBean.getMode() %>" />
          <input type="hidden" id="detailUserId" name="detailUserId" value="" />
          <input type="hidden" id="currentPage" name="currentPage" value="<%= userSearchBean.getCurrentPage() %>" />
          <input type="hidden" id="dataCount" name="dataCount" value="<%= userSearchBean.getDataCount() %>" />
        </form>
	  </div>
	</div>
	<!-- 入力フォーム END -->
	
	<% if(userSearchBean.getDetalList() != null && userSearchBean.getDetalList().size() >= 1){ %>
	  <div class="w-75 mt-2">
	    <table class="table table-hover table-bordered">
	      <thead class="table-success">
	        <tr>
	          <th scope="col" style="width: 2%"></th>
	          <th scope="col" style="width: 8%">ユーザID</th>
	          <th scope="col" style="width: 17%">ユーザ名</th>
	          <th scope="col" style="width: 10%">誕生日</th>
	          <th scope="col" style="width: 10%">性別</th>
	          <th scope="col" style="width: 10%">電話番号</th>
	          <th class="text-nowrap" scope="col" style="width: 43%">住所</th>
	        </tr>
	      </thead>
	      <tbody>
	        <% for(int i=0;i<userSearchBean.getDetalList().size();i++ ){ %>
	          <tr>
	            <td class="align-middle"><a id="btnDetail_<%= userSearchBean.getDetalList().get(i).getUserId() %>"><i class="bi bi-patch-check"></i></a></td>
	            <td><%= userSearchBean.getDetalList().get(i).getUserId() %></td>
	            <td><%= userSearchBean.getDetalList().get(i).getUserName() %></td>
	            <td><%= userSearchBean.getDetalList().get(i).getBirthDay() %></td>
	            <td><%= userSearchBean.getDetalList().get(i).getSex() %></td>
	            <td><%= userSearchBean.getDetalList().get(i).getTel() %></td>
	            <td><%= userSearchBean.getDetalList().get(i).getAddress() %></td>
	          </tr>
	        <% } %>
	      </tbody>
	    </table>
	  </div>
	  
	  <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
          <% if(!"1".equals(userSearchBean.getCurrentPage())){ %>
          <li class="page-item" id="pagingPrevious"><a class="page-link" id="pagePrevious" href="#">Previous</a></li>
          <li class="page-item" id="paging1"><a class="page-link" id="page1" href="#">1</a></li>
          <% } else {%>
          <li class="page-item active" id="paging1"><a class="page-link" id="page1" href="#">1</a></li>
          <% } %>
          
          <% if(Integer.parseInt(userSearchBean.getCurrentPage()) >= 6){ %>
          <li>　 . . .　 </li>
          <% } %>
          <% for(long i=userSearchBean.getPageFrom() ; i <= userSearchBean.getPageTo() ; i++){ %>
          <li class="page-item <%= i == Long.parseLong(userSearchBean.getCurrentPage()) ? "active" : "" %>" id="paging<%= i %>"><a class="page-link" id="page<%= i %>" href="#"><%= i %></a></li>
          <% } %>
          <% if(userSearchBean.getMaxPage() - userSearchBean.getPageTo() > 1 ||
        		  userSearchBean.getMaxPage() - Long.parseLong(userSearchBean.getCurrentPage()) > 4){ %>
          <li>　 . . .　 </li>
          <% } %>
          <% if(userSearchBean.getPageTo() != userSearchBean.getMaxPage() && userSearchBean.getMaxPage() != 1){ %>
          <li class="page-item" id="paging<%= userSearchBean.getMaxPage() %>">
            <a class="page-link <%= userSearchBean.getMaxPage() == Long.parseLong(userSearchBean.getCurrentPage()) ? "active" : "" %>" id="page<%= userSearchBean.getMaxPage() %>" href="#"><%= userSearchBean.getMaxPage() %></a>
          </li>
          <% } %>
          <% if(userSearchBean.getMaxPage() != Long.parseLong(userSearchBean.getCurrentPage())){ %>
          <li class="page-item" id="pagingNext"><a class="page-link" id="pageNext" href="#">Next</a></li>
          <% } %>
        </ul>
      </nav>
	<% } %>

  </body>
  <!-- BODY部 END -->

</html>