/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webservlet;

import baseclass.ServletBase;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author namdv
 */
public class APIServlet extends ServletBase {

    private static final Logger _logger = LoggerFactory.getLogger(APIServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JSONObject mapjson = new JSONObject();
        //do smt
//        writeLogAction(req, resp);

        try {
            String callback = "";
            if (req.getParameter(
                    "callback") != null) {
                callback = req.getParameter("callback");
            }
            this.echo(callback + "(" + mapjson.toJSONString() + ")", resp);
        } catch (Exception ex) {
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, Object> mapjson = new HashMap<String, Object>();

        String method = req.getParameter("action");
        mapjson.put("data", null);
        System.out.println("action :" + method);

        JSONObject o = new JSONObject();
        o.putAll(mapjson);
        this.responseJson(o.toString(), resp);
    }

}
