
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
    cancelCreateEvent: function() {
        window.open("http://" + window.location.hostname, "_self");
    },
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
    createEvent: function() {
        var startTimeRegister = $("#val_event_name").val();
        var startTimeRegister = $("#val_startTimeRegistration").val();
        var startTimeInjection = $("#val_startTimeInjection").val();
        var val_totalOfDoses = $("#val_totalOfDoses").val();
        var val_totalOfDay = $("#val_totalOfDay").val();
        var val_vaccine = $("#val_vaccine").val();
        if (startTimeRegister === undefined || startTimeRegister === "" || !tc.checkDateValid(startTimeRegister)) {
            BootstrapDialog.show({title: "Thông báo", message: "Ngày bắt đầu đăng ký tiêm chủng không hợp lệ"});
            return;
        }
        if (startTimeInjection === undefined || startTimeInjection === "" || !tc.checkDateValid(startTimeInjection)) {
            BootstrapDialog.show({title: "Thông báo", message: "Ngày bắt đầu tiêm chủng không hợp lệ"});
            return;
        }
        if (val_totalOfDoses === undefined || val_totalOfDoses === "" || !tc.isNumeric(val_totalOfDoses) || val_totalOfDoses <= 0) {
            BootstrapDialog.show({title: "Thông báo", message: "Tổng số liều tiêm phải lớn hơn 0"});
            return;
        }
        if (val_totalOfDay === undefined || val_totalOfDay === "" || !tc.isNumeric(val_totalOfDay) || val_totalOfDoses <= 0) {
            BootstrapDialog.show({title: "Thông báo", message: "Tổng số liều tiêm trong một ngày phải lớn hơn 0"});
            return;
        }
        $(".loading").show();
        $.post(tc.url,
                {
                    "action": "createEvent",
                    "startTimeRegister": startTimeRegister,
                    "startTimeInjection": startTimeInjection,
                    "val_totalOfDoses": val_totalOfDoses,
                    "val_totalOfDay": val_totalOfDay,
                    "val_vaccine": val_vaccine
                }, {
            "dataType": "json"
        }).done(function(data) {
            $(".loading").hide();
            if (data.error_code === 0) {
                var alertMess = BootstrapDialog.show({title: "Thông báo", message: "Tạo đợt tiêm chủng mới thành công!"});
                setTimeout(function() {
                    alertMess.close();
                    window.open("http://" + window.location.hostname, "_self");
                }, 100);
            } else {
                BootstrapDialog.show({title: "Thông báo", message: data.error_message});
                return;
            }


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