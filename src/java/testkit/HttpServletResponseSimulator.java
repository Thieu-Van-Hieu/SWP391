package testkit;

import jakarta.servlet.http.HttpServletResponse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;

public class HttpServletResponseSimulator {
	
	private HttpServletResponse response;

	public HttpServletResponse getResponse() {
		return response;
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		private HttpServletResponse response = mock(HttpServletResponse.class);
		
		public HttpServletResponseSimulator build() {
			return new HttpServletResponseSimulator(response);
		}
	}
	
	private void disableSendRedirect() throws Exception {
		doNothing().when(response).sendRedirect(anyString());
	}
	
	private HttpServletResponseSimulator(HttpServletResponse response) {
		this.response = response;
		
		try {
			disableSendRedirect();
		} catch (Exception e) {
		}
	}
}
