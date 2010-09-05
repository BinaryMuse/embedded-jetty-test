# Embedded Jetty Test

This project is just a simple test of running an embedded Jetty server from
within Java code.

## Installing and Running

These instructions assume you have [Apache Ant][1] installed. They also assume
that you begin the procedure in `~/src/`. Adjust the instructions for whatever
path you decide to use.

These instructions also download version 7.1.6.v20100715 of the Jetty
distribution (on which the project depends). Please substitute the file and
folder names depending on which version you download.

  - Clone the repository and enter the directory:
    * `git clone git://github.com/BinaryMuse/embedded-jetty-test.git`
    * `cd embedded-jetty-test`
  - Download one of the Jetty distribution archives from
    [the Jetty 7.x Stable download page][2] (using version 7.1.6.v20100715
    here):
    * `wget http://download.eclipse.org/jetty/stable-7/dist/jetty-distribution-7.1.6.v20100715.tar.gz` 
  - Extract the archive:
    * `tar -zxvf jetty-distribution-7.1.6.v20100715.tar.gz`
  - Copy the `.jar` files from the Jetty distribution to the repository's
    `lib` folder:
    * `cp jetty-distribution-7.1.6.v20100715/lib/*.jar lib/`
  - Run the project:
    * `ant`

You should be able to visit [http://localhost:8080][3] in your browser

  [1]: http://ant.apache.org/ "Apache Ant"
  [2]: http://download.eclipse.org/jetty/stable-7/dist/ "Jetty 7.x Stable Downloads"
  [3]: http://localhost:8080 "localhost port 8080"

## Examples

The example app demonstrates a few interesting/useful things. Here is a list
of all URI's:

  * Serving files from a directory: `/` and `/images`
  * Managing servlets based on file patterns: `/servlets/session`
  * Serving a webapp in war form: `/webapp`
  * Catching unhandled URIs: any non-handled URI (e.g. `/fake-uri`)

Details on each follow:

### Serving files from a directory

`EmbJetTest.java` contains code for setting up a file server. The file server
is given a directory to use as its "base" directory, as well as a few other
options (including a list of files to use as "welcome files").

### Managing servlets based on file patterns, including subdirectories

`EmbJetTest.java` sets up a handler to handle a simple servlet, `SessionServlet`,
on the URI `/servlets/session`. This servlet manages session objects as well as
a servlet object, shared amongst any instance of the servlet. Test the servlet
by opening the page in multiple browsers and refreshing. You can see that the
servlet state object is shared amongst all visitors.

Notice that the State object needs to be thread safe!

### Serving a webapp in war form

`EmbJetTest.java` also sets up a `WebAppContext` to serve up a web application
packaged in a war on a subdirectory (in this case `webapp`). I used a GWT
application as a test.

### Catching unhandled URIs

By using a `FourOhFourHandler` as the last item in the `HandlerList` for the
server, we can effectively catch any URI that was not handled already and
generate a 404 for it.