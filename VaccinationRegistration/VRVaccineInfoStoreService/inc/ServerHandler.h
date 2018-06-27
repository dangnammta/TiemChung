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
	
	virtual TVID add(const TVaccineInfo& vaccineInfo){
		if (_pServiceModel)
			return _pServiceModel->add(vaccineInfo);
		return -1;
	}
	virtual void get(TVaccineInfo& _return, const TVID vaccineId){
		if (_pServiceModel)
			_pServiceModel->get(_return, vaccineId);
	}
	
	virtual bool update(const CRDHN::VR::Data::TVID vaccineId, const CRDHN::VR::Data::TVaccineInfo& vaccineInfo){
		if (_pServiceModel)
			return _pServiceModel->update(vaccineId, vaccineInfo);
		return false;
	}
	
	virtual bool remove(const CRDHN::VR::Data::TVID vaccineId){
		if (_pServiceModel)
			return _pServiceModel->remove(vaccineId);
		return false;
	}

private:
	ServiceModelT* _pServiceModel;
};

#endif	/* SERVERHANDLER_H */

