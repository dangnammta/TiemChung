/* 
 * File:   VRDailyInjectionsStoreService.h
 * Author: longmd
 *
 * Created on December 30, 2015, 10:24 AM
 */

#ifndef VRDAILYINJECTIONSSTORESERVICE_H
#define	VRDAILYINJECTIONSSTORESERVICE_H

#include "vr_shared_types.h"

using namespace CRDHN::VR::Data;

class VRDailyInjectionsStoreService {
public:
	VRDailyInjectionsStoreService(void);
	virtual ~VRDailyInjectionsStoreService(void);

	static VRDailyInjectionsStoreService* instance(void);
	bool add(const std::string& key, const TRID rId);
	void get(TDailyInjections& response, const std::string& key);
	bool remove(const std::string& key);

private:
	static VRDailyInjectionsStoreService* _instance;
};

#endif	/* VRDAILYINJECTIONSSTORESERVICE_H */

