package com.entermoor.ddnsimulator.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;

// import sun.net.www.protocol.https.HttpsURLConnectionImpl;

/** @author fxzjshm */
public class DDnsSimulator {

	/**
	 * @param args
	 *            [0] is the objectId.
	 * @throws IOException
	 * @throws APIException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws IOException,
			InterruptedException {
		String webIP = getMyIP();
		System.out.println("Public IP : " + webIP);
		if (args.length == 0 || args[0] == null || args[0].equals("")) {
			System.out.println("ObjectId can't be null.");
			System.exit(1);
		}

                HttpURLConnection httpCon = null;
                OutputStreamWriter out = null;
                BufferedReader in = null;
                String data, result, temp;
                StringBuilder sb;
                try {
		        boolean isCreate = args[0].equalsIgnoreCase("create");
		        httpCon = getHttpConnection("https://leancloud.cn:443/1.1/classes/IP_User_Obj"
				        		+ (isCreate ? "" : "/" + args[0]), isCreate ? "POST"
			                        	: "PUT");
        		httpCon.setRequestProperty("User-Agent", "curl/7.38.0");
	        	httpCon.setRequestProperty("X-AVOSCloud-Application-Id",
		        		"CaiXYnmy3BPpcpVE9eRaKQkV-gzGzoHsz");
        		httpCon.setRequestProperty("X-AVOSCloud-Application-Key",
	        			"7tryYPnSzMGwpivDanO2yfG0");
		        httpCon.setRequestProperty("Content-Type", "application/json");
		        out = new OutputStreamWriter(httpCon.getOutputStream());
		        data = "{\"IP\":\"" + webIP + "\", \"HostName\":\""
                                + InetAddress.getLocalHost().getHostName() + "\"}";
        		System.out.println(data);
	        	out.write(data);
		        // out.close();
		        httpCon.connect();

		        in = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
        		result = null;
		        temp = null;
	        	sb = new StringBuilder();
        		while ((temp = in.readLine()) != null) {
			        sb.append(temp).append(" ");
		        }
		        result = sb.toString();
		        // in.close();
		        System.out.println(result);
                } catch (Exception e){
                        e.printStackTrace();
                        System.err.println("There's something wrong with the connection.");
                        System.err.println("Please check your object id, or report a bug at https://github.com/fxzjshm/DDNS-Simulator/issues/");
                        System.exit(2);
                } finally {
                        if (null != out)
                                out.close();
                        if (null != in)
                                in.close();
                }

		boolean doesCheck = false;
		for (String arg : args) {
			if (args[0].equalsIgnoreCase("create"))
				break;
			if (arg.equalsIgnoreCase("-c") || arg.equalsIgnoreCase("--check")) {
				doesCheck = true;
				break;
			}
		}

		if (doesCheck) {
			httpCon = getHttpConnection("https://leancloud.cn:443/1.1/classes/IP_User_Obj/"
							+ args[0], "GET");
			httpCon.setRequestProperty("X-AVOSCloud-Application-Id",
					"CaiXYnmy3BPpcpVE9eRaKQkV-gzGzoHsz");
			httpCon.setRequestProperty("X-AVOSCloud-Application-Key",
					"7tryYPnSzMGwpivDanO2yfG0");
			httpCon.connect();
			in = new BufferedReader(new InputStreamReader(
					httpCon.getInputStream()));
			result = null;
			temp = null;
			sb = new StringBuilder();
			while ((temp = in.readLine()) != null) {
				sb.append(temp).append(" ");
			}
			result = sb.toString();
			in.close();
			System.out.println(result);
			String ipInServer = result.split("\"")[3];
			if (ipInServer.equalsIgnoreCase(webIP)) {
				System.out.println("Success.");
				System.exit(0);
			}
			System.err.println("Something went wrong.");
			System.exit(1);
		}
	}

	/** From http://blog.csdn.net/gaojinshan/article/details/24725293 */
	private static String getMyIP() throws IOException {
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

	/**
	 * From
	 * http://stackoverflow.com/questions/1051004/how-to-send-put-delete-http
	 * -request-in-httpurlconnection
	 * 
	 * @author Benjamin Twilight
	 */
	private static HttpURLConnection getHttpConnection(String url, String type) {
		URL uri = null;
		HttpURLConnection con = null;
		try {
			uri = new URL(url);
			con = (HttpURLConnection) uri.openConnection();
			con.setRequestMethod(type); // type: POST, PUT, DELETE, GET
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setConnectTimeout(60000); // 60 secs
			con.setReadTimeout(60000); // 60 secs
			// con.setRequestProperty("Accept-Encoding", "UTF-8");
			// con.setRequestProperty("Content-Type", "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}
}
