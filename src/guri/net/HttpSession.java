package guri.net;

import java.util.TreeMap;

public class HttpSession {

	public TreeMap<String, String> cookie = new TreeMap<String, String>();

	public void fireShot(HttpShot httpShot) {
		httpShot.requestCookie.putAll(cookie);
		httpShot.run();
		cookie.putAll(httpShot.resultSetCookie);
	}

}
