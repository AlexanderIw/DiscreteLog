package discreteLog;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;

public class IndexCalculus {
    /* the model --> b= g^x mod p <-- the model */
	static final BigInteger negOne = new BigInteger("-1");
    static final BigInteger ZERO = new BigInteger("0");
    static final BigInteger ONE = new BigInteger("1");
    static final BigInteger TWO = new BigInteger("2"); 
    static BigInteger matrix[][];											//static so only can be one copy. this going to take up lot memory. 

    public static BigInteger indexCalculusSolv(BigInteger g, BigInteger p,BigInteger b, int bs){
    	System.out.println("stage 1: Finding logarithms of the factor base");
    	int expArray[], fb[], factorsArray[],factors=0;										// local variables
        BigInteger randS, x=ZERO, l, logarithm[], pminone=p.subtract(ONE);
   
        SecureRandom randomGenerator = new SecureRandom();

        //stage 1: part A: create the factor base B
        fb=smartFactorBase(bs, p);
        System.out.print("B=");
        //Matrix.printArray(fb);
             
        //stage 1: part B: compute the relations
        logarithm=computeRelation(fb, g, p); 
        //Matrix.printBoard(matrix);
          
        System.out.println("Stage 2: Solving for the logarithms of the factor base");
        //stage 2 part B: solve linear system with prime modulus(s) p-1
        
        System.out.println("Stage 2a: find all factors of p-1");
        //logarithm=SolveLinearSystemMod(p, logarithm);
        
       // System.out.println("hello->");
        
        //System.exit(0);
        logarithm = Matrix.GaussianEliminationModP(matrix, logarithm, p.subtract(ONE).divide(TWO));
       
        System.out.println("stage 3: Compute Log_g b=x");
        /*stage 3 part A: choose a random s , 1<= s<=p-2 
         and compute l=b*g^s*/
        do{
        	randS = new BigInteger(p.bitLength()-2,randomGenerator);
            //andS = BigInteger.valueOf(Math.abs(randomGenerator.nextLong()+101));
            l=  b.multiply( (g.modPow(randS, p)) ).mod(p);
            expArray=BigIntegerMath.factorWRTFB(fb, l);
        }while(expArray[0]<0);
        
        System.out.println("Random s= "+ randS+" and b*g^s mod p= "+ l);
        Matrix.printArray(expArray);
        
        /*Final Stage: stage 3 part B: 
        then if log_g b=c_1*log_gp_1+ c_2*log_g*p_2+....+ c_m*log_g*p_m-randS*/
        for(int i =0; i<expArray.length;i++)
        	x=x.add(logarithm[i].multiply(BigInteger.valueOf(expArray[i])));
            
        x=(x.subtract(randS)).mod( (p.subtract(ONE)) );
        
        return x;
    }
    
    public static int[] smartFactorBase(int bs, BigInteger p){
    	int ix=0;
    	int fb[]= new int[bs];
    	BigInteger nextPrime=ONE;
    	
        while(ix<bs){
            nextPrime = nextPrime.nextProbablePrime();
           // if(BigIntegerMath.eulerCriterion(nextPrime, p)){
	            fb[ix]=	nextPrime.intValue();
	            ix++;
            }
       // }   
        return fb;
    }
    
    public static void createFactorBase(){
    	
    	
    }


    public static BigInteger[] computeRelation(int fb[], BigInteger g,BigInteger p) {                         
    	int sizeFB= fb.length, relation =0,exp[], count=0 ;
        BigInteger dr, randX;

        BigInteger[] logs= new BigInteger[sizeFB];
        matrix=new BigInteger[sizeFB][sizeFB];
       
        SecureRandom randomGenerator = new SecureRandom();
  
        while (relation<sizeFB){
        	//randX = BigInteger.valueOf(Math.abs(randomGenerator.nextInt(26541)+87));
        	randX = BigInteger.valueOf(Math.abs(randomGenerator.nextLong()+101));
        	//System.out.println("RandX= "+ randX);
        	
            dr = g.modPow(randX, p); 						// g^x(random)mod p
          
            //if(count%2==0)
            exp = BigIntegerMath.factorWRTFB(fb, dr);	//factor with respect v factor base
            //else
            //exp = BigIntegerMath.factorWRTFBGTOL(fb, dr);	//factor with respect v factor base
            // System.out.println("not relation number:"+relation+": found smoothness "+g+"^"+randX+" mod "+p+"= "+ dr);
            /*
            if(count==10000){
            	System.out.println("Smoothness not found after 10000");
            	count=0;
            }
            */
            //if bad relation start over
            count++;
            if(exp[0]==-1)continue;

            System.out.println("relation number:"+relation+": found smoothness "+g+"^"+randX+" mod "+p+"= "+ dr);
        	//set b
        	logs[relation]=randX;
        	//set up A with respect 
            for(int col=0;col<exp.length;col++)                   
                matrix[relation][col]=BigInteger.valueOf(exp[col]);        
        	//if successful another relation is found add 1
            relation++;
        }// end of main-while-loop
        
        return logs;
    }//end of compute Relation
    
    /*
    public static BigInteger[] SolveLinearSystemMod(BigInteger p, BigInteger[] logarithm){
    	int ix=0;
    	BigInteger pMinusOne=p.subtract(ONE);
    	BigInteger pMinusOneFac[]={pMinusOne, ZERO}, chineseRemanderResult[]={ZERO};
    	Vector<BigInteger[]> chineseRemanderV = new Vector<BigInteger[]>();
    	 
    	
    	 do{
         	if(pMinusOneFac[ix].isProbablePrime(1)){
         		System.out.println("uahdfhoiasdjfiopaj="+pMinusOneFac[ix]);
         		chineseRemanderV.add(Matrix.GaussianEliminationModP(matrix,logarithm, pMinusOneFac[ix]));
         		
         		ix++;
         	}else pMinusOneFac=BigIntegerMath.PollardRhoFactor(pMinusOne);
         }while(ix<pMinusOneFac.length);
    	 ix=0;
    	 while(ix<chineseRemanderV.size()-1)
    		 chineseRemanderResult= BigIntegerMath.solveCRT(chineseRemanderV.elementAt(0), chineseRemanderV.elementAt(1), pMinusOneFac);
    	 
    	 return chineseRemanderResult;
    }
    
    */
  //  public static int[] factors(){
    //	int valFach=10;
    	
    	//return valFach;
   // }
    
}// end of class