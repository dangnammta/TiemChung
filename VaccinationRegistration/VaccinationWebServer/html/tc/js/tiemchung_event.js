
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
var tc_event = {
    mapEvent: {},
    arrWorkingTime: new Array(),
    arrHoliday: new Array(),
    url: "http://" + window.location.hostname + "/event/ajx",
    createEvent: function(centreId) {
        var event_name = $("#val_event_name").val();
        var startTimeRegister = $("#val_startTimeRegistration").val();
        var startTimeInjection = $("#val_startTimeInjection").val();
        var val_totalOfDoses = $("#val_totalOfDoses").val();
        var val_totalOfDay = $("#val_totalOfDay").val();
        var t = document.getElementById("val_vaccine");
        var vaccineValue = t.options[t.selectedIndex].value;
        if (event_name === undefined || event_name.trim().length === 0) {
            BootstrapDialog.show({title: "Thông báo", message: "Tên đợt tiêm chủng không được bỏ trống"});
            return;
        }
        if (startTimeRegister === undefined || startTimeRegister.trim().length === 0 || !tc_event.checkDateTimeValid(startTimeRegister)) {
            BootstrapDialog.show({title: "Thông báo", message: "Ngày bắt đầu đăng ký tiêm chủng không hợp lệ"});
            return;
        }
        if (startTimeInjection === undefined || startTimeInjection.trim().length === 0 || !tc_event.checkDateValid(startTimeInjection)) {
            BootstrapDialog.show({title: "Thông báo", message: "Ngày bắt đầu tiêm chủng không hợp lệ"});
            return;
        }
        if (val_totalOfDoses === undefined || val_totalOfDoses.trim().length === 0 || !tc_event.isNumeric(val_totalOfDoses) || val_totalOfDoses <= 0) {
            BootstrapDialog.show({title: "Thông báo", message: "Tổng số liều tiêm phải lớn hơn 0"});
            return;
        }
        if (val_totalOfDay === undefined || val_totalOfDay.trim().length === 0 || !tc_event.isNumeric(val_totalOfDay) || val_totalOfDoses <= 0) {
            BootstrapDialog.show({title: "Thông báo", message: "Tổng số liều tiêm trong một ngày phải lớn hơn 0"});
            return;
        }
        if (tc_event.arrWorkingTime.length === 0) {
            BootstrapDialog.show({title: "Thông báo", message: "Hãy khai báo các khung giờ làm việc"});
            return;
        }
        $(".loading").show();
        $.post(tc_event.url,
                {
                    "action": "createEvent",
                    "centreId": centreId,
                    "event_name": event_name,
                    "startTimeRegister": startTimeRegister.trim(),
                    "startTimeInjection": startTimeInjection.trim(),
                    "val_totalOfDoses": val_totalOfDoses.trim(),
                    "val_totalOfDay": val_totalOfDay.trim(),
                    "val_vaccine": vaccineValue,
                    "working_times": tc_event.arrWorkingTime.toString(),
                    "holidays": tc_event.arrHoliday.toString(),
                    "working_saturday": document.getElementById("holiday_saturday").checked,
                    "working_sunday": document.getElementById("holiday_sunday").checked
                }, {
            "dataType": "json"
        }).done(function(data) {
            $(".loading").hide();
            if (data.error_code === 0) {
                var alertMess = BootstrapDialog.show({title: "Thông báo", message: "Tạo đợt tiêm chủng mới thành công!"});
                setTimeout(function() {
                    alertMess.close();
                    window.open("http://" + window.location.hostname + "/centre/" + centreId, "_self");
                }, 200);
            } else {
                BootstrapDialog.show({title: "Thông báo", message: data.error_message});
                return;
            }


        });
    },
    addWorkingTime: function() {
        var workingStart = $("#val_workingstart").val();
        var workingEnd = $("#val_workingend").val();
        if (workingStart === undefined || workingStart.trim().length === 0) {
            BootstrapDialog.show({title: "Thông báo", message: "Bạn chưa nhập thời gian bắt đầu khung giờ"});
            return;
        }
        if (workingEnd === undefined || workingEnd.trim().length === 0) {
            BootstrapDialog.show({title: "Thông báo", message: "Bạn chưa nhập thời gian kết thúc khung giờ"});
            return;
        }
        var workingTime = "Từ " + workingStart + " đến " + workingEnd;
        if (tc_event.arrWorkingTime.indexOf(workingTime) >= 0) {
            BootstrapDialog.show({title: "Thông báo", message: "Khung giờ đã tồn tại!"});
            return;
        }
        tc_event.arrWorkingTime.push(workingTime);
        var d = new Date();
        var key = d.getMilliseconds();
        $("#ul_working_time").append("<li id=\"time_" + key
                + "\" class=\"select2-search-choice\">    <div>" + workingTime
                + "</div>    <a tabindex=\"-1\" class=\"select2-search-choice-close\" onclick=\"tc_event.removeWorkingTime('" + key + "','" + workingTime + "'); return false;\" href=\"javascript:void(0);\"></a></li>");
    },
    removeWorkingTime: function(timeKey, workingTime) {
        var index = tc_event.arrWorkingTime.indexOf(workingTime);
        if (index >= 0)
            tc_event.arrWorkingTime.splice(index, 1);
        $("#time_" + timeKey).hide();

    },
    addHoliday: function() {
        var val_holiday = $("#val_holiday").val();
        if (val_holiday === undefined || val_holiday.trim().length === 0) {
            BootstrapDialog.show({title: "Thông báo", message: "Bạn chưa nhập ngày nghỉ"});
            return;
        }
        if (tc_event.arrHoliday.indexOf(val_holiday) >= 0) {
            BootstrapDialog.show({title: "Thông báo", message: "Ngày nghỉ đã tồn tại!"});
            return;
        }
        tc_event.arrHoliday.push(val_holiday);
        var d = new Date();
        var key = d.getMilliseconds();
        $("#ul_holiday").append("<li id=\"holiday_" + key
                + "\" class=\"select2-search-choice\">    <div>" + val_holiday
                + "</div>    <a tabindex=\"-1\" class=\"select2-search-choice-close\" onclick=\"tc_event.removeHoliday('" + key + "','" + val_holiday + "'); return false;\" href=\"javascript:void(0);\"></a></li>");
    },
    removeHoliday: function(timeKey, holiday) {
        var index = tc_event.arrHoliday.indexOf(holiday);
        if (index >= 0)
            tc_event.arrHoliday.splice(index, 1);
        $("#holiday_" + timeKey).hide();

    },
    getEventStatus: function() {
        var param = "?action=getEventStatus";
        $.getJSON(tc_event.url + param, function(data) {
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
    cancelCreateEvent: function(centreId) {
        window.open("http://" + window.location.hostname + "/centre/" + centreId, "_self");
    },
    cancelUpdateEvent: function(centreId) {
        window.open("http://" + window.location.hostname + "/centre/" + centreId, "_self");
//        window.back();
    },
    checkUpdateEvent: function(centreId, eventId, status, statusDescription) {
        if (status === 1) {
            location.href = "/centre/" + centreId + "/event/" + eventId;
        } else {
            var alertMess = BootstrapDialog.show({title: "Thông báo", message: "Trạng thái của đợt tiêm chủng là \"" + statusDescription + "\". \n Hệ thống chỉ cho phép chỉnh sửa đợt tiêm khi trạng thái là \"Đang chờ\""});
            setTimeout(function() {
                alertMess.close();
            }, 3000);

            return;
        }
    },
    getConfigUpdateEvent: function(objectEvent) {
        if (objectEvent !== undefined) {
            if (objectEvent.holidays !== undefined) {
                for (var i = 0; i < objectEvent.holidays.length; i++) {
                    tc_event.arrHoliday.push(objectEvent.holidays[i]);
                }
            }
            if (objectEvent.workingTimes !== undefined) {
                for (var i = 0; i < objectEvent.workingTimes.length; i++) {
                    tc_event.arrWorkingTime.push(objectEvent.workingTimes[i]);
                }
            }
        }
    },
//    getEventInfo: function(eventId) {.
//        var param = "?action=getEventInfo?eventId="+eventId;
//        $.getJSON(tc_event.url + param, function(data) {
//            if (data.error_code === 20) {
//                location.href = "/register";
//                return false;
//            } else {
//                if (data.error_code === 21) {
//                    var alertMess = BootstrapDialog.show({title: "Thông báo", message: "Hiện tại chưa tới giờ đăng ký, Vui lòng quay lại sau"});
//                    setTimeout(function() {
//                        alertMess.close();
//                    }, 5000);
//                    return false;
//                } else if (data.error_code === 11) {
//                    var alertMess = BootstrapDialog.show({title: "Thông báo", message: "Số lượng Vắcxin của đợt tiêm chủng này đã hết, hẹn quý khách đợt tiêm tiếp theo."});
//                    $("#btn_registration").hide();
//                    $("#limit_message").show();
//                    setTimeout(function() {
//                        alertMess.close();
//                    }, 5000);
//                    return false;
//                } else {
//                    BootstrapDialog.show({title: "Thông báo", message: "Hiện tại không có đợt đăng ký tiêm chủng, Vui lòng quay lại sau"});
//                    return false;
//                }
//
//            }
//
//        });
//    },
    updateEventInfo: function(centreId, eventId) {
        var event_name = $("#val_event_name").val();
        var startTimeRegister = $("#val_startTimeRegistration").val();
        var startTimeInjection = $("#val_startTimeInjection").val();
        var val_totalOfDoses = $("#val_totalOfDoses").val();
        var val_totalOfDay = $("#val_totalOfDay").val();
        var t = document.getElementById("val_vaccine");
        var vaccineValue = t.options[t.selectedIndex].value;
        if (event_name === undefined || event_name.trim().length === 0) {
            BootstrapDialog.show({title: "Thông báo", message: "Tên đợt tiêm chủng không được bỏ trống"});
            return;
        }
        if (startTimeRegister === undefined || startTimeRegister.trim().length === 0 || !tc_event.checkDateTimeValid(startTimeRegister)) {
            BootstrapDialog.show({title: "Thông báo", message: "Ngày bắt đầu đăng ký tiêm chủng không hợp lệ"});
            return;
        }
        if (startTimeInjection === undefined || startTimeInjection.trim().length === 0 || !tc_event.checkDateValid(startTimeInjection)) {
            BootstrapDialog.show({title: "Thông báo", message: "Ngày bắt đầu tiêm chủng không hợp lệ"});
            return;
        }
        if (val_totalOfDoses === undefined || val_totalOfDoses.trim().length === 0 || !tc_event.isNumeric(val_totalOfDoses) || val_totalOfDoses <= 0) {
            BootstrapDialog.show({title: "Thông báo", message: "Tổng số liều tiêm phải lớn hơn 0"});
            return;
        }
        if (val_totalOfDay === undefined || val_totalOfDay.trim().length === 0 || !tc_event.isNumeric(val_totalOfDay) || val_totalOfDoses <= 0) {
            BootstrapDialog.show({title: "Thông báo", message: "Tổng số liều tiêm trong một ngày phải lớn hơn 0"});
            return;
        }
        if (tc_event.arrWorkingTime.length === 0) {
            BootstrapDialog.show({title: "Thông báo", message: "Hãy khai báo các khung giờ làm việc"});
            return;
        }
        $(".loading").show();
        $.post(tc_event.url,
                {
                    "action": "updateEvent",
                    "centreId": centreId,
                    "eventId": eventId,
                    "event_name": event_name,
                    "startTimeRegister": startTimeRegister.trim(),
                    "startTimeInjection": startTimeInjection.trim(),
                    "val_totalOfDoses": val_totalOfDoses.trim(),
                    "val_totalOfDay": val_totalOfDay.trim(),
                    "val_vaccine": vaccineValue,
                    "working_times": tc_event.arrWorkingTime.toString(),
                    "holidays": tc_event.arrHoliday.toString(),
                    "working_saturday": document.getElementById("holiday_saturday").checked,
                    "working_sunday": document.getElementById("holiday_sunday").checked
                }, {
            "dataType": "json"
        }).done(function(data) {
            $(".loading").hide();
            if (data.error_code === 0) {
                var alertMess = BootstrapDialog.show({title: "Thông báo", message: "Cập nhật thông tin đợt tiêm chủng thành công!"});
                setTimeout(function() {
                    alertMess.close();
                    window.open("http://" + window.location.hostname + "/centre/" + centreId, "_self");
                }, 200);
            } else {
                BootstrapDialog.show({title: "Thông báo", message: data.error_message});
                return;
            }


        });

    },
    deleteEvent: function(eventId) {
        BootstrapDialog.show({
            title: "Xác nhận",
            message: "Bạn có chắc chắn muốn xóa đợt tiêm chủng này không?",
            buttons: [{
                    label: "Đồng ý",
                    cssClass: 'btn-primary',
//                    hotkey: 13, // Enter.
                    action: function(dialogItself) {
                        $(".loading").show();
                        dialogItself.close();
                        var param = "?action=remove_event&id=" + eventId;
                        $.getJSON(tc_event.url + param, function(data) {
                            $(".loading").hide();
                            if (data === null || data.error_code !== 0) {
                                BootstrapDialog.show({
                                    title: "Thông báo",
                                    message: data.error_message
                                });
                                return false;
                            } else {
                                var alert_popup = BootstrapDialog.alert("Xóa đợt tiêm chủng thành công!");
                                $("#event_" + eventId).empty();
                                $("#event_" + eventId).hide();
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
    getEvents: function(centreId) {
        var param = "?action=getEvents";
        $(".loading").show();
        $.getJSON(tc_event.url + param, function(data) {
            $(".loading").hide();
            if (data.error_code === 0) {
                var html_item = "";
                for (var i = 0; i < data.items.length; i++) {
                    var item = data.items[i];
                    tc_event.mapEvent[item.eventId + ""] = item;
                    html_item += "<tr id=\"event_" + item.eventId + "\">"
                            + "<th scope=\"row\">" + (i + 1) + "</th>"
                            + "<td ><a id=\"name_event_" + item.eventId + "\" href=\"/event/" + item.eventId + "\" >" + item.eventName + "</a></td>"
                            + "<td id=\"total_injection_" + item.eventId + "\">" + item.vaccineName + "</td>"
                            + "<td id=\"startTime_" + item.eventId + "\">" + item.startTimeRegistration + "</td>"
                            + "<td><div>"
                            + "<button style=\"margin:3px;\" onclick=\"tc_event.updateEventInfo('" + item.eventId + "','" + centreId + "'); return false;\"  class=\"btn btn-primary\" type=\"button\">Sửa</button>"
                            + "<button style=\"margin:3px;\" onclick=\"tc_event.deleteEvent('" + item.eventId + "','" + centreId + "'); return false;\" class=\"btn btn-default\" type=\"button\">Xóa</button>"
                            + " </div></td>"
                            + "</tr>";
                }
                $("#content_event").html(html_item);
            } else {
                BootstrapDialog.show({
                    title: "Thông báo",
                    message: data.error_message
                });
                return;
            }
        });
    },
    addVaccine: function(centreId) {
        BootstrapDialog.show({
            title: "Thêm loại Vắcxin",
            closable: true,
            closeByBackdrop: false,
            closeByKeyboard: false,
            message: "                   <form>" +
                    "                      <div class=\"form-group\">" +
                    "                          <label>Tên Vắcxin:</label>" +
                    "                          <input id=\"vaccine_name\" class=\"form-control\" value=\"\" >" +
                    "                      </div>" +
                    "                      <div class=\"form-group\">" +
                    "                          <label>Mô tả:</label>" +
                    "                          <input id=\"description\" class=\"form-control\" value=\"\" >" +
                    "                      </div>" +
                    "                  </form>",
            buttons: [{
                    label: "Thêm",
                    cssClass: 'btn-primary',
//                        hotkey: 13, // Enter.
                    action: function(dialogItself) {
                        var vaccine_name = $("#vaccine_name").val();
                        var description = $("#description").val();
                        if (vaccine_name.trim().length <= 0) {
                            BootstrapDialog.show({title: "Thông báo", message: "Bạn chưa nhập tên Vắcxin"});
                            return;
                        }
                        $(".loading").show();
                        $.post(tc_event.url,
                                {
                                    "action": "addVaccine",
                                    "centreId": centreId,
                                    "vaccineName": vaccine_name.trim(),
                                    "description": description.trim()

                                }, {
                            "dataType": "json"
                        }).done(function(data) {
                            $(".loading").hide();
                            if (data.error_code === 0) {
                                $("#val_vaccine").append("<option value=\"" + data.vaccineId + "\" selected>" + vaccine_name + "</option>")
                                var alertMess = BootstrapDialog.alert("Thêm Vắcxin thành công!");
                                setTimeout(function() {
                                    alertMess.close();
                                }, 200);
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
    checkDateValid: function(stringDate) {
        var data = stringDate.trim().split("/", 3);
        if (data.length === 3) {
            var day = data[0];
            var month = data[1];
            var year = data[2];
            var currentDate = new Date();
            if (!tc_event.isNumeric(day) || day <= 0 || day > 31) {
                return false;
            }
            if (!tc_event.isNumeric(month) || month <= 0 || month > 12) {
                return false;
            }

            if (!tc_event.isNumeric(year) || year < 2014) {
                return false;
            }
            var anotherDate = new Date(year, month - 1, day, 0, 0, 0, 0);

            var oneDay = 24 * 60 * 60 * 1000;
            var diffDays = Math.abs((currentDate.getTime() - anotherDate.getTime()) / (oneDay));
            if (diffDays < 0) {
                return false;
            }
            return true;
        } else {
            return false;
        }



    },
    checkDateTimeValid: function(stringDateTime) {
        var data = stringDateTime.trim().split(" ");
        var date = "";
        var time = "";
        if (data.length >= 2) {
            if (data[0].trim().length === 10 && data[1].trim().length === 5) {
                date = data[0].trim();
                time = data[1].trim();
            } else {
                return false;
            }
        } else {
            return false;
        }
        date = date.split("/", 3);
        time = time.split(":", 2);
        var day = date[0];
        var month = date[1];
        var year = date[2];
        var hour = time[0];
        var minute = time[1];
        var currentDate = new Date();
//        var currentDay = currentDate.getDate();
//        var currentMonth = currentDate.getMonth() + 1;
//        var currentYear = currentDate.getFullYear();
        if (!tc_event.isNumeric(day) || day <= 0 || day > 31) {
            return false;
        }
        if (!tc_event.isNumeric(month) || month <= 0 || month > 12) {
            return false;
        }

        if (!tc_event.isNumeric(year) || year < 2014) {
            return false;
        }
//        new Date()
//        new Date(milliseconds)
//        new Date(dateString)
//        new Date(year, month, day, hours, minutes, seconds, milliseconds)
        var anotherDate = new Date(year, month - 1, day, hour, minute, 0, 0);

        var oneDay = 24 * 60 * 60 * 1000;
        var diffDays = Math.abs((currentDate.getTime() - anotherDate.getTime()) / (oneDay));
        if (diffDays < 0) {
            return false;
        }

        return true;
    },
    isNumeric: function(n) {
        return !isNaN(parseFloat(n)) && isFinite(n);
    },
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
