package logic;

import java.io.IOException;

import bean.UserRegistBean;
import check.UserRegistCheck;
import common.Common;
import common.Const;
import db.MstGroupDb;
import db.UserRegistDb;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *  ユーザ登録ロジックのクラスです。
 *  @version 1.0
 *  @since 2024/02/22
 *  @author k.yamada
 */
@WebServlet("/UserRegist")
public class UserRegistLogic extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserRegistLogic() {
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
		
		// ユーザ登録Bean
		UserRegistBean userRegistBean = new UserRegistBean();
		
		// 既にセッションが存在する場合
	    if (session != null) {
			
			try {
				// パラメータ設定
				userRegistBean = setParameter(request);
				
				// コンボボックス設定
				setComb(userRegistBean);
		    
		        // ユーザ登録のDBクラスのインスタンス生成
		        UserRegistDb userRegistDb = new UserRegistDb();
		        
		        // ユーザ登録のチェッククラスのインスタンス生成
		        UserRegistCheck check = new UserRegistCheck();
		        
		        // 登録の場合
		        if("REGIST".equals(userRegistBean.getMode())) {
		        	
		        	// チェック処理
		        	if(check.checkParameter(userRegistBean)) {
		        		// 登録するユーザIDの取得
			        	String userId = userRegistDb.getUserId();
			        	
			        	// ユーザIDの設定
			        	userRegistBean.setUserId(userId);
			        	
			        	// ユーザ情報の登録
			        	userRegistDb.registUserInfo(userRegistBean);
			        	
			        	// モードを更新に設定
			        	userRegistBean.setMode("UPDATE");
			        	
			        	// メッセージを設定
			        	userRegistBean.setKbn("1");
			        	userRegistBean.setMessage("正常に登録しました。");
		        	}
		        	
		        } else if("UPDATE".equals(userRegistBean.getMode())) {
		        	// 更新の場合
		        	
		        	// チェック処理
		        	if(check.checkParameter(userRegistBean)) {
		        		// ユーザ情報の更新
			        	userRegistDb.updateUserInfo(userRegistBean);
			        	
			        	// メッセージを設定
			        	userRegistBean.setKbn("1");
			        	userRegistBean.setMessage("正常に更新しました。");
		        	}
		        	
		        } else if("DETAIL_UPDATE".equals(Common.nullToEmpty((String)session.getAttribute("mode")))) {
		        	// 検索画面から遷移の場合
		        	
		        	// ユーザID
		        	String userId = Common.nullToEmpty((String)session.getAttribute("detailUserId"));
		        	
		        	// ユーザ情報の取得
		        	userRegistBean = userRegistDb.getUserInfo(userId);
		        	
		        	// コンボボックス設定
					setComb(userRegistBean);
		        	
		        	// モードを更新に設定
		        	userRegistBean.setMode("UPDATE");
		        	
		        	// セッションの項目削除
		        	session.removeAttribute("mode");
		        	session.removeAttribute("detailUserId");
		        	
		        } else {
		        	// リンクから遷移の場合
		        	
		        	// モードを登録に設定
		        	userRegistBean.setMode("REGIST");
		        }
				
			} catch (Exception e) {
				
				// システムエラー
				userRegistBean.setMessage(Const.CONST_SYSTEM_ERROR_MESSAGE + e.getMessage());
				userRegistBean.setKbn("2");
			}
			
			session.setAttribute("userRegistBean", userRegistBean);
			
			response.sendRedirect(request.getContextPath() + "/userRegist.jsp");
			
	    } else {
	    	response.sendRedirect(request.getContextPath() + "/sessionTimeout.jsp");
	    }
	}
	
	/**
	 *  パラメータの設定。
	 *  @param リクエスト
	 *  @return ユーザ登録情報
	 */
	private UserRegistBean setParameter(HttpServletRequest request) {
		
		// ユーザ登録Bean
		UserRegistBean userRegistBean = new UserRegistBean();
		
		// ユーザID
		userRegistBean.setUserId(Common.nullToEmpty(request.getParameter("userId")));
		
		// ユーザ名
		userRegistBean.setUserName(Common.nullToEmpty(request.getParameter("userName")));
		
		// 誕生日
		userRegistBean.setBirthDay(Common.nullToEmpty(request.getParameter("birthDay")));
		
		// パスワード
		userRegistBean.setPassword(Common.nullToEmpty(request.getParameter("password")));
		
		// 電話番号
		userRegistBean.setTel(Common.nullToEmpty(request.getParameter("tel")));
		
		// 郵便番号
		userRegistBean.setPostCode(Common.nullToEmpty(request.getParameter("postCode")));
		
		// 住所1
		userRegistBean.setAddress1(Common.nullToEmpty(request.getParameter("address1")));
		
		// 住所2
		userRegistBean.setAddress2(Common.nullToEmpty(request.getParameter("address2")));
		
		// モード
		userRegistBean.setMode(Common.nullToEmpty(request.getParameter("mode")));
		
		// 性別
		userRegistBean.setSex(Common.nullToEmpty(request.getParameter("sex")));
		
		// メールアドレス
		userRegistBean.setMailAddress(Common.nullToEmpty(request.getParameter("mailAddress")));
		
		// メモ
		userRegistBean.setMemo(Common.nullToEmpty(request.getParameter("memo")));
		
		return userRegistBean;
	}
	
	/**
	 * コンボボックスの設定。
	 * @param リクエスト
	 * @return ユーザ登録情報
	 * @throws Exception 
	 */
	private UserRegistBean setComb(UserRegistBean userRegistBean) throws Exception {
		
		// 分類マスタのDBクラス
		MstGroupDb mstGroupDb = new MstGroupDb();
		
		// 性別
		userRegistBean.setCmbSex(mstGroupDb.createComb("SEX", true)); ;
		
		return userRegistBean;
	}
}