
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
    static_url: "http://static." + window.location.hostname,
    code: "",
    path_image: "",
    current_page: 1,
    html_footer: "<div style=\" color: #fff;  font-size: 14px; background:  #0099cc repeat scroll 0 0; float: left;  padding: 10px 45px;width: 100%;\">" +
            "            <p>&copy; 2015 - BỆNH VIỆN NHI TRUNG ƯƠNG <br>Địa chỉ: 18/879 La Thành - Đống Đa - Hà Nội <br>Điện thoại: (84.4) 6 273 8573  - Fax: (84.4) 6 273 8573 <br></p>" +
            "        </div>",
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
        } else if (val_cmtnd_parent.length > 25) {
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
}
;

//$(document).ready(function() {
//    tc.getcaptcha();
//});