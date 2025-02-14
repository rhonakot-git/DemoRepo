package bean;

/**
 *  メニュー項目Beanのクラスです。
 *  @version 1.0
 *  @since 2024/02/23
 *  @author k.yamada
 */
public class MenuItemBean extends MessageBean{
	
	// メニューID
	private String menuId;
	
	// メニュー名
	private String menuName;
	
	// パス
	private String path;

	/**
	 * メニューIDを取得します。
	 * @return menuId
	 */
	public String getMenuId() {
		return menuId;
	}

	/**
	 * メニューIDを設定します。
	 * @param menuId
	 */
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	/**
	 * メニュー名を取得します。
	 * @return menuName
	 */
	public String getMenuName() {
		return menuName;
	}

	/**
	 * メニュー名を設定します。
	 * @param menuName
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	/**
	 * パスを取得します。
	 * @return path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * パスを設定します。
	 * @param path
	 */
	public void setPath(String path) {
		this.path = path;
	}
}

