
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
    url: "http://" + window.location.hostname + "/centre/ajx",
    createCentre: function() {
        BootstrapDialog.show({
            title: "Đăng ký Trung tâm tiêm chủng",
            closable: true,
            closeByBackdrop: false,
            closeByKeyboard: false,
            message: "                   <form>" +
                    "                      <div class=\"form-group\">" +
                    "                          <label>Tên trung tâm:</label>" +
                    "                          <input id=\"name_centre\" class=\"form-control\" value=\"\" >" +
                    "                      </div>" +
                    "                      <div class=\"form-group\">" +
                    "                          <label>Địa chỉ website:</label>" +
                    "                          <input id=\"domain_centre\" class=\"form-control\" value=\"\" >" +
                    "                      </div>" +
                    "                      <div class=\"form-group\">" +
                    "                          <label>Điện thoại:</label>" +
                    "                          <input id=\"phone_centre\" class=\"form-control\" value=\"\"  >" +
                    "                      </div>" +
                    "                      <div class=\"form-group\">" +
                    "                          <label>Địa chỉ:</label>" +
                    "                          <textarea id=\"address_centre\" class=\"form-control\" value=\"\" row=\"3\" ></textarea>" +
                    "                      </div>" +
                    "                  </form>",
            buttons: [{
                    label: "Đăng ký",
                    cssClass: 'btn-primary',
//                        hotkey: 13, // Enter.
                    action: function(dialogItself) {
                        var name_centre = $("#name_centre").val();
                        var address_centre = $("#address_centre").val();
                        var phone_centre = $("#phone_centre").val();
                        var domain_centre = $("#domain_centre").val();
                        if (name_centre.trim().length <= 0) {
                            BootstrapDialog.show({title: "Thông báo", message: "Tên trung tâm không được bỏ trống"});
                            return;
                        }
                        if (domain_centre.trim().length <= 0) {
                            BootstrapDialog.show({title: "Thông báo", message: "Địa chỉ website không được bỏ trống"});
                            return;
                        }
                        if (phone_centre.trim().length <= 0) {
                            BootstrapDialog.show({title: "Thông báo", message: "Điện thoại liên lạc không được bỏ trống"});
                            return;
                        }
                        if (address_centre.trim().length <= 0) {
                            BootstrapDialog.show({title: "Thông báo", message: "Địa chỉ trung tâm không được bỏ trống"});
                            return;
                        }
                        $(".loading").show();
                        $.post(tc.url,
                                {
                                    "action": "createCentre",
                                    "name_centre": name_centre,
                                    "address_centre": address_centre,
                                    "phone_centre": phone_centre,
                                    "domain_centre": domain_centre

                                }, {
                            "dataType": "json"
                        }).done(function(data) {
                            $(".loading").hide();
                            if (data.error_code === 0) {
                                var alertMess = BootstrapDialog.alert("Đăng ký thành công");
                                setTimeout(function() {
                                    alertMess.close();
                                }, 3000);
                                var html_current = $("#content_centre").html();
                                var html_item = "<tr id=\"center_" + data.centreId + "\">"
                                        + "<th scope=\"row\">#</th>"
                                        + "<td ><a id=\"name_centre_" + data.centreId + "\" href=\"/centre/" + data.centreId + "\" >" + name_centre + "</a></td>"
                                        + "<td id=\"domain_centre_" + data.centreId + "\">" + domain_centre + "</td>"
                                        + "<td id=\"phone_centre_" + data.centreId + "\">" + phone_centre + "</td>"
                                        + "<td id=\"address_centre_" + data.centreId + "\">" + address_centre + "</td>"
                                        + "<td><div>"
                                        + "<button style=\"margin:3px;\" onclick=\"tc.updateCentreInfo(" + data.centreId + "); return false;\"  class=\"btn btn-primary\" type=\"button\">Sửa</button>"
                                        + "<button style=\"margin:3px;\" onclick=\"tc.deleteCentre('" + data.centreId + "'); return false;\" class=\"btn btn-primary\" type=\"button\">Xóa</button>"
                                        + " </div></td>"
                                        + "</tr>";
                                $("#content_centre").html(html_item + html_current);
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
    updateCentreInfo: function(centreId) {
        var name_centre = $("#name_centre_" + centreId).html();
        var phone_centre = $("#phone_centre_" + centreId).html();
        var address_centre = $("#address_centre_" + centreId).html();
        var domain_centre = $("#domain_centre_" + centreId).html();
        BootstrapDialog.show({
            title: "Cập nhật thông tin Trung tâm tiêm chủng",
            closable: true,
            closeByBackdrop: false,
            closeByKeyboard: false,
            message: "                   <form>" +
                    "                      <div class=\"form-group\">" +
                    "                          <label>Tên trung tâm:</label>" +
                    "                          <input id=\"name_centre\" class=\"form-control\" value=\"" + name_centre + "\" >" +
                    "                      </div>" +
                    "                      <div class=\"form-group\">" +
                    "                          <label>Địa chỉ website:</label>" +
                    "                          <input id=\"domain_centre\" class=\"form-control\" value=\"" + domain_centre + "\" >" +
                    "                      </div>" +
                    "                      <div class=\"form-group\">" +
                    "                          <label>Điện thoại:</label>" +
                    "                          <input id=\"phone_centre\" class=\"form-control\" value=\"" + phone_centre + "\"  >" +
                    "                      </div>" +
                    "                      <div class=\"form-group\">" +
                    "                          <label>Địa chỉ:</label>" +
                    "                          <textarea id=\"address_centre\" class=\"form-control\" value=\"\" row=\"3\">" + address_centre + "</textarea>" +
                    "                      </div>" +
                    "                  </form>",
            buttons: [{
                    label: "Cập nhật",
                    cssClass: 'btn-primary',
//                        hotkey: 13, // Enter.
                    action: function(dialogItself) {
                        var name_centre = $("#name_centre").val();
                        var phone_centre = $("#phone_centre").val();
                        var address_centre = $("#address_centre").val();
                        var domain_centre = $("#domain_centre").val();
                        if (name_centre.trim().length <= 0) {
                            BootstrapDialog.show({title: "Thông báo", message: "Tên trung tâm không được bỏ trống"});
                            return;
                        }
                        if (domain_centre.trim().length <= 0) {
                            BootstrapDialog.show({title: "Thông báo", message: "Địa chỉ website không được bỏ trống"});
                            return;
                        }
                        if (phone_centre.trim().length <= 0) {
                            BootstrapDialog.show({title: "Thông báo", message: "Điện thoại liên lạc không được bỏ trống"});
                            return;
                        }
                        if (address_centre.trim().length <= 0) {
                            BootstrapDialog.show({title: "Thông báo", message: "Địa chỉ trung tâm không được bỏ trống"});
                            return;
                        }
                        $(".loading").show();
                        $.post(tc.url,
                                {
                                    "action": "updateCentre",
                                    "id": centreId,
                                    "name_centre": name_centre.trim(),
                                    "address_centre": address_centre.trim(),
                                    "phone_centre": phone_centre.trim(),
                                    "domain_centre": domain_centre.trim()

                                }, {
                            "dataType": "json"
                        }).done(function(data) {
                            $(".loading").hide();
                            if (data.error_code === 0) {
                                var alertMess = BootstrapDialog.alert("Cập nhật thành công");
                                setTimeout(function() {
                                    alertMess.close();
                                }, 3000);

                                $("#name_centre_" + centreId).html(name_centre);
                                $("#phone_centre_" + centreId).html(phone_centre);
                                $("#address_centre_" + centreId).html(address_centre);
                                $("#domain_centre_" + centreId).html(domain_centre);
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
    deleteCentre: function(centreId) {
        BootstrapDialog.show({
            title: "Xác nhận",
            message: "Bạn có chắc chắn muốn xóa trung tâm này không?",
            buttons: [{
                    label: "Đồng ý",
                    cssClass: 'btn-primary',
                    hotkey: 13, // Enter.
                    action: function(dialogItself) {
                        $(".loading").show();
                        dialogItself.close();
                        var param = "?action=remove_centre&id=" + centreId;
                        $.getJSON(tc.url + param, function(data) {

                            $(".loading").hide();
                            if (data === null || data.error_code !== 0) {
                                BootstrapDialog.show({
                                    title: "Thông báo",
                                    message: data.error_message
                                });
                                return false;
                            } else {
                                var alert_popup = BootstrapDialog.alert("Xóa trung tâm thành công!");
                                $("#center_" + centreId).empty();
                                $("#center_" + centreId).hide();
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
    getCentres: function() {
        var param = "?action=getCentres";
        $(".loading").show();
        $.getJSON(tc.url + param, function(data) {
            $(".loading").hide();
            if (data.error_code === 0) {
                var html_item = "";
                for (var i = 0; i < data.items.length; i++) {
                    var item = data.items[i];
                    html_item += "<tr id=\"center_" + item.centreId + "\">"
                            + "<th scope=\"row\">" + (i + 1) + "</th>"
                            + "<td ><a id=\"name_centre_" + item.centreId + "\"  href=\"/centre/" + item.centreId + "\" >" + item.name_centre + "</a></td>"
                            + "<td id=\"domain_centre_" + item.centreId + "\">DOMAIN</a></td>"
                            + "<td id=\"phone_centre_" + item.centreId + "\">" + item.phone_centre + "</td>"
                            + "<td id=\"address_centre_" + item.centreId + "\">" + item.address_centre + "</td>"
                            + "<td><div>"
                            + "<button style=\"margin:3px;\" onclick=\"tc.updateCentreInfo(" + item.centreId + "); return false;\"  class=\"btn btn-primary\" type=\"button\">Sửa</button>"
                            + "<button style=\"margin:3px;\" onclick=\"tc.deleteCentre('" + item.centreId + "'); return false;\" class=\"btn btn-primary\" type=\"button\">Xóa</button>"
                            + " </div></td>"
                            + "</tr>";
                }
                $("#content_centre").html(html_item);
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
                    "                          <textarea row=\"3\" id=\"description\" class=\"form-control\"></textarea>" +
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
                        $.post(tc.url,
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
                                var html_item = "<tr id=\"vaccine_" + data.vaccineId + "\">"
                                        + "<th scope=\"row\">#</th>"
                                        + "<td id=\"name_vaccine_" + data.vaccineId + "\" >" + vaccine_name + "</td>"
                                        + "<td id=\"description_" + data.vaccineId + "\">" + description + "</td>"
                                        + "<td><div>"
                                        + "<button style=\"margin:3px;\" onclick=\"tc.updateVaccine(" + centreId + ",'" + data.vaccineId + "'); return false;\"  class=\"btn btn-primary\" type=\"button\">Sửa</button>"
                                        + "<button style=\"margin:3px;\" onclick=\"tc.deleteVaccine(" + centreId + ",'" + data.vaccineId + "'); return false;\" class=\"btn btn-primary\" type=\"button\">Xóa</button>"
                                        + " </div></td>"
                                        + "</tr>";
                                $("#content_vaccine").prepend(html_item);
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
    updateVaccine: function(centreId, vaccineId) {
        var name_vaccine = $("#name_vaccine_" + vaccineId).html();
        var description_vaccine = $("#description_" + vaccineId).html();
        BootstrapDialog.show({
            title: "Cập nhật thông tin Vắcxin",
            closable: true,
            closeByBackdrop: false,
            closeByKeyboard: false,
            message: "                   <form>" +
                    "                      <div class=\"form-group\">" +
                    "                          <label>Tên Vắcxin:</label>" +
                    "                          <input id=\"vaccine_name\" class=\"form-control\" value=\"" + name_vaccine + "\" >" +
                    "                      </div>" +
                    "                      <div class=\"form-group\">" +
                    "                          <label>Mô tả:</label>" +
                    "                          <textarea row=\"3\" id=\"description\" class=\"form-control\"  >" + description_vaccine + "</textarea>" +
                    "                      </div>" +
                    "                  </form>",
            buttons: [{
                    label: "Thêm",
                    cssClass: 'btn-primary',
//                        hotkey: 13, // Enter.
                    action: function(dialogItself) {
                        var vaccine_name_new = $("#vaccine_name").val();
                        var description_new = $("#description").val();
                        if (vaccine_name_new.trim().length <= 0) {
                            BootstrapDialog.show({title: "Thông báo", message: "Tên Vắcxin không được bỏ trống"});
                            return;
                        }
                        $(".loading").show();
                        $.post(tc.url,
                                {
                                    "action": "updateVaccine",
                                    "centreId": centreId,
                                    "vaccineId": vaccineId,
                                    "vaccineName": vaccine_name_new.trim(),
                                    "description": description_new.trim()

                                }, {
                            "dataType": "json"
                        }).done(function(data) {
                            $(".loading").hide();
                            if (data.error_code === 0) {
                                var alertMess = BootstrapDialog.alert("Cập nhật Vắcxin thành công!");
                                setTimeout(function() {
                                    alertMess.close();
                                }, 200);
                                $("#name_vaccine_" + vaccineId).html(vaccine_name_new);
                                $("#description_" + vaccineId).html(description_new);
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
    deleteVaccine: function(centreId, vaccineId) {
        BootstrapDialog.show({
            title: "Xác nhận",
            message: "Bạn có chắc chắn muốn xóa Vắcxin này không?",
            buttons: [{
                    label: "Đồng ý",
                    cssClass: 'btn-primary',
                    hotkey: 13, // Enter.
                    action: function(dialogItself) {
                        $(".loading").show();
                        dialogItself.close();
                        var param = "?action=removeVaccine&vaccineId=" +vaccineId+"&centreId="+ centreId;
                        $.getJSON(tc.url + param, function(data) {

                            $(".loading").hide();
                            if (data === null || data.error_code !== 0) {
                                BootstrapDialog.show({
                                    title: "Thông báo",
                                    message: data.error_message
                                });
                                return false;
                            } else {
                                var alert_popup = BootstrapDialog.alert("Xóa Vắcxin thành công!");
                                $("#vaccine_" + vaccineId).empty();
                                $("#vaccine_" + vaccineId).hide();
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

//$(document).ready(function() {
//    tc.getcaptcha();
//});
