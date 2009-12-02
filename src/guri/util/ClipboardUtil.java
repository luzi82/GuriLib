package guri.util;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class ClipboardUtil {

	public static void setString(String string) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Clipboard cb = tk.getSystemClipboard();
		StringSelection ss = new StringSelection(string);
		cb.setContents(ss, ss);
	}

}
