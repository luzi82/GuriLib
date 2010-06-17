package guri.io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

public class InputSource {

	public static BufferedInputStream fromHttp(String url) throws IOException {
		URL aurl = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) aurl.openConnection();
		InputStream is = conn.getInputStream();
		return new BufferedInputStream(is);
	}

	public static Serializable readObject(String filename) {
		try {
			ObjectInputStream ois = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream(filename)));
			Serializable ret = (Serializable) ois.readObject();
			ois.close();
			return ret;
		} catch (Exception e) {
			throw new Error(e);
		}
	}

}
