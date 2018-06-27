fisConfig = {
    viewerName: "",
    viewerId: "",
    viewerFullName: "",
    email: "",
    importable: false,
    exportable: false,
    currentTime: "",
    type: "",
    getconfig: function() {
        var url = "http://" + window.location.hostname + "/dip/user/ajx";
        var param = "?action=getconfig";
        $.getJSON(url + param, function(data) {
            if (data === undefined || data.error_code !== 0) {
                return false;
            } else {
                fisConfig.viewerId = data.item.uid;
                fisConfig.viewerName = data.item.username;
                fisConfig.viewerFullName = data.item.fullname;
                fisConfig.currentTime = data.item.currentTime;
                fisConfig.email = data.item.email;
                fisConfig.importable = data.item.importable;
                fisConfig.exportable = data.item.exportable;
            }
        });
    }
};
$(document).ready(function() {
    fisConfig.getconfig();
});