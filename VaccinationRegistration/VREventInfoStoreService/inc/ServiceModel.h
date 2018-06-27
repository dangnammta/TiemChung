/* 
 * File:   ServiceModel.h
 * Author: longmd
 *
 * Created on December 25, 2014, 9:28 AM
 */

#ifndef SERVICEMODEL_H
#define	SERVICEMODEL_H

#include "Types.h"

using namespace CRDHN::VR::Data;

class ServiceModel {
public:
	ServiceModel(void);
	virtual ~ServiceModel(void);
	
	Poco::FastMutex _mutex;
	
	TEID add(const TEventInfo& eventInfo);
	bool update(const TEID eventId, const TEventInfo& eventInfo);
	void get(TEventInfo& response, const TEID eventId);
	void getBasicInfo(TEventBasicInfo& response, const TEID eventId);
	bool remove(const TEID eventId);
	
	int32_t getEventStatus(const TEID eventId);
	void getInjectionOrder(TInjectionOrderInfo& response, const TEID eventId, const TTIME childrenDateOfBirth);
	void getExtendedInjectionOrder(TInjectionOrderInfo& response, const TEID eventId, const TTIME childrenDateOfBirth, const int32_t desiredOrderNumber);
	void getNewRegistrationCode(std::string& response, const CRDHN::VR::Data::TEID eventId);
	
	bool addRegistration(const TEID eventId, const TRID registrationId);
	bool addExtendedRegistration(const TEID eventId, const TRID registrationId);
	bool removeRegistration(const TEID eventId, const TRID registrationId);
	void getRegistrations(std::vector<TRID> & response, const TEID eventId);
	
	bool addVaccinatedChildren(const TEID eventId);
	bool removeVaccinatedChildren(const TEID eventId);
};

#endif	/* SERVICEMODEL_H */

