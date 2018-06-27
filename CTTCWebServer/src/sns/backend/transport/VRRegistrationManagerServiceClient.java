package sns.backend.transport;

import FCore.Thrift.ClientFactory;
import FCore.Thrift.TClient;
import configuration.Config;
import crdhn.vr.thrift.TVRRegistrationManagerService;
import crdhn.vr.thrift.data.TRegistrationInfo;
import crdhn.vr.thrift.response.TError;
import crdhn.vr.thrift.response.TRMAddExtraRegistrationResponse;
import crdhn.vr.thrift.response.TRMAddRegistrationResponse;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;

/**
 *
 * @author namdv
 */
public class VRRegistrationManagerServiceClient {

    private static final Logger logger = Logger.getLogger(VRRegistrationManagerServiceClient.class);

    private static VRRegistrationManagerServiceClient _instance;
    private static String _host = "";
    private static int _port = 0;

    static {
        _host = Config.getParamString("vrregistraion", "host", "");
        _port = Config.getParamInt("vrregistraion", "port", 1617);

    }

    public TClient getClientInfo() {
        System.out.println("TVRRegistrationManagerServiceClient getClientInfo host:" + _host + "port: " + _port);
        TClient info = ClientFactory.getClient(_host, _port, 7200, TVRRegistrationManagerService.Client.class, TCompactProtocol.class);
        if (info != null) {
            if (!info.sureOpen()) {
                return null;
            }
        }
        return info;
    }

    public static VRRegistrationManagerServiceClient getInstance() {
        if (_instance == null) {
            _instance = new VRRegistrationManagerServiceClient();
        }

        return _instance;
    }

    public TRMAddRegistrationResponse addRegistration(TRegistrationInfo registrationInfo) {
        TRMAddRegistrationResponse _ret = null;
        try {
            TClient clientWrapper = getClientInfo();
            TVRRegistrationManagerService.Client aClient = (TVRRegistrationManagerService.Client) clientWrapper.getClient();
            if (aClient != null) {
                try {
                    _ret = aClient.addRegistration(registrationInfo);
                } catch (Exception e1) {
                    clientWrapper.close();
                    clientWrapper = getClientInfo();
                    aClient = (TVRRegistrationManagerService.Client) clientWrapper.getClient();
                    try {
                        _ret = aClient.addRegistration(registrationInfo);
                    } catch (TException e2) {
                        logger.error("addRegistration e2 = " + e2);
                    }
                }
            } else {
                logger.error("aClient addRegistration null========");
            }
            ClientFactory.releaseClient(clientWrapper);
        } catch (Exception e) {
            logger.error("can not connect to backend========" + e.getMessage());
        }
        return _ret;
    }
    
    public TRMAddExtraRegistrationResponse addExtraRegistration(TRegistrationInfo registrationInfo) {
        TRMAddExtraRegistrationResponse _ret = null;
        try {
            TClient clientWrapper = getClientInfo();
            TVRRegistrationManagerService.Client aClient = (TVRRegistrationManagerService.Client) clientWrapper.getClient();
            if (aClient != null) {
                try {
                    _ret = aClient.addExtraRegistration(registrationInfo);
                } catch (Exception e1) {
                    clientWrapper.close();
                    clientWrapper = getClientInfo();
                    aClient = (TVRRegistrationManagerService.Client) clientWrapper.getClient();
                    try {
                        _ret = aClient.addExtraRegistration(registrationInfo);
                    } catch (TException e2) {
                        logger.info("addExtraRegistration e2 = " + e2);
                    }
                }
            } else {
                logger.error("aClient addExtraRegistration null========");
            }
            ClientFactory.releaseClient(clientWrapper);
        } catch (Exception e) {
            logger.error("can not connect to backend========" + e.getMessage());
        }
        return _ret;
    }

    public TError getEventStatus(int eventID) {
        TError _ret = null;
        try {
            TClient clientWrapper = getClientInfo();
            TVRRegistrationManagerService.Client aClient = (TVRRegistrationManagerService.Client) clientWrapper.getClient();
            if (aClient != null) {
                try {
                    _ret = aClient.getEventStatus(eventID);
                } catch (Exception e1) {
                    clientWrapper.close();
                    clientWrapper = getClientInfo();
                    aClient = (TVRRegistrationManagerService.Client) clientWrapper.getClient();
                    try {
                        _ret = aClient.getEventStatus(eventID);
                    } catch (TException e2) {
                        logger.info("getEventStatus e2 = " + e2);
                    }
                }
            } else {
                logger.error("aClient getEventStatus null========");
            }
            ClientFactory.releaseClient(clientWrapper);
        } catch (Exception e) {
            logger.error("can not connect to backend========" + e.getMessage());
        }
        return _ret;
    }


}
