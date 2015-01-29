package cn.fh.dictionary.connection;

import static java.lang.System.out;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.fh.dictionary.source.Source;
import cn.fh.dictionary.word.Explaination;


public class YoudaoConnector implements Connector {
	private Source source;
	private String html;
	
	private final int BUF_SIZE = 1024 * 1024 * 500; // 500KB
	private final int TIMEOUT = 3000; // 3s
	
	public YoudaoConnector() {
		
	}
	
	public YoudaoConnector(Source s) {
		this.source = s;
	}

	@Override
	public List<Explaination> fetchResult() {
		parse();
		return null;
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
		/*String ip = fetchIP(source.getUrl().getHost());
		if (null == ip) {
			System.out.println("DNS lookup timeout");
			System.exit(0);
		}*/
		
		//String ipURL = "http://" + ip + "/search" + source.getQueryString();
		//out.println(ipURL);
		out.println(source.getUrl().toString() + source.getQueryString());
		URLConnection conn = doConnect(source.getUrl().toString() + source.getQueryString());
		this.html = fetchHtml(conn);
	}
	
	/**
	 * 解析HTML文档
	 * @return
	 */
	private List<Explaination> parse() {
		List<Explaination> expList = new ArrayList<>();
		
		Document doc = Jsoup.parse(this.html);
		Elements elems = doc.select("#collinsResult .ol li");
		for (Element tag : elems) {
			Elements meanningResult = tag.select(".collinsMajorTrans p");
			Element meanningTag = meanningResult.first();
			if (null != meanningTag) {
				out.println(meanningTag.text());
			}
		}
		
		return expList;
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
				out.print("timeout");
				System.exit(0);
			}

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
