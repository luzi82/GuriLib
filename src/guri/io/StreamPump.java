/*
 * Created on May 21, 2005
 *
 */
package guri.io;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Instance of this class will transfer all byte received from a InputStream to
 * an Output Stream.
 * 
 * @author luzi82
 * 
 */
public class StreamPump implements Runnable {

	private static final int BUFFER_SIZE = 1024;

	private final InputStream _is;

	private final OutputStream _os;

	private boolean _closeOutputStreamAfterEnd = false;

	private boolean _started = false;

	private boolean _stop = false;

	private boolean _end = false;

	public StreamPump(InputStream is, OutputStream os) {
		this._is = is;
		this._os = os;
	}

	public void setCloseOutputStreamAfterEnd(boolean value) {
		_closeOutputStreamAfterEnd = value;
	}

	public boolean getCloseOutputStreamAfterEnd() {
		return _closeOutputStreamAfterEnd;
	}

	public void stop() {
		_stop = true;
	}

	public boolean getEnd() {
		return _end;
	}

	/**
	 * When run, the pumping will start.
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		try {
			if (_started)
				return;
			_started = true;
			byte[] buffer = new byte[BUFFER_SIZE];
			while (!_stop) {
				int len = _is.read(buffer);
				if (len == -1)
					break;
				_os.write(buffer, 0, len);
			}
			if (_closeOutputStreamAfterEnd) {
				_os.close();
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
		_end = true;
	}

	static public void pump(InputStream is, OutputStream os) {
		StreamPump __sp = new StreamPump(is, os);
		__sp.run();
	}

}
