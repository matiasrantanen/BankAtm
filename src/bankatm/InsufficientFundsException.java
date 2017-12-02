package bankatm;
//Exception class for the insufficientexception
public class InsufficientFundsException extends Exception {
    
	private static final long serialVersionUID = 1L;

	public InsufficientFundsException() {
        super("Insufficient funds!");
    }

}