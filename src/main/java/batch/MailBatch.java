package batch;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *  メールバッチのクラスです。
 *  @version 1.0
 *  @since 2024/04/10
 *  @author k.yamada
 */
public class MailBatch {
	
	/**
	 *  メイン処理。
	 *  @param params パラメータ
	 */
	public static void main(String[] params) {
		try {
		    Properties property = new Properties();
		    property.put("mail.smtp.host", "smtp.gmail.com");
		    property.put("mail.smtp.auth", "true");
		    property.put("mail.smtp.starttls.enable", "true");
		    property.put("mail.smtp.host", "smtp.gmail.com");
		    property.put("mail.smtp.port", "587");
		    property.put("mail.smtp.debug", "true");
		
		    Session sessionTest = Session.getInstance(property, new javax.mail.Authenticator() {
		    	protected PasswordAuthentication getPasswordAuthentication() {
		    		return new PasswordAuthentication("mitranoz00", "hyjy xwfs zrab snxp");
		    	}
		    });
		
			MimeMessage mimeMessage = new MimeMessage(sessionTest);
			InternetAddress toAddress = new InternetAddress("mitrakot00@yahoo.co.jp", "山田くん");
			mimeMessage.setRecipient(Message.RecipientType.TO, toAddress);
			InternetAddress fromAddress = new InternetAddress("mitranoz00@gmail.com", "山田");
			mimeMessage.setFrom(fromAddress);
			mimeMessage.setSubject("title", "text/plain");
			mimeMessage.setContent(createHTML("<div class=\"content\"><p class=\"title\">◇ お知らせメール</p><ul><li>お知らせ1</li><li>"+ params[0] +"</li></ul></div>"), "text/html; charset=ISO-2022-JP");
			Transport.send(mimeMessage);
			System.out.println("メール送信が完了しました。");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        System.out.println("");
        System.out.println("java引数　→ " + params[0]);
    }
	
	/**
	 *  HTML文作成。
	 *  @param content 内容
	 *  @return HTML文
	 */
	private static String createHTML(String content) {
		StringBuilder b = new StringBuilder();
		b.append("<html><head>");
		b.append("<style type=\"text/css\">");
		b.append("div.content div { padding: 0 5px;}");
		b.append("div.content p.title { width: 100%; background-color: #83c51a; font-color: #fff; font-size: 1.2em; padding: 5px 0 5px 5px; margin: 5px 0;}");
		b.append("ul {background-color: #fff; width:100%;}");
		b.append("li {font-size: 1em; padding: 0; margin: 0; background-color: #fff;}");
		b.append("</style>");
		b.append("</head>");
		b.append("<body>");
		b.append(content);
		b.append("</body></html>");
		return b.toString();
	}
}
