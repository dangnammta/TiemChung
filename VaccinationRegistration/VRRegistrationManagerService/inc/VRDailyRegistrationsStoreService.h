/* 
 * File:   VRDailyRegistrationsStoreService.h
 * Author: longmd
 *
 * Created on December 30, 2015, 10:24 AM
 */

#ifndef VRDAILYREGISTRATIONSSTORESERVICE_H
#define	VRDAILYREGISTRATIONSSTORESERVICE_H

#include "vr_shared_types.h"

using namespace CRDHN::VR::Data;

class VRDailyRegistrationsStoreService {
public:
	VRDailyRegistrationsStoreService(void);
	virtual ~VRDailyRegistrationsStoreService(void);

	static VRDailyRegistrationsStoreService* instance(void);
	
	int32_t add(const std::string& key, const std::string& PIN, const TRID rId);
	void get(TDailyRegistrations& response, const std::string& key);
	int32_t getRegistrationId(const std::string& key, const std::string& PIN);
	bool remove(const std::string& key);

private:
	static VRDailyRegistrationsStoreService* _instance;
};

#endif	/* VRDAILYREGISTRATIONSSTORESERVICE_H */

