package discreteLog;

import java.math.BigInteger;

public class Factoring {
	
	static final BigInteger ZERO = new BigInteger("0");
	static final BigInteger ONE = new BigInteger("1");
	static final BigInteger TWO = new BigInteger("2");

	public BigInteger pollardrho(BigInteger n) {
		//coming soon
		
		return BigInteger.ONE;
	}

	/** f(x)=x^2 +1 */
	private BigInteger f(BigInteger x, BigInteger n) {
		return x.pow(2).add(BigInteger.ONE).mod(n);
	}
}
