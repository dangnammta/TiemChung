/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webservlet;

import baseclass.ServletBase;
import configuration.Configuration;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import render.RenderMain;

/**
 *
 * @author namdv
 */
public class HomeServlet extends ServletBase {

    private static final Logger _logger = LoggerFactory.getLogger(HomeServlet.class);
    private static int buffer_size_home = 102400;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private void doProcess(HttpServletRequest req, HttpServletResponse resp) {
        _logger.debug("do Process");
        try {
            resp.setBufferSize(buffer_size_home);
            _logger.debug("*****");
            process(req, resp);
        } catch (IOException ex) {
            _logger.error("HomeServlet Process ", ex);
        }
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String content = "";

        try {
            long startTime = System.currentTimeMillis();
            parseUrl(req);
            String page = "";
            try {
                page = req.getAttribute("_type").toString();
            } catch (Exception e) {
            }
            content = RenderMain.getInstance().render(page, req, resp);
            if (Configuration.isDebug == 1) {
                long endTime = System.currentTimeMillis();
                System.out.println("Render page " + page + " timeer=" + (endTime - startTime));
            }

        } catch (Exception ex) {
            _logger.warn("Exception while rendering", ex);
            return;
        }
        responseText(content, resp);

    }

    private void parseUrl(HttpServletRequest req) {
        String uri = req.getRequestURI();
        String[] arrUri = uri.split("/");
        int lengthPath = arrUri.length;
        System.out.println("parse uri =" + uri);
        if (lengthPath >= 2) {
            String view = arrUri[1];
            if ("register".equals(view) || "result".equals(view)
                    || "admin".equals(view) || "login".equals(view) || "home".equals(view) || "result".equals(view)) {
                if (lengthPath >= 3) {
                    String subpath2 = arrUri[2];
                    if ("admin".equals(view)) {

                        if ("monitor".equals(subpath2)) {
                            req.setAttribute("_type", "monitor_admin");
                        } else if ("register".equals(subpath2)) {
                            req.setAttribute("_type", "admin_register");
                        } else if ("create".equals(subpath2)) {
                            req.setAttribute("_type", "create_event");
                        }
//                    } else if ("event".equals(view)) {
//                        try {
//                            int eventId = Integer.valueOf(subpath2);
//                            if (eventId > 0) {
//                                req.setAttribute("_type", "event");
//                                req.setAttribute("eventId", eventId);
//                            }
//                        } catch (Exception e) {
//                        }
                    } else if ("result".equals(view)) {
                        try {
                            int registrationID = Integer.valueOf(arrUri[3]);
                            if (registrationID > 0) {
                                req.setAttribute("_type", view);
                                req.setAttribute("id", registrationID);
                                req.setAttribute("code", subpath2);
                            }
                        } catch (Exception e) {
                        }
                    }
                } else {
                    req.setAttribute("_type", view);
                }

            }
        }
    }
}
