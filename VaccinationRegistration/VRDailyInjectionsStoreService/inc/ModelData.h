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
	std::vector<TRID> _registrations;
	
	ModelData(void) {
		clear();
	}

	bool assignFrom(const TDailyInjections& tDailyInjections) {
		this->_registrations = tDailyInjections.registrations;
		return true;
	}

	void assignTo(TDailyInjections& tDailyInjections) const {
		tDailyInjections.__set_registrations(this->_registrations);
	}
	
	bool clear(void) {
		this->_registrations.clear();
		return true;
	}
	
	bool add(const TRID registrationId) {
		std::vector<TRID>::iterator pos = std::lower_bound(_registrations.begin(), _registrations.end(), registrationId);
		if ((pos == _registrations.end()) || (*pos != registrationId)) {
			_registrations.insert(pos, registrationId);
			return true;
		}
		return false;
	}
};

#endif	/* MODELDATA_H */

