package in.vaish.pw.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/test2")
public class SessionServlet2 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Getting the old session object for the same user
        HttpSession session = request.getSession(false);

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Session Information</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background-color: #f5f5f5; margin: 0; padding: 0; }");
        out.println("h1 { text-align: center; color: #35424a; }");
        out.println("table { margin: 20px auto; border-collapse: collapse; }");
        out.println("th, td { border: 2px solid #35424a; padding: 8px; text-align: left; }");
        out.println("th { background-color: #35424a; color: white; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Session Information</h1>");

        if (session == null) {
            out.println("<p>No session information available for this user.</p>");
        } else {
            out.println("<table>");
            out.println("<tr><th>Attribute Name</th><th>Attribute Value</th></tr>");

            Enumeration<String> names = session.getAttributeNames();

            while (names.hasMoreElements()) {
                String name = (String) names.nextElement();
                Object value = session.getAttribute(name);
                out.println("<tr><td>" + name + "</td><td>" + value + "</td></tr>");
            }
            out.println("</table>");

            long creationTime = session.getCreationTime();
            long lastAccessedTime = session.getLastAccessedTime();
            int maxInactiveInterval = session.getMaxInactiveInterval();

            out.println("<h2>Additional Information</h2>");
            out.println("<p>Session created at: " + new Date(creationTime) + "</p>");
            out.println("<p>Last accessed at: " + new Date(lastAccessedTime) + "</p>");
            out.println("<p>Max inactive interval: " + maxInactiveInterval + " seconds</p>");
        }

        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}

