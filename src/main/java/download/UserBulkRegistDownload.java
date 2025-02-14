package download;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.http.HttpServletResponse;

/**
 *  ユーザ一括登録のダウンロードクラスです。
 *  @version 1.0
 *  @since 2024/03/11
 *  @author k.yamada
 */
public class UserBulkRegistDownload {
	
	/** BufferedInputStream */
	private BufferedInputStream in;
	
	/** BufferedOutputStream */
	private BufferedOutputStream out;
	
	/**
	 * CSV情報作成。
	 * @param csvPath CSVパス
	 * @param response レスポンス
	 * @throws Exception 
	 */
	public void createCsvInfo(String csvPath, HttpServletResponse response) throws Exception {
		
		try{
			DateTimeFormatter dtformat = 
					DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
				
			// 現在日時
			String nowDate = dtformat.format(LocalDateTime.now());
			
			// ファイル名作成
			String fileName = "ユーザ一括登録_" + nowDate + ".csv";
	    	
	    	// 文字コードと出力するCSVファイル名を設定
	        response.setContentType("text/html; charset=UTF-8");
	        response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileName, "UTF-8") + "\"");
	        
	        // サーバ側のファイルをローカルに出力する場合
	    	in = new BufferedInputStream(new FileInputStream(csvPath));
	    	out = new BufferedOutputStream(response.getOutputStream());

	    	int x;
	    	while ((x = in.read()) >= 0) {
	    		out.write(x);
	    	}
	        
		} catch (Exception e) {
			throw e;
		} finally {
			// クローズ
			if(in != null) {
				try{
					in.close();
				} catch(IOException e){
					throw e;
				}
			}
			
			if(out != null) {
				try{
					out.close();
				} catch(IOException e){
					throw e;
				}
				
			}
		}
	}
}