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
#include "sstream"
#include "Poco/Timestamp.h"
#include <Poco/LocalDateTime.h>
#include <Poco/DateTimeFormat.h>
#include <Poco/DateTimeFormatter.h>

using namespace std;
using namespace CRDHN::VR::Data;

class ModelData {

public:
	TEID _eventId;
	std::string _eventName;
	int32_t _eventStatus;
	TTIME _startTimeRegistration;
	TTIME _startTimeInjection;
	int32_t _totalOfDoses;
	int32_t _numberOfInjectionsPerDay;
	int32_t _numberOfRegistrations;
	int32_t _numberOfVaccinatedChildren;
	TVID _vaccineId;
	TCID _centreId;
	std::vector<TRID> _registrations;
	std::vector<std::string> _workingShiftNames;
	int32_t _numberOfWorkingShifts;
	int32_t _numberOfInjectionsPerShift;
	TTIME _endTimeRegistration;
	std::vector<TRID> _extendedRegistrations;
	bool _isWorkingOnSaturday;
	bool _isWorkingOnSunday;
	std::set<TTIME> _vacationDays;
	std::map<int32_t, TInjectionOrderInfo> _injectionOrderInfos;
	std::map<TTIME, std::set<int32_t> > _mapDate2InjectionOrderNumbers;
	std::set<std::string> _registrationCodes;
	
public:
	ModelData(void) {
		clear();
	}

	bool assignFrom(const TEventInfo& tEventInfo) {
		_eventId = tEventInfo.eventId;
		_eventName = tEventInfo.eventName;
		_eventStatus = tEventInfo.eventStatus;
		_startTimeRegistration = tEventInfo.startTimeRegistration;
		_startTimeInjection = tEventInfo.startTimeInjection;
		_totalOfDoses = tEventInfo.totalOfDoses;
		_numberOfInjectionsPerDay = tEventInfo.numberOfInjectionsPerDay;
		_numberOfRegistrations = tEventInfo.numberOfRegistrations;
		_numberOfVaccinatedChildren = tEventInfo.numberOfVaccinatedChildren;
		_vaccineId = tEventInfo.vaccineId;
		_centreId = tEventInfo.centreId;
		_registrations = tEventInfo.registrations;
		_workingShiftNames = tEventInfo.workingShiftNames;
		_numberOfWorkingShifts = _workingShiftNames.size();
		_numberOfInjectionsPerShift = _numberOfInjectionsPerDay/_numberOfWorkingShifts;
		_endTimeRegistration = tEventInfo.endTimeRegistration;
		_extendedRegistrations = tEventInfo.extendedRegistrations;
		_isWorkingOnSaturday = tEventInfo.isWorkingOnSaturday;
		_isWorkingOnSunday = tEventInfo.isWorkingOnSunday;
		_vacationDays = tEventInfo.vacationDays;
		_injectionOrderInfos = tEventInfo.injectionOrderInfos;
		_mapDate2InjectionOrderNumbers = tEventInfo.mapDate2InjectionOrderNumbers;
		_registrationCodes = tEventInfo.registrationCodes;
		return true;
	}

	void assignTo(TEventInfo& tEventInfo) const {
		tEventInfo.__set_eventId(_eventId);
		tEventInfo.__set_eventName(_eventName);
		tEventInfo.__set_eventStatus(_eventStatus);
		tEventInfo.__set_startTimeRegistration(_startTimeRegistration);
		tEventInfo.__set_startTimeInjection(_startTimeInjection);
		tEventInfo.__set_totalOfDoses(_totalOfDoses);
		tEventInfo.__set_numberOfInjectionsPerDay(_numberOfInjectionsPerDay);
		tEventInfo.__set_numberOfRegistrations(_numberOfRegistrations);
		tEventInfo.__set_numberOfVaccinatedChildren(_numberOfVaccinatedChildren);
		tEventInfo.__set_vaccineId(_vaccineId);
		tEventInfo.__set_centreId(_centreId);
		tEventInfo.__set_registrations(_registrations);
		tEventInfo.__set_workingShiftNames(_workingShiftNames);
		tEventInfo.__set_endTimeRegistration(_endTimeRegistration);
		tEventInfo.__set_extendedRegistrations(_extendedRegistrations);
		tEventInfo.__set_isWorkingOnSaturday(_isWorkingOnSaturday);
		tEventInfo.__set_isWorkingOnSunday(_isWorkingOnSunday);
		tEventInfo.__set_vacationDays(_vacationDays);
		tEventInfo.__set_injectionOrderInfos(_injectionOrderInfos);
		tEventInfo.__set_mapDate2InjectionOrderNumbers(_mapDate2InjectionOrderNumbers);
		tEventInfo.__set_registrationCodes(_registrationCodes);
	}

	bool clear(void) {
		_eventId = -1;
		_eventName = "";
		_eventStatus = TES_DISABLE;
		_startTimeRegistration = -1;
		_startTimeInjection = -1;
		_totalOfDoses = 0;
		_numberOfInjectionsPerDay = 0;
		_numberOfRegistrations = 0;
		_numberOfVaccinatedChildren = 0;
		_vaccineId = -1;
		_centreId = -1;
		_registrations.clear();
		_workingShiftNames.clear();
		_numberOfWorkingShifts = 0;
		_numberOfInjectionsPerShift = 0;
		_endTimeRegistration = -1;
		_extendedRegistrations.clear();
		_isWorkingOnSaturday = false;
		_isWorkingOnSunday = false;
		_vacationDays.clear();
		_injectionOrderInfos.clear();
		_mapDate2InjectionOrderNumbers.clear();
		_registrationCodes.clear();
		return true;
	}
	
	std::string genRandomStr(int length) {
		static string charset = "ABCDEFGHIJKLMNPQRSTUVWXYZ123456789";
		string result;
		result.resize(length);
		for (int i = 0; i < length; i++)
			result[i] = charset[rand() % charset.length()];
		return result;
	}
	
	std::string getNewRegistrationCode(){
		bool isNewCode = false;
		std::string registrationCode = "";
		while (!isNewCode){
			registrationCode = genRandomStr(6);
			if (_registrationCodes.find(registrationCode) == _registrationCodes.end()){
				_registrationCodes.insert(registrationCode);
				isNewCode = true;
			}
		}
		return registrationCode;
	}
	
	void initEvent(){
		_injectionOrderInfos.clear();
		_mapDate2InjectionOrderNumbers.clear();
		_registrationCodes.clear();
		srand(time(NULL));
		
		std::map<int32_t, TTIME> mapDayNb2Date;
		std::map<int32_t, string> mapOrderInDay2Time;
		
		//calculate Date
		int maxday = (_totalOfDoses -1)/_numberOfInjectionsPerDay + 1;
		TTIME date = _startTimeInjection;
		for (int day = 1; day <= maxday; day++){
			bool isAvailableDay = false;
			while (!isAvailableDay){
				if (_vacationDays.find(date) != _vacationDays.end()){
					date += 24*60*60;
					continue;
				}
						
				tm *ltm = localtime(&date);
				if ((!_isWorkingOnSaturday && ltm->tm_wday==6)
						|| (!_isWorkingOnSunday && ltm->tm_wday==0)){
					date += 24*60*60;
					continue;
				}
//				stringstream ssDate;
//				ssDate<< ltm->tm_mday << "/" << 1 + ltm->tm_mon<< "/" << 1900 + ltm->tm_year;
				
				isAvailableDay = true;
				mapDayNb2Date.insert(std::make_pair(day, date));
			}
			date += 24*60*60;
		}
		
		//calculate Time
		int lastNumberOfInjections[_numberOfWorkingShifts];
		for (int i=0; i<_numberOfWorkingShifts; i++)
			lastNumberOfInjections[i] = (_numberOfInjectionsPerDay-1)/_numberOfWorkingShifts;
		for (int i=0; i<=(_numberOfInjectionsPerDay-1)%_numberOfWorkingShifts; i++)
			lastNumberOfInjections[i] ++;
		for (int i=1; i<_numberOfWorkingShifts; i++)
			lastNumberOfInjections[i] += lastNumberOfInjections[i-1];
		
		int lastNumber = 1;
		for (int i=0; i<_numberOfWorkingShifts; i++){
			for (int orderInDay=lastNumber; orderInDay <= lastNumberOfInjections[i]; orderInDay++)
				mapOrderInDay2Time.insert(std::make_pair(orderInDay, _workingShiftNames[i]));
			lastNumber = lastNumberOfInjections[i] + 1;
		}
		
		//setup injection order info
		for (int orderNumber = 1; orderNumber <= _totalOfDoses; orderNumber++){
			int dayNumber = (orderNumber -1)/_numberOfInjectionsPerDay +1;
			int orderNumberInDay = (orderNumber -1)%_numberOfInjectionsPerDay +1;
			TInjectionOrderInfo injectionOrderInfo;
			injectionOrderInfo.__set_injectionOrderNumber(orderNumber);
			injectionOrderInfo.__set_injectionDate(mapDayNb2Date[dayNumber]);
			injectionOrderInfo.__set_injectionTime(mapOrderInDay2Time[orderNumberInDay]);
			injectionOrderInfo.__set_registrationCode(getNewRegistrationCode());
			_injectionOrderInfos.insert(std::make_pair(orderNumber, injectionOrderInfo));
			TTIME injectionDate = mapDayNb2Date[orderNumber];
			_mapDate2InjectionOrderNumbers[injectionDate].insert(orderNumber);
		}
	}
	
	bool add(const TEID eventId, const TEventInfo& tEventInfo) {
		_eventId = eventId;
		_eventName = tEventInfo.eventName;
		_eventStatus = TES_PENDING;
		_startTimeRegistration = tEventInfo.startTimeRegistration;
		_startTimeInjection = tEventInfo.startTimeInjection;
		_totalOfDoses = tEventInfo.totalOfDoses;
		_numberOfInjectionsPerDay = tEventInfo.numberOfInjectionsPerDay;
		_numberOfRegistrations = 0;
		_numberOfVaccinatedChildren = 0;
		_vaccineId = tEventInfo.vaccineId;
		_centreId = tEventInfo.centreId;
		_registrations.clear();
		_workingShiftNames = tEventInfo.workingShiftNames;
		_numberOfWorkingShifts = _workingShiftNames.size();
		_numberOfInjectionsPerShift = _numberOfInjectionsPerDay/_numberOfWorkingShifts;
		_endTimeRegistration = -1;
		_extendedRegistrations.clear();
		_isWorkingOnSaturday = tEventInfo.isWorkingOnSaturday;
		_isWorkingOnSunday = tEventInfo.isWorkingOnSunday;
		_vacationDays = tEventInfo.vacationDays;
		initEvent();
		return true;
	}
	
	bool update(const TEventInfo& tEventInfo) {
		if (_eventId == -1 || _eventId != tEventInfo.eventId)
			return false;
		if (_startTimeInjection != tEventInfo.startTimeInjection
				|| _totalOfDoses != tEventInfo.totalOfDoses
				|| _numberOfInjectionsPerDay != tEventInfo.numberOfInjectionsPerDay
				|| _numberOfWorkingShifts != tEventInfo.workingShiftNames.size()
				|| _vacationDays != tEventInfo.vacationDays
				){
			
		}
		_eventName = tEventInfo.eventName;
		_eventStatus = tEventInfo.eventStatus;
		_startTimeRegistration = tEventInfo.startTimeRegistration;
		_startTimeInjection = tEventInfo.startTimeInjection;
		_totalOfDoses = tEventInfo.totalOfDoses;
		_numberOfInjectionsPerDay = tEventInfo.numberOfInjectionsPerDay;
		_vaccineId = tEventInfo.vaccineId;
		_centreId = tEventInfo.centreId;
		_workingShiftNames = tEventInfo.workingShiftNames;
		_numberOfWorkingShifts = _workingShiftNames.size();
		_numberOfInjectionsPerShift = _numberOfInjectionsPerDay/_numberOfWorkingShifts;
		_isWorkingOnSaturday = tEventInfo.isWorkingOnSaturday;
		_isWorkingOnSunday = tEventInfo.isWorkingOnSunday;
		_vacationDays = tEventInfo.vacationDays;
		initEvent();
		return true;
	}
	
	int32_t getEventStatus(){
		if (_eventStatus == TES_DISABLE)
			return _eventStatus;
		
		if (_eventStatus == TES_PENDING){
			TTIME currentTime = Poco::Timestamp().epochTime();
			if (currentTime >= _startTimeRegistration){
				if (!_extendedRegistrations.empty()){
					for (int i=0; i<_extendedRegistrations.size(); i++)
						addRegistration(_extendedRegistrations[i]);
					_extendedRegistrations.clear();
				}
				_eventStatus = TES_AVAILABLE;
				
			}
			return _eventStatus;
		}
		
		if (_eventStatus == TES_AVAILABLE){
			if (_numberOfRegistrations >= _totalOfDoses){
				_eventStatus = TES_DISABLE;
				_endTimeRegistration = Poco::Timestamp().epochTime();
			}
			return _eventStatus;
		}
		
		return -1;
	}
	
//	void getInjectionTime(TInjectionOrderInfo& response, const int32_t injectionOrderNumber){
//		response.injectionOrderNumber = injectionOrderNumber;
//		int32_t day = (injectionOrderNumber - 1)/_numberOfInjectionsPerDay + 1;
//		int32_t injectionOrderNumberInDay = (injectionOrderNumber-1)%_numberOfInjectionsPerDay + 1;
//		int32_t shift = (injectionOrderNumberInDay-1)/_numberOfInjectionsPerShift + 1;
//		
//		if (shift > _numberOfWorkingShifts)
//			shift = (injectionOrderNumberInDay-1)%_numberOfWorkingShifts+1;
//		
//		TTIME injectionTime = _startTimeInjection + (_numberOfWeekendDays + day - 1)*24*60*60;
//		tm *ltm = localtime(&injectionTime);
//		
//		if (ltm->tm_wday==6){ //Saturday => jump 2 days to Monday
//			_numberOfWeekendDays = _numberOfWeekendDays + 2;
//			injectionTime += 2*24*60*60;
//		}
//		else
//			if (ltm->tm_wday==0){ //Saturday => jump 1 day to Monday
//				_numberOfWeekendDays ++;
//				injectionTime += 24*60*60;
//			}
//		
//		tm *final_ltm = localtime(&injectionTime);
//		
////		stringstream ssDate;
////		ssDate<< final_ltm->tm_mday << "/" << 1 + final_ltm->tm_mon<< "/" << 1900 + final_ltm->tm_year;
//		
//		response.injectionDate = injectionTime;
//		
//		stringstream ssTime;
//		ssTime << _workingShiftNames[shift-1];
//		ssTime << ", ngày " << response.injectionDate;
//		
//		response.injectionTime = ssTime.str();
//	}
	/*
	void getInjectionTime(TInjectionOrderInfo& response, const int32_t injectionOrderNumber){
		response.injectionOrderNumber = injectionOrderNumber;
		int32_t day = (injectionOrderNumber - 1)/_numberOfInjectionsPerDay + 1;
		int32_t injectionOrderNumberInDay = (injectionOrderNumber-1)%_numberOfInjectionsPerDay + 1;
		int32_t shift = (injectionOrderNumberInDay-1)/_numberOfInjectionsPerShift + 1;
		
		if (shift > _numberOfWorkingShifts)
			shift = (injectionOrderNumberInDay-1)%_numberOfWorkingShifts+1;
		
		TTIME injectionTime = _startTimeInjection+ + (_numberOfWeekendDays + day - 1)*24*60*60;
		tm *ltm = localtime(&injectionTime);
		
		if (ltm->tm_wday==6){ //Saturday => jump 2 days to Monday
			_numberOfWeekendDays = _numberOfWeekendDays + 2;
			injectionTime += 2*24*60*60;
			
		}
		else
			if (ltm->tm_wday==0){ //Saturday => jump 1 day to Monday
				_numberOfWeekendDays ++;
				injectionTime += 24*60*60;
			}
		
		tm *final_ltm = localtime(&injectionTime);
		
		stringstream ssDate;
		ssDate<< final_ltm->tm_mday << "/" << 1 + final_ltm->tm_mon<< "/" << 1900 + final_ltm->tm_year;
		
		response.injectionDate = ssDate.str();
		
		stringstream ssTime;
		ssTime << _workingShiftNames[shift-1];
		ssTime << ", ngày " << response.injectionDate;
		
		response.injectionTime = ssTime.str();
	}
	*/
	
	bool isValidChildrenDateOfBirth(const TTIME childrenDateOfBirth, const TTIME injectionDate){
		TTIME distance = injectionDate - childrenDateOfBirth;
		// 2*30*24*60*60 = 5184000; 2 months ~ 60 days
		// 2*365*24*60*60 = 63072000; 2 years
		return (distance >= 5184000 && distance <= 63072000);
	}

	bool getInjectionOrder(TInjectionOrderInfo& response, const TTIME childrenDateOfBirth){
		if (_eventStatus == TES_AVAILABLE
				&& _numberOfRegistrations < _totalOfDoses){
			std::map<TTIME, std::set<int32_t> >::iterator iter;
			for (iter = _mapDate2InjectionOrderNumbers.begin(); iter != _mapDate2InjectionOrderNumbers.end(); iter++){
				if (iter->second.size() > 0 && isValidChildrenDateOfBirth(childrenDateOfBirth, iter->first)){
					std::set<int32_t>::iterator iterOrderNumber = iter->second.begin();
					response = _injectionOrderInfos[*iterOrderNumber];
					iter->second.erase(iterOrderNumber);
					_numberOfRegistrations ++;
					return true;
				}
			}
		}
		return false;
	}
	
	bool getExtendedInjectionOrder(TInjectionOrderInfo& response, const TTIME childrenDateOfBirth, const int32_t desiredOrderNumber){
		if (_eventStatus == TES_PENDING
				&& _numberOfRegistrations < _totalOfDoses){
			std::map<int32_t, TInjectionOrderInfo>::const_iterator iterOrderInfo;
			iterOrderInfo = _injectionOrderInfos.find(desiredOrderNumber);
			if (iterOrderInfo!=_injectionOrderInfos.end()){
				TInjectionOrderInfo injectionOrderInfo = iterOrderInfo->second;
				if (isValidChildrenDateOfBirth(childrenDateOfBirth, injectionOrderInfo.injectionDate) &&
						_mapDate2InjectionOrderNumbers[injectionOrderInfo.injectionDate].find(desiredOrderNumber)
						!= _mapDate2InjectionOrderNumbers[injectionOrderInfo.injectionDate].end()){
					response = injectionOrderInfo;
					_mapDate2InjectionOrderNumbers[injectionOrderInfo.injectionDate].erase(desiredOrderNumber);
					_numberOfRegistrations ++;
					return true;
				}
			}
		}
		return false;
	}
	
	bool addRegistration(const TRID registrationId){
		std::vector<TRID>::iterator pos = std::lower_bound(_registrations.begin(), _registrations.end(), registrationId);
		if ((pos == _registrations.end()) || (*pos != registrationId)) {
			_registrations.insert(pos, registrationId);
			return true;
		}
		return false;
	}
	
	bool addExtendedRegistration(const TRID registrationId){
		std::vector<TRID>::iterator pos = std::lower_bound(_extendedRegistrations.begin(), _extendedRegistrations.end(), registrationId);
		if ((pos == _extendedRegistrations.end()) || (*pos != registrationId)) {
			_extendedRegistrations.insert(pos, registrationId);
			return true;
		}
		return false;
	}
	
	bool removeRegistration(const TRID registrationId){
		std::vector<TRID>::iterator pos = std::lower_bound(_registrations.begin(), _registrations.end(), registrationId);
		if ((pos != _registrations.end()) && (*pos == registrationId)) {
			_registrations.erase(pos);
			return true;
		}
		return false;
	}
	
	void getRegistrations(std::vector<TRID> & response){
		response = _registrations;
	}
	
	void getBasicInfo(TEventBasicInfo& response){
		response.eventId = _eventId;
		response.eventName = _eventName;
		response.eventStatus = _eventStatus;
		response.startTimeRegistration = _startTimeRegistration;
		response.vaccineId = _vaccineId;
	}
	
	bool addVaccinatedChildren(){
		_numberOfVaccinatedChildren ++;
		return true;
	}
	
	bool removeVaccinatedChildren(){
		_numberOfVaccinatedChildren --;
		return true;
	}
};

#endif	/* MODELDATA_H */

