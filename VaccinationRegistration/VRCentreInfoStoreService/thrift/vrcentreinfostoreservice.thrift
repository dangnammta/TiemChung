include "vr_shared.thrift"

namespace java crdhn.vr.thrift
namespace cpp CRDHN.VR

service TVRCentreInfoStoreService {
	vr_shared.TCID add(1: vr_shared.TCentreInfo centreInfo);
	vr_shared.TCentreInfo get(1: vr_shared.TCID centreId);
	bool update(1: vr_shared.TCID centreId, 2: vr_shared.TCentreInfo centreInfo);
	bool remove(1: vr_shared.TCID centreId);

	bool addEvent(1: vr_shared.TCID centreId, 2: vr_shared.TEID eventId);
	bool removeEvent(1: vr_shared.TCID centreId, 2: vr_shared.TEID eventId);

	bool addVaccine(1: vr_shared.TCID centreId, 2: vr_shared.TVID vaccineId);
	bool removeVaccine(1: vr_shared.TCID centreId, 2: vr_shared.TVID vaccineId);
}