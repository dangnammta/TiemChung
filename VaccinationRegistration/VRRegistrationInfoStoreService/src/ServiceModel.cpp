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

TRID ServiceModel::add(const TRegistrationInfo& registrationInfo){
	class data_visitor : public KVDataVisitorT {
	public:
		data_visitor(const TRegistrationInfo& registrationInfo) : _registrationInfo(registrationInfo) {
		}

		virtual bool visit(const KVDataVisitorT::_KeyT& key, KVDataVisitorT::_ValueT& value) {
			value.add(key, _registrationInfo);
			return true;
		}

		const TRegistrationInfo& _registrationInfo;
	};

	TRID registrationId = StepCounterService::instance()->getNextRegistrationId();
	if (KVStoreFactoryT::size() && (registrationId != -1)) {
		data_visitor visitor(registrationInfo);
		KVStoreFactoryT::visit(registrationId, &visitor);
	}
	return registrationId;
}

bool ServiceModel::update(const TRID registrationId, const TRegistrationInfo& registrationInfo){
	class data_visitor : public KVDataVisitorT {
	public:
		data_visitor(const TRegistrationInfo& registrationInfo) : _registrationInfo(registrationInfo), _result(false) {
		}

		virtual bool visit(const KVDataVisitorT::_KeyT& key, KVDataVisitorT::_ValueT& value) {
			return (_result = value.update(_registrationInfo));
		}

		bool _result;
		const TRegistrationInfo& _registrationInfo;
	};

	if (KVStoreFactoryT::size()) {
		data_visitor visitor(registrationInfo);
		KVStoreFactoryT::visit(registrationId, &visitor);
		return visitor._result;
	}
	return false;
}

void ServiceModel::get(TRegistrationInfo& _return, const TRID registrationId){
	class data_visitor : public KVDataVisitorT {
	public:
		data_visitor(TRegistrationInfo& registrationInfo) : _registrationInfo(registrationInfo){
		}

		virtual bool visit(const KVDataVisitorT::_KeyT& key, KVDataVisitorT::_ValueT& value) {
			value.assignTo(_registrationInfo);
			return false;
		}

		TRegistrationInfo& _registrationInfo;
	};

	if (KVStoreFactoryT::size()) {
		data_visitor visitor(_return);
		KVStoreFactoryT::visit(registrationId, &visitor);
	}
}

bool ServiceModel::remove(const TRID registrationId){
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
		KVStoreFactoryT::visit(registrationId, &visitor);
		return visitor._result;
	}
	return false;
}

bool ServiceModel::setRegistrationCode(const TRID registrationId, const std::string& registrationCode){
	class data_visitor : public KVDataVisitorT {
	public:
		data_visitor(const std::string& registrationCode) : _registrationCode(registrationCode), _result(false) {
		}

		virtual bool visit(const KVDataVisitorT::_KeyT& key, KVDataVisitorT::_ValueT& value) {
			return (_result = value.setRegistrationCode(_registrationCode));
		}

		bool _result;
		const std::string& _registrationCode;
	};

	if (KVStoreFactoryT::size()) {
		data_visitor visitor(registrationCode);
		KVStoreFactoryT::visit(registrationId, &visitor);
		return visitor._result;
	}
	return false;
}

bool ServiceModel::updateInjectionStatus(const TRID registrationId, const int32_t injectionStatus){
	class data_visitor : public KVDataVisitorT {
	public:
		data_visitor(const int32_t injectionStatus) : _injectionStatus(injectionStatus), _result(false) {
		}

		virtual bool visit(const KVDataVisitorT::_KeyT& key, KVDataVisitorT::_ValueT& value) {
			return (_result = value.updateInjectionStatus(_injectionStatus));
		}

		bool _result;
		const int32_t _injectionStatus;
	};

	if (KVStoreFactoryT::size()) {
		data_visitor visitor(injectionStatus);
		KVStoreFactoryT::visit(registrationId, &visitor);
		return visitor._result;
	}
	return false;
}