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
	
	virtual bool add(const std::string& key, const TRID registrationId){
		if (_pServiceModel)
			return _pServiceModel->add(key, registrationId);
		return false;
	}
	
	virtual void get(TDailyInjections& _return, const std::string& key){
		if (_pServiceModel)
			_pServiceModel->get(_return, key);
	}
	
	virtual bool remove(const std::string& key){
		if (_pServiceModel)
			return _pServiceModel->remove(key);
		return false;
	}
	
private:
	ServiceModelT* _pServiceModel;
};

#endif	/* SERVERHANDLER_H */

