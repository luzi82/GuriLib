/*
 * Created on 2005/5/13
 *
 */
package guri.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author luzi82
 * 
 */
public final class ByteVector {

	static public byte[] toByteV(byte[][] bvv) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(baos);
			if (bvv == null) {
				dos.writeInt(-1);
			} else {
				dos.writeInt(bvv.length);
				for (int i = 0; i < bvv.length; i++) {
					byte[] bv = bvv[i];
					if (bv == null) {
						dos.writeInt(-1);
					} else {
						dos.writeInt(bv.length);
						dos.write(bv);
					}
				}
			}
			byte[] out = baos.toByteArray();
			dos.close();
			baos.close();
			return out;
		} catch (Throwable t) {
			t.printStackTrace();
			System.exit(-1);
			throw new Error("exit");
		}
	}

	static public byte[][] toByteVV(byte[] bv) {
		try {
			byte[][] bvv = null;
			ByteArrayInputStream bais = new ByteArrayInputStream(bv);
			DataInputStream dis = new DataInputStream(bais);
			int bvvLen = dis.readInt();
			if (bvvLen != -1) {
				bvv = new byte[bvvLen][];
				for (int i = 0; i < bvvLen; i++) {
					int bvLen = dis.readInt();
					if (bvLen == -1) {
						bvv[i] = null;
						continue;
					}
					bvv[i] = new byte[bvLen];
					dis.readFully(bvv[i]);
				}
				dis.close();
				bais.close();
			}
			if (bais.read() != -1)
				throw new Error();
			return bvv;
		} catch (Throwable t) {
			throw new AssertionError(t);
		}
	}

	static public byte[] toByteV(int iVal) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(baos);
			dos.writeInt(iVal);
			byte[] out = baos.toByteArray();
			dos.close();
			baos.close();
			return out;
		} catch (Throwable t) {
			t.printStackTrace();
			System.exit(-1);
			throw new Error("exit");
		}
	}

	static public int toInt(byte[] bv) {
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(bv);
			DataInputStream dis = new DataInputStream(bais);
			int out = dis.readInt();
			if (bais.read() != -1)
				throw new Error();
			dis.close();
			bais.close();
			return out;
		} catch (Throwable t) {
			throw new AssertionError(t);
		}
	}

	static public byte[] read(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[100000];
		while (true) {
			int len = is.read(buffer);
			if (len == -1)
				break;
			baos.write(buffer, 0, len);
		}
		buffer = baos.toByteArray();
		is.close();
		baos.close();
		return buffer;
	}

	static public byte[] read(File file) throws IOException {
		return read(new FileInputStream(file));
	}

	static public void write(File file, byte[] byteV) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(byteV);
		fos.close();
	}

	private ByteVector() {
	}

}
