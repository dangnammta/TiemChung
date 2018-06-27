include "vr_shared.thrift"
include "vr_response.thrift"

namespace java crdhn.vr.thrift
namespace cpp CRDHN.VR

service TVRCentreManagerService {
	vr_response.TCMCreateCentreResponse createCentre(1: vr_shared.TCentreInfo centreInfo);
	vr_response.TCMGetCentreInfoResponse getCentreInfo(1: vr_shared.TCID centreId);
	vr_response.TError updateCentreInfo(1: vr_shared.TCID centreId, 2: vr_shared.TCentreInfo centreInfo);
	vr_response.TError removeCentre(1: vr_shared.TCID centreId);
	vr_response.TCMGetCentresResponse getCentres();

	vr_response.TCMAddVaccineResponse addVaccine(1: vr_shared.TCID centreId, 2: vr_shared.TVaccineInfo vaccineInfo);
	vr_response.TCMGetVaccineInfoResponse getVaccineInfo(1: vr_shared.TVID vaccineId);
	vr_response.TError updateVaccineInfo(1: vr_shared.TVID vaccineId, 2: vr_shared.TVaccineInfo vaccineInfo);
	vr_response.TError removeVaccine(1: vr_shared.TCID centreId, 2: vr_shared.TVID vaccineId);
	vr_response.TCMGetVaccinesResponse getVaccines(1: vr_shared.TCID centreId);
}