/* 
 * File:   ServiceModel.cpp
 * Author: longmd
 * 
 * Created on December 25, 2014, 9:28 AM
 */

#include "ServiceModel.h"
#include "StepCounterService.h"

using namespace std;

ServiceModel::ServiceModel(void) {
}

ServiceModel::~ServiceModel(void) {
}

CRDHN::VR::Data::TCID ServiceModel::add(const CRDHN::VR::Data::TCentreInfo& centreInfo){
	class data_visitor : public KVDataVisitorT {
	public:
		data_visitor(const CRDHN::VR::Data::TCentreInfo& centreInfo) : _centreInfo(centreInfo) {
		}

		virtual bool visit(const KVDataVisitorT::_KeyT& key, KVDataVisitorT::_ValueT& value) {
			value.add(key, _centreInfo);
			return true;
		}

		const CRDHN::VR::Data::TCentreInfo& _centreInfo;
	};

	CRDHN::VR::Data::TCID centreId = StepCounterService::instance()->getNextCentreId();
	if (KVStoreFactoryT::size() && (centreId != -1)) {
		data_visitor visitor(centreInfo);
		KVStoreFactoryT::visit(centreId, &visitor);
	}
	return centreId;
}

void ServiceModel::get(CRDHN::VR::Data::TCentreInfo& response, const CRDHN::VR::Data::TCID centreId){
	class data_visitor : public KVDataVisitorT {
	public:
		data_visitor(CRDHN::VR::Data::TCentreInfo& centreInfo) : _centreInfo(centreInfo){
		}

		virtual bool visit(const KVDataVisitorT::_KeyT& key, KVDataVisitorT::_ValueT& value) {
			value.assignTo(_centreInfo);
			return false;
		}

		CRDHN::VR::Data::TCentreInfo& _centreInfo;
	};

	if (KVStoreFactoryT::size()) {
		data_visitor visitor(response);
		KVStoreFactoryT::visit(centreId, &visitor);
	}
}

bool ServiceModel::update(const CRDHN::VR::Data::TCID centreId, const CRDHN::VR::Data::TCentreInfo& centreInfo){
	class data_visitor : public KVDataVisitorT {
	public:
		data_visitor(const CRDHN::VR::Data::TCentreInfo& centreInfo) : _centreInfo(centreInfo), _result(false) {
		}

		virtual bool visit(const KVDataVisitorT::_KeyT& key, KVDataVisitorT::_ValueT& value) {
			return (_result = value.assignFrom(_centreInfo));
		}

		bool _result;
		CRDHN::VR::Data::TCentreInfo _centreInfo;
	};

	if (KVStoreFactoryT::size()) {
		data_visitor visitor(centreInfo);
		KVStoreFactoryT::visit(centreId, &visitor);
		return visitor._result;
	}
	return false;
}

bool ServiceModel::remove(const CRDHN::VR::Data::TCID centreId){
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
		KVStoreFactoryT::visit(centreId, &visitor);
		return visitor._result;
	}
	return false;
}

bool ServiceModel::addEvent(const CRDHN::VR::Data::TCID centreId, const CRDHN::VR::Data::TEID eventId){
	class data_visitor : public KVDataVisitorT {
	public:
		data_visitor(const CRDHN::VR::Data::TEID eventId) : _eventId(eventId), _result(false) {
		}

		virtual bool visit(const KVDataVisitorT::_KeyT& key, KVDataVisitorT::_ValueT& value) {
			return (_result = value.addEvent(_eventId));
		}

		const CRDHN::VR::Data::TEID _eventId;
		bool _result;
	};

	if (KVStoreFactoryT::size()) {
		data_visitor visitor(eventId);
		KVStoreFactoryT::visit(centreId, &visitor);
		return visitor._result;
	}
	return false;
}

bool ServiceModel::removeEvent(const CRDHN::VR::Data::TCID centreId, const CRDHN::VR::Data::TEID eventId){
	class data_visitor : public KVDataVisitorT {
	public:
		data_visitor(const CRDHN::VR::Data::TEID eventId) : _eventId(eventId), _result(false) {
		}

		virtual bool visit(const KVDataVisitorT::_KeyT& key, KVDataVisitorT::_ValueT& value) {
			return (_result = value.removeEvent(_eventId));
		}

		const CRDHN::VR::Data::TEID _eventId;
		bool _result;
	};

	if (KVStoreFactoryT::size()) {
		data_visitor visitor(eventId);
		KVStoreFactoryT::visit(centreId, &visitor);
		return visitor._result;
	}
	return false;
}

bool ServiceModel::addVaccine(const CRDHN::VR::Data::TCID centreId, const CRDHN::VR::Data::TVID vaccineId){
	class data_visitor : public KVDataVisitorT {
	public:
		data_visitor(const CRDHN::VR::Data::TVID vaccineId) : _vaccineId(vaccineId), _result(false) {
		}

		virtual bool visit(const KVDataVisitorT::_KeyT& key, KVDataVisitorT::_ValueT& value) {
			return (_result = value.addVaccine(_vaccineId));
		}

		const CRDHN::VR::Data::TVID _vaccineId;
		bool _result;
	};

	if (KVStoreFactoryT::size()) {
		data_visitor visitor(vaccineId);
		KVStoreFactoryT::visit(centreId, &visitor);
		return visitor._result;
	}
	return false;
}

bool ServiceModel::removeVaccine(const CRDHN::VR::Data::TCID centreId, const CRDHN::VR::Data::TVID vaccineId){
	class data_visitor : public KVDataVisitorT {
	public:
		data_visitor(const CRDHN::VR::Data::TVID vaccineId) : _vaccineId(vaccineId), _result(false) {
		}

		virtual bool visit(const KVDataVisitorT::_KeyT& key, KVDataVisitorT::_ValueT& value) {
			return (_result = value.removeVaccine(_vaccineId));
		}

		const CRDHN::VR::Data::TVID _vaccineId;
		bool _result;
	};

	if (KVStoreFactoryT::size()) {
		data_visitor visitor(vaccineId);
		KVStoreFactoryT::visit(centreId, &visitor);
		return visitor._result;
	}
	return false;
}