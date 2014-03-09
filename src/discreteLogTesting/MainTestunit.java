package discreteLogTesting;
import java.math.BigInteger;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import discreteLog.BigIntegerMath;
import discreteLog.IndexCalculus;
import discreteLog.Matrix;

public class MainTestunit {
	 //Description:Testing function
	public static void main (String [] args){
    	//Factor base 1<FactorBase<p-1
    	int factorBaseSize=0, range=1000000000;
    	long maxmax=1000000000000000000L;
    	int fb[];
    	String test="SieveOfEratosthenes";
    	Writer writer = null;
    	
    	try {
    	    writer = new BufferedWriter(new OutputStreamWriter(
    	          new FileOutputStream(test+".txt"), "utf-8"));
    	    
        	//B= g^x mod p
            BigInteger p = new BigInteger("14087");
            
            writer.write("N  , T(ms)\n");
            for(factorBaseSize=10; factorBaseSize<=range;factorBaseSize=factorBaseSize*10){
            	long start = System.currentTimeMillis(); 
	            //------------What need to be time goes here-------------//

            	BigIntegerMath.SieveOfEratosthenes(factorBaseSize);
	
	            //------------Timing stop here-------------------------//
        	
	        	long end = System.currentTimeMillis();
	        	double timeToProcess = end - start;
	        	
	        	//writer.write("last element factorbase= "+ fb[factorBaseSize-1]+"\n");
	        	
	    		writer.write(factorBaseSize+", "+timeToProcess+"\n"); 
            }//end of for loop
        	
        	//fb=IndexCalculus.smartFactorBase(168, p);
        	//Matrix.printArray(fb);
    	    
    	} catch (IOException ex) {} 
    	finally {
    	   try {writer.close();} catch (Exception ex) {}
    	}//End of finally

	}//End of main
}
