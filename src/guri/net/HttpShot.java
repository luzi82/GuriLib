package guri.net;

import guri.io.StreamPump;
import guri.util.RandomString;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class HttpShot implements Runnable {

	static final public Charset UTF8 = Charset.forName("utf-8");

	public String requestUrl;
	public Method requestMethod = Method.GET;
	public ContentType requestContentType = null;
	public TreeMap<String, String> requestCookie = new TreeMap<String, String>();
	public TreeMap<String, String> requestPost = new TreeMap<String, String>();

	public int resultCode = -1;
	// public LinkedList<HeaderField> resultHeader = new
	// LinkedList<HeaderField>();
	public TreeMap<String, String> resultSetCookie = new TreeMap<String, String>();
	public byte[] resultContent;

	@Override
	public void run() {
		try {
			String cookieValue = null;
			Set<String> requestCookieKeySet = requestCookie.keySet();
			for (String key : requestCookieKeySet) {
				if (cookieValue == null) {
					cookieValue = String.format("%s=%s", key, requestCookie
							.get(key));
				} else {
					cookieValue += String.format("; %s=%s", key, requestCookie
							.get(key));
				}
			}

			String postContent = null;
			String postContentType = null;
			Set<String> postContentKeySet = requestPost.keySet();
			if (requestContentType != null) {
				switch (requestContentType) {
				case APPLICATION_XWWWFORMURLENCODED: {
					postContentType = "application/x-www-form-urlencoded";
					StringBuffer buf = null;
					for (String key : postContentKeySet) {
						if (buf == null) {
							buf = new StringBuffer(String.format("%s=%s", key,
									URLEncoder.encode(requestPost.get(key),
											"utf-8")));
						} else {
							buf.append(String.format("&%s=%s", key, URLEncoder
									.encode(requestPost.get(key), "utf-8")));
						}
					}
					postContent = buf.toString();
					break;
				}
				case MULTIPART_FORMDATA: {
					String ran = RandomString.gen(32);
					ran="--"+ran;
					postContentType = "multipart/form-data; boundary=" + ran;
					StringBuffer buf = new StringBuffer();
					buf.append("--").append(ran).append("\n");
					for (String key : postContentKeySet) {
						buf.append("Content-Disposition: form-data; name=\"")
								.append(key).append("\"\n\n");
						buf.append(requestPost.get(key)).append("\n");
						buf.append("--").append(ran).append("\n");
					}
					postContent = buf.toString();
					break;
				}
				}
			}

			URL url = new URL(requestUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(requestMethod.id);
			if (cookieValue != null)
				conn.addRequestProperty("Cookie", cookieValue);

			if ((requestMethod == Method.POST) && (postContent != null)) {
				conn.addRequestProperty("Content-Type", postContentType);
				conn.setDoOutput(true);
				ByteBuffer bb = UTF8.encode(postContent);
				int len = bb.limit();
				byte[] bbbb = bb.array();
				OutputStream os = conn.getOutputStream();
				os.write(bbbb, 0, len);
				os.flush();
			}

			System.err.println("conn.getHeaderFields()");
			System.err.println(conn.getHeaderFields());

			InputStream is = conn.getInputStream();
			resultContent = StreamPump.pumpToByteArray(is);
			is.close();

			resultCode = conn.getResponseCode();

			// int i = 0;
			// while (true) {
			// HeaderField hf = new HeaderField();
			// hf.key = conn.getHeaderFieldKey(i);
			// if (hf.key == null)
			// break;
			// hf.value = conn.getHeaderField(i);
			// resultHeader.add(hf);
			// ++i;
			// }
			//
			// for (HeaderField hf : resultHeader) {
			// if (!hf.key.equals("Set-Cookie"))
			// continue;
			// String[] div = hf.value.split(Pattern.quote(";"));
			// String main = div[0].trim();
			// String[] mainDiv = main.split(Pattern.quote("="));
			// resultSetCookie.put(mainDiv[0].trim(), mainDiv[1].trim());
			// }

			List<String> setCookieHeader = conn.getHeaderFields().get(
					"Set-Cookie");
			System.err.println("setCookieHeader");
			System.err.println(setCookieHeader);
			if (setCookieHeader != null) {
				for (String setCookieHeaderDivI : setCookieHeader) {
					String t = setCookieHeaderDivI.trim();
					if (t.length() <= 0)
						continue;
					if (!t.contains(";"))
						continue;
					String[] tt = t.split(Pattern.quote(";"));
					String tt0 = tt[0].trim();
					if (!tt0.contains("="))
						continue;
					String[] ttt = tt0.trim().split(Pattern.quote("="));
					if (ttt.length == 1)
						resultSetCookie.put(ttt[0].trim(), "");
					else
						resultSetCookie.put(ttt[0].trim(), ttt[1].trim());
				}
			}

			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static class HeaderField {
		public String key;
		public String value;
	}

	public enum Method {
		GET("GET"), POST("POST");
		public String id;

		Method(String id) {
			this.id = id;
		}
	}

	public enum ContentType {
		APPLICATION_XWWWFORMURLENCODED, MULTIPART_FORMDATA
	}

}
