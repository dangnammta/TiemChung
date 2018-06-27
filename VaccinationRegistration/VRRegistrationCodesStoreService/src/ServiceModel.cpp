/* 
 * File:   ServiceModel.cpp
 * Author: longmd
 * 
 * Created on December 25, 2014, 9:28 AM
 */

#include "ServiceModel.h"

using namespace std;

ServiceModel::ServiceModel(void) {
}

ServiceModel::~ServiceModel(void) {
}

bool ServiceModel::add(const CRDHN::VR::Data::TEID eventId, const std::string& registrationCode, const CRDHN::VR::Data::TRID registrationId){
	class data_visitor : public KVDataVisitorT {
	public:
		data_visitor(const std::string& registrationCode, const CRDHN::VR::Data::TRID registrationId) :
		_registrationCode(registrationCode), _registrationId(registrationId), _result(false) {
		}

		virtual bool visit(const KVDataVisitorT::_KeyT& key, KVDataVisitorT::_ValueT& value) {
			return (_result = value.add(_registrationCode, _registrationId));
		}

		bool _result;
		std::string _registrationCode;
		CRDHN::VR::Data::TRID _registrationId;
	};

	if (KVStoreFactoryT::size()) {
		data_visitor visitor(registrationCode, registrationId);
		KVStoreFactoryT::visit(eventId, &visitor);
		return visitor._result;
	}
	return false;
}

bool ServiceModel::remove(const CRDHN::VR::Data::TEID eventId, const std::string& registrationCode, const CRDHN::VR::Data::TRID registrationId){
	class data_visitor : public KVDataVisitorT {
	public:
		data_visitor(const std::string& registrationCode, const CRDHN::VR::Data::TRID registrationId) :
		_registrationCode(registrationCode), _registrationId(registrationId), _result(false) {
		}

		virtual bool visit(const KVDataVisitorT::_KeyT& key, KVDataVisitorT::_ValueT& value) {
			return (_result = value.remove(_registrationCode, _registrationId));
		}

		bool _result;
		std::string _registrationCode;
		CRDHN::VR::Data::TRID _registrationId;
	};

	if (KVStoreFactoryT::size()) {
		data_visitor visitor(registrationCode, registrationId);
		KVStoreFactoryT::visit(eventId, &visitor);
		return visitor._result;
	}
	return false;
}

CRDHN::VR::Data::TRID ServiceModel::getRegistrationId(const CRDHN::VR::Data::TEID eventId, const std::string& registrationCode){
	class data_visitor : public KVDataVisitorT {
	public:
		data_visitor(const std::string& registrationCode) :
		_registrationCode(registrationCode), _registrationId(-1){
		}

		virtual bool visit(const KVDataVisitorT::_KeyT& key, KVDataVisitorT::_ValueT& value) {
			_registrationId = value.getRegistrationId(_registrationCode);
			return false;
		}

		CRDHN::VR::Data::TRID _registrationId;
		std::string _registrationCode;
	};

	if (KVStoreFactoryT::size()) {
		data_visitor visitor(registrationCode);
		KVStoreFactoryT::visit(eventId, &visitor);
		return visitor._registrationId;
	}
	return -1;
}

void ServiceModel::getRegistrationCodes(CRDHN::VR::Data::TRegistrationCodes& response, const CRDHN::VR::Data::TEID eventId){
	class data_visitor : public KVDataVisitorT {
	public:
		data_visitor(CRDHN::VR::Data::TRegistrationCodes& registrationCodes) : _registrationCodes(registrationCodes){
		}

		virtual bool visit(const KVDataVisitorT::_KeyT& key, KVDataVisitorT::_ValueT& value) {
			value.assignTo(_registrationCodes);
			return false;
		}

		CRDHN::VR::Data::TRegistrationCodes& _registrationCodes;
	};

	if (KVStoreFactoryT::size()) {
		data_visitor visitor(response);
		KVStoreFactoryT::visit(eventId, &visitor);
	}
}
bool ServiceModel::removeRegistrationCodes(const CRDHN::VR::Data::TEID eventId){
	class data_visitor : public KVDataVisitorT {
	public:
		data_visitor() : _result(false) {
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