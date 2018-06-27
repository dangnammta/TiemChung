/* 
 * File:   VRRegistrationCodesStoreService.cpp
 * Author: longmd
 * 
 * Created on January 27, 2016, 10:52 AM
 */

#include "VRRegistrationCodesStoreService.h"
#include "Configuration.h"

using namespace std;
using namespace CRDHN::VR::Data;

VRRegistrationCodesStoreService* VRRegistrationCodesStoreService::_instance = NULL;

VRRegistrationCodesStoreService::VRRegistrationCodesStoreService(void) {
}

VRRegistrationCodesStoreService::~VRRegistrationCodesStoreService(void) {
}

VRRegistrationCodesStoreService* VRRegistrationCodesStoreService::instance(void) {
	if (!_instance) 
		_instance = new VRRegistrationCodesStoreService();
	return _instance;
}

bool VRRegistrationCodesStoreService::add(const TEID eventId, const std::string& registrationCode, const TRID registrationId){
	VRRegistrationCodesStoreServiceClientT client = Configuration::getVRRegistrationCodesStoreServiceClient();
	if (client->sureOpen()) {
		try {
			return client->add(eventId, registrationCode, registrationId);
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

bool VRRegistrationCodesStoreService::remove(const TEID eventId, const std::string& registrationCode, const TRID registrationId){
	VRRegistrationCodesStoreServiceClientT client = Configuration::getVRRegistrationCodesStoreServiceClient();
	if (client->sureOpen()) {
		try {
			return client->remove(eventId, registrationCode, registrationId);
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

TRID VRRegistrationCodesStoreService::getRegistrationId(const TEID eventId, const std::string& registrationCode){
	VRRegistrationCodesStoreServiceClientT client = Configuration::getVRRegistrationCodesStoreServiceClient();
	if (client->sureOpen()) {
		try {
			return client->getRegistrationId(eventId, registrationCode);
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

void VRRegistrationCodesStoreService::getRegistrationCodes(TRegistrationCodes& response, const TEID eventId){
	VRRegistrationCodesStoreServiceClientT client = Configuration::getVRRegistrationCodesStoreServiceClient();
	if (client->sureOpen()) {
		try {
			client->getRegistrationCodes(response, eventId);
		}
		catch (Poco::Exception ex) {
			FLogService::instance()->printExceptionLog("["+FCore::Utility::ServiceConf::Properties::_serviceName+"] "+__FILE__+"::"+__func__+"()", ex);
		}
	}
	else {
		FLogService::instance()->printConnectionRefusedLog("["+FCore::Utility::ServiceConf::Properties::_serviceName+"] "+__FILE__+"::"+__func__+"()");
	}
	client->close();
}

bool VRRegistrationCodesStoreService::removeRegistrationCodes(const TEID eventId){
	VRRegistrationCodesStoreServiceClientT client = Configuration::getVRRegistrationCodesStoreServiceClient();
	if (client->sureOpen()) {
		try {
			return client->removeRegistrationCodes(eventId);
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