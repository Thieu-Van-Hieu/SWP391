package course.entity;

import java.sql.Timestamp;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utils.tablemapper.annotation.Column;
import utils.tablemapper.annotation.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "messages")
public class MessageEntity {
        
        @Column(name = "id")
	private int messageId;
	private int courseId;
	private int memberId;
	private String content;
	private Timestamp sendAt;

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MessageEntity other = (MessageEntity) obj;
        if (this.messageId != other.messageId) {
            return false;
        }
        if (this.courseId != other.courseId) {
            return false;
        }
        if (this.memberId != other.memberId) {
            return false;
        }
        if (!Objects.equals(this.content, other.content)) {
            return false;
        }
        
        final int DELTA = 2;
        final long diff = Math.abs(this.sendAt.getTime() - other.sendAt.getTime());
        return diff <= DELTA;
    }
}
