/*
 * Created on 2005/5/27
 *
 */
package guri.net;

import java.io.File;
import java.net.URL;

/**
 * @author luzi82
 *  
 */
public class ForceDownload implements Runnable {

    private final URL _url;

    private File _file;

    private Throwable _err = null;

    private int _responseCode = -1;

    public ForceDownload(URL url, File file) {
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
            while (true) {
                int __response = Download.download(_url, _file);
                if (__response == -1)
                    throw new Throwable("error detected");
                if (__response >= 200 && __response < 300){
                    _responseCode=__response;
                    break;
                }
            }
        } catch (Throwable _t) {
            _err = _t;
        }
    }
    
    public Throwable getThrowable(){
        return _err;
    }
    
    public int getResponseCode(){
        return _responseCode;
    }
    
    static public int download(URL url, File file) {
        ForceDownload fd=new ForceDownload(url,file);
        fd.run();
        Throwable t=fd.getThrowable();
        if(t!=null)t.printStackTrace();
        return fd.getResponseCode();
    }

}
