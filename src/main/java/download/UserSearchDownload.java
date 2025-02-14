package download;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import bean.UserSearchDetailBean;
import jakarta.servlet.http.HttpServletResponse;

/**
 *  ユーザ検索のダウンロードクラスです。
 *  @version 1.0
 *  @since 2024/03/04
 *  @author k.yamada
 */
public class UserSearchDownload {
	
	/**
	 *  CSV情報作成。
	 * @param ユーザ検索明細情報
	 * @param レスポンス
	 * @throws Exception 
	 */
	public void createCsvInfo(List<UserSearchDetailBean> detalList, 
			HttpServletResponse response) throws Exception {
		
		DateTimeFormatter dtformat = 
				DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
			
		// 現在日時
		String nowDate = dtformat.format(LocalDateTime.now());
		
		// ファイル名作成
		String fileName = "ユーザ検索_" + nowDate + ".csv";
		
		// 文字コードと出力するCSVファイル名を設定
        response.setContentType("text/html; charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileName, "UTF-8") + "\"");

        //try-with-resources文を使うことでclose処理を自動化
        try (PrintWriter pw = response.getWriter()) {
        	// CSVファイルに書き込み(ヘッダ)
        	String csvHead = "ユーザID,ユーザ名,誕生日,性別,電話番号,住所";
        	// 出力
            pw.println(csvHead);
            
        	for(UserSearchDetailBean list : detalList) {
        		// ユーザID
        		String userId = list.getUserId();
        		// ユーザ名
        		String userName = list.getUserName();
        		// 誕生日
        		String birthDay = list.getBirthDay();
        		// 性別
        		String sex = list.getSex();
        		// 電話番号
        		String tel = list.getTel();
        		// 住所
        		String address = list.getAddress();
        		
        		// CSVファイルに書き込み(明細)
        		String csvDetail = userId + "," +
        		                   userName + "," +
        		                   birthDay + "," +
        		                   sex + "," +
        		                   tel + "," +
        		                   address;
        		// 出力
                pw.println(csvDetail);
                
                // フラッシュ
                pw.flush();
        	}
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
	}
}