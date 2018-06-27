
(function() {
    function _render(tpl, data) {
        for (var f in data)
            tpl = tpl.replace(new RegExp("{" + f + "}", "g"), data[f]);
        tpl = tpl.replace(/{[a-z_0-9]+}/gi, "");
        return tpl;
    }
    window.zmTemplate = {
        render: function(template, data) {
            if ($.isArray(data)) {
                var result = new Array();
                for (var i = 0; i < data.length; i++)
                    result.push(_render(template, data));
                return result;
            }
            else
                return _render(template, data);
        }
    };
})();
var tc = {
    url: "http://" + window.location.hostname + "/ajx",
    code: "",
    path_image: "",
    current_page: 1,
    arrRegister: {},
    mapRegistration: new Object(),
    crrentHtml_search: "",
    isFilter: false,
    arrFilter: {},
    mapFilter: new Object(),
    html_registration_success: "<div class=\"header_ts\"><div class=\"header_wrap\"><div class=\"header_logo1\"><h1 class=\"header_h\"><a href=\"/\" class=\"header_logo\"></a></h1></div></div></div>" +
            "            <div id=\"page-wrapper\" style=\"min-height: 368px; margin:0px;\">"
            + "<div class=\"row\" style=\"margin-left:80px; margin-right:80px;\">" +
            "					<div class=\"col-lg-12\">" +
            "                      <h1 style=\"text-align:center\">Phiếu xác nhận đăng ký</h1>" +
            "                   </div>" +
            "                   <div style=\"text-align:center\" class=\"form-group\">" +
            "                         <label class=\"col-lg-12\">Mã số xác nhận thành công:<span  style=\"color: red; font-size:26px;\">  {key_register}</span></label>" +
            "                   </div>" +
            "                   <div style=\"margin-top:10px;\" class=\"col-lg-12\">" +
            "					   <label style=\"font-style\" class=\"col-lg-12\">Bệnh viện Nhi TW đã chấp nhận yêu cầu đăng ký tiêm chủng Vắcxin Pentaxim của quý khách, thông tin cơ bản như sau:</label>" +
            "					</div>" +
            "                   <div style=\"margin-top: 10px;\" class=\"col-lg-12\">" +
            "					   <label class=\"col-lg-12\">I. Thông tin của Trẻ</label>" +
            "					</div>" +
            "					<div class=\"col-lg-12\" >" +
            "						   <label class=\"col-lg-4\" style=\"margin-left:65px; font-weight: 100; padding:0px;\" class=\"col-lg-4\">-  Họ và tên (theo giấy khai sinh):</label>" +
            "							<label class=\"col-lg-6\" style=\"font-weight: 100; padding:0px;\" class=\"col-lg-6\">{nameChild}</label>" +
            "						</div>" +
            "					<div class=\"col-lg-12\" >" +
            "						   <label class=\"col-lg-4\" style=\"margin-left:65px; font-weight: 100; padding:0px;\" class=\"col-lg-4\">-  Ngày tháng năm sinh:</label>" +
            "							<label class=\"col-lg-6\" style=\"font-weight: 100; padding:0px;\" class=\"col-lg-6\">{birthdayChild}</label>" +
            "						</div>" +
            "					<div style=\"margin-top: 10px;\" class=\"col-lg-12\">" +
            "					   <label class=\"col-lg-12\">II. Thông tin người đưa trẻ đi tiêm (Phụ huynh/Người giám hộ):</label>" +
            "					</div>" +
            "					<div class=\"col-lg-12\">" +
            "						   <label class=\"col-lg-4\" style=\"margin-left:65px; font-weight: 100; padding:0px;\" class=\"col-lg-4\">-  Họ tên:</label>" +
            "							<label class=\"col-lg-6\" style=\"font-weight: 100; padding:0px;\" class=\"col-lg-6\">{name_parent}</label>" +
            "					</div>" +
            "					<div class=\"col-lg-12\">" +
            "						   <label  class=\"col-lg-4\" style=\"margin-left:65px; font-weight: 100; padding:0px;\" class=\"col-lg-4\">-  Số CMND/hộ chiếu:</label>" +
            "							<label class=\"col-lg-6\" style=\"font-weight: 100; padding:0px;\" class=\"col-lg-6\">{pin_parent}</label>" +
            "					</div>" +
            "					<div class=\"col-lg-12\">" +
            "						   <label class=\"col-lg-4\"  style=\"margin-left:65px; font-weight: 100; padding:0px;\" class=\"col-lg-4\">-  Số điện thoại liên lạc:</label>" +
            "							<label class=\"col-lg-6\" style=\"font-weight: 100; padding:0px;\" class=\"col-lg-6\">{phone_parent}</label>" +
            "					</div>" +
            "					<div class=\"col-lg-12\">" +
            "					   <label class=\"col-lg-4\" style=\"margin-left:65px; font-weight: 100; padding:0px;\" class=\"col-lg-4\">-  Địa chỉ:</label>" +
            "						<label class=\"col-lg-6\" style=\"font-weight: 100; padding:0px;\" class=\"col-lg-6\">{address}</label>" +
            "					</div>" +
            "					<div class=\"col-lg-12\">" +
            "					   <label class=\"col-lg-4\" style=\"margin-left:65px; font-weight: 100; padding:0px;\" class=\"col-lg-4\">-  Thời gian hẹn tiêm chủng:</label>" +
            "						<label class=\"col-lg-6\" style=\"font-weight: 100; padding:0px;\" class=\"col-lg-6\">{injection}</label>" +
            "					</div>" +
            "					<div style=\"margin-top: 10px; \" class=\"col-lg-12\">" +
            "					   <label class=\"col-lg-12\">III. Khi đi  mang theo giấy tờ:</label>" +
            "					</div>" +
            "					<div class=\"col-lg-12\">" +
            "					   <label class=\"col-lg-6\" style=\" margin-left:65px; font-weight: 100; padding:0px;\" class=\"col-lg-12\">-  Giấy khai sinh của trẻ</label>" +
            "					</div>" +
            "					<div class=\"col-lg-12\">" +
            "					   <label class=\"col-lg-6\" style=\" margin-left:65px; font-weight: 100; padding:0px;\" class=\"col-lg-12\">-  CMND/hộ chiếu của người đưa trẻ đi tiêm</label>" +
            "					</div>" +
            "					<div class=\"col-lg-12\">" +
            "					   <label class=\"col-lg-6\" style=\" margin-left:65px; font-weight: 100; padding:0px;\" class=\"col-lg-12\">-  Mã số xác nhận đăng ký thành công</label>" +
            "					</div>" +
            "					<div style=\"margin-bottom:50px;text-align: center;\" class=\"col-lg-12\">" +
            "<a  href=\"javascript:void(0);\" onclick=\"tc.print();\" class=\"btn btn-default\">\n" +
            "                                                            <i class=\"fa fa-print\"></i>" +
            "                                                            In phiếu" +
            "                                                        </a>" +
            "                                 </div>" +
            "                </div></div>",
    html_footer: "<div style=\" color: #fff;  font-size: 14px; background:  #0099cc repeat scroll 0 0; float: left;  padding: 10px 45px;width: 100%;\">" +
            "            <p>&copy; 2015 - BỆNH VIỆN NHI TRUNG ƯƠNG <br>Địa chỉ: 18/879 La Thành - Đống Đa - Hà Nội <br>Điện thoại: (84.4) 6 273 8573  - Fax: (84.4) 6 273 8573 <br></p>" +
            "        </div>",
    nextPage: function() {
        if (!$("#next_page").hasClass("disabled")) {
//            tc.getlistMonitorForPepole(tc.current_page + 1);
        }
    },
    previousPage: function() {
        if (!$("#previous_page").hasClass("disabled")) {
//            tc.getlistMonitorForPepole(tc.current_page - 1);
        }
    },
    print: function(id) {
        var iframe = $("<iframe>").attr("id", "print_frame").appendTo($('body'));
        iframe.get(0).contentWindow.document.write($("#" + id + "").html());
        iframe.get(0).contentWindow.print();
        iframe.remove();

    },
    
    getcaptcha: function() {
        $.post(tc.url,
                {
                    "action": "getcaptcha"
                }, {
            "dataType": "json"
        }).done(function(data) {
            tc.code = data.code;
            tc.path_image = data.path;
            document.getElementById('captcha_img').src = data.path;
        });
    },
    getEventStatus: function() {
        var param = "?action=getEventStatus";
        $.getJSON(tc.url + param, function(data) {
            if (data.error_code === 20) {
                location.href = "/register";
                return false;
            } else {
                if (data.error_code === 21) {
                    var alertMess = BootstrapDialog.show({title: "Thông báo", message: "Hiện tại chưa tới giờ đăng ký, Vui lòng quay lại sau"});
                    setTimeout(function() {
                        alertMess.close();
                    }, 5000);
                    return false;

                } else if (data.error_code === 11) {
                    var alertMess = BootstrapDialog.show({title: "Thông báo", message: "Số lượng Vắcxin của đợt tiêm chủng này đã hết, hẹn quý khách đợt tiêm tiếp theo."});
                    $("#btn_registration").hide();
                    $("#limit_message").show();
                    setTimeout(function() {
                        alertMess.close();
                    }, 5000);
                    return false;

                } else {
                    BootstrapDialog.show({title: "Thông báo", message: "Hiện tại không có đợt đăng ký tiêm chủng, Vui lòng quay lại sau"});
                    return false;
                }

            }

        });
    },
    getlistMonitorForPepole: function(page) {
        var param = "?action=getListRegistrationPepole";
        $(".loading").show();
        $.getJSON(tc.url + param, function(data) {
            $(".loading").hide();
            if (data === undefined || data.error_code !== 0) {
                BootstrapDialog.show({title: "Thông báo", message: data.error_message});
                return false;
            } else {
                if (data.items.length === 0) {
                    $("#tbl_import").append("<br><br><p style=\"magin:20px;\" ><b>Chưa có Trẻ nào đăng ký thành công!</b></p><br><br>")
                } else {
                    var html_item = "";
                    tc.arrRegister = new Array();
                    tc.mapRegistration = new Object();
                    for (var i = 0; i < data.items.length; i++) {

                        var item = data.items[i];
                        tc.arrRegister.push(item);
                        tc.mapRegistration[item.registrationId]=item;
                        html_item += tc.renderItemMonitor(item, i + 1);
                    }
                    $("#content_tiemchung").html(html_item);
                    tc.crrentHtml_search = $("#tbl_import").html();
                }
            }
        });
    },
    renderItemMonitor: function(item, index) {
        var html_item = "";
        var html_option_status = "";
        var type_registration = "style=\"display:none;\"";
        if (item.registrationType === "TRT_MANUAL") {
            type_registration = "";
        }
        if (item.injectionStatus === 1) {
            html_option_status = "<option value=\"1\">Đã tiêm</option><option  value=\"0\">Chưa tiêm</option>";
        } else {
            html_option_status = "<option value=\"0\">Chưa tiêm</option><option  value=\"1\">Đã tiêm</option>";
        }
        html_item = "<tr id=\"registration_" + item.registrationId + "\">"
                + "<th scope=\"row\">" + index + "</th>"
                + "<td id=\"childrenName_" + item.registrationId + ">" + item.childrenName + "</td>"
                + "<td id=\"birthday_" + item.registrationId + ">" + item.birthday + "</td>"
                + "<td id=\"parent_name_" + item.registrationId + ">" + item.parent_name + "</td>"
                + "<td id=\"parent_pin_" + item.registrationId + ">" + item.parent_pin + "</td>"
                + "<td >" + item.registrationCode + "</td>"
                + "<td id=\"injectionTime_" + item.registrationId + ">" + item.injectionTime + "</td>"
                + "<td><div>"
                + "   <select id=\"status_injection_" + item.registrationId + "\" onchange=\"tc.updateStatusRegistration(" + item.registrationId + "); return false;\" >"
                + html_option_status
                + "   </select>"
                + " </div></td>"
                + "<button onclick=\"tc.updateRegistrationInfo(" + item + "); return false;\" " + type_registration + " class=\"btn btn-primary btn-xs\" type=\"button\">Sửa</button>"
                + "<button onclick=\"tc.deleteRegistration('" + item.registrationId + "'); return false;\" " + type_registration + " class=\"btn btn-primary btn-xs\" type=\"button\">Xóa</button>"
                + "</tr>";
        return html_item;
    },
    updateRegistrationInfo: function(item) {
        var injectionTime = item.injectionTime.toLowerCase();
        var html_injection_time = "<option value=\"8h – 9h\" >Từ 8h – 9h</option>"
                + "<option value=\"9h – 10h\" >Từ 9h – 10h</option>"
                + "<option value=\"10h – 11h\" >Từ 10h – 11h</option>"
                + "<option value=\"13h30 – 14h30\" >Từ 13h30 – 14h30</option>"
                + "<option value=\"14h30 – 16h\" >Từ 14h30 – 16h</option>";
        if (injectionTime.contains("8h")) {
            html_injection_time = "<option value=\"8h – 9h\" >Từ 8h – 9h</option>"
                    + "<option value=\"9h – 10h\" >Từ 9h – 10h</option>"
                    + "<option value=\"10h – 11h\" >Từ 10h – 11h</option>"
                    + "<option value=\"13h30 – 14h30\" >Từ 13h30 – 14h30</option>"
                    + "<option value=\"14h30 – 16h\" >Từ 14h30 – 16h</option>";
        } else if (injectionTime.contains("9h") && injectionTime.contains("10h")) {
            html_injection_time = "<option value=\"9h – 10h\" >Từ 9h – 10h</option>"
                    + "<option value=\"8h – 9h\" >Từ 8h – 9h</option>"
                    + "<option value=\"10h – 11h\" >Từ 10h – 11h</option>"
                    + "<option value=\"13h30 – 14h30\" >Từ 13h30 – 14h30</option>"
                    + "<option value=\"14h30 – 16h\" >Từ 14h30 – 16h</option>";
        } else if (injectionTime.contains("10h") && injectionTime.contains("11h")) {
            html_injection_time = "<option value=\"10h – 11h\" >Từ 10h – 11h</option>"
                    + "<option value=\"8h – 9h\" >Từ 8h – 9h</option>"
                    + "<option value=\"9h – 10h\" >Từ 9h – 10h</option>"
                    + "<option value=\"13h30 – 14h30\" >Từ 13h30 – 14h30</option>"
                    + "<option value=\"14h30 – 16h\" >Từ 14h30 – 16h</option>";
        } else if (injectionTime.contains("13h30")) {
            html_injection_time = "<option value=\"13h30 – 14h30\" >Từ 13h30 – 14h30</option>"
                    + "<option value=\"8h – 9h\" >Từ 8h – 9h</option>"
                    + "<option value=\"9h – 10h\" >Từ 9h – 10h</option>"
                    + "<option value=\"10h – 11h\" >Từ 10h – 11h</option>"
                    + "<option value=\"14h30 – 16h\" >Từ 14h30 – 16h</option>";
        } else if (injectionTime.contains("16h")) {
            html_injection_time = "<option value=\"14h30 – 16h\" >Từ 14h30 – 16h</option>"
                    + "<option value=\"8h – 9h\" >Từ 8h – 9h</option>"
                    + "<option value=\"9h – 10h\" >Từ 9h – 10h</option>"
                    + "<option value=\"10h – 11h\" >Từ 10h – 11h</option>"
                    + "<option value=\"13h30 – 14h30\" >Từ 13h30 – 14h30</option>";
        }

        BootstrapDialog.show({
            title: "Cập nhật thông tin đăng ký",
            closable: true,
            closeByBackdrop: false,
            closeByKeyboard: false,
            message: "                      <div class=\"form-group\">" +
                    "                          <label>Họ và tên (theo giấy khai sinh/giấy chứng sinh):</label>" +
                    "                          <input id=\"name_childrent\" class=\"form-control\" value=\"" + item.childrenName + "\" >" +
                    "                      </div>" +
                    "                      <div class=\"form-group\">" +
                    "                          <label>Ngày tháng năm sinh:</label>" +
                    "                          <input id=\"birthday\" class=\"form-control\" value=\"" + item.birthday + "\"  >" +
                    "                      </div>" +
                    "                      <div class=\"form-group\">" +
                    "                          <label>Họ và tên của người đưa trẻ đi tiêm chủng:</label>" +
                    "                          <input id=\"parent_name\" class=\"form-control\" value=\"" + item.parent_name + "\" >" +
                    "                      </div>" +
                    "                      <div class=\"form-group\">" +
                    "                          <label>Số CMND/Hộ chiếu:</label>" +
                    "                          <input id=\"parent_pin\" class=\"form-control\" value=\"" + item.parent_pin + "\"  >" +
                    "                      </div>" +
                    "                      <div class=\"form-group\">" +
                    "                          <label>Điện thoại liên lạc:</label>" +
                    "                          <input id=\"phone\" class=\"form-control\" value=\"" + item.phone + "\" >" +
                    "                      </div>" +
                    "                      <div class=\"form-group\">" +
                    "                          <label>Email:</label>" +
                    "                          <input id=\"email\" class=\"form-control\" value=\"" + item.email + "\"  >" +
                    "                      </div>" +
                    "                      <div class=\"form-group\">" +
                    "                          <label>Địa chỉ:</label>" +
                    "                          <input id=\"address\" class=\"form-control\" value=\"" + item.address + "\" >" +
                    "                      </div>" +
                    "                      <div class=\"form-group\">" +
                    "                          <label>Ngày tiêm:</label>" +
                    "                          <textarea row=\"3\" id=\"injectionDay\" class=\"form-control\" >" + item.injectionDate + "</textarea>" +
                    "                      </div>" +
                    "                      <div class=\"form-group\">" +
                    "                          <label>Thời gian tiêm:</label>" +
                    "                          <select id=\"injectionTime\" class=\"form-control\">" +
                    html_injection_time +
                    "                          </select>" +
                    "                      </div>" +
                    "                  </form>",
            buttons: [{
                    label: "Cập nhật",
                    cssClass: 'btn-primary',
//                        hotkey: 13, // Enter.
                    action: function(dialogItself) {
                        var name_childrent = $("#name_childrent").val();
                        var birthday = $("#birthday").val();
                        var parent_name = $("#parent_name").val();
                        var parent_pin = $("#parent_pin").val();
                        var phone = $("#phone").val();
                        var email = $("#email").val();
                        var injectionDay = $("#injectionDay").val();
                        $(".loading").show();
                        var t = document.getElementById("injectionTime");
                        var injectionTime = t.options[t.selectedIndex].value;
                        $.post(tc.url,
                                {
                                    "action": "updateRegistrationInfo",
                                    "registrationID": item.registrationId,
                                    "namechild": name_childrent,
                                    "birthdaychild": birthday,
                                    "name_parent": birthday,
                                    "namedb": parent_name,
                                    "cmtnd_parent": parent_pin,
                                    "phone_parent": phone,
                                    "email": email,
                                    "dayOfInjection": injectionDay,
                                    "injectionTime": injectionTime

                                }, {
                            "dataType": "json"
                        }).done(function(data) {
                            $(".loading").hide();
                            if (data.error_code === 0) {
                                var alertMess = BootstrapDialog.alert("Cập nhật thành công");
                                setTimeout(function() {
                                    alertMess.close();
                                }, 3000);

                                $("#childrenName_" + item.registrationId).html(name_childrent);
                                $("#birthday_" + item.registrationId).html(birthday);
                                $("#parent_name_" + item.registrationId).html(parent_name);
                                $("#parent_pin_" + item.registrationId).html(parent_pin);
                                $("#injectionTime_" + item.registrationId).html(injectionTime);
//                                window.location.reload();
                            } else {
                                BootstrapDialog.show({
                                    title: "Thông báo",
                                    message: data.error_message
                                });
                                return;
                            }
                            dialogItself.close();
                        });
                    }
                }, {
                    label: "Hủy bỏ",
                    action: function(dialogItself) {
                        dialogItself.close();
                    }
                }]
        });
    },
    deleteRegistration: function(registrationId) {
        BootstrapDialog.show({
            title: "Xác nhận",
            message: "Bạn có chắc chắn muốn xóa đăng ký này không?",
            buttons: [{
                    label: "Đồng ý",
                    cssClass: 'btn-primary',
                    hotkey: 13, // Enter.
                    action: function(dialogItself) {
                        $(".loading").show();
                        dialogItself.close();
                        var param = "?action=delete_registraton&id=" + registrationId;
                        $.getJSON(tc.url + param, function(data) {

                            $(".loading").hide();
                            if (data === null || data.error_code !== 0) {
                                BootstrapDialog.show({
                                    title: "Thông báo",
                                    message: data.error_message
                                });
                                return false;
                            } else {
                                var alert_popup = BootstrapDialog.alert("Xóa đăng ký thành công!");
                                $("#registration_" + registrationId).empty();
                                $("#registration_" + registrationId).hide();
                                setTimeout(function() {
                                    alert_popup.close();
                                }, 2000);

                            }
                        });
                    }
                }, {
                    label: "Hủy bỏ",
                    action: function(dialogItself) {
                        dialogItself.close();
                    }
                }]
        });
    },
    updateStatusRegistration: function(registrationId) {
        $(".loading").show();
        var value_status = document.getElementById("status_injection_" + registrationId).value;
        var param = "?action=updateStatusPepole&id=" + registrationId + "&status=" + value_status;
        $.getJSON(tc.url + param, function(data) {
            $(".loading").hide();
            if (data === undefined || data.error_code !== 0) {
                BootstrapDialog.show({title: "Thông báo", message: data.error_message});
                if (data.error_code === "-111") {
                    setTimeout(function() {
                        logout();
                        window.open("/login", "_self");
                    }, 100);
                }
                return false;
            } else {
                var alertMess = BootstrapDialog.show({title: "Thông báo", message: "Cập nhật thành công"});
                setTimeout(function() {
                    alertMess.close();
                }, 1000);
                return false;
            }
        });
    },
    register: function() {
        var namechild = $("#val_namechild").val();
        var birthday_child = $("#val_birthdaychild").val();
        var val_name_parent = $("#val_name_parent").val();
        var val_cmtnd_parent = $("#val_cmtnd_parent").val();
        var val_phone_parent = $("#val_phone_parent").val();
//        var val_email_parent = $("#val_email_parent").val();
        var val_captcha = $("#val_captcha").val();
        var val_vaccien = $("#val_vaccine").val();
        var val_address = $("#val_address").val();
        if (namechild === undefined || namechild === "") {
            BootstrapDialog.show({title: "Thông báo", message: "Hãy nhập tên của trẻ"});
            return;
        } else if (namechild.length > 30) {
            BootstrapDialog.show({title: "Thông báo", message: "Tên của trẻ không được quá 30 ký tự"});
            return;
        }
        if (birthday_child === undefined || birthday_child === "" || !tc.checkDate(birthday_child)) {
            BootstrapDialog.show({title: "Thông báo", message: "Ngày sinh của trẻ không hợp lệ. \nTrẻ phải trong độ tuổi tiêm chủng: 2 tháng – 24 tháng"});
            return;
        }
        if (val_name_parent === undefined || val_name_parent === "") {
            BootstrapDialog.show({title: "Thông báo", message: "Nhập tên người đưa trẻ đi tiêm"});
            return;
        } else if (val_name_parent.length > 30) {
            BootstrapDialog.show({title: "Thông báo", message: "Tên của nguời đưa trẻ đi tiêm không được quá 30 ký tự"});
            return;
        }
        if (val_cmtnd_parent === undefined || val_cmtnd_parent.trim().length === 0) {
            BootstrapDialog.show({title: "Thông báo", message: "CMND/Hộ chiếu không được bỏ trống"});
            return;
        } else if (val_cmtnd_parent.trim().length !== 9 && val_cmtnd_parent.trim().length !== 12) {
            BootstrapDialog.show({title: "Thông báo", message: "CMND/Hộ chiếu không hợp lệ"});
            return;
        }
        if (val_phone_parent === undefined || val_phone_parent.trim().length === 0) {
            BootstrapDialog.show({title: "Thông báo", message: "Số điện thoại không được bỏ trống"});
            return;
        } else if (val_phone_parent.length > 12 || val_phone_parent.length < 5) {
            BootstrapDialog.show({title: "Thông báo", message: "Số điện thoại không hợp lệ"});
            return;
        }
        if (val_address === undefined || val_address === "") {
            BootstrapDialog.show({title: "Thông báo", message: "Chưa nhập địa chỉ"});
            return;
        } else if (val_address.length > 255) {
            BootstrapDialog.show({title: "Thông báo", message: "Địa chỉ không được vượt quá 255 ký tự"});
            return;
        }
        if (val_captcha === undefined || val_captcha.length < 3 || val_captcha !== tc.code) {
            BootstrapDialog.show({title: "Thông báo", message: "Mã kiểm tra không đúng"});
            return;
        }
        location.href = "/result?namechild=" + namechild + "&birthdaychild=" + birthday_child + "&name_parent=" + val_name_parent +
                "&cmtnd_parent=" + val_cmtnd_parent + "&phone_parent=" + val_phone_parent + "&typevaccien=" + val_vaccien +
                "&address=" + val_address;
//        $(".loading").show();
//        $.post(tc.url,
//                {
//                    "action": "addRegistration",
//                    "namechild": namechild,
//                    "birthdaychild": birthday_child,
//                    "name_parent": val_name_parent,
//                    "cmtnd_parent": val_cmtnd_parent,
//                    "phone_parent": val_phone_parent,
////                    "email": val_email_parent,
//                    "typevaccien": val_vaccien,
//                    "address": val_address
//                }, {
//            "dataType": "json"
//        }).done(function(data) {
//            $(".loading").hide();
//            if (data.error_code === 0) {
//                var alertMess = BootstrapDialog.show({title: "Thông báo", message: "Đăng ký thành công"});
//                setTimeout(function() {
//                    alertMess.close();
//                    location.href = "/result/" + data.registrationCode + "/" + data.registrationId;
////                    location.href = "/result?key_register=" + data.registrationCode + "&nameChild=" + data.nameChild
////                            + "&birthdayChild=" + data.birthdayChild + "&name_parent=" + data.nameParent + "&pin_parent=" + data.cmtndParent
////                            + "&phone_parent=" + data.phoneParent + "&injection=" + data.injectionTime + "&address=" + val_address;
////                    var item_data = {
////                        "key_register": data.registrationCode, //data.registrationId,
////                        "nameChild": data.nameChild,
////                        "birthdayChild": data.birthdayChild,
////                        "name_parent": data.nameParent,
////                        "pin_parent": data.cmtndParent,
////                        "phone_parent": data.phoneParent,
////                        "injection": data.injectionTime,
////                        "address": val_address
////
////
////                    };
////                    var content = zmTemplate.render(tc.html_registration_success, item_data);
////                    $("#wrapper").html(content);
////                    $("#wrapper").append(tc.html_footer);
//                }, 300);
//            } else {
//                BootstrapDialog.show({title: "Thông báo", message: data.error_message});
//                tc.getcaptcha();
//                return;
//            }
//
//        });
    },
    registerAdmin: function() {
        var namechild = $("#val_namechild").val();
        var birthday_child = $("#val_birthdaychild").val();
        var val_name_parent = $("#val_name_parent").val();
        var val_cmtnd_parent = $("#val_cmtnd_parent").val();
        var val_phone_parent = $("#val_phone_parent").val();
//        var val_email_parent = $("#val_email_parent").val();
//        var val_captcha = $("#val_captcha").val();
        var val_vaccien = $("#val_vaccine").val();
        var dayOfInjection = $("#val_vaci_day").val();
        var time_injection = $("#val_injection_time").val();
        var val_address = $("#val_address").val();

//        var reg_mail = /^[A-Za-z0-9]+([_\.\-]?[A-Za-z0-9])*@[A-Za-z0-9]+([\.\-]?[A-Za-z0-9]+)*(\.[A-Za-z]+)+$/;
        if (namechild === undefined || namechild === "") {
            BootstrapDialog.show({title: "Thông báo", message: "Hãy nhập tên của trẻ"});
            return;
        } else if (namechild.length > 30) {
            BootstrapDialog.show({title: "Thông báo", message: "Tên của trẻ không được quá 30 ký tự"});
            return;
        }
        if (birthday_child === undefined || birthday_child === "" || !tc.checkDate(birthday_child)) {
            BootstrapDialog.show({title: "Thông báo", message: "Ngày sinh của trẻ không hợp lệ. \nTrẻ phải trong độ tuổi tiêm chủng: 2 tháng – 24 tháng"});
            return;
        }
        if (val_name_parent === undefined || val_name_parent === "") {
            BootstrapDialog.show({title: "Thông báo", message: "Nhập tên người đưa trẻ đi tiêm"});
            return;
        } else if (val_name_parent.length > 30) {
            BootstrapDialog.show({title: "Thông báo", message: "Tên nguời đưa trẻ đi tiêm không được quá 30 ký tự"});
            return;
        }
        if (val_cmtnd_parent === undefined || val_cmtnd_parent.trim().length === 0) {
            BootstrapDialog.show({title: "Thông báo", message: "CMND/Hộ chiếu không được bỏ trống"});
            return;
        } else if (val_cmtnd_parent.trim().length !== 9 && val_cmtnd_parent.trim().length !== 12) {
            BootstrapDialog.show({title: "Thông báo", message: "CMND/Hộ chiếu không hợp lệ"});
            return;
        }
        if (val_phone_parent === undefined || val_phone_parent.trim().length === 0) {
            BootstrapDialog.show({title: "Thông báo", message: "Số điện thoại không được bỏ trống"});
            return;
        } else if (val_phone_parent.length > 12 || val_phone_parent.length < 5) {
            BootstrapDialog.show({title: "Thông báo", message: "Số điện thoại không hợp lệ"});
            return;
        }
        if (val_address === undefined || val_address === "") {
            BootstrapDialog.show({title: "Thông báo", message: "Chưa nhập địa chỉ"});
            return;
        } else if (val_address.length > 255) {
            BootstrapDialog.show({title: "Thông báo", message: "Địa chỉ không được vượt quá 255 ký tự"});
            return;
        }
        if (dayOfInjection === undefined || dayOfInjection === "" || !tc.isDateValidForFilter(dayOfInjection)) {
            BootstrapDialog.show({title: "Thông báo", message: "Ngày tiêm của trẻ không hợp lệ."});
            return;
        }
        location.href = "/result?namechild=" + namechild + "&birthdaychild=" + birthday_child + "&name_parent=" + val_name_parent +
                "&cmtnd_parent=" + val_cmtnd_parent + "&phone_parent=" + val_phone_parent + "&typevaccien=" + val_vaccien +
                "&address=" + val_address + "&dayOfInjection=" + dayOfInjection + "&injectionTime=" + time_injection + "&type=1";

//        $(".loading").show();
//        $.post(tc.url,
//                {
//                    "action": "addRegistrationAdmin",
//                    "namechild": namechild,
//                    "birthdaychild": birthday_child,
//                    "name_parent": val_name_parent,
//                    "cmtnd_parent": val_cmtnd_parent,
//                    "phone_parent": val_phone_parent,
////                    "email": val_email_parent,
//                    "typevaccien": val_vaccien,
//                    "dayOfInjection": dayOfInjection,
//                    "injectionTime": time_injection,
//                    "address": val_address
//                }, {
//            "dataType": "json"
//        }).done(function(data) {
//            $(".loading").hide();
//            if (data.error_code === 0) {
//                location.href = "/result/" + data.registrationCode + "/" + data.registrationId;
////                var alertMess = BootstrapDialog.show({title: "Thông báo", message: "Đăng ký thành công"});
////                setTimeout(function() {
////                    alertMess.close();
////                }, 500);
//            } else {
//                BootstrapDialog.show({title: "Thông báo", message: data.error_message});
////                tc.getcaptcha();
//                return;
//            }
//
//        });
    },
    exportToExcel: function() {
//                        obj.put("email", info.childrenCarrierEmail);
//                        obj.put("parent_name", info.childrenCarrierName);
//                        obj.put("parent_pin", info.childrenCarrierPIN);
//                        obj.put("phone", info.childrenCarrierPhoneNumber);
//                        obj.put("birthday", Utils.convertTimeSecondToDayString(info.childrenDateOfBirth));
//                        obj.put("childrenName", info.childrenName);
//                        obj.put("eventId", info.eventId);
//                        obj.put("injectionOrderNumber", info.injectionOrderNumber);
//                        obj.put("injectionStatus", info.injectionStatus);
//                        obj.put("injectionTime", info.injectionTime);
//                        obj.put("registrationId", info.registrationId);
//                        obj.put("registrationTime", Utils.convertTimeSecondToDayString(info.registrationTime));
//                        obj.put("registrationType", TRegistrationType.findByValue(info.registrationType).name());
//                        obj.put("registrationCode", info.registrationCode);
//                        obj.put("address", "adress");
        var dataSource = new Array();
        if (tc.isFilter) {
            dataSource = tc.arrFilter;
        } else {
            dataSource = tc.arrRegister;
        }
        var report = gonrin.spreadsheet({
            name: "Sheetname",
            fields: [
                {field: "registrationId", label: "STT"},
                {field: "registrationCode", label: "Mã đăng ký"},
                {field: "childrenName", label: "Họ tên trẻ"},
                {field: "birthday", label: "Ngày sinh"},
                {field: "parent_name", label: "Người đưa trẻ đi tiêm"},
                {field: "parent_pin", label: "CMND/Hộ chiếu"},
                {field: "address", label: "Địa chỉ"},
                {field: "phone", label: "Điện thoại"},
                {field: "injectionTime", label: "Thời gian tiêm"},
//                {field: "registrationTime", label: "Thời gian đăng ký"},
                {field: "injectionStatus", label: "Trạng thái"}

            ],
            data_source: dataSource,
            excel: {
                file_name: "DanhSachTiemChung2016.xlsx",
            }
        }).save_excel();
    },
    refreshSearch: function() {
        location.href = "/admin/monitor";
//        tc.isFilter = false;
//        tc.arrFilter = new Array();
//        $("#val_search_ID").val("");
//        $("#val_search_parent_pin").val("");
//        $("#val_time_start").val("");
//        $("val_time_end").val("");
//        $("#messageSearch").hide();
//        $("#tbl_import").html(tc.crrentHtml_search);
//        $("#refresh_search").hide();
    },
    CompareDate: function(dateOne, dateTwo) {
        if (dateOne >= dateTwo) {
            return true;
        } else {
            return false;
        }
    },
    isCheckInput: function(element) {
        if (element.checked) {
            return true;
        }
        return false;
    },
    filter: function() {
        //Note: 00 is month i.e. January
//       var dateOne = new Date(2010, 00, 15); //Year, Month, Date
//        document.getElementById("filter_all").checked = true;
        $("#val_search_ID").val("");
        $("#val_search_parent_pin").val("");
        var val_start_time = $("#val_time_start").val();
        var val_end_time = $("#val_time_end").val();
        var check_all = document.getElementById("filter_all").checked;
        var check_vaccinated = document.getElementById('vaccinated').checked;
        var check_registed = document.getElementById('registed').checked;
        if (val_start_time === undefined || val_start_time.trim().length === 0) {
            BootstrapDialog.show({title: "Thông báo", message: "Nhập ngày bắt đầu"});
            return;
        } else if (!tc.isDateValidForFilter(val_start_time)) {
            BootstrapDialog.show({title: "Thông báo", message: "Ngày bắt đầu không đúng định dạng.\n Nhập theo định dạng ngày/tháng/năm"});
            return;
        }
        var hasEndDate = false;
        if (val_end_time.length > 0) {
            if (!tc.isDateValidForFilter(val_end_time)) {
                BootstrapDialog.show({title: "Thông báo", message: "Ngày kết thúc không đúng định dạng.\n Nhập theo định dạng ngày/tháng/năm"});
                return;
            } else {
                hasEndDate = true;
            }
        }
        tc.arrFilter = new Array();
        tc.isFilter = true;
        var arrStartDate = val_start_time.split("/");
        var startDate = new Date(arrStartDate[2], (arrStartDate[1] - 1), arrStartDate[0]);
        if (check_vaccinated) {
            if (hasEndDate) {
                var arrEndDate = val_end_time.split("/");
                var endDate = new Date(arrEndDate[2], (arrEndDate[1] - 1), arrEndDate[0]);
                if (!tc.CompareDate(endDate, startDate)) {
                    BootstrapDialog.show({title: "Thông báo", message: "Ngày kết thúc phải lớn hơn hoặc bằng ngày bắt đầu."});
                    return;
                }
                for (var i = 0; i < tc.arrRegister.length; i++) {
                    var itemSearch = tc.arrRegister[i];
                    //1=vaccinated ;0 =registed
                    if (itemSearch.injectionStatus === 1) {
                        var arrDateSearch = itemSearch.injectionDate.split("/");
                        var dateSearch = new Date(arrDateSearch[2], (arrDateSearch[1] - 1), arrDateSearch[0]);
                        if (tc.CompareDate(dateSearch, startDate) && tc.CompareDate(endDate, dateSearch)) {
                            tc.arrFilter.push(itemSearch);
                        }
                    }

                }
            } else {
                for (var i = 0; i < tc.arrRegister.length; i++) {
                    var itemSearch = tc.arrRegister[i];
                    //1=vaccinated ;0 =registed
                    if (itemSearch.injectionStatus === 1) {
                        var arrDateSearch = itemSearch.injectionDate.split("/");
                        var dateSearch = new Date(arrDateSearch[2], (arrDateSearch[1] - 1), arrDateSearch[0]);
                        if (tc.CompareDate(dateSearch, startDate)) {
                            tc.arrFilter.push(itemSearch);
                        }
                    }

                }
            }
        } else if (check_registed) {
            if (hasEndDate) {
                var arrEndDate = val_end_time.split("/");
                var endDate = new Date(arrEndDate[2], (arrEndDate[1] - 1), arrEndDate[0]);
                if (!tc.CompareDate(endDate, startDate)) {
                    BootstrapDialog.show({title: "Thông báo", message: "Ngày kết thúc phải lớn hơn hoặc bằng ngày bắt đầu."});
                    return;
                }
                for (var i = 0; i < tc.arrRegister.length; i++) {
                    var itemSearch = tc.arrRegister[i];
                    //1=vaccinated ;0 =registed
                    if (itemSearch.injectionStatus === 0) {
                        var arrDateSearch = itemSearch.injectionDate.split("/");
                        var dateSearch = new Date(arrDateSearch[2], (arrDateSearch[1] - 1), arrDateSearch[0]);
                        if (tc.CompareDate(dateSearch, startDate) && tc.CompareDate(endDate, dateSearch)) {
                            tc.arrFilter.push(itemSearch);
                        }
                    }

                }
            } else {
                for (var i = 0; i < tc.arrRegister.length; i++) {
                    var itemSearch = tc.arrRegister[i];
                    //1=vaccinated ;0 =registed
                    if (itemSearch.injectionStatus === 0) {
                        var arrDateSearch = itemSearch.injectionDate.split("/");
                        var dateSearch = new Date(arrDateSearch[2], (arrDateSearch[1] - 1), arrDateSearch[0]);
                        if (tc.CompareDate(dateSearch, startDate)) {
                            tc.arrFilter.push(itemSearch);
                        }
                    }

                }
            }
        } else {

            if (hasEndDate) {
                var arrEndDate = val_end_time.split("/");
                var endDate = new Date(arrEndDate[2], (arrEndDate[1] - 1), arrEndDate[0]);
                if (!tc.CompareDate(endDate, startDate)) {
                    BootstrapDialog.show({title: "Thông báo", message: "Ngày kết thúc phải lớn hơn hoặc bằng ngày bắt đầu."});
                    return;
                }
                for (var i = 0; i < tc.arrRegister.length; i++) {
                    var itemSearch = tc.arrRegister[i];
                    //1=vaccinated ;0 =registed
                    if (itemSearch.injectionStatus === 0) {
                        var arrDateSearch = itemSearch.injectionDate.split("/");
                        var dateSearch = new Date(arrDateSearch[2], (arrDateSearch[1] - 1), arrDateSearch[0]);
                        if (tc.CompareDate(dateSearch, startDate) && tc.CompareDate(endDate, dateSearch)) {
                            tc.arrFilter.push(itemSearch);
                        }
                    }

                }
            } else {
                for (var i = 0; i < tc.arrRegister.length; i++) {
                    var itemSearch = tc.arrRegister[i];
                    //1=vaccinated ;0 =registed
                    var arrDateSearch = itemSearch.injectionDate.split("/");
                    var dateSearch = new Date(arrDateSearch[2], (arrDateSearch[1] - 1), arrDateSearch[0]);
                    if (tc.CompareDate(dateSearch, startDate)) {
                        tc.arrFilter.push(itemSearch);
                    }
                }
            }
        }
        var html_filter = "";
        if (tc.arrFilter.length === 0) {
            $("#content_tiemchung").html(html_filter);
            $("#previous_page").addClass("disabled");
            $("#next_page").addClass("disabled");
            $("#messageSearch").show();
        } else {
            for (var i = 0; i < tc.arrFilter.length; i++) {
                var item = tc.arrFilter[i];
                html_filter += tc.renderItemMonitor(item, i + 1);
            }
            $("#content_tiemchung").html(html_filter);
            $("#previous_page").addClass("disabled");
            $("#next_page").addClass("disabled");
        }
    },
    search: function() {
        $("#val_time_start").val("");
        $("#val_time_end").val("");
        var html_item = "";
//        $("#refresh_search").show();
        $("#messageSearch").hide();
        var val_registrationCode = $("#val_search_ID").val();
        var val_parent_pin = $("#val_search_parent_pin").val();
//        var val_time_start = $("#val_time_start").val();
//        var val_time_end = $("#val_time_end").val();
        var arrSearch = new Array();
        var reg;
        if (val_registrationCode === "" && val_parent_pin === "") {
            BootstrapDialog.show({title: "Thông báo", message: "Nhập mã đăng ký hoặc chứng minh thư"});
            return;
        } else if (val_parent_pin !== "" && val_registrationCode !== "") {
            arrSearch = new RegFilter(["parent_pin", "registrationCode"], "eq").exec(tc.arrRegister, {parent_pin: val_parent_pin, registrationCode: val_registrationCode});
        } else if (val_parent_pin !== "" && val_registrationCode === "") {
            arrSearch = new RegFilter(["parent_pin"], "eq").exec(tc.arrRegister, {parent_pin: val_parent_pin});
        } else if (val_parent_pin === "" && val_registrationCode !== "") {
            arrSearch = new RegFilter(["registrationCode"], "eq").exec(tc.arrRegister, {registrationCode: val_registrationCode});
        }

        if (arrSearch.length === 0) {
            $("#content_tiemchung").html(html_item);
            $("#previous_page").addClass("disabled");
            $("#next_page").addClass("disabled");
            $("#messageSearch").show();
        } else {

            for (var i = 0; i < arrSearch.length; i++) {
                var item = arrSearch[i];
                html_item += tc.renderItemMonitor(item, i + 1);
            }
            $("#content_tiemchung").html(html_item);
            $("#previous_page").addClass("disabled");
            $("#next_page").addClass("disabled");
        }
    },
    checkDate: function(date) {
        var data = date.split("/", 3);
        var day = data[0];
        var month = data[1];
        var year = data[2];
        var currentDate = new Date();
        var currentDay = currentDate.getDate();
        var currentMonth = currentDate.getMonth() + 1;
        var currentYear = currentDate.getFullYear();
        if (!tc.isNumeric(day) || day <= 0 || day > 31) {
            return false;
        }
        if (!tc.isNumeric(month) || month <= 0 || month > 12) {
            return false;
        }

        if (!tc.isNumeric(year) || year < 2014 || year > (currentYear + 1)) {
            return false;
        }
        var anotherDate = new Date(year, month - 1, day);
        var oneDay = 24 * 60 * 60 * 1000;
        var diffDays = Math.abs((currentDate.getTime() - anotherDate.getTime()) / (oneDay));
        if (diffDays < 60 || diffDays > 30 * 24) {
            return false;
        }

        return true;
    },
    checkDateValid: function(date) {
        var data = date.split("/", 3);
        var day = data[0];
        var month = data[1];
        var year = data[2];
        var currentDate = new Date();
//        var currentDay = currentDate.getDate();
//        var currentMonth = currentDate.getMonth() + 1;
//        var currentYear = currentDate.getFullYear();
        if (!tc.isNumeric(day) || day <= 0 || day > 31) {
            return false;
        }
        if (!tc.isNumeric(month) || month <= 0 || month > 12) {
            return false;
        }

        if (!tc.isNumeric(year) || year < 2014) {
            return false;
        }
        var anotherDate = new Date(year, month - 1, day);
        var oneDay = 24 * 60 * 60 * 1000;
        var diffDays = Math.abs((currentDate.getTime() - anotherDate.getTime()) / (oneDay));
        if (diffDays < 0) {
            return false;
        }

        return true;
    },
    isDateValidForFilter: function(date) {
        var data = date.split("/", 3);
        var day = data[0];
        var month = data[1];
        var year = data[2];
        if (!tc.isNumeric(day) || day <= 0 || day > 31) {
            return false;
        }
        if (!tc.isNumeric(month) || month <= 0 || month > 12) {
            return false;
        }

        if (!tc.isNumeric(year) || year.length !== 4) {
            return false;
        }
        return true;
    },
    isNumeric: function(n) {
        return !isNaN(parseFloat(n)) && isFinite(n);
    }
};
function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    var expires = "expires=" + d.toGMTString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
}
function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) === ' ')
            c = c.substring(1);
        if (c.indexOf(name) === 0)
            return c.substring(name.length, c.length);
    }
    return "";
}
function logout() {
    document.cookie = 'keylog=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
    var url = "http://" + window.location.hostname + "/ajx";
    $(".loading").show();
    $.getJSON(url + "?action=logout", function(data) {
        $(".loading").hide();
        if (data.error_code === 0) {
            window.open("/", "_self");
        } else {
            alert(" logout failed.");
        }

    });
//        window.open("/", "_self");
    return false;
}


function deleteAllCookies() {
    var cookies = document.cookie.split(";");

    for (var i = 0; i < cookies.length; i++) {
        var cookie = cookies[i];
        var eqPos = cookie.indexOf("=");
        var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
        document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
    }
}


function login() {
    var url = "http://" + window.location.hostname + "/ajx";
    $(".loading").show();
    var username = $("#username").val();
    var password = $("#password").val();
    if (username === undefined || username.trim().length <= 0 ||
            password === undefined || password.trim().length <= 0) {
        BootstrapDialog.alert("Tài khoản hoặc mật khẩu không đúng");
    }
    $.post(url,
            {
                "action": "login",
                "uname": username,
                "pass": password

            }, {
        "dataType": "json"
    }).done(function(data) {
        $(".loading").hide();
        if (data.error_code === 0) {
            document.cookie = "sessionkey=" + data.sessionKey + ";expires=" + data.expire_sessionkey;
//            BootstrapDialog.alert("Đăng nhập thành công");
            location.href = "/admin/monitor";
//            setTimeout(function() {
//            
//            window.history.back();
//            }, 100);
        } else {
            BootstrapDialog.alert(data.error_message);
        }

    });
}
if (!window.JSON) {
    window.JSON = {
        parse: function(sJSON) {
            return eval("(" + sJSON + ")");
        },
        stringify: function(vContent) {
            if (vContent instanceof Object) {
                var sOutput = "";
                if (vContent.constructor === Array) {
                    for (var nId = 0; nId < vContent.length; sOutput += this.stringify(vContent[nId]) + ",", nId++)
                        ;
                    return "[" + sOutput.substr(0, sOutput.length - 1) + "]";
                }
                if (vContent.toString !== Object.prototype.toString) {
                    return "\"" + vContent.toString().replace(/"/g, "\\$&") + "\"";
                }
                for (var sProp in vContent) {
                    sOutput += "\"" + sProp.replace(/"/g, "\\$&") + "\":" + this.stringify(vContent[sProp]) + ",";
                }
                return "{" + sOutput.substr(0, sOutput.length - 1) + "}";
            }
            return typeof vContent === "string" ? "\"" + vContent.replace(/"/g, "\\$&") + "\"" : String(vContent);
        }
    };
}
//var map = new Object(); // or var map = {};
//map[myKey1] = myObj1;
//map[myKey2] = myObj2;
//
//function get(k) {
//    return map[k];
//}
var Class = function(methods) {
    var klass = function() {
        this.initialize.apply(this, arguments);
    };

    for (var property in methods) {
        klass.prototype[property] = methods[property];
    }

    if (!klass.prototype.initialize)
        klass.prototype.initialize = function() {
        };

    return klass;
};
function RegFilter(attributes, method) {
    this.methods = {
        eq: function(text) {
            return new RegExp('^' + text + '$', 'g');
        },
        contain: function(text) {
            return new RegExp(text, 'g');
        },
        start_with: function(text) {
            return new RegExp('^' + text, 'g');
        },
        end_with: function(text) {
            return new RegExp(text + '$', 'g');
        }
    };

    this.attributes = attributes || [];
    this.method = method || "eq";
    this.exec = function(objects, subject) {
        var self = this;
        var matches = [];
        if (self.attributes.length > 0) {
            for (var i = 0; i < objects.length; i++) {
                var check = true;
                for (var j = 0; j < self.attributes.length; j++) {
                    if (!(objects[i][self.attributes[j]]) || !(subject[self.attributes[j]])) {
                        check = false;
                        break;
                    }
                    var key = self.attributes[j];
                    var text = subject[key];
                    var regexp = self.methods[method](text);
                    if (objects[i][key].match(regexp)) {
                    } else {
                        check = false;
                        break;
                    }
                }
                ;
                if (check === false) {
                    continue;
                }
                matches.push(objects[i]);
            }
        }

        return matches;
    };
    return this;
};

//$(document).ready(function() {
//    tc.getcaptcha();
//});