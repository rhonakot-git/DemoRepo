package bean;

import java.io.Serializable;

/**
 *  メッセージBeanのクラスです。
 *  @version 1.0
 *  @since 2024/02/20
 *  @author k.yamada
 */
public class MessageBean implements Serializable{
	
	// メッセージ
	private String message;
	
	// 区分(1:正常 2:エラー)
	private String kbn;

	/**
	 * メッセージを取得します。
	 * @return message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * メッセージを設定します。
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 区分を取得します。
	 * @return kbn
	 */
	public String getKbn() {
		return kbn;
	}

	/**
	 * 区分を設定します。
	 * @param kbn
	 */
	public void setKbn(String kbn) {
		this.kbn = kbn;
	}
}

