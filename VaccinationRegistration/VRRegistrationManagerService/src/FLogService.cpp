/* 
 * File:   FLogService.cpp
 * Author: longmd
 * 
 * Created on June 5, 2015, 10:16 AM
 */

#include "FLogService.h"

using namespace std;

FLogService* FLogService::_instance = NULL;

FLogService* FLogService::instance(void) {
	if (!_instance) {
		_instance = new FLogService();
	}
	return _instance;
}

void FLogService::printExceptionLog(const std::string& header, const Poco::Exception& ex){
	stringstream ss;
	ss << header << " : " << ex.displayText() << endl;
	printLog(Configuration::_FLogSystemLogCategory, ss.str());
}

void FLogService::printConnectionRefusedLog(const std::string& path){
	stringstream ss;
	ss << path << " : Connection REFUSED" << endl;
	printLog(Configuration::_FLogSystemLogCategory, ss.str());
}

void FLogService::printLog(const std::string& category, const std::string& message){
	FLogServiceClientT client = Configuration::getFLogServiceClient();
	if (client->sureOpen()) {
		try {
			client->printLog(category, message);
		}
		catch (Poco::Exception ex) {
			cout << "[DIPImporterService] FLogService::printLog() : " << ex.displayText() << endl;
		}
	}
	client->close();
}

void FLogService::printLog_ow(const std::string& category, const std::string& message){
	FLogServiceClientT client = Configuration::getFLogServiceClient();
	if (client->sureOpen()) {
		try {
			client->printLog_ow(category, message);
		}
		catch (Poco::Exception ex) {
			cout << "[DIPImporterService] FLogService::printLog_ow() : " << ex.displayText() << endl;
		}
	}
	client->close();
}