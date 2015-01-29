package cn.fh.dictionary.connection;

import java.io.IOException;
import java.net.URLConnection;

public class ConnectorThread implements Runnable {
	
	private URLConnection conn;
	private boolean connected = false;
	
	public ConnectorThread(URLConnection conn) {
		this.conn = conn;
	}

	@Override
	public void run() {
		try {
			conn.connect();
			finish();
		} catch (IOException e) {
		}
	}
	
	public synchronized URLConnection getConnectedObj() {
		if (connected) {
			return this.conn;
		}
		
		return null;
	}
	
	private synchronized void finish() {
		this.connected = true;
	}

}
