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
	
	void createCentre(CRDHN::VR::Response::TCMCreateCentreResponse& response, const TCentreInfo& centreInfo);
	void getCentreInfo(CRDHN::VR::Response::TCMGetCentreInfoResponse& response, const TCID centreId);
	void updateCentreInfo(CRDHN::VR::Response::TError& response, const TCID centreId, const TCentreInfo& centreInfo);
	void removeCentre(CRDHN::VR::Response::TError& response, const TCID centreId);
	void getCentres(CRDHN::VR::Response::TCMGetCentresResponse& response);
	
	void addVaccine(CRDHN::VR::Response::TCMAddVaccineResponse& response, const CRDHN::VR::Data::TCID centreId, const CRDHN::VR::Data::TVaccineInfo& vaccineInfo);
	void getVaccineInfo(CRDHN::VR::Response::TCMGetVaccineInfoResponse& response, const CRDHN::VR::Data::TVID vaccineId);
	void updateVaccineInfo(CRDHN::VR::Response::TError& response, const CRDHN::VR::Data::TVID vaccineId, const CRDHN::VR::Data::TVaccineInfo& vaccineInfo);
	void removeVaccine(CRDHN::VR::Response::TError& response, const CRDHN::VR::Data::TCID centreId, const CRDHN::VR::Data::TVID vaccineId);
	void getVaccines(CRDHN::VR::Response::TCMGetVaccinesResponse& response, const CRDHN::VR::Data::TCID centreId);
};

#endif	/* SERVICEMODEL_H */

