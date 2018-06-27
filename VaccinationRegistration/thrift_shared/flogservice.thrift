namespace cpp CRDHN.Common.Log
namespace java crdhn.common.log.thrift

service TFLogService{
    void printLog(1: string category, 2: string message);
    oneway void printLog_ow(1: string category, 2: string message);
}
