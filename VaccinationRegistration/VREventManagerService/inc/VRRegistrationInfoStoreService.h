/* 
 * File:   VRRegistrationInfoStoreService.h
 * Author: longmd
 *
 * Created on December 30, 2015, 10:24 AM
 */

#ifndef VRREGISTRATIONINFOSTORESERVICE_H
#define	VRREGISTRATIONINFOSTORESERVICE_H

#include "vr_shared_types.h"

using namespace CRDHN::VR::Data;

class VRRegistrationInfoStoreService {
public:
	VRRegistrationInfoStoreService(void);
	virtual ~VRRegistrationInfoStoreService(void);

	static VRRegistrationInfoStoreService* instance(void);
	
	TRID add(const TRegistrationInfo registrationInfo);
	bool get(TRegistrationInfo& response, const TRID registrationId);
	bool update(const TRID registrationId, const TRegistrationInfo& registrationInfo);
	bool remove(const TRID registrationId);
	bool updateInjectionStatus(const TRID registrationId, const int32_t injectionStatus);

private:
	static VRRegistrationInfoStoreService* _instance;
};

#endif	/* VRREGISTRATIONINFOSTORESERVICE_H */

