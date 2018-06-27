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
	
	TVID add(const TVaccineInfo& vaccineInfo);
	void get(TVaccineInfo& _return, const TVID vaccineId);
	bool update(const CRDHN::VR::Data::TVID vaccineId, const CRDHN::VR::Data::TVaccineInfo& vaccineInfo);
	bool remove(const CRDHN::VR::Data::TVID vaccineId);
};

#endif	/* SERVICEMODEL_H */

