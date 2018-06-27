/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webservlet;

import baseclass.ServletBase;
import configuration.Configuration;
import crdhn.vr.thrift.data.TEventBasicInfo;
import crdhn.vr.thrift.data.TEventInfo;
import crdhn.vr.thrift.data.TInjectionOrderInfo;
import crdhn.vr.thrift.data.TVaccineInfo;
import crdhn.vr.thrift.response.TCMAddVaccineResponse;
import crdhn.vr.thrift.response.TCMGetVaccinesResponse;
import crdhn.vr.thrift.response.TError;
import crdhn.vr.thrift.response.TErrorCode;
import crdhn.vr.thrift.response.TVMCreateEventResponse;
import crdhn.vr.thrift.response.TVMGetEventInfoResponse;
import crdhn.vr.thrift.response.TVMGetEventsResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
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
import sns.backend.transport.VRRegistrationManagerServiceClient;
import sns.backend.transport.VREventManagerServiceClient;

/**
 *
 * @author namdv
 */
public class EventAjaxServlet extends ServletBase {

    private static final Logger _logger = LoggerFactory.getLogger(EventAjaxServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JSONObject mapjson = new JSONObject();
        String method = req.getParameter("action");
        long startTime = System.currentTimeMillis();
//        System.out.println("AjaxServlet.doGet.action="+method);
        switch (method) {
            case "getEvents":
                getEvents(req, mapjson);
                break;
            case "getEventStatus":
                getEventStatus(req, mapjson);
                break;
            case "getEventInfo":
                getEventInfo(req, mapjson);
                break;
        }

        if (Configuration.isDebug == 1) {
            long endTime = System.currentTimeMillis();
            System.out.println("EventAjaxServlet.doGet.action :" + method + " timeer=" + (endTime - startTime));
        }
        try {
            String callback = "";
            if (req.getParameter("callback") != null) {
                callback = req.getParameter("callback");
            }
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
            case "createEvent":
                createEvent(req, resp, mapjson);
                break;
            case "updateEvent":
                updateEventInfo(req, resp, mapjson);
                break;
            case "removeEvent":
                removeEvent(req, resp, mapjson);
                break;
            case "addVaccine":
                addVaccine(req, resp, mapjson);
                break;
        }
        if (Configuration.isDebug == 1) {
            long endTime = System.currentTimeMillis();
            System.out.println("EventAjaxServlet.doPost.action :" + method + " timeer=" + (endTime - startTime));

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
        String sessionKey = HttpRequestUtils.getCookie(req, "keylog");
        String userNameSession = Utils.checkSession(sessionKey, req);
        if (userNameSession != null && !userNameSession.isEmpty()) {
            mapjson.put("isAdmin", true);
        } else {
            mapjson.put("isAdmin", false);
        }
    }

    private void createEvent(HttpServletRequest req, HttpServletResponse resp, JSONObject mapjson) {
        int centreId = ServletUtil.getIntParameter(req, "centreId");
        String event_name = ServletUtil.getStringParameter(req, "event_name");
        String startTimeRegister = ServletUtil.getStringParameter(req, "startTimeRegister");
        String startTimeInjection = ServletUtil.getStringParameter(req, "startTimeInjection");
        int val_totalOfDoses = ServletUtil.getIntParameter(req, "val_totalOfDoses");
        int val_totalOfDay = ServletUtil.getIntParameter(req, "val_totalOfDay");
        String val_vaccine = ServletUtil.getStringParameter(req, "val_vaccine");
        String working_time = ServletUtil.getStringParameter(req, "working_times");
        String holidays = ServletUtil.getStringParameter(req, "holidays");
        String working_saturday = ServletUtil.getStringParameter(req, "working_saturday");
        String working_sunday = ServletUtil.getStringParameter(req, "working_sunday");
        String sessionKey = HttpRequestUtils.getCookie(req, "keylog");
        String userNameSession = Utils.checkSession(sessionKey, req);
        if (userNameSession != null && !userNameSession.isEmpty()) {
            if (event_name.isEmpty() || startTimeInjection == null || startTimeRegister == null
                    || startTimeInjection.isEmpty() || startTimeRegister.isEmpty()
                    || val_totalOfDay <= 0 || val_totalOfDoses <= 0
                    || val_vaccine == null || val_vaccine.isEmpty() || working_time.isEmpty()) {
                mapjson.put("error_code", -1);
                mapjson.put("error_message", "Tham số không hợp lệ!");
            } else {
                TEventInfo eventInfo = new TEventInfo();
                long startTimeRegistration = Utils.parserDateTimeToLong(startTimeRegister);
                if (startTimeRegistration > 0) {
                    eventInfo.setStartTimeRegistration(startTimeRegistration);
                } else {
                    mapjson.put("error_code", -2);
                    mapjson.put("error_message", "Thời gian bắt đầu đăng ký không đúng!");
                }
                long timeInjection = Utils.parserDateToLong(startTimeInjection);
                if (timeInjection > 0) {
                    eventInfo.setStartTimeInjection(timeInjection);
                } else {
                    mapjson.put("error_code", -2);
                    mapjson.put("error_message", "Thời gian bắt đầu tiêm chủng không đúng!");
                }
                try {
                    boolean workingOnSaturday = Boolean.valueOf(working_saturday);
                    boolean workingOnSunday = Boolean.parseBoolean(working_sunday);
                    eventInfo.setIsWorkingOnSaturday(workingOnSaturday);
                    eventInfo.setIsWorkingOnSunday(workingOnSunday);
                } catch (Exception e) {
                }

                HashSet<Long> vacationDays = new HashSet<Long>();
                if (holidays != null && !holidays.isEmpty()) {
                    String[] arrHoliday = holidays.trim().split(",");
                    for (String str : arrHoliday) {
                        long day = Utils.parserDateToLong(str);
                        if (day > 0) {
                            vacationDays.add(day);
                        }
                    }
                }
                eventInfo.setVacationDays(vacationDays);
                String[] shiftTime = working_time.split(",");
                eventInfo.setWorkingShiftNames(Arrays.asList(shiftTime));
                eventInfo.setEventName(event_name);
                eventInfo.setNumberOfInjectionsPerDay(val_totalOfDay);
                eventInfo.setTotalOfDoses(val_totalOfDoses);
                eventInfo.setRegistrations(new ArrayList<Long>());
                eventInfo.setExtendedRegistrations(new ArrayList<Long>());
                eventInfo.setInjectionOrderInfos(new HashMap<Integer, TInjectionOrderInfo>());
                eventInfo.setMapDate2InjectionOrderNumbers(new HashMap<Long, Set<Integer>>());
                eventInfo.setRegistrationCodes(new HashSet<String>());
                eventInfo.setCentreId(centreId);
                TVMCreateEventResponse result = VREventManagerServiceClient.getInstance().createEvent(eventInfo);
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
        } else {
            mapjson.put("error_code", "-111");
            mapjson.put("error_message", "Phiên đăng nhập hết hạn");
        }
    }

    private void updateEventInfo(HttpServletRequest req, HttpServletResponse resp, JSONObject mapjson) {
        int centreId = ServletUtil.getIntParameter(req, "centreId");
        int eventId = ServletUtil.getIntParameter(req, "eventId");
        String event_name = ServletUtil.getStringParameter(req, "event_name");
        String startTimeRegister = ServletUtil.getStringParameter(req, "startTimeRegister");
        String startTimeInjection = ServletUtil.getStringParameter(req, "startTimeInjection");
        int val_totalOfDoses = ServletUtil.getIntParameter(req, "val_totalOfDoses");
        int val_totalOfDay = ServletUtil.getIntParameter(req, "val_totalOfDay");
        String val_vaccine = ServletUtil.getStringParameter(req, "val_vaccine");
        String working_time = ServletUtil.getStringParameter(req, "working_times");
        String holidays = ServletUtil.getStringParameter(req, "holidays");
        String working_saturday = ServletUtil.getStringParameter(req, "working_saturday");
        String working_sunday = ServletUtil.getStringParameter(req, "working_sunday");
        String sessionKey = HttpRequestUtils.getCookie(req, "keylog");
        String userNameSession = Utils.checkSession(sessionKey, req);
        if (userNameSession != null && !userNameSession.isEmpty()) {
            if (event_name.isEmpty() || startTimeInjection == null || startTimeRegister == null
                    || startTimeInjection.isEmpty() || startTimeRegister.isEmpty()
                    || val_totalOfDay <= 0 || val_totalOfDoses <= 0
                    || val_vaccine == null || val_vaccine.isEmpty() || working_time.isEmpty()) {
                mapjson.put("error_code", -1);
                mapjson.put("error_message", "Tham số không hợp lệ!");
            } else {

                TVMGetEventInfoResponse event_respone = VREventManagerServiceClient.getInstance().getEventInfo(eventId);
                if (event_respone == null || event_respone.error.errorCode != TErrorCode.EC_OK.getValue()) {
                    if (event_respone == null) {
                        mapjson.put("error_code", -10);
                        mapjson.put("error_message", "không lấy được thông tin đợt tiêm chủng từ hệ thống!");
                    } else {
                        mapjson.put("error_code", event_respone.error.errorCode);
                        mapjson.put("error_message", "Tham số không hợp lệ!");
                    }
                    return;
                }
                TEventInfo eventInfo = event_respone.getEventInfo();
                long startTimeRegistration = Utils.parserDateTimeToLong(startTimeRegister);
                if (startTimeRegistration > 0) {
                    eventInfo.setStartTimeRegistration(startTimeRegistration);
                } else {
                    mapjson.put("error_code", -2);
                    mapjson.put("error_message", "Thời gian bắt đầu đăng ký không đúng!");
                }
                long timeInjection = Utils.parserDateToLong(startTimeInjection);
                if (timeInjection > 0) {
                    eventInfo.setStartTimeInjection(timeInjection);
                } else {
                    mapjson.put("error_code", -2);
                    mapjson.put("error_message", "Thời gian bắt đầu tiêm chủng không đúng!");
                }
                try {
                    boolean workingOnSaturday = Boolean.valueOf(working_saturday);
                    boolean workingOnSunday = Boolean.parseBoolean(working_sunday);
                    eventInfo.setIsWorkingOnSaturday(workingOnSaturday);
                    eventInfo.setIsWorkingOnSunday(workingOnSunday);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                HashSet<Long> vacationDays = new HashSet<Long>();
                if (holidays != null && !holidays.isEmpty()) {
                    String[] arrHoliday = holidays.trim().split(",");
                    for (String str : arrHoliday) {
                        long day = Utils.parserDateToLong(str);
                        if (day > 0) {
                            vacationDays.add(day);
                        }
                    }
                }
                eventInfo.setVacationDays(vacationDays);
                String[] shiftTime = working_time.split(",");
                eventInfo.setWorkingShiftNames(Arrays.asList(shiftTime));
                eventInfo.setEventName(event_name);
                eventInfo.setNumberOfInjectionsPerDay(val_totalOfDay);
                eventInfo.setTotalOfDoses(val_totalOfDoses);
//            eventInfo.setRegistrations(new ArrayList<Long>());
//            eventInfo.setExtendedRegistrations(new ArrayList<Long>());
//            eventInfo.setInjectionOrderInfos(new HashMap<Integer, TInjectionOrderInfo>());
//            eventInfo.setMapDate2InjectionOrderNumbers(new HashMap<Long, Set<Integer>>());
//            eventInfo.setRegistrationCodes(new HashSet<String>());
//            eventInfo.setCentreId(centreId);
                TError result = VREventManagerServiceClient.getInstance().updateEventInfo(eventId, eventInfo);
                if (result != null && result.errorCode == 0) {
                    mapjson.put("error_code", 0);
                    mapjson.put("eventId", eventId);
                } else {
                    if (result == null) {
                        mapjson.put("error_code", -10);
                    } else {
                        mapjson.put("error_code", result.errorCode);
                    }
                    mapjson.put("error_message", "Đường truyền Internet gặp sự cố, vui lòng thử lại sau");
                }
            }
        } else {
            mapjson.put("error_code", "-111");
            mapjson.put("error_message", "Phiên đăng nhập hết hạn");
        }
    }

    private void removeEvent(HttpServletRequest req, HttpServletResponse resp, JSONObject mapjson) {
        int eventID = ServletUtil.getIntParameter(req, "eventId");
        String sessionKey = HttpRequestUtils.getCookie(req, "keylog");
        String userNameSession = Utils.checkSession(sessionKey, req);
        if (userNameSession != null && !userNameSession.isEmpty()) {
            TError result = VREventManagerServiceClient.getInstance().removeEvent(eventID);
            if (result != null && result.errorCode == 0) {
                mapjson.put("error_code", 0);
            } else {
                mapjson.put("error_code", -10);
                mapjson.put("error_message", "Đường truyền Internet gặp sự cố, vui lòng thử lại sau");
            }
        } else {
            mapjson.put("error_code", "-111");
            mapjson.put("error_message", "Phiên đăng nhập hết hạn");
        }
    }

    private void addVaccine(HttpServletRequest req, HttpServletResponse resp, JSONObject mapjson) {
        int centreId = ServletUtil.getIntParameter(req, "centreId");
        String vaccineName = ServletUtil.getStringParameter(req, "vaccineName");
        String vaccineDescription = ServletUtil.getStringParameter(req, "description");
        String sessionKey = HttpRequestUtils.getCookie(req, "keylog");
        String userNameSession = Utils.checkSession(sessionKey, req);
        if (userNameSession != null && !userNameSession.isEmpty()) {
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
        } else {
            mapjson.put("error_code", "-111");
            mapjson.put("error_message", "Phiên đăng nhập hết hạn");
        }
    }

    private void getEvents(HttpServletRequest req, JSONObject mapjson) {
        int cId = ServletUtil.getIntParameter(req, "cid");
        TVMGetEventsResponse result = VREventManagerServiceClient.getInstance().getEvents(cId);
        if (result != null && result.error.errorCode == 0) {
            mapjson.put("error_code", 0);
            JSONArray arr = new JSONArray();
            if (!result.events.isEmpty()) {
                for (int i = 0; i < result.events.size(); i++) {
                    TEventBasicInfo info = result.events.get(i);
                    if (info != null) {
                        JSONObject obj = new JSONObject();
                        obj.put("eventId", info.eventId);
                        obj.put("eventName", info.eventName);
                        obj.put("eventStatus", info.eventStatus);
                        obj.put("vaccineId", info.vaccineId);
                        obj.put("startTimeRegistration", Utils.convertTimeToStringDateTime(info.startTimeRegistration));
                        obj.put("vaccineName", info.vaccineName);
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

    private void getEventInfo(HttpServletRequest req, JSONObject mapjson) {
        int eventId = ServletUtil.getIntParameter(req, "eid");
        int centreId = ServletUtil.getIntParameter(req, "cid");
        TVMGetEventInfoResponse result = VREventManagerServiceClient.getInstance().getEventInfo(eventId);
        if (result != null && result.error.errorCode == 0) {
            mapjson.put("error_code", 0);
            mapjson.put("eventId", result.eventInfo.eventId);
            mapjson.put("eventName", result.eventInfo.eventName);
            mapjson.put("eventStatus", result.eventInfo.eventStatus);
            mapjson.put("vaccineId", result.eventInfo.vaccineId);
            mapjson.put("startTimeRegistration", Utils.convertTimeToStringDateTime(result.eventInfo.startTimeRegistration));
            mapjson.put("startTimeInjection", Utils.convertTimeSecondToDayString(result.eventInfo.startTimeInjection));
            mapjson.put("totalOfDoses", result.eventInfo.totalOfDoses);
            mapjson.put("totalOfDay", result.eventInfo.numberOfInjectionsPerDay);
            mapjson.put("vaccineId", result.eventInfo.vaccineId);
            JSONArray arr = new JSONArray();
            TCMGetVaccinesResponse vaccines = VRCentreManagerServiceClient.getInstance().getVaccines(centreId);
            if (vaccines.error.errorCode == TErrorCode.EC_OK.getValue()) {
                for (int i = 0; i < vaccines.vaccines.size(); i++) {
                    TVaccineInfo vcInfo = vaccines.vaccines.get(i);
                    JSONObject obj = new JSONObject();
                    obj.put("vaccineId", vcInfo.vaccineId);
                    obj.put("name", vcInfo.name);
                    arr.add(obj);
                }
            }
            mapjson.put("vcs", arr);

        } else {
            mapjson.put("error_code", -10);
            if (result == null) {
                mapjson.put("error_message", "Đường truyền Internet gặp sự cố, vui lòng thử lại sau");
            } else {
                mapjson.put("error_message", "Đường truyền Internet gặp sự cố, vui lòng thử lại sau " + result.error.errorCode);
            }

        }
    }
}
