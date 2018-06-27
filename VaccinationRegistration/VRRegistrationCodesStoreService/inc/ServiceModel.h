/* 
 * File:   ServiceModel.h
 * Author: longmd
 *
 * Created on December 25, 2014, 9:28 AM
 */

#ifndef SERVICEMODEL_H
#define	SERVICEMODEL_H

#include "Types.h"

class ServiceModel {
public:
	ServiceModel(void);
	virtual ~ServiceModel(void);
	
	bool add(const CRDHN::VR::Data::TEID eventId, const std::string& registrationCode, const CRDHN::VR::Data::TRID registrationId);
	bool remove(const CRDHN::VR::Data::TEID eventId, const std::string& registrationCode, const CRDHN::VR::Data::TRID registrationId);
	CRDHN::VR::Data::TRID getRegistrationId(const CRDHN::VR::Data::TEID eventId, const std::string& registrationCode);
	void getRegistrationCodes(CRDHN::VR::Data::TRegistrationCodes& response, const CRDHN::VR::Data::TEID eventId);
	bool removeRegistrationCodes(const CRDHN::VR::Data::TEID eventId);
};

#endif	/* SERVICEMODEL_H */

