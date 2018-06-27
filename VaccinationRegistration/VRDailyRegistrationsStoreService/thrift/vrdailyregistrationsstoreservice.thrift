include "vr_shared.thrift"

namespace java crdhn.vr.thrift
namespace cpp CRDHN.VR

service TVRDailyRegistrationsStoreService {
	i32 add(1: string key, 2: string PIN, 3: vr_shared.TRID registrationId);
	vr_shared.TDailyRegistrations get(1: string key);
	i32 getRegistrationId(1: string key, 2: string PIN);
	bool remove(1: string key);
}

