/**
 * 
 */
package com.MarkovskiSolutions.Helpers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Georgi Chavdarov
 *
 */
public final class ErrorPage {

	/**
	 * @param request
	 * @param response
	 * @param exception
	 */
	public static void showError(final HttpServletRequest request, final HttpServletResponse response, final Exception exception)
			throws ServletException, IOException {
		request.setAttribute("error", exception);
		request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
	}
	
	/**
	 * @param request
	 * @param response
	 * @param error
	 */
	public static void showError(final HttpServletRequest request, final HttpServletResponse response, final String error)
			throws ServletException, IOException {
		request.setAttribute("error", error);
		request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
	}
	
	/**
	 * @param request
	 * @param response
	 * @param error
	 */
	public static void show404(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("error", "Page not found!");
		request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
	}
}
