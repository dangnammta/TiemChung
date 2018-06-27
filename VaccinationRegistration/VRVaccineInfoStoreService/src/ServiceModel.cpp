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

TVID ServiceModel::add(const TVaccineInfo& vaccineInfo){
	class data_visitor : public KVDataVisitorT {
	public:
		data_visitor(const TVaccineInfo& vaccineInfo) : _vaccineInfo(vaccineInfo) {
		}

		virtual bool visit(const KVDataVisitorT::_KeyT& key, KVDataVisitorT::_ValueT& value) {
			value.add(key, _vaccineInfo);
			return true;
		}

		const TVaccineInfo& _vaccineInfo;
	};

	TVID id = StepCounterService::instance()->getNextVaccineId();
	if (KVStoreFactoryT::size() && (id != -1)) {
		data_visitor visitor(vaccineInfo);
		KVStoreFactoryT::visit(id, &visitor);
	}
	return id;
}

void ServiceModel::get(TVaccineInfo& _return, const TVID vaccineId){
	class data_visitor : public KVDataVisitorT {
	public:
		data_visitor(TVaccineInfo& vaccineInfo) : _vaccineInfo(vaccineInfo){
		}

		virtual bool visit(const KVDataVisitorT::_KeyT& key, KVDataVisitorT::_ValueT& value) {
			value.assignTo(_vaccineInfo);
			return false;
		}

		TVaccineInfo& _vaccineInfo;
	};

	if (KVStoreFactoryT::size()) {
		data_visitor visitor(_return);
		KVStoreFactoryT::visit(vaccineId, &visitor);
	}
}

bool ServiceModel::update(const CRDHN::VR::Data::TVID vaccineId, const CRDHN::VR::Data::TVaccineInfo& vaccineInfo){
	class data_visitor : public KVDataVisitorT {
	public:
		data_visitor(const CRDHN::VR::Data::TVaccineInfo& vaccineInfo) : _vaccineInfo(vaccineInfo), _result(false) {
		}

		virtual bool visit(const KVDataVisitorT::_KeyT& key, KVDataVisitorT::_ValueT& value) {
			return (_result = value.assignFrom(_vaccineInfo));
		}

		bool _result;
		CRDHN::VR::Data::TVaccineInfo _vaccineInfo;
	};

	if (KVStoreFactoryT::size()) {
		data_visitor visitor(vaccineInfo);
		KVStoreFactoryT::visit(vaccineId, &visitor);
		return visitor._result;
	}
	return false;
}

bool ServiceModel::remove(const CRDHN::VR::Data::TVID vaccineId){
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
		KVStoreFactoryT::visit(vaccineId, &visitor);
		return visitor._result;
	}
	return false;
}