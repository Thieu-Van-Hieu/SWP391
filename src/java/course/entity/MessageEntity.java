package course.entity;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageEntity {

	private int messageId;
	private int courseId;
	private int memberId;
	private String content;
	private Timestamp sendAt;
}
