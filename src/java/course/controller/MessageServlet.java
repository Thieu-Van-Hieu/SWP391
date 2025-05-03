/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package course.controller;

import course.dto.message.MessageResponseDTO;
import course.facade.MessageFacade;
import exception.common.UnknownActionException;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 * @author quann
 */
public class MessageServlet extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	public void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			/* TODO output your page here. You may use following sample code. */
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

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
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

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
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
			} {
			request.setAttribute("error", "");
		}
		} else {
			throw new UnknownActionException();
		}

		response.sendRedirect(request.getContextPath() + "/MessageServlet");
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
