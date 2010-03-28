package net.binarymuse.embjettest.servlets;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.binarymuse.embjettest.State;

public class SessionServlet extends HttpServlet {

    private static final long serialVersionUID = -5896453056121248656L;
    
    private State state;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("<html><head><title>Embedded Jetty Test</title></head><body>");
        response.getWriter().println("Servlet processing<br />");
        response.getWriter().println("session=" + request.getSession(true).getId() + "<br />");
        response.getWriter().println(request.toString() + "<br />");
        
        // Generate values for our two state objects
        Random random = new Random();
        int num = random.nextInt(1000000);
        String sNum = Integer.toString(num);
        int num2 = random.nextInt(1000000);
        String sNum2 = Integer.toString(num2);
        
        // Work with the session based state object.
        HttpSession session = request.getSession();
        response.getWriter().println("<h2>Session State Object</h2>");
        if(session.getAttribute("state") == null)
        {
            response.getWriter().println("No State object in session! Initializing one.<br />");
            State state = new State();
            state.setState(sNum);
            session.setAttribute("state", state);
            response.getWriter().println("Set State object value to " + sNum + "<br />");
        }
        else
        {
            State state = (State) session.getAttribute("state");
            response.getWriter().println("Found a State object with value " + state.getState() + "<br />");
            state.setState(sNum);
            response.getWriter().println("Changed State value to " + state.getState() + "<br />");
        }
        
        // Work with the servlet state object -- shared amongst all sessions!!
        response.getWriter().println("<h2>Servlet State Object</h2>");
        response.getWriter().println("<em>Open this page in another browser to test</em><br/>");
        if(this.state == null)
        {
            this.state = new State();
            response.getWriter().println("First visitor to this servlet since restart. " +
                    "No servlet based State!<br/>");
            this.state.setState(sNum2);
            response.getWriter().println("Set State to " + sNum2 + "<br />");
        }
        else
        {
            response.getWriter().println("Found State with value " + this.state.getState() + "<br />");
            this.state.setState(sNum2);
            response.getWriter().println("Set State to " + sNum2 + "<br />");
        }
        
        
        response.getWriter().println("</body></html>");
    }

}
