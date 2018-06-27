/* 
 * File:   VRVaccineInfoStoreService.h
 * Author: longmd
 *
 * Created on January 1, 2016, 2:20 AM
 */

#ifndef VRVACCINEINFOSTORESERVICE_H
#define	VRVACCINEINFOSTORESERVICE_H

#include "vr_shared_types.h"

using namespace CRDHN::VR::Data;

class VRVaccineInfoStoreService {
public:
	VRVaccineInfoStoreService(void);
	virtual ~VRVaccineInfoStoreService(void);

	static VRVaccineInfoStoreService* instance(void);
	
	bool get(TVaccineInfo& response, const TVID vaccineId);

private:
	static VRVaccineInfoStoreService* _instance;
};

#endif	/* VRVACCINEINFOSTORESERVICE_H */

