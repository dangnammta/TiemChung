/* 
 * File:   VRCentreInfoStoreService.cpp
 * Author: longmd
 * 
 * Created on January 2, 2016, 12:02 AM
 */

#include "VRCentreInfoStoreService.h"
#include "Configuration.h"

using namespace std;
using namespace CRDHN::VR::Data;

VRCentreInfoStoreService* VRCentreInfoStoreService::_instance = NULL;

VRCentreInfoStoreService::VRCentreInfoStoreService(void) {
}

VRCentreInfoStoreService::~VRCentreInfoStoreService(void) {
}

VRCentreInfoStoreService* VRCentreInfoStoreService::instance(void) {
	if (!_instance) 
		_instance = new VRCentreInfoStoreService();
	return _instance;
}

bool VRCentreInfoStoreService::get(TCentreInfo& response, const TCID centreId){
	VRCentreInfoStoreServiceClientT client = Configuration::getVRCentreInfoStoreServiceClient();
	if (client->sureOpen()) {
		try {
			client->get(response, centreId);
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

bool VRCentreInfoStoreService::addEvent(const TCID centreId, const TEID eventId){
	VRCentreInfoStoreServiceClientT client = Configuration::getVRCentreInfoStoreServiceClient();
	if (client->sureOpen()) {
		try {
			return client->addEvent(centreId, eventId);
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

bool VRCentreInfoStoreService::removeEvent(const TCID centreId, const TEID eventId){
	VRCentreInfoStoreServiceClientT client = Configuration::getVRCentreInfoStoreServiceClient();
	if (client->sureOpen()) {
		try {
			return client->removeEvent(centreId, eventId);
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