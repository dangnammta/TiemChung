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
	
	TRID add(const TRegistrationInfo& registrationInfo);
	bool update(const TRID registrationId, const TRegistrationInfo& registrationInfo);
	void get(TRegistrationInfo& _return, const TRID registrationId);
	bool remove(const TRID registrationId);
	bool setRegistrationCode(const TRID registrationId, const std::string& registrationCode);
	bool updateInjectionStatus(const TRID registrationId, const int32_t injectionStatus);
};

#endif	/* SERVICEMODEL_H */

