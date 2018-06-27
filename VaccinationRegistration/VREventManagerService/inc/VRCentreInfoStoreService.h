/* 
 * File:   VRCentreInfoStoreService.h
 * Author: longmd
 *
 * Created on January 2, 2016, 12:02 AM
 */

#ifndef VRCENTREINFOSTORESERVICE_H
#define	VRCENTREINFOSTORESERVICE_H

#include "vr_shared_types.h"

using namespace CRDHN::VR::Data;

class VRCentreInfoStoreService {
public:
	VRCentreInfoStoreService(void);
	virtual ~VRCentreInfoStoreService(void);

	static VRCentreInfoStoreService* instance(void);
		
	bool get(TCentreInfo& response, const TCID centreId);
	bool addEvent(const TCID centreId, const TEID eventId);
	bool removeEvent(const TCID centreId, const TEID eventId);

private:
	static VRCentreInfoStoreService* _instance;
};

#endif	/* VRCENTREINFOSTORESERVICE_H */

