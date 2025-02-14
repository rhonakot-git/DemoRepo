package check;

import java.io.UnsupportedEncodingException;

import bean.MstGroupBean;
import bean.UserBulkRegistBean;
import common.Common;
import common.Const;
import db.MstGroupDb;
import jakarta.servlet.http.Part;

/**
 *  ユーザ一括登録のチェッククラスです。
 *  @version 1.0
 *  @since 2024/03/07
 *  @author k.yamada
 */
public class UserBulkRegistCheck {
	
	/**
	 * 登録時のチェック処理
	 * @param userBulkRegistBean ユーザ一括情報
	 * @param part ファイル
	 * @return true：チェック成功 false：チェック異常
	 * @throws UnsupportedEncodingException 
	 */
	public boolean checkParameter(UserBulkRegistBean userBulkRegistBean, Part part) throws UnsupportedEncodingException {
		
		String message = "";
		
		// アップロード日時(FROM)が日付形式か
		if(!Common.checkStrEmpty(userBulkRegistBean.getUploadDateTimeFrom())) {
			if(!Common.checkStrYyyyMmDd(userBulkRegistBean.getUploadDateTimeFrom())) {
				message += Const.createMessage(Const.CONST_DATE_ERROR_MESSAGE, "アップロード日時(開始)", "YYYYMMDD");
				userBulkRegistBean.setKbn("2");
			}
		}
		
		// アップロード日時(TO)が日付形式か
		if(!Common.checkStrEmpty(userBulkRegistBean.getUploadDateTimeTo())) {
			if(!Common.checkStrYyyyMmDd(userBulkRegistBean.getUploadDateTimeTo())) {
				message += Const.createMessage(Const.CONST_DATE_ERROR_MESSAGE, "アップロード日時(終了)", "YYYYMMDD");
				userBulkRegistBean.setKbn("2");
			}
		}
		
		/////////////////////////////////////////
		// CSVファイル
		/////////////////////////////////////////
		// 拡張子チェック
		String suffix = Common.getSuffix(part.getSubmittedFileName());
		if(!"csv".equals(suffix)) {
			message += Const.createMessage(Const.CONST_FILE_SUFFIX_ERROR_MESSAGE, "CSV");
			userBulkRegistBean.setKbn("2");
		}
		
		// サイズチェック
		long fileSize = part.getSize();
		if(fileSize == 0) {
			message += Const.createMessage(Const.CONST_FILE_SIZE_ERROR_MESSAGE);
			userBulkRegistBean.setKbn("2");
		}
		
		userBulkRegistBean.setMessage(message);
		
		if("2".equals(userBulkRegistBean.getKbn())) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 検索時のチェック処理
	 * @param userBulkRegistBean ユーザ一括情報
	 * @return true：チェック成功 false：チェック異常
	 * @throws UnsupportedEncodingException 
	 */
	public boolean checkSearchParameter(UserBulkRegistBean userBulkRegistBean) throws UnsupportedEncodingException {
		
		String message = "";
		
		// アップロード日時(FROM)が日付形式か
		if(!Common.checkStrEmpty(userBulkRegistBean.getUploadDateTimeFrom())) {
			if(!Common.checkStrYyyyMmDd(userBulkRegistBean.getUploadDateTimeFrom())) {
				message += Const.createMessage(Const.CONST_DATE_ERROR_MESSAGE, "アップロード日時(開始)", "YYYYMMDD");
				userBulkRegistBean.setKbn("2");
			}
		}
		
		// アップロード日時(TO)が日付形式か
		if(!Common.checkStrEmpty(userBulkRegistBean.getUploadDateTimeTo())) {
			if(!Common.checkStrYyyyMmDd(userBulkRegistBean.getUploadDateTimeTo())) {
				message += Const.createMessage(Const.CONST_DATE_ERROR_MESSAGE, "アップロード日時(終了)", "YYYYMMDD");
				userBulkRegistBean.setKbn("2");
			}
		}
		
		userBulkRegistBean.setMessage(message);
		
		if("2".equals(userBulkRegistBean.getKbn())) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 登録時の明細チェック処理
	 * @param detailData 行データ
	 * @return エラーメッセージ
	 * @throws Exception 
	 */
	public String checkDetailParameter(String[] detailData) throws Exception {
		
		String message = "";
		
		MstGroupDb mstGroupDb = new  MstGroupDb();
		
		/////////////////////////////////////////
		// 個数
		/////////////////////////////////////////
		if(detailData.length != 10) {
			message += Const.createMessage(Const.CONST_DETAIL_ITEM_COUNT_ERROR_MESSAGE);
			return message.replaceAll("<BR>", ",");
		}
		
		// 項目取得
		// ユーザ名
		String userName = Common.nullToEmpty(detailData[0]);
		// 誕生日
		String birthDay = Common.nullToEmpty(detailData[1]);
		// パスワード
		String password = Common.nullToEmpty(detailData[2]);
		// 住所1
		String address1 = Common.nullToEmpty(detailData[3]);
		// 住所2
		String address2 = Common.nullToEmpty(detailData[4]);
		// 郵便番号
		String postCode = Common.nullToEmpty(detailData[5]);
		// 電話番号
		String tel = Common.nullToEmpty(detailData[6]);
		// メールアドレス
		String mailAddress = Common.nullToEmpty(detailData[7]);
		// 性別
		String sex = Common.nullToEmpty(detailData[8]);
		// メモ
		String memo = Common.nullToEmpty(detailData[9]);
		
		/////////////////////////////////////////
		// ユーザ名
		/////////////////////////////////////////
		// 必須チェック
		if(Common.checkStrEmpty(userName)) {
			message += Const.createMessage(Const.CONST_REQUIRED_ERROR_MESSAGE, "ユーザ名");
		} else if(Common.byteUtf8(userName) > 100) {
			// バイト数チェック
			message += Const.createMessage(Const.CONST_BYTE_NUM_ERROR_MESSAGE, "ユーザ名", "100");
		}
		
		/////////////////////////////////////////
		// 誕生日
		/////////////////////////////////////////
		// 必須チェック
		if(Common.checkStrEmpty(birthDay)) {
			message += Const.createMessage(Const.CONST_REQUIRED_ERROR_MESSAGE, "誕生日");
		} else if(!Common.checkStrYyyyMmDd(birthDay)) {
			// 日付形式チェック
			message += Const.createMessage(Const.CONST_DATE_ERROR_MESSAGE, "誕生日", "YYYYMMDD");
		}
		
		/////////////////////////////////////////
		// パスワード
		/////////////////////////////////////////
		// 必須チェック
		if(Common.checkStrEmpty(password)) {
			message += Const.createMessage(Const.CONST_REQUIRED_ERROR_MESSAGE, "パスワード");
		} else if(!Common.checkHankakuEisu(password)) {
			// 半角英数字チェック
			message += Const.createMessage(Const.CONST_HANKAKU_EISUJI_ERROR_MESSAGE, "パスワード");
		}
		
		/////////////////////////////////////////
		// 住所1
		/////////////////////////////////////////
		// 必須チェック
		if(Common.checkStrEmpty(address1)) {
			message += Const.createMessage(Const.CONST_REQUIRED_ERROR_MESSAGE, "住所1");
		} else if(Common.byteUtf8(address1) > 150) {
			// バイト数チェック
			message += Const.createMessage(Const.CONST_BYTE_NUM_ERROR_MESSAGE, "住所1", "150");
		}
		
		/////////////////////////////////////////
		// 住所2
		/////////////////////////////////////////
		// 必須チェック
		if(Common.checkStrEmpty(address2)) {
			message += Const.createMessage(Const.CONST_REQUIRED_ERROR_MESSAGE, "住所2");
		} else if(Common.byteUtf8(address2) > 150) {
			// バイト数チェック
			message += Const.createMessage(Const.CONST_BYTE_NUM_ERROR_MESSAGE, "住所2", "150");
		}
		
		/////////////////////////////////////////
		// 郵便番号
		/////////////////////////////////////////
		// 必須チェック
		if(Common.checkStrEmpty(postCode)) {
			message += Const.createMessage(Const.CONST_REQUIRED_ERROR_MESSAGE, "郵便番号");
		} else if(!Common.checkPostCode(postCode)) {
			// 形式チェック
			message += Const.createMessage(Const.CONST_NUMBER_ERROR_MESSAGE, "郵便番号");
		}
		
		/////////////////////////////////////////
		// 電話番号
		/////////////////////////////////////////
		// 必須チェック
		if(Common.checkStrEmpty(tel)) {
			message += Const.createMessage(Const.CONST_REQUIRED_ERROR_MESSAGE, "電話番号");
		} else if(!Common.checkTel(tel)) {
			// 形式チェック
			message += Const.createMessage(Const.CONST_NUMBER_ERROR_MESSAGE, "電話番号");
		}
		
		/////////////////////////////////////////
		// メールアドレス
		/////////////////////////////////////////
		// 必須チェック
		if(Common.checkStrEmpty(mailAddress)) {
			message += Const.createMessage(Const.CONST_REQUIRED_ERROR_MESSAGE, "電話番号");
		} else if(!Common.checkMailAddress(mailAddress)) {
			// 形式チェック
			message += Const.createMessage(Const.CONST_NUMBER_ERROR_MESSAGE, "メールアドレス");
		}
		
		/////////////////////////////////////////
		// 性別
		/////////////////////////////////////////
		try {
			// 必須チェック
			if(Common.checkStrEmpty(sex)) {
				message += Const.createMessage(Const.CONST_REQUIRED_ERROR_MESSAGE, "性別");
			} else {
				// 整合性チェック
				MstGroupBean mstGroupData = mstGroupDb.getMstGroupData("SEX", sex, false);
				if(Common.checkStrEmpty(mstGroupData.getKbn())) {
					message += Const.createMessage(Const.CONST_MST_GROUP_INREGRITY_ERROR_MESSAGE, "性別", sex);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		/////////////////////////////////////////
		// メモ
		/////////////////////////////////////////
		// バイト数チェック
		if(Common.byteUtf8(memo) > 100) {
			message += Const.createMessage(Const.CONST_BYTE_NUM_ERROR_MESSAGE, "メモ", "300");
		}
		
		return message.replaceAll("<BR>", ",");
	}
}