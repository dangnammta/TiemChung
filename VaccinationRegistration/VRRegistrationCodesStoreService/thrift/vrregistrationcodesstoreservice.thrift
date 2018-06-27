include "vr_shared.thrift"

namespace java crdhn.vr.thrift
namespace cpp CRDHN.VR

service TVRRegistrationCodesStoreService {
	bool add(1: vr_shared.TEID eventId, 2: string registrationCode, 3: vr_shared.TRID registrationId);
	bool remove(1: vr_shared.TEID eventId, 2: string registrationCode, 3: vr_shared.TRID registrationId);
	vr_shared.TRID getRegistrationId(1: vr_shared.TEID eventId, 2: string registrationCode);

	vr_shared.TRegistrationCodes getRegistrationCodes(1: vr_shared.TEID eventId);
	bool removeRegistrationCodes(1: vr_shared.TEID eventId);
}