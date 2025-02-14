package check;

import java.io.UnsupportedEncodingException;

import bean.UserRegistBean;
import common.Common;
import common.Const;

/**
 *  ユーザ登録のチェッククラスです。
 *  @version 1.0
 *  @since 2024/02/27
 *  @author k.yamada
 */
public class UserRegistCheck {
	
	/**
	 * 登録時のチェック処理
	 * @param userInfo ユーザ情報
	 * @return true：チェック成功 false：チェック異常
	 * @throws UnsupportedEncodingException 
	 */
	public boolean checkParameter(UserRegistBean userInfo) throws UnsupportedEncodingException {
		
		String message = "";
		
		/////////////////////////////////////////
		// ユーザ名
		/////////////////////////////////////////
		// バイト数チェック
		if(Common.byteUtf8(userInfo.getUserName()) > 100) {
			message += Const.createMessage(Const.CONST_BYTE_NUM_ERROR_MESSAGE, "ユーザ名", "100");
			userInfo.setKbn("2");
		}
		
		/////////////////////////////////////////
		// パスワード
		/////////////////////////////////////////
		// 半角英数字チェック
		if(!Common.checkHankakuEisu(userInfo.getPassword())) {
			message += Const.createMessage(Const.CONST_HANKAKU_EISUJI_ERROR_MESSAGE, "パスワード");
			userInfo.setKbn("2");
		}
		
		/////////////////////////////////////////
		// 誕生日
		/////////////////////////////////////////
		// 日付形式チェック
		if(!Common.checkStrYyyyMmDd(userInfo.getBirthDay())) {
			message += Const.createMessage(Const.CONST_DATE_ERROR_MESSAGE, "誕生日", "YYYYMMDD");
			userInfo.setKbn("2");
		}
		
		/////////////////////////////////////////
		// 電話番号
		/////////////////////////////////////////
		// 形式チェック
		if(!Common.checkTel(userInfo.getTel())) {;
			message += Const.createMessage(Const.CONST_NUMBER_ERROR_MESSAGE, "電話番号");
			userInfo.setKbn("2");
		}
		
		/////////////////////////////////////////
		// メールアドレス
		/////////////////////////////////////////
		// 形式チェック
		if(!Common.checkMailAddress(userInfo.getMailAddress())) {;
			message += Const.createMessage(Const.CONST_NUMBER_ERROR_MESSAGE, "メールアドレス");
			userInfo.setKbn("2");
		}
		
		/////////////////////////////////////////
		// 郵便番号
		/////////////////////////////////////////
		// 形式チェック
		if(!Common.checkPostCode(userInfo.getPostCode())) {;
			message += Const.createMessage(Const.CONST_NUMBER_ERROR_MESSAGE, "郵便番号");
			userInfo.setKbn("2");
		}
		
		/////////////////////////////////////////
		// 住所1
		/////////////////////////////////////////
		// バイト数チェック
		if(Common.byteUtf8(userInfo.getUserName()) > 150) {
			message += Const.createMessage(Const.CONST_BYTE_NUM_ERROR_MESSAGE, "住所1", "150");
			userInfo.setKbn("2");
		}
		
		/////////////////////////////////////////
		// 住所2
		/////////////////////////////////////////
		// バイト数チェック
		if(Common.byteUtf8(userInfo.getUserName()) > 150) {
			message += Const.createMessage(Const.CONST_BYTE_NUM_ERROR_MESSAGE, "住所2", "150");
			userInfo.setKbn("2");
		}
		
		/////////////////////////////////////////
		// メモ
		/////////////////////////////////////////
		// バイト数チェック
		if(Common.byteUtf8(userInfo.getMemo()) > 100) {
			message += Const.createMessage(Const.CONST_BYTE_NUM_ERROR_MESSAGE, "メモ", "300");
			userInfo.setKbn("2");
		}
		
		userInfo.setMessage(message);
		
		if("2".equals(userInfo.getKbn())) {
			return false;
		} else {
			return true;
		}
	}
}