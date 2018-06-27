/* 
 * File:   VRVaccineInfoStoreService.cpp
 * Author: longmd
 * 
 * Created on January 1, 2016, 2:20 AM
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