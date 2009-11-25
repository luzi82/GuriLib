package guri.io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Created on 2005/5/13
 *
 */

/**
 * @author luzi82
 * 
 */
public class SocketByteVectorIO extends StreamByteVectorIO {
    
    Socket soc;

    public SocketByteVectorIO(Socket socket) throws IOException {
        super(socket.getInputStream(), socket.getOutputStream());
        this.soc=socket;
    }
    
    public void close(){
        super.close();
        try{
            soc.close();
        }catch(Throwable t){
        }
    }

    public static SocketByteVectorIO accept(int port) throws IOException {
        ServerSocket ss = new ServerSocket(port);
        return new SocketByteVectorIO(ss.accept());
    }

    public static SocketByteVectorIO open(String host, int port)
            throws IOException {
        return new SocketByteVectorIO(new Socket(host, port));
    }

}
