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

TRID StepCounterService::getNextRegistrationId(void) {
	StepCounterServiceClientT client = Configuration::getStepCounterServiceClient();
	if (client->sureOpen()) {
		try {
			return ((TRID)client->getNextValue(Configuration::_StepCounterServiceVRRegistrationIdCntName));
		}
		catch (exception& ex) {
			cout << "[VRRegistrationInfoStoreService] StepCounter::getNextRegistrationId() : " << ex.what() << endl;
		}
	}
	client->close();
	return -1;
}