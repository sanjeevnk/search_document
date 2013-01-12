package search;

/**
 * 
 * @author Sanjeev Kulkarni
 * @version 1.0
 *  Custom exception for messages in file search 
 */
public class FileSearchException extends Exception {

  public FileSearchException(){
		super();
	}
	
	public FileSearchException(String msg){
		super(msg);
	}
	
}
