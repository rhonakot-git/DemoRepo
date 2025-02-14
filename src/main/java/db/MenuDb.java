package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.MenuBean;
import bean.MenuItemBean;
import common.Common;
import common.DBManager;

/**
 *  メニューのDBクラスです。
 *  @version 1.0
 *  @since 2024/02/22
 *  @author k.yamada
 */
public class MenuDb {
	
	/**
	 *  メニュー情報の取得。
	 *  @return メニュー情報
	 */
	public MenuBean getMenuInfo() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		MenuBean menuBean = new MenuBean();
		
		try {
    		// ORACLEのDBを接続
			connection = DBManager.getConnection();
			
			// SQLを定義
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("    MENU_ID ");
			sql.append("  , MENU_NAME ");
			sql.append("  , PATH ");
			sql.append("FROM ");
			sql.append("    MST_MENU ");

            // ステートメントを作成
			preparedStatement = connection.prepareStatement(sql.toString());

			// SQLを実行し、実行結果を受け取る
			resultSet = preparedStatement.executeQuery();
			
			// メニュ項目Beanリスト
			List<MenuItemBean> menuItemBeanList = new ArrayList<MenuItemBean>();
			
			while (resultSet.next()) {
				
				// メニュー項目Bean
				MenuItemBean menuItemBean = new MenuItemBean();
				
				// メニューID
				menuItemBean.setMenuId(Common.nullToEmpty(resultSet.getString("MENU_ID")));
				// メニュー名
				menuItemBean.setMenuName(Common.nullToEmpty(resultSet.getString("MENU_NAME")));
				// パス
				menuItemBean.setPath(Common.nullToEmpty(resultSet.getString("PATH")));
				
				// メニュー項目Beanをリストに追加
				menuItemBeanList.add(menuItemBean);
			}
			
			menuBean.setMenuItemList(menuItemBeanList);
			
			return menuBean;
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			// 接続を切断
			DBManager.close(resultSet);
			DBManager.close(preparedStatement);
			DBManager.close(connection);
		}
		return menuBean;
	}
}