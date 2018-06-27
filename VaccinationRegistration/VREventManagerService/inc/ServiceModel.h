/* 
 * File:   ServiceModel.h
 * Author: longmd
 *
 * Created on December 30, 2014, 11:28 AM
 */

#ifndef SERVICEMODEL_H
#define	SERVICEMODEL_H

#include <vector>
#include "Types.h"

using namespace CRDHN::VR::Data;

class ServiceModel {
public:
	ServiceModel(void);
	virtual ~ServiceModel(void);
	
	void createEvent(CRDHN::VR::Response::TVMCreateEventResponse& response, const TEventInfo& eventInfo);
	void updateEventInfo(CRDHN::VR::Response::TError& response, const TEID eventId, const TEventInfo& eventInfo);
	void getEventInfo(CRDHN::VR::Response::TVMGetEventInfoResponse& response, const TEID eventId);
	void removeEvent(CRDHN::VR::Response::TError& response, const TEID eventId);
	void getEvents(CRDHN::VR::Response::TVMGetEventsResponse& response, const TCID centreId);
	
	void updateRegistration(CRDHN::VR::Response::TError& response, const  ::TRID registrationId, const TRegistrationInfo& registrationInfo);
	void removeRegistration(CRDHN::VR::Response::TError& response, const TRID registrationId);
	void getRegistration(CRDHN::VR::Response::TVMGetRegistrationResponse& response, const TRID registrationId);
	void getRegistrations(CRDHN::VR::Response::TVMGetRegistrationsResponse& response, const TEID eventId);
	void updateInjectionStatus(CRDHN::VR::Response::TError& response, const TRID registrationId, const int32_t injectionStatus);
};

#endif	/* SERVICEMODEL_H */

