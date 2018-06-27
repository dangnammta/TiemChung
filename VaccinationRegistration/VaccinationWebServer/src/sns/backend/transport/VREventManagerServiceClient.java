package sns.backend.transport;

import FCore.Thrift.ClientFactory;
import FCore.Thrift.TClient;
import configuration.Config;
import crdhn.vr.thrift.TVREventManagerService;
import crdhn.vr.thrift.data.TEventInfo;
import crdhn.vr.thrift.data.TRegistrationInfo;
import crdhn.vr.thrift.response.TError;
import crdhn.vr.thrift.response.TVMCreateEventResponse;
import crdhn.vr.thrift.response.TVMGetEventInfoResponse;
import crdhn.vr.thrift.response.TVMGetEventsResponse;
import crdhn.vr.thrift.response.TVMGetRegistrationResponse;
import crdhn.vr.thrift.response.TVMGetRegistrationsResponse;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;

/**
 *
 * @author namdv
 */
public class VREventManagerServiceClient {

    private static final Logger logger = Logger.getLogger(VREventManagerServiceClient.class);

    private static VREventManagerServiceClient _instance;
    private static String _host = "";
    private static int _port = 0;

    static {
        _host = Config.getParamString("vrevent", "host", "");
        _port = Config.getParamInt("vrevent", "port", 1611);

    }

    public TClient getClientInfo() {
        System.out.println("TVREventManagerServiceClient getClientInfo host:" + _host + "port: " + _port);
        TClient info = ClientFactory.getClient(_host, _port, 7200, TVREventManagerService.Client.class, TCompactProtocol.class);
        if (info != null) {
            if (!info.sureOpen()) {
                return null;
            }
        }
        return info;
    }

    public static VREventManagerServiceClient getInstance() {
        if (_instance == null) {
            _instance = new VREventManagerServiceClient();
        }
        return _instance;
    }

    public TError updateRegistrationInfo(long registrationID, TRegistrationInfo registrationInfo) {
        TError _ret = null;
        try {
            TClient clientWrapper = getClientInfo();
            TVREventManagerService.Client aClient = (TVREventManagerService.Client) clientWrapper.getClient();
            if (aClient != null) {
                try {
                    _ret = aClient.updateRegistration(registrationID, registrationInfo);
                } catch (Exception e1) {
                    clientWrapper.close();
                    clientWrapper = getClientInfo();
                    aClient = (TVREventManagerService.Client) clientWrapper.getClient();
                    try {
                        _ret = aClient.updateRegistration(registrationID, registrationInfo);
                    } catch (TException e2) {
                        logger.info("updateRegistration e2 = " + e2);
                    }
                }
            } else {
                logger.error("aClient updateRegistration null========");
            }
            ClientFactory.releaseClient(clientWrapper);
        } catch (Exception e) {
            logger.error("updateRegistration can not connect to backend========" + e.getMessage());
        }
        return _ret;
    }

    public TError removeRegistration(long registrationID) {
        TError _ret = null;
        try {
            TClient clientWrapper = getClientInfo();
            TVREventManagerService.Client aClient = (TVREventManagerService.Client) clientWrapper.getClient();
            if (aClient != null) {
                try {
                    _ret = aClient.removeRegistration(registrationID);
                } catch (Exception e1) {
                    clientWrapper.close();
                    clientWrapper = getClientInfo();
                    aClient = (TVREventManagerService.Client) clientWrapper.getClient();
                    try {
                        _ret = aClient.removeRegistration(registrationID);
                    } catch (TException e2) {
                        logger.info("removeRegistration e2 = " + e2);
                    }
                }
            } else {
                logger.error("aClient removeRegistration null========");
            }
            ClientFactory.releaseClient(clientWrapper);
        } catch (Exception e) {
            logger.error("updateRegistration can not connect to backend========" + e.getMessage());
        }
        return _ret;
    }

    public TVMGetRegistrationResponse getRegistrationInfo(int registrationID) {
        TVMGetRegistrationResponse _ret = null;
        try {
            TClient clientWrapper = getClientInfo();
            TVREventManagerService.Client aClient = (TVREventManagerService.Client) clientWrapper.getClient();
            if (aClient != null) {
                try {
                    _ret = aClient.getRegistration(registrationID);
                } catch (Exception e1) {
                    clientWrapper.close();
                    clientWrapper = getClientInfo();
                    aClient = (TVREventManagerService.Client) clientWrapper.getClient();
                    try {
                        _ret = aClient.getRegistration(registrationID);
                    } catch (TException e2) {
                        logger.info("getRegistrationInfo e2 = " + e2);
                    }
                }
            } else {
                logger.error("aClient getRegistrationInfo null========");
            }
            ClientFactory.releaseClient(clientWrapper);
        } catch (Exception e) {
            logger.error("getRegistrationInfo can not connect to backend========" + e.getMessage());
        }
        return _ret;
    }

    public TVMGetRegistrationsResponse getRegistrations(int eventId) {
        TVMGetRegistrationsResponse _ret = null;
        try {
            TClient clientWrapper = getClientInfo();
            TVREventManagerService.Client aClient = (TVREventManagerService.Client) clientWrapper.getClient();
            if (aClient != null) {
                try {
                    _ret = aClient.getRegistrations(eventId);
                } catch (Exception e1) {
                    clientWrapper.close();
                    clientWrapper = getClientInfo();
                    aClient = (TVREventManagerService.Client) clientWrapper.getClient();
                    try {
                        _ret = aClient.getRegistrations(eventId);
                    } catch (TException e2) {
                        logger.info("getRegistrations e2 = " + e2);
                    }
                }
            } else {
                logger.error("aClient getRegistrations null========");
            }
            ClientFactory.releaseClient(clientWrapper);
        } catch (Exception e) {
            logger.error("can not connect to backend========" + e.getMessage());
        }
        return _ret;
    }

    public TError updateInjectionStatus(long registrationId, int injectionStatus) {
        TError _ret = null;
        try {
            TClient clientWrapper = getClientInfo();
            TVREventManagerService.Client aClient = (TVREventManagerService.Client) clientWrapper.getClient();
            if (aClient != null) {
                try {
                    _ret = aClient.updateInjectionStatus(registrationId, injectionStatus);
                } catch (Exception e1) {
                    clientWrapper.close();
                    clientWrapper = getClientInfo();
                    aClient = (TVREventManagerService.Client) clientWrapper.getClient();
                    try {
                        _ret = aClient.updateInjectionStatus(registrationId, injectionStatus);
                    } catch (TException e2) {
                        logger.info("updateInjectionStatus e2 = " + e2);
                    }
                }
            } else {
                logger.error("aClient updateInjectionStatus null========");
            }
            ClientFactory.releaseClient(clientWrapper);
        } catch (Exception e) {
            logger.error("can not connect to backend========" + e.getMessage());
        }
        return _ret;
    }

    public TVMGetEventsResponse getEvents(int centeId) {
        TVMGetEventsResponse _ret = null;
        try {
            TClient clientWrapper = getClientInfo();
            TVREventManagerService.Client aClient = (TVREventManagerService.Client) clientWrapper.getClient();
            if (aClient != null) {
                try {
                    _ret = aClient.getEvents(centeId);
                } catch (Exception e1) {
                    clientWrapper.close();
                    clientWrapper = getClientInfo();
                    aClient = (TVREventManagerService.Client) clientWrapper.getClient();
                    try {
                        _ret = aClient.getEvents(centeId);
                    } catch (TException e2) {
                        logger.info("getEvents e2 = " + e2);
                    }
                }
            } else {
                logger.error("aClient getEvents null========");
            }
            ClientFactory.releaseClient(clientWrapper);
        } catch (Exception e) {
            logger.error("getEvents can not connect to backend========" + e.getMessage());
        }
        return _ret;
    }

    public TVMCreateEventResponse createEvent(TEventInfo eventInfo) {
        TVMCreateEventResponse _ret = null;
        try {
            TClient clientWrapper = getClientInfo();
            TVREventManagerService.Client aClient = (TVREventManagerService.Client) clientWrapper.getClient();
            if (aClient != null) {
                try {
                    _ret = aClient.createEvent(eventInfo);
                } catch (Exception e1) {
                    clientWrapper.close();
                    clientWrapper = getClientInfo();
                    aClient = (TVREventManagerService.Client) clientWrapper.getClient();
                    try {
                        _ret = aClient.createEvent(eventInfo);
                    } catch (TException e2) {
                        logger.info("createEvent e2 = " + e2);
                    }
                }
            } else {
                logger.error("aClient createEvent null========");
            }
            ClientFactory.releaseClient(clientWrapper);
        } catch (Exception e) {
            logger.error("can not connect to backend========" + e.getMessage());
        }
        return _ret;
    }

    public TError updateEventInfo(int eventId, TEventInfo eventInfo) {
        TError _ret = null;
        try {
            TClient clientWrapper = getClientInfo();
            TVREventManagerService.Client aClient = (TVREventManagerService.Client) clientWrapper.getClient();
            if (aClient != null) {
                try {
                    _ret = aClient.updateEventInfo(eventId, eventInfo);
                } catch (Exception e1) {
                    clientWrapper.close();
                    clientWrapper = getClientInfo();
                    aClient = (TVREventManagerService.Client) clientWrapper.getClient();
                    try {
                        _ret = aClient.updateEventInfo(eventId, eventInfo);
                    } catch (TException e2) {
                        logger.info("updateEventInfo e2 = " + e2);
                    }
                }
            } else {
                logger.error("aClient updateEventInfo null========");
            }
            ClientFactory.releaseClient(clientWrapper);
        } catch (Exception e) {
            logger.error("can not connect to backend========" + e.getMessage());
        }
        return _ret;
    }

    public TVMGetEventInfoResponse getEventInfo(int eventId) {
        TVMGetEventInfoResponse _ret = null;
        try {
            TClient clientWrapper = getClientInfo();
            TVREventManagerService.Client aClient = (TVREventManagerService.Client) clientWrapper.getClient();
            if (aClient != null) {
                try {
                    _ret = aClient.getEventInfo(eventId);
                } catch (Exception e1) {
                    clientWrapper.close();
                    clientWrapper = getClientInfo();
                    aClient = (TVREventManagerService.Client) clientWrapper.getClient();
                    try {
                        _ret = aClient.getEventInfo(eventId);
                    } catch (TException e2) {
                        logger.info("getEventInfo e2 = " + e2);
                    }
                }
            } else {
                logger.error("aClient getEventInfo null========");
            }
            ClientFactory.releaseClient(clientWrapper);
        } catch (Exception e) {
            logger.error("can not connect to backend========" + e.getMessage());
        }
        return _ret;
    }

    public TError removeEvent(int eventId) {
        TError _ret = null;
        try {
            TClient clientWrapper = getClientInfo();
            TVREventManagerService.Client aClient = (TVREventManagerService.Client) clientWrapper.getClient();
            if (aClient != null) {
                try {
                    _ret = aClient.removeEvent(eventId);
                } catch (Exception e1) {
                    clientWrapper.close();
                    clientWrapper = getClientInfo();
                    aClient = (TVREventManagerService.Client) clientWrapper.getClient();
                    try {
                        _ret = aClient.removeEvent(eventId);
                    } catch (TException e2) {
                        logger.info("removeEvent e2 = " + e2);
                    }
                }
            } else {
                logger.error("aClient removeEvent null========");
            }
            ClientFactory.releaseClient(clientWrapper);
        } catch (Exception e) {
            logger.error("can not connect to backend========" + e.getMessage());
        }
        return _ret;
    }

}
