/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
#ifndef TVRCentreInfoStoreService_H
#define TVRCentreInfoStoreService_H

#include <thrift/TDispatchProcessor.h>
#include "vrcentreinfostoreservice_types.h"

namespace CRDHN { namespace VR {

class TVRCentreInfoStoreServiceIf {
 public:
  virtual ~TVRCentreInfoStoreServiceIf() {}
  virtual  ::CRDHN::VR::Data::TCID add(const  ::CRDHN::VR::Data::TCentreInfo& centreInfo) = 0;
  virtual void get( ::CRDHN::VR::Data::TCentreInfo& _return, const  ::CRDHN::VR::Data::TCID centreId) = 0;
  virtual bool update(const  ::CRDHN::VR::Data::TCID centreId, const  ::CRDHN::VR::Data::TCentreInfo& centreInfo) = 0;
  virtual bool remove(const  ::CRDHN::VR::Data::TCID centreId) = 0;
  virtual bool addEvent(const  ::CRDHN::VR::Data::TCID centreId, const  ::CRDHN::VR::Data::TEID eventId) = 0;
  virtual bool removeEvent(const  ::CRDHN::VR::Data::TCID centreId, const  ::CRDHN::VR::Data::TEID eventId) = 0;
  virtual bool addVaccine(const  ::CRDHN::VR::Data::TCID centreId, const  ::CRDHN::VR::Data::TVID vaccineId) = 0;
  virtual bool removeVaccine(const  ::CRDHN::VR::Data::TCID centreId, const  ::CRDHN::VR::Data::TVID vaccineId) = 0;
};

class TVRCentreInfoStoreServiceIfFactory {
 public:
  typedef TVRCentreInfoStoreServiceIf Handler;

  virtual ~TVRCentreInfoStoreServiceIfFactory() {}

  virtual TVRCentreInfoStoreServiceIf* getHandler(const ::apache::thrift::TConnectionInfo& connInfo) = 0;
  virtual void releaseHandler(TVRCentreInfoStoreServiceIf* /* handler */) = 0;
};

class TVRCentreInfoStoreServiceIfSingletonFactory : virtual public TVRCentreInfoStoreServiceIfFactory {
 public:
  TVRCentreInfoStoreServiceIfSingletonFactory(const boost::shared_ptr<TVRCentreInfoStoreServiceIf>& iface) : iface_(iface) {}
  virtual ~TVRCentreInfoStoreServiceIfSingletonFactory() {}

  virtual TVRCentreInfoStoreServiceIf* getHandler(const ::apache::thrift::TConnectionInfo&) {
    return iface_.get();
  }
  virtual void releaseHandler(TVRCentreInfoStoreServiceIf* /* handler */) {}

 protected:
  boost::shared_ptr<TVRCentreInfoStoreServiceIf> iface_;
};

class TVRCentreInfoStoreServiceNull : virtual public TVRCentreInfoStoreServiceIf {
 public:
  virtual ~TVRCentreInfoStoreServiceNull() {}
   ::CRDHN::VR::Data::TCID add(const  ::CRDHN::VR::Data::TCentreInfo& /* centreInfo */) {
     ::CRDHN::VR::Data::TCID _return = 0;
    return _return;
  }
  void get( ::CRDHN::VR::Data::TCentreInfo& /* _return */, const  ::CRDHN::VR::Data::TCID /* centreId */) {
    return;
  }
  bool update(const  ::CRDHN::VR::Data::TCID /* centreId */, const  ::CRDHN::VR::Data::TCentreInfo& /* centreInfo */) {
    bool _return = false;
    return _return;
  }
  bool remove(const  ::CRDHN::VR::Data::TCID /* centreId */) {
    bool _return = false;
    return _return;
  }
  bool addEvent(const  ::CRDHN::VR::Data::TCID /* centreId */, const  ::CRDHN::VR::Data::TEID /* eventId */) {
    bool _return = false;
    return _return;
  }
  bool removeEvent(const  ::CRDHN::VR::Data::TCID /* centreId */, const  ::CRDHN::VR::Data::TEID /* eventId */) {
    bool _return = false;
    return _return;
  }
  bool addVaccine(const  ::CRDHN::VR::Data::TCID /* centreId */, const  ::CRDHN::VR::Data::TVID /* vaccineId */) {
    bool _return = false;
    return _return;
  }
  bool removeVaccine(const  ::CRDHN::VR::Data::TCID /* centreId */, const  ::CRDHN::VR::Data::TVID /* vaccineId */) {
    bool _return = false;
    return _return;
  }
};

typedef struct _TVRCentreInfoStoreService_add_args__isset {
  _TVRCentreInfoStoreService_add_args__isset() : centreInfo(false) {}
  bool centreInfo;
} _TVRCentreInfoStoreService_add_args__isset;

class TVRCentreInfoStoreService_add_args {
 public:

  TVRCentreInfoStoreService_add_args() {
  }

  virtual ~TVRCentreInfoStoreService_add_args() throw() {}

   ::CRDHN::VR::Data::TCentreInfo centreInfo;

  _TVRCentreInfoStoreService_add_args__isset __isset;

  void __set_centreInfo(const  ::CRDHN::VR::Data::TCentreInfo& val) {
    centreInfo = val;
  }

  bool operator == (const TVRCentreInfoStoreService_add_args & rhs) const
  {
    if (!(centreInfo == rhs.centreInfo))
      return false;
    return true;
  }
  bool operator != (const TVRCentreInfoStoreService_add_args &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const TVRCentreInfoStoreService_add_args & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};


class TVRCentreInfoStoreService_add_pargs {
 public:


  virtual ~TVRCentreInfoStoreService_add_pargs() throw() {}

  const  ::CRDHN::VR::Data::TCentreInfo* centreInfo;

  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

typedef struct _TVRCentreInfoStoreService_add_result__isset {
  _TVRCentreInfoStoreService_add_result__isset() : success(false) {}
  bool success;
} _TVRCentreInfoStoreService_add_result__isset;

class TVRCentreInfoStoreService_add_result {
 public:

  TVRCentreInfoStoreService_add_result() : success(0) {
  }

  virtual ~TVRCentreInfoStoreService_add_result() throw() {}

   ::CRDHN::VR::Data::TCID success;

  _TVRCentreInfoStoreService_add_result__isset __isset;

  void __set_success(const  ::CRDHN::VR::Data::TCID val) {
    success = val;
  }

  bool operator == (const TVRCentreInfoStoreService_add_result & rhs) const
  {
    if (!(success == rhs.success))
      return false;
    return true;
  }
  bool operator != (const TVRCentreInfoStoreService_add_result &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const TVRCentreInfoStoreService_add_result & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

typedef struct _TVRCentreInfoStoreService_add_presult__isset {
  _TVRCentreInfoStoreService_add_presult__isset() : success(false) {}
  bool success;
} _TVRCentreInfoStoreService_add_presult__isset;

class TVRCentreInfoStoreService_add_presult {
 public:


  virtual ~TVRCentreInfoStoreService_add_presult() throw() {}

   ::CRDHN::VR::Data::TCID* success;

  _TVRCentreInfoStoreService_add_presult__isset __isset;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);

};

typedef struct _TVRCentreInfoStoreService_get_args__isset {
  _TVRCentreInfoStoreService_get_args__isset() : centreId(false) {}
  bool centreId;
} _TVRCentreInfoStoreService_get_args__isset;

class TVRCentreInfoStoreService_get_args {
 public:

  TVRCentreInfoStoreService_get_args() : centreId(0) {
  }

  virtual ~TVRCentreInfoStoreService_get_args() throw() {}

   ::CRDHN::VR::Data::TCID centreId;

  _TVRCentreInfoStoreService_get_args__isset __isset;

  void __set_centreId(const  ::CRDHN::VR::Data::TCID val) {
    centreId = val;
  }

  bool operator == (const TVRCentreInfoStoreService_get_args & rhs) const
  {
    if (!(centreId == rhs.centreId))
      return false;
    return true;
  }
  bool operator != (const TVRCentreInfoStoreService_get_args &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const TVRCentreInfoStoreService_get_args & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};


class TVRCentreInfoStoreService_get_pargs {
 public:


  virtual ~TVRCentreInfoStoreService_get_pargs() throw() {}

  const  ::CRDHN::VR::Data::TCID* centreId;

  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

typedef struct _TVRCentreInfoStoreService_get_result__isset {
  _TVRCentreInfoStoreService_get_result__isset() : success(false) {}
  bool success;
} _TVRCentreInfoStoreService_get_result__isset;

class TVRCentreInfoStoreService_get_result {
 public:

  TVRCentreInfoStoreService_get_result() {
  }

  virtual ~TVRCentreInfoStoreService_get_result() throw() {}

   ::CRDHN::VR::Data::TCentreInfo success;

  _TVRCentreInfoStoreService_get_result__isset __isset;

  void __set_success(const  ::CRDHN::VR::Data::TCentreInfo& val) {
    success = val;
  }

  bool operator == (const TVRCentreInfoStoreService_get_result & rhs) const
  {
    if (!(success == rhs.success))
      return false;
    return true;
  }
  bool operator != (const TVRCentreInfoStoreService_get_result &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const TVRCentreInfoStoreService_get_result & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

typedef struct _TVRCentreInfoStoreService_get_presult__isset {
  _TVRCentreInfoStoreService_get_presult__isset() : success(false) {}
  bool success;
} _TVRCentreInfoStoreService_get_presult__isset;

class TVRCentreInfoStoreService_get_presult {
 public:


  virtual ~TVRCentreInfoStoreService_get_presult() throw() {}

   ::CRDHN::VR::Data::TCentreInfo* success;

  _TVRCentreInfoStoreService_get_presult__isset __isset;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);

};

typedef struct _TVRCentreInfoStoreService_update_args__isset {
  _TVRCentreInfoStoreService_update_args__isset() : centreId(false), centreInfo(false) {}
  bool centreId;
  bool centreInfo;
} _TVRCentreInfoStoreService_update_args__isset;

class TVRCentreInfoStoreService_update_args {
 public:

  TVRCentreInfoStoreService_update_args() : centreId(0) {
  }

  virtual ~TVRCentreInfoStoreService_update_args() throw() {}

   ::CRDHN::VR::Data::TCID centreId;
   ::CRDHN::VR::Data::TCentreInfo centreInfo;

  _TVRCentreInfoStoreService_update_args__isset __isset;

  void __set_centreId(const  ::CRDHN::VR::Data::TCID val) {
    centreId = val;
  }

  void __set_centreInfo(const  ::CRDHN::VR::Data::TCentreInfo& val) {
    centreInfo = val;
  }

  bool operator == (const TVRCentreInfoStoreService_update_args & rhs) const
  {
    if (!(centreId == rhs.centreId))
      return false;
    if (!(centreInfo == rhs.centreInfo))
      return false;
    return true;
  }
  bool operator != (const TVRCentreInfoStoreService_update_args &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const TVRCentreInfoStoreService_update_args & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};


class TVRCentreInfoStoreService_update_pargs {
 public:


  virtual ~TVRCentreInfoStoreService_update_pargs() throw() {}

  const  ::CRDHN::VR::Data::TCID* centreId;
  const  ::CRDHN::VR::Data::TCentreInfo* centreInfo;

  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

typedef struct _TVRCentreInfoStoreService_update_result__isset {
  _TVRCentreInfoStoreService_update_result__isset() : success(false) {}
  bool success;
} _TVRCentreInfoStoreService_update_result__isset;

class TVRCentreInfoStoreService_update_result {
 public:

  TVRCentreInfoStoreService_update_result() : success(0) {
  }

  virtual ~TVRCentreInfoStoreService_update_result() throw() {}

  bool success;

  _TVRCentreInfoStoreService_update_result__isset __isset;

  void __set_success(const bool val) {
    success = val;
  }

  bool operator == (const TVRCentreInfoStoreService_update_result & rhs) const
  {
    if (!(success == rhs.success))
      return false;
    return true;
  }
  bool operator != (const TVRCentreInfoStoreService_update_result &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const TVRCentreInfoStoreService_update_result & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

typedef struct _TVRCentreInfoStoreService_update_presult__isset {
  _TVRCentreInfoStoreService_update_presult__isset() : success(false) {}
  bool success;
} _TVRCentreInfoStoreService_update_presult__isset;

class TVRCentreInfoStoreService_update_presult {
 public:


  virtual ~TVRCentreInfoStoreService_update_presult() throw() {}

  bool* success;

  _TVRCentreInfoStoreService_update_presult__isset __isset;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);

};

typedef struct _TVRCentreInfoStoreService_remove_args__isset {
  _TVRCentreInfoStoreService_remove_args__isset() : centreId(false) {}
  bool centreId;
} _TVRCentreInfoStoreService_remove_args__isset;

class TVRCentreInfoStoreService_remove_args {
 public:

  TVRCentreInfoStoreService_remove_args() : centreId(0) {
  }

  virtual ~TVRCentreInfoStoreService_remove_args() throw() {}

   ::CRDHN::VR::Data::TCID centreId;

  _TVRCentreInfoStoreService_remove_args__isset __isset;

  void __set_centreId(const  ::CRDHN::VR::Data::TCID val) {
    centreId = val;
  }

  bool operator == (const TVRCentreInfoStoreService_remove_args & rhs) const
  {
    if (!(centreId == rhs.centreId))
      return false;
    return true;
  }
  bool operator != (const TVRCentreInfoStoreService_remove_args &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const TVRCentreInfoStoreService_remove_args & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};


class TVRCentreInfoStoreService_remove_pargs {
 public:


  virtual ~TVRCentreInfoStoreService_remove_pargs() throw() {}

  const  ::CRDHN::VR::Data::TCID* centreId;

  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

typedef struct _TVRCentreInfoStoreService_remove_result__isset {
  _TVRCentreInfoStoreService_remove_result__isset() : success(false) {}
  bool success;
} _TVRCentreInfoStoreService_remove_result__isset;

class TVRCentreInfoStoreService_remove_result {
 public:

  TVRCentreInfoStoreService_remove_result() : success(0) {
  }

  virtual ~TVRCentreInfoStoreService_remove_result() throw() {}

  bool success;

  _TVRCentreInfoStoreService_remove_result__isset __isset;

  void __set_success(const bool val) {
    success = val;
  }

  bool operator == (const TVRCentreInfoStoreService_remove_result & rhs) const
  {
    if (!(success == rhs.success))
      return false;
    return true;
  }
  bool operator != (const TVRCentreInfoStoreService_remove_result &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const TVRCentreInfoStoreService_remove_result & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

typedef struct _TVRCentreInfoStoreService_remove_presult__isset {
  _TVRCentreInfoStoreService_remove_presult__isset() : success(false) {}
  bool success;
} _TVRCentreInfoStoreService_remove_presult__isset;

class TVRCentreInfoStoreService_remove_presult {
 public:


  virtual ~TVRCentreInfoStoreService_remove_presult() throw() {}

  bool* success;

  _TVRCentreInfoStoreService_remove_presult__isset __isset;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);

};

typedef struct _TVRCentreInfoStoreService_addEvent_args__isset {
  _TVRCentreInfoStoreService_addEvent_args__isset() : centreId(false), eventId(false) {}
  bool centreId;
  bool eventId;
} _TVRCentreInfoStoreService_addEvent_args__isset;

class TVRCentreInfoStoreService_addEvent_args {
 public:

  TVRCentreInfoStoreService_addEvent_args() : centreId(0), eventId(0) {
  }

  virtual ~TVRCentreInfoStoreService_addEvent_args() throw() {}

   ::CRDHN::VR::Data::TCID centreId;
   ::CRDHN::VR::Data::TEID eventId;

  _TVRCentreInfoStoreService_addEvent_args__isset __isset;

  void __set_centreId(const  ::CRDHN::VR::Data::TCID val) {
    centreId = val;
  }

  void __set_eventId(const  ::CRDHN::VR::Data::TEID val) {
    eventId = val;
  }

  bool operator == (const TVRCentreInfoStoreService_addEvent_args & rhs) const
  {
    if (!(centreId == rhs.centreId))
      return false;
    if (!(eventId == rhs.eventId))
      return false;
    return true;
  }
  bool operator != (const TVRCentreInfoStoreService_addEvent_args &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const TVRCentreInfoStoreService_addEvent_args & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};


class TVRCentreInfoStoreService_addEvent_pargs {
 public:


  virtual ~TVRCentreInfoStoreService_addEvent_pargs() throw() {}

  const  ::CRDHN::VR::Data::TCID* centreId;
  const  ::CRDHN::VR::Data::TEID* eventId;

  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

typedef struct _TVRCentreInfoStoreService_addEvent_result__isset {
  _TVRCentreInfoStoreService_addEvent_result__isset() : success(false) {}
  bool success;
} _TVRCentreInfoStoreService_addEvent_result__isset;

class TVRCentreInfoStoreService_addEvent_result {
 public:

  TVRCentreInfoStoreService_addEvent_result() : success(0) {
  }

  virtual ~TVRCentreInfoStoreService_addEvent_result() throw() {}

  bool success;

  _TVRCentreInfoStoreService_addEvent_result__isset __isset;

  void __set_success(const bool val) {
    success = val;
  }

  bool operator == (const TVRCentreInfoStoreService_addEvent_result & rhs) const
  {
    if (!(success == rhs.success))
      return false;
    return true;
  }
  bool operator != (const TVRCentreInfoStoreService_addEvent_result &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const TVRCentreInfoStoreService_addEvent_result & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

typedef struct _TVRCentreInfoStoreService_addEvent_presult__isset {
  _TVRCentreInfoStoreService_addEvent_presult__isset() : success(false) {}
  bool success;
} _TVRCentreInfoStoreService_addEvent_presult__isset;

class TVRCentreInfoStoreService_addEvent_presult {
 public:


  virtual ~TVRCentreInfoStoreService_addEvent_presult() throw() {}

  bool* success;

  _TVRCentreInfoStoreService_addEvent_presult__isset __isset;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);

};

typedef struct _TVRCentreInfoStoreService_removeEvent_args__isset {
  _TVRCentreInfoStoreService_removeEvent_args__isset() : centreId(false), eventId(false) {}
  bool centreId;
  bool eventId;
} _TVRCentreInfoStoreService_removeEvent_args__isset;

class TVRCentreInfoStoreService_removeEvent_args {
 public:

  TVRCentreInfoStoreService_removeEvent_args() : centreId(0), eventId(0) {
  }

  virtual ~TVRCentreInfoStoreService_removeEvent_args() throw() {}

   ::CRDHN::VR::Data::TCID centreId;
   ::CRDHN::VR::Data::TEID eventId;

  _TVRCentreInfoStoreService_removeEvent_args__isset __isset;

  void __set_centreId(const  ::CRDHN::VR::Data::TCID val) {
    centreId = val;
  }

  void __set_eventId(const  ::CRDHN::VR::Data::TEID val) {
    eventId = val;
  }

  bool operator == (const TVRCentreInfoStoreService_removeEvent_args & rhs) const
  {
    if (!(centreId == rhs.centreId))
      return false;
    if (!(eventId == rhs.eventId))
      return false;
    return true;
  }
  bool operator != (const TVRCentreInfoStoreService_removeEvent_args &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const TVRCentreInfoStoreService_removeEvent_args & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};


class TVRCentreInfoStoreService_removeEvent_pargs {
 public:


  virtual ~TVRCentreInfoStoreService_removeEvent_pargs() throw() {}

  const  ::CRDHN::VR::Data::TCID* centreId;
  const  ::CRDHN::VR::Data::TEID* eventId;

  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

typedef struct _TVRCentreInfoStoreService_removeEvent_result__isset {
  _TVRCentreInfoStoreService_removeEvent_result__isset() : success(false) {}
  bool success;
} _TVRCentreInfoStoreService_removeEvent_result__isset;

class TVRCentreInfoStoreService_removeEvent_result {
 public:

  TVRCentreInfoStoreService_removeEvent_result() : success(0) {
  }

  virtual ~TVRCentreInfoStoreService_removeEvent_result() throw() {}

  bool success;

  _TVRCentreInfoStoreService_removeEvent_result__isset __isset;

  void __set_success(const bool val) {
    success = val;
  }

  bool operator == (const TVRCentreInfoStoreService_removeEvent_result & rhs) const
  {
    if (!(success == rhs.success))
      return false;
    return true;
  }
  bool operator != (const TVRCentreInfoStoreService_removeEvent_result &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const TVRCentreInfoStoreService_removeEvent_result & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

typedef struct _TVRCentreInfoStoreService_removeEvent_presult__isset {
  _TVRCentreInfoStoreService_removeEvent_presult__isset() : success(false) {}
  bool success;
} _TVRCentreInfoStoreService_removeEvent_presult__isset;

class TVRCentreInfoStoreService_removeEvent_presult {
 public:


  virtual ~TVRCentreInfoStoreService_removeEvent_presult() throw() {}

  bool* success;

  _TVRCentreInfoStoreService_removeEvent_presult__isset __isset;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);

};

typedef struct _TVRCentreInfoStoreService_addVaccine_args__isset {
  _TVRCentreInfoStoreService_addVaccine_args__isset() : centreId(false), vaccineId(false) {}
  bool centreId;
  bool vaccineId;
} _TVRCentreInfoStoreService_addVaccine_args__isset;

class TVRCentreInfoStoreService_addVaccine_args {
 public:

  TVRCentreInfoStoreService_addVaccine_args() : centreId(0), vaccineId(0) {
  }

  virtual ~TVRCentreInfoStoreService_addVaccine_args() throw() {}

   ::CRDHN::VR::Data::TCID centreId;
   ::CRDHN::VR::Data::TVID vaccineId;

  _TVRCentreInfoStoreService_addVaccine_args__isset __isset;

  void __set_centreId(const  ::CRDHN::VR::Data::TCID val) {
    centreId = val;
  }

  void __set_vaccineId(const  ::CRDHN::VR::Data::TVID val) {
    vaccineId = val;
  }

  bool operator == (const TVRCentreInfoStoreService_addVaccine_args & rhs) const
  {
    if (!(centreId == rhs.centreId))
      return false;
    if (!(vaccineId == rhs.vaccineId))
      return false;
    return true;
  }
  bool operator != (const TVRCentreInfoStoreService_addVaccine_args &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const TVRCentreInfoStoreService_addVaccine_args & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};


class TVRCentreInfoStoreService_addVaccine_pargs {
 public:


  virtual ~TVRCentreInfoStoreService_addVaccine_pargs() throw() {}

  const  ::CRDHN::VR::Data::TCID* centreId;
  const  ::CRDHN::VR::Data::TVID* vaccineId;

  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

typedef struct _TVRCentreInfoStoreService_addVaccine_result__isset {
  _TVRCentreInfoStoreService_addVaccine_result__isset() : success(false) {}
  bool success;
} _TVRCentreInfoStoreService_addVaccine_result__isset;

class TVRCentreInfoStoreService_addVaccine_result {
 public:

  TVRCentreInfoStoreService_addVaccine_result() : success(0) {
  }

  virtual ~TVRCentreInfoStoreService_addVaccine_result() throw() {}

  bool success;

  _TVRCentreInfoStoreService_addVaccine_result__isset __isset;

  void __set_success(const bool val) {
    success = val;
  }

  bool operator == (const TVRCentreInfoStoreService_addVaccine_result & rhs) const
  {
    if (!(success == rhs.success))
      return false;
    return true;
  }
  bool operator != (const TVRCentreInfoStoreService_addVaccine_result &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const TVRCentreInfoStoreService_addVaccine_result & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

typedef struct _TVRCentreInfoStoreService_addVaccine_presult__isset {
  _TVRCentreInfoStoreService_addVaccine_presult__isset() : success(false) {}
  bool success;
} _TVRCentreInfoStoreService_addVaccine_presult__isset;

class TVRCentreInfoStoreService_addVaccine_presult {
 public:


  virtual ~TVRCentreInfoStoreService_addVaccine_presult() throw() {}

  bool* success;

  _TVRCentreInfoStoreService_addVaccine_presult__isset __isset;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);

};

typedef struct _TVRCentreInfoStoreService_removeVaccine_args__isset {
  _TVRCentreInfoStoreService_removeVaccine_args__isset() : centreId(false), vaccineId(false) {}
  bool centreId;
  bool vaccineId;
} _TVRCentreInfoStoreService_removeVaccine_args__isset;

class TVRCentreInfoStoreService_removeVaccine_args {
 public:

  TVRCentreInfoStoreService_removeVaccine_args() : centreId(0), vaccineId(0) {
  }

  virtual ~TVRCentreInfoStoreService_removeVaccine_args() throw() {}

   ::CRDHN::VR::Data::TCID centreId;
   ::CRDHN::VR::Data::TVID vaccineId;

  _TVRCentreInfoStoreService_removeVaccine_args__isset __isset;

  void __set_centreId(const  ::CRDHN::VR::Data::TCID val) {
    centreId = val;
  }

  void __set_vaccineId(const  ::CRDHN::VR::Data::TVID val) {
    vaccineId = val;
  }

  bool operator == (const TVRCentreInfoStoreService_removeVaccine_args & rhs) const
  {
    if (!(centreId == rhs.centreId))
      return false;
    if (!(vaccineId == rhs.vaccineId))
      return false;
    return true;
  }
  bool operator != (const TVRCentreInfoStoreService_removeVaccine_args &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const TVRCentreInfoStoreService_removeVaccine_args & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};


class TVRCentreInfoStoreService_removeVaccine_pargs {
 public:


  virtual ~TVRCentreInfoStoreService_removeVaccine_pargs() throw() {}

  const  ::CRDHN::VR::Data::TCID* centreId;
  const  ::CRDHN::VR::Data::TVID* vaccineId;

  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

typedef struct _TVRCentreInfoStoreService_removeVaccine_result__isset {
  _TVRCentreInfoStoreService_removeVaccine_result__isset() : success(false) {}
  bool success;
} _TVRCentreInfoStoreService_removeVaccine_result__isset;

class TVRCentreInfoStoreService_removeVaccine_result {
 public:

  TVRCentreInfoStoreService_removeVaccine_result() : success(0) {
  }

  virtual ~TVRCentreInfoStoreService_removeVaccine_result() throw() {}

  bool success;

  _TVRCentreInfoStoreService_removeVaccine_result__isset __isset;

  void __set_success(const bool val) {
    success = val;
  }

  bool operator == (const TVRCentreInfoStoreService_removeVaccine_result & rhs) const
  {
    if (!(success == rhs.success))
      return false;
    return true;
  }
  bool operator != (const TVRCentreInfoStoreService_removeVaccine_result &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const TVRCentreInfoStoreService_removeVaccine_result & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

typedef struct _TVRCentreInfoStoreService_removeVaccine_presult__isset {
  _TVRCentreInfoStoreService_removeVaccine_presult__isset() : success(false) {}
  bool success;
} _TVRCentreInfoStoreService_removeVaccine_presult__isset;

class TVRCentreInfoStoreService_removeVaccine_presult {
 public:


  virtual ~TVRCentreInfoStoreService_removeVaccine_presult() throw() {}

  bool* success;

  _TVRCentreInfoStoreService_removeVaccine_presult__isset __isset;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);

};

class TVRCentreInfoStoreServiceClient : virtual public TVRCentreInfoStoreServiceIf {
 public:
  TVRCentreInfoStoreServiceClient(boost::shared_ptr< ::apache::thrift::protocol::TProtocol> prot) :
    piprot_(prot),
    poprot_(prot) {
    iprot_ = prot.get();
    oprot_ = prot.get();
  }
  TVRCentreInfoStoreServiceClient(boost::shared_ptr< ::apache::thrift::protocol::TProtocol> iprot, boost::shared_ptr< ::apache::thrift::protocol::TProtocol> oprot) :
    piprot_(iprot),
    poprot_(oprot) {
    iprot_ = iprot.get();
    oprot_ = oprot.get();
  }
  boost::shared_ptr< ::apache::thrift::protocol::TProtocol> getInputProtocol() {
    return piprot_;
  }
  boost::shared_ptr< ::apache::thrift::protocol::TProtocol> getOutputProtocol() {
    return poprot_;
  }
   ::CRDHN::VR::Data::TCID add(const  ::CRDHN::VR::Data::TCentreInfo& centreInfo);
  void send_add(const  ::CRDHN::VR::Data::TCentreInfo& centreInfo);
   ::CRDHN::VR::Data::TCID recv_add();
  void get( ::CRDHN::VR::Data::TCentreInfo& _return, const  ::CRDHN::VR::Data::TCID centreId);
  void send_get(const  ::CRDHN::VR::Data::TCID centreId);
  void recv_get( ::CRDHN::VR::Data::TCentreInfo& _return);
  bool update(const  ::CRDHN::VR::Data::TCID centreId, const  ::CRDHN::VR::Data::TCentreInfo& centreInfo);
  void send_update(const  ::CRDHN::VR::Data::TCID centreId, const  ::CRDHN::VR::Data::TCentreInfo& centreInfo);
  bool recv_update();
  bool remove(const  ::CRDHN::VR::Data::TCID centreId);
  void send_remove(const  ::CRDHN::VR::Data::TCID centreId);
  bool recv_remove();
  bool addEvent(const  ::CRDHN::VR::Data::TCID centreId, const  ::CRDHN::VR::Data::TEID eventId);
  void send_addEvent(const  ::CRDHN::VR::Data::TCID centreId, const  ::CRDHN::VR::Data::TEID eventId);
  bool recv_addEvent();
  bool removeEvent(const  ::CRDHN::VR::Data::TCID centreId, const  ::CRDHN::VR::Data::TEID eventId);
  void send_removeEvent(const  ::CRDHN::VR::Data::TCID centreId, const  ::CRDHN::VR::Data::TEID eventId);
  bool recv_removeEvent();
  bool addVaccine(const  ::CRDHN::VR::Data::TCID centreId, const  ::CRDHN::VR::Data::TVID vaccineId);
  void send_addVaccine(const  ::CRDHN::VR::Data::TCID centreId, const  ::CRDHN::VR::Data::TVID vaccineId);
  bool recv_addVaccine();
  bool removeVaccine(const  ::CRDHN::VR::Data::TCID centreId, const  ::CRDHN::VR::Data::TVID vaccineId);
  void send_removeVaccine(const  ::CRDHN::VR::Data::TCID centreId, const  ::CRDHN::VR::Data::TVID vaccineId);
  bool recv_removeVaccine();
 protected:
  boost::shared_ptr< ::apache::thrift::protocol::TProtocol> piprot_;
  boost::shared_ptr< ::apache::thrift::protocol::TProtocol> poprot_;
  ::apache::thrift::protocol::TProtocol* iprot_;
  ::apache::thrift::protocol::TProtocol* oprot_;
};

class TVRCentreInfoStoreServiceProcessor : public ::apache::thrift::TDispatchProcessor {
 protected:
  boost::shared_ptr<TVRCentreInfoStoreServiceIf> iface_;
  virtual bool dispatchCall(::apache::thrift::protocol::TProtocol* iprot, ::apache::thrift::protocol::TProtocol* oprot, const std::string& fname, int32_t seqid, void* callContext);
 private:
  typedef  void (TVRCentreInfoStoreServiceProcessor::*ProcessFunction)(int32_t, ::apache::thrift::protocol::TProtocol*, ::apache::thrift::protocol::TProtocol*, void*);
  typedef std::map<std::string, ProcessFunction> ProcessMap;
  ProcessMap processMap_;
  void process_add(int32_t seqid, ::apache::thrift::protocol::TProtocol* iprot, ::apache::thrift::protocol::TProtocol* oprot, void* callContext);
  void process_get(int32_t seqid, ::apache::thrift::protocol::TProtocol* iprot, ::apache::thrift::protocol::TProtocol* oprot, void* callContext);
  void process_update(int32_t seqid, ::apache::thrift::protocol::TProtocol* iprot, ::apache::thrift::protocol::TProtocol* oprot, void* callContext);
  void process_remove(int32_t seqid, ::apache::thrift::protocol::TProtocol* iprot, ::apache::thrift::protocol::TProtocol* oprot, void* callContext);
  void process_addEvent(int32_t seqid, ::apache::thrift::protocol::TProtocol* iprot, ::apache::thrift::protocol::TProtocol* oprot, void* callContext);
  void process_removeEvent(int32_t seqid, ::apache::thrift::protocol::TProtocol* iprot, ::apache::thrift::protocol::TProtocol* oprot, void* callContext);
  void process_addVaccine(int32_t seqid, ::apache::thrift::protocol::TProtocol* iprot, ::apache::thrift::protocol::TProtocol* oprot, void* callContext);
  void process_removeVaccine(int32_t seqid, ::apache::thrift::protocol::TProtocol* iprot, ::apache::thrift::protocol::TProtocol* oprot, void* callContext);
 public:
  TVRCentreInfoStoreServiceProcessor(boost::shared_ptr<TVRCentreInfoStoreServiceIf> iface) :
    iface_(iface) {
    processMap_["add"] = &TVRCentreInfoStoreServiceProcessor::process_add;
    processMap_["get"] = &TVRCentreInfoStoreServiceProcessor::process_get;
    processMap_["update"] = &TVRCentreInfoStoreServiceProcessor::process_update;
    processMap_["remove"] = &TVRCentreInfoStoreServiceProcessor::process_remove;
    processMap_["addEvent"] = &TVRCentreInfoStoreServiceProcessor::process_addEvent;
    processMap_["removeEvent"] = &TVRCentreInfoStoreServiceProcessor::process_removeEvent;
    processMap_["addVaccine"] = &TVRCentreInfoStoreServiceProcessor::process_addVaccine;
    processMap_["removeVaccine"] = &TVRCentreInfoStoreServiceProcessor::process_removeVaccine;
  }

  virtual ~TVRCentreInfoStoreServiceProcessor() {}
};

class TVRCentreInfoStoreServiceProcessorFactory : public ::apache::thrift::TProcessorFactory {
 public:
  TVRCentreInfoStoreServiceProcessorFactory(const ::boost::shared_ptr< TVRCentreInfoStoreServiceIfFactory >& handlerFactory) :
      handlerFactory_(handlerFactory) {}

  ::boost::shared_ptr< ::apache::thrift::TProcessor > getProcessor(const ::apache::thrift::TConnectionInfo& connInfo);

 protected:
  ::boost::shared_ptr< TVRCentreInfoStoreServiceIfFactory > handlerFactory_;
};

class TVRCentreInfoStoreServiceMultiface : virtual public TVRCentreInfoStoreServiceIf {
 public:
  TVRCentreInfoStoreServiceMultiface(std::vector<boost::shared_ptr<TVRCentreInfoStoreServiceIf> >& ifaces) : ifaces_(ifaces) {
  }
  virtual ~TVRCentreInfoStoreServiceMultiface() {}
 protected:
  std::vector<boost::shared_ptr<TVRCentreInfoStoreServiceIf> > ifaces_;
  TVRCentreInfoStoreServiceMultiface() {}
  void add(boost::shared_ptr<TVRCentreInfoStoreServiceIf> iface) {
    ifaces_.push_back(iface);
  }
 public:
   ::CRDHN::VR::Data::TCID add(const  ::CRDHN::VR::Data::TCentreInfo& centreInfo) {
    size_t sz = ifaces_.size();
    size_t i = 0;
    for (; i < (sz - 1); ++i) {
      ifaces_[i]->add(centreInfo);
    }
    return ifaces_[i]->add(centreInfo);
  }

  void get( ::CRDHN::VR::Data::TCentreInfo& _return, const  ::CRDHN::VR::Data::TCID centreId) {
    size_t sz = ifaces_.size();
    size_t i = 0;
    for (; i < (sz - 1); ++i) {
      ifaces_[i]->get(_return, centreId);
    }
    ifaces_[i]->get(_return, centreId);
    return;
  }

  bool update(const  ::CRDHN::VR::Data::TCID centreId, const  ::CRDHN::VR::Data::TCentreInfo& centreInfo) {
    size_t sz = ifaces_.size();
    size_t i = 0;
    for (; i < (sz - 1); ++i) {
      ifaces_[i]->update(centreId, centreInfo);
    }
    return ifaces_[i]->update(centreId, centreInfo);
  }

  bool remove(const  ::CRDHN::VR::Data::TCID centreId) {
    size_t sz = ifaces_.size();
    size_t i = 0;
    for (; i < (sz - 1); ++i) {
      ifaces_[i]->remove(centreId);
    }
    return ifaces_[i]->remove(centreId);
  }

  bool addEvent(const  ::CRDHN::VR::Data::TCID centreId, const  ::CRDHN::VR::Data::TEID eventId) {
    size_t sz = ifaces_.size();
    size_t i = 0;
    for (; i < (sz - 1); ++i) {
      ifaces_[i]->addEvent(centreId, eventId);
    }
    return ifaces_[i]->addEvent(centreId, eventId);
  }

  bool removeEvent(const  ::CRDHN::VR::Data::TCID centreId, const  ::CRDHN::VR::Data::TEID eventId) {
    size_t sz = ifaces_.size();
    size_t i = 0;
    for (; i < (sz - 1); ++i) {
      ifaces_[i]->removeEvent(centreId, eventId);
    }
    return ifaces_[i]->removeEvent(centreId, eventId);
  }

  bool addVaccine(const  ::CRDHN::VR::Data::TCID centreId, const  ::CRDHN::VR::Data::TVID vaccineId) {
    size_t sz = ifaces_.size();
    size_t i = 0;
    for (; i < (sz - 1); ++i) {
      ifaces_[i]->addVaccine(centreId, vaccineId);
    }
    return ifaces_[i]->addVaccine(centreId, vaccineId);
  }

  bool removeVaccine(const  ::CRDHN::VR::Data::TCID centreId, const  ::CRDHN::VR::Data::TVID vaccineId) {
    size_t sz = ifaces_.size();
    size_t i = 0;
    for (; i < (sz - 1); ++i) {
      ifaces_[i]->removeVaccine(centreId, vaccineId);
    }
    return ifaces_[i]->removeVaccine(centreId, vaccineId);
  }

};

}} // namespace

#endif
