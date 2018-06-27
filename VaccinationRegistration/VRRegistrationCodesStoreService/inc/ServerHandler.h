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

template < class ServiceModelT >
class ServerHandler : public ThriftIfaceT {
public:
	ServerHandler(ServiceModelT* pServiceModel) : _pServiceModel(pServiceModel) {
	}

	virtual ~ServerHandler(void) {
	}
	
	virtual bool add(const CRDHN::VR::Data::TEID eventId, const std::string& registrationCode, const CRDHN::VR::Data::TRID registrationId){
		if (_pServiceModel){
			return _pServiceModel->add(eventId, registrationCode, registrationId);
		}
		return false;
	}
	virtual bool remove(const CRDHN::VR::Data::TEID eventId, const std::string& registrationCode, const CRDHN::VR::Data::TRID registrationId){
		if (_pServiceModel){
			return _pServiceModel->remove(eventId, registrationCode, registrationId);
		}
		return false;
	}
	virtual CRDHN::VR::Data::TRID getRegistrationId(const CRDHN::VR::Data::TEID eventId, const std::string& registrationCode){
		if (_pServiceModel){
			return _pServiceModel->getRegistrationId(eventId, registrationCode);
		}
		return -1;
	}
	virtual void getRegistrationCodes(CRDHN::VR::Data::TRegistrationCodes& _return, const CRDHN::VR::Data::TEID eventId){
		if (_pServiceModel){
			_pServiceModel->getRegistrationCodes(_return, eventId);
		}
	}
	virtual bool removeRegistrationCodes(const CRDHN::VR::Data::TEID eventId){
		if (_pServiceModel){
			return _pServiceModel->removeRegistrationCodes(eventId);
		}
		return false;
	}
private:
	ServiceModelT* _pServiceModel;
};

#endif	/* SERVERHANDLER_H */

