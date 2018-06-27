/* 
 * File:   VREventInfoStoreService.h
 * Author: longmd
 *
 * Created on December 31, 2015, 3:32 PM
 */

#ifndef VREVENTINFOSTORESERVICE_H
#define	VREVENTINFOSTORESERVICE_H

#include "vr_shared_types.h"

using namespace CRDHN::VR::Data;

class VREventInfoStoreService {
public:
	VREventInfoStoreService(void);
	virtual ~VREventInfoStoreService(void);

	static VREventInfoStoreService* instance(void);
	
	bool get(TEventInfo& response, const TEID eventId);
	int32_t getEventStatus(const TEID eventId);
	bool getInjectionOrder(TInjectionOrderInfo& response, const TEID eventId, const TTIME childrenDateOfBirth);
	bool getExtendedInjectionOrder(TInjectionOrderInfo& response, const TEID eventId, const TTIME childrenDateOfBirth, const int32_t desiredOrderNumber);
	bool getNewRegistrationCode(std::string& response, const CRDHN::VR::Data::TEID eventId);
	
	bool addRegistration(const TEID eventId, const TRID registrationId);
	bool addExtendedRegistration(const TEID eventId, const TRID registrationId);

private:
	static VREventInfoStoreService* _instance;
};

#endif	/* VREVENTINFOSTORESERVICE_H */

