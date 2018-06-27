/* 
 * File:   Configuration.h
 * Author: LongMD
 *
 * Created on December 9, 2014, 1:15 PM
 */

#ifndef CONFIGURATION_H
#define	CONFIGURATION_H

#include <string>
#include "Poco/Util/Application.h"
#include "TClientTypes.h"
#include "FCore/Utility/ServiceConf/FConfKeeper.h"

class Configuration {
public:
	static void initialize(Poco::Util::Application& application, FCore::Utility::ServiceConf::FConfKeeper* pCKServers = NULL);

	static StepCounterServiceClientT getStepCounterServiceClient(void);

public:
	static FCore::Utility::ServiceConf::FConfKeeper* _pCKServers;

public:
	static std::string _StepCounterServiceHost;
	static int _StepCounterServicePort;
	static std::string _StepCounterServiceVRVaccineIdCntName;
	static std::string _StepCounterServiceCKPath;
	static std::string _StepCounterServiceProtocol;

private:
	static const std::string _configKeyStepCounterServiceHost;
	static const std::string _configKeyStepCounterServicePort;
	static const std::string _configKeyStepCounterServiceVRVaccineIdCntName;
	static const std::string _configKeyStepCounterServiceCKPath;
	static const std::string _configKeyStepCounterServiceProtocol;
};

#endif	/* CONFIGURATION_H */
