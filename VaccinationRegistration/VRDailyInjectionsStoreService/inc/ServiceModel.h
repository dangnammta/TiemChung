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
	
	bool add(const std::string& key, const TRID registrationId);
	void get(TDailyInjections& _return, const std::string& key);
	bool remove(const std::string& key);
};

#endif	/* SERVICEMODEL_H */

