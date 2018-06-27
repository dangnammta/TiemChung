include "vr_shared.thrift"

namespace java crdhn.vr.thrift
namespace cpp CRDHN.VR

service TVRRegistrationInfoStoreService {
	vr_shared.TRID add(1: vr_shared.TRegistrationInfo registrationInfo);
	bool update(1: vr_shared.TRID registrationId, 2: vr_shared.TRegistrationInfo registrationInfo);
	vr_shared.TRegistrationInfo get(1: vr_shared.TRID registrationId);
	bool remove(1: vr_shared.TRID registrationId);
	bool setRegistrationCode(1: vr_shared.TRID registrationId, 2: string registrationCode);
	bool updateInjectionStatus(1: vr_shared.TRID registrationId, 2: i32 injectionStatus);
}

