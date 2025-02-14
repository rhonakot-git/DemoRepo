package logic;

import java.io.IOException;

import bean.LoginBean;
import bean.MenuBean;
import common.NewsApi;
import db.MenuDb;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *  メニューロジックのクラスです。
 *  @version 1.0
 *  @since 2024/02/21
 *  @author k.yamada
 */
@WebServlet("/Menu")
public class MenuLogic extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MenuLogic() {
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

		// メニュー情報のインスタンス生成
		MenuBean menuBean = new MenuBean();
		
		// セッション取得
		HttpSession session = request.getSession(false);
		
		// 既にセッションが存在する場合
	    if (session != null) {
	    	
	    	// セッションからログイン情報の取得
	    	LoginBean loginBean = (LoginBean)session.getAttribute("loginBean");
	    	
			// メニューDBのインスタンス生成
			MenuDb menuDb = new MenuDb();
			
			// ニュースAPIのインスタンス生成
			NewsApi newsApi = new NewsApi();
			
			// メニュー情報の取得
			menuBean = menuDb.getMenuInfo();
			
			// ニュース情報の取得
			menuBean.setNewsInfoList(newsApi.getNewsApi());
	    	
	    	// ユーザ名
	    	menuBean.setUserName(loginBean.getUserName());
	    	
	    	session.setAttribute("menuBean", menuBean);
			
			response.sendRedirect(request.getContextPath() + "/menu.jsp");
	    } else {
	    	response.sendRedirect(request.getContextPath() + "/Login");
	    }
	}
}