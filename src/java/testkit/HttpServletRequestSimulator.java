/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testkit;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import static org.mockito.Mockito.*;

public class HttpServletRequestSimulator {

	private HttpServletRequest request;

	private HttpServletRequestSimulator(Builder builder) {
		this.request = builder.request;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public static class Builder {

		private HttpServletRequest request = mock(HttpServletRequest.class);

		public Builder addParam(String key, String value) {
			when(request.getParameter(key)).thenReturn(value);
			return this;
		}

		public Builder params(Map<String, String> params) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				addParam(key, value);
			}
			return this;
		}

		public Builder addAttribute(String key, Object value) {
			when(request.getAttribute(key)).thenReturn(value);
			return this;
		}

		public Builder attributes(Map<String, Object> attributes) {
			for (Map.Entry<String, Object> entry : attributes.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
				addAttribute(key, value);
			}
			return this;
		}

		public HttpServletRequestSimulator build() {
			return new HttpServletRequestSimulator(this);
		}
	}
}
