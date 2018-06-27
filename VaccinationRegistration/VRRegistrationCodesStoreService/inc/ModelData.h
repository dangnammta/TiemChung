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

class ModelData {
public:
	CRDHN::VR::Data::TEID _eventId;
	std::map<std::string, CRDHN::VR::Data::TRID>  _mapRCode2RId;
	
	ModelData(void) {
		clear();
	}

	bool assignFrom(const CRDHN::VR::Data::TRegistrationCodes& tRegistrationCodes) {
		this->_eventId = tRegistrationCodes.eventId;
		this->_mapRCode2RId = tRegistrationCodes.mapRCode2RId;
		return true;
	}

	void assignTo(CRDHN::VR::Data::TRegistrationCodes& tRegistrationCodes) const {
		tRegistrationCodes.__set_eventId(this->_eventId);
		tRegistrationCodes.__set_mapRCode2RId(this->_mapRCode2RId);
	}

	bool clear(void) {
		this->_eventId = -1;
		this->_mapRCode2RId.clear();
		return true;
	}
	
	bool add(const std::string& registrationCode, const CRDHN::VR::Data::TRID registrationId) {
		std::map<std::string, CRDHN::VR::Data::TRID>::iterator pos;
		pos = this->_mapRCode2RId.find(registrationCode);
		if (pos==this->_mapRCode2RId.end()){
			this->_mapRCode2RId.insert(std::make_pair(registrationCode, registrationId));
		}
		else
			pos->second = registrationId;
		return true;
	}
	
	bool remove(const std::string& registrationCode, const CRDHN::VR::Data::TRID registrationId) {
		std::map<std::string, CRDHN::VR::Data::TRID>::iterator pos;
		pos = this->_mapRCode2RId.find(registrationCode);
		if (pos!=this->_mapRCode2RId.end() && (pos->second == registrationId)){
			this->_mapRCode2RId.erase(pos);
			return true;
		}
		return false;
	}
	
	CRDHN::VR::Data::TRID getRegistrationId(const std::string& registrationCode){
		std::map<std::string, CRDHN::VR::Data::TRID>::iterator pos;
		pos = this->_mapRCode2RId.find(registrationCode);
		if (pos!=this->_mapRCode2RId.end()){
			return pos->second;
		}
		return -1;
	}
};

#endif	/* MODELDATA_H */

