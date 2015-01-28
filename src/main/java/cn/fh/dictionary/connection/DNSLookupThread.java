package cn.fh.dictionary.connection;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * DNS查询线程.
 * <p>之所以专门用一个线程来查询IP地址是为了给查询操作添加超时限制功能
 * 
 * @author whf
 *
 */
public class DNSLookupThread extends Thread {
	/**
	 * 查询结果，封装了目标主机的IP地址
	 */
	private InetAddress addr;
	/**
	 * 要查询的主机名
	 */
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
	
	private synchronized void set(InetAddress addr) {
		this.addr = addr;
	}
	
	/**
	 * 得到查询结果，以字符串形式返回
	 * @return
	 */
	public synchronized String getIP() {
		if (null != this.addr) {
			return addr.getHostAddress();
		}
		
		return null;
	}
}
