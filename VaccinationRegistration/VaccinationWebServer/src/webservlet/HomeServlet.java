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
            if ("centre".equals(view) || "register".equals(view) || "result".equals(view)
                    || "admin".equals(view) || "login".equals(view) || "home".equals(view) || "result".equals(view)) {
                if (lengthPath >= 3) {
                    String subpath2 = arrUri[2];
                    switch (view) {
                        case "admin":
                            switch (subpath2) {
                                case "monitor":
                                    req.setAttribute("_type", "monitor_admin");
                                    break;
                                case "export":
                                    req.setAttribute("_type", "monitor_export");
                                    break;
                                case "register":
                                    req.setAttribute("_type", "admin_register");
                                    break;
                                case "create":
                                    req.setAttribute("_type", "create_event");
                                    break;
                                case "events":
                                    req.setAttribute("_type", "admin_list_event");
                                    break;
                            }
                            break;
                        case "event":
                            try {
                                int eventId = Integer.valueOf(subpath2);
                                if (eventId > 0) {
                                    req.setAttribute("_type", "event");
                                    req.setAttribute("eventId", eventId);
                                }
                            } catch (NumberFormatException e) {
                            }
                            break;
                        case "events":
                            req.setAttribute("_type", "list_event_public");
                            break;
                        case "result":
                            try {
                                int registrationID = Integer.valueOf(arrUri[3]);
                                if (registrationID > 0) {
                                    req.setAttribute("_type", view);
                                    req.setAttribute("id", registrationID);
                                    req.setAttribute("code", subpath2);
                                }
                            } catch (NumberFormatException e) {
                            }
                            break;
                        case "centre":
                            try {
                                int centreId = Integer.valueOf(subpath2);
                                if (centreId > 0) {
                                    req.setAttribute("_type", "admin_list_event");
                                    req.setAttribute("id", centreId);
                                }

                                if (lengthPath >= 4) {
                                    String subpath3 = arrUri[3];
                                    if ("create_event".equals(subpath3)) {
                                        req.setAttribute("_type", "create_event");
                                    } else if ("vaccine".equals(subpath3)) {
                                        req.setAttribute("_type", "manage_vaccine");
                                    }
                                    if (lengthPath >= 5) {
                                        if ("event".equals(subpath3)) {
                                            String subpath4 = arrUri[4];
                                            int eventId = Integer.parseInt(subpath4);
                                            if (eventId > 0) {
                                                req.setAttribute("_type", "update_event");
                                                req.setAttribute("eId", eventId);
                                            }
                                        }
                                    }

                                }

                            } catch (NumberFormatException e) {
                                req.setAttribute("_type", view);
                            }
                            break;
                    }
                } else {
                    req.setAttribute("_type", view);
                }

            }
        }
    }
}
