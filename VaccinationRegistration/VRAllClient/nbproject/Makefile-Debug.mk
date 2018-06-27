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
CND_CONF=Debug
CND_DISTDIR=dist
CND_BUILDDIR=build

# Include project Makefile
include Makefile

# Object Directory
OBJECTDIR=${CND_BUILDDIR}/${CND_CONF}/${CND_PLATFORM}

# Object Files
OBJECTFILES= \
	${OBJECTDIR}/main.o \
	${OBJECTDIR}/src/Configuration.o \
	${OBJECTDIR}/src/ServerApp.o \
	${OBJECTDIR}/thrift/gen-cpp/TVRRegistrationManagerService.o \
	${OBJECTDIR}/thrift/gen-cpp/TVRVaccinationCentreInfoStoreService.o \
	${OBJECTDIR}/thrift/gen-cpp/TVRVaccinationManagerService.o \
	${OBJECTDIR}/thrift/gen-cpp/vr_response_constants.o \
	${OBJECTDIR}/thrift/gen-cpp/vr_response_types.o \
	${OBJECTDIR}/thrift/gen-cpp/vr_shared_constants.o \
	${OBJECTDIR}/thrift/gen-cpp/vr_shared_types.o \
	${OBJECTDIR}/thrift/gen-cpp/vrregistrationmanagerservice_constants.o \
	${OBJECTDIR}/thrift/gen-cpp/vrregistrationmanagerservice_types.o \
	${OBJECTDIR}/thrift/gen-cpp/vrvaccinationcentreinfostoreservice_constants.o \
	${OBJECTDIR}/thrift/gen-cpp/vrvaccinationcentreinfostoreservice_types.o \
	${OBJECTDIR}/thrift/gen-cpp/vrvaccinationmanagerservice_constants.o \
	${OBJECTDIR}/thrift/gen-cpp/vrvaccinationmanagerservice_types.o


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
	"${MAKE}"  -f nbproject/Makefile-${CND_CONF}.mk bin/vrallclientd

bin/vrallclientd: ../corelibs/FUtility/lib/libfutility.a

bin/vrallclientd: ../corelibs/FThrift/lib/libfthrift.a

bin/vrallclientd: ../corelibs/FMongoDB/lib/libfmongodb.a

bin/vrallclientd: ../corelibs/FPoco/lib/libfpoco.a

bin/vrallclientd: ../corelibs/FEvent/lib/libfevent.a

bin/vrallclientd: ../corelibs/FZ/lib/libfz.a

bin/vrallclientd: ../corelibs/FOpenSSL/libs/libssl.a

bin/vrallclientd: ../corelibs/FOpenSSL/libs/libcrypto.a

bin/vrallclientd: ${OBJECTFILES}
	${MKDIR} -p bin
	${LINK.cc} -o bin/vrallclientd ${OBJECTFILES} ${LDLIBSOPTIONS}

${OBJECTDIR}/main.o: main.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/main.o main.cpp

${OBJECTDIR}/src/Configuration.o: src/Configuration.cpp 
	${MKDIR} -p ${OBJECTDIR}/src
	${RM} "$@.d"
	$(COMPILE.cc) -g -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/src/Configuration.o src/Configuration.cpp

${OBJECTDIR}/src/ServerApp.o: src/ServerApp.cpp 
	${MKDIR} -p ${OBJECTDIR}/src
	${RM} "$@.d"
	$(COMPILE.cc) -g -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/src/ServerApp.o src/ServerApp.cpp

${OBJECTDIR}/thrift/gen-cpp/TVRRegistrationManagerService.o: thrift/gen-cpp/TVRRegistrationManagerService.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -g -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/TVRRegistrationManagerService.o thrift/gen-cpp/TVRRegistrationManagerService.cpp

${OBJECTDIR}/thrift/gen-cpp/TVRVaccinationCentreInfoStoreService.o: thrift/gen-cpp/TVRVaccinationCentreInfoStoreService.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -g -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/TVRVaccinationCentreInfoStoreService.o thrift/gen-cpp/TVRVaccinationCentreInfoStoreService.cpp

${OBJECTDIR}/thrift/gen-cpp/TVRVaccinationManagerService.o: thrift/gen-cpp/TVRVaccinationManagerService.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -g -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/TVRVaccinationManagerService.o thrift/gen-cpp/TVRVaccinationManagerService.cpp

${OBJECTDIR}/thrift/gen-cpp/vr_response_constants.o: thrift/gen-cpp/vr_response_constants.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -g -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/vr_response_constants.o thrift/gen-cpp/vr_response_constants.cpp

${OBJECTDIR}/thrift/gen-cpp/vr_response_types.o: thrift/gen-cpp/vr_response_types.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -g -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/vr_response_types.o thrift/gen-cpp/vr_response_types.cpp

${OBJECTDIR}/thrift/gen-cpp/vr_shared_constants.o: thrift/gen-cpp/vr_shared_constants.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -g -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/vr_shared_constants.o thrift/gen-cpp/vr_shared_constants.cpp

${OBJECTDIR}/thrift/gen-cpp/vr_shared_types.o: thrift/gen-cpp/vr_shared_types.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -g -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/vr_shared_types.o thrift/gen-cpp/vr_shared_types.cpp

${OBJECTDIR}/thrift/gen-cpp/vrregistrationmanagerservice_constants.o: thrift/gen-cpp/vrregistrationmanagerservice_constants.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -g -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/vrregistrationmanagerservice_constants.o thrift/gen-cpp/vrregistrationmanagerservice_constants.cpp

${OBJECTDIR}/thrift/gen-cpp/vrregistrationmanagerservice_types.o: thrift/gen-cpp/vrregistrationmanagerservice_types.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -g -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/vrregistrationmanagerservice_types.o thrift/gen-cpp/vrregistrationmanagerservice_types.cpp

${OBJECTDIR}/thrift/gen-cpp/vrvaccinationcentreinfostoreservice_constants.o: thrift/gen-cpp/vrvaccinationcentreinfostoreservice_constants.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -g -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/vrvaccinationcentreinfostoreservice_constants.o thrift/gen-cpp/vrvaccinationcentreinfostoreservice_constants.cpp

${OBJECTDIR}/thrift/gen-cpp/vrvaccinationcentreinfostoreservice_types.o: thrift/gen-cpp/vrvaccinationcentreinfostoreservice_types.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -g -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/vrvaccinationcentreinfostoreservice_types.o thrift/gen-cpp/vrvaccinationcentreinfostoreservice_types.cpp

${OBJECTDIR}/thrift/gen-cpp/vrvaccinationmanagerservice_constants.o: thrift/gen-cpp/vrvaccinationmanagerservice_constants.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -g -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/vrvaccinationmanagerservice_constants.o thrift/gen-cpp/vrvaccinationmanagerservice_constants.cpp

${OBJECTDIR}/thrift/gen-cpp/vrvaccinationmanagerservice_types.o: thrift/gen-cpp/vrvaccinationmanagerservice_types.cpp 
	${MKDIR} -p ${OBJECTDIR}/thrift/gen-cpp
	${RM} "$@.d"
	$(COMPILE.cc) -g -DHAVE_CONFIG_H -I../corelibs/FThrift/inc -I../corelibs/FPoco/inc -I../corelibs/FUtility/inc -I../corelibs/FUtility/thrift -Iinc -Ithrift/gen-cpp -I../corelibs/FBoost/inc -I../corelibs/FEvent/inc -I../corelibs/FOpenSSL/inc -I../corelibs/FZ/inc -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/thrift/gen-cpp/vrvaccinationmanagerservice_types.o thrift/gen-cpp/vrvaccinationmanagerservice_types.cpp

# Subprojects
.build-subprojects:

# Clean Targets
.clean-conf: ${CLEAN_SUBPROJECTS}
	${RM} -r ${CND_BUILDDIR}/${CND_CONF}
	${RM} bin/vrallclientd

# Subprojects
.clean-subprojects:

# Enable dependency checking
.dep.inc: .depcheck-impl

include .dep.inc
