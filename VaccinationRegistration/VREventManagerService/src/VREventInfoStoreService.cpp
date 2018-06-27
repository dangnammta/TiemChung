/* 
 * File:   VREventInfoStoreService.cpp
 * Author: longmd
 * 
 * Created on December 31, 2015, 3:32 PM
 */

#include "VREventInfoStoreService.h"
#include "Configuration.h"

using namespace std;
using namespace CRDHN::VR::Data;

VREventInfoStoreService* VREventInfoStoreService::_instance = NULL;

VREventInfoStoreService::VREventInfoStoreService(void) {
}

VREventInfoStoreService::~VREventInfoStoreService(void) {
}

VREventInfoStoreService* VREventInfoStoreService::instance(void) {
	if (!_instance) 
		_instance = new VREventInfoStoreService();
	return _instance;
}

TEID VREventInfoStoreService::add(const TEventInfo& eventInfo){
	VREventInfoStoreServiceClientT client = Configuration::getVREventInfoStoreServiceClient();
	if (client->sureOpen()) {
		try {
			return client->add(eventInfo);
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

bool VREventInfoStoreService::update(const TEID eventId, const TEventInfo& eventInfo){
	VREventInfoStoreServiceClientT client = Configuration::getVREventInfoStoreServiceClient();
	if (client->sureOpen()) {
		try {
			return client->update(eventId, eventInfo);
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

bool VREventInfoStoreService::get(TEventInfo& response, const TEID eventId){
	VREventInfoStoreServiceClientT client = Configuration::getVREventInfoStoreServiceClient();
	if (client->sureOpen()) {
		try {
			client->get(response, eventId);
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

bool VREventInfoStoreService::getBasicInfo(TEventBasicInfo& response, const TEID eventId){
	VREventInfoStoreServiceClientT client = Configuration::getVREventInfoStoreServiceClient();
	if (client->sureOpen()) {
		try {
			client->getBasicInfo(response, eventId);
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

bool VREventInfoStoreService::remove(const TEID eventId){
	VREventInfoStoreServiceClientT client = Configuration::getVREventInfoStoreServiceClient();
	if (client->sureOpen()) {
		try {
			return client->remove(eventId);
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

bool VREventInfoStoreService::removeRegistration(const TEID eventId, const TRID registrationId){
	VREventInfoStoreServiceClientT client = Configuration::getVREventInfoStoreServiceClient();
	if (client->sureOpen()) {
		try {
			return client->removeRegistration(eventId, registrationId);
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

bool VREventInfoStoreService::getRegistrations(std::vector<TRID> & response, const TEID eventId){
	VREventInfoStoreServiceClientT client = Configuration::getVREventInfoStoreServiceClient();
	if (client->sureOpen()) {
		try {
			client->getRegistrations(response, eventId);
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

bool VREventInfoStoreService::addVaccinatedChildren(const TEID eventId){
	VREventInfoStoreServiceClientT client = Configuration::getVREventInfoStoreServiceClient();
	if (client->sureOpen()) {
		try {
			return client->addVaccinatedChildren(eventId);
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

bool VREventInfoStoreService::removeVaccinatedChildren(const TEID eventId){
	VREventInfoStoreServiceClientT client = Configuration::getVREventInfoStoreServiceClient();
	if (client->sureOpen()) {
		try {
			return client->removeVaccinatedChildren(eventId);
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