/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package httpservice;

import crdhn.fcore.common.LogUtil;
import java.io.File;
import org.apache.log4j.Logger;

/**
 *
 * @author namdv
 */
public class ServiceDaemon {

    private static Logger logger_ = Logger.getLogger(ServiceDaemon.class);

    public static void main(String[] args) throws Exception {
        LogUtil.init();
        WebServer webserver = new WebServer();
        String pidFile = System.getProperty("pidfile");
        try {
            if (pidFile != null) {
                new File(pidFile).deleteOnExit();
            }
            if (System.getProperty("foreground") == null) {
            
            }
            webserver.start();

        } catch (Throwable e) {
            String msg = "Exception encountered during startup.";
            logger_.error(msg, e);

            // try to warn user on stdout too, if we haven't already detached
            System.out.println(e.getMessage());
            logger_.error("Uncaught exception: " + e.getMessage());

            System.exit(3);
        }
    }
}
