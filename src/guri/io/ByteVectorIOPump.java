/*
 * Created on 2005/5/15
 *
 */
package guri.io;

import guri.util.Syn;

/**
 * @author luzi82
 *
 */
public class ByteVectorIOPump {
    
    ByteVectorIO[] bvioV=new ByteVectorIO[2];
    
    public ByteVectorIOPump(ByteVectorIO bvio1,ByteVectorIO bvio2){
        bvioV[0]=bvio1;
        bvioV[1]=bvio2;
    }
    
    public void start(){
        for(int i=0;i<2;i++){
            Pump p=new Pump(i);
            p.start();
        }
    }
    
    public void close(){
        bvioV[0].close();
        bvioV[1].close();
        joinDead=true;
        joinLock.u();
    }
    
    Syn joinLock = new Syn();
    boolean joinDead = false;
    
    public void join(){
        while(!joinDead){
            joinLock.w();
        }
    }
    
    class Pump extends Thread{
        
        int index;
        
        public Pump(int index){
            this.index=index;
        }

        /* (non-Javadoc)
         * @see java.lang.Runnable#run()
         */
        public void run() {
            try{
                while(true){
                    bvioV[index].send(bvioV[1-index].get());
                }
            }catch(Throwable t){
            }
            close();
        }
        
    }
}
