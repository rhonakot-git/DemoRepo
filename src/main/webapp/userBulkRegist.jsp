<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="userBulkRegistBean" class="bean.UserBulkRegistBean" scope="session" />
<%@ page import="bean.UserBulkRegistDetailBean" %>
<%@ page import="common.Common" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-31j">
    <title>ユーザ一括登録</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="js/bootstrap.bundle.js"></script>
  </head>
  
  <script>

  $(function () {

	  $("[id^=btnDetail_]").click(function() {
		  // CSVパス
		  var csvPath = $(this).closest('tr').children('td').eq(4).text();

		  $("#detailCsvPath").val(csvPath);

		  $("#mode").val("DETAIL_CSV_PATH")

		  $("#frm").submit();
	  });
	  
	  $("#registBtn").click(function() {
		      
		  if($('#csvFile').val() == ""){
		      alert('CSVファイルが未選択です。');
		      return false;
		  }

		  $("#mode").val("REGIST");
		  $("#frm").submit();
	  });

	  $("#searchBtn").click(function() {
		  $("#currentPage").val("1");
		  $("#mode").val("SEARCH");
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
    <% if("1".equals(userBulkRegistBean.getKbn())){ %>
	  <div class="alert alert-info alert-dismissible fade show" role="alert">
	    <%= userBulkRegistBean.getMessage() %>
	    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
	  </div>
    <% } else if("2".equals(userBulkRegistBean.getKbn())){ %>
      <div class="alert alert-danger alert-dismissible fade show" role="alert">
	    <%= userBulkRegistBean.getMessage() %>
	    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
	  </div>
    <% }%>
    
    <!-- タイトル START -->
    <div class="page-title border-bottom pb-2">
      <h2>ユーザ一括登録</h2>
    </div>
    <!-- タイトル END -->
    
    <!-- 入力フォーム START -->
    <div class="card w-50 mt-3">
	  <div class="card-header">
	    入力フォーム
	  </div>
	  <div class="card-body">
	    <form id="frm" action="<%=request.getContextPath() %>/UserBulkRegist" method="post" enctype="multipart/form-data">
          <!-- CSVファイル -->
          <div class="row mb-3">
            <label id="lblUserName" class="col-sm-3 col-form-label">CSVファイル</label>
            <div class="col-sm-6">
              <input type="file" id="csvFile" name="csvFile" required>
            </div>
          </div>
          <!-- 結果 -->
          <div class="row mb-3">
            <label id="lblResult" class="col-sm-3 col-form-label">結果</label>
            <div class="col-sm-3">
              <select id="result" name="result" class="form-select" aria-label="Default select example">
                <% for(int i=0;i<userBulkRegistBean.getCmbResult().size();i++ ){
                	if(userBulkRegistBean.getResult().equals(userBulkRegistBean.getCmbResult().get(i).getKbn())){
                %>
                  <option value="<%= userBulkRegistBean.getCmbResult().get(i).getKbn() %>" selected><%= userBulkRegistBean.getCmbResult().get(i).getKbnName() %></option>
                <% } else { %>
                  <option value="<%= userBulkRegistBean.getCmbResult().get(i).getKbn() %>"><%= userBulkRegistBean.getCmbResult().get(i).getKbnName() %></option>
                <% }} %>
              </select>
            </div>
          </div>
          <!-- アップロード日時 -->
          <div class="row mb-3">
            <label id="lblUploadDateTime" class="col-sm-3 col-form-label">アップロード日時</label>
            <div class="col-sm-3">
              <input type="text" class="form-control" id="uploadDateTimeFrom" name="uploadDateTimeFrom" value="<%= userBulkRegistBean.getUploadDateTimeFrom() %>" maxlength="8"/>
            </div>
            <label class="col-sm-1 control-label">～</label>
            <div class="col-sm-3">
              <input type="text" class="form-control" id="uploadDateTimeTo" name="uploadDateTimeTo" value="<%= userBulkRegistBean.getUploadDateTimeTo() %>" maxlength="8"/>
            </div>
          </div>
          <!-- 検索 -->
          <button type="button" id="registBtn" class="btn btn-primary">登録</button>
          <button type="button" id="searchBtn" class="btn btn-warning">検索</button>
          <!-- 隠し項目 -->
          <input type="hidden" id="mode" name="mode" value="<%= userBulkRegistBean.getMode() %>" />
          <input type="hidden" id="currentPage" name="currentPage" value="<%= userBulkRegistBean.getCurrentPage() %>" />
          <input type="hidden" id="dataCount" name="dataCount" value="<%= userBulkRegistBean.getDataCount() %>" />
          <input type="hidden" id="detailCsvPath" name="detailCsvPath" value="" />
        </form>
	  </div>
	</div>
	<!-- 入力フォーム END -->
	
	<% if(userBulkRegistBean.getDetailList() != null && userBulkRegistBean.getDetailList().size() >= 1){ %>
	  <div class="w-75 mt-2">
	    <table class="table table-hover table-bordered">
	      <thead class="table-success">
	        <tr>
	          <th scope="col" style="width: 8%">結果</th>
	          <th scope="col" style="width: 17%">アップロード日時</th>
	          <th scope="col" style="width: 10%">処理件数</th>
	          <th scope="col" style="width: 2%"></th>
	          <th scope="col" style="width: 10%">CSVパス</th>
	        </tr>
	      </thead>
	      <tbody>
	        <% for(int i=0;i<userBulkRegistBean.getDetailList().size();i++ ){ %>
	          <tr>
	            <% if(userBulkRegistBean.getDetailList().get(i).getResult().indexOf("エラー") >= 1 ){ %>
	              <td class="text-danger"><%= userBulkRegistBean.getDetailList().get(i).getResult() %></td>
	            <% } else { %>
	              <td><%= userBulkRegistBean.getDetailList().get(i).getResult() %></td>
	            <% } %>
	            <td><%= userBulkRegistBean.getDetailList().get(i).getUploadDateTime() %></td>
	            <td><%= userBulkRegistBean.getDetailList().get(i).getProcCnt() %></td>
	            <% if(Common.checkStrEmpty(userBulkRegistBean.getDetailList().get(i).getCsvPath())){ %>
	              <td class="align-middle"></td>
	            <% } else { %>
	              <td class="align-middle"><a id="btnDetail_<%= userBulkRegistBean.getDetailList().get(i).getRegistNo() %>"><i class="bi bi-download"></i></a></td>
	            <% } %>
	            <td><%= userBulkRegistBean.getDetailList().get(i).getCsvPath() %></td>
	          </tr>
	        <% } %>
	      </tbody>
	    </table>
	  </div>
	  
	  <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
          <% if(!"1".equals(userBulkRegistBean.getCurrentPage())){ %>
          <li class="page-item" id="pagingPrevious"><a class="page-link" id="pagePrevious" href="#">Previous</a></li>
          <li class="page-item" id="paging1"><a class="page-link" id="page1" href="#">1</a></li>
          <% } else {%>
          <li class="page-item active" id="paging1"><a class="page-link" id="page1" href="#">1</a></li>
          <% } %>
          
          <% if(Integer.parseInt(userBulkRegistBean.getCurrentPage()) >= 6){ %>
          <li>　 . . .　 </li>
          <% } %>
          <% for(long i=userBulkRegistBean.getPageFrom() ; i <= userBulkRegistBean.getPageTo() ; i++){ %>
          <li class="page-item <%= i == Long.parseLong(userBulkRegistBean.getCurrentPage()) ? "active" : "" %>" id="paging<%= i %>"><a class="page-link" id="page<%= i %>" href="#"><%= i %></a></li>
          <% } %>
          <% if(userBulkRegistBean.getMaxPage() - userBulkRegistBean.getPageTo() > 1 ||
        		  userBulkRegistBean.getMaxPage() - Long.parseLong(userBulkRegistBean.getCurrentPage()) > 4){ %>
          <li>　 . . .　 </li>
          <% } %>
          <% if(userBulkRegistBean.getPageTo() != userBulkRegistBean.getMaxPage() && userBulkRegistBean.getMaxPage() != 1){ %>
          <li class="page-item" id="paging<%= userBulkRegistBean.getMaxPage() %>">
            <a class="page-link <%= userBulkRegistBean.getMaxPage() == Long.parseLong(userBulkRegistBean.getCurrentPage()) ? "active" : "" %>" id="page<%= userBulkRegistBean.getMaxPage() %>" href="#"><%= userBulkRegistBean.getMaxPage() %></a>
          </li>
          <% } %>
          <% if(userBulkRegistBean.getMaxPage() != Long.parseLong(userBulkRegistBean.getCurrentPage())){ %>
          <li class="page-item" id="pagingNext"><a class="page-link" id="pageNext" href="#">Next</a></li>
          <% } %>
        </ul>
      </nav>
	<% } %>

  </body>
  <!-- BODY部 END -->

</html>