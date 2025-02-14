package common;

/**
 *  定数のクラスです。
 *  @version 1.0
 *  @since 2024/02/29
 *  @author k.yamada
 */
public class Const {
	
	/** 非同期処理中 */
	public static String CONST_ASYNCHRONOUS_MESSAGE = "非同期処理中です。結果は検索で確認してください。<BR>"; 
	
	/** システムエラー */
	public static String CONST_SYSTEM_ERROR_MESSAGE = "システムエラーが発生しました。<BR>";
	
	/** 必須 */
	public static String CONST_REQUIRED_ERROR_MESSAGE = "???が未入力です。<BR>";
	
	/** バイト数 */
	public static String CONST_BYTE_NUM_ERROR_MESSAGE = "???は!!!バイト以下で設定しでください。<BR>";
	
	/** 日付形式 */
	public static String CONST_DATE_ERROR_MESSAGE = "???が!!!形式ではありません。<BR>";
	
	/** 番号形式 */
	public static String CONST_NUMBER_ERROR_MESSAGE = "???が正式な形式ではありません。<BR>";
	
	/** 半角英数字形式 */
	public static String CONST_HANKAKU_EISUJI_ERROR_MESSAGE = "???は半角英数字のみで設定してください。<BR>";
	
	/** 分類マスタ(整合性) */
	public static String CONST_MST_GROUP_INREGRITY_ERROR_MESSAGE = "???が分類マスタに存在しません。 値：!!!<BR>";
	
	/** ファイル読み込み */
	public static String CONST_FILE_READ_ERROR_MESSAGE = "ファイルの読み込みに失敗しました。ファイル名:???";
	
	/** ファイル形式 */
	public static String CONST_FILE_SUFFIX_ERROR_MESSAGE = "???ファイルのみ設定してください。<BR>";
	
	/** ファイルサイズ */
	public static String CONST_FILE_SIZE_ERROR_MESSAGE = "ファイルが0サイズです。<BR>";
	
	/** データ個数 */
	public static String CONST_DETAIL_ITEM_COUNT_ERROR_MESSAGE = "明細の個数が正しくありません。<BR>";
	
	/**
	 *  メッセージの作成。
	 *  @param message メッセージ
	 *  @return メッセージ
	 */
	public static String createMessage(String message) {
		return message;
	}
	
	/**
	 *  メッセージの作成。
	 *  @param message メッセージ
	 *  @param param1 パラメータ1
	 *  @return メッセージ
	 */
	public static String createMessage(String message, String param1) {
		return message.replace("???", param1);
	}
	
	/**
	 *  メッセージの作成。
	 *  @param message メッセージ
	 *  @param param1 パラメータ1
	 *  @param param2 パラメータ2
	 *  @return メッセージ
	 */
	public static String createMessage(String message, String param1, String param2) {
		return message.replace("???", param1).replace("!!!", param2);
	}
}
