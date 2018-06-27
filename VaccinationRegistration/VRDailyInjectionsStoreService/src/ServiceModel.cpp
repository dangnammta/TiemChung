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

bool ServiceModel::add(const std::string& key, const TRID registrationId){
	class data_visitor : public KVDataVisitorT {
	public:
		data_visitor(const TRID registrationId) : _registrationId(registrationId) {
		}

		virtual bool visit(const KVDataVisitorT::_KeyT& key, KVDataVisitorT::_ValueT& value) {
			return value.add(_registrationId);
		}

		const TRID _registrationId;
	};

	if (KVStoreFactoryT::size()) {
		data_visitor visitor(registrationId);
		KVStoreFactoryT::visit(key, &visitor);
		return true;
	}
	return false;
}

void ServiceModel::get(TDailyInjections& _return, const std::string& key){
	class data_visitor : public KVDataVisitorT {
	public:
		data_visitor(TDailyInjections& dailyInjections) : _dailyInjections(dailyInjections){
		}

		virtual bool visit(const KVDataVisitorT::_KeyT& key, KVDataVisitorT::_ValueT& value) {
			value.assignTo(_dailyInjections);
			return false;
		}

		TDailyInjections& _dailyInjections;
	};

	if (KVStoreFactoryT::size()) {
		data_visitor visitor(_return);
		KVStoreFactoryT::visit(key, &visitor);
	}
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