package bean;

import java.util.List;

import common.PropertyUtil;

/**
 *  ユーザ検索Beanのクラスです。
 *  @version 1.0
 *  @since 2024/02/27
 *  @author k.yamada
 */
public class UserSearchBean extends MessageBean{
	
	// ユーザID
	private String userId;
	
	// ユーザ名
	private String userName;
	
	// 誕生日(FROM)
	private String birthDayFrom;
	
	// 誕生日(TO)
	private String birthDayTo;
	
	// 電話番号
	private String tel;
	
	// 性別
	private String sex;
	
	// 性別(コンボボックス)
	private List<MstGroupBean> cmbSex;
	
	// 住所
	private String address;
	
	// モード
	private String mode;
	
	// 表示しているページ
	private String currentPage;
	
	// データ数
	private long dataCount;
	
	// 明細情報
	private List<UserSearchDetailBean> detalList;

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
	 * 誕生日(FROM)を取得します。
	 * @return birthDayFrom
	 */
	public String getBirthDayFrom() {
		return birthDayFrom;
	}

	/**
	 * 誕生日(FROM)を設定します。
	 * @param birthDayFrom
	 */
	public void setBirthDayFrom(String birthDayFrom) {
		this.birthDayFrom = birthDayFrom;
	}

	/**
	 * 誕生日(TO)を取得します。
	 * @return birthDayTo
	 */
	public String getBirthDayTo() {
		return birthDayTo;
	}

	/**
	 * 誕生日(TO)を設定します。
	 * @param birthDayTo
	 */
	public void setBirthDayTo(String birthDayTo) {
		this.birthDayTo = birthDayTo;
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
	
	/**
	 * 表示しているページを取得します。
	 * @return currentPage
	 */
	public String getCurrentPage() {
		return currentPage;
	}

	/**
	 * 表示しているページを設定します。
	 * @param currentPage
	 */
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * データ数を取得します。
	 * @return dataCount
	 */
	public long getDataCount() {
		return dataCount;
	}

	/**
	 * データ数を設定します。
	 * @param dataCount
	 */
	public void setDataCount(long dataCount) {
		this.dataCount = dataCount;
	}

	/**
	 * 明細情報を取得します。
	 * @return detalList
	 */
	public List<UserSearchDetailBean> getDetalList() {
		return detalList;
	}

	/**
	 * 明細情報を設定します。
	 * @param detalList
	 */
	public void setDetalList(List<UserSearchDetailBean> detalList) {
		this.detalList = detalList;
	}
	
	/**
	 * ページFROMを取得します。
	 * @return maxPage
	 */
	public long getPageFrom() {
		return Math.max(Long.parseLong(currentPage) - (Integer.parseInt(PropertyUtil.getProperty("page.nation.count"))), 2);
	}
	
	/**
	 * ページTOを取得します。
	 * @return maxPage
	 */
	public long getPageTo() {
		
		if(getMaxPage() > Integer.parseInt(PropertyUtil.getProperty("offset")) + 1) {
			return Math.min(getPageFrom() + (Integer.parseInt(PropertyUtil.getProperty("page.nation.count")) * 2), getMaxPage() - 1);
		} else {
			return Math.min(getMaxPage() - 1, 1);
		}
	}
	
	/**
	 * MAXページを取得します。
	 * @return maxPage
	 */
	public long getMaxPage() {
		return (long)(Math.floor(dataCount / Integer.parseInt(PropertyUtil.getProperty("offset"))) + 1);
	}
}

