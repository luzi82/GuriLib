package guri.io;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;

public class OutputTarget {

	public static BufferedWriter toFile(String filename) {
		try {
			return new BufferedWriter(new OutputStreamWriter(
					new BufferedOutputStream(new FileOutputStream(filename)),
					"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new Error(e);
		} catch (FileNotFoundException e) {
			throw new Error(e);
		}
	}

	public static void writeObject(String filename, Serializable s) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(filename)));
			oos.writeObject(s);
			oos.flush();
			oos.close();
		} catch (Exception e) {
			throw new Error(e);
		}
	}

}
