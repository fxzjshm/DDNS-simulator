package com.entermoor.ddnsimulator.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import cn.leancloud.api.LCClient;
import cn.leancloud.api.exception.APIException;

/** @author fxzjshm */
public class DnsSimulator {

	/**
	 * @param args [0] is the objectId.
	 * @throws IOException
	 * @throws APIException 
	 */
	public static void main(String[] args) throws IOException, APIException {
		String webIP = getMyIP();
		System.out.println("Public IP : " + webIP);
		if (args[0]==null||args[0].equals("")){
			System.out.println("ObjectId can't be null.");
			System.exit(-1);
		}
		LCClient client = new LCClient("CaiXYnmy3BPpcpVE9eRaKQkV-gzGzoHsz", "7tryYPnSzMGwpivDanO2yfG0");
		Map<String, String> data = new HashMap<String, String>();
		data.put("IP", webIP);
		data.put("HostName", InetAddress.getLocalHost().getHostName());
		client.put("classes/IP_User_Obj/"+args[0], data);
	}

	/** From http://blog.csdn.net/gaojinshan/article/details/24725293 */
	public static String getMyIP() throws IOException {
		InputStream ins = null;
		try {
			URL url = new URL("http://1212.ip138.com/ic.asp");
			URLConnection con = url.openConnection();
			ins = con.getInputStream();
			InputStreamReader isReader = new InputStreamReader(ins, "GB2312");
			BufferedReader bReader = new BufferedReader(isReader);
			StringBuffer webContent = new StringBuffer();
			String str = null;
			while ((str = bReader.readLine()) != null) {
				webContent.append(str);
			}
			int start = webContent.indexOf("[") + 1;
			int end = webContent.indexOf("]");
			return webContent.substring(start, end);
		} finally {
			if (ins != null) {
				ins.close();
			}
		}
	}
}
