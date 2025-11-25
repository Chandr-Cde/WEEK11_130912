package week11.chandra.id.ac.umn;

public class AuthenticationException extends Exception{
	
	public AuthenticationException() {
		super("Anda telah mencapai jumlah bats login");
	}
	
	public AuthenticationException(String msg) {
		super(msg);
	}

}
