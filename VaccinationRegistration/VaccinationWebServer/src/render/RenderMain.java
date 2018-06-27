/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package render;

import configuration.Configuration;
import crdhn.vr.thrift.data.TRegistrationInfo;
import crdhn.vr.thrift.data.TRegistrationType;
import crdhn.vr.thrift.data.TCentreInfo;
import crdhn.vr.thrift.data.TEventBasicInfo;
import crdhn.vr.thrift.data.TEventInfo;
import crdhn.vr.thrift.data.TEventStatus;
import crdhn.vr.thrift.data.TVaccineInfo;
import crdhn.vr.thrift.response.TCMGetCentresResponse;
import crdhn.vr.thrift.response.TCMGetVaccinesResponse;
import crdhn.vr.thrift.response.TError;
import crdhn.vr.thrift.response.TErrorCode;
import crdhn.vr.thrift.response.TRMAddExtraRegistrationResponse;
import crdhn.vr.thrift.response.TRMAddRegistrationResponse;
import crdhn.vr.thrift.response.TVMGetEventInfoResponse;
import crdhn.vr.thrift.response.TVMGetEventsResponse;
import hapax.Template;
import hapax.TemplateDataDictionary;
import hapax.TemplateDictionary;
import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
public class RenderMain extends RenderBase {

    private static final Logger log = Log.getLogger(RenderMain.class);
    private static RenderMain _instance = new RenderMain();

    public static RenderMain getInstance() {
        return _instance;
    }

    public String render(String page, HttpServletRequest req, HttpServletResponse resp)
            throws IOException, Exception {
        //	log.info(" public String render");

//        System.out.println("index=========" + CounterSynchronized.increment());
        return this.doRender(page, req, resp);
    }

    private String doRender(String page, HttpServletRequest req, HttpServletResponse resp)
            throws IOException, Exception {
        String content = "";

        switch (page) {
            case "register":
                content = renderRegisterByDesigner(req, resp);
//                content = renderRegister(req, resp);
                break;
            case "result":
                content = renderResult(req, resp);
                break;
            case "monitor_export":
                content = renderMonitorExport(req, resp);
                break;
            case "monitor_admin":
                content = renderMonitorAdmin(req, resp);
                break;
            case "admin_register":
                content = renderRegisterAdmin(req, resp);
                break;
            case "create_event":
                content = renderCreateEventAdmin(req, resp);
                break;
            case "update_event":
                content = renderUpdateEventInfo(req, resp);
                break;
            case "admin_list_event":
                content = renderEventsAdmin(req, resp);
                break;
            case "list_event_public":
                content = renderEventsPublic(req, resp);
                break;
            case "login":
                content = renderLogin(req, resp);
                break;
            case "centre":
                content = renderManageCentre(req, resp);
                break;
            case "manage_vaccine":
                content = renderManageVaccine(req, resp);
                break;
            default:
//                content = renderHome(req, resp);
                content = renderHomeDesign(req, resp);
                break;
        }

        return content;
    }

    public void pause1(long sleeptime) {
        try {
            Thread.sleep(sleeptime);
        } catch (InterruptedException ex) {
            //ToCatchOrNot
        }
    }

    private String renderManageCentre(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String content = "";
        TemplateDataDictionary dic = TemplateDictionary.create();

        try {
            dic.setVariable("static_url", Configuration.STATIC_URL);
            String html_content = "";
            Template template;
            String sessionKey = HttpRequestUtils.getCookie(req, "keylog");
            String userNameSession = Utils.checkSession(sessionKey, req);
            if (userNameSession != null && !userNameSession.isEmpty()) {
                dic.setVariable("username", userNameSession);
                TCMGetCentresResponse centres = VRCentreManagerServiceClient.getInstance().getCentres();
                if (centres != null && centres.error.errorCode == TErrorCode.EC_OK.getValue()) {
                    List<TCentreInfo> listCentreInfo = centres.getCentres();
                    if (listCentreInfo != null && listCentreInfo.size() > 0) {
                        for (int i = 0; i < listCentreInfo.size(); i++) {
                            TCentreInfo centerInfo = listCentreInfo.get(i);
                            if (centerInfo != null) {
                                String domain_test = "dangkynhitw.org.vn";//centerInfo.domain
                                html_content += "<tr id=\"center_" + centerInfo.centreId + "\">"
                                        + "<th scope=\"row\">" + (i + 1) + "</th>"
                                        + "<td ><a id=\"name_centre_" + centerInfo.centreId + "\" href=\"/centre/" + centerInfo.centreId + "\" >" + centerInfo.name + "</a></td>"
                                        + "<td ><a id=\"domain_centre_" + centerInfo.centreId + "\" href=\"" + domain_test + "\">" + domain_test + "</a></td>"
                                        + "<td id=\"phone_centre_" + centerInfo.centreId + "\">" + centerInfo.phoneNumber + "</td>"
                                        + "<td id=\"address_centre_" + centerInfo.centreId + "\">" + centerInfo.address + "</td>"
                                        + "<td><div>"
                                        + "<button style=\"margin:3px;\" onclick=\"tc.updateCentreInfo(" + centerInfo.centreId + "); return false;\"  class=\"btn btn-primary\" type=\"button\">Sửa</button>"
                                        + "<button style=\"margin:3px;\" onclick=\"tc.deleteCentre('" + centerInfo.centreId + "'); return false;\" class=\"btn btn-primary\" type=\"button\">Xóa</button>"
                                        + " </div></td>"
                                        + "</tr>";
                            }

                        }
                    }
                }

                dic.setVariable("LISTCENTER", html_content);
                template = getCTemplate("html/center/manage_center.xtm");
            } else {
                template = getCTemplate("html/login.xtm");
            }
            content = template.renderToString(dic);
        } catch (Exception ex) {
            log.warn("render renderManageCentre", ex);
        }
        return content;
    }

    private String renderManageVaccine(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String content = "";
        TemplateDataDictionary dic = TemplateDictionary.create();

        try {
            dic.setVariable("static_url", Configuration.STATIC_URL);
            String html_content = "";
            String centreIdStr = req.getAttribute("id").toString();
            int centreId = Integer.parseInt(centreIdStr);
            Template template;
            String sessionKey = HttpRequestUtils.getCookie(req, "keylog");
            String userNameSession = Utils.checkSession(sessionKey, req);
            if (userNameSession != null && !userNameSession.isEmpty()) {
                dic.setVariable("username", userNameSession);
                TCMGetVaccinesResponse vaccines = VRCentreManagerServiceClient.getInstance().getVaccines(centreId);
                if (vaccines != null && vaccines.error.errorCode == TErrorCode.EC_OK.getValue()) {
                    List<TVaccineInfo> listVCInfo = vaccines.getVaccines();
                    if (listVCInfo != null && listVCInfo.size() > 0) {
                        for (int i = 0; i < listVCInfo.size(); i++) {
                            TVaccineInfo vcInfo = listVCInfo.get(i);
                            if (vcInfo != null) {
                                html_content += "<tr id=\"vaccine_" + vcInfo.vaccineId + "\">"
                                        + "<th scope=\"row\">" + (i + 1) + "</th>"
                                        + "<td id=\"name_vaccine_" + vcInfo.vaccineId + "\"  >" + vcInfo.name + "</td>"
                                        + "<td id=\"description_" + vcInfo.vaccineId + "\">" + vcInfo.description + "</td>"
                                        + "<td><div>"
                                        + "<button style=\"margin:3px;\" onclick=\"tc.updateVaccine(" + centreId + ",'" + vcInfo.vaccineId + "'); return false;\"  class=\"btn btn-primary\" type=\"button\">Sửa</button>"
                                        + "<button style=\"margin:3px;\" onclick=\"tc.deleteVaccine(" + centreId + ",'" + vcInfo.vaccineId + "'); return false;\" class=\"btn btn-primary\" type=\"button\">Xóa</button>"
                                        + " </div></td>"
                                        + "</tr>";
                            }

                        }
                    }
                }

                dic.setVariable("CENTREID", centreIdStr);
                dic.setVariable("LIST_VACCINE", html_content);
                template = getCTemplate("html/center/manage_vaccine.xtm");
            } else {
                template = getCTemplate("html/login.xtm");
            }
            content = template.renderToString(dic);
        } catch (Exception ex) {
            log.warn("render renderManageVaccine", ex);
        }
        return content;
    }

    private String renderLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String content = "";
        TemplateDataDictionary dic = TemplateDictionary.create();

        try {
            dic.setVariable("static_url", Configuration.STATIC_URL);
            Template template = getCTemplate("html/login.xtm");

            content = template.renderToString(dic);
        } catch (Exception ex) {
            log.warn("render renderLogin", ex);
        }
        return content;
    }

    private String renderEventsAdmin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String content = "";
        TemplateDataDictionary dic = TemplateDictionary.create();

        try {
            String html_content = "";
            dic.setVariable("static_url", Configuration.STATIC_URL);
            String domain = req.getRemoteHost();
            int centreId = 1;//VRCentreManagerServiceClient.getInstance().getCentreInfo(domain);
            String cId = "";
            if (req.getAttribute("id") != null) {
                cId = req.getAttribute("id").toString();
                centreId = Integer.parseInt(cId);
            }
            Template template;
            String sessionKey = HttpRequestUtils.getCookie(req, "keylog");
            String userNameSession = Utils.checkSession(sessionKey, req);
            if (userNameSession != null && !userNameSession.isEmpty()) {
                dic.setVariable("username", userNameSession);
                TVMGetEventsResponse eventsInfo = VREventManagerServiceClient.getInstance().getEvents(centreId);
                if (eventsInfo != null && eventsInfo.error.errorCode == TErrorCode.EC_OK.getValue()) {
                    for (int i = 0; i < eventsInfo.events.size(); i++) {
                        TEventBasicInfo eInfo = eventsInfo.events.get(i);
                        if (eInfo != null) {
                            String status = "";
                            if (eInfo.eventStatus == TEventStatus.TES_AVAILABLE.getValue()) {
                                status = "Đang họat động";
                            } else if (eInfo.eventStatus == TEventStatus.TES_DELETED.getValue()) {
                                status = "Đã xóa";
                            } else if (eInfo.eventStatus == TEventStatus.TES_DISABLE.getValue()) {
                                status = "Đã kết thúc";
                            } else if (eInfo.eventStatus == TEventStatus.TES_PENDING.getValue()) {
                                status = "Đang chờ";
                            }
                            html_content += "<tr id=\"event_" + eInfo.eventId + "\">"
                                    + "<th scope=\"row\">" + (i + 1) + "</th>"
                                    + "<td ><a id=\"name_event_" + eInfo.eventId + "\" href=\"/event/" + eInfo.eventId + "\" >" + eInfo.eventName + "</a></td>"
                                    //                                + "<td id=\"total_injection_" + eInfo.eventId + "\">" + eInfo.vaccineName + "</td>"
                                    + "<td id=\"startTime_" + eInfo.eventId + "\">" + Utils.convertTimeToStringDateTime(eInfo.startTimeRegistration) + "</td>"
                                    + "<td id=\"status" + eInfo.eventId + "\">" + status + "</td>"
                                    + "<td><div>"
                                    + "<button style=\"margin:3px;\" onclick=\"tc_event.checkUpdateEvent(" + centreId + "," + eInfo.eventId + "," + eInfo.eventStatus + ",'" + status + "'); return false;\"  class=\"btn btn-primary\" type=\"button\">Sửa</button>"
                                    + "<button style=\"margin:3px;\" onclick=\"tc_event.deleteEvent('" + eInfo.eventId + "'); return false;\" class=\"btn btn-primary\" type=\"button\">Xóa</button>"
                                    + " </div></td>"
                                    + "</tr>";
                        }

                    }
                    if (eventsInfo.events.isEmpty()) {
                        dic.setVariable("MESSAGE_EMPTY", "<div id=\"message_empty\" class=\"col-lg-12\">Hiện tại không có đợt tiêm chủng nào!</div>");
                    }
                }
                dic.setVariable("CENTREID", centreId + "");
                dic.setVariable("LISTEVENT", html_content);
                template = getCTemplate("html/event/manage_event.xtm");
            } else {
                template = getCTemplate("html/login.xtm");
            }
            content = template.renderToString(dic);
        } catch (Exception ex) {
            log.warn("render renderEventsAdmin", ex);
        }
        return content;
    }

    private String renderEventsPublic(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String content = "";
        TemplateDataDictionary dic = TemplateDictionary.create();

        try {
            String html_content = "";
            dic.setVariable("static_url", Configuration.STATIC_URL);
            String domain = req.getRemoteHost();
            int centreId = 1;//VRCentreManagerServiceClient.getInstance().getCentreInfo(domain);

            TVMGetEventsResponse eventsInfo = VREventManagerServiceClient.getInstance().getEvents(centreId);
            if (eventsInfo != null && eventsInfo.error.errorCode == TErrorCode.EC_OK.getValue()) {
                for (int i = 0; i < eventsInfo.events.size(); i++) {
                    TEventBasicInfo eInfo = eventsInfo.events.get(i);
                    if (eInfo != null) {
                        html_content += "<tr id=\"event_" + eInfo.eventId + "\">"
                                + "<th scope=\"row\">" + (i + 1) + "</th>"
                                + "<td ><a id=\"name_event_" + eInfo.eventId + "\" href=\"/event/" + eInfo.eventId + "\" >" + eInfo.eventName + "</a></td>"
                                + "<td id=\"total_injection_" + eInfo.eventId + "\">" + eInfo.vaccineName + "</td>"
                                + "<td id=\"startTime_" + eInfo.eventId + "\">" + eInfo.startTimeRegistration + "</td>"
                                + "<td id=\"status_" + eInfo.eventId + "\">" + TEventStatus.findByValue(eInfo.eventStatus).name() + "</td>"
                                + "</tr>";
                    }

                }
                if (eventsInfo.events.isEmpty()) {
                    dic.setVariable("MESSAGE_EMPTY", "<div id=\"message_empty\" class=\"col-lg-6\">Hiện tại không có đợt tiêm chủng nào!</div>");
                }
            }
            dic.setVariable("LISTEVENT", html_content);
            Template template = getCTemplate("html/event_" + domain.replaceAll("\\.", "_") + ".xtm");

            content = template.renderToString(dic);
        } catch (Exception ex) {
            log.warn("render renderGetEventsPublic", ex);
        }
        return content;
    }

    private String renderHomeDesign(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String content = "";
        TemplateDataDictionary dic = TemplateDictionary.create();

        try {
            TError result = VRRegistrationManagerServiceClient.getInstance().getEventStatus(Configuration.evenId);
            if (result != null && (result.errorCode == TErrorCode.EC_REGISTRATION_AVAILABLE.getValue() || result.errorCode == TErrorCode.EC_REGISTRATION_PENDING.getValue())) {
                dic.setVariable("show_limit", "style=\"display:none;\"");
                dic.setVariable("hide_register", "style=\"text-align:center; vertical-align: top;\"");

            } else {
                dic.setVariable("hide_register", "style=\"display:none;\"");
                dic.setVariable("show_limit", "style=\"font-weight: 700; color:red\"");
            }
            dic.setVariable("static_url", Configuration.STATIC_URL);
            Template template = getCTemplate("html/home_design.xtm");

            content = template.renderToString(dic);
        } catch (Exception ex) {
            log.warn("render renderHomeDesign", ex);
        }
        return content;
    }

    private String renderCreateEventAdmin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String content = "";
        TemplateDataDictionary dic = TemplateDictionary.create();

        try {
            dic.setVariable("static_url", Configuration.STATIC_URL);
            Template template;
            String sessionKey = HttpRequestUtils.getCookie(req, "keylog");
            String userNameSession = Utils.checkSession(sessionKey, req);
            if (userNameSession != null && !userNameSession.isEmpty()) {
                dic.setVariable("username", userNameSession);
                String centreId = req.getAttribute("id").toString();
                dic.setVariable("CENTREID", centreId);
                String listVaccine = "";
                TCMGetVaccinesResponse vcs = VRCentreManagerServiceClient.getInstance().getVaccines(Integer.parseInt(centreId));
                if (vcs != null && vcs.error.errorCode == TErrorCode.EC_OK.getValue()) {
                    for (int i = 0; i < vcs.vaccines.size(); i++) {
                        TVaccineInfo vcInfo = vcs.vaccines.get(i);
                        if (vcInfo != null) {
                            listVaccine += "<option value=\"" + vcInfo.vaccineId + "\" >" + vcInfo.name + "</option>";
                        }

                    }
                }
                dic.setVariable("VACCINES", listVaccine);
                template = getCTemplate("html/event/create_event.xtm");
            } else {
                template = getCTemplate("html/login.xtm");
            }

            content = template.renderToString(dic);
        } catch (Exception ex) {
            log.warn("render renderCreateEventAdmin", ex);
        }
        return content;
    }

    private String renderUpdateEventInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String content = "";
        TemplateDataDictionary dic = TemplateDictionary.create();
        try {

            dic.setVariable("static_url", Configuration.STATIC_URL);
            Template template;
            String sessionKey = HttpRequestUtils.getCookie(req, "keylog");
            String userNameSession = Utils.checkSession(sessionKey, req);
            if (userNameSession != null && !userNameSession.isEmpty()) {
                dic.setVariable("username", userNameSession);
                String centreId = req.getAttribute("id").toString();
                dic.setVariable("CENTREID", centreId);
                String eId = req.getAttribute("eId").toString();
                int eventId = Integer.parseInt(eId);
                TVMGetEventInfoResponse event_respone = VREventManagerServiceClient.getInstance().getEventInfo(eventId);
                if (event_respone == null || event_respone.error.errorCode != TErrorCode.EC_OK.getValue()) {
                    return "-1";
                }
                TEventInfo eventInfo = event_respone.getEventInfo();
                dic.setVariable("EVENTID", eventId + "");
                dic.setVariable("event_name", eventInfo.eventName);
                dic.setVariable("registrationTime", Utils.convertTimeToStringDateTime(eventInfo.startTimeRegistration));
                dic.setVariable("injectionTime", Utils.convertTimeSecondToDayString(eventInfo.startTimeInjection));
                dic.setVariable("totalOfDoses", eventInfo.totalOfDoses + "");
                dic.setVariable("totalOfDay", eventInfo.numberOfInjectionsPerDay + "");
                if (eventInfo.isWorkingOnSaturday) {
                    dic.setVariable("check_saturday", "checked");
                }
                if (eventInfo.isWorkingOnSunday) {
                    dic.setVariable("check_sunday", "checked");
                }
                String listVaccine = "";
                TCMGetVaccinesResponse vcs = VRCentreManagerServiceClient.getInstance().getVaccines(Integer.parseInt(centreId));
                if (vcs != null && vcs.error.errorCode == TErrorCode.EC_OK.getValue()) {
                    for (int i = 0; i < vcs.vaccines.size(); i++) {
                        TVaccineInfo vcInfo = vcs.vaccines.get(i);
                        if (vcInfo != null) {
                            if (vcInfo.vaccineId == eventInfo.vaccineId) {
                                listVaccine += "<option value=\"" + vcInfo.vaccineId + "\" selected >" + vcInfo.name + "</option>";
                            } else {
                                listVaccine += "<option value=\"" + vcInfo.vaccineId + "\"  >" + vcInfo.name + "</option>";
                            }
                        }

                    }
                }
                JSONObject objEventUpdate = new JSONObject();
                JSONArray arrWorkingTime = new JSONArray();
                String listWorkingTime = "";
                for (int i = 0; i < eventInfo.workingShiftNames.size(); i++) {
                    String time_tmp = eventInfo.workingShiftNames.get(i);
                    listWorkingTime += "<li id=\"time_" + i
                            + "\" class=\"select2-search-choice\">    <div>" + time_tmp
                            + "</div>    <a class=\"select2-search-choice-close\" onclick=\"tc_event.removeWorkingTime('" + i + "','" + time_tmp + "'); return false;\" href=\"javascript:void(0);\"></a></li>";
                    arrWorkingTime.add(time_tmp);
                }
                objEventUpdate.put("workingTimes", arrWorkingTime);
                dic.setVariable("listWorkingTime", listWorkingTime);
                String listHoliday = "";
                Iterator<Long> iterator = eventInfo.vacationDays.iterator();
                int index = 1;
                JSONArray arrHoliday = new JSONArray();
                while (iterator.hasNext()) {
                    Long holidayTime = iterator.next();
                    String holidayString = Utils.convertTimeSecondToDayString(holidayTime);
                    listHoliday += "<li id=\"holiday_" + index
                            + "\" class=\"select2-search-choice\">    <div>" + holidayString
                            + "</div>    <a tabindex=\"-1\" class=\"select2-search-choice-close\" onclick=\"tc_event.removeHoliday('" + index + "','" + holidayString + "'); return false;\" href=\"javascript:void(0);\"></a></li>";
                    arrHoliday.add(holidayString);
                }
                objEventUpdate.put("holidays", arrHoliday);
                dic.setVariable("objectEvent", objEventUpdate.toJSONString());
                dic.setVariable("listHoliday", listHoliday);
                dic.setVariable("VACCINES", listVaccine);
                template = getCTemplate("html/event/update_event.xtm");
            } else {
                template = getCTemplate("html/login.xtm");
            }

            content = template.renderToString(dic);
        } catch (Exception ex) {
            log.warn("render renderCreateEventAdmin", ex);
        }
        return content;
    }

    private String renderRegisterAdmin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String content = "";
        TemplateDataDictionary dic = TemplateDictionary.create();
        try {
            dic.setVariable("static_url", Configuration.STATIC_URL);
            Template template;
            String sessionKey = HttpRequestUtils.getCookie(req, "keylog");
            String userNameSession = Utils.checkSession(sessionKey, req);
            if (userNameSession != null && !userNameSession.isEmpty()) {
                dic.setVariable("username", userNameSession);
//                TError result = VRRegistrationManagerServiceClient.getInstance().getEventStatus(Configuration.evenId);
//                if (result != null && result.errorCode == TErrorCode.EC_REGISTRATION_AVAILABLE.getValue()) {
                String html = "<option value=\"8h – 9h\" >Từ 8h – 9h</option>"
                        + "<option value=\"9h – 10h\" >Từ 9h – 10h</option>"
                        + "<option value=\"10h – 11h\" >Từ 10h – 11h</option>"
                        + "<option value=\"13h30 – 14h30\" >Từ 13h30 – 14h30</option>"
                        + "<option value=\"14h30 – 16h\" >Từ 14h30 – 16h</option>";

                dic.setVariable("option_time_injection", html);
                template = getCTemplate("html/register_admin_design.xtm");
//                } else {
//                    template = getCTemplate("html/home_design.xtm");
//                }

            } else {
                template = getCTemplate("html/login.xtm");
            }
            content = template.renderToString(dic);
        } catch (Exception ex) {
            log.warn("render register_admin", ex);
        }
        return content;
    }

    private String renderMonitorAdmin(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String content = "";
        TemplateDataDictionary dic = TemplateDictionary.create();
        try {
            dic.setVariable("static_url", Configuration.STATIC_URL);
            Template template;
            String sessionKey = HttpRequestUtils.getCookie(req, "keylog");
            String userNameSession = Utils.checkSession(sessionKey, req);
            if (userNameSession != null && !userNameSession.isEmpty()) {
                dic.setVariable("username", userNameSession);

                template = getCTemplate("html/monitor_admin.xtm");
            } else {
                template = getCTemplate("html/login.xtm");
            }

            content = template.renderToString(dic);
        } catch (Exception ex) {
            log.warn("render monitor_admin", ex);
        }
        return content;
    }

    private String renderResult(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String content = "";
        TemplateDataDictionary dic = TemplateDictionary.create();
        dic.setVariable("static_url", Configuration.STATIC_URL);
        dic.setVariable("show_manager", "style=\"display:none;\"");
        try {
            String nameChild = ServletUtil.getStringParameter(req, "namechild");
            String birthdayChild = ServletUtil.getStringParameter(req, "birthdaychild");
            String nameParent = ServletUtil.getStringParameter(req, "name_parent");
            String cmtndParent = ServletUtil.getStringParameter(req, "cmtnd_parent");
            String phoneParent = ServletUtil.getStringParameter(req, "phone_parent");
//        String email = ServletUtil.getStringParameter(req, "email");
//            int typeVaccine = ServletUtil.getIntParameter(req, "typevaccien");
            int type_register = ServletUtil.getIntParameter(req, "type_register");
            String address = ServletUtil.getStringParameter(req, "address");
            //for admin
            String dayOfInjection = ServletUtil.getStringParameter(req, "dayOfInjection");
            String injectionTime = ServletUtil.getStringParameter(req, "injectionTime");
            int type = ServletUtil.getIntParameter(req, "type");
            String sessionKey = HttpRequestUtils.getCookie(req, "keylog");
            String userNameSession = Utils.checkSession(sessionKey, req);
            if (userNameSession != null && !userNameSession.isEmpty() && type == 1) {
                dic.setVariable("username", userNameSession);
                dic.setVariable("show_manager", "");
                dic.setVariable("control", "tiemchung_admin");
            } else {
                dic.setVariable("control", "tiemchung");
                TError result = VRRegistrationManagerServiceClient.getInstance().getEventStatus(Configuration.evenId);
                if (result != null && result.errorCode != TErrorCode.EC_REGISTRATION_AVAILABLE.getValue()) {
                    if (result.errorCode == TErrorCode.EC_REGISTRATION_PENDING.getValue()) {
                        dic.setVariable("show_limit", "style=\"display:none;\"");
                        dic.setVariable("hide_register", "style=\"text-align:center; vertical-align: top;\"");

                    } else {
                        dic.setVariable("hide_register", "style=\"display:none;\"");
                        dic.setVariable("show_limit", "style=\"font-weight: 700; color:red;\"");
                    }
                    Template template = getCTemplate("html/home_design.xtm");
                    content = template.renderToString(dic);
                    return content;
                }
            }
            if (nameChild.isEmpty() || birthdayChild.isEmpty()
                    || nameParent.isEmpty() || cmtndParent.isEmpty()
                    || phoneParent.isEmpty()
                    || address.isEmpty() || type_register < 0 || type_register > 1) {
                dic.setVariable("showcontent", "style=\"display:none;\"");
                dic.setVariable("id_mesage_error", "");
                dic.setVariable("mesage_error", "Các tham số không hợp lệ, vui lòng đăng ký lại");
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
                registerInfo.setRegistrationTime(System.currentTimeMillis() / 1000);
                registerInfo.setChildrenCarrierAddress(address);
                if (type == 1) {
                    cal = Calendar.getInstance();
                    String[] dayInjection = dayOfInjection.split("/");
                    if (dayInjection != null && dayInjection.length == 3) {
                        int day = Integer.valueOf(dayInjection[0]);
                        int month = Integer.valueOf(dayInjection[1]);
                        int year = Integer.valueOf(dayInjection[2]);
                        cal.set(year, (month - 1), day);
                        registerInfo.setInjectionDate(cal.getTimeInMillis() / 1000);
                    }

                    registerInfo.setInjectionTime(injectionTime + ", ngày " + dayOfInjection);
                    registerInfo.setRegistrationType(TRegistrationType.TRT_MANUAL.getValue());
                    TRMAddExtraRegistrationResponse addResp = VRRegistrationManagerServiceClient.getInstance().addExtraRegistration(registerInfo);
                    if (addResp != null && addResp.error.errorCode == 0) {
                        dic.setVariable("showcontent", "");
                        dic.setVariable("id_mesage_error", "style=\"display:none;\"");
                        dic.setVariable("key_register", addResp.registrationCode);
                        dic.setVariable("nameChild", nameChild);
                        dic.setVariable("birthdayChild", birthdayChild);
                        dic.setVariable("name_parent", nameParent);
                        dic.setVariable("pin_parent", cmtndParent);
                        dic.setVariable("phone_parent", phoneParent);
                        dic.setVariable("injection", addResp.injectionTime);
                        dic.setVariable("address", address);

                    } else {
                        dic.setVariable("showcontent", "style=\"display:none;\"");
                        dic.setVariable("id_mesage_error", "");
                        if (addResp == null) {
                            dic.setVariable("mesage_error", "Đường truyền Internet gặp sự cố, vui lòng thử lại sau");
                        } else {
                            if (addResp.error.errorCode == TErrorCode.EC_PIN_REGISTERED.getValue()) {
                                dic.setVariable("mesage_error", "Số CMND/Hộ chiếu này đã đăng ký thành công trong ngày.<br>(Một CMND/Hộ chiếu không được phép đăng ký 2 lần trong 1 ngày)");
                            } else if (addResp.error.errorCode == TErrorCode.EC_REGISTRATION_FULL.getValue()) {
                                dic.setVariable("mesage_error", "Số lượng Vắcxin của đợt tiêm chủng này đã hết,<br> hẹn quý khách đợt tiêm tiếp theo.");
                            } else {
                                dic.setVariable("mesage_error", "Đường truyền Internet gặp sự cố, vui lòng thử lại sau " + addResp.error.errorCode);
                            }

                        }

                    }
                } else {
                    registerInfo.setRegistrationType(TRegistrationType.TRT_ONLINE.getValue());
                    TRMAddRegistrationResponse addResp = VRRegistrationManagerServiceClient.getInstance().addRegistration(registerInfo);
                    if (addResp != null && addResp.error.errorCode == 0) {
                        dic.setVariable("showcontent", "");
                        dic.setVariable("id_mesage_error", "style=\"display:none;\"");
                        dic.setVariable("key_register", addResp.registrationCode);
                        dic.setVariable("nameChild", nameChild);
                        dic.setVariable("birthdayChild", birthdayChild);
                        dic.setVariable("name_parent", nameParent);
                        dic.setVariable("pin_parent", cmtndParent);
                        dic.setVariable("phone_parent", phoneParent);
                        dic.setVariable("injection", addResp.injectionTime);
                        dic.setVariable("address", address);

                    } else {
                        dic.setVariable("showcontent", "style=\"display:none;\"");
                        dic.setVariable("id_mesage_error", "");
                        if (addResp == null) {
                            dic.setVariable("mesage_error", "Đường truyền Internet gặp sự cố, vui lòng thử lại sau");
                        } else {
                            if (addResp.error.errorCode == TErrorCode.EC_PIN_REGISTERED.getValue()) {
                                dic.setVariable("mesage_error", "Số CMND/Hộ chiếu này đã đăng ký thành công trong ngày.<br>(Một CMND/Hộ chiếu không được phép đăng ký 2 lần trong 1 ngày)");
                            } else if (addResp.error.errorCode == TErrorCode.EC_REGISTRATION_FULL.getValue()) {
                                dic.setVariable("mesage_error", "Số lượng Vắcxin của đợt tiêm chủng này đã hết,<br> hẹn quý khách đợt tiêm tiếp theo.");
                            } else {
                                dic.setVariable("mesage_error", "Đường truyền Internet gặp sự cố, vui lòng thử lại sau" + addResp.error.errorCode);
                            }

                        }

                    }
                }

            }

            Template template = getCTemplate("html/result.xtm");

            content = template.renderToString(dic);
        } catch (Exception ex) {
            log.warn("render result", ex);
        }
        return content;
    }

    private String renderMonitorExport(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String content = "";
        TemplateDataDictionary dic = TemplateDictionary.create();
        String sessionKey = HttpRequestUtils.getCookie(req, "keylog");
        String userNameSession = Utils.checkSession(sessionKey, req);
        try {
            dic.setVariable("static_url", Configuration.STATIC_URL);
            Template template;
            if (userNameSession != null && !userNameSession.isEmpty()) {
                dic.setVariable("username", userNameSession);

                template = getCTemplate("html/monitor_filter_export.xtm");
            } else {
                template = getCTemplate("html/login.xtm");
            }

            content = template.renderToString(dic);
        } catch (Exception ex) {
            log.warn("render monitor_filter_export", ex);
        }
        return content;
    }

    private String renderRegister(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String content = "";
        TemplateDataDictionary dic = TemplateDictionary.create();

        try {
            dic.setVariable("static_url", Configuration.STATIC_URL);
//            dic.setVariable("url_captcha", Configuration.captcha_url);
            Template template = getCTemplate("html/register.xtm");

            content = template.renderToString(dic);
        } catch (Exception ex) {
            log.warn("render detail", ex);
        }
        return content;
    }

    private String renderRegisterByDesigner(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String content = "";
        TemplateDataDictionary dic = TemplateDictionary.create();

        try {
            Template template;
            dic.setVariable("static_url", Configuration.STATIC_URL);
            TError result = VRRegistrationManagerServiceClient.getInstance().getEventStatus(Configuration.evenId);
            if (result != null && result.errorCode == TErrorCode.EC_REGISTRATION_AVAILABLE.getValue()) {
                template = getCTemplate("html/register_design.xtm");
            } else {
                if (result != null && result.errorCode == TErrorCode.EC_REGISTRATION_PENDING.getValue()) {
                    dic.setVariable("show_limit", "style=\"display:none;\"");
                    dic.setVariable("hide_register", "style=\"text-align:center; vertical-align: top;\"");

                } else {
                    dic.setVariable("hide_register", "style=\"display:none;\"");
                    dic.setVariable("show_limit", "style=\"font-weight: 700; color:red\"");
                }
                template = getCTemplate("html/home_design.xtm");
            }

            content = template.renderToString(dic);
        } catch (Exception ex) {
            log.warn("render detail", ex);
        }
        return content;
    }

}
