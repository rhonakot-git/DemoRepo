package common;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 *  共通部品のクラスです。
 *  @version 1.0
 *  @since 2024/02/19
 *  @author k.yamada
 */
public class Common {
	
	/**
	 * 日付型を文字列に変換。
	 * @param format 日付形式
	 * @param format 日付形式
	 * @return ファイルの拡張子
	 */
	public static String changeDateStr(Date date, String format) {		
		String strDate = null; 
		
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			strDate = sdf.format(date);
		} catch(Exception ex) {
			// 変換できない場合は、YYYY/MM/DD形式
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			strDate = sdf.format(date);
		}
        
        return strDate;
	}
	
	/**
	 * 現在日付(文字列)を返却。
	 * @param format 日付形式
	 * @return ファイルの拡張子
	 */
	public static String getStrNowDate(String format) {
		// カレンダー(現在日時)
		Calendar cl = Calendar.getInstance();
		
		String strDate = null; 
		
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			strDate = sdf.format(cl.getTime());
		} catch(Exception ex) {
			// 変換できない場合は、YYYY/MM/DD形式
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			strDate = sdf.format(cl.getTime());
		}
        
        return strDate;
	}
	
	/**
	 * 現在日付から〇日後(文字列)を返却。
	 * @param format 日付形式
	 * @param addDay 日後
	 * @return ファイルの拡張子
	 */
	public static String getStrAddDate(String format, int addDay) {
		// カレンダー(現在日時)
		Calendar cl = Calendar.getInstance();
		cl.add(Calendar.DATE, addDay);
		
		String strDate = null; 
		
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			strDate = sdf.format(cl.getTime());
		} catch(Exception ex) {
			// 変換できない場合は、YYYY/MM/DD形式の現在日付
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			strDate = sdf.format(cl.getTime());
		}
        
        return strDate;
	}
	
	/**
	 * ファイル名から拡張子を返却。
	 * @param fileName ファイル名
	 * @return ファイルの拡張子
	 */
	public static String getSuffix(String fileName) {
		if(fileName == null) {
			return null;
		}
	    int point = fileName.lastIndexOf(".");
	    if (point != -1) {
	        return fileName.substring(point + 1);
	    }
	    return fileName;
	}
	
	/**
	 *  nullの文字列を空白に変換。
	 *  @param str 文字列
	 */
	public static String nullToEmpty(String str) {
        if (str == null) {
              return "";
        }
        return str;
	}
	
	/**
	 *  nullの文字列を0に変換。
	 *  @param str 文字列
	 */
	public static String nullToZero(String str) {
        if (str == null) {
              return "0";
        }
        return str;
	}
	
	/**
	 * バイト数取得(UTF-8)
	 * @param target 対象文字列
	 * @return バイト数(UTF-8)
	 * @throws UnsupportedEncodingException 
	 */
	public static int byteUtf8(String target) throws UnsupportedEncodingException{
		return nullToEmpty(target).getBytes("Shift_JIS").length;
	}
	
	/**
	 * 文字列が空白かチェック
	 * @param target 対象文字列
	 * @return true：空白 false：空白以外
	 */
	public static boolean checkStrEmpty(String target){
	  try{
		  if (target == null) {
              return true;
		  } else if("".equals(target)) {
			  return true;
		  }
        return false;
	  }catch(Exception ex){
	    return true;
	  }
	}
	
	/**
	 * 文字列がYYYYMMDD形式かチェック
	 * @param target 対象文字列
	 * @return true：YYYYMMDD形式 false：YYYYMMDD形式以外
	 */
	public static boolean checkStrYyyyMmDd(String target){
	  try{
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	    sdf.setLenient(false);
	    sdf.parse(target);
	    return true;
	  }catch(Exception ex){
	    return false;
	  }
	}
	
	/**
	 * 文字列が電話番号かチェック
	 * @param target 対象文字列
	 * @return true：電話番号 false：電話番号以外
	 */
	public static boolean checkTel(String target){
	  try{
		  Pattern pattern = Pattern.compile("^[0-9]+$");
		  return pattern.matcher(target).matches();
	  }catch(Exception ex){
		  return false;
	  }
	}
	
	/**
	 * 文字列が郵便番号かチェック
	 * @param target 対象文字列
	 * @return true：郵便番号 false：郵便番号以外
	 */
	public static boolean checkPostCode(String target){
	  try{
		  Pattern pattern = Pattern.compile("^\\d{7}$");
		  return pattern.matcher(target).matches();
	  }catch(Exception ex){
		  return false;
	  }
	}
	
	/**
	 * 文字列が半角英数字かチェック
	 * @param target 対象文字列
	 * @return true：半角英数字 false：半角英数字以外
	 */
	public static boolean checkHankakuEisu(String target){
	  try{
		  Pattern pattern = Pattern.compile("^[A-Za-z0-9]+$");
		  return pattern.matcher(target).matches();
	  }catch(Exception ex){
		  return false;
	  }
	}
	
	/**
	 * 文字列がメールアドレスかチェック
	 * @param target 対象文字列
	 * @return true：メールアドレス false：メールアドレス以外
	 */
	public static boolean checkMailAddress(String target){
	  try{
		  Pattern pattern = Pattern.compile("^([a-zA-Z0-9])+([a-zA-Z0-9\\._-])*@([a-zA-Z0-9_-])+([a-zA-Z0-9\\._-]+)+$");
		  return pattern.matcher(target).matches();
	  }catch(Exception ex){
		  return false;
	  }
	}
}
