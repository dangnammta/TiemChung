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

bool VRRegistrationInfoStoreService::get(TRegistrationInfo& response, const TRID registrationId){
	VRRegistrationInfoStoreServiceClientT client = Configuration::getVRRegistrationInfoStoreServiceClient();
	if (client->sureOpen()) {
		try {
			client->get(response, registrationId);
			return true;
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

bool VRRegistrationInfoStoreService::update(const TRID registrationId, const TRegistrationInfo& registrationInfo){
	VRRegistrationInfoStoreServiceClientT client = Configuration::getVRRegistrationInfoStoreServiceClient();
	if (client->sureOpen()) {
		try {
			return client->update(registrationId, registrationInfo);
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

bool VRRegistrationInfoStoreService::remove(const TRID registrationId){
	VRRegistrationInfoStoreServiceClientT client = Configuration::getVRRegistrationInfoStoreServiceClient();
	if (client->sureOpen()) {
		try {
			return client->remove(registrationId);
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

bool VRRegistrationInfoStoreService::updateInjectionStatus(const TRID registrationId, const int32_t injectionStatus){
	VRRegistrationInfoStoreServiceClientT client = Configuration::getVRRegistrationInfoStoreServiceClient();
	if (client->sureOpen()) {
		try {
			return client->updateInjectionStatus(registrationId, injectionStatus);
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