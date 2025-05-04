package course.entity;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utils.tablemapper.annotation.Column;
import utils.tablemapper.annotation.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "messages")
public class MessageEntity {
        
        @Column(name = "id")
	private int messageId;
	private int courseId;
	private int memberId;
	private String content;
	private Timestamp sendAt;
}
