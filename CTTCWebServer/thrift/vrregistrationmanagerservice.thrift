include "vr_shared.thrift"
include "vr_response.thrift"

namespace java crdhn.vr.thrift
namespace cpp CRDHN.VR

service TVRRegistrationManagerService {
	vr_response.TError getEventStatus(1: vr_shared.TVEID eventId);
	vr_response.TRMAddRegistrationResponse addRegistration(1: vr_shared.TRegistrationInfo registrationInfo);
	vr_response.TRMAddExtraRegistrationResponse addExtraRegistration(1: vr_shared.TRegistrationInfo registrationInfo);
}

