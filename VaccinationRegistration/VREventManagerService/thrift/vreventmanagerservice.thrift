include "vr_shared.thrift"
include "vr_response.thrift"

namespace java crdhn.vr.thrift
namespace cpp CRDHN.VR

service TVREventManagerService {
	vr_response.TVMCreateEventResponse createEvent(1: vr_shared.TEventInfo eventInfo);
	vr_response.TError updateEventInfo(1: vr_shared.TEID eventId, 2: vr_shared.TEventInfo eventInfo);
	vr_response.TVMGetEventInfoResponse getEventInfo(1: vr_shared.TEID eventId);
	vr_response.TError removeEvent(1: vr_shared.TEID eventId);
	vr_response.TVMGetEventsResponse getEvents(1: vr_shared.TCID centreId);

	vr_response.TError updateRegistration(1: vr_shared.TRID registrationId, 2: vr_shared.TRegistrationInfo registrationInfo);
	vr_response.TError removeRegistration(1: vr_shared.TRID registrationId);
	vr_response.TVMGetRegistrationResponse getRegistration(1: vr_shared.TRID registrationId);
	vr_response.TVMGetRegistrationsResponse getRegistrations(1: vr_shared.TEID eventId);
	vr_response.TError updateInjectionStatus(1: vr_shared.TRID registrationId, 2: i32 injectionStatus);
}

