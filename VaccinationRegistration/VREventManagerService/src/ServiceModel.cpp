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
#include "VRCentreInfoStoreService.h"
#include "VRVaccineInfoStoreService.h"
#include <iostream>
#include <Poco/LocalDateTime.h>
#include <Poco/DateTimeFormat.h>
#include <Poco/DateTimeFormatter.h>
#include <sstream>

using namespace std;
using namespace CRDHN::VR::Data;

ServiceModel::ServiceModel(void) {
}

ServiceModel::~ServiceModel(void) {
}

void ServiceModel::createEvent(CRDHN::VR::Response::TVMCreateEventResponse& response, const TEventInfo& eventInfo) {
	TEID eventId = VREventInfoStoreService::instance()->add(eventInfo);
	if (eventId <= 0) {
		response.error.__set_errorCode(CRDHN::VR::Response::EC_EVENT_INFO);
		return;
	}
	if (!VRCentreInfoStoreService::instance()->addEvent(eventInfo.centreId, eventId)) {
		response.error.__set_errorCode(CRDHN::VR::Response::EC_CENTRE_INFO);
		return;
	}

	response.error.__set_errorCode(CRDHN::VR::Response::EC_OK);
	response.__set_eventId(eventId);
}

void ServiceModel::updateEventInfo(CRDHN::VR::Response::TError& response, const TEID eventId, const TEventInfo& eventInfo) {
	if (!VREventInfoStoreService::instance()->update(eventId, eventInfo)) {
		response.__set_errorCode(CRDHN::VR::Response::EC_EVENT_INFO);
		return;
	}
	response.__set_errorCode(CRDHN::VR::Response::EC_OK);
}

void ServiceModel::getEventInfo(CRDHN::VR::Response::TVMGetEventInfoResponse& response, const TEID eventId) {
	TEventInfo eventInfo;
	if (!VREventInfoStoreService::instance()->get(eventInfo, eventId)) {
		response.error.__set_errorCode(CRDHN::VR::Response::EC_EVENT_INFO);
		return;
	}
	response.error.__set_errorCode(CRDHN::VR::Response::EC_OK);
	response.__set_eventInfo(eventInfo);
}

void ServiceModel::removeEvent(CRDHN::VR::Response::TError& response, const TEID eventId) {
	TEventInfo eventInfo;
	if (!VREventInfoStoreService::instance()->get(eventInfo, eventId)) {
		response.__set_errorCode(CRDHN::VR::Response::EC_EVENT_INFO);
		return;
	}
	if (!VRCentreInfoStoreService::instance()->removeEvent(eventInfo.centreId, eventId)) {
		response.__set_errorCode(CRDHN::VR::Response::EC_CENTRE_INFO);
		return;
	}
	if (!VREventInfoStoreService::instance()->remove(eventId)) {
		response.__set_errorCode(CRDHN::VR::Response::EC_EVENT_INFO);
		return;
	}
	response.__set_errorCode(CRDHN::VR::Response::EC_OK);
}

void ServiceModel::getEvents(CRDHN::VR::Response::TVMGetEventsResponse& response, const TCID centreId) {
	std::vector<TEventBasicInfo> events;
	TCentreInfo centreInfo;
	if (!VRCentreInfoStoreService::instance()->get(centreInfo, centreId)) {
		response.error.__set_errorCode(CRDHN::VR::Response::EC_CENTRE_INFO);
		return;
	}

	for (int i = 0; i < centreInfo.eventIds.size(); i++) {
		TEventBasicInfo eventBasicInfo;
		if (!VREventInfoStoreService::instance()->getBasicInfo(eventBasicInfo, centreInfo.eventIds[i])) {
			response.error.__set_errorCode(CRDHN::VR::Response::EC_EVENT_INFO);
			return;
		}
		if (eventBasicInfo.eventStatus == TES_DELETED)
			continue;
		TVaccineInfo vaccineInfo;
		if (!VRVaccineInfoStoreService::instance()->get(vaccineInfo, eventBasicInfo.vaccineId)) {
			response.error.__set_errorCode(CRDHN::VR::Response::EC_VACCINE_INFO);
			return;
		}
		eventBasicInfo.__set_vaccineName(vaccineInfo.name);
		events.push_back(eventBasicInfo);
	}
	response.error.__set_errorCode(CRDHN::VR::Response::EC_OK);
	response.__set_events(events);
}

void ServiceModel::updateRegistration(CRDHN::VR::Response::TError& response, const ::TRID registrationId, const TRegistrationInfo& registrationInfo) {
	if (!VRRegistrationInfoStoreService::instance()->update(registrationId, registrationInfo)) {
		response.__set_errorCode(CRDHN::VR::Response::EC_REGISTRATION_INFO);
		return;
	}
	response.__set_errorCode(CRDHN::VR::Response::EC_OK);
}

void ServiceModel::removeRegistration(CRDHN::VR::Response::TError& response, const TRID registrationId) {
	TRegistrationInfo registrationInfo;
	if (!VRRegistrationInfoStoreService::instance()->get(registrationInfo, registrationId)) {
		response.__set_errorCode(CRDHN::VR::Response::EC_REGISTRATION_INFO);
		return;
	}

	if (registrationInfo.registrationType != TRT_MANUAL) {
		response.__set_errorCode(CRDHN::VR::Response::EC_NOT_ALLOWED);
		return;
	}

	if (!VREventInfoStoreService::instance()->removeRegistration(registrationInfo.eventId, registrationId)) {
		response.__set_errorCode(CRDHN::VR::Response::EC_EVENT_INFO);
		return;
	}

	if (!VRRegistrationInfoStoreService::instance()->remove(registrationId)) {
		response.__set_errorCode(CRDHN::VR::Response::EC_REGISTRATION_INFO);
		return;
	}
	response.__set_errorCode(CRDHN::VR::Response::EC_OK);
}

void ServiceModel::getRegistration(CRDHN::VR::Response::TVMGetRegistrationResponse& response, const TRID registrationId) {
	TRegistrationInfo registrationInfo;
	if (!VRRegistrationInfoStoreService::instance()->get(registrationInfo, registrationId)) {
		response.error.__set_errorCode(CRDHN::VR::Response::EC_REGISTRATION_INFO);
		return;
	}
	response.error.__set_errorCode(CRDHN::VR::Response::EC_OK);
	response.__set_registrationInfo(registrationInfo);
}

void ServiceModel::getRegistrations(CRDHN::VR::Response::TVMGetRegistrationsResponse& response, const TEID eventId) {
	std::vector<TRID> listRIds;
	std::vector<TRegistrationInfo> registrations;
	if (!VREventInfoStoreService::instance()->getRegistrations(listRIds, eventId)) {
		response.error.__set_errorCode(CRDHN::VR::Response::EC_EVENT_INFO);
		return;
	}

	for (int i = 0; i < listRIds.size(); i++) {
		TRegistrationInfo registrationInfo;
		if (!VRRegistrationInfoStoreService::instance()->get(registrationInfo, listRIds[i])) {
			response.error.__set_errorCode(CRDHN::VR::Response::EC_REGISTRATION_INFO);
			return;
		}
		registrations.push_back(registrationInfo);
	}
	response.error.__set_errorCode(CRDHN::VR::Response::EC_OK);
	response.__set_registrations(registrations);
}

void ServiceModel::updateInjectionStatus(CRDHN::VR::Response::TError& response, const TRID registrationId, const int32_t injectionStatus) {
	TRegistrationInfo registrationInfo;
	if (!VRRegistrationInfoStoreService::instance()->get(registrationInfo, registrationId)) {
		response.__set_errorCode(CRDHN::VR::Response::EC_REGISTRATION_INFO);
		return;
	}
	if (!VRRegistrationInfoStoreService::instance()->updateInjectionStatus(registrationId, injectionStatus)) {
		response.__set_errorCode(CRDHN::VR::Response::EC_REGISTRATION_INFO);
		return;
	}

	if (registrationInfo.injectionStatus == TIS_REGISTED &&
			injectionStatus == TIS_VACCINATED) {
		if (!VREventInfoStoreService::instance()->addVaccinatedChildren(registrationInfo.eventId)) {
			response.__set_errorCode(CRDHN::VR::Response::EC_EVENT_INFO);
			return;
		}
	}
	else
		if (registrationInfo.injectionStatus == TIS_VACCINATED &&
			injectionStatus == TIS_REGISTED) {
		if (!VREventInfoStoreService::instance()->removeVaccinatedChildren(registrationInfo.eventId)) {
			response.__set_errorCode(CRDHN::VR::Response::EC_EVENT_INFO);
			return;
		}
	}
	response.__set_errorCode(CRDHN::VR::Response::EC_OK);
}