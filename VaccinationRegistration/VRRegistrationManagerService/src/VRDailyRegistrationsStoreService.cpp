/* 
 * File:   VRDailyRegistrationsStoreService.cpp
 * Author: longmd
 * 
 * Created on December 30, 2015, 10:24 AM
 */

#include "VRDailyRegistrationsStoreService.h"
#include "Configuration.h"

using namespace std;
using namespace CRDHN::VR::Data;

VRDailyRegistrationsStoreService* VRDailyRegistrationsStoreService::_instance = NULL;

VRDailyRegistrationsStoreService::VRDailyRegistrationsStoreService(void) {
}

VRDailyRegistrationsStoreService::~VRDailyRegistrationsStoreService(void) {
}

VRDailyRegistrationsStoreService* VRDailyRegistrationsStoreService::instance(void) {
	if (!_instance) 
		_instance = new VRDailyRegistrationsStoreService();
	return _instance;
}

int32_t VRDailyRegistrationsStoreService::add(const std::string& key, const std::string& PIN, const TRID rId){
	VRDailyRegistrationsStoreServiceClientT client = Configuration::getVRDailyRegistrationsStoreServiceClient();
	if (client->sureOpen()) {
		try {
			return client->add(key, PIN, rId);
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

void VRDailyRegistrationsStoreService::get(TDailyRegistrations& response, const std::string& key){
	VRDailyRegistrationsStoreServiceClientT client = Configuration::getVRDailyRegistrationsStoreServiceClient();
	if (client->sureOpen()) {
		try {
			client->get(response, key);
			return;
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

int32_t VRDailyRegistrationsStoreService::getRegistrationId(const std::string& key, const std::string& PIN){
	VRDailyRegistrationsStoreServiceClientT client = Configuration::getVRDailyRegistrationsStoreServiceClient();
	if (client->sureOpen()) {
		try {
			return client->getRegistrationId(key, PIN);
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

bool VRDailyRegistrationsStoreService::remove(const std::string& key){
	VRDailyRegistrationsStoreServiceClientT client = Configuration::getVRDailyRegistrationsStoreServiceClient();
	if (client->sureOpen()) {
		try {
			return client->remove(key);
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