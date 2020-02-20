package com.mycompany.staffweb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Update extends HttpServlet {
    
    private Connection connection;
    private String id;
    private String lastName;
    private String firstName;
    private String mi;
    private String address;
    private String city;
    private String state;
    private String telephone;
    private String email;
    
    @Override
    public void init() throws ServletException {
	initializeDB();
    }
    
    private void initializeDB() {
	try {
	    Class.forName("com.mysql.jdbc.Driver");
	    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lab18?useSSL=false", "root", "1729384#General");
	    System.out.println("connected....");
	} catch (SQLException e) {
	    System.out.println(e.getMessage());
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try (PrintWriter out = response.getWriter()) {
	    HttpSession session = request.getSession();
	    id = ((String) session.getAttribute("id"));
	    lastName = request.getParameter("lastName");
	    firstName = request.getParameter("firstName");
	    mi = request.getParameter("mi");
	    address = request.getParameter("address");
	    city = request.getParameter("city");
	    state = request.getParameter("state");
	    telephone = request.getParameter("telephone");
	    email = request.getParameter("email");
	    PreparedStatement update = connection.prepareStatement("UPDATE lab18.staff set lastname=?,firstname=?,mi=?,address=?,city=?,state=?,telephone=?,email=? where idstaff=?");
	    update.setString(1, lastName);
	    update.setString(2, firstName);
	    update.setString(3, mi);
	    update.setString(4, address);
	    update.setString(5, city);
	    update.setString(6, state);
	    update.setString(7, telephone);
	    update.setString(8, email);
	    update.setString(9, id);
	    update.executeUpdate();
	    update.close();
	    System.out.println(id);
	    out.print("<!DOCTYPE html>\n"
		      + "<html>\n"
		      + "    <head>\n"
		      + "        <title>Staff Information</title>\n"
		      + "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
		      + "	<style>\n"
		      + "	    @import url(staff_css.css);\n"
		      + "	</style>\n"
		      + "	<script src=\"staff_js.js\"></script>\n"
		      + "    </head>\n"
		      + "    <body id=\"body\">\n"
		      + "        <main>\n"
		      + "	    <form id=\"form\" method=\"post\" action=\"Update\">\n"
		      + "	    <h4>This Record Update successfully</h4>"
		      + "		<table id=\"table\">\n"
		      + "		    <tr>\n"
		      + "			<td>\n"
		      + "			    <h4>ID: </h4>\n"
		      + "			</td>\n"
		      + "			<td>\n"
		      + "			    <input type=\"text\" maxlength=\"9\" value=\"" + id + "\" size=\"20\" name=\"id\" id=\"1\" disabled/>\n"
		      + "			</td>\n"
		      + "		    </tr>\n"
		      + "		    <tr>\n"
		      + "			<td>\n"
		      + "			    <h4>Last Name: </h4>\n"
		      + "			</td>\n"
		      + "			<td>\n"
		      + "			    <input type=\"text\" maxlength=\"15\" size=\"20\" value=\"" + lastName + "\" name=\"lastName\" id=\"2\" />\n"
		      + "			</td>\n"
		      + "			<td>\n"
		      + "			    <h4>First Name: </h4>\n"
		      + "			</td>\n"
		      + "			<td>\n"
		      + "			    <input type=\"text\" maxlength=\"15\" size=\"20\" value=\"" + firstName + "\" name=\"firstName\" id=\"3\"/>\n"
		      + "			</td>\n"
		      + "			<td>\n"
		      + "			    <h4>MI : </h4>\n"
		      + "			</td>\n"
		      + "			<td>\n"
		      + "			    <input type=\"text\" maxlength=\"1\" size=\"1\" value=\"" + mi + "\" name=\"mi\" id=\"4\"/>\n"
		      + "			</td>\n"
		      + "		    </tr>\n"
		      + "		    <tr>\n"
		      + "			<td>\n"
		      + "			    <h4>Address: </h4>\n"
		      + "			</td>\n"
		      + "			<td>\n"
		      + "			    <input type=\"text\" maxlength=\"20\" size=\"20\" value=\"" + address + "\" name=\"address\" id=\"5\"/>\n"
		      + "			</td>\n"
		      + "		    </tr>\n"
		      + "		    <tr>\n"
		      + "			<td>\n"
		      + "			    <h4>City: </h4>\n"
		      + "			</td>\n"
		      + "			<td>\n"
		      + "			    <input type=\"text\" maxlength=\"20\" size=\"20\" value=\"" + city + "\" name=\"city\" id=\"6\"/>\n"
		      + "			</td>\n"
		      + "			<td>\n"
		      + "			    <h4>State: </h4>\n"
		      + "			</td>\n"
		      + "			<td>\n"
		      + "			    <input type=\"text\" maxlength=\"2\" size=\"20\" value=\"" + state + "\" name=\"state\" id=\"7\"/>\n"
		      + "			</td>\n"
		      + "		    </tr>\n"
		      + "		    <tr>\n"
		      + "			<td>\n"
		      + "			    <h4>Telephone: </h4>\n"
		      + "			</td>\n"
		      + "			<td>\n"
		      + "			    <input type=\"number\" maxlength=\"10\" size=\"20\" value=\"" + telephone + "\" name=\"telephone\" id=\"8\"/>\n"
		      + "			</td>\n"
		      + "			<td>\n"
		      + "			    <h4>Email: </h4>\n"
		      + "			</td>\n"
		      + "			<td>\n"
		      + "			    <input type=\"text\" maxlength=\"40\" size=\"20\" value=\"" + email + "\" name=\"email\" id=\"9\" />\n"
		      + "			</td>\n"
		      + "		    </tr>\n"
		      + "		</table>\n"
		      + "	    <a href=\"staff_html.html\"><input type=\"button\" id=\"sub\" value=\"Back\"/> </a>\n" + "\n"
		      + "	    </form>\n"
		      + "	</main>\n"
		      + "	<br>\n"
		      + "    </body>\n"
		      + "</html>\n"
		      + "");
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}
    }
    
    @Override
    public String getServletInfo() {
	return "Short description";
    }
    
}
