

package guri.io;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/*
 * Created on 2005/5/13
 *
 */

/**
 * @author luzi82
 * 
 */
public class StreamByteVectorIO implements ByteVectorIO {

    DataInputStream dis;

    DataOutputStream dos;

    public StreamByteVectorIO(InputStream is, OutputStream os) {
        dis = new DataInputStream(is);
        dos = new DataOutputStream(os);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ByteVectorIO#input(byte[])
     */
    public void send(byte[] b) throws IOException {
        dos.writeInt(b.length);
        dos.write(b);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ByteVectorIO#output()
     */
    public byte[] get() throws IOException {
        int len = dis.readInt();
        byte[] out = new byte[len];
        dis.readFully(out);
        return out;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ByteVectorIO#close()
     */
    public void close() {
        try {
            dis.close();
        } catch (Throwable t) {
        }
        try {
            dos.close();
        } catch (Throwable t) {
        }
    }

}
