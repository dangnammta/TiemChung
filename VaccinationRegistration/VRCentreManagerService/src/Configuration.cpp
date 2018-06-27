/* 
 * File:   Configuration.cpp
 * Author: longmd
 *
 * Created on April 26, 2012, 1:18 PM
 */

#include "Configuration.h"
#include "fconfkeeper_types.h"

using namespace std;

FCore::Utility::ServiceConf::FConfKeeper* Configuration::_pCKServers = NULL;

string Configuration::_VRCentreInfoStoreServiceHost = "127.0.0.1";
int Configuration::_VRCentreInfoStoreServicePort = 0;
string Configuration::_VRCentreInfoStoreServiceCKPath = "/crdhn/dip/dipexporteddbinfostore";
string Configuration::_VRCentreInfoStoreServiceProtocol = "THRIFT_COMPACT";

string Configuration::_VRVaccineInfoStoreServiceHost = "127.0.0.1";
int Configuration::_VRVaccineInfoStoreServicePort = 0;
string Configuration::_VRVaccineInfoStoreServiceCKPath = "/crdhn/dip/dipexporteddbinfostore";
string Configuration::_VRVaccineInfoStoreServiceProtocol = "THRIFT_COMPACT";

string Configuration::_VRCommonStoreServiceHost = "127.0.0.1";
int Configuration::_VRCommonStoreServicePort = 0;
string Configuration::_VRCommonStoreServiceCKPath = "/crdhn/dip/dipexporterscheduler";
string Configuration::_VRCommonStoreServiceProtocol = "THRIFT_COMPACT";
string Configuration::_VRCommonKeyListCentres = "";

string Configuration::_FLogServiceHost = "10.30.12.168";
int Configuration::_FLogServicePort = 7773;
string Configuration::_FLogServiceCKPath = "/crdhn/common/log/flogservice";
string Configuration::_FLogServiceProtocol = "THRIFT_COMPACT";
string Configuration::_FLogSystemLogCategory = "DIPSystemLog";
bool Configuration::_FLogEnable = false;

//config key

const string Configuration::_configKeyVRCentreInfoStoreServiceHost = "crdhn.vrcentreinfostore.host";
const string Configuration::_configKeyVRCentreInfoStoreServicePort = "crdhn.vrcentreinfostore.port";
const string Configuration::_configKeyVRCentreInfoStoreServiceCKPath = "crdhn.vrcentreinfostore.ckpath";
const string Configuration::_configKeyVRCentreInfoStoreServiceProtocol = "crdhn.vrcentreinfostore.protocol";

const string Configuration::_configKeyVRVaccineInfoStoreServiceHost = "crdhn.vrvaccineinfostore.host";
const string Configuration::_configKeyVRVaccineInfoStoreServicePort = "crdhn.vrvaccineinfostore.port";
const string Configuration::_configKeyVRVaccineInfoStoreServiceCKPath = "crdhn.vrvaccineinfostore.ckpath";
const string Configuration::_configKeyVRVaccineInfoStoreServiceProtocol = "crdhn.vrvaccineinfostore.protocol";

const string Configuration::_configKeyVRCommonStoreServiceHost = "crdhn.vrcommonstore.host";
const string Configuration::_configKeyVRCommonStoreServicePort = "crdhn.vrcommonstore.port";
const string Configuration::_configKeyVRCommonStoreServiceCKPath = "crdhn.vrcommonstore.ckpath";
const string Configuration::_configKeyVRCommonStoreServiceProtocol = "crdhn.vrcommonstore.protocol";
const string Configuration::_configKeyVRCommonKeyListCentres = "crdhn.vrcommonstore.listcentres.key";

const string Configuration::_configKeyFLogServiceHost = "crdhn.common.log.flog.host";
const string Configuration::_configKeyFLogServicePort = "crdhn.common.log.flog.port";
const string Configuration::_configKeyFLogServiceCKPath = "crdhn.common.log.flog.ckpath";
const string Configuration::_configKeyFLogServiceProtocol = "crdhn.common.log.flog.protocol";
const string Configuration::_configKeyFLogSystemLogCategory = "crdhn.common.log.flog.systemlog";
const string Configuration::_configKeyFLogEnable = "crdhn.common.log.flog.enable";

void Configuration::initialize(Poco::Util::Application& application, FCore::Utility::ServiceConf::FConfKeeper* pCKServers) {
	_pCKServers = pCKServers;

	_VRCentreInfoStoreServiceHost = application.config().getString(_configKeyVRCentreInfoStoreServiceHost, _VRCentreInfoStoreServiceHost);
	_VRCentreInfoStoreServicePort = application.config().getInt(_configKeyVRCentreInfoStoreServicePort, _VRCentreInfoStoreServicePort);
	_VRCentreInfoStoreServiceCKPath = application.config().getString(_configKeyVRCentreInfoStoreServiceCKPath, _VRCentreInfoStoreServiceCKPath);
	_VRCentreInfoStoreServiceProtocol = application.config().getString(_configKeyVRCentreInfoStoreServiceProtocol, _VRCentreInfoStoreServiceProtocol);
	
	_VRVaccineInfoStoreServiceHost = application.config().getString(_configKeyVRVaccineInfoStoreServiceHost, _VRVaccineInfoStoreServiceHost);
	_VRVaccineInfoStoreServicePort = application.config().getInt(_configKeyVRVaccineInfoStoreServicePort, _VRVaccineInfoStoreServicePort);
	_VRVaccineInfoStoreServiceCKPath = application.config().getString(_configKeyVRVaccineInfoStoreServiceCKPath, _VRVaccineInfoStoreServiceCKPath);
	_VRVaccineInfoStoreServiceProtocol = application.config().getString(_configKeyVRVaccineInfoStoreServiceProtocol, _VRVaccineInfoStoreServiceProtocol);

	_VRCommonStoreServiceHost = application.config().getString(_configKeyVRCommonStoreServiceHost, _VRCommonStoreServiceHost);
	_VRCommonStoreServicePort = application.config().getInt(_configKeyVRCommonStoreServicePort, _VRCommonStoreServicePort);
	_VRCommonStoreServiceCKPath = application.config().getString(_configKeyVRCommonStoreServiceCKPath, _VRCommonStoreServiceCKPath);
	_VRCommonStoreServiceProtocol = application.config().getString(_configKeyVRCommonStoreServiceProtocol, _VRCommonStoreServiceProtocol);
	_VRCommonKeyListCentres = application.config().getString(_configKeyVRCommonKeyListCentres, _VRCommonKeyListCentres);

	_FLogServiceHost = application.config().getString(_configKeyFLogServiceHost, _FLogServiceHost);
	_FLogServicePort = application.config().getInt(_configKeyFLogServicePort, _FLogServicePort);
	_FLogServiceCKPath = application.config().getString(_configKeyFLogServiceCKPath, _FLogServiceCKPath);
	_FLogServiceProtocol = application.config().getString(_configKeyFLogServiceProtocol, _FLogServiceProtocol);
	_FLogSystemLogCategory = application.config().getString(_configKeyFLogSystemLogCategory, _FLogSystemLogCategory);
	_FLogEnable = application.config().getBool(_configKeyFLogEnable, _FLogEnable);
}

VRCentreInfoStoreServiceClientT Configuration::getVRCentreInfoStoreServiceClient(void) {
	//	if (_pCKServers) {
	//		CRDHN::FCore::Services::TEndpointGettingResult result;
	//		if (_pCKServers->getEndpoint(result, _VRCentreInfoStoreServiceCKPath, true) && (result.error == CRDHN::FCore::Services::TEError::OK))
	//			return VRCentreInfoStoreServiceClientT(result.endpoint.host, result.endpoint.port, true);
	//	}
	return VRCentreInfoStoreServiceClientT(_VRCentreInfoStoreServiceHost, _VRCentreInfoStoreServicePort, true);
}

VRVaccineInfoStoreServiceClientT Configuration::getVRVaccineInfoStoreServiceClient(void) {
	//	if (_pCKServers) {
	//		CRDHN::FCore::Services::TEndpointGettingResult result;
	//		if (_pCKServers->getEndpoint(result, _VRVaccineInfoStoreServiceCKPath, true) && (result.error == CRDHN::FCore::Services::TEError::OK))
	//			return VRVaccineInfoStoreServiceClientT(result.endpoint.host, result.endpoint.port, true);
	//	}
	return VRVaccineInfoStoreServiceClientT(_VRVaccineInfoStoreServiceHost, _VRVaccineInfoStoreServicePort, true);
}

VRCommonStoreServiceClientT Configuration::getVRCommonStoreServiceClient(void) {
	//	if (_pCKServers) {
	//		CRDHN::FCore::Services::TEndpointGettingResult result;
	//		if (_pCKServers->getEndpoint(result, _VRCommonStoreServiceCKPath, true) && (result.error == CRDHN::FCore::Services::TEError::OK))
	//			return VRCommonStoreServiceClientT(result.endpoint.host, result.endpoint.port, true);
	//	}
	return VRCommonStoreServiceClientT(_VRCommonStoreServiceHost, _VRCommonStoreServicePort, true);
}

FLogServiceClientT Configuration::getFLogServiceClient(void) {
	//	if (_pCKServers) {
	//		CRDHN::FCore::Services::TEndpointGettingResult result;
	//		if (_pCKServers->getEndpoint(result, _FLogServiceCKPath, true) && (result.error == CRDHN::FCore::Services::TEError::OK))
	//			return FLogServiceClientT(result.endpoint.host, result.endpoint.port, true);
	//	}
	return FLogServiceClientT(_FLogServiceHost, _FLogServicePort, true);
}

