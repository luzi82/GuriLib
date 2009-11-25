package guri.io;
import java.io.IOException;

/*
 * Created on 2005/5/13
 *
 */

/**
 * @author luzi82
 * 
 */
public interface ByteVectorIO {
    public void send(byte[] b) throws IOException;

    public byte[] get() throws IOException;

    public void close();
}
