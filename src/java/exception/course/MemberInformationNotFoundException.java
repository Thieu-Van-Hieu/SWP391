package exception.course;

public class MemberInformationNotFoundException extends RuntimeException {

	public MemberInformationNotFoundException() {
		super("Member information not found!!!");
	}

}
