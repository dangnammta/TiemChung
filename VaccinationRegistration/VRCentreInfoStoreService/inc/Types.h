/* 
 * File:   Types.h
 * Author: longmd
 *
 * Created on November 27, 2014, 4:09 PM
 */

#ifndef TYPES_H
#define	TYPES_H

#include <string>
#include "FCore/Utility/Data/SimpleSerializer.h"
#include "FCore/Utility/ServiceConf/KVStoreFactory.h"
#include "FCore/Utility/Data/TSerializer.h"
#include "thrift/protocol/TCompactProtocol.h"
#include "FCore/Thrift/TNonblockingServer.h"
#include "FCore/Database/AbstractKVStore.h"
#include "TVRCentreInfoStoreService.h"
#include "ModelData.h"

using namespace CRDHN::VR::Data;

typedef TCID KeyT;
typedef TCentreInfo ValueT;
typedef ModelData ModelDataT;

typedef apache::thrift::protocol::TCompactProtocol ThriftProtocolT;
typedef apache::thrift::protocol::TCompactProtocolFactory ThriftProtocolFactoryT;
typedef CRDHN::VR::TVRCentreInfoStoreServiceIf ThriftIfaceT;
typedef CRDHN::VR::TVRCentreInfoStoreServiceProcessor ThriftProcessorT;
typedef FCore::Thrift::TNonblockingServer<ThriftIfaceT, ThriftProcessorT, ThriftProtocolFactoryT> ThriftServerT;

typedef FCore::Utility::Data::SimpleSerializer KeySerializerT;
typedef FCore::Utility::Data::TSerializer<ValueT, ThriftProtocolT> ValueSerializerT;
typedef FCore::Utility::ServiceConf::KVStoreFactory<KeyT, ModelDataT, KeySerializerT, ValueSerializerT> KVStoreFactoryT;
typedef FCore::Database::KVDataVisitor<KeyT, ModelDataT> KVDataVisitorT;

#endif	/* TYPES_H */

