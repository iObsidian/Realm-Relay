package realmrelay;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;
import java.util.Vector;
import realmrelay.net.ListenSocket;
import realmrelay.packets.Packet;


public final class ROTMGRelay {
	
	public static final ROTMGRelay instance = new ROTMGRelay();
	
	// #settings
	public String listenHost = "localhost";
	public int listenPort = 2050;
	
	public boolean bUseProxy = false;
	public String proxyHost = "socks4or5.someproxy.net";
	public int proxyPort = 1080;
	
	public String remoteHost = "54.226.214.216";
	public int remotePort = 2050;
	
	public String key0 = "311f80691451c71d09a13a2a6e";
	public String key1 = "72c5583cafb6818995cdd74b80";

	// #settings end
	
	private final ListenSocket listenSocket;
	private final List<User> users = new ArrayList<User>();
	private final List<User> newUsers = new Vector<User>();
	private final Map<Integer, InetSocketAddress> gameIdSocketAddressMap = new Hashtable<Integer, InetSocketAddress>();
	private final Map<String, Object> globalVarMap = new Hashtable<String, Object>();
	
	private ROTMGRelay() {
		Properties p = new Properties();
		p.setProperty("listenHost", this.listenHost);
		p.setProperty("listenPort", String.valueOf(this.listenPort));
		p.setProperty("bUseProxy", String.valueOf(this.bUseProxy));
		p.setProperty("proxyHost", this.proxyHost);
		p.setProperty("proxyPort", String.valueOf(this.proxyPort));
		p.setProperty("remoteHost", this.remoteHost);
		p.setProperty("remotePort", String.valueOf(this.remotePort));
		p.setProperty("key0", this.key0);
		p.setProperty("key1", this.key1);
		File file = new File("settings.properties");
		if (!file.isFile()) {
			try {
				OutputStream out = new FileOutputStream(file);
				p.store(out, null);
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		p = new Properties(p);
		try {
			InputStream in = new FileInputStream(file);
			p.load(in);
			in.close();
			this.listenHost = p.getProperty("listenHost");
			this.listenPort = Integer.parseInt(p.getProperty("listenPort"));
			this.bUseProxy = Boolean.parseBoolean(p.getProperty("bUseProxy"));
			this.proxyHost = p.getProperty("proxyHost");
			this.proxyPort = Integer.parseInt(p.getProperty("proxyPort"));
			this.remoteHost = p.getProperty("remoteHost");
			this.remotePort = Integer.parseInt(p.getProperty("remotePort"));
			this.key0 = p.getProperty("key0");
			this.key1 = p.getProperty("key1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.listenSocket = new ListenSocket(this.listenHost, this.listenPort) {

			@Override
			public void socketAccepted(Socket localSocket) {
				try {
					User user = new User(localSocket);
					ROTMGRelay.instance.newUsers.add(user);
				} catch (Exception e) {
					e.printStackTrace();
					try {
						localSocket.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
			
		};
	}
	
	/**
	 * error message
	 * @param message
	 */
	public static void error(String message) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		String timestamp = sdf.format(new Date());
		String raw = timestamp + " " + message;
		System.err.println(raw);
	}
	
	/**
	 * echo message
	 * @param message
	 */
	public static void echo(String message) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		String timestamp = sdf.format(new Date());
		String raw = timestamp + " " + message;
		System.out.println(raw);
	}
	
	public Object getGlobal(String var) {
		return this.globalVarMap.get(var);
	}
	
	public InetSocketAddress getSocketAddress(int gameId) {
		InetSocketAddress socketAddress = this.gameIdSocketAddressMap.get(gameId);
		if (socketAddress == null) {
			return new InetSocketAddress(this.remoteHost, this.remotePort);
		}
		return socketAddress;
	}
	
	public void setGlobal(String var, Object value) {
		this.globalVarMap.put(var, value);
	}
	
	public void setSocketAddress(int gameId, String host, int port) {
		InetSocketAddress socketAddress = new InetSocketAddress(host, port);
		this.gameIdSocketAddressMap.put(gameId, socketAddress);
	}
	
	public static void main(String[] args) {
		try {
			GETXmlParse.parseXMLData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Packet.init();
		if (ROTMGRelay.instance.listenSocket.start()) {
			ROTMGRelay.echo("This updated version of RR is brought to you by iObsidian.");
			while (!ROTMGRelay.instance.listenSocket.isClosed()) {
				while (!ROTMGRelay.instance.newUsers.isEmpty()) {
					User user = ROTMGRelay.instance.newUsers.remove(0);
					ROTMGRelay.instance.users.add(user);
					ROTMGRelay.echo("Connected " + user.localSocket);
					user.scriptManager.trigger("onEnable");
				}
				
				int cores = Runtime.getRuntime().availableProcessors();
				Thread[] threads = new Thread[cores];
				int core = 0;
				Iterator<User> i = ROTMGRelay.instance.users.iterator();
				while (i.hasNext()) {
					final User user = i.next();
					if (user.localSocket.isClosed()) {
						i.remove();
						continue;
					}
					if (threads[core] != null) {
						try {
							threads[core].join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					(threads[core] = new Thread(new Runnable() {
						
						@Override
						public void run() {
							user.process();
						}
						
					})).start();
					core = (core + 1) % cores;
				}
				for (Thread thread: threads) {
					if (thread == null) {
						continue;
					}
					try {
						thread.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				Thread.yield();
			}
			Iterator<User> i = ROTMGRelay.instance.users.iterator();
			while (i.hasNext()) {
				User user = i.next();
				user.kick();
			}
		} else {
			ROTMGRelay.echo("Realm Relay listener problem. Make sure there are no instances of Realm Relay already running.");
		}
	}

}
