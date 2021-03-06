/* 
 * File:   TClientType.h
 * Author: longmd
 *
 * Created on January 14, 2015, 2:55 PM
 */

#ifndef TCLIENTTYPES_H
#define	TCLIENTTYPES_H

#include "FCore/Thrift/TBinaryClient.h"
#include "FCore/Thrift/TClientWrapper.h"
#include "TStepCounterService.h"

typedef FCore::Thrift::TClientWrapper< FCore::Thrift::TBinaryClient<CRDHN::Common::TStepCounterServiceClient> > StepCounterServiceClientT;

#endif	/* TCLIENTTYPES_H */
