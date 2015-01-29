package cn.fh.dictionary.connection;

import static java.lang.System.out;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import cn.fh.dictionary.source.Parser;
import cn.fh.dictionary.source.Source;
import cn.fh.dictionary.word.Explaination;
import cn.fh.dictionary.word.Word;

/**
 * 向有道词典网站发起连接，并得到HTML代码
 * 
 * @author whf
 *
 */
public class YoudaoConnector implements Connector {
	private Source source;
	private String html;
	private String word;
	
	private final int BUF_SIZE = 1024 * 1024 * 500; // 500KB
	private final int TIMEOUT = 3000; // 3s
	
	/**
	 * 用于解析HTML代码的解析器
	 * HTML解析工作委托给此对象
	 */
	private Parser parser;
	
	public YoudaoConnector() {
		
	}
	
	public YoudaoConnector(Source s, Parser parser) {
		this.source = s;
		this.parser = parser;
	}
	
	@Override
	public void setWord(String word) {
		this.word = word;
	}

	@Override
	public Word fetchResult() {
		List<Explaination> expList = parser.getExplainationList();
		return new Word(this.word, expList);
	}

	@Override
	public void setParser(Parser parser) {
		this.parser = parser;
	}
	
	@Override
	public void setSource(Source source) {
		this.source = source;
	}
	
	/**
	 * 发起查询请求，并获取结果
	 * @throws IOException
	 */
	public void connect() throws IOException {
		source.setWord(this.word);

		out.println(source.getUrl());
		URLConnection conn = doConnect(source.getUrl());
		this.html = fetchHtml(conn);
	}

	private String fetchHtml(URLConnection conn) throws IOException {
		InputStream in = conn.getInputStream();
		byte[] buf = new byte[BUF_SIZE];
		int len = 0;
		
		StringBuilder sb = new StringBuilder();
		while ( (len = in.read(buf)) != -1 ) {
			sb.append(new String(buf, 0, len));
		}
		
		in.close();
		
		return sb.toString();
	}
	
	/**
	 * @deprecated
	 * 得到目标主机的IP地址
	 * @param host 主机名
	 * @return
	 */
	private String fetchIP(String host) {
		DNSLookupThread th = new DNSLookupThread(host);
		String ip = null;
		th.start();
		try {
			th.join(3000);
			ip = th.getIP();
		} catch (InterruptedException e) {
		}
		
		return ip;
	}

	private URLConnection doConnect(String urlAddr) {
		URLConnection conn = null;
		try {

			URL url = new URL(urlAddr);
			
			// 在新线程中发起连接
			ConnectorThread cTh = new ConnectorThread(url.openConnection());
			Thread th = new Thread(cTh);
			th.start();
			th.join(TIMEOUT);

			// 从线程中返回已经连接完成的对象
			conn = cTh.getConnectedObj();
			if (null == conn) {
				out.print("connection timeout");
				System.exit(0);
			}
			out.println("connection established");

		} catch (MalformedURLException e) {
			out.println("invalid url:" + source.getUrl());
			System.exit(0);
		} catch (SocketTimeoutException ex) {
			out.println("connection timeout");
			System.exit(0);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return conn;
	}



}
