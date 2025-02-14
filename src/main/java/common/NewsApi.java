package common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import bean.NewsApiBean;

/**
 *  ニュースAPIのクラスです。
 *  @version 1.0
 *  @since 2024/04/03
 *  @author k.yamada
 */
public class NewsApi {
	
	/**
	 * ニュースAPIからニュース情報の取得。
	 * @return ニュースAPI情報
	 */
	public List<NewsApiBean> getNewsApi() {
		
		// ニュースAPIBeanリスト
		List<NewsApiBean> list = new ArrayList<NewsApiBean>();
		
		try {
            // APIエンドポイントのURLを設定
            URL url = URI.create("https://newsapi.org/v2/top-headlines?"
            		+ "country="
            		+ PropertyUtil.getProperty("newsApi.country")
            		+ "&from="
            		+ Common.getStrAddDate("yyyy-MM-dd", 2)
            		+ "&to="
            		+ Common.getStrNowDate("yyyy-MM-dd")
            		+ "&pageSize="
            		+ PropertyUtil.getProperty("newsApi.page")
            		+ "&apiKey="
            		+ PropertyUtil.getProperty("newsApi.apiKey")).toURL();
            // HTTPコネクションを開く
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // メソッドタイプをGETに設定
            conn.setRequestMethod("GET");
            // レスポンスコードを取得
            int responseCode = conn.getResponseCode();

            if (responseCode == 200) {
                // レスポンスの読み取り
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                
                // JSON形式の分解
                JSONObject json_obj = new JSONObject(response.toString());
                // articles(配列)
                JSONArray articlesList = json_obj.getJSONArray("articles");
                
                // articles(配列)のループ
                for(int i = 0; i < articlesList.length(); i++) {
            	  // ニュースAPIBean
    			  NewsApiBean newsApiBean = new NewsApiBean();
        			
            	  JSONObject articles = articlesList.getJSONObject(i);

            	  // タイトル
            	  if(!articles.isNull("title")) {
                	  newsApiBean.setTitle(articles.getString("title"));
            	  } else {
                	  newsApiBean.setTitle("");
            	  }
            	  
            	  // 内容
            	  if(!articles.isNull("description")) {
            		  newsApiBean.setContent(articles.getString("description"));
            	  } else {
            		  newsApiBean.setContent("");
            	  }
            	  
            	  // URL
            	  if(!articles.isNull("url")) {
            		  newsApiBean.setUrl(articles.getString("url"));
            	  } else {
            		  newsApiBean.setUrl("");
            	  }
            	  
            	  // URL画像
            	  if(!articles.isNull("urlToImage")) {
                	  newsApiBean.setUrlToImage(articles.getString("urlToImage"));
            	  } else {
            		// URL画像
                	  newsApiBean.setUrlToImage("");
            	  }
            	  
            	  // 公開日時
            	  if(!articles.isNull("publishedAt")) {
            		  
            		  Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX").parse(articles.getString("publishedAt"));
            		  
            		  newsApiBean.setPublishedAt(Common.changeDateStr(date, "yyyy-MM-dd HH:mm:ss"));
            	  } else {
            		  newsApiBean.setPublishedAt("");
            	  }
            	  
            	  list.add(newsApiBean);
            	}

            } else {
                System.out.println("エラー発生: " + responseCode);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		return list;
	}
}
