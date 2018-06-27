/* 
 * File:   VRRegistrationInfoStoreService.cpp
 * Author: longmd
 * 
 * Created on December 30, 2015, 10:24 AM
 */

#include "VRRegistrationInfoStoreService.h"
#include "Configuration.h"

using namespace std;
using namespace CRDHN::VR::Data;

VRRegistrationInfoStoreService* VRRegistrationInfoStoreService::_instance = NULL;

VRRegistrationInfoStoreService::VRRegistrationInfoStoreService(void) {
}

VRRegistrationInfoStoreService::~VRRegistrationInfoStoreService(void) {
}

VRRegistrationInfoStoreService* VRRegistrationInfoStoreService::instance(void) {
	if (!_instance) 
		_instance = new VRRegistrationInfoStoreService();
	return _instance;
}

TRID VRRegistrationInfoStoreService::add(const TRegistrationInfo registrationInfo){
	VRRegistrationInfoStoreServiceClientT client = Configuration::getVRRegistrationInfoStoreServiceClient();
	if (client->sureOpen()) {
		try {
			return client->add(registrationInfo);
		}
		catch (Poco::Exception ex) {
			FLogService::instance()->printExceptionLog("["+FCore::Utility::ServiceConf::Properties::_serviceName+"] "+__FILE__+"::"+__func__+"()", ex);
		}
	}
	else {
		FLogService::instance()->printConnectionRefusedLog("["+FCore::Utility::ServiceConf::Properties::_serviceName+"] "+__FILE__+"::"+__func__+"()");
	}
	client->close();
	return -1;
}

bool VRRegistrationInfoStoreService::setRegistrationCode(const TRID registrationId, const std::string& registrationCode){
	VRRegistrationInfoStoreServiceClientT client = Configuration::getVRRegistrationInfoStoreServiceClient();
	if (client->sureOpen()) {
		try {
			return client->setRegistrationCode(registrationId, registrationCode);
		}
		catch (Poco::Exception ex) {
			FLogService::instance()->printExceptionLog("["+FCore::Utility::ServiceConf::Properties::_serviceName+"] "+__FILE__+"::"+__func__+"()", ex);
		}
	}
	else {
		FLogService::instance()->printConnectionRefusedLog("["+FCore::Utility::ServiceConf::Properties::_serviceName+"] "+__FILE__+"::"+__func__+"()");
	}
	client->close();
	return false;
}