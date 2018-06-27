/* 
 * File:   ServiceModel.h
 * Author: longmd
 *
 * Created on December 25, 2014, 9:28 AM
 */

#ifndef SERVICEMODEL_H
#define	SERVICEMODEL_H

#include "Types.h"

using namespace CRDHN::VR::Data;

class ServiceModel {
public:
	ServiceModel(void);
	virtual ~ServiceModel(void);
	
	int32_t add(const std::string& key, const std::string& PIN, const TRID registrationId);
	void get(TDailyRegistrations& _return, const std::string& key);
	int32_t getRegistrationId(const std::string& key, const std::string& PIN);
	bool remove(const std::string& key);
};

#endif	/* SERVICEMODEL_H */

