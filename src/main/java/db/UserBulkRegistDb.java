package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import bean.TUserBulkBean;
import bean.UserBulkRegistBean;
import bean.UserBulkRegistDetailBean;
import common.Common;
import common.DBManager;
import common.PropertyUtil;

/**
 *  ユーザ一括登録のDBクラスです。
 *  @version 1.0
 *  @since 2024/03/09
 *  @author k.yamada
 */
public class UserBulkRegistDb {
	
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
	 * @param userID ユーザID
	 * @param userInfo ユーザ情報
	 */
	public void registUserInfo(String userId, String[] detailData) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
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
			preparedStatement.setString(1, userId);
			// ユーザ名
			preparedStatement.setString(2, userName);
			// 誕生日
			preparedStatement.setString(3, birthDay);
			// パスワード
			preparedStatement.setString(4, password);
			// 住所1
			preparedStatement.setString(5, address1);
			// 住所2
			preparedStatement.setString(6, address2);
			// 郵便番号
			preparedStatement.setString(7, postCode);
			// 電話番号
			preparedStatement.setString(8, tel);
			// メールアドレス
			preparedStatement.setString(9, mailAddress);
			// 性別
			preparedStatement.setString(10, sex);
			// メモ
			preparedStatement.setString(11, memo);
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
	 * ユーザ一括情報の取得。
	 * @param userBulkRegistBean ユーザ一括情報
	 * @return ユーザ一括登録明細情報
	 * @throws Exception 
	 */
	@SuppressWarnings("resource")
	public List<UserBulkRegistDetailBean> getUserDetailInfo(UserBulkRegistBean userBulkInfo) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int offset = Integer.parseInt(PropertyUtil.getProperty("offset"));
		
		List<UserBulkRegistDetailBean> detailList = new ArrayList<UserBulkRegistDetailBean>();
		
		try {
    		// ORACLEのDBを接続
			connection = DBManager.getConnection();
			
			// SQLを定義
			StringBuilder sql = new StringBuilder();
			StringBuilder dataSql = new StringBuilder();
			StringBuilder countSql = new StringBuilder();
			
			dataSql.append("SELECT ");
			dataSql.append("    * ");
			
			countSql.append("SELECT ");
			countSql.append("    COUNT(*) DATA_COUNT ");
			
			sql.append("FROM ");
			sql.append(" ( ");
			sql.append("SELECT ");
			sql.append("    USR.REGIST_NO ");
			sql.append("  , GROUP1.KBN_NAME AS RESULT ");
			sql.append("  , TO_CHAR(USR.UPLOAD_DATE_TIME, 'YYYY/MM/DD HH24:MI:SS') AS UPLOAD_DATE_TIME ");
			sql.append("  , PROC_CNT ");
			sql.append("  , USR.CSV_PATH ");
			sql.append("  , ROWNUM NUM ");
			sql.append("FROM ");
			sql.append("    T_USER_BULK USR ");
			sql.append("LEFT JOIN ");
			sql.append("    MST_GROUP GROUP1 ");
			sql.append("ON  GROUP1.KBN = USR.RESULT ");
			sql.append("AND GROUP1.GROUP_ID = 'UPLOAD_RESULT' ");
			sql.append("WHERE ");
			sql.append("    1 = 1 ");
			
			// 結果
			if(!"".equals(userBulkInfo.getResult())) {
				sql.append("AND USR.RESULT = \'");
				sql.append(userBulkInfo.getResult());
				sql.append("\' ");
			}
			
			// 誕生日(FROM)
			if(!"".equals(userBulkInfo.getUploadDateTimeFrom())) {
				sql.append("AND TO_CHAR(USR.UPLOAD_DATE_TIME, 'YYYYMMDD') >= \'");
				sql.append(userBulkInfo.getUploadDateTimeFrom());
				sql.append("\' ");
			}
			
			// 誕生日(TO)
			if(!"".equals(userBulkInfo.getUploadDateTimeTo())) {
				sql.append("AND TO_CHAR(USR.UPLOAD_DATE_TIME, 'YYYYMMDD') <= \'");
				sql.append(userBulkInfo.getUploadDateTimeTo());
				sql.append("\' ");
			}
			
			sql.append("ORDER BY ");
			sql.append("    TO_NUMBER(USR.REGIST_NO) ");
			sql.append(" ) ");
			
            // ステートメントを作成
			preparedStatement = connection.prepareStatement(countSql.toString() + sql.toString());
			
			// SQLを実行し、実行結果を受け取る
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				// 検索数
				userBulkInfo.setDataCount(resultSet.getLong("DATA_COUNT"));
			}

            // ステートメントを作成
			preparedStatement = connection.prepareStatement(dataSql.toString() + sql.toString());
			
			// SQLを実行し、実行結果を受け取る
			resultSet = preparedStatement.executeQuery();
			
			// ページ数
			if(!"".equals(userBulkInfo.getCurrentPage())) {
				sql.append("WHERE ");
				sql.append("    NUM BETWEEN ");
				sql.append((Integer.parseInt(userBulkInfo.getCurrentPage()) * offset - (offset - 1)) + " AND ");
				sql.append((Integer.parseInt(userBulkInfo.getCurrentPage()) * offset) + " ");
			}
			
            // ステートメントを作成
			preparedStatement = connection.prepareStatement(dataSql.toString() + sql.toString());
			
			// SQLを実行し、実行結果を受け取る
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				// ユーザ一括登録明細Bean
				UserBulkRegistDetailBean userBulkRegistDetailBean = new UserBulkRegistDetailBean();
				
				// 登録NO
				userBulkRegistDetailBean.setRegistNo(Common.nullToEmpty(resultSet.getString("REGIST_NO")));
				// 結果
				userBulkRegistDetailBean.setResult(Common.nullToEmpty(resultSet.getString("RESULT")));
				// アップロード日時
				userBulkRegistDetailBean.setUploadDateTime(Common.nullToEmpty(resultSet.getString("UPLOAD_DATE_TIME")));
				// 処理件数
				userBulkRegistDetailBean.setProcCnt(resultSet.getLong("PROC_CNT"));
				// CSVパス
				userBulkRegistDetailBean.setCsvPath(Common.nullToEmpty(resultSet.getString("CSV_PATH")));
				
				// リストに追加
				detailList.add(userBulkRegistDetailBean);
			}
			
			return detailList;
			
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
	 * 登録NOの取得。
	 * @return 登録NO
	 * @throws Exception
	 */
	public String getRegistNo() throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		// 登録NO
		String registNo = null;
		
		try {
    		// ORACLEのDBを接続
			connection = DBManager.getConnection();
			
			// SQLを定義
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("    SEQ_USER_REGIST_NO.NEXTVAL AS REGIST_ID ");
			sql.append("FROM ");
			sql.append("    DUAL ");

            // ステートメントを作成
			preparedStatement = connection.prepareStatement(sql.toString());

			// SQLを実行し、実行結果を受け取る
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				// 登録NO
				registNo = Common.nullToEmpty(resultSet.getString("REGIST_ID"));
			}
			
			return registNo;
			
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
	 * 一括ユーザの登録。
	 * @param tUserBulkInfo ユーザ一括情報
	 */
	public void registBulkUserInfo(TUserBulkBean tUserBulkInfo) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
    		// ORACLEのDBを接続
			connection = DBManager.getConnection();
			
			// SQLを定義
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO ");
			sql.append("  T_USER_BULK ");
			sql.append("  ( ");
			sql.append("     REGIST_NO ");
			sql.append("    ,RESULT ");
			sql.append("    ,UPLOAD_DATE_TIME ");
			sql.append("    ,PROC_CNT ");
			sql.append("    ,CSV_PATH ");
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
			sql.append("  ) ");
			
			// オートコミットオフ
			connection.setAutoCommit(false);

            // SQLを実行し、実行結果を受け取る
			preparedStatement = connection.prepareStatement(sql.toString());

			// 登録NO
			preparedStatement.setString(1, tUserBulkInfo.getRegistNo());
			// 結果(処理中)
			preparedStatement.setString(2, "4");
			// アップロード日時
			preparedStatement.setDate(3, new Date(Calendar.getInstance().getTimeInMillis()));
			// 処理件数
			preparedStatement.setLong(4, 0);
			// CSVパス
			preparedStatement.setString(5, null);
			// 作成日時
			preparedStatement.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
			// 更新日時
			preparedStatement.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
            
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
	 * ユーザ一括情報の更新。
	 * @param tUserBulkInfo ユーザ情報
	 * @param procCnt 処理件数
	 * @param errCnt エラー件数
	 * @param scvPath CSVパス
	 */
	public void updateBulkUserInfo(TUserBulkBean tUserBulkInfo, long procCnt, long errCnt, String scvPath) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
    		// ORACLEのDBを接続
			connection = DBManager.getConnection();
			
			// SQLを定義
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE ");
			sql.append("  T_USER_BULK ");
			sql.append("SET ");
			sql.append("   RESULT = ? ");
			sql.append(" , PROC_CNT = ? ");
			sql.append(" , CSV_PATH = ? ");
			sql.append(" , UPDATE_DATE_TIME = ? ");
			sql.append("WHERE ");
			sql.append("   REGIST_NO = ? ");
			
			// オートコミットオフ
			connection.setAutoCommit(false);

            // SQLを実行し、実行結果を受け取る
			preparedStatement = connection.prepareStatement(sql.toString());

			// 結果
			if(errCnt == 0) {
				preparedStatement.setString(1, "1");
			} else if(procCnt == errCnt) {
				preparedStatement.setString(1, "2");
			} else if(procCnt != errCnt) {
				preparedStatement.setString(1, "3");
			}
			// 処理件数
			preparedStatement.setLong(2, procCnt);
			// CSVパス
			preparedStatement.setString(3, scvPath);
			// 更新日時
			preparedStatement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			
			// 登録NO
			preparedStatement.setString(5, tUserBulkInfo.getRegistNo());
            
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
}