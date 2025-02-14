package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.MstGroupBean;
import common.Common;
import common.DBManager;

/**
 *  分類マスタのDBクラスです。
 *  @version 1.0
 *  @since 2024/03/04
 *  @author k.yamada
 */
public class MstGroupDb {
	
	/**
	 * コンボボックスの作成。
	 * @param groupId 分類ID
	 * @param emptyFlag 空白フラグ(true:空白あり, false:空白なし)
	 * @throws Exception 
	 */
	public List<MstGroupBean> createComb(String groupId, boolean emptyFlag) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		List<MstGroupBean> detailList = new ArrayList<MstGroupBean>();
		
		try {
    		// ORACLEのDBを接続
			connection = DBManager.getConnection();
			
			// SQLを定義
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("    GROUP_ID ");
			sql.append("  , KBN ");
			sql.append("  , KBN_NAME ");
			sql.append("  , DEL_FLG ");
			sql.append("FROM ");
			sql.append("    MST_GROUP ");
			sql.append("WHERE ");
			sql.append("    1 = 1 ");
			// 分類ID
			sql.append("AND GROUP_ID = \'"+ groupId +"\'");

            // ステートメントを作成
			preparedStatement = connection.prepareStatement(sql.toString());
			
			// SQLを実行し、実行結果を受け取る
			resultSet = preparedStatement.executeQuery();
			
			int count = 0;
			
			while (resultSet.next()) {
				
				// 分類マスタBean
				MstGroupBean mstGroupBean = new MstGroupBean();
				
				// 1行目が空白ありの場合
				if(count == 0 && emptyFlag) {
					mstGroupBean = new MstGroupBean();
					// 分類ID
					mstGroupBean.setGroupId("");
					// 区分
					mstGroupBean.setKbn("");
					// 区分名
					mstGroupBean.setKbnName("");
					// 削除フラグ
					mstGroupBean.setDelFlag("");
					// リストに追加
					detailList.add(mstGroupBean);
					mstGroupBean = new MstGroupBean();
				}
				
				// 分類ID
				mstGroupBean.setGroupId(Common.nullToEmpty(resultSet.getString("GROUP_ID")));
				// 区分
				mstGroupBean.setKbn(Common.nullToEmpty(resultSet.getString("KBN")));
				// 分類ID
				mstGroupBean.setKbnName(Common.nullToEmpty(resultSet.getString("KBN_NAME")));
				// 削除フラグ
				mstGroupBean.setDelFlag(Common.nullToEmpty(resultSet.getString("DEL_FLG")));
				
				// リストに追加
				detailList.add(mstGroupBean);
				
				count++;
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
	 * 分類マスタの1データ取得
	 * @param groupId 分類ID
	 * @param kbn 分類ID
	 * @param emptyFlag 空白フラグ(true:空白あり, false:空白なし)
	 * @throws Exception 
	 */
	public MstGroupBean getMstGroupData(String groupId, String kbn, boolean emptyFlag) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		MstGroupBean mstGroupBean = new MstGroupBean();
		
		try {
    		// ORACLEのDBを接続
			connection = DBManager.getConnection();
			
			// SQLを定義
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("    GROUP_ID ");
			sql.append("  , KBN ");
			sql.append("  , KBN_NAME ");
			sql.append("  , DEL_FLG ");
			sql.append("FROM ");
			sql.append("    MST_GROUP ");
			sql.append("WHERE ");
			sql.append("    1 = 1 ");
			// 分類ID
			sql.append("AND GROUP_ID = \'"+ groupId +"\'");
			// 区分
			sql.append("AND KBN = \'"+ kbn +"\'");
			sql.append("ORDER BY ");
			sql.append("    GROUP_ID ");
			sql.append("  , KBN ");

            // ステートメントを作成
			preparedStatement = connection.prepareStatement(sql.toString());
			
			// SQLを実行し、実行結果を受け取る
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				// 分類ID
				mstGroupBean.setGroupId(Common.nullToEmpty(resultSet.getString("GROUP_ID")));
				// 区分
				mstGroupBean.setKbn(Common.nullToEmpty(resultSet.getString("KBN")));
				//　区分名
				mstGroupBean.setKbnName(Common.nullToEmpty(resultSet.getString("KBN_NAME")));
				// 削除フラグ
				mstGroupBean.setDelFlag(Common.nullToEmpty(resultSet.getString("DEL_FLG")));
			}
			
			return mstGroupBean;
			
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