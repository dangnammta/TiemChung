/* 
 * File:   TClientType.h
 * Author: longmd
 *
 * Created on January 14, 2015, 2:55 PM
 */

#ifndef TCLIENTTYPES_H
#define	TCLIENTTYPES_H

#include "FCore/Thrift/TCompactClient.h"
#include "FCore/Thrift/TBinaryClient.h"
#include "FCore/Thrift/TClientWrapper.h"
#include "TVRRegistrationInfoStoreService.h"
#include "TVRDailyRegistrationsStoreService.h"
#include "TVRDailyInjectionsStoreService.h"
#include "TVREventInfoStoreService.h"
#include "TFLogService.h"

typedef FCore::Thrift::TClientWrapper<FCore::Thrift::TCompactClient< CRDHN::VR::TVRRegistrationInfoStoreServiceClient> > VRRegistrationInfoStoreServiceClientT;
typedef FCore::Thrift::TClientWrapper<FCore::Thrift::TCompactClient< CRDHN::VR::TVRDailyRegistrationsStoreServiceClient> > VRDailyRegistrationsStoreServiceClientT;
typedef FCore::Thrift::TClientWrapper<FCore::Thrift::TCompactClient< CRDHN::VR::TVRDailyInjectionsStoreServiceClient> > VRDailyInjectionsStoreServiceClientT;
typedef FCore::Thrift::TClientWrapper<FCore::Thrift::TCompactClient< CRDHN::VR::TVREventInfoStoreServiceClient> > VREventInfoStoreServiceClientT;
typedef FCore::Thrift::TClientWrapper<FCore::Thrift::TCompactClient< CRDHN::Common::Log::TFLogServiceClient> > FLogServiceClientT;

#endif	/* TCLIENTTYPES_H */