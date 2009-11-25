/*
 * Created on May 13, 2005
 *
 */
package guri.util.test;

import guri.util.ByteVector;
import guri.util.Compare;

import java.util.Random;

/**
 * @author cs_ltkaa
 *
 */
public class ByteVectorTest {
	
	public static void main(String[] args) {
		try{
		for(int i=0;i<10000;i++){
			byte[][] stage0=ranByteVV();
			byte[] stage1=ByteVector.toByteV(stage0);
			byte[][] stage2=ByteVector.toByteVV(stage1);
			if(Compare.compare(stage0,stage2)!=0)
				throw new Error("byteVV");
			System.out.println("byteVV "+i);
		}
		System.out.println("byteVV ok");
		for(int i=0;i<10000;i++){
			int stage0=ran.nextInt();
			byte[] stage1=ByteVector.toByteV(stage0);
			int stage2=ByteVector.toInt(stage1);
			if(stage0!=stage2)
				throw new Error("int");
			System.out.println("int "+i);
		}
		System.out.println("int ok");
		}catch(Throwable t){
			t.printStackTrace();
			System.exit(-1);
		}
	}

	public static Random ran=new Random();
	
	public static byte[][] ranByteVV(){
		int len=ran.nextInt(11)-1;
		if(len==-1)return null;
		byte[][] out=new byte[len][];
		for(int i=0;i<len;i++){
			out[i]=ranByteV();
		}
		return out;
	}
	
	public static byte[] ranByteV(){
		int len=ran.nextInt(11)-1;
		if(len==-1)return null;
		byte[] out=new byte[len];
		ran.nextBytes(out);
		return out;
	}

}
