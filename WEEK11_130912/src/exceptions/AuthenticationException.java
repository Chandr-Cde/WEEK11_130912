package exceptions;

public class AuthenticationException extends Exception{
	
	public AuthenticationException() {
		super("Anda telah mencapai jumlah bats login");
	}
	
	public AuthenticationException(String msg) {
		super(msg);
	}

}
