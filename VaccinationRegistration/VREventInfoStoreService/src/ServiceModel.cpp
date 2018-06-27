/* 
 * File:   ServiceModel.cpp
 * Author: longmd
 * 
 * Created on December 25, 2014, 9:28 AM
 */

#include "ServiceModel.h"
#include "StepCounterService.h"

using namespace std;
using namespace CRDHN::VR::Data;

ServiceModel::ServiceModel(void) {
}

ServiceModel::~ServiceModel(void) {
}

TEID ServiceModel::add(const TEventInfo& eventInfo){
	class data_visitor : public KVDataVisitorT {
	public:
		data_visitor(const TEventInfo& eventInfo) : _eventInfo(eventInfo) {
		}

		virtual bool visit(const KVDataVisitorT::_KeyT& key, KVDataVisitorT::_ValueT& value) {
			value.add(key, _eventInfo);
			return true;
		}

		const TEventInfo& _eventInfo;
	};

	TEID eventId = StepCounterService::instance()->getNextEventId();
	if (KVStoreFactoryT::size() && (eventId != -1)) {
		data_visitor visitor(eventInfo);
		KVStoreFactoryT::visit(eventId, &visitor);
	}
	return eventId;
}

bool ServiceModel::update(const TEID eventId, const TEventInfo& eventInfo){
	class data_visitor : public KVDataVisitorT {
	public:
		data_visitor(const TEventInfo& eventInfo) : _eventInfo(eventInfo), _result(false) {
		}

		virtual bool visit(const KVDataVisitorT::_KeyT& key, KVDataVisitorT::_ValueT& value) {
			return (_result = value.update(_eventInfo));
		}

		bool _result;
		const TEventInfo& _eventInfo;
	};

	if (KVStoreFactoryT::size()) {
		data_visitor visitor(eventInfo);
		KVStoreFactoryT::visit(eventId, &visitor);
		return visitor._result;
	}
	return false;
}

void ServiceModel::get(TEventInfo& response, const TEID eventId){
	class data_visitor : public KVDataVisitorT {
	public:
		data_visitor(TEventInfo& eventInfo) : _eventInfo(eventInfo){
		}

		virtual bool visit(const KVDataVisitorT::_KeyT& key, KVDataVisitorT::_ValueT& value) {
			value.assignTo(_eventInfo);
			return false;
		}

		TEventInfo& _eventInfo;
	};

	if (KVStoreFactoryT::size()) {
		data_visitor visitor(response);
		KVStoreFactoryT::visit(eventId, &visitor);
	}
}

void ServiceModel::getBasicInfo(TEventBasicInfo& response, const TEID eventId){
	class data_visitor : public KVDataVisitorT {
	public:
		data_visitor(TEventBasicInfo& eventBasicInfo) : _eventBasicInfo(eventBasicInfo){
		}

		virtual bool visit(const KVDataVisitorT::_KeyT& key, KVDataVisitorT::_ValueT& value) {
			value.getBasicInfo(_eventBasicInfo);
			return false;
		}

		TEventBasicInfo& _eventBasicInfo;
	};

	if (KVStoreFactoryT::size()) {
		data_visitor visitor(response);
		KVStoreFactoryT::visit(eventId, &visitor);
	}
}

bool ServiceModel::remove(const TEID eventId){
	class data_visitor : public KVDataVisitorT {
	public:
		data_visitor(void) : _result(false) {
		}

		virtual bool visit(const KVDataVisitorT::_KeyT& key, KVDataVisitorT::_ValueT& value) {
			return (_result = value.clear());
		}

		bool _result;
	};

	if (KVStoreFactoryT::size()) {
		data_visitor visitor;
		KVStoreFactoryT::visit(eventId, &visitor);
		return visitor._result;
	}
	return false;
}

int32_t ServiceModel::getEventStatus(const TEID eventId){
	class data_visitor : public KVDataVisitorT {
	public:
		data_visitor(void) : _result(-1) {
		}

		virtual bool visit(const KVDataVisitorT::_KeyT& key, KVDataVisitorT::_ValueT& value) {
			_result = value.getEventStatus();
			return true;
		}

		int32_t _result;
	};

	if (KVStoreFactoryT::size()) {
		data_visitor visitor;
		KVStoreFactoryT::visit(eventId, &visitor);
		return visitor._result;
	}
	return TES_DISABLE;
}

void ServiceModel::getInjectionOrder(TInjectionOrderInfo& response, const TEID eventId, const TTIME childrenDateOfBirth){
	class data_visitor : public KVDataVisitorT {
	public:
		data_visitor(TInjectionOrderInfo& injectionOrderInfo, const TTIME childrenDateOfBirth) : 
		_injectionOrderInfo(injectionOrderInfo), _childrenDateOfBirth(childrenDateOfBirth) {
		}

		virtual bool visit(const KVDataVisitorT::_KeyT& key, KVDataVisitorT::_ValueT& value) {
			return value.getInjectionOrder(_injectionOrderInfo, _childrenDateOfBirth);
		}

		TInjectionOrderInfo& _injectionOrderInfo;
		const TTIME _childrenDateOfBirth;
	};

	if (KVStoreFactoryT::size()) {
		Poco::FastMutex::ScopedLock lock(_mutex);
		data_visitor visitor(response, childrenDateOfBirth);
		KVStoreFactoryT::visit(eventId, &visitor);
	}
}

void ServiceModel::getExtendedInjectionOrder(TInjectionOrderInfo& response, const TEID eventId, 
		const TTIME childrenDateOfBirth, const int32_t desiredOrderNumber){
	class data_visitor : public KVDataVisitorT {
	public:
		data_visitor(TInjectionOrderInfo& injectionOrderInfo, const TTIME childrenDateOfBirth, const int32_t desiredOrderNumber) : 
		_injectionOrderInfo(injectionOrderInfo), _childrenDateOfBirth(childrenDateOfBirth), _desiredOrderNumber(desiredOrderNumber) {
		}

		virtual bool visit(const KVDataVisitorT::_KeyT& key, KVDataVisitorT::_ValueT& value) {
			return value.getExtendedInjectionOrder(_injectionOrderInfo, _childrenDateOfBirth, _desiredOrderNumber);
		}

		TInjectionOrderInfo& _injectionOrderInfo;
		const TTIME _childrenDateOfBirth;
		const int32_t _desiredOrderNumber;
	};

	if (KVStoreFactoryT::size()) {
		Poco::FastMutex::ScopedLock lock(_mutex);
		data_visitor visitor(response, childrenDateOfBirth, desiredOrderNumber);
		KVStoreFactoryT::visit(eventId, &visitor);
	}
}

void ServiceModel::getNewRegistrationCode(std::string& response, const CRDHN::VR::Data::TEID eventId){
	class data_visitor : public KVDataVisitorT {
	public:
		data_visitor(std::string& newRegistrationCode) : _newRegistrationCode(newRegistrationCode){
		}

		virtual bool visit(const KVDataVisitorT::_KeyT& key, KVDataVisitorT::_ValueT& value) {
			_newRegistrationCode = value.getNewRegistrationCode();
			return true;
		}

		std::string& _newRegistrationCode;
	};

	if (KVStoreFactoryT::size()) {
		Poco::FastMutex::ScopedLock lock(_mutex);
		data_visitor visitor(response);
		KVStoreFactoryT::visit(eventId, &visitor);
	}
}

bool ServiceModel::addRegistration(const TEID eventId, const TRID registrationId){
	class data_visitor : public KVDataVisitorT {
	public:
		data_visitor(const TRID registrationId) : _registrationId(registrationId) {
		}

		virtual bool visit(const KVDataVisitorT::_KeyT& key, KVDataVisitorT::_ValueT& value) {
			return value.addRegistration(_registrationId);
		}

		const TRID _registrationId;
	};

	if (KVStoreFactoryT::size()) {
		data_visitor visitor(registrationId);
		KVStoreFactoryT::visit(eventId, &visitor);
		return true;
	}
	return false;
}

bool ServiceModel::addExtendedRegistration(const TEID eventId, const TRID registrationId){
	class data_visitor : public KVDataVisitorT {
	public:
		data_visitor(const TRID registrationId) : _registrationId(registrationId) {
		}

		virtual bool visit(const KVDataVisitorT::_KeyT& key, KVDataVisitorT::_ValueT& value) {
			return value.addExtendedRegistration(_registrationId);
		}

		const TRID _registrationId;
	};

	if (KVStoreFactoryT::size()) {
		data_visitor visitor(registrationId);
		KVStoreFactoryT::visit(eventId, &visitor);
		return true;
	}
	return false;
}

bool ServiceModel::removeRegistration(const TEID eventId, const TRID registrationId){
	class data_visitor : public KVDataVisitorT {
	public:
		data_visitor(const TRID registrationId) : _registrationId(registrationId) {
		}

		virtual bool visit(const KVDataVisitorT::_KeyT& key, KVDataVisitorT::_ValueT& value) {
			return value.removeRegistration(_registrationId);
		}

		const TRID _registrationId;
	};

	if (KVStoreFactoryT::size()) {
		data_visitor visitor(registrationId);
		KVStoreFactoryT::visit(eventId, &visitor);
		return true;
	}
	return false;
}

void ServiceModel::getRegistrations(std::vector<TRID> & response, const TEID eventId){
	class data_visitor : public KVDataVisitorT {
	public:
		data_visitor(std::vector<TRID> & registrations) : _registrations(registrations){
		}

		virtual bool visit(const KVDataVisitorT::_KeyT& key, KVDataVisitorT::_ValueT& value) {
			value.getRegistrations(_registrations);
			return false;
		}

		std::vector<TRID> & _registrations;
	};

	if (KVStoreFactoryT::size()) {
		data_visitor visitor(response);
		KVStoreFactoryT::visit(eventId, &visitor);
	}
}

bool ServiceModel::addVaccinatedChildren(const TEID eventId){
	class data_visitor : public KVDataVisitorT {
	public:
		data_visitor(void) : _result(false) {
		}

		virtual bool visit(const KVDataVisitorT::_KeyT& key, KVDataVisitorT::_ValueT& value) {
			return (_result = value.addVaccinatedChildren());
		}

		bool _result;
	};

	if (KVStoreFactoryT::size()) {
		data_visitor visitor;
		KVStoreFactoryT::visit(eventId, &visitor);
		return visitor._result;
	}
	return false;
}

bool ServiceModel::removeVaccinatedChildren(const TEID eventId){
	class data_visitor : public KVDataVisitorT {
	public:
		data_visitor(void) : _result(false) {
		}

		virtual bool visit(const KVDataVisitorT::_KeyT& key, KVDataVisitorT::_ValueT& value) {
			return (_result = value.removeVaccinatedChildren());
		}

		bool _result;
	};

	if (KVStoreFactoryT::size()) {
		data_visitor visitor;
		KVStoreFactoryT::visit(eventId, &visitor);
		return visitor._result;
	}
	return false;
}