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

StepCounterService* StepCounterService::_instance = NULL;

StepCounterService* StepCounterService::instance(void) {
	if (!_instance) _instance = new StepCounterService();
	return _instance;
}

CRDHN::VR::Data::TVID StepCounterService::getNextCentreId(void) {
	StepCounterServiceClientT client = Configuration::getStepCounterServiceClient();
	if (client->sureOpen()) {
		try {
			return ((CRDHN::VR::Data::TCID)client->getNextValue(Configuration::_StepCounterServiceVRCentreIdCntName));
		}
		catch (exception& ex) {
			cout << "[VRCentreInfoStoreService] StepCounter::getNextCentreId() : " << ex.what() << endl;
		}
	}
	client->close();
	return -1;
}