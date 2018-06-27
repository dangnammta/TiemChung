/* 
 * File:   Configuration.h
 * Author: longmd
 *
 * Created on April 26, 2012, 1:15 PM
 */

#ifndef CONFIGURATION_H
#define	CONFIGURATION_H

#include <string>
#include "Poco/Util/Application.h"
#include "TClientTypes.h"
#include "FCore/Utility/ServiceConf/FConfKeeper.h"
#include "FCore/Utility/ServiceConf/Properties.h"
#include <iostream>
#include "sstream"
#include "FLogService.h"
#include "Poco/Exception.h"

class Configuration {
public:
	static void initialize(Poco::Util::Application& application, FCore::Utility::ServiceConf::FConfKeeper* pCKServers = NULL);

	static VRRegistrationInfoStoreServiceClientT getVRRegistrationInfoStoreServiceClient(void);
	static VRDailyRegistrationsStoreServiceClientT getVRDailyRegistrationsStoreServiceClient(void);
	static VRDailyInjectionsStoreServiceClientT getVRDailyInjectionsStoreServiceClient(void);
	static VREventInfoStoreServiceClientT getVREventInfoStoreServiceClient(void);
	static FLogServiceClientT getFLogServiceClient(void);

public:
	static FCore::Utility::ServiceConf::FConfKeeper* _pCKServers;

public:
	
	static std::string _VRRegistrationInfoStoreServiceHost;
	static int _VRRegistrationInfoStoreServicePort;
	static std::string _VRRegistrationInfoStoreServiceCKPath;
	static std::string _VRRegistrationInfoStoreServiceProtocol;

	static std::string _VRDailyRegistrationsStoreServiceHost;
	static int _VRDailyRegistrationsStoreServicePort;
	static std::string _VRDailyRegistrationsStoreServiceCKPath;
	static std::string _VRDailyRegistrationsStoreServiceProtocol;

	static std::string _VRDailyInjectionsStoreServiceHost;
	static int _VRDailyInjectionsStoreServicePort;
	static std::string _VRDailyInjectionsStoreServiceCKPath;
	static std::string _VRDailyInjectionsStoreServiceProtocol;
	
	static std::string _VREventInfoStoreServiceHost;
	static int _VREventInfoStoreServicePort;
	static std::string _VREventInfoStoreServiceCKPath;
	static std::string _VREventInfoStoreServiceProtocol;

	static std::string _FLogServiceHost;
	static int _FLogServicePort;
	static std::string _FLogServiceCKPath;
	static std::string _FLogServiceProtocol;
	static std::string _FLogSystemLogCategory;
	static bool _FLogEnable;
	
	static int _lengthRegistrationCode;

private:
	
	static const std::string _configKeyVRRegistrationInfoStoreServiceHost;
	static const std::string _configKeyVRRegistrationInfoStoreServicePort;
	static const std::string _configKeyVRRegistrationInfoStoreServiceCKPath;
	static const std::string _configKeyVRRegistrationInfoStoreServiceProtocol;

	static const std::string _configKeyVRDailyRegistrationsStoreServiceHost;
	static const std::string _configKeyVRDailyRegistrationsStoreServicePort;
	static const std::string _configKeyVRDailyRegistrationsStoreServiceCKPath;
	static const std::string _configKeyVRDailyRegistrationsStoreServiceProtocol;

	static const std::string _configKeyVRDailyInjectionsStoreServiceHost;
	static const std::string _configKeyVRDailyInjectionsStoreServicePort;
	static const std::string _configKeyVRDailyInjectionsStoreServiceCKPath;
	static const std::string _configKeyVRDailyInjectionsStoreServiceProtocol;
	
	static const std::string _configKeyVREventInfoStoreServiceHost;
	static const std::string _configKeyVREventInfoStoreServicePort;
	static const std::string _configKeyVREventInfoStoreServiceCKPath;
	static const std::string _configKeyVREventInfoStoreServiceProtocol;

	static const std::string _configKeyFLogServiceHost;
	static const std::string _configKeyFLogServicePort;
	static const std::string _configKeyFLogServiceCKPath;
	static const std::string _configKeyFLogServiceProtocol;
	static const std::string _configKeyFLogSystemLogCategory;
	static const std::string _configKeyFLogEnable;
	
	static const std::string _configKeyLengthRegistrationCode;
};

#endif	/* CONFIGURATION_H */

