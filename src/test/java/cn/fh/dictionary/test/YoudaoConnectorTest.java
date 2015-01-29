package cn.fh.dictionary.test;



import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cn.fh.dictionary.connection.YoudaoConnector;
import cn.fh.dictionary.source.Source;
import cn.fh.dictionary.source.YoudaoSource;

public class YoudaoConnectorTest extends YoudaoConnector {
	@Before
	public void init() {
	}

	@Test
	public void testConnect() throws Exception {
/*		setSource(new YoudaoSource());
		Assert.assertNotNull(html);
		Assert.assertFalse(html.isEmpty());*/
		
		/*InetAddress addr = InetAddress.getByName("www.baidu.com");
		System.out.println(addr);*/
		
		
		/*URL u = new URL("http://www.baidu.com");
		URLConnection conn = u.openConnection();
		conn.setReadTimeout(3000);
		conn.setConnectTimeout(1000);
		conn.connect();*/
		
		/*InetSocketAddress addr = new InetSocketAddress("www.baidu.com", 80);
		System.out.println(addr);*/
		
	}
	
	/**
	 * 测试连接是否能正常发起
	 * @throws IOException
	 */
	@Test
	public void testConnection() throws IOException {
		Source s = new YoudaoSource();
		setSource(s);
		setWord("apple");
		
		connect();
	}
}