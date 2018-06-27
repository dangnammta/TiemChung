/* 
 * File:   ServerHandler.h
 * Author: longmd
 *
 * Created on November 20, 2014, 4:26 PM
 */

#ifndef SERVERHANDLER_H
#define	SERVERHANDLER_H

#include "Types.h"
#include "ServiceModel.h"

using namespace CRDHN::VR::Data;

template < class ServiceModelT >
class ServerHandler : public ThriftIfaceT {
public:
	ServerHandler(ServiceModelT* pServiceModel) : _pServiceModel(pServiceModel) {
	}

	virtual ~ServerHandler(void) {
	}
	
	virtual TRID add(const TRegistrationInfo& registrationInfo){
		if (_pServiceModel)
			return _pServiceModel->add(registrationInfo);
		return -1;
	}
	
	virtual bool update(const TRID registrationId, const TRegistrationInfo& registrationInfo){
		if (_pServiceModel)
			return _pServiceModel->update(registrationId, registrationInfo);
		return false;
	}
	
	virtual void get(TRegistrationInfo& _return, const TRID registrationId){
		if (_pServiceModel)
			_pServiceModel->get(_return, registrationId);
	}
	
	virtual bool remove(const TRID registrationId){
		if (_pServiceModel)
			return _pServiceModel->remove(registrationId);
		return false;
	}
	
	virtual bool setRegistrationCode(const TRID registrationId, const std::string& registrationCode){
		if (_pServiceModel)
			return _pServiceModel->setRegistrationCode(registrationId, registrationCode);
		return false;
	}
	
	virtual bool updateInjectionStatus(const TRID registrationId, const int32_t injectionStatus){
		if (_pServiceModel)
			return _pServiceModel->updateInjectionStatus(registrationId, injectionStatus);
		return false;
	}
	
private:
	ServiceModelT* _pServiceModel;
};

#endif	/* SERVERHANDLER_H */

