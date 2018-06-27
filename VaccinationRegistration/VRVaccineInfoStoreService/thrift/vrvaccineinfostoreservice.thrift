include "vr_shared.thrift"

namespace java crdhn.vr.thrift
namespace cpp CRDHN.VR

service TVRVaccineInfoStoreService {
	vr_shared.TVID add(1: vr_shared.TVaccineInfo vaccineInfo);
	vr_shared.TVaccineInfo get(1: vr_shared.TVID vaccineId);
	bool update(1: vr_shared.TVID vaccineId, 2: vr_shared.TVaccineInfo vaccineInfo);
	bool remove(1: vr_shared.TVID vaccineId);
}