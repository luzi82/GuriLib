package guri.util;

import guri.io.InputSource;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XML {

	static private DocumentBuilderFactory dbf;

	static private DocumentBuilder db;

	static void init() {
		try {
			if (dbf == null) {
				dbf = DocumentBuilderFactory.newInstance();
			}
			if (db == null) {
				db = dbf.newDocumentBuilder();
			}
		} catch (Exception e) {
			throw new Error(e);
		}
	}

	static public Document parse(InputStream is) throws SAXException,
			IOException {
		init();
		Document ret = db.parse(is);
		return ret;
	}

	static public Document parseHttp(String url) throws SAXException,
			IOException {
		return parse(InputSource.fromHttp(url));
	}
	
}
