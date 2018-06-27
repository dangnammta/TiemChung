/* 
 * File:   ServerHandler.h
 * Author: longmd
 *
 * Created on December 30, 2014, 11:28 AM
 */

#ifndef SERVERHANDLER_H
#define	SERVERHANDLER_H

#include "Types.h"
#include "ServiceModel.h"
#include "Configuration.h"

using namespace CRDHN::VR::Data;

template < class ServiceModelT >
class ServerHandler : public ThriftIfaceT {
public:

	ServerHandler(ServiceModelT* pServiceModel) : _pServiceModel(pServiceModel){
	}

	virtual ~ServerHandler(void){
	}
	
	virtual void createCentre(CRDHN::VR::Response::TCMCreateCentreResponse& _return, const TCentreInfo& centreInfo){
		if (_pServiceModel){
			_pServiceModel->createCentre(_return, centreInfo);
			return;
		}
		_return.error.__set_errorCode(CRDHN::VR::Response::EC_SYSTEM);
	}
	
	virtual void getCentreInfo(CRDHN::VR::Response::TCMGetCentreInfoResponse& _return, const TCID centreId){
		if (_pServiceModel){
			_pServiceModel->getCentreInfo(_return, centreId);
			return;
		}
		_return.error.__set_errorCode(CRDHN::VR::Response::EC_SYSTEM);
	}
	
	virtual void updateCentreInfo(CRDHN::VR::Response::TError& _return, const TCID centreId, const TCentreInfo& centreInfo){
		if (_pServiceModel){
			_pServiceModel->updateCentreInfo(_return, centreId, centreInfo);
			return;
		}
		_return.__set_errorCode(CRDHN::VR::Response::EC_SYSTEM);
	}
	
	virtual void removeCentre(CRDHN::VR::Response::TError& _return, const TCID centreId){
		if (_pServiceModel){
			_pServiceModel->removeCentre(_return, centreId);
			return;
		}
		_return.__set_errorCode(CRDHN::VR::Response::EC_SYSTEM);
		
	}
	
	virtual void getCentres(CRDHN::VR::Response::TCMGetCentresResponse& _return){
		if (_pServiceModel){
			_pServiceModel->getCentres(_return);
			return;
		}
		_return.error.__set_errorCode(CRDHN::VR::Response::EC_SYSTEM);
	}
	
	virtual void addVaccine(CRDHN::VR::Response::TCMAddVaccineResponse& _return, const CRDHN::VR::Data::TCID centreId, const CRDHN::VR::Data::TVaccineInfo& vaccineInfo){
		if (_pServiceModel){
			_pServiceModel->addVaccine(_return, centreId, vaccineInfo);
			return;
		}
		_return.error.__set_errorCode(CRDHN::VR::Response::EC_SYSTEM);
	}
	
	virtual void getVaccineInfo(CRDHN::VR::Response::TCMGetVaccineInfoResponse& _return, const CRDHN::VR::Data::TVID vaccineId){
		if (_pServiceModel){
			_pServiceModel->getVaccineInfo(_return, vaccineId);
			return;
		}
		_return.error.__set_errorCode(CRDHN::VR::Response::EC_SYSTEM);
	}
	
	virtual void updateVaccineInfo(CRDHN::VR::Response::TError& _return, const CRDHN::VR::Data::TVID vaccineId, const CRDHN::VR::Data::TVaccineInfo& vaccineInfo){
		if (_pServiceModel){
			_pServiceModel->updateVaccineInfo(_return, vaccineId, vaccineInfo);
			return;
		}
		_return.__set_errorCode(CRDHN::VR::Response::EC_SYSTEM);
	}
	
	virtual void removeVaccine(CRDHN::VR::Response::TError& _return, const CRDHN::VR::Data::TCID centreId, const CRDHN::VR::Data::TVID vaccineId){
		if (_pServiceModel){
			_pServiceModel->removeVaccine(_return, centreId, vaccineId);
			return;
		}
		_return.__set_errorCode(CRDHN::VR::Response::EC_SYSTEM);
	}
	
	virtual void getVaccines(CRDHN::VR::Response::TCMGetVaccinesResponse& _return, const CRDHN::VR::Data::TCID centreId){
		if (_pServiceModel){
			_pServiceModel->getVaccines(_return, centreId);
			return;
		}
		_return.error.__set_errorCode(CRDHN::VR::Response::EC_SYSTEM);
	}
	
private:
	ServiceModelT* _pServiceModel;
};

#endif	/* SERVERHANDLER_H */

