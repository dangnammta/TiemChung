/* 
 * File:   StepCounterService.h
 * Author: longmd
 *
 * Created on December 30, 2015, 12:55 AM
 */

#ifndef STEPCOUNTERSERVICE_H
#define	STEPCOUNTERSERVICE_H

#include "vr_shared_types.h"

using namespace CRDHN::VR::Data;

class StepCounterService {
public:
	static StepCounterService* instance(void);

	TRID getNextRegistrationId(void);

private:
	static StepCounterService* _instance;
};

#endif	/* STEPCOUNTERSERVICE_H */

