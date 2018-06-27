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
	TCID _centreId;
	std::string _name;
	std::string _address;
	std::string _phoneNumber;
	std::vector<TEID> _eventIds;
	std::vector<TVID> _vaccineIds;
	
	ModelData(void) {
		clear();
	}

	bool assignFrom(const TCentreInfo& tCentreInfo) {
		this->_centreId = tCentreInfo.centreId;
		this->_name = tCentreInfo.name;
		this->_address = tCentreInfo.address;
		this->_phoneNumber = tCentreInfo.phoneNumber;
		this->_eventIds = tCentreInfo.eventIds;
		this->_vaccineIds = tCentreInfo.vaccineIds;
		return true;
	}

	void assignTo(TCentreInfo& tCentreInfo) const {
		tCentreInfo.__set_centreId(this->_centreId);
		tCentreInfo.__set_name(this->_name);
		tCentreInfo.__set_address(this->_address);
		tCentreInfo.__set_phoneNumber(this->_phoneNumber);
		tCentreInfo.__set_eventIds(this->_eventIds);
		tCentreInfo.__set_vaccineIds(this->_vaccineIds);
	}

	bool clear(void) {
		this->_centreId = -1;
		this->_name = "";
		this->_address = "";
		this->_phoneNumber = "";
		this->_eventIds.clear();
		this->_vaccineIds.clear();
		return true;
	}
	
	bool add(const TCID centreId, const TCentreInfo& tCentreInfo) {
		this->_centreId = centreId;
		this->_name = tCentreInfo.name;
		this->_address = tCentreInfo.address;
		this->_phoneNumber = tCentreInfo.phoneNumber;
		this->_eventIds.clear();
		this->_vaccineIds.clear();
		return true;
	}
	
	bool addEvent(const TEID eventId){
		std::vector<TEID>::iterator pos = std::lower_bound(_eventIds.begin(), _eventIds.end(), eventId);
		if ((pos == _eventIds.end()) || (*pos != eventId)) {
			_eventIds.insert(pos, eventId);
			return true;
		}
		return false;
	}
	
	bool removeEvent(const TEID eventId){
		std::vector<TEID>::iterator pos = std::lower_bound(_eventIds.begin(), _eventIds.end(), eventId);
		if ((pos != _eventIds.end()) && (*pos == eventId)) {
			_eventIds.erase(pos);
			return true;
		}
		return false;
	}
	
	bool addVaccine(const TVID vaccineId){
		std::vector<TVID>::iterator pos = std::lower_bound(_vaccineIds.begin(), _vaccineIds.end(), vaccineId);
		if ((pos == _vaccineIds.end()) || (*pos != vaccineId)) {
			_vaccineIds.insert(pos, vaccineId);
			return true;
		}
		return false;
	}
	
	bool removeVaccine(const TVID vaccineId){
		std::vector<TVID>::iterator pos = std::lower_bound(_vaccineIds.begin(), _vaccineIds.end(), vaccineId);
		if ((pos != _vaccineIds.end()) && (*pos == vaccineId)) {
			_vaccineIds.erase(pos);
			return true;
		}
		return false;
	}
};

#endif	/* MODELDATA_H */

