package check;

import bean.UserSearchBean;
import common.Common;
import common.Const;

/**
 *  ユーザ検索のチェッククラスです。
 *  @version 1.0
 *  @since 2024/02/27
 *  @author k.yamada
 */
public class UserSearchCheck {
	
	/**
	 *  登録時のチェック処理
	 *  @param ユーザ情報
	 *  @return true：チェック成功 false：チェック異常
	 */
	public boolean checkParameter(UserSearchBean UuserInfo) {
		
		String message = "";
		
		// 誕生日(FROM)が日付形式か
		if(!Common.checkStrEmpty(UuserInfo.getBirthDayFrom())) {
			if(!Common.checkStrYyyyMmDd(UuserInfo.getBirthDayFrom())) {
				message += Const.createMessage(Const.CONST_DATE_ERROR_MESSAGE, "誕生日(開始)", "YYYYMMDD");
				UuserInfo.setKbn("2");
			}
		}
		
		// 誕生日(TO)が日付形式か
		if(!Common.checkStrEmpty(UuserInfo.getBirthDayTo())) {
			if(!Common.checkStrYyyyMmDd(UuserInfo.getBirthDayTo())) {
				message += Const.createMessage(Const.CONST_DATE_ERROR_MESSAGE, "誕生日(終了)", "YYYYMMDD");
				UuserInfo.setKbn("2");
			}
		}
		
		UuserInfo.setMessage(message);
		
		if("2".equals(UuserInfo.getKbn())) {
			return false;
		} else {
			return true;
		}
	}
}