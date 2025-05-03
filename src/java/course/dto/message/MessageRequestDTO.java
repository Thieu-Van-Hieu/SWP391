package course.dto.message;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequestDTO {

	private int messageId;
	private int courseId;
	private int memberId;
	private String content;
}
