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
	
	CRDHN::VR::Data::TCID add(const CRDHN::VR::Data::TCentreInfo& centreInfo);
	void get(CRDHN::VR::Data::TCentreInfo& response, const CRDHN::VR::Data::TCID centreId);
	bool update(const CRDHN::VR::Data::TCID centreId, const CRDHN::VR::Data::TCentreInfo& centreInfo);
	bool remove(const CRDHN::VR::Data::TCID centreId);
	bool addEvent(const CRDHN::VR::Data::TCID centreId, const CRDHN::VR::Data::TEID eventId);
	bool removeEvent(const CRDHN::VR::Data::TCID centreId, const CRDHN::VR::Data::TEID eventId);
	bool addVaccine(const CRDHN::VR::Data::TCID centreId, const CRDHN::VR::Data::TVID vaccineId);
	bool removeVaccine(const CRDHN::VR::Data::TCID centreId, const CRDHN::VR::Data::TVID vaccineId);
};

#endif	/* SERVICEMODEL_H */

