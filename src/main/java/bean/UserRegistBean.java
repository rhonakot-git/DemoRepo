package bean;

import java.util.List;

/**
 *  ユーザ登録Beanのクラスです。
 *  @version 1.0
 *  @since 2024/02/25
 *  @author k.yamada
 */
public class UserRegistBean extends MessageBean{
	
	// ユーザID
	private String userId;
	
	// ユーザ名
	private String userName;
	
	// 誕生日
	private String birthDay;
	
	// パスワード
	private String password;
	
	// 電話番号
	private String tel;
	
	// 郵便番号
	private String postCode;
	
	// 住所1
	private String address1;
	
	// 住所2
	private String address2;
	
	// メモ
	private String memo;
	
	// 性別
	private String sex;
	
	// 性別(コンボボックス)
	private List<MstGroupBean> cmbSex;
	
	// メールアドレス
	private String mailAddress;
	
	// モード
	private String mode;

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
	 * 郵便番号を取得します。
	 * @return postCode
	 */
	public String getPostCode() {
		return postCode;
	}

	/**
	 * 郵便番号を設定します。
	 * @param postCode
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	/**
	 * 住所1を取得します。
	 * @return address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * 住所1を設定します。
	 * @param address1
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * 住所2を取得します。
	 * @return address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * 住所2を設定します。
	 * @param address2
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * メモを取得します。
	 * @return memo
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * メモを設定します。
	 * @param memo
	 */
	public void setMemo(String memo) {
		this.memo = memo;
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
	 * 性別(コンボボックス)を取得します。
	 * @return cmbSex
	 */
	public List<MstGroupBean> getCmbSex() {
		return cmbSex;
	}

	/**
	 * 性別(コンボボックス)を設定します。
	 * @param cmbSex
	 */
	public void setCmbSex(List<MstGroupBean> cmbSex) {
		this.cmbSex = cmbSex;
	}

	/**
	 * メールアドレスを取得します。
	 * @return mailAddress
	 */
	public String getMailAddress() {
		return mailAddress;
	}

	/**
	 * メールアドレスを設定します。
	 * @param mailAddress
	 */
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	/**
	 * モードを取得します。
	 * @return mode
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * モードを設定します。
	 * @param mode
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}
}

