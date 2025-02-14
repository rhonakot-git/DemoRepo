package common;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *  ORACLEDB処理のクラスです。
 *  @version 1.0
 *  @since 2024/02/20
 *  @author k.yamada
 */
public class DBManager {
	
	/** ドライバ */
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	/** URL */
	private static final String URL = "jdbc:oracle:thin:@localhost:1521/xepdb1";
	/** ユーザ名 */
	private static final String USER_NAME = PropertyUtil.getProperty("oracle.user");
	/** パスワード */
	private static final String PASSWORD = PropertyUtil.getProperty("oracle.password");
	
	/**
	 *  ORACLEのDBを接続。 
	 *  @throws ClassNotFoundException
	 *  @throws SQLException
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		
		// JVMにJDBCドライバクラスを登録
		Class.forName(DRIVER);
		
		// DBに接続
		Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        
		// 接続情報を返す
		return conn;
	}
	
	/**
	 *  ORACLEのDBを切断。
	 *  @param connection 接続情報
	 */
	public static void close(Connection connection) {
		// 接続情報が入っていたら、切断処理を実行
		if(connection != null) {
			try {
				connection.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 *  ORACLEのDBを切断。
	 *  @param preparedStatement ステートメント
	 */
	public static void close(PreparedStatement preparedStatement) {
		if(preparedStatement != null) {
			try {
				preparedStatement.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 *  ORACLEのDBを切断。
	 *  @param resultSet リザルトセット
	 */
	public static void close(ResultSet resultSet) {
		if(resultSet != null) {
			try {
				resultSet.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}