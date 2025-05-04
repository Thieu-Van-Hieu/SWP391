package course.dto.message;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MessageResponseDTO {

	private int messageId;
	private String senderName;
	private String content;
	private Timestamp sendAt;
}
