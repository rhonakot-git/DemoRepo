package logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bean.UserSearchBean;
import bean.UserSearchDetailBean;
import check.UserSearchCheck;
import common.Common;
import common.Const;
import db.MstGroupDb;
import db.UserSearchDb;
import download.UserSearchDownload;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *  ユーザ検索ロジックのクラスです。
 *  @version 1.0
 *  @since 2024/02/27
 *  @author k.yamada
 */
@WebServlet("/UserSearch")
public class UserSearchLogic extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserSearchLogic() {
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
		UserSearchBean userSearchBean = new UserSearchBean();
		
		// 明細リスト
		List<UserSearchDetailBean> detalList = new ArrayList<UserSearchDetailBean>();
		
		// 既にセッションが存在する場合
	    if (session != null) {
	    	
	    	try {
	    		// パラメータ設定
				userSearchBean = setParameter(request);
				
				// コンボボックス設定
				setComb(userSearchBean);
		        
		        // 検索の場合
		        if("SEARCH".equals(userSearchBean.getMode())) {
		        	// ユーザ検索のDBクラスのインスタンス生成
			        UserSearchDb userSearchDb = new UserSearchDb();
			        
			        // ユーザ検索のチェッククラスのインスタンス生成
			        UserSearchCheck check = new UserSearchCheck();
			        
			        // チェック処理
		        	if(check.checkParameter(userSearchBean)) {
		        		// ユーザ情報の取得
				        detalList = userSearchDb.getUserDetailInfo(userSearchBean, false);
				        
				        // 明細情報の設定
				        userSearchBean.setDetalList(detalList);
		        	}
		        	
		        } else if("CSV".equals(userSearchBean.getMode())) {
		        	// CSV出力
		        	
		        	// ユーザ検索のDBクラスのインスタンス生成
			        UserSearchDb userSearchDb = new UserSearchDb();
			        
			        // ユーザ検索のチェッククラスのインスタンス生成
			        UserSearchCheck check = new UserSearchCheck();
			        
			        // ユーザ検索のDBクラスのインスタンス生成
			        UserSearchDownload userSearchDownload = new UserSearchDownload();
			        
			        // チェック処理
		        	if(check.checkParameter(userSearchBean)) {
		        		// ユーザ情報の取得
				        detalList = userSearchDb.getUserDetailInfo(userSearchBean, true);
				        
				        // CSV出力
				        userSearchDownload.createCsvInfo(detalList, response);
				        
				        // 明細情報の設定
				        userSearchBean.setDetalList(detalList);
				        
				        return;
		        	}
		        	
		        } else if("DETAIL_UPDATE".equals(userSearchBean.getMode())) {
		        	// 明細から遷移の場合
		        	
		        	session.setAttribute("mode", "DETAIL_UPDATE");
		        	session.setAttribute("detailUserId", Common.nullToEmpty(request.getParameter("detailUserId")));
		        	session.setAttribute("userSearchBean", userSearchBean);
		        	response.sendRedirect(request.getContextPath() + "/UserRegist");
		        	return;
		        } else {
		        	// リンクから遷移の場合
		        	
		        	// モードを検索に設定
		        	userSearchBean.setMode("SEARCH");
		        	// ページを1に設定
		        	userSearchBean.setCurrentPage("1");
		        }
			} catch (Exception e) {
				// システムエラー
				userSearchBean.setMessage(Const.CONST_SYSTEM_ERROR_MESSAGE + e.getMessage());
				userSearchBean.setKbn("2");
			}
	        
	        session.setAttribute("userSearchBean", userSearchBean);
			
			response.sendRedirect(request.getContextPath() + "/userSearch.jsp");
			
	    } else {
	    	response.sendRedirect(request.getContextPath() + "/sessionTimeout.jsp");
	    }
	}
	
	/**
	 *  パラメータの設定。
	 *  @param リクエスト
	 *  @return ユーザ登録情報
	 */
	private UserSearchBean setParameter(HttpServletRequest request) {
		
		// ユーザ検索Bean
		UserSearchBean userSearchBean = new UserSearchBean();
		
		// ユーザID
		userSearchBean.setUserId(Common.nullToEmpty(request.getParameter("userId")));
		
		// ユーザ名
		userSearchBean.setUserName(Common.nullToEmpty(request.getParameter("userName")));
		
		// 誕生日(FROM)
		userSearchBean.setBirthDayFrom(Common.nullToEmpty(request.getParameter("birthDayFrom")));
		
		// 誕生日(TO)
		userSearchBean.setBirthDayTo(Common.nullToEmpty(request.getParameter("birthDayTo")));
		
		// 電話番号
		userSearchBean.setTel(Common.nullToEmpty(request.getParameter("tel")));
		
		// 性別
		userSearchBean.setSex(Common.nullToEmpty(request.getParameter("sex")));
		
		// 住所
		userSearchBean.setAddress(Common.nullToEmpty(request.getParameter("address")));
		
		// モード
		userSearchBean.setMode(Common.nullToEmpty(request.getParameter("mode")));
		
		// ページ
		userSearchBean.setCurrentPage(Common.nullToEmpty(request.getParameter("currentPage")));
		
		// データ数
		userSearchBean.setDataCount(Long.parseLong(Common.nullToZero(request.getParameter("dataCount"))));
		
		return userSearchBean;
	}
	
	/**
	 * コンボボックスの設定。
	 * @param リクエスト
	 * @return ユーザ登録情報
	 * @throws Exception 
	 */
	private UserSearchBean setComb(UserSearchBean userSearchBean) throws Exception {
		
		// 分類マスタのDBクラス
		MstGroupDb mstGroupDb = new MstGroupDb();
		
		// 性別
		userSearchBean.setCmbSex(mstGroupDb.createComb("SEX", true)); ;
		
		return userSearchBean;
	}
}