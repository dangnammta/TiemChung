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
	
	virtual TEID add(const TEventInfo& eventInfo){
		if (_pServiceModel)
			return _pServiceModel->add(eventInfo);
		return -1;
	}
	
	virtual bool update(const TEID eventId, const TEventInfo& eventInfo){
		if (_pServiceModel)
			return _pServiceModel->update(eventId, eventInfo);
		return false;
	}
	
	virtual void get(TEventInfo& _return, const TEID eventId){
		if (_pServiceModel)
			_pServiceModel->get(_return, eventId);
	}
	
	virtual void getBasicInfo(TEventBasicInfo& _return, const TEID eventId){
		if (_pServiceModel)
			_pServiceModel->getBasicInfo(_return, eventId);
	}
	
	virtual bool remove(const TEID eventId){
		if (_pServiceModel)
			return _pServiceModel->remove(eventId);
		return false;
	}
	
	virtual int32_t getEventStatus(const TEID eventId){
		if (_pServiceModel)
			return _pServiceModel->getEventStatus(eventId);
		return -1;
	}
	
	virtual void getInjectionOrder(TInjectionOrderInfo& _return, const TEID eventId, const TTIME childrenDateOfBirth){
		if (_pServiceModel)
			return _pServiceModel->getInjectionOrder(_return, eventId, childrenDateOfBirth);
	}
	
	virtual void getExtendedInjectionOrder(TInjectionOrderInfo& _return, const TEID eventId, const TTIME childrenDateOfBirth, const int32_t desiredOrderNumber){
		if (_pServiceModel)
			return _pServiceModel->getExtendedInjectionOrder(_return, eventId, childrenDateOfBirth, desiredOrderNumber);
	}
	
	virtual void getNewRegistrationCode(std::string& _return, const CRDHN::VR::Data::TEID eventId){
		if (_pServiceModel)
			return _pServiceModel->getNewRegistrationCode(_return, eventId);
	}
	
	virtual bool addRegistration(const TEID eventId, const TRID registrationId){
		if (_pServiceModel)
			return _pServiceModel->addRegistration(eventId, registrationId);
		return false;
	}
	
	virtual bool addExtendedRegistration(const TEID eventId, const TRID registrationId){
		if (_pServiceModel)
			return _pServiceModel->addExtendedRegistration(eventId, registrationId);
		return false;
	}
	
	virtual bool removeRegistration(const TEID eventId, const TRID registrationId){
		if (_pServiceModel)
			return _pServiceModel->removeRegistration(eventId, registrationId);
		return false;
	}
	
	virtual void getRegistrations(std::vector<TRID> & _return, const TEID eventId){
		if (_pServiceModel)
			_pServiceModel->getRegistrations(_return, eventId);
	}
	
	virtual bool addVaccinatedChildren(const TEID eventId){
		if (_pServiceModel)
			return _pServiceModel->addVaccinatedChildren(eventId);
		return false;
	}
	
	virtual bool removeVaccinatedChildren(const TEID eventId){
		if (_pServiceModel)
			return _pServiceModel->removeVaccinatedChildren(eventId);
		return false;
	}
	
private:
	ServiceModelT* _pServiceModel;
};

#endif	/* SERVERHANDLER_H */

