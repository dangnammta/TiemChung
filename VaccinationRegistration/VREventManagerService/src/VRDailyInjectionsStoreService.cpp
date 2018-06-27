/* 
 * File:   VRDailyInjectionsStoreService.cpp
 * Author: longmd
 * 
 * Created on December 30, 2015, 10:24 AM
 */

#include "VRDailyInjectionsStoreService.h"
#include "Configuration.h"

using namespace std;
using namespace CRDHN::VR::Data;

VRDailyInjectionsStoreService* VRDailyInjectionsStoreService::_instance = NULL;

VRDailyInjectionsStoreService::VRDailyInjectionsStoreService(void) {
}

VRDailyInjectionsStoreService::~VRDailyInjectionsStoreService(void) {
}

VRDailyInjectionsStoreService* VRDailyInjectionsStoreService::instance(void) {
	if (!_instance) 
		_instance = new VRDailyInjectionsStoreService();
	return _instance;
}

int32_t VRDailyInjectionsStoreService::add(const std::string& key, const TRID rId){
	VRDailyInjectionsStoreServiceClientT client = Configuration::getVRDailyInjectionsStoreServiceClient();
	if (client->sureOpen()) {
		try {
			return client->add(key, rId);
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

void VRDailyInjectionsStoreService::get(TDailyInjections& response, const std::string& key){
	VRDailyInjectionsStoreServiceClientT client = Configuration::getVRDailyInjectionsStoreServiceClient();
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

bool VRDailyInjectionsStoreService::remove(const std::string& key){
	VRDailyInjectionsStoreServiceClientT client = Configuration::getVRDailyInjectionsStoreServiceClient();
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