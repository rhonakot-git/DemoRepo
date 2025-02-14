package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.LoginBean;
import common.Common;
import common.DBManager;

/**
 *  ログインのDBクラスです。
 *  @version 1.0
 *  @since 2024/02/20
 *  @author k.yamada
 */
public class LoginDb {
	
	/**
	 *  ログイン情報の取得。
	 *  @param userID ユーザID
	 *  @param password パスワード
	 *  @return ログイン情報
	 */
	public LoginBean getLoginInfo(String userID, String password) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		LoginBean loginBean = new LoginBean();
		
		try {
    		// ORACLEのDBを接続
			connection = DBManager.getConnection();
			
			// SQLを定義
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("    USER_ID ");
			sql.append("  , USER_NAME ");
			sql.append("FROM ");
			sql.append("    MST_USER ");
			sql.append("WHERE ");
			sql.append("    USER_ID = ? ");
			sql.append("AND PASSWORD = ? ");

            // ステートメントを作成
			preparedStatement = connection.prepareStatement(sql.toString());

            // プレースホルダに値をバインド
            preparedStatement.setString(1, userID);
            preparedStatement.setString(2, password);
			
			// SQLを実行し、実行結果を受け取る
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				// ユーザID
				loginBean.setUserId(Common.nullToEmpty(resultSet.getString("USER_ID")));
				// ユーザ名
				loginBean.setUserName(Common.nullToEmpty(resultSet.getString("USER_NAME")));
			}
			
			return loginBean;
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			// 接続を切断
			DBManager.close(resultSet);
			DBManager.close(preparedStatement);
			DBManager.close(connection);
		}
		return loginBean;
	}
}