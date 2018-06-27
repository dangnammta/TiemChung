namespace java crdhn.vr.thrift.data
namespace cpp CRDHN.VR.Data

typedef i32 TCID //centre
typedef i32 TEID //event
typedef i64 TRID //registration
typedef i32 TVID //vaccine
typedef i64 TTIME //time

enum TEventStatus{
	TES_AVAILABLE = 0;
	TES_PENDING = 1;
	TES_DISABLE = 2;
	TES_DELETED = 3;
}

enum TInjectionStatus{
	TIS_REGISTED = 0;
	TIS_VACCINATED = 1;
	TIS_CANCELED = 2;
}

enum TRegistrationType{
	TRT_ONLINE = 0;
	TRT_MANUAL = 1;
}

struct TCentreInfo {
	1: required TCID centreId = -1;
	2: required string name = "";
	3: required string address = "";
	4: required string phoneNumber = "";
	5: required list<TEID> eventIds;
	6: required list<TVID> vaccineIds;
}

struct TVaccineInfo {
	1: required TVID vaccineId = -1;
	2: required string name = "";
	3: required string description = "";
}

struct TInjectionOrderInfo{
	1: required string registrationCode = "";
	2: required i32 injectionOrderNumber = -1;
	3: required TTIME injectionDate = -1;
	4: required string injectionTime = "";
}

struct TEventInfo{
	1: required TEID eventId = -1;
	2: required string eventName = "";
	3: required i32 eventStatus = TEventStatus.TES_DISABLE;
	4: required TTIME startTimeRegistration = -1;
	5: required TTIME startTimeInjection = -1;
	6: required i32 totalOfDoses = 0;
	7: required i32 numberOfInjectionsPerDay = 0;
	8: required i32 numberOfRegistrations = 0;
	9: required i32 numberOfVaccinatedChildren = 0;
	10: required TVID vaccineId = -1;
	11: required TCID centreId = -1;
	12: required list<TRID> registrations;
	13: required list<string> workingShiftNames;
	14:	required TTIME endTimeRegistration = -1;
	15: required list<TRID> extendedRegistrations;
	16: required bool isWorkingOnSaturday = false;
	17: required bool isWorkingOnSunday = false;
	18: required set<TTIME> vacationDays;
	19: required map<i32, TInjectionOrderInfo> injectionOrderInfos;
	20: required map<TTIME, set<i32> > mapDate2InjectionOrderNumbers;
	21: required set<string> registrationCodes;
}

struct TRegistrationCodes{
	1: required TEID eventId = -1;
	2: required map<string, TRID> mapRCode2RId;
}

struct TEventBasicInfo{
	1: required TEID eventId = -1;
	2: required string eventName = "";
	3: required i32 eventStatus = TEventStatus.TES_DISABLE;
	4: required TTIME startTimeRegistration = -1;
	5: required TVID vaccineId = -1;
	6: required string vaccineName = "";
}

struct TRegistrationInfo{
	1: required TRID registrationId = -1;
	2: required i32 registrationType = TRegistrationType.TRT_ONLINE;
	3: required TTIME registrationTime = -1;
	4: required TEID eventId = -1;
	5: required string childrenName = "";
	6: required TTIME childrenDateOfBirth = "";
	7: required string childrenCarrierName = "";
	8: required string childrenCarrierPIN = "";
	9: required string childrenCarrierPhoneNumber = "";
	10: required string childrenCarrierEmail = "";
	11: required string childrenCarrierAddress = "";
	12: required i32 injectionStatus = TInjectionStatus.TIS_REGISTED;
	13: required string registrationCode = "";
	14: required i32 injectionOrderNumber = -1;
	15: required string injectionTime = "";
	16: required TTIME injectionDate = -1;
	17: required TTIME injectionRealTime = -1;
}

struct TDailyRegistrations{
	1: map<string, TRID> registrations;
}

struct TDailyInjections{
	1: list<TRID> registrations;
}