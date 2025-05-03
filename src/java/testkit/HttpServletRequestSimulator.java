/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testkit;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doAnswer;
import org.mockito.stubbing.Answer;

public class HttpServletRequestSimulator {

	private HttpServletRequest request;

	public static Builder builder() {
		return new Builder();
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

		public Builder setAttribute(String key, Object value) {
			when(request.getAttribute(key)).thenReturn(value);
			return this;
		}

		public Builder attributes(Map<String, Object> attributes) {
			for (Map.Entry<String, Object> entry : attributes.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
				setAttribute(key, value);
			}
			return this;
		}

		public HttpServletRequestSimulator build() {
			return new HttpServletRequestSimulator(request);
		}
	}

	private void disableGetRequestDispatcher() throws Exception {
		RequestDispatcher dispatcher = mock(RequestDispatcher.class);
		when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
		doNothing().when(dispatcher).forward(any(), any());
	}
	
	private void enableSetAttribute() {
		doAnswer(invocation -> when(request.getAttribute((String) invocation.getArgument(0))).thenReturn(invocation.getArgument(1))).when(request).setAttribute(anyString(), any());
	}

	private HttpServletRequestSimulator(HttpServletRequest request) {
		this.request = request;
		
		try {
			disableGetRequestDispatcher();
			enableSetAttribute();
		} catch (Exception e) {
		}
	}

}
