/* 
 * File:   VRCommonStoreService.cpp
 * Author: longmd
 * 
 * Created on January 20, 2016, 12:08 PM
 */

#include "VRCommonStoreService.h"
#include "Configuration.h"

using namespace std;

VRCommonStoreService* VRCommonStoreService::_instance = NULL;

VRCommonStoreService::VRCommonStoreService(void) {
}

VRCommonStoreService::~VRCommonStoreService(void) {
}

VRCommonStoreService* VRCommonStoreService::instance(void) {
	if (!_instance) 
		_instance = new VRCommonStoreService();
	return _instance;
}

bool VRCommonStoreService::addItem(const CRDHN::Common::Data::StringToI32Set::TKey& key, const CRDHN::Common::Data::StringToI32Set::TItem item){
	VRCommonStoreServiceClientT client = Configuration::getVRCommonStoreServiceClient();
	if (client->sureOpen()) {
		try {
			return client->addItem(key, item);
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

bool VRCommonStoreService::removeItem(const CRDHN::Common::Data::StringToI32Set::TKey& key, const CRDHN::Common::Data::StringToI32Set::TItem item){
	VRCommonStoreServiceClientT client = Configuration::getVRCommonStoreServiceClient();
	if (client->sureOpen()) {
		try {
			return client->removeItem(key, item);
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

bool VRCommonStoreService::getItems(std::vector<CRDHN::Common::Data::StringToI32Set::TItem>& response, const CRDHN::Common::Data::StringToI32Set::TKey& key){
	VRCommonStoreServiceClientT client = Configuration::getVRCommonStoreServiceClient();
	if (client->sureOpen()) {
		try {
			CRDHN::Common::Data::StringToI32Set::TValue value;
			client->getValue(value, key);
			response = value.items;
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