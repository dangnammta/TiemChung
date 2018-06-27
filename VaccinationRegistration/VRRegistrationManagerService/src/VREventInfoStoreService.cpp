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

int32_t VREventInfoStoreService::getEventStatus(const TEID eventId){
	VREventInfoStoreServiceClientT client = Configuration::getVREventInfoStoreServiceClient();
	if (client->sureOpen()) {
		try {
			return client->getEventStatus(eventId);
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

bool VREventInfoStoreService::getInjectionOrder(TInjectionOrderInfo& response, const TEID eventId, const TTIME childrenDateOfBirth){
	VREventInfoStoreServiceClientT client = Configuration::getVREventInfoStoreServiceClient();
	if (client->sureOpen()) {
		try {
			client->getInjectionOrder(response, eventId, childrenDateOfBirth);
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

bool VREventInfoStoreService::getExtendedInjectionOrder(TInjectionOrderInfo& response, const TEID eventId, const TTIME childrenDateOfBirth, const int32_t desiredOrderNumber){
	VREventInfoStoreServiceClientT client = Configuration::getVREventInfoStoreServiceClient();
	if (client->sureOpen()) {
		try {
			client->getExtendedInjectionOrder(response, eventId, childrenDateOfBirth, desiredOrderNumber);
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

bool VREventInfoStoreService::getNewRegistrationCode(std::string& response, const CRDHN::VR::Data::TEID eventId){
	VREventInfoStoreServiceClientT client = Configuration::getVREventInfoStoreServiceClient();
	if (client->sureOpen()) {
		try {
			client->getNewRegistrationCode(response, eventId);
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

bool VREventInfoStoreService::addRegistration(const TEID eventId, const TRID registrationId){
	VREventInfoStoreServiceClientT client = Configuration::getVREventInfoStoreServiceClient();
	if (client->sureOpen()) {
		try {
			return client->addRegistration(eventId, registrationId);
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

bool VREventInfoStoreService::addExtendedRegistration(const TEID eventId, const TRID registrationId){
	VREventInfoStoreServiceClientT client = Configuration::getVREventInfoStoreServiceClient();
	if (client->sureOpen()) {
		try {
			return client->addExtendedRegistration(eventId, registrationId);
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