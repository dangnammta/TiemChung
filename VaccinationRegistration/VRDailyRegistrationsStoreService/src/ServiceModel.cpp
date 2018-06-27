/* 
 * File:   ServiceModel.cpp
 * Author: longmd
 * 
 * Created on December 25, 2014, 9:28 AM
 */

#include "ServiceModel.h"

using namespace std;
using namespace CRDHN::VR::Data;

ServiceModel::ServiceModel(void) {
}

ServiceModel::~ServiceModel(void) {
}

int32_t ServiceModel::add(const std::string& key, const std::string& PIN, const TRID registrationId){
	class data_visitor : public KVDataVisitorT {
	public:
		data_visitor(const std::string& PIN, const TRID registrationId) : _PIN(PIN), _registrationId(registrationId), _result(-1) {
		}

		virtual bool visit(const KVDataVisitorT::_KeyT& key, KVDataVisitorT::_ValueT& value) {
			_result = value.add(_PIN, _registrationId);
			return true;
		}

		const std::string& _PIN;
		const TRID _registrationId;
		int32_t _result;
	};

	if (KVStoreFactoryT::size()) {
		data_visitor visitor(PIN, registrationId);
		KVStoreFactoryT::visit(key, &visitor);
		return visitor._result;
	}
	return -1;
}

void ServiceModel::get(TDailyRegistrations& _return, const std::string& key){
	class data_visitor : public KVDataVisitorT {
	public:
		data_visitor(TDailyRegistrations& dailyRegistrations) : _dailyRegistrations(dailyRegistrations){
		}

		virtual bool visit(const KVDataVisitorT::_KeyT& key, KVDataVisitorT::_ValueT& value) {
			value.assignTo(_dailyRegistrations);
			return false;
		}

		TDailyRegistrations& _dailyRegistrations;
	};

	if (KVStoreFactoryT::size()) {
		data_visitor visitor(_return);
		KVStoreFactoryT::visit(key, &visitor);
	}
}

int32_t ServiceModel::getRegistrationId(const std::string& key, const std::string& PIN){
	class data_visitor : public KVDataVisitorT {
	public:
		data_visitor(const std::string& PIN) : _PIN(PIN), _result(-1) {
		}

		virtual bool visit(const KVDataVisitorT::_KeyT& key, KVDataVisitorT::_ValueT& value) {
			_result = value.getRegistrationId(_PIN);
			return false;
		}

		const std::string& _PIN;
		int32_t _result;
	};

	if (KVStoreFactoryT::size()) {
		data_visitor visitor(PIN);
		KVStoreFactoryT::visit(key, &visitor);
		return visitor._result;
	}
	return -1;
}

bool ServiceModel::remove(const std::string& key){
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
		KVStoreFactoryT::visit(key, &visitor);
		return visitor._result;
	}
	return false;
}