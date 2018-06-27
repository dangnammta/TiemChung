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

TEID StepCounterService::getNextEventId(void) {
	StepCounterServiceClientT client = Configuration::getStepCounterServiceClient();
	if (client->sureOpen()) {
		try {
			return ((TEID)client->getNextValue(Configuration::_StepCounterServiceVREventIdCntName));
		}
		catch (exception& ex) {
			cout << "[VREventInfoStoreService] StepCounter::getNextEventId() : " << ex.what() << endl;
		}
	}
	client->close();
	return -1;
}