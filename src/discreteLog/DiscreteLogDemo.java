package discreteLog;
import java.math.BigInteger;
public class DiscreteLogDemo {
	/**********************************************************************
	 *Author: Lonique Alexander											  *
	 *Class:Cryptography CS 352		 									  *
	 *Description:solve hard discrete log technique: Index Calculus	 	  *
	 *contact:www.LoniqueAlexander.com									  *
	 *last updated: 03/01/2014											  *	
	 *Due date: 05/04/2010		 										  *								  
	 **********************************************************************/
    public static void main (String []args)
    { 
    	int factorBaseSize=5;
    	//B= g^x mod p
        BigInteger b, g, p, x;        			//local variable
        b = new BigInteger("5872");     			//B = 	g^x mod p
        g = new BigInteger("5");				//g^x 	non-primitive root mod p
        p = new BigInteger("14087"); 			//p 	prime
       
        long start = System.currentTimeMillis(); 
        x=IndexCalculus.indexCalculusSolv(g,p,b, factorBaseSize);
        long end = System.currentTimeMillis();
    	double timeToProcess = end - start;
    	
		System.out.println("Time To Solve a Discrete Log: " + timeToProcess +
				" Milliseconds= "+ (timeToProcess*0.001)+"Seconds\n");

        // verify logarithm
       	if(g.modPow(x, p).equals(b))System.out.println("Discrete Log was found ;-):\nx= "+x);
       	else System.out.println("Discrete Log failed:\nx=unknown\n try rerunning the program");
    }
}