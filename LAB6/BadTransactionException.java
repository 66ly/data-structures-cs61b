
public class BadTransactionException extends Exception{
	
	public int amount;
	
	public BadTransactionException(int invalidamount){
		super("invalid amount to deposit: " + invalidamount);
		
		amount = invalidamount;
	}

}
