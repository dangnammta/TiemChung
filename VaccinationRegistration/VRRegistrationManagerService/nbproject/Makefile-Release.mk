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
	${OBJECTDIR}/src/VRDailyInjectionsStoreService.o \
	${OBJECTDIR}/src/VRDailyRegistrationsStoreService.o \
	${OBJECTDIR}/src/VREventInfoStoreService.o \
	${OBJECTDIR}/src/VRRegistrationInfoStoreService.o \
	${OBJECTDIR}/src/main.o \
	${OBJECTDIR}/thrift/gen-cpp/TFLogService.o \
	${OBJECTDIR}/thrift/gen-cpp/TVRDailyInjectionsStoreService.o \
	${OBJECTDIR}/thrift/gen-cpp/TVRDailyRegistrationsStoreService.o \
	${OBJECTDIR}/thrift/gen-cpp/TVREventInfoStoreService.o \
	${OBJECTDIR}/thrift/gen-cpp/TVRRegistrationInfoStoreService.o \
	${OBJECTDIR}/thrift/gen-cpp/TVRRegistrationManagerService.o \
	${OBJECTDIR}/thrift/gen-cpp/flogservice_constants.o \
	${OBJECTDIR}/thrift/gen-cpp/flogservice_types.o \
	${OBJECTDIR}/thrift/gen-cpp/vr_response_constants.o \
	${OBJECTDIR}/thrift/gen-cpp/vr_response_types.o \
	${OBJECTDIR}/thrift/gen-cpp/vr_shared_constants.o \
	${OBJECTDIR}/thrift/gen-cpp/vr_shared_types.o \
	${OBJECTDIR}/thrift/gen-cpp/vrdailyinjectionsstoreservice_constants.o \
	${OBJECTDIR}/thrift/gen-cpp/vrdailyinjectionsstoreservice_types.o \
	${OBJECTDIR}/thrift/gen-cpp/vrdailyregistrationsstoreservice_constants.o \
	${OBJECTDIR}/thrift/gen-cpp/vrdailyregistrationsstoreservice_types.o \
	${OBJECTDIR}/thrift/gen-cpp/vreventinfostoreservice_constants.o \
	${OBJECTDIR}/thrift/gen-cpp/vreventinfostoreservice_types.o \
	${OBJECTDIR}/thrift/gen-cpp/vrregistrationinfostoreservice_constants.o \
	${OBJECTDIR}/thrift/gen-cpp/vrregistrationinfostoreservice_types.o \
	${OBJECTDIR}/thrift/gen-cpp/vrregistrationmanagerservice_constants.o \
	${OBJECTDIR}/thrift/gen-cpp/vrregistrationmanagerservice_types.o


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
	"${MAKE}"  -f nbproject/Makefile-${CND_CONF}.mk bin/vrregistrationmanagerservice

bin/vrregistrationmanagerservice: ../corelibs/FUtility/lib/libfutility.a

bin/vrregistrationmanagerservice: ../corelibs/FThrift/lib/libfthrift.a

bin/vrregistrationmanagerservice: ../corelibs/FMongoDB/lib/libfmongodb.a

bin/vrregistrationmanagerservice: ../corelibs/FPoco/lib/libfpoco.a

bin/vrregistrationmanagerservice: ../corelibs/FEvent/lib/libfevent.a

bin/vrregistrationmanagerservice: ../corelibs/FZ/lib/libfz.a

bin/vrregistrationmanagerservice: ../corelibs/FOpenSSL/libs/libssl.a

bin/vrregistrationmanagerservice: ../corelibs/FOpenSSL/libs/libcrypto.a

bin/vrregistrationmanagerservice: ${OBJECTFILES}
	${MKDIR} -p bin
	${LINK.cc} -o bin/vrregistrationmanagerservice ${OBJECTFILES} ${LDLIBSOPTIONS}

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

${OBJECTDIR}/src/VRDailyInjectionsStoreService.o: src/VRDailyInjectionsStoreService.cpp 
	${MKDIR} -p ${OBJECTDIR}/src
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/src/VRDailyInjectionsStoreService.o src/VRDailyInjectionsStoreService.cpp

${OBJECTDIR}/src/VRDailyRegistrationsStoreService.o: src/VRDailyRegistrationsStoreService.cpp 
	${MKDIR} -p ${OBJECTDIR}/src
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/src/VRDailyRegistrationsStoreService.o src/VRDailyRegistrationsStoreService.cpp

${OBJECTDIR}/src/VREventInfoStoreService.o: src/VREventInfoStoreService.cpp 
	${MKDIR} -p ${OBJECTDIR}/src
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/src/VREventInfoStoreService.o src/VREventInfoStoreService.cpp

${OBJECTDIR}/src/VRRegistrationInfoStoreService.o: src/VRRegistrationInfoStoreService.cpp 
	${MKDIR} -p ${OBJECTDIR}/src
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/src/VRRegistrationInfoStoreService.o src/VRRegistrationInfoStoreService.cpp

${OBJECTDIR}/src/main.o: src/main.cpp 
	${MKDIR} -p ${OBJECTDIR}/src
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/src/main.o src/main.cpp

${OBJECTDIR}/thrift/gen-cpp/TFLogService.o: thrift/gen-cpp/TFLogService.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/TFLogService.o thrift/gen-cpp/TFLogService.cpp

${OBJECTDIR}/thrift/gen-cpp/TVRDailyInjectionsStoreService.o: thrift/gen-cpp/TVRDailyInjectionsStoreService.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/TVRDailyInjectionsStoreService.o thrift/gen-cpp/TVRDailyInjectionsStoreService.cpp

${OBJECTDIR}/thrift/gen-cpp/TVRDailyRegistrationsStoreService.o: thrift/gen-cpp/TVRDailyRegistrationsStoreService.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/TVRDailyRegistrationsStoreService.o thrift/gen-cpp/TVRDailyRegistrationsStoreService.cpp

${OBJECTDIR}/thrift/gen-cpp/TVREventInfoStoreService.o: thrift/gen-cpp/TVREventInfoStoreService.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/TVREventInfoStoreService.o thrift/gen-cpp/TVREventInfoStoreService.cpp

${OBJECTDIR}/thrift/gen-cpp/TVRRegistrationInfoStoreService.o: thrift/gen-cpp/TVRRegistrationInfoStoreService.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/TVRRegistrationInfoStoreService.o thrift/gen-cpp/TVRRegistrationInfoStoreService.cpp

${OBJECTDIR}/thrift/gen-cpp/TVRRegistrationManagerService.o: thrift/gen-cpp/TVRRegistrationManagerService.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/TVRRegistrationManagerService.o thrift/gen-cpp/TVRRegistrationManagerService.cpp

${OBJECTDIR}/thrift/gen-cpp/flogservice_constants.o: thrift/gen-cpp/flogservice_constants.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/flogservice_constants.o thrift/gen-cpp/flogservice_constants.cpp

${OBJECTDIR}/thrift/gen-cpp/flogservice_types.o: thrift/gen-cpp/flogservice_types.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/flogservice_types.o thrift/gen-cpp/flogservice_types.cpp

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

${OBJECTDIR}/thrift/gen-cpp/vrdailyinjectionsstoreservice_constants.o: thrift/gen-cpp/vrdailyinjectionsstoreservice_constants.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/vrdailyinjectionsstoreservice_constants.o thrift/gen-cpp/vrdailyinjectionsstoreservice_constants.cpp

${OBJECTDIR}/thrift/gen-cpp/vrdailyinjectionsstoreservice_types.o: thrift/gen-cpp/vrdailyinjectionsstoreservice_types.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/vrdailyinjectionsstoreservice_types.o thrift/gen-cpp/vrdailyinjectionsstoreservice_types.cpp

${OBJECTDIR}/thrift/gen-cpp/vrdailyregistrationsstoreservice_constants.o: thrift/gen-cpp/vrdailyregistrationsstoreservice_constants.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/vrdailyregistrationsstoreservice_constants.o thrift/gen-cpp/vrdailyregistrationsstoreservice_constants.cpp

${OBJECTDIR}/thrift/gen-cpp/vrdailyregistrationsstoreservice_types.o: thrift/gen-cpp/vrdailyregistrationsstoreservice_types.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/vrdailyregistrationsstoreservice_types.o thrift/gen-cpp/vrdailyregistrationsstoreservice_types.cpp

${OBJECTDIR}/thrift/gen-cpp/vreventinfostoreservice_constants.o: thrift/gen-cpp/vreventinfostoreservice_constants.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/vreventinfostoreservice_constants.o thrift/gen-cpp/vreventinfostoreservice_constants.cpp

${OBJECTDIR}/thrift/gen-cpp/vreventinfostoreservice_types.o: thrift/gen-cpp/vreventinfostoreservice_types.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/vreventinfostoreservice_types.o thrift/gen-cpp/vreventinfostoreservice_types.cpp

${OBJECTDIR}/thrift/gen-cpp/vrregistrationinfostoreservice_constants.o: thrift/gen-cpp/vrregistrationinfostoreservice_constants.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/vrregistrationinfostoreservice_constants.o thrift/gen-cpp/vrregistrationinfostoreservice_constants.cpp

${OBJECTDIR}/thrift/gen-cpp/vrregistrationinfostoreservice_types.o: thrift/gen-cpp/vrregistrationinfostoreservice_types.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/vrregistrationinfostoreservice_types.o thrift/gen-cpp/vrregistrationinfostoreservice_types.cpp

${OBJECTDIR}/thrift/gen-cpp/vrregistrationmanagerservice_constants.o: thrift/gen-cpp/vrregistrationmanagerservice_constants.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/vrregistrationmanagerservice_constants.o thrift/gen-cpp/vrregistrationmanagerservice_constants.cpp

${OBJECTDIR}/thrift/gen-cpp/vrregistrationmanagerservice_types.o: thrift/gen-cpp/vrregistrationmanagerservice_types.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/vrregistrationmanagerservice_types.o thrift/gen-cpp/vrregistrationmanagerservice_types.cpp

# Subprojects
.build-subprojects:

# Clean Targets
.clean-conf: ${CLEAN_SUBPROJECTS}
	${RM} -r ${CND_BUILDDIR}/${CND_CONF}
	${RM} bin/vrregistrationmanagerservice

# Subprojects
.clean-subprojects:

# Enable dependency checking
.dep.inc: .depcheck-impl

include .dep.inc
