package bean;

/**
 *  ニュースAPIBeanのクラスです。
 *  @version 1.0
 *  @since 2024/04/03
 *  @author k.yamada
 */
public class NewsApiBean extends MessageBean{
	
	// タイトル
	private String title;
	
	// 内容
	private String content;
	
	// URL
	private String url;
	
	// URL画像
	private String urlToImage;
	
	// 公開日時
	private String publishedAt;

	/**
	 * タイトルを取得します。
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * タイトルを設定します。
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 内容を取得します。
	 * @return content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 内容を設定します。
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * URLを取得します。
	 * @return url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * URLを設定します。
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * URL画像を取得します。
	 * @return urlToImage
	 */
	public String getUrlToImage() {
		return urlToImage;
	}

	/**
	 * URL画像を設定します。
	 * @param urlToImage
	 */
	public void setUrlToImage(String urlToImage) {
		this.urlToImage = urlToImage;
	}

	/**
	 * 公開日時を取得します。
	 * @return publishedAt
	 */
	public String getPublishedAt() {
		return publishedAt;
	}

	/**
	 * 公開日時を設定します。
	 * @param publishedAt
	 */
	public void setPublishedAt(String publishedAt) {
		this.publishedAt = publishedAt;
	}
}

