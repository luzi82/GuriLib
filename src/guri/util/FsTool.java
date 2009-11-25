package guri.util;

import guri.io.StreamPump;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FsTool {

	static public void rename(String aTo, String aFrom) {
		new File(aFrom).renameTo(new File(aTo));
	}

	static public void copy(String aTo, String aFrom) throws IOException {
		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(aTo));
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
				aFrom));
		StreamPump.pump(bis, bos);
		bis.close();
		bos.close();
	}

}
