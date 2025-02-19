package bean;

import java.util.List;

/**
 *  メニューBeanのクラスです。
 *  @version 1.0
 *  @since 2024/02/21
 *  @author k.yamada
 */
public class MenuBean extends MessageBean{
	
	// ユーザ名
	private String userName;
	
	// ユーザID2
	private String userId;
	
	// メニュー項目リスト
	private List<MenuItemBean> menuItemList;
	
	// ニュース情報リスト
	private List<NewsApiBean> newsInfoList;
	

	/**
	 * ユーザ名を取得します。
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * ユーザ名を設定します。
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * メニュー項目リストを取得します。
	 * @return menuItemList
	 */
	public List<MenuItemBean> getMenuItemList() {
		return menuItemList;
	}

	/**
	 * メニュー項目リストを設定します。
	 * @param menuItemList
	 */
	public void setMenuItemList(List<MenuItemBean> menuItemList) {
		this.menuItemList = menuItemList;
	}

	/**
	 * ニュース情報リストを取得します。
	 * @return newsInfoList
	 */
	public List<NewsApiBean> getNewsInfoList() {
		return newsInfoList;
	}

	/**
	 * ニュース情報リストを設定します。
	 * @param newsInfoList
	 */
	public void setNewsInfoList(List<NewsApiBean> newsInfoList) {
		this.newsInfoList = newsInfoList;
	}
}

