package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import bean.UserRegistBean;
import common.Common;
import common.DBManager;

/**
 *  ユーザ登録のDBクラスです。
 *  @version 1.0
 *  @since 2024/02/25
 *  @author k.yamada
 */
public class UserRegistDb {
	
	/**
	 * 登録するユーザIDの取得。
	 * @return ユーザID
	 * @throws Exception
	 */
	public String getUserId() throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		// ユーザID
		String userId = null;
		
		try {
    		// ORACLEのDBを接続
			connection = DBManager.getConnection();
			
			// SQLを定義
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("    'A' || TO_CHAR(SEQ_USER_ID.NEXTVAL,'FM0999999') AS USER_ID ");
			sql.append("FROM ");
			sql.append("    DUAL ");

            // ステートメントを作成
			preparedStatement = connection.prepareStatement(sql.toString());

			// SQLを実行し、実行結果を受け取る
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				// ユーザID
				userId = Common.nullToEmpty(resultSet.getString("USER_ID"));
			}
			
			return userId;
			
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			// 接続を切断
			DBManager.close(resultSet);
			DBManager.close(preparedStatement);
			DBManager.close(connection);
		}
	}
	
	/**
	 * ユーザ情報の登録。
	 * @param userInfo ユーザ情報
	 */
	public void registUserInfo(UserRegistBean userInfo) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
    		// ORACLEのDBを接続
			connection = DBManager.getConnection();
			
			// SQLを定義
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO ");
			sql.append("  MST_USER ");
			sql.append("  ( ");
			sql.append("     USER_ID ");
			sql.append("    ,USER_NAME ");
			sql.append("    ,BIRTH_DAY ");
			sql.append("    ,PASSWORD ");
			sql.append("    ,ADDRESS1 ");
			sql.append("    ,ADDRESS2 ");
			sql.append("    ,POST_CODE ");
			sql.append("    ,TEL ");
			sql.append("    ,MAIL_ADDRESS ");
			sql.append("    ,SEX ");
			sql.append("    ,MEMO ");
			sql.append("    ,CREATE_DATE_TIME ");
			sql.append("    ,UPDATE_DATE_TIME ");
			sql.append("  ) ");
			sql.append("VALUES ");
			sql.append("  ( ");
			sql.append("     ? ");
			sql.append("    ,? ");
			sql.append("    ,? ");
			sql.append("    ,? ");
			sql.append("    ,? ");
			sql.append("    ,? ");
			sql.append("    ,? ");
			sql.append("    ,? ");
			sql.append("    ,? ");
			sql.append("    ,? ");
			sql.append("    ,? ");
			sql.append("    ,? ");
			sql.append("    ,? ");
			sql.append("  ) ");
			
			// オートコミットオフ
			connection.setAutoCommit(false);

            // SQLを実行し、実行結果を受け取る
			preparedStatement = connection.prepareStatement(sql.toString());

			// ユーザID
			preparedStatement.setString(1, userInfo.getUserId());
			// ユーザ名
			preparedStatement.setString(2, userInfo.getUserName());
			// 誕生日
			preparedStatement.setString(3, userInfo.getBirthDay());
			// パスワード
			preparedStatement.setString(4, userInfo.getPassword());
			// 住所1
			preparedStatement.setString(5, userInfo.getAddress1());
			// 住所2
			preparedStatement.setString(6, userInfo.getAddress2());
			// 郵便番号
			preparedStatement.setString(7, userInfo.getPostCode());
			// 電話番号
			preparedStatement.setString(8, userInfo.getTel());
			// メールアドレス
			preparedStatement.setString(9, userInfo.getMailAddress());
			// 性別
			preparedStatement.setString(10, userInfo.getSex());
			// メモ
			preparedStatement.setString(11, userInfo.getMemo());
			// 作成日時
			preparedStatement.setTimestamp(12, new Timestamp(System.currentTimeMillis()));
			// 更新日時
			preparedStatement.setTimestamp(13, new Timestamp(System.currentTimeMillis()));
            
            // SQLを実行
			preparedStatement.executeUpdate();
			
			// コミット
			connection.commit();
			
		}catch(Exception e) {
			
			try {
				// ロールバック
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw e;
		}finally {
			// 接続を切断
			DBManager.close(resultSet);
			DBManager.close(preparedStatement);
			DBManager.close(connection);
		}
	}
	
	/**
	 *  ユーザ情報の更新。
	 *  @param userInfo ユーザ情報
	 */
	public void updateUserInfo(UserRegistBean userInfo) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
    		// ORACLEのDBを接続
			connection = DBManager.getConnection();
			
			// SQLを定義
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE ");
			sql.append("  MST_USER ");
			sql.append("SET ");
			sql.append("   USER_NAME = ? ");
			sql.append(" , BIRTH_DAY = ? ");
			sql.append(" , PASSWORD = ? ");
			sql.append(" , UPDATE_DATE_TIME = ? ");
			sql.append(" , TEL = ? ");
			sql.append(" , POST_CODE = ? ");
			sql.append(" , ADDRESS1 = ? ");
			sql.append(" , ADDRESS2 = ? ");
			sql.append(" , MEMO = ? ");
			sql.append(" , MAIL_ADDRESS = ? ");
			sql.append(" , SEX = ? ");
			sql.append("WHERE ");
			sql.append("   USER_ID = ? ");
			
			// オートコミットオフ
			connection.setAutoCommit(false);

            // SQLを実行し、実行結果を受け取る
			preparedStatement = connection.prepareStatement(sql.toString());

			// ユーザ名
			preparedStatement.setString(1, userInfo.getUserName());
			// 誕生日
			preparedStatement.setString(2, userInfo.getBirthDay());
			// パスワード
			preparedStatement.setString(3, userInfo.getPassword());
			// 更新日時
			preparedStatement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			// 電話番号
			preparedStatement.setString(5, userInfo.getTel());
			// 郵便番号
			preparedStatement.setString(6, userInfo.getPostCode());
			// 住所1
			preparedStatement.setString(7, userInfo.getAddress1());
			// 住所2
			preparedStatement.setString(8, userInfo.getAddress2());
			// メモ
			preparedStatement.setString(9, userInfo.getMemo());
			// メールアドレス
			preparedStatement.setString(10, userInfo.getMailAddress());
			// 性別
			preparedStatement.setString(11, userInfo.getSex());
			
			// ユーザID
			preparedStatement.setString(12, userInfo.getUserId());
            
            // SQLを実行
			preparedStatement.executeUpdate();
			
			// コミット
			connection.commit();
			
		}catch(Exception e) {
			
			try {
				// ロールバック
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw e;
		}finally {
			// 接続を切断
			DBManager.close(resultSet);
			DBManager.close(preparedStatement);
			DBManager.close(connection);
		}
	}
	
	/**
	 *  ユーザ情報の取得。
	 *  @param ユーザID
	 *  @return ユーザ登録情報
	 */
	public UserRegistBean getUserInfo(String userId) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		UserRegistBean userRegistBean = new UserRegistBean();
		
		try {
    		// ORACLEのDBを接続
			connection = DBManager.getConnection();
			
			// SQLを定義
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("    USER_ID ");
			sql.append("  , USER_NAME ");
			sql.append("  , TO_CHAR(BIRTH_DAY, 'YYYYMMDD') AS BIRTH_DAY ");
			sql.append("  , PASSWORD ");
			sql.append("  , TEL ");
			sql.append("  , POST_CODE ");
			sql.append("  , ADDRESS1 ");
			sql.append("  , ADDRESS2 ");
			sql.append("  , SEX ");
			sql.append("  , MEMO ");
			sql.append("  , MAIL_ADDRESS ");
			sql.append("FROM ");
			sql.append("    MST_USER ");
			sql.append("WHERE ");
			sql.append("    USER_ID = ? ");

            // ステートメントを作成
			preparedStatement = connection.prepareStatement(sql.toString());
			
			// プレースホルダに値をバインド
            preparedStatement.setString(1, userId);
			
			// SQLを実行し、実行結果を受け取る
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				// ユーザID
				userRegistBean.setUserId(Common.nullToEmpty(resultSet.getString("USER_ID")));
				// ユーザ名
				userRegistBean.setUserName(Common.nullToEmpty(resultSet.getString("USER_NAME")));
				// 誕生日
				userRegistBean.setBirthDay(Common.nullToEmpty(resultSet.getString("BIRTH_DAY")));
				// パスワード
				userRegistBean.setPassword(Common.nullToEmpty(resultSet.getString("PASSWORD")));
				// 電話番号
				userRegistBean.setTel(Common.nullToEmpty(resultSet.getString("TEL")));
				// 郵便番号
				userRegistBean.setPostCode(Common.nullToEmpty(resultSet.getString("POST_CODE")));
				// 住所1
				userRegistBean.setAddress1(Common.nullToEmpty(resultSet.getString("ADDRESS1")));
				// 住所2
				userRegistBean.setAddress2(Common.nullToEmpty(resultSet.getString("ADDRESS2")));
				// メモ
				userRegistBean.setMemo(Common.nullToEmpty(resultSet.getString("MEMO")));
				// メールアドレス
				userRegistBean.setMailAddress(Common.nullToEmpty(resultSet.getString("MAIL_ADDRESS")));
				// 性別
				userRegistBean.setSex(Common.nullToEmpty(resultSet.getString("SEX")));
			}
			
			return userRegistBean;
			
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			// 接続を切断
			DBManager.close(resultSet);
			DBManager.close(preparedStatement);
			DBManager.close(connection);
		}
	}
}