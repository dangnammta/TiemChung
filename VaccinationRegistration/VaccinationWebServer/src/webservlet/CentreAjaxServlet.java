/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webservlet;

import baseclass.ServletBase;
import crdhn.vr.thrift.data.TCentreInfo;
import crdhn.vr.thrift.data.TVaccineInfo;
import crdhn.vr.thrift.response.TCMAddVaccineResponse;
import crdhn.vr.thrift.response.TCMCreateCentreResponse;
import crdhn.vr.thrift.response.TCMGetCentreInfoResponse;
import crdhn.vr.thrift.response.TCMGetCentresResponse;
import crdhn.vr.thrift.response.TCMGetVaccinesResponse;
import crdhn.vr.thrift.response.TError;
import crdhn.vr.thrift.response.TErrorCode;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.utils.HttpRequestUtils;
import service.utils.ServletUtil;
import service.utils.Utils;
import sns.backend.transport.VRCentreManagerServiceClient;

/**
 *
 * @author namdv
 */
public class CentreAjaxServlet extends ServletBase {

    private static final Logger _logger = LoggerFactory.getLogger(CentreAjaxServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JSONObject mapjson = new JSONObject();
        String method = req.getParameter("action");
        System.out.println("CentreAjaxServlet.doGet.action=" + method);
        String sessionKey = HttpRequestUtils.getCookie(req, "keylog");
        String userNameSession = Utils.checkSession(sessionKey, req);
        if (userNameSession != null && !userNameSession.isEmpty()) {
            switch (method) {
                case "getCentres":
                    getCentres(req, mapjson);
                    break;
                case "remove_centre":
                    removeCentre(req, mapjson);
                    break;
                case "getVaccines":
                    getVaccines(req, mapjson);
                    break;
                case "removeVaccine":
                    removeVaccine(req, mapjson);
                    break;
            }
        } else {
            mapjson.put("error_code", "-111");
            mapjson.put("error_message", "Phiên đăng nhập hết hạn");
        }
        try {
            String callback = "";
            if (req.getParameter("callback") != null) {
                callback = req.getParameter("callback");
            }
//            this.echo(mapjson.toJSONString()+"", resp);
            this.echo(callback + "" + mapjson.toJSONString() + "", resp);
        } catch (Exception ex) {
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JSONObject mapjson = new JSONObject();
        String method = req.getParameter("action");
        String sessionKey = HttpRequestUtils.getCookie(req, "keylog");
        String userNameSession = Utils.checkSession(sessionKey, req);
        if (userNameSession != null && !userNameSession.isEmpty()) {
            switch (method) {
                case "createCentre":
                    createCentre(req, resp, mapjson);
                    break;
                case "updateCentre":
                    updateCentre(req, resp, mapjson);
                    break;
                case "addVaccine":
                    addVaccine(req, mapjson);
                    break;
                case "updateVaccine":
                    updateVaccine(req, mapjson);
                    break;

            }
        } else {
            mapjson.put("error_code", "-111");
            mapjson.put("error_message", "Phiên đăng nhập hết hạn");
        }
        System.out.println("CentreAjaxServlet.doPost.action :" + method);

        JSONObject o = new JSONObject();
        o.putAll(mapjson);
        this.responseJson(o.toString(), resp);
    }

    private void addVaccine(HttpServletRequest req, JSONObject mapjson) {
        int centreId = ServletUtil.getIntParameter(req, "centreId");
        String vaccineName = ServletUtil.getStringParameter(req, "vaccineName");
        String vaccineDescription = ServletUtil.getStringParameter(req, "description");
        if (vaccineName.isEmpty() || centreId <= 0) {
            mapjson.put("error_code", -1);
            mapjson.put("error_message", "Tham số không hợp lệ!");
        } else {
            TVaccineInfo vcInfo = new TVaccineInfo();
            vcInfo.setName(vaccineName);
            vcInfo.setDescription(vaccineDescription);
            TCMAddVaccineResponse result = VRCentreManagerServiceClient.getInstance().addVaccine(centreId, vcInfo);
            if (result != null && result.error.errorCode == 0) {
                mapjson.put("error_code", 0);
                mapjson.put("vaccineId", result.vaccineId);
            } else {
                mapjson.put("error_code", -10);
                mapjson.put("error_message", "Đường truyền Internet gặp sự cố, vui lòng thử lại sau");
            }
        }
    }

    private void updateVaccine(HttpServletRequest req, JSONObject mapjson) {
        int centreId = ServletUtil.getIntParameter(req, "centreId");
        int vaccineId = ServletUtil.getIntParameter(req, "vaccineId");
        String vaccineName = ServletUtil.getStringParameter(req, "vaccineName");
        String vaccineDescription = ServletUtil.getStringParameter(req, "description");
        if (vaccineName.isEmpty() || centreId <= 0 || vaccineId <= 0) {
            mapjson.put("error_code", -1);
            mapjson.put("error_message", "Tham số không hợp lệ!");
        } else {
            TVaccineInfo vcInfo = new TVaccineInfo(vaccineId, vaccineName, vaccineDescription);
            TError result = VRCentreManagerServiceClient.getInstance().updateVaccineInfo(centreId, vcInfo);
            if (result != null && result.errorCode == 0) {
                mapjson.put("error_code", 0);
            } else {
                mapjson.put("error_code", -10);
                mapjson.put("error_message", "Đường truyền Internet gặp sự cố, vui lòng thử lại sau");
            }
        }

    }

    private void removeVaccine(HttpServletRequest req, JSONObject mapjson) {
        int centreId = ServletUtil.getIntParameter(req, "centreId");
        int vaccineId = ServletUtil.getIntParameter(req, "vaccineId");

        TError result = VRCentreManagerServiceClient.getInstance().removeVaccine(centreId, vaccineId);
        if (result != null && result.errorCode == 0) {
            mapjson.put("error_code", 0);
        } else {
            mapjson.put("error_code", -10);
            mapjson.put("error_message", "Đường truyền Internet gặp sự cố, vui lòng thử lại sau");
        }
    }

    private void getVaccines(HttpServletRequest req, JSONObject mapjson) {
        int centreId = ServletUtil.getIntParameter(req, "centreId");
        TCMGetVaccinesResponse result = VRCentreManagerServiceClient.getInstance().getVaccines(centreId);
        if (result != null && result.error.errorCode == 0) {
            mapjson.put("error_code", 0);
            JSONArray arr = new JSONArray();
            if (!result.vaccines.isEmpty()) {
                for (int i = 0; i < result.vaccines.size(); i++) {
                    TVaccineInfo info = result.vaccines.get(i);
                    if (info != null) {
                        JSONObject obj = new JSONObject();
                        obj.put("vaccineId", info.vaccineId);
                        obj.put("name", info.name);
                        obj.put("description", info.description);

                        arr.add(obj);
                    }
                }
            }
            mapjson.put("items", arr);
        } else {
            mapjson.put("error_code", -10);
            if (result == null) {
                mapjson.put("error_message", "Đường truyền Internet gặp sự cố, vui lòng thử lại sau");
            } else {
                mapjson.put("error_message", "Đường truyền Internet gặp sự cố, vui lòng thử lại sau " + result.error.errorCode);
            }

        }
    }

    private void removeCentre(HttpServletRequest req, JSONObject mapjson) {
        int centreID = ServletUtil.getIntParameter(req, "id");
        if (centreID <= 0) {
            mapjson.put("error_code", -1);
            mapjson.put("error_message", "Tham số không hợp lệ!");
        } else {
            TError result = VRCentreManagerServiceClient.getInstance().removeCentre(centreID);
            if (result != null && result.errorCode == TErrorCode.EC_OK.getValue()) {
                mapjson.put("error_code", 0);
                mapjson.put("error_message", "Xóa trung tâm thành công!");
            } else {
                mapjson.put("error_code", -10);
                mapjson.put("error_message", "Có lỗi xảy ra, vui lòng thử lại sau!");
            }

        }
    }

    private void updateCentre(HttpServletRequest req, HttpServletResponse resp, JSONObject mapjson) {
        String name_centre = ServletUtil.getStringParameter(req, "name_centre");
        String address_centre = ServletUtil.getStringParameter(req, "address_centre");
        String phone_centre = ServletUtil.getStringParameter(req, "phone_centre");
        int centreId = ServletUtil.getIntParameter(req, "id");
        int error_code = 0;
        if (address_centre == null || name_centre == null || phone_centre == null
                || address_centre.isEmpty() || name_centre.isEmpty()
                || centreId <= 0 || phone_centre.isEmpty()) {
            error_code = -1;
            mapjson.put("error_message", "Tham số không hợp lệ!");
        } else {
            TCMGetCentreInfoResponse responeInfo = VRCentreManagerServiceClient.getInstance().getCentreInfo(centreId);
            if (responeInfo.error.errorCode == TErrorCode.EC_OK.getValue()) {
                TCentreInfo cInfo = responeInfo.getCentreInfo();
                cInfo.setAddress(address_centre);
                cInfo.setCentreId(centreId);
                cInfo.setName(name_centre);
                cInfo.setPhoneNumber(phone_centre);
                TError result = VRCentreManagerServiceClient.getInstance().updateCentreInfo(centreId, cInfo);
                if (result == null) {
                    error_code = -10;
                } else {
                    error_code = result.errorCode;
                }
            }
            mapjson.put("error_message", "Đường truyền Internet gặp sự cố, vui lòng thử lại sau");
        }
        mapjson.put("error_code", error_code);
    }

    private void getCentres(HttpServletRequest req, JSONObject mapjson) {
        TCMGetCentresResponse result = VRCentreManagerServiceClient.getInstance().getCentres();
        if (result != null && result.error.errorCode == 0) {
            mapjson.put("error_code", 0);
            JSONArray arr = new JSONArray();
            if (!result.centres.isEmpty()) {
                for (int i = 0; i < result.centres.size(); i++) {
                    TCentreInfo info = result.centres.get(i);
                    if (info != null) {
                        JSONObject obj = new JSONObject();
                        obj.put("centreId", info.centreId);
                        obj.put("name_centre", info.name);
                        obj.put("phone_centre", info.phoneNumber);
                        obj.put("address_centre", info.address);

                        arr.add(obj);
                    }
                }
            }
            mapjson.put("items", arr);
        } else {
            mapjson.put("error_code", -10);
            if (result == null) {
                mapjson.put("error_message", "Đường truyền Internet gặp sự cố, vui lòng thử lại sau");
            } else {
                mapjson.put("error_message", "Đường truyền Internet gặp sự cố, vui lòng thử lại sau " + result.error.errorCode);
            }

        }
    }

    private void createCentre(HttpServletRequest req, HttpServletResponse resp, JSONObject mapjson) {
        String name_centre = ServletUtil.getStringParameter(req, "name_centre");
        String address_centre = ServletUtil.getStringParameter(req, "address_centre");
        String phone_centre = ServletUtil.getStringParameter(req, "phone_centre");
        String domain_centre = ServletUtil.getStringParameter(req, "domain_centre");
        if (name_centre == null || name_centre.isEmpty()
                || address_centre == null || address_centre.isEmpty()
                || phone_centre == null || phone_centre.isEmpty()) {
            mapjson.put("error_code", -1);
            mapjson.put("error_message", "Tham số không hợp lệ!");
        } else {
            TCentreInfo cInfo = new TCentreInfo();
            cInfo.setAddress(address_centre);
            cInfo.setName(name_centre);
            cInfo.setPhoneNumber(phone_centre);
            cInfo.setEventIds(new ArrayList<Integer>());
            cInfo.setVaccineIds(new ArrayList<Integer>());
//            cInfo.setDomain(domain_centre);
            TCMCreateCentreResponse result = VRCentreManagerServiceClient.getInstance().createCentre(cInfo);
            if (result != null && result.error.errorCode == 0) {
                mapjson.put("error_code", 0);
                mapjson.put("centreId", result.centreId);

            } else {
                mapjson.put("error_code", -10);
                if (result != null) {
                    mapjson.put("error_code", result.error.errorCode);
                }
                mapjson.put("error_message", "Đường truyền Internet gặp sự cố, vui lòng thử lại sau ");
            }

        }

    }

}
