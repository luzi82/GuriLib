/*
 * Created on 2005/5/27
 *
 */
package guri.net;

import guri.io.StreamPump;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author luzi82
 *  
 */
public class Download implements Runnable {

	private final URL _url;

	private File _file;

	private Throwable _err = null;

	private int _responseCode = -1;

	public Download(URL url, File file) {
		if (file == null)
			throw new NullPointerException("file");
		if (url == null)
			throw new NullPointerException("url");
		if (file.exists())
			throw new IllegalArgumentException("file exist");
		_url = url;
		_file = file;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		try {
			HttpURLConnection __huc = (HttpURLConnection) _url.openConnection();
			_responseCode = __huc.getResponseCode();
			if (_responseCode < 200 || _responseCode >= 300)
				return;
			InputStream __is = __huc.getInputStream();
			OutputStream __os = new FileOutputStream(_file);
			StreamPump.pump(__is, __os);
		} catch (Throwable t) {
			_err = t;
		}
	}

	public int getResponseCode() {
		return _responseCode;
	}

	public Throwable getThrowable() {
		return _err;
	}

	static int download(URL url, File file) {
		Download __dl = new Download(url, file);
		__dl.run();
		Throwable t = __dl.getThrowable();
		if (t != null)
			t.printStackTrace();
		return __dl._responseCode;
	}

}
