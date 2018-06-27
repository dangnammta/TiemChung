/* 
 * File:   VRCentreInfoStoreService.cpp
 * Author: longmd
 * 
 * Created on January 20, 2016, 12:05 PM
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

TCID VRCentreInfoStoreService::add(const TCentreInfo& centreInfo){
	VRCentreInfoStoreServiceClientT client = Configuration::getVRCentreInfoStoreServiceClient();
	if (client->sureOpen()) {
		try {
			return client->add(centreInfo);
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

bool VRCentreInfoStoreService::update(const TCID centreId, const TCentreInfo& centreInfo){
	VRCentreInfoStoreServiceClientT client = Configuration::getVRCentreInfoStoreServiceClient();
	if (client->sureOpen()) {
		try {
			return client->update(centreId, centreInfo);
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

bool VRCentreInfoStoreService::remove(const TCID centreId){
	VRCentreInfoStoreServiceClientT client = Configuration::getVRCentreInfoStoreServiceClient();
	if (client->sureOpen()) {
		try {
			return client->remove(centreId);
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

bool VRCentreInfoStoreService::addVaccine(const CRDHN::VR::Data::TCID centreId, const CRDHN::VR::Data::TVID vaccineId){
	VRCentreInfoStoreServiceClientT client = Configuration::getVRCentreInfoStoreServiceClient();
	if (client->sureOpen()) {
		try {
			return client->addVaccine(centreId, vaccineId);
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

bool VRCentreInfoStoreService::removeVaccine(const CRDHN::VR::Data::TCID centreId, const CRDHN::VR::Data::TVID vaccineId){
	VRCentreInfoStoreServiceClientT client = Configuration::getVRCentreInfoStoreServiceClient();
	if (client->sureOpen()) {
		try {
			return client->removeVaccine(centreId, vaccineId);
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