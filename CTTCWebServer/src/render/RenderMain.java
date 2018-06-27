/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package render;

import configuration.Configuration;
import crdhn.vr.thrift.data.TRegistrationInfo;
import crdhn.vr.thrift.data.TRegistrationType;
import crdhn.vr.thrift.response.TError;
import crdhn.vr.thrift.response.TErrorCode;
import crdhn.vr.thrift.response.TRMAddExtraRegistrationResponse;
import crdhn.vr.thrift.response.TRMAddRegistrationResponse;
import hapax.Template;
import hapax.TemplateDataDictionary;
import hapax.TemplateDictionary;
import java.io.IOException;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import service.utils.CounterSynchronized;
import service.utils.HttpRequestUtils;
import service.utils.ServletUtil;
import service.utils.Utils;
import sns.backend.transport.VRRegistrationManagerServiceClient;

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

        System.out.println("index=========" + CounterSynchronized.increment());
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
//            case "monitor":
//                content = renderMonitor(req, resp);
//                break;
            case "monitor_admin":
                content = renderMonitorAdmin(req, resp);
                break;
            case "admin_register":
                content = renderRegisterAdmin(req, resp);
                break;
            case "create_event":
                content = renderCreateEventAdmin(req, resp);
                break;
            case "login":
                content = renderLogin(req, resp);
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

    private String renderLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String content = "";
        TemplateDataDictionary dic = TemplateDictionary.create();

        try {
            dic.setVariable("static_url", Configuration.STATIC_URL);
            Template template = getCTemplate("html/login.xtm");

            content = template.renderToString(dic);
        } catch (Exception ex) {
            log.warn("render renderHome", ex);
        }
        return content;
    }

    private String renderHomeFreeStyle(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String content = "";
        TemplateDataDictionary dic = TemplateDictionary.create();

        try {
            dic.setVariable("static_url", Configuration.STATIC_URL);
            dic.setVariable("EVENTID", "1");
            Template template = getCTemplate("html/home_tiemchung.xtm");

            content = template.renderToString(dic);
        } catch (Exception ex) {
            log.warn("render renderHome", ex);
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
            log.warn("render renderHome", ex);
        }
        return content;
    }

    private String renderCreateEventAdmin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String content = "";
        TemplateDataDictionary dic = TemplateDictionary.create();
        String sessionKey = HttpRequestUtils.getCookie(req, "keylog");
        String userNameSession = Utils.checkSession(sessionKey, req);
        try {
            dic.setVariable("static_url", Configuration.STATIC_URL);
            Template template;
            if (userNameSession != null && !userNameSession.isEmpty()) {
                template = getCTemplate("html/create_event.xtm");
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
        String sessionKey = HttpRequestUtils.getCookie(req, "keylog");
        String userNameSession = Utils.checkSession(sessionKey, req);
        try {
            dic.setVariable("static_url", Configuration.STATIC_URL);
            Template template;
            if (userNameSession != null && !userNameSession.isEmpty()) {
                dic.setVariable("username", userNameSession);
//                TError result = VRRegistrationManagerServiceClient.getInstance().getEventStatus(Configuration.evenId);
//                if (result != null && result.errorCode == TErrorCode.EC_REGISTRATION_AVAILABLE.getValue()) {
                String html = "<option value=\"Từ 8h – 9h\" >Từ 8h – 9h</option>"
                        + "<option value=\"Từ 9h – 10h\" >Từ 9h – 10h</option>"
                        + "<option value=\"Từ 10h – 11h\" >Từ 10h – 11h</option>"
                        + "<option value=\"Từ 13h30 – 14h30\" >Từ 13h30 – 14h30</option>"
                        + "<option value=\"Từ 14h30 – 16h\" >Từ 14h30 – 16h</option>";

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
        String sessionKey = HttpRequestUtils.getCookie(req, "keylog");
        String userNameSession = Utils.checkSession(sessionKey, req);
        try {
            dic.setVariable("static_url", Configuration.STATIC_URL);
            Template template;
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
            if (userNameSession != null && !userNameSession.isEmpty() && type==1) {
                dic.setVariable("username", userNameSession);
                dic.setVariable("show_manager", "");
            } else {
                TError result = VRRegistrationManagerServiceClient.getInstance().getEventStatus(Configuration.evenId);
                if (result != null && result.errorCode != TErrorCode.EC_REGISTRATION_AVAILABLE.getValue()) {
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
                    registerInfo.setInjectionDate(dayOfInjection);
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

    private String renderMonitor(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String content = "";
        TemplateDataDictionary dic = TemplateDictionary.create();

        try {
            dic.setVariable("static_url", Configuration.STATIC_URL);
//            dic.setVariable("url_captcha", Configuration.captcha_url);
            String item_register = "";
            int index = 1;
            item_register += "<th scope=\"row\">11</th>"
                    + "                                           <td>Đặng Thái Sơn</td>"
                    + "                                           <td>Đặng Văn Nam</td>"
                    + "                                           <td>112233123</td>"
                    + "                                           <td>12/12/2015</td>"
                    + "                                           <td>30/12/2015</td>";
            Template template = getCTemplate("html/monitor_tiemchung.xtm");

            content = template.renderToString(dic);
        } catch (Exception ex) {
            log.warn("render monitor_tiemchung", ex);
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
            if (result != null && (result.errorCode == TErrorCode.EC_REGISTRATION_AVAILABLE.getValue()||result.errorCode == TErrorCode.EC_REGISTRATION_PENDING.getValue())) {
                template = getCTemplate("html/register_design.xtm");
            } else {
                template = getCTemplate("html/home_design.xtm");
            }

            content = template.renderToString(dic);
        } catch (Exception ex) {
            log.warn("render detail", ex);
        }
        return content;
    }

//    private String renderHome(HttpServletRequest req, HttpServletResponse resp)
//            throws IOException, Exception {
//        String content = "";
//        TemplateDataDictionary dic = TemplateDictionary.create();
//
//        try {
//
//            dic.setVariable("static_url", Configuration.STATIC_URL);
//            TVMGetVaccinationEvents listEvent = VRVaccinationManagerServiceClient.getInstance().getVaccinationEvents("nhiTW");
//            if (listEvent != null && listEvent.error.errorCode == TErrorCode.EC_OK.getValue()) {
//                if (listEvent.events.isEmpty()) {
//                    dic.setVariable("LISTEVENT", "<div class=\"form-group\" >"
//                            + "                                <label class=\"col-lg-12 control-label\">Hiện tại không có đợt tiêm chủng nào!</label>"
//                            + "                            </div>");
//                }else {
//                    String html_item="";
//                    for (int i = 0; i < listEvent.events.size(); i++) {
//                        TVaccinationEventBasicInfo eventInfo = listEvent.events.get(i);
//                        if(eventInfo!=null){
//                            String status ="";
//                            if(eventInfo.eventStatus == TEventStatus.TES_DISABLE.getValue()){
//                                status ="disabled";
//                            }
//                            html_item+="<a class=\"btn"+status+" btn-outline btn-default btn-lg btn-block\" type=\"button\">"+eventInfo.eventName+" "+eventInfo.vaccineName+" ngày "+eventInfo.startTimeRegistration+"</a>";
//                        }
//                        
//                    }
//                }
//                        
//            }
//            Template template = getCTemplate("html/home_listevent.xtm");
//
//            content = template.renderToString(dic);
//
//        } catch (Exception ex) {
//            log.warn("render home", ex);
//        }
//        return content;
//    }
    private String renderTestCapcha(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, Exception {
        String content = "";
        TemplateDataDictionary dic = TemplateDictionary.create();

        try {
            Template template = getCTemplate("html/testcapcha.xtm");
            content = template.renderToString(dic);

        } catch (Exception ex) {
            log.warn("render capcha", ex);
        }
        return content;
    }

}
