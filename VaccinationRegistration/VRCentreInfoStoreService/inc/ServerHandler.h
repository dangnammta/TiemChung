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
	
	virtual TCID add(const TCentreInfo& centreInfo){
		if (_pServiceModel){
			return _pServiceModel->add(centreInfo);
		}
		return -1;
	}
	
	virtual void get(TCentreInfo& _return, const TCID centreId){
		if (_pServiceModel){
			_pServiceModel->get(_return, centreId);
		}
	}
	
	virtual bool update(const TCID centreId, const TCentreInfo& centreInfo){
		if (_pServiceModel)
			return _pServiceModel->update(centreId, centreInfo);
		return false;
	}
	
	virtual bool remove(const TCID centreId){
		if (_pServiceModel)
			return _pServiceModel->remove(centreId);
		return false;
	}
	
	virtual bool addEvent(const TCID centreId, const TEID eventId){
		if (_pServiceModel)
			return _pServiceModel->addEvent(centreId, eventId);
		return false;
	}
	
	virtual bool removeEvent(const TCID centreId, const TEID eventId){
		if (_pServiceModel)
			return _pServiceModel->removeEvent(centreId, eventId);
		return false;
	}
	
	virtual bool addVaccine(const CRDHN::VR::Data::TCID centreId, const CRDHN::VR::Data::TVID vaccineId){
		if (_pServiceModel)
			return _pServiceModel->addVaccine(centreId, vaccineId);
		return false;
	}
	
	virtual bool removeVaccine(const CRDHN::VR::Data::TCID centreId, const CRDHN::VR::Data::TVID vaccineId){
		if (_pServiceModel)
			return _pServiceModel->removeVaccine(centreId, vaccineId);
		return false;
	}

private:
	ServiceModelT* _pServiceModel;
};

#endif	/* SERVERHANDLER_H */

