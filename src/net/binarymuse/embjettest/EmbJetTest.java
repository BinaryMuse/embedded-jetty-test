package net.binarymuse.embjettest;

import net.binarymuse.embjettest.handlers.FourOhFourHander;
import net.binarymuse.embjettest.servlets.SessionServlet;
import net.binarymuse.embjettest.servlets.ShutdownServlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;

public class EmbJetTest {

    public static Server server;

    public static void main(String[] args) throws Throwable {
        int port = 8080;
        if(args.length == 1) {
            port = Integer.valueOf(args[0]);
        }
        EmbJetTest.server = new Server(port);

        // First our servlet handlers
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        EmbJetTest.server.setHandler(context);
        // SessionServlet on /servlets/session
        context.addServlet(new ServletHolder(new SessionServlet()), "/servlets/session");
        context.addServlet(new ServletHolder(new ShutdownServlet()), "/servlets/shutdown");

        // Now our resource handler
        ResourceHandler resource = new ResourceHandler();
        resource.setDirectoriesListed(true);
        resource.setWelcomeFiles(new String[] {"index.htm"});
        resource.setResourceBase("./htdocs");

        // war handler
        WebAppContext waContext = new WebAppContext();
        waContext.setContextPath("/webapp");
        waContext.setWar("./apps/DefaultApp.war");

        // Make it a list
        HandlerList handlers = new HandlerList();
        handlers.addHandler(context);
        handlers.addHandler(resource);
        handlers.addHandler(waContext);
        handlers.addHandler(new FourOhFourHander()); // give 404's for anything not handled yet

        // Add it to the server
        EmbJetTest.server.setHandler(handlers);

        EmbJetTest.server.setThreadPool(new QueuedThreadPool(10));

        EmbJetTest.server.start();
        EmbJetTest.server.join();
    }

}
