/* 
 * File:   Configuration.cpp
 * Author: LongMD
 *
 * Created on December 9, 2014, 1:18 PM
 */

#include "Configuration.h"
#include "fconfkeeper_types.h"

using namespace std;

FCore::Utility::ServiceConf::FConfKeeper* Configuration::_pCKServers = NULL;

void Configuration::initialize(Poco::Util::Application& application, FCore::Utility::ServiceConf::FConfKeeper* pCKServers) {
	_pCKServers = pCKServers;
}