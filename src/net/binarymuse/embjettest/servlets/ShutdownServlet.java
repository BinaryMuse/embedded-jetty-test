package net.binarymuse.embjettest.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.binarymuse.embjettest.ShutdownThread;

public class ShutdownServlet extends HttpServlet {

    private static final long serialVersionUID = -6403026498208170371L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("<html><head><title>Jetty Shut Down</title></head><body>");
        response.getWriter().println("Shutting down the server.");
        response.getWriter().println("</body></html>");

        Thread thread = new Thread(new ShutdownThread());
        thread.start();
    }

}
