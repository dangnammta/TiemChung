/* 
 * File:   Configuration.cpp
 * Author: longmd
 * Created on March 6, 2013, 10:15 AM
 */

#include "Configuration.h"

using namespace std;

int Configuration::_VRRegistrationManagerServicePort = 0;
string Configuration::_VRRegistrationManagerServiceHost = "10.30.22.132";

int Configuration::_VREventManagerServicePort = 0;
string Configuration::_VREventManagerServiceHost = "10.30.22.132";

int Configuration::_VRCentreInfoStoreServicePort = 0;
string Configuration::_VRCentreInfoStoreServiceHost = "10.30.22.132";

int Configuration::_VRCentreManagerServicePort = 0;
string Configuration::_VRCentreManagerServiceHost = "10.30.22.132";

const string Configuration::_configVRRegistrationManagerServicePort = "registrationmanager.port";
const string Configuration::_configVRRegistrationManagerServiceHost = "registrationmanager.host";

const string Configuration::_configVREventManagerServicePort = "eventmanager.port";
const string Configuration::_configVREventManagerServiceHost = "eventmanager.host";

const string Configuration::_configVRCentreInfoStoreServicePort = "centreinfo.port";
const string Configuration::_configVRCentreInfoStoreServiceHost = "centreinfo.host";

const string Configuration::_configVRCentreManagerServicePort = "centremanager.port";
const string Configuration::_configVRCentreManagerServiceHost = "centremanager.host";

VRRegistrationManagerServiceClientT Configuration::getVRRegistrationManagerServiceClient(void){
    return VRRegistrationManagerServiceClientT(_VRRegistrationManagerServiceHost,_VRRegistrationManagerServicePort, true);
}

VREventManagerServiceClientT Configuration::getVREventManagerServiceClient(void){
    return VREventManagerServiceClientT(_VREventManagerServiceHost,_VREventManagerServicePort, true);
}

//VRCentreInfoStoreServiceClientT Configuration::getVRCentreInfoStoreServiceClient(void){
//    return VRCentreInfoStoreServiceClientT(_VRCentreInfoStoreServiceHost,_VRCentreInfoStoreServicePort, true);
//}

VRCentreManagerServiceClientT Configuration::getVRCentreManagerServiceClientT(void){
    return VRCentreManagerServiceClientT(_VRCentreManagerServiceHost,_VRCentreManagerServicePort, true);
}