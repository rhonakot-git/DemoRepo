package bean;

import java.util.Date;

/**
 *  一括ユーザBeanのクラスです。
 *  @version 1.0
 *  @since 2024/03/04
 *  @author k.yamada
 */
public class TUserBulkBean extends MessageBean{
	
	// 登録NO
	private String registNo;
	
	// 結果
	private String result;
	
	// アップロード日時
	private Date uploadDateTime;
	
	// 処理件数
	private long procCnt;

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
	 * アップロード日時を取得します。
	 * @return uploadDateTime
	 */
	public Date getUploadDateTime() {
		return uploadDateTime;
	}

	/**
	 * アップロード日時を設定します。
	 * @param uploadDateTime
	 */
	public void setUploadDateTime(Date uploadDateTime) {
		this.uploadDateTime = uploadDateTime;
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
}

