/* 
 * File:   VRCentreInfoStoreService.h
 * Author: longmd
 *
 * Created on January 20, 2016, 12:05 PM
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
	
	TCID add(const TCentreInfo& centreInfo);
	bool get(TCentreInfo& response, const TCID centreId);
	bool update(const TCID centreId, const TCentreInfo& centreInfo);
	bool remove(const TCID centreId);
	bool addVaccine(const CRDHN::VR::Data::TCID centreId, const CRDHN::VR::Data::TVID vaccineId);
	bool removeVaccine(const CRDHN::VR::Data::TCID centreId, const CRDHN::VR::Data::TVID vaccineId);

private:
	static VRCentreInfoStoreService* _instance;
};

#endif	/* VRCENTREINFOSTORESERVICE_H */

