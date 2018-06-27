/* 
 * File:   VRCommonStoreService.h
 * Author: longmd
 *
 * Created on January 20, 2016, 12:08 PM
 */

#ifndef VRCOMMONSTORESERVICE_H
#define	VRCOMMONSTORESERVICE_H

#include "stringtoi32set_shared_types.h"

class VRCommonStoreService {
public:
	VRCommonStoreService(void);
	virtual ~VRCommonStoreService(void);

	static VRCommonStoreService* instance(void);
	
	bool addItem(const CRDHN::Common::Data::StringToI32Set::TKey& key, const CRDHN::Common::Data::StringToI32Set::TItem item);
	bool removeItem(const CRDHN::Common::Data::StringToI32Set::TKey& key, const CRDHN::Common::Data::StringToI32Set::TItem item);
	bool getItems(std::vector<CRDHN::Common::Data::StringToI32Set::TItem>& response, const CRDHN::Common::Data::StringToI32Set::TKey& key);

private:
	static VRCommonStoreService* _instance;
};

#endif	/* VRCOMMONSTORESERVICE_H */

