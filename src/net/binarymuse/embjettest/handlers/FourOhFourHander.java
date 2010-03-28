package net.binarymuse.embjettest.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

/**
 * A catch-all handler; put it at the end of a HandlerList to
 * hand out free 404 errors to any URI that wasn't handled.
 * @author BinaryMuse
 *
 */
public class FourOhFourHander extends AbstractHandler {

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=utf-8");
        response.sendError(HttpServletResponse.SC_NOT_FOUND,
                "I'm terribly sorry, but the page you were looking for wasn't found.");
        baseRequest.setHandled(true);
    }

}
