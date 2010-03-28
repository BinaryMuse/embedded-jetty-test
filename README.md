# Embedded Jetty Test

This project is just a simple test of running an embedded Jetty server from
within Java code. The example does a few interesting/useful things:

## Manages servlets based on file patterns, including subdirectories

`EmbJetTest.java` sets up a handler to handle a simple servlet, `SessionServlet`,
on the URI `/servlets/session`. This servlet manages session objects as well as
a servlet object, shared amongst any instance of the servlet. Test the servlet
by opening the page in multiple browsers and refreshing. You can see that the
servlet state object is shared amongst all visitors.

Notice that the State object needs to be thread safe!

## Serving files from a directory

`EmbJetTest.java` also contains code for setting up a file server. The file server
is given a directory to use as its "base" directory, as well as a few other
options (including a list of files to use as "welcome files").

## Catching unhandled URIs

By using a `FourOhFourHandler` as the last item in the `HandlerList` for the
server, we can effectively catch any URI that was not handled already and
generate a 404 for it.

## Requirements

Requires the Jetty jars from the `lib` folder in your classpath.
See [the Jetty 7.x Stable download page][1] for a link to the Jetty distribution.

  [1]: http://download.eclipse.org/jetty/stable-7/dist/ "Jetty 7.x Stable Downloads"