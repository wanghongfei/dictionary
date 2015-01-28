package cn.fh.dictionary.connection;

import static java.lang.System.out;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import cn.fh.dictionary.source.Source;
import cn.fh.dictionary.word.Explaination;

public class YoudaoConnector implements Connector {
	private Source source;
	
	private final int BUF_SIZE = 1024 * 1024 * 500; // 500KB
	
	public YoudaoConnector() {
		
	}
	
	public YoudaoConnector(Source s) {
		this.source = s;
	}

	@Override
	public List<Explaination> fetchResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSource(Source source) {
		this.source = source;
	}
	
	public void connect() throws IOException {
		String ip = fetchIP(source.getUrl().getHost());
		if (null == ip) {
			System.out.println("DNS lookup timeout");
			System.exit(0);
		}
		
		String ipURL = "http://" + ip;
		URLConnection conn = doConnect(ipURL);
		String html = fetchHtml(conn);
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
	
	private String fetchIP(String host) {
		DNSLookupThread th = new DNSLookupThread(host);
		String ip = null;
		th.start();
		try {
			th.join(2000);
			ip = th.getIP();
		} catch (InterruptedException e) {
		}
		
		return ip;
	}

	private URLConnection doConnect(String ip) {
		URLConnection conn = null;
		try {

			//URL url = new URL(source.getUrl());
			URL url = new URL(ip);
			conn = url.openConnection();
			conn.setConnectTimeout(3000);
			conn.connect();

		} catch (MalformedURLException e) {
			out.println("invalid url:" + source.getUrl());
			System.exit(0);
		} catch (SocketTimeoutException ex) {
			out.println("connection timeout");
			System.exit(0);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(0);
		}
		
		return conn;
	}
	
	
	
	
	/**
	 * For test purpose only!
	 * @param html
	 * @return
	 */
	protected String fetchHtml() throws IOException {
		String ip = fetchIP(source.getUrl().getHost());
		if (null == ip) {
			System.out.println("DNS lookup timeout");
			System.exit(0);
		}
		
		String ipURL = "http://" + ip;
		URLConnection conn = doConnect(ipURL);
		String html = fetchHtml(conn);
		
		return html;
	}
}
