/* 
 * File:   ServiceModel.h
 * Author: longmd
 *
 * Created on December 30, 2014, 11:28 AM
 */

#ifndef SERVICEMODEL_H
#define	SERVICEMODEL_H

#include <vector>
#include "Types.h"

using namespace CRDHN::VR::Data;

class ServiceModel {
public:
	ServiceModel(void);
	virtual ~ServiceModel(void);
	
	void getEventStatus(CRDHN::VR::Response::TError& response, const TEID eventId);
	void addRegistration(CRDHN::VR::Response::TRMAddRegistrationResponse& response, const TRegistrationInfo& registrationInfo);
	void addExtraRegistration(CRDHN::VR::Response::TRMAddExtraRegistrationResponse& response, const TRegistrationInfo& registrationInfo);
	void addExtendedRegistration(CRDHN::VR::Response::TRMAddRegistrationResponse& response, const TRegistrationInfo& registrationInfo, const int32_t desiredOrderNumber);
};

#endif	/* SERVICEMODEL_H */

