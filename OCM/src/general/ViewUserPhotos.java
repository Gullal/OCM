package general;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewUserPhotos
 */
public class ViewUserPhotos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	   Statement st;
	   ResultSet rs;    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewUserPhotos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		try
		{
			out.println("<div id='info'><center><h2>Photos</h2><ul>");
			String userid=request.getParameter("id").toString();
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
			con=DriverManager.getConnection("jdbc:db2://localhost:50000/PRAC","db2inst1","1491");
			st=con.createStatement();
			rs=st.executeQuery("SELECT ID,CAPTION FROM PHOTOS WHERE UPLOADER = (SELECT USERNAME FROM LOGIN WHERE ID="+userid+") ");
			while(rs.next())
			{
				out.println("<div id='pic'><img height=200 width=200 src='http://localhost:8080/OCM/ViewPhoto?post=photos&id="+rs.getString(1)+"'/><br><a class='imgs' href='http://localhost:8080/OCM/Preview?id="+rs.getString(1)+"'>"+rs.getString(2)+"</a></li></div>");
			}
			
			out.println("</ul></center></div>");
		}
		catch(Exception e)
		{
			out.println(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
