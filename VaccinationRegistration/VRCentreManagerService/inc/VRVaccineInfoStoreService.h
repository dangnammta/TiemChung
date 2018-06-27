/* 
 * File:   VRVaccineInfoStoreService.h
 * Author: longmd
 *
 * Created on March 9, 2016, 3:41 PM
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
	
	TVID add(const TVaccineInfo& vaccineInfo);
	bool get(TVaccineInfo& response, const TVID vaccineId);
	bool update(const TVID vaccineId, const TVaccineInfo& vaccineInfo);
	bool remove(const TVID vaccineId);

private:
	static VRVaccineInfoStoreService* _instance;
};

#endif	/* VRVACCINEINFOSTORESERVICE_H */

