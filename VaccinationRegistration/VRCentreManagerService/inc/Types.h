/* 
 * File:   Types.h
 * Author: longmd
 *
 * Created on December 30, 2014, 11:28 AM
 */

#ifndef TYPES_H
#define	TYPES_H

#include "thrift/protocol/TBinaryProtocol.h"
#include "thrift/protocol/TCompactProtocol.h"
#include "FCore/Thrift/TNonblockingServer.h"
#include "TVRCentreManagerService.h"

typedef apache::thrift::protocol::TCompactProtocolFactory ThriftProtocolFactoryT;
typedef CRDHN::VR::TVRCentreManagerServiceIf ThriftIfaceT;
typedef CRDHN::VR::TVRCentreManagerServiceProcessor ThriftProcessorT;
typedef FCore::Thrift::TNonblockingServer<ThriftIfaceT, ThriftProcessorT, ThriftProtocolFactoryT> ThriftServerT;

#endif	/* TYPES_H */

