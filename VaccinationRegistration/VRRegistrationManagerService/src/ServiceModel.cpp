/* 
 * File:   ServiceModel.cpp
 * Author: longmd
 * 
 * Created on December 30, 2014, 11:28 AM
 */

#include "ServiceModel.h"
#include "VRRegistrationInfoStoreService.h"
#include "VRDailyRegistrationsStoreService.h"
#include "VRDailyInjectionsStoreService.h"
#include "VREventInfoStoreService.h"
#include "Configuration.h"
#include <iostream>
#include <Poco/LocalDateTime.h>
#include <Poco/DateTimeFormat.h>
#include <Poco/DateTimeFormatter.h>
#include <sstream>
#include <ctime>

using namespace std;
using namespace CRDHN::VR::Data;

ServiceModel::ServiceModel(void) {
}

ServiceModel::~ServiceModel(void) {
}

void ServiceModel::getEventStatus(CRDHN::VR::Response::TError& response, const TEID eventId){
	int32_t eventStatus = VREventInfoStoreService::instance()->getEventStatus(eventId);
	switch (eventStatus){
		case TES_AVAILABLE: 
			response.errorCode = CRDHN::VR::Response::EC_REGISTRATION_AVAILABLE;
			break;
		case TES_PENDING: 
			response.errorCode = CRDHN::VR::Response::EC_REGISTRATION_PENDING;
			break;
		case TES_DISABLE: 
			response.errorCode = CRDHN::VR::Response::EC_REGISTRATION_DISABLE;
			break;
		default:
			response.errorCode = CRDHN::VR::Response::EC_SYSTEM;
	}
}

string getKeyDate(const TEID eventId, const TTIME _time){
	tm *ltm = localtime(&_time);
	stringstream ss;
	ss<<eventId<<"#";
	ss<<ltm->tm_mday<<"/"<<1 + ltm->tm_mon<<"/"<<1900 + ltm->tm_year;
	cout << ss.str() << endl;
	cout<<ltm->tm_hour<<":"<<ltm->tm_min<<":"<<ltm->tm_sec << endl;
	cout<<ltm->tm_mday<<"/"<<1 + ltm->tm_mon<<"/"<<1900 + ltm->tm_year << endl;
	return ss.str();
}

void ServiceModel::addRegistration(CRDHN::VR::Response::TRMAddRegistrationResponse& response, const TRegistrationInfo& registrationInfo){
	TTIME currentTime = Poco::Timestamp().epochTime();
	string keyDateRegistration = getKeyDate(registrationInfo.eventId, currentTime);

	TRID existedRId = VRDailyRegistrationsStoreService::instance()->getRegistrationId(keyDateRegistration, registrationInfo.childrenCarrierPIN);
	
	if (existedRId >= 0){
		response.error.__set_errorCode(CRDHN::VR::Response::EC_PIN_REGISTERED);
		return;
	}
	
	TInjectionOrderInfo injectionOrderInfo;
	if (!VREventInfoStoreService::instance()->getInjectionOrder(injectionOrderInfo, registrationInfo.eventId, registrationInfo.childrenDateOfBirth)){
		response.error.__set_errorCode(CRDHN::VR::Response::EC_EVENT_INFO);
		return;
	}
		
	if (injectionOrderInfo.injectionOrderNumber < 0){
		response.error.__set_errorCode(CRDHN::VR::Response::EC_REGISTRATION_FULL);
		return;
	}
		
	TRegistrationInfo newRegistrationInfo;
	newRegistrationInfo.__set_registrationType(registrationInfo.registrationType);
	newRegistrationInfo.__set_registrationTime(currentTime);
	newRegistrationInfo.__set_eventId(registrationInfo.eventId);
	newRegistrationInfo.__set_childrenName(registrationInfo.childrenName);
	newRegistrationInfo.__set_childrenDateOfBirth(registrationInfo.childrenDateOfBirth);
	newRegistrationInfo.__set_childrenCarrierName(registrationInfo.childrenCarrierName);
	newRegistrationInfo.__set_childrenCarrierPIN(registrationInfo.childrenCarrierPIN);
	newRegistrationInfo.__set_childrenCarrierPhoneNumber(registrationInfo.childrenCarrierPhoneNumber);
	newRegistrationInfo.__set_childrenCarrierEmail(registrationInfo.childrenCarrierEmail);
	newRegistrationInfo.__set_childrenCarrierAddress(registrationInfo.childrenCarrierAddress);
	newRegistrationInfo.__set_injectionStatus(TIS_REGISTED);
	newRegistrationInfo.__set_injectionRealTime(-1);
	newRegistrationInfo.__set_registrationCode(injectionOrderInfo.registrationCode);
	newRegistrationInfo.__set_injectionOrderNumber(injectionOrderInfo.injectionOrderNumber);
	newRegistrationInfo.__set_injectionTime(injectionOrderInfo.injectionTime);
	newRegistrationInfo.__set_injectionDate(injectionOrderInfo.injectionDate);
	
	TRID registrationId = VRRegistrationInfoStoreService::instance()->add(newRegistrationInfo);
	if (registrationId < 0){
		response.error.__set_errorCode(CRDHN::VR::Response::EC_REGISTRATION_INFO);
		return;
	}
	
	if (VRDailyRegistrationsStoreService::instance()->add(keyDateRegistration, registrationInfo.childrenCarrierPIN, registrationId) < 0){
		response.error.__set_errorCode(CRDHN::VR::Response::EC_DAILY_REGISTRATIONS);
		return;
	}
	
//	if (!VRDailyInjectionsStoreService::instance()->add(keyDateRegistration, registrationId)){
//		response.error.__set_errorCode(CRDHN::VR::Response::EC_DAILY_INJECTION);
//		return;
//	}
	
	if (!VREventInfoStoreService::instance()->addRegistration(newRegistrationInfo.eventId, registrationId)){
		response.error.__set_errorCode(CRDHN::VR::Response::EC_EVENT_INFO);
		return;
	}
	
	response.error.__set_errorCode(CRDHN::VR::Response::EC_OK);
	response.__set_registrationCode(newRegistrationInfo.registrationCode);
	response.__set_injectionOrderNumber(newRegistrationInfo.injectionOrderNumber);
	response.__set_injectionDate(newRegistrationInfo.injectionDate);
	response.__set_injectionTime(newRegistrationInfo.injectionTime);
}

void ServiceModel::addExtraRegistration(CRDHN::VR::Response::TRMAddExtraRegistrationResponse& response, const TRegistrationInfo& registrationInfo){
	TTIME currentTime = Poco::Timestamp().epochTime();
	string keyDateRegistration = getKeyDate(registrationInfo.eventId, currentTime);
	
	TRID existedRId = VRDailyRegistrationsStoreService::instance()->getRegistrationId(keyDateRegistration, registrationInfo.childrenCarrierPIN);
	
	if (existedRId >= 0){
		response.error.__set_errorCode(CRDHN::VR::Response::EC_PIN_REGISTERED);
		return;
	}
	
	TEventInfo eventInfo;
	if (!VREventInfoStoreService::instance()->get(eventInfo, registrationInfo.eventId)){
		response.error.__set_errorCode(CRDHN::VR::Response::EC_EVENT_INFO);
		return;
	}
	
	string registrationCode;
	if (!VREventInfoStoreService::instance()->getNewRegistrationCode(registrationCode, registrationInfo.eventId)){
		response.error.__set_errorCode(CRDHN::VR::Response::EC_EVENT_INFO);
		return;
	}
	
	TRegistrationInfo newRegistrationInfo;
	newRegistrationInfo.__set_registrationType(registrationInfo.registrationType);
	newRegistrationInfo.__set_registrationTime(eventInfo.startTimeRegistration);
	newRegistrationInfo.__set_eventId(registrationInfo.eventId);
	newRegistrationInfo.__set_childrenName(registrationInfo.childrenName);
	newRegistrationInfo.__set_childrenDateOfBirth(registrationInfo.childrenDateOfBirth);
	newRegistrationInfo.__set_childrenCarrierName(registrationInfo.childrenCarrierName);
	newRegistrationInfo.__set_childrenCarrierPIN(registrationInfo.childrenCarrierPIN);
	newRegistrationInfo.__set_childrenCarrierPhoneNumber(registrationInfo.childrenCarrierPhoneNumber);
	newRegistrationInfo.__set_childrenCarrierEmail(registrationInfo.childrenCarrierEmail);
	newRegistrationInfo.__set_childrenCarrierAddress(registrationInfo.childrenCarrierAddress);
	newRegistrationInfo.__set_injectionStatus(TIS_REGISTED);
	newRegistrationInfo.__set_injectionRealTime(-1);
	newRegistrationInfo.__set_registrationCode(registrationCode);
	newRegistrationInfo.__set_injectionOrderNumber(0);
	newRegistrationInfo.__set_injectionTime(registrationInfo.injectionTime);
	newRegistrationInfo.__set_injectionDate(registrationInfo.injectionDate);
	
	TRID registrationId = VRRegistrationInfoStoreService::instance()->add(newRegistrationInfo);
	if (registrationId < 0){
		response.error.__set_errorCode(CRDHN::VR::Response::EC_REGISTRATION_INFO);
		return;
	}
	
	if (VRDailyRegistrationsStoreService::instance()->add(keyDateRegistration, registrationInfo.childrenCarrierPIN, registrationId) < 0){
		response.error.__set_errorCode(CRDHN::VR::Response::EC_DAILY_REGISTRATIONS);
		return;
	}
	
//	if (!VRDailyInjectionsStoreService::instance()->add(keyDateRegistration, registrationId)){
//		response.error.__set_errorCode(CRDHN::VR::Response::EC_DAILY_INJECTION);
//		return;
//	}
	
	if (!VREventInfoStoreService::instance()->addRegistration(newRegistrationInfo.eventId, registrationId)){
		response.error.__set_errorCode(CRDHN::VR::Response::EC_EVENT_INFO);
		return;
	}
	
	response.error.__set_errorCode(CRDHN::VR::Response::EC_OK);
	response.__set_registrationCode(newRegistrationInfo.registrationCode);
	response.__set_injectionOrderNumber(newRegistrationInfo.injectionOrderNumber);
	response.__set_injectionDate(newRegistrationInfo.injectionDate);
	response.__set_injectionTime(newRegistrationInfo.injectionTime);
}

void ServiceModel::addExtendedRegistration(CRDHN::VR::Response::TRMAddRegistrationResponse& response, const TRegistrationInfo& registrationInfo, const int32_t desiredOrderNumber){
	TTIME currentTime = Poco::Timestamp().epochTime();
	string keyDateRegistration = getKeyDate(registrationInfo.eventId, currentTime);

	TRID existedRId = VRDailyRegistrationsStoreService::instance()->getRegistrationId(keyDateRegistration, registrationInfo.childrenCarrierPIN);
	
	if (existedRId >= 0){
		response.error.__set_errorCode(CRDHN::VR::Response::EC_PIN_REGISTERED);
		return;
	}
	
	TInjectionOrderInfo injectionOrderInfo;
	if (!VREventInfoStoreService::instance()->getExtendedInjectionOrder(injectionOrderInfo, registrationInfo.eventId, registrationInfo.childrenDateOfBirth, desiredOrderNumber)){
		response.error.__set_errorCode(CRDHN::VR::Response::EC_EVENT_INFO);
		return;
	}
		
	if (injectionOrderInfo.injectionOrderNumber < 0){
		response.error.__set_errorCode(CRDHN::VR::Response::EC_REGISTRATION_FULL);
		return;
	}
		
	TRegistrationInfo newRegistrationInfo;
	newRegistrationInfo.__set_registrationType(registrationInfo.registrationType);
	newRegistrationInfo.__set_registrationTime(currentTime);
	newRegistrationInfo.__set_eventId(registrationInfo.eventId);
	newRegistrationInfo.__set_childrenName(registrationInfo.childrenName);
	newRegistrationInfo.__set_childrenDateOfBirth(registrationInfo.childrenDateOfBirth);
	newRegistrationInfo.__set_childrenCarrierName(registrationInfo.childrenCarrierName);
	newRegistrationInfo.__set_childrenCarrierPIN(registrationInfo.childrenCarrierPIN);
	newRegistrationInfo.__set_childrenCarrierPhoneNumber(registrationInfo.childrenCarrierPhoneNumber);
	newRegistrationInfo.__set_childrenCarrierEmail(registrationInfo.childrenCarrierEmail);
	newRegistrationInfo.__set_childrenCarrierAddress(registrationInfo.childrenCarrierAddress);
	newRegistrationInfo.__set_injectionStatus(TIS_REGISTED);
	newRegistrationInfo.__set_injectionRealTime(-1);
	newRegistrationInfo.__set_registrationCode(injectionOrderInfo.registrationCode);
	newRegistrationInfo.__set_injectionOrderNumber(injectionOrderInfo.injectionOrderNumber);
	newRegistrationInfo.__set_injectionTime(injectionOrderInfo.injectionTime);
	newRegistrationInfo.__set_injectionDate(injectionOrderInfo.injectionDate);
	
	TRID registrationId = VRRegistrationInfoStoreService::instance()->add(newRegistrationInfo);
	if (registrationId < 0){
		response.error.__set_errorCode(CRDHN::VR::Response::EC_REGISTRATION_INFO);
		return;
	}
	
	if (VRDailyRegistrationsStoreService::instance()->add(keyDateRegistration, registrationInfo.childrenCarrierPIN, registrationId) < 0){
		response.error.__set_errorCode(CRDHN::VR::Response::EC_DAILY_REGISTRATIONS);
		return;
	}
//	
//	if (!VRDailyInjectionsStoreService::instance()->add(keyDate, registrationId)){
//		response.error.__set_errorCode(CRDHN::VR::Response::EC_DAILY_INJECTION);
//		return;
//	}
	
	if (!VREventInfoStoreService::instance()->addExtendedRegistration(newRegistrationInfo.eventId, registrationId)){
		response.error.__set_errorCode(CRDHN::VR::Response::EC_EVENT_INFO);
		return;
	}
	
	response.error.__set_errorCode(CRDHN::VR::Response::EC_OK);
	response.__set_registrationCode(newRegistrationInfo.registrationCode);
	response.__set_injectionOrderNumber(newRegistrationInfo.injectionOrderNumber);
	response.__set_injectionDate(newRegistrationInfo.injectionDate);
	response.__set_injectionTime(newRegistrationInfo.injectionTime);
}
