/* 
 * File:   ModelData.h
 * Author: longmd
 *
 * Created on December 9, 2014, 3:58 PM
 */

#ifndef MODELDATA_H
#define	MODELDATA_H

#include "vr_shared_types.h"
#include "Types.h"
#include "iostream"
#include "Poco/Timestamp.h"

using namespace std;
using namespace CRDHN::VR::Data;

class ModelData {
public:
	std::map<std::string, TRID> _registrations;
	
	ModelData(void) {
		clear();
	}

	bool assignFrom(const TDailyRegistrations& tDailyRegistrations) {
		this->_registrations = tDailyRegistrations.registrations;
		return true;
	}

	void assignTo(TDailyRegistrations& tDailyRegistrations) const {
		tDailyRegistrations.__set_registrations(this->_registrations);
	}
	
	bool clear(void) {
		this->_registrations.clear();
		return true;
	}
	
	int32_t add(const std::string& PIN, const TRID registrationId) {
		if (this->_registrations.find(PIN)!=this->_registrations.end())
			return -100;
		this->_registrations.insert(std::make_pair(PIN, registrationId));
		return 0;
	}
	
	int32_t getRegistrationId(const std::string& PIN){
		std::map<std::string, TRID>::const_iterator iter;
		iter = this->_registrations.find(PIN);
		if (iter!=this->_registrations.end())
			return iter->second;
		else 
			return -1;
	}
};

#endif	/* MODELDATA_H */