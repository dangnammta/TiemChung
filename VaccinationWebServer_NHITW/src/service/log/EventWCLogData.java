package service.log;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
//import service.localcache.LocalUserProfile;

public class EventWCLogData {

    public static class ActionStringId {
        //ZMController

        public static final String VIEW_MATCH_PAGE = "101";
        public static final String VIEW_TOTAL_GOAL_PAGE = "102";
        public static final String VIEW_CHAMPION_PAGE = "103";
        public static final String VIEW_TOP_PAGE = "104";
        public static final String VIEW_SCHEDULE_PAGE = "105";
        public static final String VIEW_HISTORY_PAGE = "106";
        public static final String VIEW_HISTORY_RANKING = "107";
        public static final String ACTION_BETTING_MATCH = "108";
        public static final String ACTION_BETTING_TOTAL_GOAL = "109";
        public static final String ACTION_BETTING_CHAMPION = "110";
        public static final String ACTION_BETTING_ALL = "111";
        public static final String ACTION_SHAREFEED = "112";
        public static final String ACTION_SHAREPOINT = "113";
        public static final String ACTION_SHARE_FACEBOOK = "114";

        //Admin controller
        public static final String ADMIN_SET_RESULT_MATCH = "201";
        public static final String ADMIN_SET_COIN_USER = "202";
        public static final String ADMIN_BONUS_COIN_USER = "203";
    }
    public static final String CATEGORY_REQUEST = "ZME_EVENT_TOUCH_WC2014_REQUEST";
    public static String LOG_REQUEST_TEMPLATE = "%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s";
    //request_time, server_ip, client_ip, request_domain, request_uri, execution_time, username, id, appdata
    //* execution_time tinh bang milisecond
    //* request_time: unix time stamp
    //* client_ip: x_forwarded_for, remote_host....
    public static final String CATEGORY_ACTION = "ZME_EVENT_TOUCH_WC2014_ACTION";
    public static final String LOG_ACTION_TEMPLATE = "%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s";
    // time, serverIp, clientIp, domain, exectionTime, username, actionId, appData
    //* appdata=??? tam thoi de trong
    //* execution_time: milisecond
    //* request_time: unix time stamp

    public static EventWCLogData getLog(HttpServletRequest req) {
        Object attr = req.getAttribute("logData");
        if (attr == null) {
            attr = new EventWCLogData();
            req.setAttribute("logData", attr);
        }
        try {
            return (EventWCLogData) attr;
        } catch (Exception e) {
            return null;
        }
    }
    private EventWCLogData.LogRequestData _logRequest = null;
    private List<EventWCLogData.LogActionData> _logActionList = new ArrayList<EventWCLogData.LogActionData>();
    private EventWCLogData.LogActionData _currentLogActionData = null;
//    private AppContext context;

    public EventWCLogData.LogRequestData getCurrentLogRequest() {
        if (_logRequest == null) {
            _logRequest = new EventWCLogData.LogRequestData();
        }
        return _logRequest;
    }

    public EventWCLogData.LogActionData getCurrentLogAction() {
        if (_currentLogActionData == null) {
            _currentLogActionData = new EventWCLogData.LogActionData();
        }
        return _currentLogActionData;
    }

    public void finishLogAction() {
        if (_currentLogActionData != null) {
            _currentLogActionData.finish();
            _logActionList.add(_currentLogActionData);
        }
        _currentLogActionData = null;
    }

    public String getLogRequestData() {
        if (_logRequest != null) {
            return _logRequest.getData();
        }
        return "";
    }

    public String getLogActionData() {
        String data = "";
        for (int i = 0; i < _logActionList.size(); i++) {
            EventWCLogData.LogActionData logData = _logActionList.get(i);
            if (logData != null && logData.getData().length() > 0) {
                if (data.length() == 0) {
                    data = logData.getData();
                } else {
                    data = data + "\n" + logData.getData();
                }
            } else {
                System.out.println("logData == null");
            }
        }
        return data;
    }

    public static class LogRequestData {

        public LogRequestData() {
            timeBegin = System.currentTimeMillis();
            requestTime = String.valueOf(timeBegin / 1000); // chuyen tu milisecond ve second
        }

        public void finish() {
            long currentTime = System.currentTimeMillis();
            long exTime = currentTime - timeBegin;
            executionTime = exTime + "";
        }

//        public void setData2LogReq(HttpServletRequest req) {
//            AppContext ctx;
//            try {
//                ctx = (AppContext) req.getAttribute("app.context");
//            } catch (Exception ex) {
//                return;
//            }
//            serverIp = ctx.serverIp;
//            requestDomain = ctx.requestDomain;
//            requestUri = ctx.requestUri;
//            clientIp = ctx.clientIp;
//            username = ctx.username;
//			ostype = ctx.ostype;
//			
//
//        }
        public String getData() {
            String data = String.format(LOG_REQUEST_TEMPLATE, requestTime, serverIp, clientIp, requestDomain, requestUri, executionTime, username, id, appData, ostype);
            return data;
        }
        //request_time, server_ip, client_ip, request_domain, request_uri, execution_time, username, appdata
        private long timeBegin;
        public String serverIp = "";
        public String requestDomain = "";
        public String requestUri = "";
        public String requestTime = "0";
        public String executionTime = "0";
        public String clientIp = "";
        public String username = "";
        public String id = "";
        public String appData = "";
        public String ostype = "";
    }

    public static class LogActionData {

        public LogActionData() {
            timeBegin = System.currentTimeMillis();
            time = String.valueOf(timeBegin / 1000);

        }

        public void finish() {
            long currentTime = System.currentTimeMillis();
            long exTime = currentTime - timeBegin;
            exectionTime = String.valueOf(exTime);
        }

//        public void setData2LogAction(HttpServletRequest req, String actionId, String coin, String matchId, String typeBetting, String teamId) {
//            this.actionId = actionId;
//            AppContext ctx;
//            try {
//                ctx = (AppContext) req.getAttribute("app.context");
//            } catch (Exception ex) {
//                return;
//            }
//            serverIp = ctx.serverIp;
//            domain = ctx.requestDomain;
//            clientIp = ctx.clientIp;
//            username = ctx.username;
//            userId = String.valueOf(ctx.userid);
//            this.coin = coin;
//            this.matchId = matchId;
//            this.typeBetting = typeBetting;
//            this.teamId = teamId;
//			ostype =ctx.ostype;
//
//        }
//        public void setData2LogAction(HttpServletRequest req, int ownerid, String actionId, String bonusCoin, String matchId, String totalCoin) {
//            this.actionId = actionId;
//            AppContext ctx = null;
//            try {
//                ctx = (AppContext) req.getAttribute("app.context");
//            } catch (Exception ex) {
//            }
//            if (ctx != null) {
//                try {
//                    serverIp = ctx.serverIp;
//                } catch (Exception ex) {
//                }
//                try {
//                    domain = ctx.requestDomain;
//                } catch (Exception ex) {
//                }
//                try {
//                    clientIp = ctx.clientIp;
//                } catch (Exception ex) {
//                }
//                try {
//                    username = ctx.username;
//                } catch (Exception ex) {
//                }
////                try {
////                    userId = String.valueOf(ctx.userid);
////                } catch (Exception ex) {
////                }
//				ostype = ctx.ostype;
//            }
//
//            try {
//                userId = String.valueOf(ownerid);
////                String userName = LocalUserProfile.instance().getCache(ownerid).userName;
////                username = userName;
//            } catch (Exception ex) {
//
//            }
//
//            this.coin = bonusCoin;
//            this.totalCoin = totalCoin;
//            this.matchId = matchId;
//
//        }
//		
        public void setData2LogAction(int ownerid, String actionId, String bonusCoin, String matchId, String totalCoin) {
            this.actionId = actionId;
            try {
                userId = String.valueOf(ownerid);
//                String userName = LocalUserProfile.instance().getCache(ownerid).userName;
//                username = userName;
            } catch (Exception ex) {

            }

            this.coin = bonusCoin;
            this.totalCoin = totalCoin;
            this.matchId = matchId;

        }

        public String getData() {
            if (actionId.length() == 0) {
//				System.out.println("actionStringId invalid");
                return "";
            }
            String data = String.format(LOG_ACTION_TEMPLATE, time, serverIp, clientIp, domain, exectionTime, userId, username, actionId, appData, coin, matchId, typeBetting, teamId, totalCoin, ostype);
            return data;
        }
        private long timeBegin;
        public String serverIp = "";
        public String domain = "";
        public String clientIp = "";
        public String username = "";
        public String actionId = "";
        public String time = "0";
        public String appData = "";
        public String exectionTime = "0";
        public String coin = "0";
        public String matchId = "0";
        public String typeBetting = "0";
        public String teamId = "0";
        public String totalCoin = "0";
        public String userId = "0";
        public String ostype = "";
    }
}
