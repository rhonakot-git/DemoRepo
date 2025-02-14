package bean;

/**
 *  分類マスタBeanのクラスです。
 *  @version 1.0
 *  @since 2024/03/04
 *  @author k.yamada
 */
public class MstGroupBean extends MessageBean{
	
	// 分類ID
	private String groupId;
	
	// 区分
	private String kbn;
	
	// 区分名
	private String kbnName;
	
	// 削除フラグ
	private String delFlag;
	
	// 選択タグ
	private String selectedTag;

	/**
	 * 分類IDを取得します。
	 * @return groupId
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * 分類IDを設定します。
	 * @param groupId
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
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

	/**
	 * 区分名を取得します。
	 * @return kbnName
	 */
	public String getKbnName() {
		return kbnName;
	}

	/**
	 * 区分名を設定します。
	 * @param kbnName
	 */
	public void setKbnName(String kbnName) {
		this.kbnName = kbnName;
	}

	/**
	 * 削除フラグを取得します。
	 * @return delFlag
	 */
	public String getDelFlag() {
		return delFlag;
	}

	/**
	 * 削除フラグを設定します。
	 * @param delFlag
	 */
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	/**
	 * 選択タグを取得します。
	 * @return selectedTag
	 */
	public String getSelectedTag() {
		return selectedTag;
	}

	/**
	 * 選択タグを設定します。
	 * @param selectedTag
	 */
	public void setSelectedTag(String selectedTag) {
		this.selectedTag = selectedTag;
	}
}

