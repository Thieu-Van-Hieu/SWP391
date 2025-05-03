package course.controller;

import course.dto.message.MessageResponseDTO;
import course.facade.MessageFacade;
import exception.common.UnknownActionException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class MessageServlet extends HttpServlet {

	public void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet MessageServlet</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Servlet MessageServlet at " + request.getContextPath() + "</h1>");
			out.println("</body>");
			out.println("</html>");
		}
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, UnknownActionException {
		String action = request.getParameter("action");

		if (action == null) {
			action = "";
		}

		Map<String, Function<HttpServletRequest, ArrayList<MessageResponseDTO>>> actionMap = Map.of(
				"getAll", MessageFacade::getMessagesByCourseId
		);
		if (actionMap.containsKey(action)) {
			ArrayList<MessageResponseDTO> messageResponseDTOs = actionMap.get(action).apply(request);
			request.setAttribute("messageResponseDTOs", messageResponseDTOs);
		} else {
			throw new UnknownActionException();
		}
		
		request.getRequestDispatcher("").forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action == null) {
			action = "";
		}

		Map<String, Predicate<HttpServletRequest>> actionMap = Map.of(
				"sendMessage", MessageFacade::sendMessage,
				"editMessage", MessageFacade::editMessage,
				"deleteMessage", MessageFacade::deleteMessage
		);

		if (actionMap.containsKey(action)) {
			if (actionMap.get(action).test(request)) {
				request.setAttribute("success", "");
			} else {
			request.setAttribute("error", "");
		}
		} else {
			throw new UnknownActionException();
		}

		response.sendRedirect(request.getContextPath() + "/MessageServlet");
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}

}
