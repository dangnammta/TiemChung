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

string Configuration::_VRRegistrationInfoStoreServiceHost = "127.0.0.1";
int Configuration::_VRRegistrationInfoStoreServicePort = 0;
string Configuration::_VRRegistrationInfoStoreServiceCKPath = "/crdhn/dip/dipexporteddbinfostore";
string Configuration::_VRRegistrationInfoStoreServiceProtocol = "THRIFT_COMPACT";

string Configuration::_VRDailyRegistrationsStoreServiceHost = "127.0.0.1";
int Configuration::_VRDailyRegistrationsStoreServicePort = 0;
string Configuration::_VRDailyRegistrationsStoreServiceCKPath = "/crdhn/dip/dipexporteddbschedulestore";
string Configuration::_VRDailyRegistrationsStoreServiceProtocol = "THRIFT_COMPACT";

string Configuration::_VRDailyInjectionsStoreServiceHost = "127.0.0.1";
int Configuration::_VRDailyInjectionsStoreServicePort = 0;
string Configuration::_VRDailyInjectionsStoreServiceCKPath = "/crdhn/dip/dipexporterscheduler";
string Configuration::_VRDailyInjectionsStoreServiceProtocol = "THRIFT_COMPACT";

string Configuration::_VREventInfoStoreServiceHost = "127.0.0.1";
int Configuration::_VREventInfoStoreServicePort = 0;
string Configuration::_VREventInfoStoreServiceCKPath = "/crdhn/dip/dipexporterscheduler";
string Configuration::_VREventInfoStoreServiceProtocol = "THRIFT_COMPACT";

string Configuration::_FLogServiceHost = "10.30.12.168";
int Configuration::_FLogServicePort = 7773;
string Configuration::_FLogServiceCKPath = "/crdhn/common/log/flogservice";
string Configuration::_FLogServiceProtocol = "THRIFT_COMPACT";
string Configuration::_FLogSystemLogCategory = "DIPSystemLog";
bool Configuration::_FLogEnable = false;

int Configuration::_lengthRegistrationCode = 6;

//config key

const string Configuration::_configKeyVRRegistrationInfoStoreServiceHost = "crdhn.vrregistrationinfostore.host";
const string Configuration::_configKeyVRRegistrationInfoStoreServicePort = "crdhn.vrregistrationinfostore.port";
const string Configuration::_configKeyVRRegistrationInfoStoreServiceCKPath = "crdhn.vrregistrationinfostore.ckpath";
const string Configuration::_configKeyVRRegistrationInfoStoreServiceProtocol = "crdhn.vrregistrationinfostore.protocol";

const string Configuration::_configKeyVRDailyRegistrationsStoreServiceHost = "crdhn.vrdailyregistrationsstore.host";
const string Configuration::_configKeyVRDailyRegistrationsStoreServicePort = "crdhn.vrdailyregistrationsstore.port";
const string Configuration::_configKeyVRDailyRegistrationsStoreServiceCKPath = "crdhn.vrdailyregistrationsstore.ckpath";
const string Configuration::_configKeyVRDailyRegistrationsStoreServiceProtocol = "crdhn.vrdailyregistrationsstore.protocol";

const string Configuration::_configKeyVRDailyInjectionsStoreServiceHost = "crdhn.vrdailyinjectionsstore.host";
const string Configuration::_configKeyVRDailyInjectionsStoreServicePort = "crdhn.vrdailyinjectionsstore.port";
const string Configuration::_configKeyVRDailyInjectionsStoreServiceCKPath = "crdhn.vrdailyinjectionsstore.ckpath";
const string Configuration::_configKeyVRDailyInjectionsStoreServiceProtocol = "crdhn.vrdailyinjectionsstore.protocol";

const string Configuration::_configKeyVREventInfoStoreServiceHost = "crdhn.vreventinfostore.host";
const string Configuration::_configKeyVREventInfoStoreServicePort = "crdhn.vreventinfostore.port";
const string Configuration::_configKeyVREventInfoStoreServiceCKPath = "crdhn.vreventinfostore.ckpath";
const string Configuration::_configKeyVREventInfoStoreServiceProtocol = "crdhn.vreventinfostore.protocol";

const string Configuration::_configKeyFLogServiceHost = "crdhn.common.log.flog.host";
const string Configuration::_configKeyFLogServicePort = "crdhn.common.log.flog.port";
const string Configuration::_configKeyFLogServiceCKPath = "crdhn.common.log.flog.ckpath";
const string Configuration::_configKeyFLogServiceProtocol = "crdhn.common.log.flog.protocol";
const string Configuration::_configKeyFLogSystemLogCategory = "crdhn.common.log.flog.systemlog";
const string Configuration::_configKeyFLogEnable = "crdhn.common.log.flog.enable";

const string Configuration::_configKeyLengthRegistrationCode = "crdhn.lengthregistrationcode";

void Configuration::initialize(Poco::Util::Application& application, FCore::Utility::ServiceConf::FConfKeeper* pCKServers) {
	_pCKServers = pCKServers;

	_VRRegistrationInfoStoreServiceHost = application.config().getString(_configKeyVRRegistrationInfoStoreServiceHost, _VRRegistrationInfoStoreServiceHost);
	_VRRegistrationInfoStoreServicePort = application.config().getInt(_configKeyVRRegistrationInfoStoreServicePort, _VRRegistrationInfoStoreServicePort);
	_VRRegistrationInfoStoreServiceCKPath = application.config().getString(_configKeyVRRegistrationInfoStoreServiceCKPath, _VRRegistrationInfoStoreServiceCKPath);
	_VRRegistrationInfoStoreServiceProtocol = application.config().getString(_configKeyVRRegistrationInfoStoreServiceProtocol, _VRRegistrationInfoStoreServiceProtocol);

	_VRDailyRegistrationsStoreServiceHost = application.config().getString(_configKeyVRDailyRegistrationsStoreServiceHost, _VRDailyRegistrationsStoreServiceHost);
	_VRDailyRegistrationsStoreServicePort = application.config().getInt(_configKeyVRDailyRegistrationsStoreServicePort, _VRDailyRegistrationsStoreServicePort);
	_VRDailyRegistrationsStoreServiceCKPath = application.config().getString(_configKeyVRDailyRegistrationsStoreServiceCKPath, _VRDailyRegistrationsStoreServiceCKPath);
	_VRDailyRegistrationsStoreServiceProtocol = application.config().getString(_configKeyVRDailyRegistrationsStoreServiceProtocol, _VRDailyRegistrationsStoreServiceProtocol);

	_VRDailyInjectionsStoreServiceHost = application.config().getString(_configKeyVRDailyInjectionsStoreServiceHost, _VRDailyInjectionsStoreServiceHost);
	_VRDailyInjectionsStoreServicePort = application.config().getInt(_configKeyVRDailyInjectionsStoreServicePort, _VRDailyInjectionsStoreServicePort);
	_VRDailyInjectionsStoreServiceCKPath = application.config().getString(_configKeyVRDailyInjectionsStoreServiceCKPath, _VRDailyInjectionsStoreServiceCKPath);
	_VRDailyInjectionsStoreServiceProtocol = application.config().getString(_configKeyVRDailyInjectionsStoreServiceProtocol, _VRDailyInjectionsStoreServiceProtocol);
	
	_VREventInfoStoreServiceHost = application.config().getString(_configKeyVREventInfoStoreServiceHost, _VREventInfoStoreServiceHost);
	_VREventInfoStoreServicePort = application.config().getInt(_configKeyVREventInfoStoreServicePort, _VREventInfoStoreServicePort);
	_VREventInfoStoreServiceCKPath = application.config().getString(_configKeyVREventInfoStoreServiceCKPath, _VREventInfoStoreServiceCKPath);
	_VREventInfoStoreServiceProtocol = application.config().getString(_configKeyVREventInfoStoreServiceProtocol, _VREventInfoStoreServiceProtocol);

	_FLogServiceHost = application.config().getString(_configKeyFLogServiceHost, _FLogServiceHost);
	_FLogServicePort = application.config().getInt(_configKeyFLogServicePort, _FLogServicePort);
	_FLogServiceCKPath = application.config().getString(_configKeyFLogServiceCKPath, _FLogServiceCKPath);
	_FLogServiceProtocol = application.config().getString(_configKeyFLogServiceProtocol, _FLogServiceProtocol);
	_FLogSystemLogCategory = application.config().getString(_configKeyFLogSystemLogCategory, _FLogSystemLogCategory);
	_FLogEnable = application.config().getBool(_configKeyFLogEnable, _FLogEnable);
	
	_lengthRegistrationCode = application.config().getInt(_configKeyLengthRegistrationCode, _lengthRegistrationCode);
}

VRRegistrationInfoStoreServiceClientT Configuration::getVRRegistrationInfoStoreServiceClient(void) {
	//	if (_pCKServers) {
	//		CRDHN::FCore::Services::TEndpointGettingResult result;
	//		if (_pCKServers->getEndpoint(result, _VRRegistrationInfoStoreServiceCKPath, true) && (result.error == CRDHN::FCore::Services::TEError::OK))
	//			return VRRegistrationInfoStoreServiceClientT(result.endpoint.host, result.endpoint.port, true);
	//	}
	return VRRegistrationInfoStoreServiceClientT(_VRRegistrationInfoStoreServiceHost, _VRRegistrationInfoStoreServicePort, true);
}

VRDailyRegistrationsStoreServiceClientT Configuration::getVRDailyRegistrationsStoreServiceClient(void) {
	//	if (_pCKServers) {
	//		CRDHN::FCore::Services::TEndpointGettingResult result;
	//		if (_pCKServers->getEndpoint(result, _VRDailyRegistrationsStoreServiceCKPath, true) && (result.error == CRDHN::FCore::Services::TEError::OK))
	//			return VRDailyRegistrationsStoreServiceClientT(result.endpoint.host, result.endpoint.port, true);
	//	}
	return VRDailyRegistrationsStoreServiceClientT(_VRDailyRegistrationsStoreServiceHost, _VRDailyRegistrationsStoreServicePort, true);
}

VRDailyInjectionsStoreServiceClientT Configuration::getVRDailyInjectionsStoreServiceClient(void) {
	//	if (_pCKServers) {
	//		CRDHN::FCore::Services::TEndpointGettingResult result;
	//		if (_pCKServers->getEndpoint(result, _VRDailyInjectionsStoreServiceCKPath, true) && (result.error == CRDHN::FCore::Services::TEError::OK))
	//			return VRDailyInjectionsStoreServiceClientT(result.endpoint.host, result.endpoint.port, true);
	//	}
	return VRDailyInjectionsStoreServiceClientT(_VRDailyInjectionsStoreServiceHost, _VRDailyInjectionsStoreServicePort, true);
}

VREventInfoStoreServiceClientT Configuration::getVREventInfoStoreServiceClient(void) {
	//	if (_pCKServers) {
	//		CRDHN::FCore::Services::TEndpointGettingResult result;
	//		if (_pCKServers->getEndpoint(result, _VREventInfoStoreServiceCKPath, true) && (result.error == CRDHN::FCore::Services::TEError::OK))
	//			return VREventInfoStoreServiceClientT(result.endpoint.host, result.endpoint.port, true);
	//	}
	return VREventInfoStoreServiceClientT(_VREventInfoStoreServiceHost, _VREventInfoStoreServicePort, true);
}

FLogServiceClientT Configuration::getFLogServiceClient(void) {
	//	if (_pCKServers) {
	//		CRDHN::FCore::Services::TEndpointGettingResult result;
	//		if (_pCKServers->getEndpoint(result, _FLogServiceCKPath, true) && (result.error == CRDHN::FCore::Services::TEError::OK))
	//			return FLogServiceClientT(result.endpoint.host, result.endpoint.port, true);
	//	}
	return FLogServiceClientT(_FLogServiceHost, _FLogServicePort, true);
}

