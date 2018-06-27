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
#include "TVRDailyRegistrationsStoreService.h"
#include "ModelData.h"

using namespace CRDHN::VR::Data;

typedef std::string KeyT;
typedef TDailyRegistrations ValueT;
typedef ModelData ModelDataT;

typedef apache::thrift::protocol::TCompactProtocol ThriftProtocolT;
typedef apache::thrift::protocol::TCompactProtocolFactory ThriftProtocolFactoryT;
typedef CRDHN::VR::TVRDailyRegistrationsStoreServiceIf ThriftIfaceT;
typedef CRDHN::VR::TVRDailyRegistrationsStoreServiceProcessor ThriftProcessorT;
typedef FCore::Thrift::TNonblockingServer<ThriftIfaceT, ThriftProcessorT, ThriftProtocolFactoryT> ThriftServerT;

typedef FCore::Utility::Data::SimpleSerializer KeySerializerT;
typedef FCore::Utility::Data::TSerializer<ValueT, ThriftProtocolT> ValueSerializerT;
typedef FCore::Utility::ServiceConf::KVStoreFactory<KeyT, ModelDataT, KeySerializerT, ValueSerializerT> KVStoreFactoryT;
typedef FCore::Database::KVDataVisitor<KeyT, ModelDataT> KVDataVisitorT;

#endif	/* TYPES_H */

