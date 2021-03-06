/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
#ifndef TStringToI32SetService_H
#define TStringToI32SetService_H

#include <thrift/TDispatchProcessor.h>
#include "stringtoi32setservice_types.h"
#include "TStringToI32SetServiceR.h"

namespace CRDHN { namespace Common {

class TStringToI32SetServiceIf : virtual public TStringToI32SetServiceRIf {
 public:
  virtual ~TStringToI32SetServiceIf() {}
  virtual bool setItems(const  ::CRDHN::Common::Data::StringToI32Set::TKey& key, const std::vector< ::CRDHN::Common::Data::StringToI32Set::TItem> & items) = 0;
  virtual bool clearItems(const  ::CRDHN::Common::Data::StringToI32Set::TKey& key) = 0;
  virtual bool addItem(const  ::CRDHN::Common::Data::StringToI32Set::TKey& key, const  ::CRDHN::Common::Data::StringToI32Set::TItem item) = 0;
  virtual bool addItems(const  ::CRDHN::Common::Data::StringToI32Set::TKey& key, const std::vector< ::CRDHN::Common::Data::StringToI32Set::TItem> & items) = 0;
  virtual bool removeItem(const  ::CRDHN::Common::Data::StringToI32Set::TKey& key, const  ::CRDHN::Common::Data::StringToI32Set::TItem item) = 0;
  virtual bool removeItems(const  ::CRDHN::Common::Data::StringToI32Set::TKey& key, const std::vector< ::CRDHN::Common::Data::StringToI32Set::TItem> & items) = 0;
};

class TStringToI32SetServiceIfFactory : virtual public TStringToI32SetServiceRIfFactory {
 public:
  typedef TStringToI32SetServiceIf Handler;

  virtual ~TStringToI32SetServiceIfFactory() {}

  virtual TStringToI32SetServiceIf* getHandler(const ::apache::thrift::TConnectionInfo& connInfo) = 0;
  virtual void releaseHandler(TStringToI32SetServiceRIf* /* handler */) = 0;
};

class TStringToI32SetServiceIfSingletonFactory : virtual public TStringToI32SetServiceIfFactory {
 public:
  TStringToI32SetServiceIfSingletonFactory(const boost::shared_ptr<TStringToI32SetServiceIf>& iface) : iface_(iface) {}
  virtual ~TStringToI32SetServiceIfSingletonFactory() {}

  virtual TStringToI32SetServiceIf* getHandler(const ::apache::thrift::TConnectionInfo&) {
    return iface_.get();
  }
  virtual void releaseHandler(TStringToI32SetServiceRIf* /* handler */) {}

 protected:
  boost::shared_ptr<TStringToI32SetServiceIf> iface_;
};

class TStringToI32SetServiceNull : virtual public TStringToI32SetServiceIf , virtual public TStringToI32SetServiceRNull {
 public:
  virtual ~TStringToI32SetServiceNull() {}
  bool setItems(const  ::CRDHN::Common::Data::StringToI32Set::TKey& /* key */, const std::vector< ::CRDHN::Common::Data::StringToI32Set::TItem> & /* items */) {
    bool _return = false;
    return _return;
  }
  bool clearItems(const  ::CRDHN::Common::Data::StringToI32Set::TKey& /* key */) {
    bool _return = false;
    return _return;
  }
  bool addItem(const  ::CRDHN::Common::Data::StringToI32Set::TKey& /* key */, const  ::CRDHN::Common::Data::StringToI32Set::TItem /* item */) {
    bool _return = false;
    return _return;
  }
  bool addItems(const  ::CRDHN::Common::Data::StringToI32Set::TKey& /* key */, const std::vector< ::CRDHN::Common::Data::StringToI32Set::TItem> & /* items */) {
    bool _return = false;
    return _return;
  }
  bool removeItem(const  ::CRDHN::Common::Data::StringToI32Set::TKey& /* key */, const  ::CRDHN::Common::Data::StringToI32Set::TItem /* item */) {
    bool _return = false;
    return _return;
  }
  bool removeItems(const  ::CRDHN::Common::Data::StringToI32Set::TKey& /* key */, const std::vector< ::CRDHN::Common::Data::StringToI32Set::TItem> & /* items */) {
    bool _return = false;
    return _return;
  }
};

typedef struct _TStringToI32SetService_setItems_args__isset {
  _TStringToI32SetService_setItems_args__isset() : key(false), items(false) {}
  bool key;
  bool items;
} _TStringToI32SetService_setItems_args__isset;

class TStringToI32SetService_setItems_args {
 public:

  TStringToI32SetService_setItems_args() : key() {
  }

  virtual ~TStringToI32SetService_setItems_args() throw() {}

   ::CRDHN::Common::Data::StringToI32Set::TKey key;
  std::vector< ::CRDHN::Common::Data::StringToI32Set::TItem>  items;

  _TStringToI32SetService_setItems_args__isset __isset;

  void __set_key(const  ::CRDHN::Common::Data::StringToI32Set::TKey& val) {
    key = val;
  }

  void __set_items(const std::vector< ::CRDHN::Common::Data::StringToI32Set::TItem> & val) {
    items = val;
  }

  bool operator == (const TStringToI32SetService_setItems_args & rhs) const
  {
    if (!(key == rhs.key))
      return false;
    if (!(items == rhs.items))
      return false;
    return true;
  }
  bool operator != (const TStringToI32SetService_setItems_args &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const TStringToI32SetService_setItems_args & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};


class TStringToI32SetService_setItems_pargs {
 public:


  virtual ~TStringToI32SetService_setItems_pargs() throw() {}

  const  ::CRDHN::Common::Data::StringToI32Set::TKey* key;
  const std::vector< ::CRDHN::Common::Data::StringToI32Set::TItem> * items;

  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

typedef struct _TStringToI32SetService_setItems_result__isset {
  _TStringToI32SetService_setItems_result__isset() : success(false) {}
  bool success;
} _TStringToI32SetService_setItems_result__isset;

class TStringToI32SetService_setItems_result {
 public:

  TStringToI32SetService_setItems_result() : success(0) {
  }

  virtual ~TStringToI32SetService_setItems_result() throw() {}

  bool success;

  _TStringToI32SetService_setItems_result__isset __isset;

  void __set_success(const bool val) {
    success = val;
  }

  bool operator == (const TStringToI32SetService_setItems_result & rhs) const
  {
    if (!(success == rhs.success))
      return false;
    return true;
  }
  bool operator != (const TStringToI32SetService_setItems_result &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const TStringToI32SetService_setItems_result & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

typedef struct _TStringToI32SetService_setItems_presult__isset {
  _TStringToI32SetService_setItems_presult__isset() : success(false) {}
  bool success;
} _TStringToI32SetService_setItems_presult__isset;

class TStringToI32SetService_setItems_presult {
 public:


  virtual ~TStringToI32SetService_setItems_presult() throw() {}

  bool* success;

  _TStringToI32SetService_setItems_presult__isset __isset;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);

};

typedef struct _TStringToI32SetService_clearItems_args__isset {
  _TStringToI32SetService_clearItems_args__isset() : key(false) {}
  bool key;
} _TStringToI32SetService_clearItems_args__isset;

class TStringToI32SetService_clearItems_args {
 public:

  TStringToI32SetService_clearItems_args() : key() {
  }

  virtual ~TStringToI32SetService_clearItems_args() throw() {}

   ::CRDHN::Common::Data::StringToI32Set::TKey key;

  _TStringToI32SetService_clearItems_args__isset __isset;

  void __set_key(const  ::CRDHN::Common::Data::StringToI32Set::TKey& val) {
    key = val;
  }

  bool operator == (const TStringToI32SetService_clearItems_args & rhs) const
  {
    if (!(key == rhs.key))
      return false;
    return true;
  }
  bool operator != (const TStringToI32SetService_clearItems_args &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const TStringToI32SetService_clearItems_args & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};


class TStringToI32SetService_clearItems_pargs {
 public:


  virtual ~TStringToI32SetService_clearItems_pargs() throw() {}

  const  ::CRDHN::Common::Data::StringToI32Set::TKey* key;

  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

typedef struct _TStringToI32SetService_clearItems_result__isset {
  _TStringToI32SetService_clearItems_result__isset() : success(false) {}
  bool success;
} _TStringToI32SetService_clearItems_result__isset;

class TStringToI32SetService_clearItems_result {
 public:

  TStringToI32SetService_clearItems_result() : success(0) {
  }

  virtual ~TStringToI32SetService_clearItems_result() throw() {}

  bool success;

  _TStringToI32SetService_clearItems_result__isset __isset;

  void __set_success(const bool val) {
    success = val;
  }

  bool operator == (const TStringToI32SetService_clearItems_result & rhs) const
  {
    if (!(success == rhs.success))
      return false;
    return true;
  }
  bool operator != (const TStringToI32SetService_clearItems_result &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const TStringToI32SetService_clearItems_result & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

typedef struct _TStringToI32SetService_clearItems_presult__isset {
  _TStringToI32SetService_clearItems_presult__isset() : success(false) {}
  bool success;
} _TStringToI32SetService_clearItems_presult__isset;

class TStringToI32SetService_clearItems_presult {
 public:


  virtual ~TStringToI32SetService_clearItems_presult() throw() {}

  bool* success;

  _TStringToI32SetService_clearItems_presult__isset __isset;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);

};

typedef struct _TStringToI32SetService_addItem_args__isset {
  _TStringToI32SetService_addItem_args__isset() : key(false), item(false) {}
  bool key;
  bool item;
} _TStringToI32SetService_addItem_args__isset;

class TStringToI32SetService_addItem_args {
 public:

  TStringToI32SetService_addItem_args() : key(), item(0) {
  }

  virtual ~TStringToI32SetService_addItem_args() throw() {}

   ::CRDHN::Common::Data::StringToI32Set::TKey key;
   ::CRDHN::Common::Data::StringToI32Set::TItem item;

  _TStringToI32SetService_addItem_args__isset __isset;

  void __set_key(const  ::CRDHN::Common::Data::StringToI32Set::TKey& val) {
    key = val;
  }

  void __set_item(const  ::CRDHN::Common::Data::StringToI32Set::TItem val) {
    item = val;
  }

  bool operator == (const TStringToI32SetService_addItem_args & rhs) const
  {
    if (!(key == rhs.key))
      return false;
    if (!(item == rhs.item))
      return false;
    return true;
  }
  bool operator != (const TStringToI32SetService_addItem_args &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const TStringToI32SetService_addItem_args & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};


class TStringToI32SetService_addItem_pargs {
 public:


  virtual ~TStringToI32SetService_addItem_pargs() throw() {}

  const  ::CRDHN::Common::Data::StringToI32Set::TKey* key;
  const  ::CRDHN::Common::Data::StringToI32Set::TItem* item;

  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

typedef struct _TStringToI32SetService_addItem_result__isset {
  _TStringToI32SetService_addItem_result__isset() : success(false) {}
  bool success;
} _TStringToI32SetService_addItem_result__isset;

class TStringToI32SetService_addItem_result {
 public:

  TStringToI32SetService_addItem_result() : success(0) {
  }

  virtual ~TStringToI32SetService_addItem_result() throw() {}

  bool success;

  _TStringToI32SetService_addItem_result__isset __isset;

  void __set_success(const bool val) {
    success = val;
  }

  bool operator == (const TStringToI32SetService_addItem_result & rhs) const
  {
    if (!(success == rhs.success))
      return false;
    return true;
  }
  bool operator != (const TStringToI32SetService_addItem_result &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const TStringToI32SetService_addItem_result & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

typedef struct _TStringToI32SetService_addItem_presult__isset {
  _TStringToI32SetService_addItem_presult__isset() : success(false) {}
  bool success;
} _TStringToI32SetService_addItem_presult__isset;

class TStringToI32SetService_addItem_presult {
 public:


  virtual ~TStringToI32SetService_addItem_presult() throw() {}

  bool* success;

  _TStringToI32SetService_addItem_presult__isset __isset;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);

};

typedef struct _TStringToI32SetService_addItems_args__isset {
  _TStringToI32SetService_addItems_args__isset() : key(false), items(false) {}
  bool key;
  bool items;
} _TStringToI32SetService_addItems_args__isset;

class TStringToI32SetService_addItems_args {
 public:

  TStringToI32SetService_addItems_args() : key() {
  }

  virtual ~TStringToI32SetService_addItems_args() throw() {}

   ::CRDHN::Common::Data::StringToI32Set::TKey key;
  std::vector< ::CRDHN::Common::Data::StringToI32Set::TItem>  items;

  _TStringToI32SetService_addItems_args__isset __isset;

  void __set_key(const  ::CRDHN::Common::Data::StringToI32Set::TKey& val) {
    key = val;
  }

  void __set_items(const std::vector< ::CRDHN::Common::Data::StringToI32Set::TItem> & val) {
    items = val;
  }

  bool operator == (const TStringToI32SetService_addItems_args & rhs) const
  {
    if (!(key == rhs.key))
      return false;
    if (!(items == rhs.items))
      return false;
    return true;
  }
  bool operator != (const TStringToI32SetService_addItems_args &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const TStringToI32SetService_addItems_args & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};


class TStringToI32SetService_addItems_pargs {
 public:


  virtual ~TStringToI32SetService_addItems_pargs() throw() {}

  const  ::CRDHN::Common::Data::StringToI32Set::TKey* key;
  const std::vector< ::CRDHN::Common::Data::StringToI32Set::TItem> * items;

  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

typedef struct _TStringToI32SetService_addItems_result__isset {
  _TStringToI32SetService_addItems_result__isset() : success(false) {}
  bool success;
} _TStringToI32SetService_addItems_result__isset;

class TStringToI32SetService_addItems_result {
 public:

  TStringToI32SetService_addItems_result() : success(0) {
  }

  virtual ~TStringToI32SetService_addItems_result() throw() {}

  bool success;

  _TStringToI32SetService_addItems_result__isset __isset;

  void __set_success(const bool val) {
    success = val;
  }

  bool operator == (const TStringToI32SetService_addItems_result & rhs) const
  {
    if (!(success == rhs.success))
      return false;
    return true;
  }
  bool operator != (const TStringToI32SetService_addItems_result &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const TStringToI32SetService_addItems_result & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

typedef struct _TStringToI32SetService_addItems_presult__isset {
  _TStringToI32SetService_addItems_presult__isset() : success(false) {}
  bool success;
} _TStringToI32SetService_addItems_presult__isset;

class TStringToI32SetService_addItems_presult {
 public:


  virtual ~TStringToI32SetService_addItems_presult() throw() {}

  bool* success;

  _TStringToI32SetService_addItems_presult__isset __isset;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);

};

typedef struct _TStringToI32SetService_removeItem_args__isset {
  _TStringToI32SetService_removeItem_args__isset() : key(false), item(false) {}
  bool key;
  bool item;
} _TStringToI32SetService_removeItem_args__isset;

class TStringToI32SetService_removeItem_args {
 public:

  TStringToI32SetService_removeItem_args() : key(), item(0) {
  }

  virtual ~TStringToI32SetService_removeItem_args() throw() {}

   ::CRDHN::Common::Data::StringToI32Set::TKey key;
   ::CRDHN::Common::Data::StringToI32Set::TItem item;

  _TStringToI32SetService_removeItem_args__isset __isset;

  void __set_key(const  ::CRDHN::Common::Data::StringToI32Set::TKey& val) {
    key = val;
  }

  void __set_item(const  ::CRDHN::Common::Data::StringToI32Set::TItem val) {
    item = val;
  }

  bool operator == (const TStringToI32SetService_removeItem_args & rhs) const
  {
    if (!(key == rhs.key))
      return false;
    if (!(item == rhs.item))
      return false;
    return true;
  }
  bool operator != (const TStringToI32SetService_removeItem_args &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const TStringToI32SetService_removeItem_args & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};


class TStringToI32SetService_removeItem_pargs {
 public:


  virtual ~TStringToI32SetService_removeItem_pargs() throw() {}

  const  ::CRDHN::Common::Data::StringToI32Set::TKey* key;
  const  ::CRDHN::Common::Data::StringToI32Set::TItem* item;

  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

typedef struct _TStringToI32SetService_removeItem_result__isset {
  _TStringToI32SetService_removeItem_result__isset() : success(false) {}
  bool success;
} _TStringToI32SetService_removeItem_result__isset;

class TStringToI32SetService_removeItem_result {
 public:

  TStringToI32SetService_removeItem_result() : success(0) {
  }

  virtual ~TStringToI32SetService_removeItem_result() throw() {}

  bool success;

  _TStringToI32SetService_removeItem_result__isset __isset;

  void __set_success(const bool val) {
    success = val;
  }

  bool operator == (const TStringToI32SetService_removeItem_result & rhs) const
  {
    if (!(success == rhs.success))
      return false;
    return true;
  }
  bool operator != (const TStringToI32SetService_removeItem_result &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const TStringToI32SetService_removeItem_result & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

typedef struct _TStringToI32SetService_removeItem_presult__isset {
  _TStringToI32SetService_removeItem_presult__isset() : success(false) {}
  bool success;
} _TStringToI32SetService_removeItem_presult__isset;

class TStringToI32SetService_removeItem_presult {
 public:


  virtual ~TStringToI32SetService_removeItem_presult() throw() {}

  bool* success;

  _TStringToI32SetService_removeItem_presult__isset __isset;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);

};

typedef struct _TStringToI32SetService_removeItems_args__isset {
  _TStringToI32SetService_removeItems_args__isset() : key(false), items(false) {}
  bool key;
  bool items;
} _TStringToI32SetService_removeItems_args__isset;

class TStringToI32SetService_removeItems_args {
 public:

  TStringToI32SetService_removeItems_args() : key() {
  }

  virtual ~TStringToI32SetService_removeItems_args() throw() {}

   ::CRDHN::Common::Data::StringToI32Set::TKey key;
  std::vector< ::CRDHN::Common::Data::StringToI32Set::TItem>  items;

  _TStringToI32SetService_removeItems_args__isset __isset;

  void __set_key(const  ::CRDHN::Common::Data::StringToI32Set::TKey& val) {
    key = val;
  }

  void __set_items(const std::vector< ::CRDHN::Common::Data::StringToI32Set::TItem> & val) {
    items = val;
  }

  bool operator == (const TStringToI32SetService_removeItems_args & rhs) const
  {
    if (!(key == rhs.key))
      return false;
    if (!(items == rhs.items))
      return false;
    return true;
  }
  bool operator != (const TStringToI32SetService_removeItems_args &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const TStringToI32SetService_removeItems_args & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};


class TStringToI32SetService_removeItems_pargs {
 public:


  virtual ~TStringToI32SetService_removeItems_pargs() throw() {}

  const  ::CRDHN::Common::Data::StringToI32Set::TKey* key;
  const std::vector< ::CRDHN::Common::Data::StringToI32Set::TItem> * items;

  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

typedef struct _TStringToI32SetService_removeItems_result__isset {
  _TStringToI32SetService_removeItems_result__isset() : success(false) {}
  bool success;
} _TStringToI32SetService_removeItems_result__isset;

class TStringToI32SetService_removeItems_result {
 public:

  TStringToI32SetService_removeItems_result() : success(0) {
  }

  virtual ~TStringToI32SetService_removeItems_result() throw() {}

  bool success;

  _TStringToI32SetService_removeItems_result__isset __isset;

  void __set_success(const bool val) {
    success = val;
  }

  bool operator == (const TStringToI32SetService_removeItems_result & rhs) const
  {
    if (!(success == rhs.success))
      return false;
    return true;
  }
  bool operator != (const TStringToI32SetService_removeItems_result &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const TStringToI32SetService_removeItems_result & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

typedef struct _TStringToI32SetService_removeItems_presult__isset {
  _TStringToI32SetService_removeItems_presult__isset() : success(false) {}
  bool success;
} _TStringToI32SetService_removeItems_presult__isset;

class TStringToI32SetService_removeItems_presult {
 public:


  virtual ~TStringToI32SetService_removeItems_presult() throw() {}

  bool* success;

  _TStringToI32SetService_removeItems_presult__isset __isset;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);

};

class TStringToI32SetServiceClient : virtual public TStringToI32SetServiceIf, public TStringToI32SetServiceRClient {
 public:
  TStringToI32SetServiceClient(boost::shared_ptr< ::apache::thrift::protocol::TProtocol> prot) :
    TStringToI32SetServiceRClient(prot, prot) {}
  TStringToI32SetServiceClient(boost::shared_ptr< ::apache::thrift::protocol::TProtocol> iprot, boost::shared_ptr< ::apache::thrift::protocol::TProtocol> oprot) :
    TStringToI32SetServiceRClient(iprot, oprot) {}
  boost::shared_ptr< ::apache::thrift::protocol::TProtocol> getInputProtocol() {
    return piprot_;
  }
  boost::shared_ptr< ::apache::thrift::protocol::TProtocol> getOutputProtocol() {
    return poprot_;
  }
  bool setItems(const  ::CRDHN::Common::Data::StringToI32Set::TKey& key, const std::vector< ::CRDHN::Common::Data::StringToI32Set::TItem> & items);
  void send_setItems(const  ::CRDHN::Common::Data::StringToI32Set::TKey& key, const std::vector< ::CRDHN::Common::Data::StringToI32Set::TItem> & items);
  bool recv_setItems();
  bool clearItems(const  ::CRDHN::Common::Data::StringToI32Set::TKey& key);
  void send_clearItems(const  ::CRDHN::Common::Data::StringToI32Set::TKey& key);
  bool recv_clearItems();
  bool addItem(const  ::CRDHN::Common::Data::StringToI32Set::TKey& key, const  ::CRDHN::Common::Data::StringToI32Set::TItem item);
  void send_addItem(const  ::CRDHN::Common::Data::StringToI32Set::TKey& key, const  ::CRDHN::Common::Data::StringToI32Set::TItem item);
  bool recv_addItem();
  bool addItems(const  ::CRDHN::Common::Data::StringToI32Set::TKey& key, const std::vector< ::CRDHN::Common::Data::StringToI32Set::TItem> & items);
  void send_addItems(const  ::CRDHN::Common::Data::StringToI32Set::TKey& key, const std::vector< ::CRDHN::Common::Data::StringToI32Set::TItem> & items);
  bool recv_addItems();
  bool removeItem(const  ::CRDHN::Common::Data::StringToI32Set::TKey& key, const  ::CRDHN::Common::Data::StringToI32Set::TItem item);
  void send_removeItem(const  ::CRDHN::Common::Data::StringToI32Set::TKey& key, const  ::CRDHN::Common::Data::StringToI32Set::TItem item);
  bool recv_removeItem();
  bool removeItems(const  ::CRDHN::Common::Data::StringToI32Set::TKey& key, const std::vector< ::CRDHN::Common::Data::StringToI32Set::TItem> & items);
  void send_removeItems(const  ::CRDHN::Common::Data::StringToI32Set::TKey& key, const std::vector< ::CRDHN::Common::Data::StringToI32Set::TItem> & items);
  bool recv_removeItems();
};

class TStringToI32SetServiceProcessor : public TStringToI32SetServiceRProcessor {
 protected:
  boost::shared_ptr<TStringToI32SetServiceIf> iface_;
  virtual bool dispatchCall(::apache::thrift::protocol::TProtocol* iprot, ::apache::thrift::protocol::TProtocol* oprot, const std::string& fname, int32_t seqid, void* callContext);
 private:
  typedef  void (TStringToI32SetServiceProcessor::*ProcessFunction)(int32_t, ::apache::thrift::protocol::TProtocol*, ::apache::thrift::protocol::TProtocol*, void*);
  typedef std::map<std::string, ProcessFunction> ProcessMap;
  ProcessMap processMap_;
  void process_setItems(int32_t seqid, ::apache::thrift::protocol::TProtocol* iprot, ::apache::thrift::protocol::TProtocol* oprot, void* callContext);
  void process_clearItems(int32_t seqid, ::apache::thrift::protocol::TProtocol* iprot, ::apache::thrift::protocol::TProtocol* oprot, void* callContext);
  void process_addItem(int32_t seqid, ::apache::thrift::protocol::TProtocol* iprot, ::apache::thrift::protocol::TProtocol* oprot, void* callContext);
  void process_addItems(int32_t seqid, ::apache::thrift::protocol::TProtocol* iprot, ::apache::thrift::protocol::TProtocol* oprot, void* callContext);
  void process_removeItem(int32_t seqid, ::apache::thrift::protocol::TProtocol* iprot, ::apache::thrift::protocol::TProtocol* oprot, void* callContext);
  void process_removeItems(int32_t seqid, ::apache::thrift::protocol::TProtocol* iprot, ::apache::thrift::protocol::TProtocol* oprot, void* callContext);
 public:
  TStringToI32SetServiceProcessor(boost::shared_ptr<TStringToI32SetServiceIf> iface) :
    TStringToI32SetServiceRProcessor(iface),
    iface_(iface) {
    processMap_["setItems"] = &TStringToI32SetServiceProcessor::process_setItems;
    processMap_["clearItems"] = &TStringToI32SetServiceProcessor::process_clearItems;
    processMap_["addItem"] = &TStringToI32SetServiceProcessor::process_addItem;
    processMap_["addItems"] = &TStringToI32SetServiceProcessor::process_addItems;
    processMap_["removeItem"] = &TStringToI32SetServiceProcessor::process_removeItem;
    processMap_["removeItems"] = &TStringToI32SetServiceProcessor::process_removeItems;
  }

  virtual ~TStringToI32SetServiceProcessor() {}
};

class TStringToI32SetServiceProcessorFactory : public ::apache::thrift::TProcessorFactory {
 public:
  TStringToI32SetServiceProcessorFactory(const ::boost::shared_ptr< TStringToI32SetServiceIfFactory >& handlerFactory) :
      handlerFactory_(handlerFactory) {}

  ::boost::shared_ptr< ::apache::thrift::TProcessor > getProcessor(const ::apache::thrift::TConnectionInfo& connInfo);

 protected:
  ::boost::shared_ptr< TStringToI32SetServiceIfFactory > handlerFactory_;
};

class TStringToI32SetServiceMultiface : virtual public TStringToI32SetServiceIf, public TStringToI32SetServiceRMultiface {
 public:
  TStringToI32SetServiceMultiface(std::vector<boost::shared_ptr<TStringToI32SetServiceIf> >& ifaces) : ifaces_(ifaces) {
    std::vector<boost::shared_ptr<TStringToI32SetServiceIf> >::iterator iter;
    for (iter = ifaces.begin(); iter != ifaces.end(); ++iter) {
      TStringToI32SetServiceRMultiface::add(*iter);
    }
  }
  virtual ~TStringToI32SetServiceMultiface() {}
 protected:
  std::vector<boost::shared_ptr<TStringToI32SetServiceIf> > ifaces_;
  TStringToI32SetServiceMultiface() {}
  void add(boost::shared_ptr<TStringToI32SetServiceIf> iface) {
    TStringToI32SetServiceRMultiface::add(iface);
    ifaces_.push_back(iface);
  }
 public:
  bool setItems(const  ::CRDHN::Common::Data::StringToI32Set::TKey& key, const std::vector< ::CRDHN::Common::Data::StringToI32Set::TItem> & items) {
    size_t sz = ifaces_.size();
    size_t i = 0;
    for (; i < (sz - 1); ++i) {
      ifaces_[i]->setItems(key, items);
    }
    return ifaces_[i]->setItems(key, items);
  }

  bool clearItems(const  ::CRDHN::Common::Data::StringToI32Set::TKey& key) {
    size_t sz = ifaces_.size();
    size_t i = 0;
    for (; i < (sz - 1); ++i) {
      ifaces_[i]->clearItems(key);
    }
    return ifaces_[i]->clearItems(key);
  }

  bool addItem(const  ::CRDHN::Common::Data::StringToI32Set::TKey& key, const  ::CRDHN::Common::Data::StringToI32Set::TItem item) {
    size_t sz = ifaces_.size();
    size_t i = 0;
    for (; i < (sz - 1); ++i) {
      ifaces_[i]->addItem(key, item);
    }
    return ifaces_[i]->addItem(key, item);
  }

  bool addItems(const  ::CRDHN::Common::Data::StringToI32Set::TKey& key, const std::vector< ::CRDHN::Common::Data::StringToI32Set::TItem> & items) {
    size_t sz = ifaces_.size();
    size_t i = 0;
    for (; i < (sz - 1); ++i) {
      ifaces_[i]->addItems(key, items);
    }
    return ifaces_[i]->addItems(key, items);
  }

  bool removeItem(const  ::CRDHN::Common::Data::StringToI32Set::TKey& key, const  ::CRDHN::Common::Data::StringToI32Set::TItem item) {
    size_t sz = ifaces_.size();
    size_t i = 0;
    for (; i < (sz - 1); ++i) {
      ifaces_[i]->removeItem(key, item);
    }
    return ifaces_[i]->removeItem(key, item);
  }

  bool removeItems(const  ::CRDHN::Common::Data::StringToI32Set::TKey& key, const std::vector< ::CRDHN::Common::Data::StringToI32Set::TItem> & items) {
    size_t sz = ifaces_.size();
    size_t i = 0;
    for (; i < (sz - 1); ++i) {
      ifaces_[i]->removeItems(key, items);
    }
    return ifaces_[i]->removeItems(key, items);
  }

};

}} // namespace

#endif
