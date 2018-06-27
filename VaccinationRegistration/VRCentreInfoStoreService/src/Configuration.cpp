/* 
 * File:   Configuration.cpp
 * Author: LongMD
 *
 * Created on April 26, 2012, 1:18 PM
 */

#include "Configuration.h"
#include "fconfkeeper_types.h"

using namespace std;

FCore::Utility::ServiceConf::FConfKeeper* Configuration::_pCKServers = NULL;

string Configuration::_StepCounterServiceHost = "10.30.12.168";
int Configuration::_StepCounterServicePort = 7773;
string Configuration::_StepCounterServiceVRCentreIdCntName = "CRDHN_VR_CENTREID";
string Configuration::_StepCounterServiceCKPath = "/crdhn/vr/vrstepcounterservice";
string Configuration::_StepCounterServiceProtocol = "thrift_compact";

const string Configuration::_configKeyStepCounterServiceHost = "crdhn.stepcounter.host";
const string Configuration::_configKeyStepCounterServicePort = "crdhn.stepcounter.port";
const string Configuration::_configKeyStepCounterServiceVRCentreIdCntName = "crdhn.stepcounter.vr.centreid.cntname";
const string Configuration::_configKeyStepCounterServiceCKPath = "crdhn.stepcounter.ckpath";
const string Configuration::_configKeyStepCounterServiceProtocol = "crdhn.stepcounter.protocol";


void Configuration::initialize(Poco::Util::Application& application, FCore::Utility::ServiceConf::FConfKeeper* pCKServers) {
	_pCKServers = pCKServers;
	
	_StepCounterServiceHost = application.config().getString(_configKeyStepCounterServiceHost, _StepCounterServiceHost);
	_StepCounterServicePort = application.config().getInt(_configKeyStepCounterServicePort, _StepCounterServicePort);
	_StepCounterServiceVRCentreIdCntName = application.config().getString(_configKeyStepCounterServiceVRCentreIdCntName, _StepCounterServiceVRCentreIdCntName);
	_StepCounterServiceCKPath = application.config().getString(_configKeyStepCounterServiceCKPath, _StepCounterServiceCKPath);
	_StepCounterServiceProtocol = application.config().getString(_configKeyStepCounterServiceProtocol, _StepCounterServiceProtocol);
}

StepCounterServiceClientT Configuration::getStepCounterServiceClient(void) {
//	if (_pCKServers) {
//		CRDHN::FCore::Services::TEndpointGettingResult result;
//		if (_pCKServers->getEndpoint(result, _StepCounterServiceCKPath, true) && (result.error == CRDHN::FCore::Services::TEError::OK))
//			return StepCounterServiceClientT(result.endpoint.host, result.endpoint.port, true);
//	}
	return StepCounterServiceClientT(_StepCounterServiceHost, _StepCounterServicePort, true);
}