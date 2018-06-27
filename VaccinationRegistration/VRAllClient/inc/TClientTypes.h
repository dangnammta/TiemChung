/* 
 * File:   TClientTypes.h
 * Author: longmd
 *
 * Created on June 25, 2012, 10:25 AM
 */

#ifndef TCLIENTTYPES_H
#define	TCLIENTTYPES_H

#include "TVRRegistrationManagerService.h"
#include "TVREventManagerService.h"
#include "TVRCentreManagerService.h"
//#include "TVRCentreInfoStoreService.h"
#include "FCore/Thrift/TCompactClient.h"
#include "FCore/Thrift/TClientWrapper.h"

typedef FCore::Thrift::TClientWrapper< FCore::Thrift::TCompactClient< CRDHN::VR::TVRRegistrationManagerServiceClient > > VRRegistrationManagerServiceClientT;
typedef FCore::Thrift::TClientWrapper< FCore::Thrift::TCompactClient< CRDHN::VR::TVREventManagerServiceClient > > VREventManagerServiceClientT;
//typedef FCore::Thrift::TClientWrapper< FCore::Thrift::TCompactClient< CRDHN::VR::TVRCentreInfoStoreServiceClient > > VRCentreInfoStoreServiceClientT;
typedef FCore::Thrift::TClientWrapper< FCore::Thrift::TCompactClient< CRDHN::VR::TVRCentreManagerServiceClient > > VRCentreManagerServiceClientT;

#endif	/* TCLIENTTYPES_H */

