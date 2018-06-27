package sns.backend.transport;

import FCore.Thrift.ClientFactory;
import FCore.Thrift.TClient;
import configuration.Config;
import crdhn.vr.thrift.TVRCentreManagerService;
import crdhn.vr.thrift.data.TCentreInfo;
import crdhn.vr.thrift.data.TVaccineInfo;
import crdhn.vr.thrift.response.TCMAddVaccineResponse;
import crdhn.vr.thrift.response.TCMCreateCentreResponse;
import crdhn.vr.thrift.response.TCMGetCentreInfoResponse;
import crdhn.vr.thrift.response.TCMGetCentresResponse;
import crdhn.vr.thrift.response.TCMGetVaccineInfoResponse;
import crdhn.vr.thrift.response.TCMGetVaccinesResponse;
import crdhn.vr.thrift.response.TError;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;

/**
 *
 * @author namdv
 */
public class VRCentreManagerServiceClient {

    private static final Logger logger = Logger.getLogger(VRCentreManagerServiceClient.class);

    private static VRCentreManagerServiceClient _instance;
    private static String _host = "";
    private static int _port = 0;

    static {
        _host = Config.getParamString("vrcentre", "host", "");
        _port = Config.getParamInt("vrcentre", "port", 1623);

    }

    public TClient getClientInfo() {
        System.out.println("VRCentreManagerServiceClient getClientInfo host:" + _host + "port: " + _port);
        TClient info = ClientFactory.getClient(_host, _port, 7200, TVRCentreManagerService.Client.class, TCompactProtocol.class);
        if (info != null) {
            if (!info.sureOpen()) {
                return null;
            }
        }
        return info;
    }

    public static VRCentreManagerServiceClient getInstance() {
        if (_instance == null) {
            _instance = new VRCentreManagerServiceClient();
        }

        return _instance;
    }

    public TCMCreateCentreResponse createCentre(TCentreInfo centreInfo) {
        TCMCreateCentreResponse _ret = null;
        try {
            TClient clientWrapper = getClientInfo();
            TVRCentreManagerService.Client aClient = (TVRCentreManagerService.Client) clientWrapper.getClient();
            if (aClient != null) {
                try {
                    _ret = aClient.createCentre(centreInfo);
                } catch (Exception e1) {
                    clientWrapper.close();
                    clientWrapper = getClientInfo();
                    aClient = (TVRCentreManagerService.Client) clientWrapper.getClient();
                    try {
                        _ret = aClient.createCentre(centreInfo);
                    } catch (TException e2) {
                        logger.error("VRCentreManagerServiceClient.createCentre e2 = " + e2);
                    }
                }
            } else {
                logger.error("aClient VRCentreManagerServiceClient.createCentre null========");
            }
            ClientFactory.releaseClient(clientWrapper);
        } catch (Exception e) {
            logger.error("createCentre can not connect to backend========" + e.getMessage());
        }
        return _ret;
    }

    public TError updateCentreInfo(int centreId, TCentreInfo centreInfo) {
        TError _ret = null;
        try {
            TClient clientWrapper = getClientInfo();
            TVRCentreManagerService.Client aClient = (TVRCentreManagerService.Client) clientWrapper.getClient();
            if (aClient != null) {
                try {
                    _ret = aClient.updateCentreInfo(centreId, centreInfo);
                } catch (Exception e1) {
                    clientWrapper.close();
                    clientWrapper = getClientInfo();
                    aClient = (TVRCentreManagerService.Client) clientWrapper.getClient();
                    try {
                        _ret = aClient.updateCentreInfo(centreId, centreInfo);
                    } catch (TException e2) {
                        logger.error("VRCentreManagerServiceClient.updateCentreInfo e2 = " + e2);
                    }
                }
            } else {
                logger.error("aClient VRCentreManagerServiceClient.updateCentreInfo null========");
            }
            ClientFactory.releaseClient(clientWrapper);
        } catch (Exception e) {
            logger.error("updateCentreInfo can not connect to backend========" + e.getMessage());
        }
        return _ret;
    }

    public TCMGetCentreInfoResponse getCentreInfo(int centreId) {
        TCMGetCentreInfoResponse _ret = null;
        try {
            TClient clientWrapper = getClientInfo();
            TVRCentreManagerService.Client aClient = (TVRCentreManagerService.Client) clientWrapper.getClient();
            if (aClient != null) {
                try {
                    _ret = aClient.getCentreInfo(centreId);
                } catch (Exception e1) {
                    clientWrapper.close();
                    clientWrapper = getClientInfo();
                    aClient = (TVRCentreManagerService.Client) clientWrapper.getClient();
                    try {
                        _ret = aClient.getCentreInfo(centreId);
                    } catch (TException e2) {
                        logger.info("getCenterInfo e2 = " + e2);
                    }
                }
            } else {
                logger.error("aClient getCenterInfo null========");
            }
            ClientFactory.releaseClient(clientWrapper);
        } catch (Exception e) {
            logger.error("can not connect to backend========" + e.getMessage());
        }
        return _ret;
    }

    public TCMGetCentresResponse getCentres() {
        TCMGetCentresResponse _ret = null;
        try {
            TClient clientWrapper = getClientInfo();
            TVRCentreManagerService.Client aClient = (TVRCentreManagerService.Client) clientWrapper.getClient();
            if (aClient != null) {
                try {
                    _ret = aClient.getCentres();
                } catch (Exception e1) {
                    clientWrapper.close();
                    clientWrapper = getClientInfo();
                    aClient = (TVRCentreManagerService.Client) clientWrapper.getClient();
                    try {
                        _ret = aClient.getCentres();
                    } catch (TException e2) {
                        logger.info("getCentres e2 = " + e2);
                    }
                }
            } else {
                logger.error("aClient getCentres null========");
            }
            ClientFactory.releaseClient(clientWrapper);
        } catch (Exception e) {
            logger.error("getCentres can not connect to backend========" + e.getMessage());
        }
        return _ret;
    }

    public TError removeCentre(int centreId) {
        TError _ret = null;
        try {
            TClient clientWrapper = getClientInfo();
            TVRCentreManagerService.Client aClient = (TVRCentreManagerService.Client) clientWrapper.getClient();
            if (aClient != null) {
                try {
                    _ret = aClient.removeCentre(centreId);
                } catch (Exception e1) {
                    clientWrapper.close();
                    clientWrapper = getClientInfo();
                    aClient = (TVRCentreManagerService.Client) clientWrapper.getClient();
                    try {
                        _ret = aClient.removeCentre(centreId);
                    } catch (TException e2) {
                        logger.info("removeCentre e2 = " + e2);
                    }
                }
            } else {
                logger.error("aClient removeCentre null========");
            }
            ClientFactory.releaseClient(clientWrapper);
        } catch (Exception e) {
            logger.error("removeCentre can not connect to backend========" + e.getMessage());
        }
        return _ret;
    }
    
    public TCMAddVaccineResponse addVaccine(int centreId, TVaccineInfo vaccineInfo){
        TCMAddVaccineResponse _ret = null;
        try {
            TClient clientWrapper = getClientInfo();
            TVRCentreManagerService.Client aClient = (TVRCentreManagerService.Client) clientWrapper.getClient();
            if (aClient != null) {
                try {
                    _ret = aClient.addVaccine(centreId, vaccineInfo);
                } catch (Exception e1) {
                    clientWrapper.close();
                    clientWrapper = getClientInfo();
                    aClient = (TVRCentreManagerService.Client) clientWrapper.getClient();
                    try {
                        _ret = aClient.addVaccine(centreId, vaccineInfo);
                    } catch (TException e2) {
                        logger.info("addVaccine e2 = " + e2);
                    }
                }
            } else {
                logger.error("aClient addVaccine null========");
            }
            ClientFactory.releaseClient(clientWrapper);
        } catch (Exception e) {
            logger.error("addVaccine can not connect to backend========" + e.getMessage());
        }
        return _ret;
    }
    
    public TError updateVaccineInfo(int centreId, TVaccineInfo vaccineInfo){
        TError _ret = null;
        try {
            TClient clientWrapper = getClientInfo();
            TVRCentreManagerService.Client aClient = (TVRCentreManagerService.Client) clientWrapper.getClient();
            if (aClient != null) {
                try {
                    _ret = aClient.updateVaccineInfo(centreId, vaccineInfo);
                } catch (Exception e1) {
                    clientWrapper.close();
                    clientWrapper = getClientInfo();
                    aClient = (TVRCentreManagerService.Client) clientWrapper.getClient();
                    try {
                        _ret = aClient.updateVaccineInfo(centreId, vaccineInfo);
                    } catch (TException e2) {
                        logger.info("updateVaccineInfo e2 = " + e2);
                    }
                }
            } else {
                logger.error("aClient updateVaccineInfo null========");
            }
            ClientFactory.releaseClient(clientWrapper);
        } catch (Exception e) {
            logger.error("updateVaccineInfo can not connect to backend========" + e.getMessage());
        }
        return _ret;
    }
    
    public TCMGetVaccineInfoResponse getVaccineInfo(int vaccineId){
        TCMGetVaccineInfoResponse _ret = null;
        try {
            TClient clientWrapper = getClientInfo();
            TVRCentreManagerService.Client aClient = (TVRCentreManagerService.Client) clientWrapper.getClient();
            if (aClient != null) {
                try {
                    _ret = aClient.getVaccineInfo(vaccineId);
                } catch (Exception e1) {
                    clientWrapper.close();
                    clientWrapper = getClientInfo();
                    aClient = (TVRCentreManagerService.Client) clientWrapper.getClient();
                    try {
                        _ret = aClient.getVaccineInfo(vaccineId);
                    } catch (TException e2) {
                        logger.info("getVaccineInfo e2 = " + e2);
                    }
                }
            } else {
                logger.error("aClient getVaccineInfo null========");
            }
            ClientFactory.releaseClient(clientWrapper);
        } catch (Exception e) {
            logger.error("getVaccineInfo can not connect to backend========" + e.getMessage());
        }
        return _ret;
    }
    
    public TCMGetVaccinesResponse getVaccines(int centreId){
        TCMGetVaccinesResponse _ret = null;
        try {
            TClient clientWrapper = getClientInfo();
            TVRCentreManagerService.Client aClient = (TVRCentreManagerService.Client) clientWrapper.getClient();
            if (aClient != null) {
                try {
                    _ret = aClient.getVaccines(centreId);
                } catch (Exception e1) {
                    clientWrapper.close();
                    clientWrapper = getClientInfo();
                    aClient = (TVRCentreManagerService.Client) clientWrapper.getClient();
                    try {
                        _ret = aClient.getVaccines(centreId);
                    } catch (TException e2) {
                        logger.info("getVaccines e2 = " + e2);
                    }
                }
            } else {
                logger.error("aClient getVaccines null========");
            }
            ClientFactory.releaseClient(clientWrapper);
        } catch (Exception e) {
            logger.error("getVaccines can not connect to backend========" + e.getMessage());
        }
        return _ret;
    }
    
    public TError removeVaccine(int centreId, int vaccineId){
        TError _ret = null;
        try {
            TClient clientWrapper = getClientInfo();
            TVRCentreManagerService.Client aClient = (TVRCentreManagerService.Client) clientWrapper.getClient();
            if (aClient != null) {
                try {
                    _ret = aClient.removeVaccine(centreId, vaccineId);
                } catch (Exception e1) {
                    clientWrapper.close();
                    clientWrapper = getClientInfo();
                    aClient = (TVRCentreManagerService.Client) clientWrapper.getClient();
                    try {
                        _ret = aClient.removeVaccine(centreId, vaccineId);
                    } catch (TException e2) {
                        logger.info("removeVaccine e2 = " + e2);
                    }
                }
            } else {
                logger.error("aClient removeVaccine null========");
            }
            ClientFactory.releaseClient(clientWrapper);
        } catch (Exception e) {
            logger.error("removeVaccine can not connect to backend========" + e.getMessage());
        }
        return _ret;
    }

}
