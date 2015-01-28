package cn.fh.dictionary.test;


import java.net.URL;
import java.net.URLConnection;

import org.junit.Test;

import cn.fh.dictionary.connection.YoudaoConnector;

public class YoudaoConnectorTest extends YoudaoConnector {
	@Test
	public void testConnect() throws Exception {
		/*setSource(new YoudaoSource());
		
		String html = fetchHtml();
		System.out.println(html);
		Assert.assertNotNull(html);
		Assert.assertFalse(html.isEmpty());*/
		
		
		URL u = new URL("http://www.baidu.com");
		URLConnection conn = u.openConnection();
		conn.setReadTimeout(3000);
		conn.setConnectTimeout(1000);
		conn.connect();
		
		/*InetSocketAddress addr = new InetSocketAddress("www.baidu.com", 80);
		System.out.println(addr);*/
		
	}
}