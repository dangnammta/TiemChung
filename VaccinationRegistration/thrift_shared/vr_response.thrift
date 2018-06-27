include "vr_shared.thrift"

namespace java crdhn.vr.thrift.response
namespace cpp CRDHN.VR.Response

enum TErrorCode {
	EC_OK = 0;
	EC_SYSTEM = 1;
	EC_PARAM_ERROR = 2;
	EC_NOT_ALLOWED = 3;
	
	EC_PIN_REGISTERED = 10;
	EC_REGISTRATION_FULL = 11;

	EC_REGISTRATION_AVAILABLE = 20;
	EC_REGISTRATION_PENDING = 21;
	EC_REGISTRATION_DISABLE = 22;

	EC_EVENT_INFO = 101;
	EC_REGISTRATION_INFO = 102;
	EC_DAILY_REGISTRATIONS = 103;
	EC_DAILY_INJECTION = 104;
	EC_LIST_EVENTS = 105;
	EC_VACCINE_INFO = 106;
	EC_CENTRE_INFO = 107;
	EC_COMMON_STORE = 108;
}

struct TError{
	1: required i32 errorCode = TErrorCode.EC_OK;
	2: optional string errorMessage;
}

//RegistrationManager
struct TRMAddRegistrationResponse {
	1: required TError error;
	2: required string registrationCode;
	3: required i32 injectionOrderNumber;
	4: required vr_shared.TTIME injectionDate;
	5: required string injectionTime;
}

struct TRMAddExtraRegistrationResponse {
	1: required TError error;
	2: required string registrationCode;
	3: required i32 injectionOrderNumber;
	4: required vr_shared.TTIME injectionDate;
	5: required string injectionTime;
}

//VaccinationManager
struct TVMCreateEventResponse {
	1: required TError error;
	2: optional vr_shared.TEID eventId;
}

struct TVMGetEventInfoResponse {
	1: required TError error;
	2: optional vr_shared.TEventInfo eventInfo;
}

struct TVMGetEventsResponse {
	1: required TError error;
	2: optional list<vr_shared.TEventBasicInfo> events;
}

struct TVMGetRegistrationResponse {
	1: required TError error;
	2: optional vr_shared.TRegistrationInfo registrationInfo;
}

struct TVMGetRegistrationsResponse {
	1: required TError error;
	2: optional list<vr_shared.TRegistrationInfo> registrations;
}

//CentreManager
struct TCMCreateCentreResponse{
	1: required TError error;
	2: optional vr_shared.TCID centreId;
}

struct TCMGetCentreInfoResponse{
	1: required TError error;
	2: optional vr_shared.TCentreInfo centreInfo;
}

struct TCMGetCentresResponse{
	1: required TError error;
	2: optional list<vr_shared.TCentreInfo> centres;
}

struct TCMAddVaccineResponse{
	1: required TError error;
	2: optional vr_shared.TVID vaccineId;
}

struct TCMGetVaccineInfoResponse{
	1: required TError error;
	2: optional vr_shared.TVaccineInfo vaccineInfo;
}

struct TCMGetVaccinesResponse{
	1: required TError error;
	2: optional list<vr_shared.TVaccineInfo> vaccines;
}