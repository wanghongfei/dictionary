package cn.fh.dictionary.connection;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class DNSLookupThread extends Thread {
	private InetAddress addr;
	private String hostname;
	
	public DNSLookupThread(String hostname) {
		this.hostname = hostname;
	}
	
	public void run() {
		try {
			InetAddress add = InetAddress.getByName(hostname);
			set(add);
		} catch (UnknownHostException e) {
		}
	}
	
	private void set(InetAddress addr) {
		this.addr = addr;
	}
	
	public String getIP() {
		if (null != this.addr) {
			return addr.getHostAddress();
		}
		
		return null;
	}
}
