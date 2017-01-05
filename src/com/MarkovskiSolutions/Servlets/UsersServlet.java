package com.MarkovskiSolutions.Servlets;

import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.StringJoiner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.MarkovskiSolutions.Entity.User;
import com.MarkovskiSolutions.Helpers.ErrorPage;

class UserRowMapper {
	/**Maps ResultSet row as User object
	 * 
	 * @param rs
	 * @param user
	 * @throws SQLException
	 */
	public static void map(ResultSet rs, User user) throws SQLException {
		user.setId(rs.getInt("id"));
		user.setFirstName(rs.getString("FirstName"));
		user.setLastName(rs.getString("LastName"));
		user.setDateBirth(rs.getDate("DateBirth"));
		user.setPhoneNumber(rs.getString("PhoneNumber"));
		user.setEMail(rs.getString("EMail"));
	}
}

class UserRequestMapper {
	/**
	 * Maps HttpRequest as User object
	 * @param user
	 * @param request
	 */
	public static void map(User user, HttpServletRequest request) {
		user.setId(Integer.valueOf(request.getParameter("id")));
		user.setFirstName(request.getParameter("FirstName"));
		user.setLastName(request.getParameter("LastName"));
		user.setDateBirth(Date.valueOf(request.getParameter("DateBirth")));
		user.setPhoneNumber(request.getParameter("PhoneNumber"));
		user.setEMail(request.getParameter("EMail"));
	}
}

/**
 * Servlet implementation class UsersServlet
 */
@WebServlet("/UsersServlet")
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsersServlet() {
		super();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://putnapomosht-byala.com:3306/putnapo1_usersmanagment",
					"putnapo1_root", "BhSH!=864*?7");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("action") != null) {
			
			if (request.getParameter("action").equals("details")) {
				showDetails(request, response);
			} else if (request.getParameter("action").equals("delete")) {
				deleteUsers(request, response);
			}
			
		} else {
			showAllUsers(request, response);
		}


	}

	/**
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void deleteUsers(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String[] usersForDeleteString = request.getParameterValues("du");
		
		StringJoiner ids = new StringJoiner(", ");
		if (usersForDeleteString != null) {
			
		
			for (String sid : usersForDeleteString) {
				try {
					
					int id = Integer.valueOf(sid);
					
					if (id != 0) {
						ids.add(String.valueOf(id));
					}
					
				} catch (NumberFormatException ex) {
					// Skip non-numeric values
				}
			}
			
			final String sql = "DELETE FROM users WHERE id in (" + ids + ")";
			try {
				Statement stmt = con.createStatement();
				response.getWriter().write(sql);
				/*stmt.executeUpdate(sql);
				response.sendRedirect("/UserManagementWebApplication/UsersServlet");*/
			} catch (SQLException e) {
				ErrorPage.showError(request, response, e);
			}
		} else {
			response.sendRedirect("/UserManagementWebApplication/UsersServlet");
		}
	}

	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void showDetails(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = new User();

		if (request.getParameter("id") != null) {
			String sql = "SELECT * FROM users WHERE id = ?";
			try {

				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, Integer.valueOf(request.getParameter("id")));

				ResultSet rs = stmt.executeQuery();

				if (!rs.next()) {
					ErrorPage.show404(request, response);
				} else {

					UserRowMapper.map(rs, user);
					request.setAttribute("user", user);

					request.getRequestDispatcher("/WEB-INF/users/details.jsp").forward(request, response);
				}

			} catch (SQLException e) {
				ErrorPage.showError(request, response, e);
			}
		} else {
			request.setAttribute("user", user);
			request.getRequestDispatcher("/WEB-INF/users/details.jsp").forward(request, response);
		}
	}

	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void showAllUsers(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			
			String search = "%";
			String order = "id";
			String orderType = "DESC";
			
			request.setAttribute("orderType", "ASC");
			
			if (request.getParameter("order") != null && (request.getParameter("order").equals("LastName") || request.getParameter("order").equals("DateBirth"))) {
				
				order = request.getParameter("order");
				
				if (request.getParameter("orderType").equals("ASC")) {
					orderType = "ASC";
					request.setAttribute("orderType", "DESC");
				}
				
			} 
			
			if (request.getParameter("search") != null) {
				search = "%" + request.getParameter("search") + "%";
			}

			String sql = "SELECT * FROM users WHERE FirstName LIKE ? OR LastName LIKE ? ORDER BY " + order + " " + orderType;

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, search);
			stmt.setString(2, search);

			
			
			ResultSet rs = stmt.executeQuery();

			ArrayList<User> users = new ArrayList<>();

			while (rs.next()) {

				User user = new User();
				UserRowMapper.map(rs, user);
				users.add(user);

			}

			stmt.close();

			request.setAttribute("users", users);
			request.setAttribute("search", request.getParameter("search"));
			request.getRequestDispatcher("/WEB-INF/users/index.jsp").forward(request, response);

		} catch (SQLException e) {
			ErrorPage.showError(request, response, e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = new User();
		UserRequestMapper.map(user, request);
		
		final String sql;
		if (user.getId() != 0) {
			sql = "UPDATE users SET FirstName = ?, LastName = ?, DateBirth = ?, PhoneNumber = ?, EMail = ? WHERE id = ?";
		} else {
			sql = "INSERT INTO users(FirstName, LastName, DateBirth, PhoneNumber, EMail) VALUES(?, ?, ?, ?, ?)";
		}
		
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, user.getFirstName());
			stmt.setString(2, user.getLastName());
			stmt.setDate(3, user.getDateBirth());
			stmt.setString(4, user.getPhoneNumber());
			stmt.setString(5, user.getEMail());
			
			if (user.getId() != 0) {
				stmt.setInt(6, user.getId());
			}
			
			stmt.executeUpdate();
			response.sendRedirect("/UserManagementWebApplication/UsersServlet");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
