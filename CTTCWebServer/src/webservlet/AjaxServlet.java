/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webservlet;

import baseclass.ServletBase;
import configuration.Configuration;
import crdhn.vr.thrift.data.TInjectionStatus;
import crdhn.vr.thrift.data.TRegistrationInfo;
import crdhn.vr.thrift.data.TRegistrationType;
import crdhn.vr.thrift.data.TVaccinationEventInfo;
import crdhn.vr.thrift.response.TError;
import crdhn.vr.thrift.response.TErrorCode;
import crdhn.vr.thrift.response.TRMAddExtraRegistrationResponse;
import crdhn.vr.thrift.response.TRMAddRegistrationResponse;
import crdhn.vr.thrift.response.TVMCreateEventResponse;
import crdhn.vr.thrift.response.TVMGetRegistrationsResponse;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Random;
import java.util.logging.Level;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import render.RenderMain;
import service.utils.HttpRequestUtils;
import service.utils.ServletUtil;
import service.utils.Utils;
import sns.backend.transport.VRRegistrationManagerServiceClient;
import sns.backend.transport.VRVaccinationManagerServiceClient;

/**
 *
 * @author namdv
 */
public class AjaxServlet extends ServletBase {

    private static final Logger _logger = LoggerFactory.getLogger(AjaxServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JSONObject mapjson = new JSONObject();
        String method = req.getParameter("action");
        long startTime = System.currentTimeMillis();
        switch (method) {
            case "getListRegistrationPepole":
                getListRegistrationPepole(req, mapjson);
                break;
            case "logout":
                logout(req, resp, mapjson);
                break;
            case "updateStatusPepole":
                updateStatusPepole(req, mapjson);
                break;
            case "getEventStatus":
                getEventStatus(req, mapjson);
                break;
        }

        if (Configuration.isDebug == 1) {
            long endTime = System.currentTimeMillis();
            System.out.println("AjaxServlet.doGet.action :" + method + " timeer=" + (endTime - startTime));
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
        long startTime = System.currentTimeMillis();
        switch (method) {
            case "getcaptcha":
                mapjson = generatorCapcha(req, resp);
                break;
            case "addRegistration":
                addRegistration(req, resp, mapjson);
                break;
            case "addRegistrationAdmin":
                addRegistrationByAdmin(req, resp, mapjson);
                break;
            case "createEvent":
                createEvent(req, resp, mapjson);
                break;
            case "updateEvent":
                updateEventInfo(req, resp, mapjson);
                break;
            case "removeEvent":
                removeEvent(req, resp, mapjson);
                break;
            case "login":
                login(req, resp, mapjson);
                break;
        }
        if (Configuration.isDebug == 1) {
            long endTime = System.currentTimeMillis();
            System.out.println("AjaxServlet.doPost.action :" + method + " timeer=" + (endTime - startTime));

        }

        JSONObject o = new JSONObject();
        o.putAll(mapjson);
        this.responseJson(o.toString(), resp);
    }

    private void getEventStatus(HttpServletRequest req, JSONObject mapjson) {
        TError result = VRRegistrationManagerServiceClient.getInstance().getEventStatus(Configuration.evenId);
        if (result != null) {
            mapjson.put("error_code", result.errorCode);
        } else {
            mapjson.put("error_code", "-10");
        }
        String sessionKey = HttpRequestUtils.getCookie(req, Configuration.cookieName);
        String userNameSession = Utils.checkSession(sessionKey, req);
        if (userNameSession != null && !userNameSession.isEmpty()) {
            mapjson.put("isAdmin", true);
        } else {
            mapjson.put("isAdmin", false);
        }
    }

    private void logout(HttpServletRequest req, HttpServletResponse resp, JSONObject mapjson) {
        try {
            ServletBase.deleteCookieValue(Configuration.cookieName, req, resp);
            String token = HttpRequestUtils.getCookie(req, "keylog");
            System.out.println("logout token ==" + token);
            if (Configuration.mapTimeSession.containsKey(token)) {
                Configuration.mapTimeSession.remove(token);
            }
        } catch (Exception e) {
            System.out.println("AjaxServlet.logout===>Exception=" + e.getMessage());
        }

        mapjson.put("error_code", 0);
    }

    private void login(HttpServletRequest req, HttpServletResponse resp, JSONObject mapjson) {
        int error_code = -1;
        String error_message = "";
        try {
            String userName = ServletUtil.getParameter(req, "uname");
            String password = ServletUtil.getParameter(req, "pass");
            System.out.println("AjaxServlet.login username===" + userName + "\t pass=" + password);
            if (!userName.isEmpty() && !password.isEmpty()) {
                String sessionMD5 = Utils.md5(userName + "." + password + ".");
                String userCache = Configuration.mapSession.get(sessionMD5);
                System.out.println("user login userCache===" + userCache);
                if (userCache != null && userCache.trim().length() > 0) {
                    ServletBase.setCookieValue(Configuration.cookieName, sessionMD5, req, resp);
                    Configuration.mapTimeSession.put(sessionMD5, System.currentTimeMillis());
                    error_code = 0;
                    error_message = "Đăng nhập thành công";
                    mapjson.put("token", sessionMD5);
                    System.out.println("login success sessionMD5===" + sessionMD5);
//                    resp.sendRedirect("/monitor?t=" + sessionMD5);
                } else {
                    error_code = -2;
                    error_message = "Tài khoản không hợp lệ";
                }
            } else {
                error_message = "Tài khoản không hợp lệ";
            }
        } catch (Exception e) {
            System.out.println("AjaxServlet.login===>Exception=" + e.getMessage());
            error_message = "Đường truyền Internet gặp sự cố, vui lòng thử lại sau";
        }
        mapjson.put("error_code", error_code);
        mapjson.put("error_message", error_message);
    }

    private void createEvent(HttpServletRequest req, HttpServletResponse resp, JSONObject mapjson) {
        String startTimeRegister = ServletUtil.getStringParameter(req, "startTimeRegister");
        String startTimeInjection = ServletUtil.getStringParameter(req, "startTimeInjection");
        int val_totalOfDoses = ServletUtil.getIntParameter(req, "val_totalOfDoses");
        int val_totalOfDay = ServletUtil.getIntParameter(req, "val_totalOfDay");
        String val_vaccine = ServletUtil.getStringParameter(req, "val_vaccine");
        if (startTimeInjection == null || startTimeRegister == null
                || startTimeInjection.isEmpty() || startTimeRegister.isEmpty()
                || val_totalOfDay <= 0 || val_totalOfDoses <= 0
                || val_vaccine == null || val_vaccine.isEmpty()) {
            mapjson.put("error_code", -1);
            mapjson.put("error_message", "Tham số không hợp lệ!");
        } else {
            TVaccinationEventInfo eventInfo = new TVaccinationEventInfo();
            Calendar cal_register = Calendar.getInstance();
            String[] time_register = startTimeRegister.split("/");
            if (time_register != null && time_register.length == 3) {
                int day = Integer.valueOf(time_register[0]);
                int month = Integer.valueOf(time_register[1]);
                int year = Integer.valueOf(time_register[2]);
                cal_register.set(year, (month - 1), day);
                eventInfo.setStartTimeRegistration(cal_register.getTimeInMillis() / 1000);
            } else {
                mapjson.put("error_code", -2);
                mapjson.put("error_message", "Thời gian bắt đầu đăng ký không đúng!");
            }

            String[] time_vaccine = startTimeInjection.split("/");
            if (time_vaccine != null && time_vaccine.length == 3) {
                int day = Integer.valueOf(time_vaccine[0]);
                int month = Integer.valueOf(time_vaccine[1]);
                int year = Integer.valueOf(time_vaccine[2]);
                cal_register.set(year, (month - 1), day);
                eventInfo.setStartTimeInjection(cal_register.getTimeInMillis() / 1000);
            } else {
                mapjson.put("error_code", -2);
                mapjson.put("error_message", "Thời gian bắt đầu tiêm chủng không đúng!");
            }

            eventInfo.setNumberOfInjectionsPerDay(val_totalOfDay);
            eventInfo.setTotalOfDoses(val_totalOfDoses);

            TVMCreateEventResponse result = VRVaccinationManagerServiceClient.getInstance().createEvent(eventInfo);
            if (result != null && result.error.errorCode == 0) {
                mapjson.put("error_code", 0);
                mapjson.put("eventId", result.eventId);
            } else {
                if (result == null) {
                    mapjson.put("error_code", -10);
                } else {
                    mapjson.put("error_code", result.error.errorCode);

                }
                mapjson.put("error_message", "Đường truyền Internet gặp sự cố, vui lòng thử lại sau");
            }
        }
    }

    private void updateEventInfo(HttpServletRequest req, HttpServletResponse resp, JSONObject mapjson) {
        String startTimeRegister = ServletUtil.getStringParameter(req, "startTimeRegister");
        String startTimeInjection = ServletUtil.getStringParameter(req, "startTimeInjection");
        int val_totalOfDoses = ServletUtil.getIntParameter(req, "val_totalOfDoses");
        int val_totalOfDay = ServletUtil.getIntParameter(req, "val_totalOfDay");
        String val_vaccine = ServletUtil.getStringParameter(req, "val_vaccine");
        int eventID = ServletUtil.getIntParameter(req, "eventId");
        if (startTimeInjection == null || startTimeRegister == null
                || startTimeInjection.isEmpty() || startTimeRegister.isEmpty()
                || val_totalOfDay <= 0 || val_totalOfDoses <= 0
                || val_vaccine == null || val_vaccine.isEmpty()) {
            mapjson.put("error_code", -1);
            mapjson.put("error_message", "Tham số không hợp lệ!");
        } else {
            TVaccinationEventInfo eventInfo = new TVaccinationEventInfo();
            Calendar cal_register = Calendar.getInstance();
            String[] time_register = startTimeRegister.split("/");
            if (time_register != null && time_register.length == 3) {
                int day = Integer.valueOf(time_register[0]);
                int month = Integer.valueOf(time_register[1]);
                int year = Integer.valueOf(time_register[2]);
                cal_register.set(year, (month - 1), day);
                eventInfo.setStartTimeRegistration(cal_register.getTimeInMillis() / 1000);
            } else {
                mapjson.put("error_code", -2);
                mapjson.put("error_message", "Thời gian bắt đầu đăng ký không đúng!");
            }

            String[] time_vaccine = startTimeInjection.split("/");
            if (time_vaccine != null && time_vaccine.length == 3) {
                int day = Integer.valueOf(time_vaccine[0]);
                int month = Integer.valueOf(time_vaccine[1]);
                int year = Integer.valueOf(time_vaccine[2]);
                cal_register.set(year, (month - 1), day);
                eventInfo.setStartTimeInjection(cal_register.getTimeInMillis() / 1000);
            } else {
                mapjson.put("error_code", -2);
                mapjson.put("error_message", "Thời gian bắt đầu tiêm chủng không đúng!");
            }

            eventInfo.setNumberOfInjectionsPerDay(val_totalOfDay);
            eventInfo.setTotalOfDoses(val_totalOfDoses);

            TError result = VRVaccinationManagerServiceClient.getInstance().updateEventInfo(eventID, eventInfo);
            if (result != null && result.errorCode == 0) {
                mapjson.put("error_code", 0);
            } else {
                if (result == null) {
                    mapjson.put("error_code", -10);
                } else {
                    mapjson.put("error_code", result.errorCode);

                }
                mapjson.put("error_message", "Đường truyền Internet gặp sự cố, vui lòng thử lại sau");
            }
        }
    }

    private void removeEvent(HttpServletRequest req, HttpServletResponse resp, JSONObject mapjson) {
        int eventID = ServletUtil.getIntParameter(req, "eventId");
        TError result = VRVaccinationManagerServiceClient.getInstance().removeEvent(eventID);
        if (result != null && result.errorCode == 0) {
            mapjson.put("error_code", 0);
        } else {
            mapjson.put("error_code", -10);
            mapjson.put("error_message", "Đường truyền Internet gặp sự cố, vui lòng thử lại sau");
        }
    }

    private void updateStatusPepole(HttpServletRequest req, JSONObject mapjson) {
        int registrationID = ServletUtil.getIntParameter(req, "id");
        int statusInjection = ServletUtil.getIntParameter(req, "status");
        String sessionKey = HttpRequestUtils.getCookie(req, "keylog");
        String userNameSession = Utils.checkSession(sessionKey, req);
        if (userNameSession != null && !userNameSession.isEmpty()) {

            TError result = VRVaccinationManagerServiceClient.getInstance().updateInjectionStatus(registrationID, statusInjection);
            if (result != null && result.errorCode == TErrorCode.EC_OK.getValue()) {
                mapjson.put("error_code", 0);
                mapjson.put("error_message", "Cập nhật thành công");
            } else {
                mapjson.put("error_code", -10);
                mapjson.put("error_message", "Đường truyền Internet gặp sự cố, vui lòng thử lại sau");
            }
        } else {
            mapjson.put("error_code", "-111");
            mapjson.put("error_message", "Phiên đăng nhập hết hạn");
        }

    }

    private void getListRegistrationPepole(HttpServletRequest req, JSONObject mapjson) {
        int eventID = Configuration.evenId;//ServletUtil.getIntParameter(req, "eventId");
        TVMGetRegistrationsResponse result = VRVaccinationManagerServiceClient.getInstance().getRegistrations(eventID);
        if (result != null && result.error.errorCode == 0) {
            mapjson.put("error_code", 0);
            JSONArray arr = new JSONArray();
            if (!result.registrations.isEmpty()) {
                for (int i = 0; i < result.registrations.size(); i++) {
                    TRegistrationInfo info = result.registrations.get(i);
                    if (info != null) {
                        JSONObject obj = new JSONObject();
                        obj.put("email", info.childrenCarrierEmail);
                        obj.put("parent_name", info.childrenCarrierName);
                        obj.put("parent_pin", info.childrenCarrierPIN);
                        obj.put("phone", info.childrenCarrierPhoneNumber);
                        obj.put("birthday", Utils.convertTimeSecondToDayString(info.childrenDateOfBirth));
                        obj.put("childrenName", info.childrenName);
                        obj.put("eventId", info.eventId);
                        obj.put("injectionOrderNumber", info.injectionOrderNumber);
                        obj.put("injectionStatus", info.injectionStatus);
                        obj.put("injectionTime", info.injectionTime);
                        obj.put("registrationId", info.registrationId);
                        obj.put("registrationTime", Utils.convertTimeSecondToDayString(info.registrationTime));
                        obj.put("registrationType", TRegistrationType.findByValue(info.registrationType).name());
                        obj.put("registrationCode", info.registrationCode);
                        obj.put("address", info.childrenCarrierAddress);
                        obj.put("injectionDate", info.injectionDate);

                        arr.add(obj);
//                        {
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

    private void addRegistrationByAdmin(HttpServletRequest req, HttpServletResponse resp, JSONObject mapjson) {
        String nameChild = ServletUtil.getStringParameter(req, "namechild");
        String birthdayChild = ServletUtil.getStringParameter(req, "birthdaychild");
        String nameParent = ServletUtil.getStringParameter(req, "name_parent");
        String cmtndParent = ServletUtil.getStringParameter(req, "cmtnd_parent");
        String phoneParent = ServletUtil.getStringParameter(req, "phone_parent");
//        String email = ServletUtil.getStringParameter(req, "email");
        int typeVaccine = ServletUtil.getIntParameter(req, "typevaccien");
//        String path_image = ServletUtil.getStringParameter(req, "path_image");
        int type_register = ServletUtil.getIntParameter(req, "type_register");
        String dayOfInjection = ServletUtil.getStringParameter(req, "dayOfInjection");
//        try {
//            if (path_image != null) {
//                String[] subpath = path_image.split("/");
//                if (subpath != null && subpath[subpath.length - 1].contains(".png")) {
//                    File file = new File(configuration.Configuration.captcha_url + "/captcha/" + subpath[subpath.length - 1]);
//                    if (file.delete()) {
//                        System.out.println(file.getName() + " is deleted!");
//                    } else {
//                        System.out.println("Deleteation is failed.");
//                    }
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        if (nameChild == null || nameChild.isEmpty()
                || birthdayChild == null || birthdayChild.isEmpty()
                || nameParent == null || nameParent.isEmpty()
                || cmtndParent == null || cmtndParent.isEmpty()
                || phoneParent == null || phoneParent.isEmpty()
                //                || email == null || email.isEmpty()
                || type_register < 0 || type_register > 1) {
            mapjson.put("error_code", -1);
            mapjson.put("error_message", "Tham số không hợp lệ!");
        } else {

            Calendar cal = Calendar.getInstance();
            String[] date_birthday = birthdayChild.split("/");
            if (date_birthday != null && date_birthday.length == 3) {
                int day = Integer.valueOf(date_birthday[0]);
                int month = Integer.valueOf(date_birthday[1]);
                int year = Integer.valueOf(date_birthday[2]);
                cal.set(year, (month - 1), day);
            }
            TRegistrationInfo registerInfo = new TRegistrationInfo();
//            registerInfo.setChildrenCarrierEmail(email);
            registerInfo.setChildrenCarrierName(nameParent);
            registerInfo.setChildrenCarrierPIN(cmtndParent);
            registerInfo.setChildrenCarrierPhoneNumber(phoneParent);
            registerInfo.setChildrenDateOfBirth(cal.getTimeInMillis() / 1000);
            registerInfo.setChildrenName(nameChild);
            registerInfo.setEventId(1);
            registerInfo.setRegistrationType(TRegistrationType.TRT_MANUAL.getValue());
            registerInfo.setRegistrationTime(System.currentTimeMillis() / 1000);
            registerInfo.setInjectionTime(dayOfInjection);
            TRMAddExtraRegistrationResponse addResp = VRRegistrationManagerServiceClient.getInstance().addExtraRegistration(registerInfo);

            if (addResp != null && addResp.error.errorCode == 0) {
                mapjson.put("error_code", 0);
                mapjson.put("nameChild", nameChild);
                mapjson.put("birthdayChild", birthdayChild);
                mapjson.put("cmtndParent", cmtndParent);
                mapjson.put("phoneParent", phoneParent);
//                mapjson.put("email", email);
                mapjson.put("typeVaccine", typeVaccine);
                mapjson.put("nameParent", nameParent);
                mapjson.put("injectionOrderNumber", addResp.injectionOrderNumber);
                mapjson.put("injectionTime", addResp.injectionTime);
                mapjson.put("registrationId", addResp.registrationId);
                mapjson.put("registrationCode", addResp.registrationCode);
            } else {
                if (addResp == null) {
                    mapjson.put("error_code", "-10");
                    mapjson.put("error_message", "Đường truyền Internet gặp sự cố, vui lòng thử lại sau");
                } else {
                    mapjson.put("error_code", addResp.error.errorCode);
                    if (addResp.error.errorCode == TErrorCode.EC_PIN_REGISTERED.getValue()) {
                        mapjson.put("error_message", "Số CMND/Hộ chiếu này đã đăng ký thành công trong ngày.\n(Một CMND/Hộ chiếu không được phép đăng ký 2 lần trong 1 ngày)");
                    } else if (addResp.error.errorCode == TErrorCode.EC_REGISTRATION_FULL.getValue()) {
                        mapjson.put("error_message", "Số lượng Vắcxin của đợt tiêm chủng này đã hết, hẹn quý khách đợt tiêm tiếp theo.");
                    } else {
                        mapjson.put("error_message", "Đường truyền Internet gặp sự cố, vui lòng thử lại sau " + addResp.error.errorCode);
                    }

                }

            }
        }

    }

    private void addRegistration(HttpServletRequest req, HttpServletResponse resp, JSONObject mapjson) {
        String nameChild = ServletUtil.getStringParameter(req, "namechild");
        String birthdayChild = ServletUtil.getStringParameter(req, "birthdaychild");
        String nameParent = ServletUtil.getStringParameter(req, "name_parent");
        String cmtndParent = ServletUtil.getStringParameter(req, "cmtnd_parent");
        String phoneParent = ServletUtil.getStringParameter(req, "phone_parent");
//        String email = ServletUtil.getStringParameter(req, "email");
        int typeVaccine = ServletUtil.getIntParameter(req, "typevaccien");
        int type_register = ServletUtil.getIntParameter(req, "type_register");
        String address = ServletUtil.getStringParameter(req, "address");
        if (nameChild.isEmpty() || birthdayChild.isEmpty()
                || nameParent.isEmpty() || cmtndParent.isEmpty()
                || phoneParent.isEmpty()
                || address.isEmpty() || type_register < 0 || type_register > 1) {
            mapjson.put("error_code", -1);
            mapjson.put("error_message", "Tham số không hợp lệ!");
        } else {

            Calendar cal = Calendar.getInstance();
            String[] date_birthday = birthdayChild.split("/");
            if (date_birthday != null && date_birthday.length == 3) {
                int day = Integer.valueOf(date_birthday[0]);
                int month = Integer.valueOf(date_birthday[1]);
                int year = Integer.valueOf(date_birthday[2]);
                cal.set(year, (month - 1), day);
            }
            TRegistrationInfo registerInfo = new TRegistrationInfo();
//            registerInfo.setChildrenCarrierEmail(email);
            registerInfo.setChildrenCarrierName(nameParent);
            registerInfo.setChildrenCarrierPIN(cmtndParent);
            registerInfo.setChildrenCarrierPhoneNumber(phoneParent);
            registerInfo.setChildrenDateOfBirth(cal.getTimeInMillis() / 1000);
            registerInfo.setChildrenName(nameChild);
            registerInfo.setEventId(Configuration.evenId);
            registerInfo.setRegistrationType(TRegistrationType.TRT_ONLINE.getValue());
            registerInfo.setRegistrationTime(System.currentTimeMillis() / 1000);
            registerInfo.setChildrenCarrierAddress(address);
            TRMAddRegistrationResponse addResp = VRRegistrationManagerServiceClient.getInstance().addRegistration(registerInfo);

            if (addResp != null && addResp.error.errorCode == 0) {
                mapjson.put("error_code", 0);
                mapjson.put("nameChild", nameChild);
                mapjson.put("birthdayChild", birthdayChild);
                mapjson.put("cmtndParent", cmtndParent);
                mapjson.put("phoneParent", phoneParent);
//                mapjson.put("email", email);
                mapjson.put("typeVaccine", typeVaccine);
                mapjson.put("nameParent", nameParent);
                mapjson.put("injectionOrderNumber", addResp.injectionOrderNumber);
                mapjson.put("injectionTime", addResp.injectionTime);
                mapjson.put("registrationId", addResp.registrationId);
                mapjson.put("registrationCode", addResp.registrationCode);

            } else {
                if (addResp == null) {
                    mapjson.put("error_code", "-10");
                    mapjson.put("error_message", "Đường truyền Internet gặp sự cố, vui lòng thử lại sau");
                } else {
                    mapjson.put("error_code", addResp.error.errorCode);
                    if (addResp.error.errorCode == TErrorCode.EC_PIN_REGISTERED.getValue()) {
                        mapjson.put("error_message", "Số CMND/Hộ chiếu này đã đăng ký thành công trong ngày.\n(Một CMND/Hộ chiếu không được phép đăng ký 2 lần trong 1 ngày)");
                    } else if (addResp.error.errorCode == TErrorCode.EC_REGISTRATION_FULL.getValue()) {
                        mapjson.put("error_message", "Số lượng Vắcxin của đợt tiêm chủng này đã hết, hẹn quý khách đợt tiêm tiếp theo.");
                    } else {
                        mapjson.put("error_message", "Đường truyền Internet gặp sự cố, vui lòng thử lại sau " + addResp.error.errorCode);
                    }

                }

            }
        }

    }

    public JSONObject generatorCapcha(HttpServletRequest req, HttpServletResponse resp) {
        Random r = new Random();
        int index = Math.abs(r.nextInt(149));
        return Configuration.mapCaptcha.get(index);
    }

}
