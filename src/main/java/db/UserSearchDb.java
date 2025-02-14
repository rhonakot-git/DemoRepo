package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.UserSearchBean;
import bean.UserSearchDetailBean;
import common.Common;
import common.DBManager;
import common.PropertyUtil;

/**
 *  ユーザ検索のDBクラスです。
 *  @version 1.0
 *  @since 2024/02/27
 *  @author k.yamada
 */
public class UserSearchDb {
	
	/**
	 * ユーザ情報の取得。
	 * @param userSearchBean ユーザ情報
	 * @param csvFlag CSV検索フラグ(true：CSV検索, false：CSV検索以外)
	 * @return ユーザ検索明細情報
	 * @throws Exception 
	 */
	@SuppressWarnings("resource")
	public List<UserSearchDetailBean> getUserDetailInfo(UserSearchBean userSearchBean, boolean csvFlag) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int offset = Integer.parseInt(PropertyUtil.getProperty("offset"));
		
		List<UserSearchDetailBean> detailList = new ArrayList<UserSearchDetailBean>();
		
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
			sql.append("    USR.USER_ID ");
			sql.append("  , USR.USER_NAME ");
			sql.append("  , TO_CHAR(USR.BIRTH_DAY, 'YYYY/MM/DD') AS BIRTH_DAY ");
			sql.append("  , GROUP1.KBN_NAME AS SEX ");
			sql.append("  , USR.PASSWORD ");
			sql.append("  , USR.TEL ");
			sql.append("  , USR.ADDRESS1 || USR.ADDRESS2 AS ADDRESS ");
			sql.append("  , ROWNUM NUM ");
			sql.append("FROM ");
			sql.append("    MST_USER USR ");
			sql.append("LEFT JOIN ");
			sql.append("    MST_GROUP GROUP1 ");
			sql.append("ON  GROUP1.KBN = USR.SEX ");
			sql.append("AND GROUP1.GROUP_ID = 'SEX' ");
			sql.append("WHERE ");
			sql.append("    1 = 1 ");
			
			// ユーザID
			if(!"".equals(userSearchBean.getUserId())) {
				sql.append("AND USR.USER_ID LIKE \'");
				sql.append(userSearchBean.getUserId());
				sql.append("%\' ");
			}
			
			// ユーザ名
			if(!"".equals(userSearchBean.getUserName())) {
				sql.append("AND USR.USER_NAME LIKE \'");
				sql.append(userSearchBean.getUserName());
				sql.append("%\' ");
			}
			
			// 誕生日(FROM)
			if(!"".equals(userSearchBean.getBirthDayFrom())) {
				sql.append("AND USR.BIRTH_DAY >= \'");
				sql.append(userSearchBean.getBirthDayFrom());
				sql.append("\' ");
			}
			
			// 誕生日(TO)
			if(!"".equals(userSearchBean.getBirthDayTo())) {
				sql.append("AND USR.BIRTH_DAY <= \'");
				sql.append(userSearchBean.getBirthDayTo());
				sql.append("\' ");
			}
			
			// 電話番号
			if(!"".equals(userSearchBean.getTel())) {
				sql.append("AND USR.TEL LIKE \'");
				sql.append(userSearchBean.getTel());
				sql.append("%\' ");
			}
			
			// 性別
			if(!"".equals(userSearchBean.getSex())) {
				sql.append("AND USR.SEX = \'");
				sql.append(userSearchBean.getSex());
				sql.append("\' ");
			}
			
			// 住所
			if(!"".equals(userSearchBean.getAddress())) {
				sql.append("AND USR.ADDRESS1 || USR.ADDRESS2 LIKE \'%");
				sql.append(userSearchBean.getAddress());
				sql.append("%\' ");
			}
			
			sql.append("ORDER BY ");
			sql.append("    USR.USER_ID ");
			sql.append(" ) ");

            // ステートメントを作成
			preparedStatement = connection.prepareStatement(countSql.toString() + sql.toString());
			
			// SQLを実行し、実行結果を受け取る
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				// 検索数
				userSearchBean.setDataCount(resultSet.getLong("DATA_COUNT"));
			}
			
			if(!csvFlag) {
				// ページ数
				if(!"".equals(userSearchBean.getCurrentPage())) {
					sql.append("WHERE ");
					sql.append("    NUM BETWEEN ");
					sql.append((Integer.parseInt(userSearchBean.getCurrentPage()) * offset - (offset - 1)) + " AND ");
					sql.append((Integer.parseInt(userSearchBean.getCurrentPage()) * offset) + " ");
				}
			}
			
            // ステートメントを作成
			preparedStatement = connection.prepareStatement(dataSql.toString() + sql.toString());
			
			// SQLを実行し、実行結果を受け取る
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				// ユーザ検索明細Bean
				UserSearchDetailBean userSearchDetailBean = new UserSearchDetailBean();
				
				// ユーザID
				userSearchDetailBean.setUserId(Common.nullToEmpty(resultSet.getString("USER_ID")));
				// ユーザ名
				userSearchDetailBean.setUserName(Common.nullToEmpty(resultSet.getString("USER_NAME")));
				// 誕生日
				userSearchDetailBean.setBirthDay(Common.nullToEmpty(resultSet.getString("BIRTH_DAY")));
				// 電話番号
				userSearchDetailBean.setTel(Common.nullToEmpty(resultSet.getString("TEL")));
				// 性別
				userSearchDetailBean.setSex(Common.nullToEmpty(resultSet.getString("SEX")));
				// 住所
				userSearchDetailBean.setAddress(Common.nullToEmpty(resultSet.getString("ADDRESS")));
				
				// リストに追加
				detailList.add(userSearchDetailBean);
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
}