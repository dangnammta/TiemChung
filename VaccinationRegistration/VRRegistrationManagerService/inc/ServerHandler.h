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
	
	virtual void getEventStatus(CRDHN::VR::Response::TError& _return, const TEID eventId){
		if (_pServiceModel){
			_pServiceModel->getEventStatus(_return, eventId);
			return;
		}
		_return.__set_errorCode(CRDHN::VR::Response::EC_SYSTEM);
	}
	
	virtual void addRegistration(CRDHN::VR::Response::TRMAddRegistrationResponse& _return, const TRegistrationInfo& registrationInfo){
		if (_pServiceModel){
			_pServiceModel->addRegistration(_return, registrationInfo);
			return;
		}
		_return.error.__set_errorCode(CRDHN::VR::Response::EC_SYSTEM);
	}
	
	virtual void addExtraRegistration(CRDHN::VR::Response::TRMAddExtraRegistrationResponse& _return, const TRegistrationInfo& registrationInfo){
		if (_pServiceModel){
			_pServiceModel->addExtraRegistration(_return, registrationInfo);
			return;
		}
		_return.error.__set_errorCode(CRDHN::VR::Response::EC_SYSTEM);
	}
	
	virtual void addExtendedRegistration(CRDHN::VR::Response::TRMAddRegistrationResponse& _return, const TRegistrationInfo& registrationInfo, const int32_t desiredOrderNumber){
		if (_pServiceModel){
			_pServiceModel->addExtendedRegistration(_return, registrationInfo, desiredOrderNumber);
			return;
		}
		_return.error.__set_errorCode(CRDHN::VR::Response::EC_SYSTEM);
	}
	
private:
	ServiceModelT* _pServiceModel;
};

#endif	/* SERVERHANDLER_H */

