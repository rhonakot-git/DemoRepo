package logic;

import java.io.IOException;

import bean.LoginBean;
import common.Common;
import db.LoginDb;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *  ログインロジックのクラスです。
 *  @version 1.0
 *  @since 2024/02/19
 *  @author k.yamada
 */
@WebServlet("/Login")
public class LoginLogic extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginLogic() {
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
		
		// ログイン情報のインスタンス生成
		LoginBean loginBean = new LoginBean();
		
		// ユーザID
		String userId = request.getParameter("userId");
		// パスワード
		String password = request.getParameter("password");

		// セッション取得
		HttpSession session = request.getSession(false);
		
		// 既にセッションが存在する場合は一度破棄する
	    if (session != null) {
	    	session.invalidate();
	    }
		
		// 新規セッションを開始
		HttpSession newSession = request.getSession(true);
		
		// ユーザIDが空白の場合、ログイン情報の取得
		if(userId != null) {
			
			// ログインDBのインスタンス生成
			LoginDb logindb = new LoginDb();
			
			// ログイン情報の取得
			loginBean = logindb.getLoginInfo(userId, password);
		}
		
		// ログイン情報が取得できた場合
		if (loginBean.getUserName() != null) {
			
			newSession.setAttribute("loginBean", loginBean);
			
			response.sendRedirect(request.getContextPath() + "/Menu");
			
		} else {
			// 上記以外の場合
			
			// ユーザID
			loginBean.setUserId(Common.nullToEmpty(userId));
			// パスワード
			loginBean.setPassword(Common.nullToEmpty(password));
			// ログイン失敗フラグ
			if(userId == null) {
				loginBean.setRoginFailureFlg(false);
			} else {
				loginBean.setRoginFailureFlg(true);
				// メッセージ
				loginBean.setMessage("ユーザIDまたはパスワードが間違っています。");
			}
			
			newSession.setAttribute("loginBean", loginBean);
			
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		}
	}
}