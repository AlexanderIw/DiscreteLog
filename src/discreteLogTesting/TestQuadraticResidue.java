package discreteLogTesting;

import java.math.BigInteger;

import discreteLog.BigIntegerMath;
import discreteLog.IndexCalculus;

public class TestQuadraticResidue {

    public static void testinFactorBase ()
    { 
    	//Factor base 1<FactorBase<p-1
    	int factorBaseSize=10;
    	int fb[];
    	
    	//B= g^x mod p
        BigInteger b, g, p, x;        			//local variable
        b = new BigInteger("92327518017224");     		//B = 	g^x mod p
        g = new BigInteger("7");				//g^x 	non-primitive root mod p
        p = new BigInteger("247457076132467"); 			//p 	prime
        
        
        long start = System.currentTimeMillis(); 
        IndexCalculus.smartFactorBase(factorBaseSize, p);
        long end = System.currentTimeMillis();
    	double timeToProcess = end - start;
    	fb=IndexCalculus.smartFactorBase(factorBaseSize, p);
    	System.out.print("last element factorbase= "+ fb[factorBaseSize-1]+"\n");
    	
		System.out.println("Testing time it take to generate factor base of size n= "+ factorBaseSize+
				":\n"+timeToProcess + " Milliseconds");
               
    }
    
    public static void testPrimitiveRoot(){
    	BigInteger root = new BigInteger("5");
    	BigInteger p = new BigInteger("1608507319599300885545233649993174934218196787014586228337099");
    	if( BigIntegerMath.primitiveRootTest(root, p))
    		System.out.println("is primitive root");
    	else
    		System.out.println("not a primitive root");
    	
    }
}
