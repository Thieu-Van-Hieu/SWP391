/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package course.dto.message;

import java.sql.Timestamp;

public class MessageResponseDTO {
	private int id;
	private String sender;
	private String content;
	private Timestamp sendAt;

	public MessageResponseDTO() {
	}

	private MessageResponseDTO(Builder builder) {
		this.id = builder.id;
		this.sender = builder.sender;
		this.content = builder.content;
		this.sendAt = builder.sendAt;
	}

	public int getId() {
		return id;
	}

	public String getSender() {
		return sender;
	}

	public String getContent() {
		return content;
	}

	public Timestamp getSendAt() {
		return sendAt;
	}

	public static class Builder {
		private int id;
		private String sender;
		private String content;
		private Timestamp sendAt;

		public Builder id(int id) {
			this.id = id;
			return this;
		}

		public Builder sender(String sender) {
			this.sender = sender;
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