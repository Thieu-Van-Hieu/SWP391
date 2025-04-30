/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package course.entity;

import java.sql.Timestamp;

public class MessageEntity {

	private int id;
	private int courseId;
	private int memberId;
	private String content;
	private Timestamp sendAt;

	public MessageEntity(Builder builder) {
		this.id = builder.id;
		this.courseId = builder.courseId;
		this.memberId = builder.memberId;
		this.content = builder.content;
		this.sendAt = builder.sendAt;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setSendAt(Timestamp sendAt) {
		this.sendAt = sendAt;
	}

	class Builder {
		private int id;
		private int courseId;
		private int memberId;
		private String content;
		private Timestamp sendAt;

		public Builder() {
		}

		public void setId(int id) {
			this.id = id;
		}

		public void setCourseId(int courseId) {
			this.courseId = courseId;
		}

		public void setMemberId(int memberId) {
			this.memberId = memberId;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public void setSendAt(Timestamp sendAt) {
			this.sendAt = sendAt;
		}
		
		public MessageEntity build() {
			return new MessageEntity(this);
		}
	}
}
