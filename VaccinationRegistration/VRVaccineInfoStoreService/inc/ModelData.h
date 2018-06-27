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
	TVID _vaccineId;
	std::string _name;
	std::string _description;
	
	ModelData(void) {
		clear();
	}

	bool assignFrom(const TVaccineInfo& tVaccineInfo) {
		this->_vaccineId = tVaccineInfo.vaccineId;
		this->_name = tVaccineInfo.name;
		this->_description = tVaccineInfo.description;
		return true;
	}

	void assignTo(TVaccineInfo& tVaccineInfo) const {
		tVaccineInfo.__set_vaccineId(this->_vaccineId);
		tVaccineInfo.__set_name(this->_name);
		tVaccineInfo.__set_description(this->_description);
	}

	bool clear(void) {
		this->_vaccineId = -1;
		this->_name = "";
		this->_description = "";
		return true;
	}
	
	bool add(const TVID id, const TVaccineInfo& tVaccineInfo) {
		this->_vaccineId = id;
		this->_name = tVaccineInfo.name;
		this->_description = tVaccineInfo.description;
		return true;
	}
};

#endif	/* MODELDATA_H */

