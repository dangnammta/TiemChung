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
#include "TVRCentreInfoStoreService.h"
#include "TVRVaccineInfoStoreService.h"
#include "TStringToI32SetService.h"
#include "TFLogService.h"

typedef FCore::Thrift::TClientWrapper<FCore::Thrift::TCompactClient< CRDHN::VR::TVRCentreInfoStoreServiceClient> > VRCentreInfoStoreServiceClientT;
typedef FCore::Thrift::TClientWrapper<FCore::Thrift::TCompactClient< CRDHN::VR::TVRVaccineInfoStoreServiceClient> > VRVaccineInfoStoreServiceClientT;
typedef FCore::Thrift::TClientWrapper<FCore::Thrift::TCompactClient< CRDHN::Common::TStringToI32SetServiceClient> > VRCommonStoreServiceClientT;
typedef FCore::Thrift::TClientWrapper<FCore::Thrift::TCompactClient< CRDHN::Common::Log::TFLogServiceClient> > FLogServiceClientT;

#endif	/* TCLIENTTYPES_H */