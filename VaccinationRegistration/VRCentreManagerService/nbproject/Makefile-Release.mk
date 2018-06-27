#
# Generated Makefile - do not edit!
#
# Edit the Makefile in the project folder instead (../Makefile). Each target
# has a -pre and a -post target defined where you can add customized code.
#
# This makefile implements configuration specific macros and targets.


# Environment
MKDIR=mkdir
CP=cp
GREP=grep
NM=nm
CCADMIN=CCadmin
RANLIB=ranlib
CC=gcc
CCC=g++
CXX=g++
FC=gfortran
AS=as

# Macros
CND_PLATFORM=GNU-Linux-x86
CND_DLIB_EXT=so
CND_CONF=Release
CND_DISTDIR=dist
CND_BUILDDIR=build

# Include project Makefile
include Makefile

# Object Directory
OBJECTDIR=${CND_BUILDDIR}/${CND_CONF}/${CND_PLATFORM}

# Object Files
OBJECTFILES= \
	${OBJECTDIR}/src/Configuration.o \
	${OBJECTDIR}/src/FLogService.o \
	${OBJECTDIR}/src/ServerApp.o \
	${OBJECTDIR}/src/ServiceModel.o \
	${OBJECTDIR}/src/VRCentreInfoStoreService.o \
	${OBJECTDIR}/src/VRCommonStoreService.o \
	${OBJECTDIR}/src/VRVaccineInfoStoreService.o \
	${OBJECTDIR}/src/main.o \
	${OBJECTDIR}/thrift/gen-cpp/TFLogService.o \
	${OBJECTDIR}/thrift/gen-cpp/TStringToI32SetService.o \
	${OBJECTDIR}/thrift/gen-cpp/TStringToI32SetServiceR.o \
	${OBJECTDIR}/thrift/gen-cpp/TVRCentreInfoStoreService.o \
	${OBJECTDIR}/thrift/gen-cpp/TVRCentreManagerService.o \
	${OBJECTDIR}/thrift/gen-cpp/TVRVaccineInfoStoreService.o \
	${OBJECTDIR}/thrift/gen-cpp/flogservice_constants.o \
	${OBJECTDIR}/thrift/gen-cpp/flogservice_types.o \
	${OBJECTDIR}/thrift/gen-cpp/stringtoi32set_shared_constants.o \
	${OBJECTDIR}/thrift/gen-cpp/stringtoi32set_shared_types.o \
	${OBJECTDIR}/thrift/gen-cpp/stringtoi32setservice_constants.o \
	${OBJECTDIR}/thrift/gen-cpp/stringtoi32setservice_types.o \
	${OBJECTDIR}/thrift/gen-cpp/vr_response_constants.o \
	${OBJECTDIR}/thrift/gen-cpp/vr_response_types.o \
	${OBJECTDIR}/thrift/gen-cpp/vr_shared_constants.o \
	${OBJECTDIR}/thrift/gen-cpp/vr_shared_types.o \
	${OBJECTDIR}/thrift/gen-cpp/vrcentreinfostoreservice_constants.o \
	${OBJECTDIR}/thrift/gen-cpp/vrcentreinfostoreservice_types.o \
	${OBJECTDIR}/thrift/gen-cpp/vrcentremanagerservice_constants.o \
	${OBJECTDIR}/thrift/gen-cpp/vrcentremanagerservice_types.o \
	${OBJECTDIR}/thrift/gen-cpp/vrvaccineinfostoreservice_constants.o \
	${OBJECTDIR}/thrift/gen-cpp/vrvaccineinfostoreservice_types.o


# C Compiler Flags
CFLAGS=

# CC Compiler Flags
CCFLAGS=
CXXFLAGS=

# Fortran Compiler Flags
FFLAGS=

# Assembler Flags
ASFLAGS=

# Link Libraries and Options
LDLIBSOPTIONS=../corelibs/FUtility/lib/libfutility.a ../corelibs/FThrift/lib/libfthrift.a ../corelibs/FMongoDB/lib/libfmongodb.a ../corelibs/FPoco/lib/libfpoco.a ../corelibs/FEvent/lib/libfevent.a ../corelibs/FZ/lib/libfz.a ../corelibs/FOpenSSL/libs/libssl.a ../corelibs/FOpenSSL/libs/libcrypto.a -lpthread -ldl -lrt

# Build Targets
.build-conf: ${BUILD_SUBPROJECTS}
	"${MAKE}"  -f nbproject/Makefile-${CND_CONF}.mk bin/vrcentremanagerservice

bin/vrcentremanagerservice: ../corelibs/FUtility/lib/libfutility.a

bin/vrcentremanagerservice: ../corelibs/FThrift/lib/libfthrift.a

bin/vrcentremanagerservice: ../corelibs/FMongoDB/lib/libfmongodb.a

bin/vrcentremanagerservice: ../corelibs/FPoco/lib/libfpoco.a

bin/vrcentremanagerservice: ../corelibs/FEvent/lib/libfevent.a

bin/vrcentremanagerservice: ../corelibs/FZ/lib/libfz.a

bin/vrcentremanagerservice: ../corelibs/FOpenSSL/libs/libssl.a

bin/vrcentremanagerservice: ../corelibs/FOpenSSL/libs/libcrypto.a

bin/vrcentremanagerservice: ${OBJECTFILES}
	${MKDIR} -p bin
	${LINK.cc} -o bin/vrcentremanagerservice ${OBJECTFILES} ${LDLIBSOPTIONS}

${OBJECTDIR}/src/Configuration.o: src/Configuration.cpp 
	${MKDIR} -p ${OBJECTDIR}/src
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/src/Configuration.o src/Configuration.cpp

${OBJECTDIR}/src/FLogService.o: src/FLogService.cpp 
	${MKDIR} -p ${OBJECTDIR}/src
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/src/FLogService.o src/FLogService.cpp

${OBJECTDIR}/src/ServerApp.o: src/ServerApp.cpp 
	${MKDIR} -p ${OBJECTDIR}/src
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/src/ServerApp.o src/ServerApp.cpp

${OBJECTDIR}/src/ServiceModel.o: src/ServiceModel.cpp 
	${MKDIR} -p ${OBJECTDIR}/src
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/src/ServiceModel.o src/ServiceModel.cpp

${OBJECTDIR}/src/VRCentreInfoStoreService.o: src/VRCentreInfoStoreService.cpp 
	${MKDIR} -p ${OBJECTDIR}/src
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/src/VRCentreInfoStoreService.o src/VRCentreInfoStoreService.cpp

${OBJECTDIR}/src/VRCommonStoreService.o: src/VRCommonStoreService.cpp 
	${MKDIR} -p ${OBJECTDIR}/src
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/src/VRCommonStoreService.o src/VRCommonStoreService.cpp

${OBJECTDIR}/src/VRVaccineInfoStoreService.o: src/VRVaccineInfoStoreService.cpp 
	${MKDIR} -p ${OBJECTDIR}/src
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/src/VRVaccineInfoStoreService.o src/VRVaccineInfoStoreService.cpp

${OBJECTDIR}/src/main.o: src/main.cpp 
	${MKDIR} -p ${OBJECTDIR}/src
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/src/main.o src/main.cpp

${OBJECTDIR}/thrift/gen-cpp/TFLogService.o: thrift/gen-cpp/TFLogService.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/TFLogService.o thrift/gen-cpp/TFLogService.cpp

${OBJECTDIR}/thrift/gen-cpp/TStringToI32SetService.o: thrift/gen-cpp/TStringToI32SetService.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/TStringToI32SetService.o thrift/gen-cpp/TStringToI32SetService.cpp

${OBJECTDIR}/thrift/gen-cpp/TStringToI32SetServiceR.o: thrift/gen-cpp/TStringToI32SetServiceR.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/TStringToI32SetServiceR.o thrift/gen-cpp/TStringToI32SetServiceR.cpp

${OBJECTDIR}/thrift/gen-cpp/TVRCentreInfoStoreService.o: thrift/gen-cpp/TVRCentreInfoStoreService.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/TVRCentreInfoStoreService.o thrift/gen-cpp/TVRCentreInfoStoreService.cpp

${OBJECTDIR}/thrift/gen-cpp/TVRCentreManagerService.o: thrift/gen-cpp/TVRCentreManagerService.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/TVRCentreManagerService.o thrift/gen-cpp/TVRCentreManagerService.cpp

${OBJECTDIR}/thrift/gen-cpp/TVRVaccineInfoStoreService.o: thrift/gen-cpp/TVRVaccineInfoStoreService.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/TVRVaccineInfoStoreService.o thrift/gen-cpp/TVRVaccineInfoStoreService.cpp

${OBJECTDIR}/thrift/gen-cpp/flogservice_constants.o: thrift/gen-cpp/flogservice_constants.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/flogservice_constants.o thrift/gen-cpp/flogservice_constants.cpp

${OBJECTDIR}/thrift/gen-cpp/flogservice_types.o: thrift/gen-cpp/flogservice_types.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/flogservice_types.o thrift/gen-cpp/flogservice_types.cpp

${OBJECTDIR}/thrift/gen-cpp/stringtoi32set_shared_constants.o: thrift/gen-cpp/stringtoi32set_shared_constants.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/stringtoi32set_shared_constants.o thrift/gen-cpp/stringtoi32set_shared_constants.cpp

${OBJECTDIR}/thrift/gen-cpp/stringtoi32set_shared_types.o: thrift/gen-cpp/stringtoi32set_shared_types.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/stringtoi32set_shared_types.o thrift/gen-cpp/stringtoi32set_shared_types.cpp

${OBJECTDIR}/thrift/gen-cpp/stringtoi32setservice_constants.o: thrift/gen-cpp/stringtoi32setservice_constants.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/stringtoi32setservice_constants.o thrift/gen-cpp/stringtoi32setservice_constants.cpp

${OBJECTDIR}/thrift/gen-cpp/stringtoi32setservice_types.o: thrift/gen-cpp/stringtoi32setservice_types.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/stringtoi32setservice_types.o thrift/gen-cpp/stringtoi32setservice_types.cpp

${OBJECTDIR}/thrift/gen-cpp/vr_response_constants.o: thrift/gen-cpp/vr_response_constants.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/vr_response_constants.o thrift/gen-cpp/vr_response_constants.cpp

${OBJECTDIR}/thrift/gen-cpp/vr_response_types.o: thrift/gen-cpp/vr_response_types.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/vr_response_types.o thrift/gen-cpp/vr_response_types.cpp

${OBJECTDIR}/thrift/gen-cpp/vr_shared_constants.o: thrift/gen-cpp/vr_shared_constants.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/vr_shared_constants.o thrift/gen-cpp/vr_shared_constants.cpp

${OBJECTDIR}/thrift/gen-cpp/vr_shared_types.o: thrift/gen-cpp/vr_shared_types.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/vr_shared_types.o thrift/gen-cpp/vr_shared_types.cpp

${OBJECTDIR}/thrift/gen-cpp/vrcentreinfostoreservice_constants.o: thrift/gen-cpp/vrcentreinfostoreservice_constants.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/vrcentreinfostoreservice_constants.o thrift/gen-cpp/vrcentreinfostoreservice_constants.cpp

${OBJECTDIR}/thrift/gen-cpp/vrcentreinfostoreservice_types.o: thrift/gen-cpp/vrcentreinfostoreservice_types.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/vrcentreinfostoreservice_types.o thrift/gen-cpp/vrcentreinfostoreservice_types.cpp

${OBJECTDIR}/thrift/gen-cpp/vrcentremanagerservice_constants.o: thrift/gen-cpp/vrcentremanagerservice_constants.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/vrcentremanagerservice_constants.o thrift/gen-cpp/vrcentremanagerservice_constants.cpp

${OBJECTDIR}/thrift/gen-cpp/vrcentremanagerservice_types.o: thrift/gen-cpp/vrcentremanagerservice_types.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/vrcentremanagerservice_types.o thrift/gen-cpp/vrcentremanagerservice_types.cpp

${OBJECTDIR}/thrift/gen-cpp/vrvaccineinfostoreservice_constants.o: thrift/gen-cpp/vrvaccineinfostoreservice_constants.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/vrvaccineinfostoreservice_constants.o thrift/gen-cpp/vrvaccineinfostoreservice_constants.cpp

${OBJECTDIR}/thrift/gen-cpp/vrvaccineinfostoreservice_types.o: thrift/gen-cpp/vrvaccineinfostoreservice_types.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/vrvaccineinfostoreservice_types.o thrift/gen-cpp/vrvaccineinfostoreservice_types.cpp

# Subprojects
.build-subprojects:

# Clean Targets
.clean-conf: ${CLEAN_SUBPROJECTS}
	${RM} -r ${CND_BUILDDIR}/${CND_CONF}
	${RM} bin/vrcentremanagerservice

# Subprojects
.clean-subprojects:

# Enable dependency checking
.dep.inc: .depcheck-impl

include .dep.inc
