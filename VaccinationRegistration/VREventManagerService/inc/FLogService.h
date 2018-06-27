/* 
 * File:   FLogService.h
 * Author: longmd
 *
 * Created on June 5, 2015, 10:16 AM
 */

#ifndef FLOGSERVICE_H
#define	FLOGSERVICE_H

#include "Configuration.h"

class FLogService {
public:
	static FLogService* instance(void);
	
	void printExceptionLog(const std::string& header, const Poco::Exception& ex);
	void printConnectionRefusedLog(const std::string& path);
	
	void printLog(const std::string& category, const std::string& message);
	void printLog_ow(const std::string& category, const std::string& message);
private:
	static FLogService* _instance;
};

#endif	/* FLOGSERVICE_H */

