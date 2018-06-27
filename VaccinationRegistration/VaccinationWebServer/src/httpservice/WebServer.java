package httpservice;

import configuration.Config;
import configuration.Configuration;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebServer {

    private static final Logger _logger = LoggerFactory.getLogger(WebServer.class);

    public void start() throws Exception {

        Server server = new Server();

        QueuedThreadPool threadPool = new QueuedThreadPool();
        threadPool.setMinThreads(100);
        threadPool.setMaxThreads(2000);
        server.setThreadPool(threadPool);
        //_logger.info("Home path: {}", Configuration.getHomePath());
        int port_listen = Config.getParamInt("rest", "port_listen", 6996);
        _logger.info("Listening on port {}", port_listen);

        SelectChannelConnector connector = new SelectChannelConnector();
        connector.setPort(port_listen);
        connector.setMaxIdleTime(30000);
        connector.setConfidentialPort(8443);
        connector.setStatsOn(false);
        connector.setLowResourcesConnections(20000);
        connector.setLowResourcesMaxIdleTime(5000);

        server.setConnectors(new Connector[]{connector});

        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);

        handler.addServletWithMapping("webservlet.HomeServlet", "/");
        handler.addServletWithMapping("webservlet.HomeServlet", "/centre");
        handler.addServletWithMapping("webservlet.HomeServlet", "/centre/*");
        handler.addServletWithMapping("webservlet.HomeServlet", "/login");
        handler.addServletWithMapping("webservlet.HomeServlet", "/register");
        handler.addServletWithMapping("webservlet.HomeServlet", "/register/*");
        handler.addServletWithMapping("webservlet.HomeServlet", "/result");
        handler.addServletWithMapping("webservlet.HomeServlet", "/monitor");
//        handler.addServletWithMapping("webservlet.HomeServlet", "/event");
//        handler.addServletWithMapping("webservlet.HomeServlet", "/event/*");
        handler.addServletWithMapping("webservlet.HomeServlet", "/admin/monitor");
        handler.addServletWithMapping("webservlet.HomeServlet", "/admin/register");
        handler.addServletWithMapping("webservlet.HomeServlet", "/admin/register/*");
        handler.addServletWithMapping("webservlet.HomeServlet", "/admin/create");
        handler.addServletWithMapping("webservlet.AjaxServlet", "/ajx");
        handler.addServletWithMapping("webservlet.CentreAjaxServlet", "/centre/ajx");
        handler.addServletWithMapping("webservlet.EventAjaxServlet", "/event/ajx");
        server.setStopAtShutdown(true);
        server.setSendServerVersion(true);
        System.out.println("Configuration.STATIC_URL=" + Configuration.STATIC_URL);
        server.start();
        
        server.join();

    }
}
