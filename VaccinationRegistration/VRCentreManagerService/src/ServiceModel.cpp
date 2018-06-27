/* 
 * File:   ServiceModel.cpp
 * Author: longmd
 * 
 * Created on December 30, 2014, 11:28 AM
 */

#include "ServiceModel.h"
#include "VRCentreInfoStoreService.h"
#include "VRCommonStoreService.h"
#include "Configuration.h"
#include "VRVaccineInfoStoreService.h"
#include <iostream>
#include <sstream>

using namespace std;
using namespace CRDHN::VR::Data;

ServiceModel::ServiceModel(void) {
}

ServiceModel::~ServiceModel(void) {
}

void ServiceModel::createCentre(CRDHN::VR::Response::TCMCreateCentreResponse& response, const TCentreInfo& centreInfo){
	TCID centreId = VRCentreInfoStoreService::instance()->add(centreInfo);
	if (centreId <= 0){
		response.error.__set_errorCode(CRDHN::VR::Response::EC_CENTRE_INFO);
		return;
	}
	if (!VRCommonStoreService::instance()->addItem(Configuration::_VRCommonKeyListCentres, centreId)) {
		response.error.__set_errorCode(CRDHN::VR::Response::EC_COMMON_STORE);
		return;
	}

	response.error.__set_errorCode(CRDHN::VR::Response::EC_OK);
	response.__set_centreId(centreId);
}

void ServiceModel::getCentreInfo(CRDHN::VR::Response::TCMGetCentreInfoResponse& response, const TCID centreId){
	TCentreInfo centreInfo;
	if (!VRCentreInfoStoreService::instance()->get(centreInfo, centreId)) {
		response.error.__set_errorCode(CRDHN::VR::Response::EC_CENTRE_INFO);
		return;
	}
	response.error.__set_errorCode(CRDHN::VR::Response::EC_OK);
	response.__set_centreInfo(centreInfo);
}

void ServiceModel::updateCentreInfo(CRDHN::VR::Response::TError& response, const TCID centreId, const TCentreInfo& centreInfo){
	if (!VRCentreInfoStoreService::instance()->update(centreId, centreInfo)) {
		response.__set_errorCode(CRDHN::VR::Response::EC_CENTRE_INFO);
		return;
	}
	response.__set_errorCode(CRDHN::VR::Response::EC_OK);
}

void ServiceModel::removeCentre(CRDHN::VR::Response::TError& response, const TCID centreId){
	if (!VRCommonStoreService::instance()->removeItem(Configuration::_VRCommonKeyListCentres, centreId)) {
		response.__set_errorCode(CRDHN::VR::Response::EC_COMMON_STORE);
		return;
	}
	if (!VRCentreInfoStoreService::instance()->remove(centreId)) {
		response.__set_errorCode(CRDHN::VR::Response::EC_CENTRE_INFO);
		return;
	}
	response.__set_errorCode(CRDHN::VR::Response::EC_OK);
}

void ServiceModel::getCentres(CRDHN::VR::Response::TCMGetCentresResponse& response){
	std::vector<TCID> centreIds;
	std::vector<TCentreInfo> centreInfos;
	if (!VRCommonStoreService::instance()->getItems(centreIds, Configuration::_VRCommonKeyListCentres)){
		response.error.__set_errorCode(CRDHN::VR::Response::EC_COMMON_STORE);
		return;
	}
	for (int i=0; i<centreIds.size(); i++){
		TCentreInfo centreInfo;
		if (!VRCentreInfoStoreService::instance()->get(centreInfo, centreIds[i])) {
			response.error.__set_errorCode(CRDHN::VR::Response::EC_CENTRE_INFO);
			return;
		}
		centreInfos.push_back(centreInfo);
	}
	response.error.__set_errorCode(CRDHN::VR::Response::EC_OK);
	response.__set_centres(centreInfos);
}

void ServiceModel::addVaccine(CRDHN::VR::Response::TCMAddVaccineResponse& response, const CRDHN::VR::Data::TCID centreId, const CRDHN::VR::Data::TVaccineInfo& vaccineInfo){
	TVID vaccineId = VRVaccineInfoStoreService::instance()->add(vaccineInfo);

	if (vaccineId <= 0){
		response.error.__set_errorCode(CRDHN::VR::Response::EC_VACCINE_INFO);
		return;
	}
	
	if (!VRCentreInfoStoreService::instance()->addVaccine(centreId, vaccineId)){
		response.error.__set_errorCode(CRDHN::VR::Response::EC_CENTRE_INFO);
		return;
	}

	response.error.__set_errorCode(CRDHN::VR::Response::EC_OK);
	response.__set_vaccineId(vaccineId);
}

void ServiceModel::getVaccineInfo(CRDHN::VR::Response::TCMGetVaccineInfoResponse& response, const CRDHN::VR::Data::TVID vaccineId){
	TVaccineInfo vaccineInfo;
	if (!VRVaccineInfoStoreService::instance()->get(vaccineInfo, vaccineId)){
		response.error.__set_errorCode(CRDHN::VR::Response::EC_VACCINE_INFO);
		return;
	}

	response.error.__set_errorCode(CRDHN::VR::Response::EC_OK);
	response.__set_vaccineInfo(vaccineInfo);
}

void ServiceModel::updateVaccineInfo(CRDHN::VR::Response::TError& response, const CRDHN::VR::Data::TVID vaccineId, const CRDHN::VR::Data::TVaccineInfo& vaccineInfo){
	if (!VRVaccineInfoStoreService::instance()->update(vaccineId, vaccineInfo)){
		response.__set_errorCode(CRDHN::VR::Response::EC_VACCINE_INFO);
		return;
	}

	response.__set_errorCode(CRDHN::VR::Response::EC_OK);
}

void ServiceModel::removeVaccine(CRDHN::VR::Response::TError& response, const CRDHN::VR::Data::TCID centreId, const CRDHN::VR::Data::TVID vaccineId){
	if (!VRCentreInfoStoreService::instance()->removeVaccine(centreId, vaccineId)){
		response.__set_errorCode(CRDHN::VR::Response::EC_CENTRE_INFO);
		return;
	}
	
	if (!VRVaccineInfoStoreService::instance()->remove(vaccineId)){
		response.__set_errorCode(CRDHN::VR::Response::EC_VACCINE_INFO);
		return;
	}

	response.__set_errorCode(CRDHN::VR::Response::EC_OK);
}

void ServiceModel::getVaccines(CRDHN::VR::Response::TCMGetVaccinesResponse& response, const CRDHN::VR::Data::TCID centreId){
	TCentreInfo centreInfo;
	if (!VRCentreInfoStoreService::instance()->get(centreInfo, centreId)) {
		response.error.__set_errorCode(CRDHN::VR::Response::EC_CENTRE_INFO);
		return;
	}
	
	std::vector<TVaccineInfo>  vaccines;
	for (int i = 0; i < centreInfo.vaccineIds.size(); i++) {
		TVaccineInfo vaccineInfo;
		if (!VRVaccineInfoStoreService::instance()->get(vaccineInfo, centreInfo.vaccineIds[i])) {
			response.error.__set_errorCode(CRDHN::VR::Response::EC_VACCINE_INFO);
			return;
		}
		vaccines.push_back(vaccineInfo);
	}
	
	response.error.__set_errorCode(CRDHN::VR::Response::EC_OK);
	response.__set_vaccines(vaccines);
}