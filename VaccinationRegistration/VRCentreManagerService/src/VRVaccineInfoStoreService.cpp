/* 
 * File:   VRVaccineInfoStoreService.cpp
 * Author: longmd
 * 
 * Created on January 20, 2016, 12:05 PM
 */

#include "VRVaccineInfoStoreService.h"
#include "Configuration.h"

using namespace std;
using namespace CRDHN::VR::Data;

VRVaccineInfoStoreService* VRVaccineInfoStoreService::_instance = NULL;

VRVaccineInfoStoreService::VRVaccineInfoStoreService(void) {
}

VRVaccineInfoStoreService::~VRVaccineInfoStoreService(void) {
}

VRVaccineInfoStoreService* VRVaccineInfoStoreService::instance(void) {
	if (!_instance) 
		_instance = new VRVaccineInfoStoreService();
	return _instance;
}

TVID VRVaccineInfoStoreService::add(const TVaccineInfo& vaccineInfo){
	VRVaccineInfoStoreServiceClientT client = Configuration::getVRVaccineInfoStoreServiceClient();
	if (client->sureOpen()) {
		try {
			return client->add(vaccineInfo);
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

bool VRVaccineInfoStoreService::get(TVaccineInfo& response, const TVID vaccineId){
	VRVaccineInfoStoreServiceClientT client = Configuration::getVRVaccineInfoStoreServiceClient();
	if (client->sureOpen()) {
		try {
			client->get(response, vaccineId);
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

bool VRVaccineInfoStoreService::update(const TVID vaccineId, const TVaccineInfo& vaccineInfo){
	VRVaccineInfoStoreServiceClientT client = Configuration::getVRVaccineInfoStoreServiceClient();
	if (client->sureOpen()) {
		try {
			return client->update(vaccineId, vaccineInfo);
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

bool VRVaccineInfoStoreService::remove(const TVID vaccineId){
	VRVaccineInfoStoreServiceClientT client = Configuration::getVRVaccineInfoStoreServiceClient();
	if (client->sureOpen()) {
		try {
			return client->remove(vaccineId);
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