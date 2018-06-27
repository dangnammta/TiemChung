include "vr_shared.thrift"
include "vr_response.thrift"

namespace java crdhn.vr.thrift
namespace cpp CRDHN.VR

service TVRVaccinationManagerService {
	vr_response.TVMCreateEventResponse createEvent(1: vr_shared.TVaccinationEventInfo eventInfo);
	vr_response.TError updateEventInfo(1: vr_shared.TVEID eventId, 2: vr_shared.TVaccinationEventInfo eventInfo);
	vr_response.TVMGetEventInfoResponse getEventInfo(1: vr_shared.TVEID eventId);
	vr_response.TError removeEvent(1: vr_shared.TVEID eventId);

	vr_response.TVMGetRegistrationsResponse getRegistrations(1: vr_shared.TVEID eventId);
	vr_response.TVMGetVaccinationEventsResponse getVaccinationEvents(1: vr_shared.TCID centreId);
	vr_response.TError updateInjectionStatus(1: vr_shared.TRID registrationId, 2: i32 injectionStatus);
}

