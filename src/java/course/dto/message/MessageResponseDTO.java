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

	public MessageResponseDTO(Builder builder) {
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

	class Builder {
		private int id;
		private String sender;
		private String content;
		private Timestamp sendAt;

		public void id(int id) {
			this.id = id;
		}

		public void sender(String sender) {
			this.sender = sender;
		}

		public void content(String content) {
			this.content = content;
		}

		public void sendAt(Timestamp sendAt) {
			this.sendAt = sendAt;
		}

		public MessageResponseDTO build() {
			return new MessageResponseDTO(this);
		}
	}
}