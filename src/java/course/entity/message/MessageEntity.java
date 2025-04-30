/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package course.entity.message;

import java.sql.Timestamp;

public class MessageEntity {

    private int id;
    private int courseId;
    private int memberId;
    private String content;
    private Timestamp sendAt;

	public MessageEntity() {
	}

	public MessageEntity(int id, int courseId, int memberId, String content, Timestamp sendAt) {
		this.id = id;
		this.courseId = courseId;
		this.memberId = memberId;
		this.content = content;
		this.sendAt = sendAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getSendAt() {
		return sendAt;
	}

	public void setSendAt(Timestamp sendAt) {
		this.sendAt = sendAt;
	}
}