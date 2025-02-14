package bean;

/**
 *  ユーザ検索明細Beanのクラスです。
 *  @version 1.0
 *  @since 2024/02/27
 *  @author k.yamada
 */
public class UserSearchDetailBean extends MessageBean{
	
	// ユーザID
	private String userId;
	
	// ユーザ名
	private String userName;
	
	// 誕生日
	private String birthDay;
	
	// 性別
	private String sex;
	
	// 電話番号
	private String tel;
	
	// 住所
	private String address;

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
	 * 誕生日を取得します。
	 * @return birthDay
	 */
	public String getBirthDay() {
		return birthDay;
	}

	/**
	 * 誕生日を設定します。
	 * @param birthDay
	 */
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	/**
	 * 性別を取得します。
	 * @return sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * 性別を設定します。
	 * @param sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * 電話番号を取得します。
	 * @return tel
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * 電話番号を設定します。
	 * @param tel
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * 住所を取得します。
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 住所を設定します。
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
}

