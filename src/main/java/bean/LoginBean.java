package bean;

/**
 *  ログインBeanのクラスです。
 *  @version 1.0
 *  @since 2024/02/19
 *  @author k.yamada
 */
public class LoginBean extends MessageBean{
	
	// ユーザID
	private String userId;
	
	// ユーザ名
	private String userName;
	
	// パスワード
	private String password;
	
	// ログイン失敗フラグ
	private boolean roginFailureFlg;

	/**
	 * ユーザIDを取得します。
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * ユーザIDを設定します。
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

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
	 * パスワードを取得します。
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * パスワードを設定します。
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * ログイン失敗フラグを取得します。
	 * @return roginFailureFlg
	 */
	public boolean isRoginFailureFlg() {
		return roginFailureFlg;
	}

	/**
	 * ログイン失敗フラグを設定します。
	 * @param roginFailureFlg
	 */
	public void setRoginFailureFlg(boolean roginFailureFlg) {
		this.roginFailureFlg = roginFailureFlg;
	}
}

