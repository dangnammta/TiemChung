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
		
	TEID add(const TEventInfo& eventInfo);
	bool update(const TEID eventId, const TEventInfo& eventInfo);
	bool get(TEventInfo& response, const TEID eventId);
	bool getBasicInfo(TEventBasicInfo& response, const TEID eventId);
	bool remove(const TEID eventId);

	bool removeRegistration(const TEID eventId, const TRID registrationId);
	bool getRegistrations(std::vector<TRID> & response, const TEID eventId);
	bool getExtraRegistrations(std::vector<TRID> & response, const TEID eventId);
	
	bool addVaccinatedChildren(const TEID eventId);
	bool removeVaccinatedChildren(const TEID eventId);

private:
	static VREventInfoStoreService* _instance;
};

#endif	/* VREVENTINFOSTORESERVICE_H */

