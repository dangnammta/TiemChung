/* 
 * File:   Configuration.h
 * Author: longmd
 *
 * Created on March 6, 2013, 10:15 AM
 */

#ifndef CONFIGURATION_H
#define	CONFIGURATION_H

#include <string>
#include "TClientTypes.h"
#include "ServerApp.h"

class Configuration {
public:                    
	static VRRegistrationManagerServiceClientT getVRRegistrationManagerServiceClient(void);
	static VREventManagerServiceClientT getVREventManagerServiceClient(void);
//	static VRCentreInfoStoreServiceClientT getVRCentreInfoStoreServiceClient(void);
        static VRCentreManagerServiceClientT getVRCentreManagerServiceClientT(void);
                
public:
	static int _VRRegistrationManagerServicePort;
	static std::string _VRRegistrationManagerServiceHost;
	
	static int _VREventManagerServicePort;
	static std::string _VREventManagerServiceHost;
	
	static int _VRCentreInfoStoreServicePort;
	static std::string _VRCentreInfoStoreServiceHost;
        
        static int _VRCentreManagerServicePort;
	static std::string _VRCentreManagerServiceHost;

	static const std::string _configVRRegistrationManagerServicePort;
	static const std::string _configVRRegistrationManagerServiceHost;
	
	static const std::string _configVREventManagerServicePort;
	static const std::string _configVREventManagerServiceHost;
	
	static const std::string _configVRCentreInfoStoreServicePort;
	static const std::string _configVRCentreInfoStoreServiceHost;
        
        static const std::string _configVRCentreManagerServicePort;
	static const std::string _configVRCentreManagerServiceHost;
};

#endif	/* CONFIGURATION_H */

