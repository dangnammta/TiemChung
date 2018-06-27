/* 
 * File:   StepCounterService.cpp
 * Author: longmd
 * 
 * Created on December 30, 2015, 12:55 AM
 */

#include <iostream>
#include "StepCounterService.h"
#include "Configuration.h"

using namespace std;
using namespace CRDHN::VR::Data;

StepCounterService* StepCounterService::_instance = NULL;

StepCounterService* StepCounterService::instance(void) {
	if (!_instance) _instance = new StepCounterService();
	return _instance;
}

TVID StepCounterService::getNextVaccineId(void) {
	StepCounterServiceClientT client = Configuration::getStepCounterServiceClient();
	if (client->sureOpen()) {
		try {
			return ((TVID)client->getNextValue(Configuration::_StepCounterServiceVRVaccineIdCntName));
		}
		catch (exception& ex) {
			cout << "[VRVaccineInfoStoreService] StepCounter::getNextVaccineId() : " << ex.what() << endl;
		}
	}
	client->close();
	return -1;
}