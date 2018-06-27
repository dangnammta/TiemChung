include "vr_shared.thrift"

namespace java crdhn.vr.thrift
namespace cpp CRDHN.VR

service TVREventInfoStoreService {
	vr_shared.TEID add(1: vr_shared.TEventInfo eventInfo);
	bool update(1: vr_shared.TEID eventId, 2: vr_shared.TEventInfo eventInfo);
	vr_shared.TEventInfo get(1: vr_shared.TEID eventId);
	vr_shared.TEventBasicInfo getBasicInfo(1: vr_shared.TEID eventId);
	bool remove(1: vr_shared.TEID eventId);

	i32 getEventStatus(1: vr_shared.TEID eventId);
	vr_shared.TInjectionOrderInfo getInjectionOrder(1: vr_shared.TEID eventId, 2: vr_shared.TTIME childrenDateOfBirth);
	vr_shared.TInjectionOrderInfo getExtendedInjectionOrder(1: vr_shared.TEID eventId, 2: vr_shared.TTIME childrenDateOfBirth, 3: i32 desiredOrderNumber);
	string getNewRegistrationCode(1: vr_shared.TEID eventId);

	bool addRegistration(1: vr_shared.TEID eventId, 2: vr_shared.TRID registrationId);
	bool addExtendedRegistration(1: vr_shared.TEID eventId, 2: vr_shared.TRID registrationId);
	bool removeRegistration(1: vr_shared.TEID eventId, 2: vr_shared.TRID registrationId);
	list<vr_shared.TRID> getRegistrations(1: vr_shared.TEID eventId);
	
	bool addVaccinatedChildren(1: vr_shared.TEID eventId);
	bool removeVaccinatedChildren(1: vr_shared.TEID eventId);
}