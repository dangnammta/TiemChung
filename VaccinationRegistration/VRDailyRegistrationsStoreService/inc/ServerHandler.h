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
	
	virtual int32_t add(const std::string& key, const std::string& PIN, const TRID registrationId){
		if (_pServiceModel)
			return _pServiceModel->add(key, PIN, registrationId);
		return -1;
	}
	
	virtual void get(TDailyRegistrations& _return, const std::string& key){
		if (_pServiceModel)
			_pServiceModel->get(_return, key);
	}
	
	virtual int32_t getRegistrationId(const std::string& key, const std::string& PIN){
		if (_pServiceModel)
			return _pServiceModel->getRegistrationId(key, PIN);
		return -1;
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

