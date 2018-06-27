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
	
	virtual void createEvent(CRDHN::VR::Response::TVMCreateEventResponse& _return, const TEventInfo& eventInfo){
		if (_pServiceModel){
			_pServiceModel->createEvent(_return, eventInfo);
			return;
		}
		_return.error.__set_errorCode(CRDHN::VR::Response::EC_SYSTEM);
	}
	
	virtual void updateEventInfo(CRDHN::VR::Response::TError& _return, const TEID eventId, const TEventInfo& eventInfo){
		if (_pServiceModel){
			_pServiceModel->updateEventInfo(_return, eventId, eventInfo);
			return;
		}
		_return.__set_errorCode(CRDHN::VR::Response::EC_SYSTEM);
	}
	
	virtual void getEventInfo(CRDHN::VR::Response::TVMGetEventInfoResponse& _return, const TEID eventId){
		if (_pServiceModel){
			_pServiceModel->getEventInfo(_return, eventId);
			return;
		}
		_return.error.__set_errorCode(CRDHN::VR::Response::EC_SYSTEM);
	}
	
	virtual void removeEvent(CRDHN::VR::Response::TError& _return, const TEID eventId){
		if (_pServiceModel){
			_pServiceModel->removeEvent(_return, eventId);
			return;
		}
		_return.__set_errorCode(CRDHN::VR::Response::EC_SYSTEM);
	}
	
	virtual void getEvents(CRDHN::VR::Response::TVMGetEventsResponse& _return, const TCID centreId){
		if (_pServiceModel){
			_pServiceModel->getEvents(_return, centreId);
			return;
		}
		_return.error.__set_errorCode(CRDHN::VR::Response::EC_SYSTEM);
	}
	
	virtual void updateRegistration(CRDHN::VR::Response::TError& _return, const TRID registrationId, const TRegistrationInfo& registrationInfo){
		if (_pServiceModel){
			_pServiceModel->updateRegistration(_return, registrationId, registrationInfo);
			return;
		}
		_return.__set_errorCode(CRDHN::VR::Response::EC_SYSTEM);
	}
	
	virtual void removeRegistration(CRDHN::VR::Response::TError& _return, const TRID registrationId){
		if (_pServiceModel){
			_pServiceModel->removeRegistration(_return, registrationId);
			return;
		}
		_return.__set_errorCode(CRDHN::VR::Response::EC_SYSTEM);
	}
	
	virtual void getRegistration(CRDHN::VR::Response::TVMGetRegistrationResponse& _return, const TRID registrationId){
		if (_pServiceModel){
			_pServiceModel->getRegistration(_return, registrationId);
			return;
		}
		_return.error.__set_errorCode(CRDHN::VR::Response::EC_SYSTEM);
	}
	
	virtual void getRegistrations(CRDHN::VR::Response::TVMGetRegistrationsResponse& _return, const TEID eventId){
		if (_pServiceModel){
			_pServiceModel->getRegistrations(_return, eventId);
			return;
		}
		_return.error.__set_errorCode(CRDHN::VR::Response::EC_SYSTEM);
	}
	
	virtual void updateInjectionStatus(CRDHN::VR::Response::TError& _return, const TRID registrationId, const int32_t injectionStatus){
		if (_pServiceModel){
			_pServiceModel->updateInjectionStatus(_return, registrationId, injectionStatus);
			return;
		}
		_return.__set_errorCode(CRDHN::VR::Response::EC_SYSTEM);
	}
	
private:
	ServiceModelT* _pServiceModel;
};

#endif	/* SERVERHANDLER_H */

