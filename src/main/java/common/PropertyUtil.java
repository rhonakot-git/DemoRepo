package common;

import java.io.IOException;
import java.util.Properties;

/**
 *  プロパティのクラスです。
 *  @version 1.0
 *  @since 2024/04/02
 *  @author k.yamada
 */
public class PropertyUtil {

	/** ドライバ */
    private static final String INIT_FILE_PATH = "/Common.properties";
    /** プロパティ */
    private static final Properties properties;

    private PropertyUtil() throws Exception {
    }

    static {
        properties = new Properties();
        try {
        	properties.load(PropertyUtil.class.getResourceAsStream(INIT_FILE_PATH));
        } catch (IOException e) {
            // ファイル読み込みに失敗
            System.out.println(Const.createMessage(Const.CONST_FILE_READ_ERROR_MESSAGE, INIT_FILE_PATH));
        }
    }

    /**
     * プロパティ値を取得する
     * @param key キー
     * @return 値
     */
    public static String getProperty(final String key) {
        return getProperty(key, "");
    }

    /**
     * プロパティ値を取得する
     * @param key キー
     * @param defaultValue デフォルト値
     * @return キーが存在しない場合、デフォルト値
     *          存在する場合、値
     */
    public static String getProperty(final String key, final String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}