package logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bean.UserBulkRegistBean;
import bean.UserBulkRegistDetailBean;
import check.UserBulkRegistCheck;
import common.Common;
import common.Const;
import db.MstGroupDb;
import db.UserBulkRegistDb;
import download.UserBulkRegistDownload;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import thread.UserBulkRegistThread;

/**
 *  ユーザ一括登録ロジックのクラスです。
 *  @version 1.0
 *  @since 2024/02/27
 *  @author k.yamada
 */
@WebServlet("/UserBulkRegist")
@MultipartConfig()
public class UserBulkRegistLogic extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserBulkRegistLogic() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doMethod(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		    throws IOException, ServletException{
		doMethod(request, response);
	}
	
	private void doMethod(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

		// セッション取得
		HttpSession session = request.getSession(false);
		
		// ユーザ検索Bean
		UserBulkRegistBean userBulkRegistBean = new UserBulkRegistBean();
		
		// 明細リスト
		List<UserBulkRegistDetailBean> detalList = new ArrayList<UserBulkRegistDetailBean>();
		
		// 既にセッションが存在する場合
	    if (session != null) {
	        
	    	try {
	    		// パラメータ設定
	    		userBulkRegistBean = setParameter(request);
	    		
	    		// コンボボックス設定
				setComb(userBulkRegistBean);
		        
		        // 登録の場合
		        if("REGIST".equals(userBulkRegistBean.getMode())) {
		        	
		        	// ユーザ一括登録のチェッククラスのインスタンス生成
			        UserBulkRegistCheck check = new UserBulkRegistCheck();
		        	
		        	// CSVファイル取得
		        	Part part = request.getPart("csvFile");
		        	
		        	// チェック処理
 		        	if(check.checkParameter(userBulkRegistBean, part)) {
		        		// スレッドを生成
 		        		var thread = new UserBulkRegistThread(part);

			            // 非同期処理の実行
			            thread.start();
			            
			            Thread.sleep(1000);
			            
			            // ユーザ一括登録のDBクラスのインスタンス生成
			        	UserBulkRegistDb userBulkRegistDb = new UserBulkRegistDb();
			        	
			        	// ユーザ情報の取得
				        detalList = userBulkRegistDb.getUserDetailInfo(userBulkRegistBean);
				        
				        // 明細情報の設定
				        userBulkRegistBean.setDetailList(detalList);
			            
			            // メッセージ(正常)
			            userBulkRegistBean.setKbn("1");
			            userBulkRegistBean.setMessage(Const.CONST_ASYNCHRONOUS_MESSAGE);
		        	}
 		        	
		        } else if("SEARCH".equals(userBulkRegistBean.getMode())) {
		        	// 検索の場合
	        	
		        	// ユーザ一括登録のDBクラスのインスタンス生成
		        	UserBulkRegistDb userBulkRegistDb = new UserBulkRegistDb();
			        
			        // ユーザ一括登録のチェッククラスのインスタンス生成
			        UserBulkRegistCheck check = new UserBulkRegistCheck();
			        
			        // チェック処理
		        	if(check.checkSearchParameter(userBulkRegistBean)) {
		        		// ユーザ情報の取得
				        detalList = userBulkRegistDb.getUserDetailInfo(userBulkRegistBean);
				        
				        // 明細情報の設定
				        userBulkRegistBean.setDetailList(detalList);
		        	}
		        	
		        } else if("DETAIL_CSV_PATH".equals(userBulkRegistBean.getMode())) {
		        	// CSV出力
		        	
		        	// ユーザ一括登録のダウンロードクラス
		        	UserBulkRegistDownload userBulkRegistDownload = new UserBulkRegistDownload();
		        	
		        	// CSV情報作成
		        	userBulkRegistDownload.createCsvInfo(Common.nullToEmpty(request.getParameter("detailCsvPath")), response);
		        	
		        	return;
		        	
		        } else {
		        	// リンクから遷移の場合
		        	
		        	// モードを検索に設定
		        	userBulkRegistBean.setMode("REGIST");
		        }
		        
			} catch (Exception e) {
				// システムエラー
				userBulkRegistBean.setMessage(Const.CONST_SYSTEM_ERROR_MESSAGE + e.getMessage());
				userBulkRegistBean.setKbn("2");
			}
	    	
	        session.setAttribute("userBulkRegistBean", userBulkRegistBean);
			
			response.sendRedirect(request.getContextPath() + "/userBulkRegist.jsp");
			
	    } else {
	    	response.sendRedirect(request.getContextPath() + "/sessionTimeout.jsp");
	    }
	}
	
	/**
	 *  パラメータの設定。
	 *  @param リクエスト
	 *  @return ユーザ登録情報
	 */
	private UserBulkRegistBean setParameter(HttpServletRequest request) {
		
		// ユーザ一括登録Bean
		UserBulkRegistBean userBulkRegistBean = new UserBulkRegistBean();
		
		// 結果
		userBulkRegistBean.setResult(Common.nullToEmpty(request.getParameter("result")));
		
		// アップロード日時(開始)
		userBulkRegistBean.setUploadDateTimeFrom(Common.nullToEmpty(request.getParameter("uploadDateTimeFrom")));
		
		// アップロード日時(終了)
		userBulkRegistBean.setUploadDateTimeTo(Common.nullToEmpty(request.getParameter("uploadDateTimeTo")));
		
		// モード
		userBulkRegistBean.setMode(Common.nullToEmpty(request.getParameter("mode")));
		
		// ページ
		userBulkRegistBean.setCurrentPage(Common.nullToEmpty(request.getParameter("currentPage")));
		
		// データ数
		userBulkRegistBean.setDataCount(Long.parseLong(Common.nullToZero(request.getParameter("dataCount"))));
		
		// CSVパス
		userBulkRegistBean.setCsvPath(Common.nullToEmpty(request.getParameter("detailCsvPath")));
		
		return userBulkRegistBean;
	}
	
	/**
	 * コンボボックスの設定。
	 * @param リクエスト
	 * @return ユーザ一括登録情報
	 * @throws Exception 
	 */
	private UserBulkRegistBean setComb(UserBulkRegistBean userBulkRegistBean) throws Exception {
		
		// 分類マスタのDBクラス
		MstGroupDb mstGroupDb = new MstGroupDb();
		
		// 結果
		userBulkRegistBean.setCmbResult(mstGroupDb.createComb("UPLOAD_RESULT", true));
		
		return userBulkRegistBean;
	}
}