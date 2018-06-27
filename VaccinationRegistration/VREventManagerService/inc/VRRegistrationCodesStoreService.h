/* 
 * File:   VRRegistrationCodesStoreService.h
 * Author: longmd
 *
 * Created on January 27, 2016, 10:52 AM
 */

#ifndef VRREGISTRATIONCODESSTORESERVICE_H
#define	VRREGISTRATIONCODESSTORESERVICE_H

#include "vr_shared_types.h"

using namespace CRDHN::VR::Data;

class VRRegistrationCodesStoreService {
public:
	VRRegistrationCodesStoreService(void);
	virtual ~VRRegistrationCodesStoreService(void);

	static VRRegistrationCodesStoreService* instance(void);
	
	bool add(const TEID eventId, const std::string& registrationCode, const TRID registrationId);
	bool remove(const TEID eventId, const std::string& registrationCode, const TRID registrationId);
	TRID getRegistrationId(const TEID eventId, const std::string& registrationCode);
	void getRegistrationCodes(TRegistrationCodes& response, const TEID eventId);
	bool removeRegistrationCodes(const TEID eventId);

private:
	static VRRegistrationCodesStoreService* _instance;
};

#endif	/* VRREGISTRATIONCODESSTORESERVICE_H */

