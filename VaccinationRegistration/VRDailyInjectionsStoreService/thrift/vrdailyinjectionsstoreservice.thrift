include "vr_shared.thrift"

namespace java crdhn.vr.thrift
namespace cpp CRDHN.VR

service TVRDailyInjectionsStoreService {
	bool add(1: string key, 2: vr_shared.TRID registrationId);
	vr_shared.TDailyInjections get(1: string key);
	bool remove(1: string key);
}

