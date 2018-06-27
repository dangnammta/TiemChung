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

	static VRCentreInfoStoreServiceClientT getVRCentreInfoStoreServiceClient(void);
	static VRVaccineInfoStoreServiceClientT getVRVaccineInfoStoreServiceClient(void);
	static VRCommonStoreServiceClientT getVRCommonStoreServiceClient(void);
	static FLogServiceClientT getFLogServiceClient(void);

public:
	static FCore::Utility::ServiceConf::FConfKeeper* _pCKServers;

public:
	
	static std::string _VRCentreInfoStoreServiceHost;
	static int _VRCentreInfoStoreServicePort;
	static std::string _VRCentreInfoStoreServiceCKPath;
	static std::string _VRCentreInfoStoreServiceProtocol;
	
	static std::string _VRVaccineInfoStoreServiceHost;
	static int _VRVaccineInfoStoreServicePort;
	static std::string _VRVaccineInfoStoreServiceCKPath;
	static std::string _VRVaccineInfoStoreServiceProtocol;

	static std::string _VRCommonStoreServiceHost;
	static int _VRCommonStoreServicePort;
	static std::string _VRCommonStoreServiceCKPath;
	static std::string _VRCommonStoreServiceProtocol;
	static std::string _VRCommonKeyListCentres;

	static std::string _FLogServiceHost;
	static int _FLogServicePort;
	static std::string _FLogServiceCKPath;
	static std::string _FLogServiceProtocol;
	static std::string _FLogSystemLogCategory;
	static bool _FLogEnable;
	
	

private:
	
	static const std::string _configKeyVRCentreInfoStoreServiceHost;
	static const std::string _configKeyVRCentreInfoStoreServicePort;
	static const std::string _configKeyVRCentreInfoStoreServiceCKPath;
	static const std::string _configKeyVRCentreInfoStoreServiceProtocol;
	
	static const std::string _configKeyVRVaccineInfoStoreServiceHost;
	static const std::string _configKeyVRVaccineInfoStoreServicePort;
	static const std::string _configKeyVRVaccineInfoStoreServiceCKPath;
	static const std::string _configKeyVRVaccineInfoStoreServiceProtocol;

	static const std::string _configKeyVRCommonStoreServiceHost;
	static const std::string _configKeyVRCommonStoreServicePort;
	static const std::string _configKeyVRCommonStoreServiceCKPath;
	static const std::string _configKeyVRCommonStoreServiceProtocol;
	static const std::string _configKeyVRCommonKeyListCentres;

	static const std::string _configKeyFLogServiceHost;
	static const std::string _configKeyFLogServicePort;
	static const std::string _configKeyFLogServiceCKPath;
	static const std::string _configKeyFLogServiceProtocol;
	static const std::string _configKeyFLogSystemLogCategory;
	static const std::string _configKeyFLogEnable;
};

#endif	/* CONFIGURATION_H */

