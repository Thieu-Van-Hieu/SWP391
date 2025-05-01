/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package course.dto.message;

import java.sql.Timestamp;

public class MessageResponseDTO {

	private int messageId;
	private String firstName;
	private String lastName;
	private String content;
	private Timestamp sendAt;

	public MessageResponseDTO() {
	}

	public MessageResponseDTO(Builder builder) {
		this.messageId = builder.messageId;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.content = builder.content;
		this.sendAt = builder.sendAt;
	}

	public int getMessageId() {
		return messageId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getContent() {
		return content;
	}

	public Timestamp getSendAt() {
		return sendAt;
	}

	public static class Builder {

		private int messageId;
		private String firstName;
		private String lastName;
		private String content;
		private Timestamp sendAt;
		
		public Builder messageId(int messageId) {
			this.messageId = messageId;
			return this;
		}
		
		public Builder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}
		
		public Builder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}
		
		public Builder content(String content) {
			this.content = content;
			return this;
		}
		
		public Builder sendAt(Timestamp sendAt) {
			this.sendAt = sendAt;
			return this;
		}
		
		public MessageResponseDTO build() {
			return new MessageResponseDTO(this);
		}
	}
}
