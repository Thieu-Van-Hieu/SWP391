package exception.course;

public class MessageNotFoundException extends RuntimeException {
	public MessageNotFoundException() {
		super("Message not found!!!");
	}
}
