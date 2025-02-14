package bean;

import java.util.List;

import common.PropertyUtil;

/**
 *  ユーザ一括登録Beanのクラスです。
 *  @version 1.0
 *  @since 2024/02/25
 *  @author k.yamada
 */
public class UserBulkRegistBean extends MessageBean{
	
	// 登録NO
	private String registNo;
	
	// 結果
	private String result;
	
	// 結果(コンボボックス)
	private List<MstGroupBean> cmbResult;
	
	// アップロード日時(開始)
	private String uploadDateTimeFrom;
	
	// アップロード日時(終了)
	private String uploadDateTimeTo;
	
	// 処理件数
	private long procCnt;
	
	// CSVパス
	private String csvPath;
	
	// モード
	private String mode;
	
	// 表示しているページ
	private String currentPage;
	
	// データ数
	private long dataCount;
	
	// 明細情報
	private List<UserBulkRegistDetailBean> detailList;

	/**
	 * 登録NOを取得します。
	 * @return registNo
	 */
	public String getRegistNo() {
		return registNo;
	}

	/**
	 * 登録NOを設定します。
	 * @param registNo
	 */
	public void setRegistNo(String registNo) {
		this.registNo = registNo;
	}

	/**
	 * 結果を取得します。
	 * @return result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * 結果を設定します。
	 * @param result
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * 結果(コンボボックス)を取得します。
	 * @return cmbResult
	 */
	public List<MstGroupBean> getCmbResult() {
		return cmbResult;
	}

	/**
	 * 結果(コンボボックス)を設定します。
	 * @param cmbResult
	 */
	public void setCmbResult(List<MstGroupBean> cmbResult) {
		this.cmbResult = cmbResult;
	}

	/**
	 * アップロード日時(開始)を取得します。
	 * @return uploadDateTimeFrom
	 */
	public String getUploadDateTimeFrom() {
		return uploadDateTimeFrom;
	}

	/**
	 * アップロード日時(開始)を設定します。
	 * @param uploadDateTimeFrom
	 */
	public void setUploadDateTimeFrom(String uploadDateTimeFrom) {
		this.uploadDateTimeFrom = uploadDateTimeFrom;
	}

	/**
	 * アップロード日時(終了)を取得します。
	 * @return uploadDateTimeTo
	 */
	public String getUploadDateTimeTo() {
		return uploadDateTimeTo;
	}

	/**
	 * アップロード日時(終了)を設定します。
	 * @param uploadDateTimeTo
	 */
	public void setUploadDateTimeTo(String uploadDateTimeTo) {
		this.uploadDateTimeTo = uploadDateTimeTo;
	}

	/**
	 * 処理件数を取得します。
	 * @return procCnt
	 */
	public long getProcCnt() {
		return procCnt;
	}

	/**
	 * 処理件数を設定します。
	 * @param procCnt
	 */
	public void setProcCnt(long procCnt) {
		this.procCnt = procCnt;
	}

	/**
	 * CSVパスを取得します。
	 * @return csvPath
	 */
	public String getCsvPath() {
		return csvPath;
	}

	/**
	 * CSVパスを設定します。
	 * @param csvPath
	 */
	public void setCsvPath(String csvPath) {
		this.csvPath = csvPath;
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
	 * @return detailList
	 */
	public List<UserBulkRegistDetailBean> getDetailList() {
		return detailList;
	}

	/**
	 * 明細情報を設定します。
	 * @param detailList
	 */
	public void setDetailList(List<UserBulkRegistDetailBean> detailList) {
		this.detailList = detailList;
	}
	
	/**
	 * ページFROMを取得します。
	 * @return maxPage
	 */
	public long getPageFrom() {
		return Math.max(Long.parseLong(currentPage) - 4, 2);
	}
	
	/**
	 * ページTOを取得します。
	 * @return maxPage
	 */
	public long getPageTo() {
		
		if(getMaxPage() > 11) {
			return Math.min(getPageFrom() + 8, getMaxPage() - 1);
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