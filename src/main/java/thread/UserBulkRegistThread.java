package thread;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import bean.TUserBulkBean;
import check.UserBulkRegistCheck;
import common.Common;
import db.UserBulkRegistDb;
import jakarta.servlet.http.Part;

/**
 *  ユーザ一括登録(非同期)のクラスです。
 *  @version 1.0
 *  @since 2024/03/07
 *  @author k.yamada
 */
public class UserBulkRegistThread extends Thread {
	
	/** CSVファイル */
	private Part part;
	
	public UserBulkRegistThread(Part part) {
		this.part = part;
    }

	@Override
    public void run() {
		
		BufferedReader br = null;
		
		try {
			// csv読み込み
	        InputStream is = null;
			try {
				is = part.getInputStream();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        InputStreamReader isr = new InputStreamReader(is);
	        br = new BufferedReader(isr);
	        String line;
	        
	        // 処理件数
	        long cnt = 0;
	        
	        // エラー件数
	        long errCnt = 0;
        	
        	// ユーザ一括登録のチェッククラス
        	UserBulkRegistCheck check = new UserBulkRegistCheck();
        	
        	// ユーザ一括登録のDBクラス
        	UserBulkRegistDb db = new UserBulkRegistDb();
        	
        	// 下記で示すディレクトリに一時ファイルを生成  
            File tempFile = File.createTempFile("prefix", ".csv");
            
            // FileWriterクラスのオブジェクトを生成
            FileWriter file = new FileWriter(tempFile.getPath());
            
            // PrintWriterクラスのオブジェクトを生成する
            PrintWriter pw = new PrintWriter(new BufferedWriter(file));
            
            // 一括ユーザBean
            TUserBulkBean tUserBulkBean = new TUserBulkBean();
            
            // 登録NOを設定
            tUserBulkBean.setRegistNo(db.getRegistNo());
            
            // 一括ユーザの登録
            db.registBulkUserInfo(tUserBulkBean);
        	
        	// CSVファイルをループ
			while ((line = br.readLine()) != null) {
				// カウントアップ
				cnt++;
			    if(cnt==1) {
			    	// ファイルに書き込む
		            pw.println("明細行の各結果を下記に記載");
			    	
			    	// ヘッダは読み飛ばし
			    	continue;
			    }
			    // 行データ
			    String[] detailData = line.split(",", 0);
			    
			    // 明細行のチェック処理
			    String message = check.checkDetailParameter(detailData);
			    
			    // ファイルに書き込む
	            pw.println(cnt + "行目：" + message);
	            
	            // メッセージがない場合
	            if(Common.checkStrEmpty(message)) {
	            	// ユーザ情報の登録
	            	db.registUserInfo(db.getUserId(), detailData);
	            } else {
	            	errCnt++;
	            }
			}
			
			//　ファイルを閉じる
            pw.close();
		
			Thread.sleep(10000);
			
			// ユーザ一括情報の更新
			db.updateBulkUserInfo(tUserBulkBean, cnt-1, errCnt, tempFile.getPath());
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        System.out.println("非同期的に処理を実行します。");
    }
}