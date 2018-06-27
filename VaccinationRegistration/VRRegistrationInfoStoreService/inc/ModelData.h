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
	TRID _registrationId;
	int32_t _registrationType;
	TTIME _registrationTime;
	TEID _eventId;
	std::string _childrenName;
	TTIME _childrenDateOfBirth;
	std::string _childrenCarrierName;
	std::string _childrenCarrierPIN;
	std::string _childrenCarrierPhoneNumber;
	std::string _childrenCarrierEmail;
	std::string _childrenCarrierAddress;
	int32_t _injectionStatus;
	std::string _registrationCode;
	int32_t _injectionOrderNumber;
	std::string _injectionTime;
	TTIME _injectionDate;
	TTIME _injectionRealTime;
	
	ModelData(void) {
		clear();
	}

	bool assignFrom(const TRegistrationInfo& tRegistrationInfo) {
		this->_registrationId = tRegistrationInfo.registrationId;
		this->_registrationType = tRegistrationInfo.registrationType;
		this->_registrationTime = tRegistrationInfo.registrationTime;
		this->_eventId = tRegistrationInfo.eventId;
		this->_childrenName = tRegistrationInfo.childrenName;
		this->_childrenDateOfBirth = tRegistrationInfo.childrenDateOfBirth;
		this->_childrenCarrierName = tRegistrationInfo.childrenCarrierName;
		this->_childrenCarrierPIN = tRegistrationInfo.childrenCarrierPIN;
		this->_childrenCarrierPhoneNumber = tRegistrationInfo.childrenCarrierPhoneNumber;
		this->_childrenCarrierEmail = tRegistrationInfo.childrenCarrierEmail;
		this->_childrenCarrierAddress = tRegistrationInfo.childrenCarrierAddress;
		this->_injectionStatus = tRegistrationInfo.injectionStatus;
		this->_registrationCode = tRegistrationInfo.registrationCode;
		this->_injectionOrderNumber = tRegistrationInfo.injectionOrderNumber;
		this->_injectionTime = tRegistrationInfo.injectionTime;
		this->_injectionDate = tRegistrationInfo.injectionDate;
		this->_injectionRealTime = tRegistrationInfo.injectionRealTime;
		return true;
	}

	void assignTo(TRegistrationInfo& tRegistrationInfo) const {
		tRegistrationInfo.__set_registrationId(this->_registrationId);
		tRegistrationInfo.__set_registrationType(this->_registrationType);
		tRegistrationInfo.__set_registrationTime(this->_registrationTime);
		tRegistrationInfo.__set_eventId(this->_eventId);
		tRegistrationInfo.__set_childrenName(this->_childrenName);
		tRegistrationInfo.__set_childrenDateOfBirth(this->_childrenDateOfBirth);
		tRegistrationInfo.__set_childrenCarrierName(this->_childrenCarrierName);
		tRegistrationInfo.__set_childrenCarrierPIN(this->_childrenCarrierPIN);
		tRegistrationInfo.__set_childrenCarrierPhoneNumber(this->_childrenCarrierPhoneNumber);
		tRegistrationInfo.__set_childrenCarrierEmail(this->_childrenCarrierEmail);
		tRegistrationInfo.__set_childrenCarrierAddress(this->_childrenCarrierAddress);
		tRegistrationInfo.__set_injectionStatus(this->_injectionStatus);
		tRegistrationInfo.__set_registrationCode(this->_registrationCode);
		tRegistrationInfo.__set_injectionOrderNumber(this->_injectionOrderNumber);
		tRegistrationInfo.__set_injectionTime(this->_injectionTime);
		tRegistrationInfo.__set_injectionDate(this->_injectionDate);
		tRegistrationInfo.__set_injectionRealTime(this->_injectionRealTime);
	}
	
	bool clear(void) {
		this->_registrationId = -1;
		this->_registrationType = -1;
		this->_registrationTime = -1;
		this->_eventId = -1;
		this->_childrenName = "";
		this->_childrenDateOfBirth = -1;
		this->_childrenCarrierName = "";
		this->_childrenCarrierPIN = "";
		this->_childrenCarrierPhoneNumber = "";
		this->_childrenCarrierEmail = "";
		this->_childrenCarrierAddress = "";
		this->_injectionStatus = -1;
		this->_registrationCode = "";
		this->_injectionOrderNumber = -1;
		this->_injectionTime = "";
		this->_injectionDate = -1;
		this->_injectionRealTime = -1;
		return true;
	}
	
	bool add(const TRID registrationId, const TRegistrationInfo& tRegistrationInfo) {
		this->_registrationId = registrationId;
		this->_registrationType = tRegistrationInfo.registrationType;
		this->_registrationTime = tRegistrationInfo.registrationTime;
		this->_eventId = tRegistrationInfo.eventId;
		this->_childrenName = tRegistrationInfo.childrenName;
		this->_childrenDateOfBirth = tRegistrationInfo.childrenDateOfBirth;
		this->_childrenCarrierName = tRegistrationInfo.childrenCarrierName;
		this->_childrenCarrierPIN = tRegistrationInfo.childrenCarrierPIN;
		this->_childrenCarrierPhoneNumber = tRegistrationInfo.childrenCarrierPhoneNumber;
		this->_childrenCarrierEmail = tRegistrationInfo.childrenCarrierEmail;
		this->_childrenCarrierAddress = tRegistrationInfo.childrenCarrierAddress;
		this->_injectionStatus = tRegistrationInfo.injectionStatus;
		this->_registrationCode = tRegistrationInfo.registrationCode;
		this->_injectionOrderNumber = tRegistrationInfo.injectionOrderNumber;
		this->_injectionTime = tRegistrationInfo.injectionTime;
		this->_injectionDate = tRegistrationInfo.injectionDate;
		this->_injectionRealTime = tRegistrationInfo.injectionRealTime;
		return true;
	}
	
	bool update(const TRegistrationInfo& tRegistrationInfo) {
		if (this->_registrationId == -1 || this->_registrationId != tRegistrationInfo.registrationId)
			return false;
		this->_registrationType = tRegistrationInfo.registrationType;
		this->_registrationTime = tRegistrationInfo.registrationTime;
		this->_eventId = tRegistrationInfo.eventId;
		this->_childrenName = tRegistrationInfo.childrenName;
		this->_childrenDateOfBirth = tRegistrationInfo.childrenDateOfBirth;
		this->_childrenCarrierName = tRegistrationInfo.childrenCarrierName;
		this->_childrenCarrierPIN = tRegistrationInfo.childrenCarrierPIN;
		this->_childrenCarrierPhoneNumber = tRegistrationInfo.childrenCarrierPhoneNumber;
		this->_childrenCarrierEmail = tRegistrationInfo.childrenCarrierEmail;
		this->_childrenCarrierAddress = tRegistrationInfo.childrenCarrierAddress;
		this->_injectionStatus = tRegistrationInfo.injectionStatus;
		this->_registrationCode = tRegistrationInfo.registrationCode;
		this->_injectionOrderNumber = tRegistrationInfo.injectionOrderNumber;
		this->_injectionTime = tRegistrationInfo.injectionTime;
		this->_injectionDate = tRegistrationInfo.injectionDate;
		this->_injectionRealTime = tRegistrationInfo.injectionRealTime;
		return true;
	}
	
	bool setRegistrationCode(const std::string& registrationCode){
		this->_registrationCode = registrationCode;
		return true;
	}
	
	bool updateInjectionStatus(const int32_t injectionStatus){
		this->_injectionStatus = injectionStatus;
		if (injectionStatus == TIS_VACCINATED)
			this->_injectionRealTime = Poco::Timestamp().epochTime();
		else 
			this->_injectionRealTime = -1;
		return true;
	}
};

#endif	/* MODELDATA_H */

