/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package crdhn.vr.thrift.response;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TVMGetRegistrationsResponse implements org.apache.thrift.TBase<TVMGetRegistrationsResponse, TVMGetRegistrationsResponse._Fields>, java.io.Serializable, Cloneable, Comparable<TVMGetRegistrationsResponse> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TVMGetRegistrationsResponse");

  private static final org.apache.thrift.protocol.TField ERROR_FIELD_DESC = new org.apache.thrift.protocol.TField("error", org.apache.thrift.protocol.TType.STRUCT, (short)1);
  private static final org.apache.thrift.protocol.TField REGISTRATIONS_FIELD_DESC = new org.apache.thrift.protocol.TField("registrations", org.apache.thrift.protocol.TType.LIST, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new TVMGetRegistrationsResponseStandardSchemeFactory());
    schemes.put(TupleScheme.class, new TVMGetRegistrationsResponseTupleSchemeFactory());
  }

  public TError error; // required
  public List<crdhn.vr.thrift.data.TRegistrationInfo> registrations; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ERROR((short)1, "error"),
    REGISTRATIONS((short)2, "registrations");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // ERROR
          return ERROR;
        case 2: // REGISTRATIONS
          return REGISTRATIONS;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private _Fields optionals[] = {_Fields.REGISTRATIONS};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ERROR, new org.apache.thrift.meta_data.FieldMetaData("error", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TError.class)));
    tmpMap.put(_Fields.REGISTRATIONS, new org.apache.thrift.meta_data.FieldMetaData("registrations", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, crdhn.vr.thrift.data.TRegistrationInfo.class))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TVMGetRegistrationsResponse.class, metaDataMap);
  }

  public TVMGetRegistrationsResponse() {
  }

  public TVMGetRegistrationsResponse(
    TError error)
  {
    this();
    this.error = error;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TVMGetRegistrationsResponse(TVMGetRegistrationsResponse other) {
    if (other.isSetError()) {
      this.error = new TError(other.error);
    }
    if (other.isSetRegistrations()) {
      List<crdhn.vr.thrift.data.TRegistrationInfo> __this__registrations = new ArrayList<crdhn.vr.thrift.data.TRegistrationInfo>(other.registrations.size());
      for (crdhn.vr.thrift.data.TRegistrationInfo other_element : other.registrations) {
        __this__registrations.add(new crdhn.vr.thrift.data.TRegistrationInfo(other_element));
      }
      this.registrations = __this__registrations;
    }
  }

  public TVMGetRegistrationsResponse deepCopy() {
    return new TVMGetRegistrationsResponse(this);
  }

  @Override
  public void clear() {
    this.error = null;
    this.registrations = null;
  }

  public TError getError() {
    return this.error;
  }

  public TVMGetRegistrationsResponse setError(TError error) {
    this.error = error;
    return this;
  }

  public void unsetError() {
    this.error = null;
  }

  /** Returns true if field error is set (has been assigned a value) and false otherwise */
  public boolean isSetError() {
    return this.error != null;
  }

  public void setErrorIsSet(boolean value) {
    if (!value) {
      this.error = null;
    }
  }

  public int getRegistrationsSize() {
    return (this.registrations == null) ? 0 : this.registrations.size();
  }

  public java.util.Iterator<crdhn.vr.thrift.data.TRegistrationInfo> getRegistrationsIterator() {
    return (this.registrations == null) ? null : this.registrations.iterator();
  }

  public void addToRegistrations(crdhn.vr.thrift.data.TRegistrationInfo elem) {
    if (this.registrations == null) {
      this.registrations = new ArrayList<crdhn.vr.thrift.data.TRegistrationInfo>();
    }
    this.registrations.add(elem);
  }

  public List<crdhn.vr.thrift.data.TRegistrationInfo> getRegistrations() {
    return this.registrations;
  }

  public TVMGetRegistrationsResponse setRegistrations(List<crdhn.vr.thrift.data.TRegistrationInfo> registrations) {
    this.registrations = registrations;
    return this;
  }

  public void unsetRegistrations() {
    this.registrations = null;
  }

  /** Returns true if field registrations is set (has been assigned a value) and false otherwise */
  public boolean isSetRegistrations() {
    return this.registrations != null;
  }

  public void setRegistrationsIsSet(boolean value) {
    if (!value) {
      this.registrations = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case ERROR:
      if (value == null) {
        unsetError();
      } else {
        setError((TError)value);
      }
      break;

    case REGISTRATIONS:
      if (value == null) {
        unsetRegistrations();
      } else {
        setRegistrations((List<crdhn.vr.thrift.data.TRegistrationInfo>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ERROR:
      return getError();

    case REGISTRATIONS:
      return getRegistrations();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case ERROR:
      return isSetError();
    case REGISTRATIONS:
      return isSetRegistrations();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TVMGetRegistrationsResponse)
      return this.equals((TVMGetRegistrationsResponse)that);
    return false;
  }

  public boolean equals(TVMGetRegistrationsResponse that) {
    if (that == null)
      return false;

    boolean this_present_error = true && this.isSetError();
    boolean that_present_error = true && that.isSetError();
    if (this_present_error || that_present_error) {
      if (!(this_present_error && that_present_error))
        return false;
      if (!this.error.equals(that.error))
        return false;
    }

    boolean this_present_registrations = true && this.isSetRegistrations();
    boolean that_present_registrations = true && that.isSetRegistrations();
    if (this_present_registrations || that_present_registrations) {
      if (!(this_present_registrations && that_present_registrations))
        return false;
      if (!this.registrations.equals(that.registrations))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(TVMGetRegistrationsResponse other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetError()).compareTo(other.isSetError());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetError()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.error, other.error);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetRegistrations()).compareTo(other.isSetRegistrations());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRegistrations()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.registrations, other.registrations);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("TVMGetRegistrationsResponse(");
    boolean first = true;

    sb.append("error:");
    if (this.error == null) {
      sb.append("null");
    } else {
      sb.append(this.error);
    }
    first = false;
    if (isSetRegistrations()) {
      if (!first) sb.append(", ");
      sb.append("registrations:");
      if (this.registrations == null) {
        sb.append("null");
      } else {
        sb.append(this.registrations);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (error == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'error' was not present! Struct: " + toString());
    }
    // check for sub-struct validity
    if (error != null) {
      error.validate();
    }
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TVMGetRegistrationsResponseStandardSchemeFactory implements SchemeFactory {
    public TVMGetRegistrationsResponseStandardScheme getScheme() {
      return new TVMGetRegistrationsResponseStandardScheme();
    }
  }

  private static class TVMGetRegistrationsResponseStandardScheme extends StandardScheme<TVMGetRegistrationsResponse> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TVMGetRegistrationsResponse struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ERROR
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.error = new TError();
              struct.error.read(iprot);
              struct.setErrorIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // REGISTRATIONS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list0 = iprot.readListBegin();
                struct.registrations = new ArrayList<crdhn.vr.thrift.data.TRegistrationInfo>(_list0.size);
                for (int _i1 = 0; _i1 < _list0.size; ++_i1)
                {
                  crdhn.vr.thrift.data.TRegistrationInfo _elem2;
                  _elem2 = new crdhn.vr.thrift.data.TRegistrationInfo();
                  _elem2.read(iprot);
                  struct.registrations.add(_elem2);
                }
                iprot.readListEnd();
              }
              struct.setRegistrationsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, TVMGetRegistrationsResponse struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.error != null) {
        oprot.writeFieldBegin(ERROR_FIELD_DESC);
        struct.error.write(oprot);
        oprot.writeFieldEnd();
      }
      if (struct.registrations != null) {
        if (struct.isSetRegistrations()) {
          oprot.writeFieldBegin(REGISTRATIONS_FIELD_DESC);
          {
            oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.registrations.size()));
            for (crdhn.vr.thrift.data.TRegistrationInfo _iter3 : struct.registrations)
            {
              _iter3.write(oprot);
            }
            oprot.writeListEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TVMGetRegistrationsResponseTupleSchemeFactory implements SchemeFactory {
    public TVMGetRegistrationsResponseTupleScheme getScheme() {
      return new TVMGetRegistrationsResponseTupleScheme();
    }
  }

  private static class TVMGetRegistrationsResponseTupleScheme extends TupleScheme<TVMGetRegistrationsResponse> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TVMGetRegistrationsResponse struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      struct.error.write(oprot);
      BitSet optionals = new BitSet();
      if (struct.isSetRegistrations()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetRegistrations()) {
        {
          oprot.writeI32(struct.registrations.size());
          for (crdhn.vr.thrift.data.TRegistrationInfo _iter4 : struct.registrations)
          {
            _iter4.write(oprot);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TVMGetRegistrationsResponse struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.error = new TError();
      struct.error.read(iprot);
      struct.setErrorIsSet(true);
      BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TList _list5 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.registrations = new ArrayList<crdhn.vr.thrift.data.TRegistrationInfo>(_list5.size);
          for (int _i6 = 0; _i6 < _list5.size; ++_i6)
          {
            crdhn.vr.thrift.data.TRegistrationInfo _elem7;
            _elem7 = new crdhn.vr.thrift.data.TRegistrationInfo();
            _elem7.read(iprot);
            struct.registrations.add(_elem7);
          }
        }
        struct.setRegistrationsIsSet(true);
      }
    }
  }

}

