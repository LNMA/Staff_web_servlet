package com.mycompany.staffweb;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.CookieStore;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

public class StaffInfo extends HttpServlet {

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

    private void view(HttpServletRequest request, PrintWriter out) {
	try {
	    id = request.getParameter("id");
	    PreparedStatement view = connection.prepareStatement("SELECT * FROM staff where idStaff = ?");
	    view.setString(1, id);
	    ResultSet rset = view.executeQuery();
	    while (rset.next()) {
		id = rset.getString(1);
		lastName = rset.getString(2);
		firstName = rset.getString(3);
		mi = rset.getString(4);
		address = rset.getString(5);
		city = rset.getString(6);
		state = rset.getString(7);
		telephone = rset.getString(8);
		email = rset.getString(9);
	    }
	    view.close();

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
		      + "	    <form id=\"form\" method=\"post\" action=\"StaffInfo\">\n"
		      + "		<table id=\"table\">\n"
		      + "		    <tr>\n"
		      + "			<td>\n"
		      + "			    <h4>ID: </h4>\n"
		      + "			</td>\n"
		      + "			<td>\n"
		      + "			    <input type=\"text\" maxlength=\"9\" value=\"" + id + "\" size=\"20\" name=\"id\" id=\"1\"/>\n"
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
	    out.flush();
	    out.close();
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}
    }

    private void insert(HttpServletRequest request, PrintWriter out) {
	try {
	    id = request.getParameter("id");
	    lastName = request.getParameter("lastName");
	    firstName = request.getParameter("firstName");
	    mi = request.getParameter("mi");
	    address = request.getParameter("address");
	    city = request.getParameter("city");
	    state = request.getParameter("state");
	    telephone = request.getParameter("telephone");
	    email = request.getParameter("email");
	    PreparedStatement insert = connection.prepareStatement("insert into lab18.staff(idStaff, lastName, firstName, mi, address, city, state, telephone, email) values (?,?,?,?,?,?,?,?,?)");
	    insert.setString(1, id);
	    insert.setString(2, lastName);
	    insert.setString(3, firstName);
	    insert.setString(4, mi);
	    insert.setString(5, address);
	    insert.setString(6, city);
	    insert.setString(7, state);
	    insert.setString(8, telephone);
	    insert.setString(9, email);
	    insert.executeUpdate();
	    insert.close();
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
		      + "	    <form id=\"form\" method=\"post\" action=\"StaffInfo\">\n"
		      + "	    <h4>Record insert successfully</h4>"
		      + "		<table id=\"table\">\n"
		      + "		    <tr>\n"
		      + "			<td>\n"
		      + "			    <h4>ID: </h4>\n"
		      + "			</td>\n"
		      + "			<td>\n"
		      + "			    <input type=\"text\" maxlength=\"9\" value=\"" + id + "\" size=\"20\" name=\"id\" id=\"1\"/>\n"
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
	    out.flush();
	    out.close();
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

    }

    private void clear(HttpServletRequest request, PrintWriter out) {
	try {
	    id = request.getParameter("id");
	    PreparedStatement view = connection.prepareStatement("SELECT * FROM staff where idStaff = ?");
	    view.setString(1, id);
	    ResultSet rset = view.executeQuery();
	    while (rset.next()) {
		id = rset.getString(1);
		lastName = rset.getString(2);
		firstName = rset.getString(3);
		mi = rset.getString(4);
		address = rset.getString(5);
		city = rset.getString(6);
		state = rset.getString(7);
		telephone = rset.getString(8);
		email = rset.getString(9);
	    }
	    view.close();
	    PreparedStatement delete = connection.prepareStatement("DELETE from lab18.staff where idstaff = ?");
	    delete.setString(1, id);
	    delete.executeUpdate();
	    delete.close();
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
		      + "	    <form id=\"form\" method=\"post\" action=\"StaffInfo\">\n"
		      + "	    <h4>This Record delete successfully</h4>"
		      + "		<table id=\"table\">\n"
		      + "		    <tr>\n"
		      + "			<td>\n"
		      + "			    <h4>ID: </h4>\n"
		      + "			</td>\n"
		      + "			<td>\n"
		      + "			    <input type=\"text\" maxlength=\"9\" value=\"" + id + "\" size=\"20\" name=\"id\" id=\"1\"/>\n"
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
	    out.flush();
	    out.close();
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}
    }
    
    private void update(HttpServletResponse response, HttpServletRequest request, PrintWriter out) {
	try {
	    id = request.getParameter("id");
	    HttpSession session = request.getSession();
	    session.setAttribute("id", id);
	    PreparedStatement view = connection.prepareStatement("SELECT * FROM staff where idStaff = ?");
	    view.setString(1, id);
	    ResultSet rset = view.executeQuery();
	    while (rset.next()) {
		id = rset.getString(1);
		lastName = rset.getString(2);
		firstName = rset.getString(3);
		mi = rset.getString(4);
		address = rset.getString(5);
		city = rset.getString(6);
		state = rset.getString(7);
		telephone = rset.getString(8);
		email = rset.getString(9);
	    }
	    view.close();
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
		      + "	    <h4>please edit this recorde</h4>"
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
		      + "	    <input type=\"submit\" name=\"sub\" value=\"Update\" id=\"sub\"/>\n" + "\n"
		      + "	    </form>\n"
		      + "	</main>\n"
		      + "	<br>\n"
		      + "    </body>\n"
		      + "</html>\n"
		      + "");
	    out.flush();
	    out.close();
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html;charset=UTF-8");
	try (PrintWriter out = response.getWriter()) {
	    String oper = request.getParameter("operation");
	    if (oper != null) {
		if (oper.equals("view")) {
		    view(request, out);
		}
		if (oper.equals("insert")) {
		    insert(request, out);
		}
		if (oper.equals("clear")) {
		    clear(request, out);
		}
		if (oper.equals("update")) {
		    update(response, request, out);
		}
	    }
	    out.flush();
	    out.close();
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}
    }

    @Override
    public String getServletInfo() {
	return "Short description";
    }

}

