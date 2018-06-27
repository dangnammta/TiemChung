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

public class TRMAddExtraRegistrationResponse implements org.apache.thrift.TBase<TRMAddExtraRegistrationResponse, TRMAddExtraRegistrationResponse._Fields>, java.io.Serializable, Cloneable, Comparable<TRMAddExtraRegistrationResponse> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TRMAddExtraRegistrationResponse");

  private static final org.apache.thrift.protocol.TField ERROR_FIELD_DESC = new org.apache.thrift.protocol.TField("error", org.apache.thrift.protocol.TType.STRUCT, (short)1);
  private static final org.apache.thrift.protocol.TField REGISTRATION_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("registrationId", org.apache.thrift.protocol.TType.I64, (short)2);
  private static final org.apache.thrift.protocol.TField REGISTRATION_CODE_FIELD_DESC = new org.apache.thrift.protocol.TField("registrationCode", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField INJECTION_ORDER_NUMBER_FIELD_DESC = new org.apache.thrift.protocol.TField("injectionOrderNumber", org.apache.thrift.protocol.TType.I32, (short)4);
  private static final org.apache.thrift.protocol.TField INJECTION_TIME_FIELD_DESC = new org.apache.thrift.protocol.TField("injectionTime", org.apache.thrift.protocol.TType.STRING, (short)5);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new TRMAddExtraRegistrationResponseStandardSchemeFactory());
    schemes.put(TupleScheme.class, new TRMAddExtraRegistrationResponseTupleSchemeFactory());
  }

  public TError error; // required
  public long registrationId; // optional
  public String registrationCode; // optional
  public int injectionOrderNumber; // optional
  public String injectionTime; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ERROR((short)1, "error"),
    REGISTRATION_ID((short)2, "registrationId"),
    REGISTRATION_CODE((short)3, "registrationCode"),
    INJECTION_ORDER_NUMBER((short)4, "injectionOrderNumber"),
    INJECTION_TIME((short)5, "injectionTime");

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
        case 2: // REGISTRATION_ID
          return REGISTRATION_ID;
        case 3: // REGISTRATION_CODE
          return REGISTRATION_CODE;
        case 4: // INJECTION_ORDER_NUMBER
          return INJECTION_ORDER_NUMBER;
        case 5: // INJECTION_TIME
          return INJECTION_TIME;
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
  private static final int __REGISTRATIONID_ISSET_ID = 0;
  private static final int __INJECTIONORDERNUMBER_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  private _Fields optionals[] = {_Fields.REGISTRATION_ID,_Fields.REGISTRATION_CODE,_Fields.INJECTION_ORDER_NUMBER,_Fields.INJECTION_TIME};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ERROR, new org.apache.thrift.meta_data.FieldMetaData("error", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TError.class)));
    tmpMap.put(_Fields.REGISTRATION_ID, new org.apache.thrift.meta_data.FieldMetaData("registrationId", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64        , "TRID")));
    tmpMap.put(_Fields.REGISTRATION_CODE, new org.apache.thrift.meta_data.FieldMetaData("registrationCode", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.INJECTION_ORDER_NUMBER, new org.apache.thrift.meta_data.FieldMetaData("injectionOrderNumber", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.INJECTION_TIME, new org.apache.thrift.meta_data.FieldMetaData("injectionTime", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TRMAddExtraRegistrationResponse.class, metaDataMap);
  }

  public TRMAddExtraRegistrationResponse() {
  }

  public TRMAddExtraRegistrationResponse(
    TError error)
  {
    this();
    this.error = error;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TRMAddExtraRegistrationResponse(TRMAddExtraRegistrationResponse other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetError()) {
      this.error = new TError(other.error);
    }
    this.registrationId = other.registrationId;
    if (other.isSetRegistrationCode()) {
      this.registrationCode = other.registrationCode;
    }
    this.injectionOrderNumber = other.injectionOrderNumber;
    if (other.isSetInjectionTime()) {
      this.injectionTime = other.injectionTime;
    }
  }

  public TRMAddExtraRegistrationResponse deepCopy() {
    return new TRMAddExtraRegistrationResponse(this);
  }

  @Override
  public void clear() {
    this.error = null;
    setRegistrationIdIsSet(false);
    this.registrationId = 0;
    this.registrationCode = null;
    setInjectionOrderNumberIsSet(false);
    this.injectionOrderNumber = 0;
    this.injectionTime = null;
  }

  public TError getError() {
    return this.error;
  }

  public TRMAddExtraRegistrationResponse setError(TError error) {
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

  public long getRegistrationId() {
    return this.registrationId;
  }

  public TRMAddExtraRegistrationResponse setRegistrationId(long registrationId) {
    this.registrationId = registrationId;
    setRegistrationIdIsSet(true);
    return this;
  }

  public void unsetRegistrationId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __REGISTRATIONID_ISSET_ID);
  }

  /** Returns true if field registrationId is set (has been assigned a value) and false otherwise */
  public boolean isSetRegistrationId() {
    return EncodingUtils.testBit(__isset_bitfield, __REGISTRATIONID_ISSET_ID);
  }

  public void setRegistrationIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __REGISTRATIONID_ISSET_ID, value);
  }

  public String getRegistrationCode() {
    return this.registrationCode;
  }

  public TRMAddExtraRegistrationResponse setRegistrationCode(String registrationCode) {
    this.registrationCode = registrationCode;
    return this;
  }

  public void unsetRegistrationCode() {
    this.registrationCode = null;
  }

  /** Returns true if field registrationCode is set (has been assigned a value) and false otherwise */
  public boolean isSetRegistrationCode() {
    return this.registrationCode != null;
  }

  public void setRegistrationCodeIsSet(boolean value) {
    if (!value) {
      this.registrationCode = null;
    }
  }

  public int getInjectionOrderNumber() {
    return this.injectionOrderNumber;
  }

  public TRMAddExtraRegistrationResponse setInjectionOrderNumber(int injectionOrderNumber) {
    this.injectionOrderNumber = injectionOrderNumber;
    setInjectionOrderNumberIsSet(true);
    return this;
  }

  public void unsetInjectionOrderNumber() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __INJECTIONORDERNUMBER_ISSET_ID);
  }

  /** Returns true if field injectionOrderNumber is set (has been assigned a value) and false otherwise */
  public boolean isSetInjectionOrderNumber() {
    return EncodingUtils.testBit(__isset_bitfield, __INJECTIONORDERNUMBER_ISSET_ID);
  }

  public void setInjectionOrderNumberIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __INJECTIONORDERNUMBER_ISSET_ID, value);
  }

  public String getInjectionTime() {
    return this.injectionTime;
  }

  public TRMAddExtraRegistrationResponse setInjectionTime(String injectionTime) {
    this.injectionTime = injectionTime;
    return this;
  }

  public void unsetInjectionTime() {
    this.injectionTime = null;
  }

  /** Returns true if field injectionTime is set (has been assigned a value) and false otherwise */
  public boolean isSetInjectionTime() {
    return this.injectionTime != null;
  }

  public void setInjectionTimeIsSet(boolean value) {
    if (!value) {
      this.injectionTime = null;
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

    case REGISTRATION_ID:
      if (value == null) {
        unsetRegistrationId();
      } else {
        setRegistrationId((Long)value);
      }
      break;

    case REGISTRATION_CODE:
      if (value == null) {
        unsetRegistrationCode();
      } else {
        setRegistrationCode((String)value);
      }
      break;

    case INJECTION_ORDER_NUMBER:
      if (value == null) {
        unsetInjectionOrderNumber();
      } else {
        setInjectionOrderNumber((Integer)value);
      }
      break;

    case INJECTION_TIME:
      if (value == null) {
        unsetInjectionTime();
      } else {
        setInjectionTime((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ERROR:
      return getError();

    case REGISTRATION_ID:
      return Long.valueOf(getRegistrationId());

    case REGISTRATION_CODE:
      return getRegistrationCode();

    case INJECTION_ORDER_NUMBER:
      return Integer.valueOf(getInjectionOrderNumber());

    case INJECTION_TIME:
      return getInjectionTime();

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
    case REGISTRATION_ID:
      return isSetRegistrationId();
    case REGISTRATION_CODE:
      return isSetRegistrationCode();
    case INJECTION_ORDER_NUMBER:
      return isSetInjectionOrderNumber();
    case INJECTION_TIME:
      return isSetInjectionTime();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TRMAddExtraRegistrationResponse)
      return this.equals((TRMAddExtraRegistrationResponse)that);
    return false;
  }

  public boolean equals(TRMAddExtraRegistrationResponse that) {
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

    boolean this_present_registrationId = true && this.isSetRegistrationId();
    boolean that_present_registrationId = true && that.isSetRegistrationId();
    if (this_present_registrationId || that_present_registrationId) {
      if (!(this_present_registrationId && that_present_registrationId))
        return false;
      if (this.registrationId != that.registrationId)
        return false;
    }

    boolean this_present_registrationCode = true && this.isSetRegistrationCode();
    boolean that_present_registrationCode = true && that.isSetRegistrationCode();
    if (this_present_registrationCode || that_present_registrationCode) {
      if (!(this_present_registrationCode && that_present_registrationCode))
        return false;
      if (!this.registrationCode.equals(that.registrationCode))
        return false;
    }

    boolean this_present_injectionOrderNumber = true && this.isSetInjectionOrderNumber();
    boolean that_present_injectionOrderNumber = true && that.isSetInjectionOrderNumber();
    if (this_present_injectionOrderNumber || that_present_injectionOrderNumber) {
      if (!(this_present_injectionOrderNumber && that_present_injectionOrderNumber))
        return false;
      if (this.injectionOrderNumber != that.injectionOrderNumber)
        return false;
    }

    boolean this_present_injectionTime = true && this.isSetInjectionTime();
    boolean that_present_injectionTime = true && that.isSetInjectionTime();
    if (this_present_injectionTime || that_present_injectionTime) {
      if (!(this_present_injectionTime && that_present_injectionTime))
        return false;
      if (!this.injectionTime.equals(that.injectionTime))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(TRMAddExtraRegistrationResponse other) {
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
    lastComparison = Boolean.valueOf(isSetRegistrationId()).compareTo(other.isSetRegistrationId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRegistrationId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.registrationId, other.registrationId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetRegistrationCode()).compareTo(other.isSetRegistrationCode());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRegistrationCode()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.registrationCode, other.registrationCode);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetInjectionOrderNumber()).compareTo(other.isSetInjectionOrderNumber());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetInjectionOrderNumber()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.injectionOrderNumber, other.injectionOrderNumber);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetInjectionTime()).compareTo(other.isSetInjectionTime());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetInjectionTime()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.injectionTime, other.injectionTime);
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
    StringBuilder sb = new StringBuilder("TRMAddExtraRegistrationResponse(");
    boolean first = true;

    sb.append("error:");
    if (this.error == null) {
      sb.append("null");
    } else {
      sb.append(this.error);
    }
    first = false;
    if (isSetRegistrationId()) {
      if (!first) sb.append(", ");
      sb.append("registrationId:");
      sb.append(this.registrationId);
      first = false;
    }
    if (isSetRegistrationCode()) {
      if (!first) sb.append(", ");
      sb.append("registrationCode:");
      if (this.registrationCode == null) {
        sb.append("null");
      } else {
        sb.append(this.registrationCode);
      }
      first = false;
    }
    if (isSetInjectionOrderNumber()) {
      if (!first) sb.append(", ");
      sb.append("injectionOrderNumber:");
      sb.append(this.injectionOrderNumber);
      first = false;
    }
    if (isSetInjectionTime()) {
      if (!first) sb.append(", ");
      sb.append("injectionTime:");
      if (this.injectionTime == null) {
        sb.append("null");
      } else {
        sb.append(this.injectionTime);
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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TRMAddExtraRegistrationResponseStandardSchemeFactory implements SchemeFactory {
    public TRMAddExtraRegistrationResponseStandardScheme getScheme() {
      return new TRMAddExtraRegistrationResponseStandardScheme();
    }
  }

  private static class TRMAddExtraRegistrationResponseStandardScheme extends StandardScheme<TRMAddExtraRegistrationResponse> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TRMAddExtraRegistrationResponse struct) throws org.apache.thrift.TException {
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
          case 2: // REGISTRATION_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.registrationId = iprot.readI64();
              struct.setRegistrationIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // REGISTRATION_CODE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.registrationCode = iprot.readString();
              struct.setRegistrationCodeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // INJECTION_ORDER_NUMBER
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.injectionOrderNumber = iprot.readI32();
              struct.setInjectionOrderNumberIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // INJECTION_TIME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.injectionTime = iprot.readString();
              struct.setInjectionTimeIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, TRMAddExtraRegistrationResponse struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.error != null) {
        oprot.writeFieldBegin(ERROR_FIELD_DESC);
        struct.error.write(oprot);
        oprot.writeFieldEnd();
      }
      if (struct.isSetRegistrationId()) {
        oprot.writeFieldBegin(REGISTRATION_ID_FIELD_DESC);
        oprot.writeI64(struct.registrationId);
        oprot.writeFieldEnd();
      }
      if (struct.registrationCode != null) {
        if (struct.isSetRegistrationCode()) {
          oprot.writeFieldBegin(REGISTRATION_CODE_FIELD_DESC);
          oprot.writeString(struct.registrationCode);
          oprot.writeFieldEnd();
        }
      }
      if (struct.isSetInjectionOrderNumber()) {
        oprot.writeFieldBegin(INJECTION_ORDER_NUMBER_FIELD_DESC);
        oprot.writeI32(struct.injectionOrderNumber);
        oprot.writeFieldEnd();
      }
      if (struct.injectionTime != null) {
        if (struct.isSetInjectionTime()) {
          oprot.writeFieldBegin(INJECTION_TIME_FIELD_DESC);
          oprot.writeString(struct.injectionTime);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TRMAddExtraRegistrationResponseTupleSchemeFactory implements SchemeFactory {
    public TRMAddExtraRegistrationResponseTupleScheme getScheme() {
      return new TRMAddExtraRegistrationResponseTupleScheme();
    }
  }

  private static class TRMAddExtraRegistrationResponseTupleScheme extends TupleScheme<TRMAddExtraRegistrationResponse> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TRMAddExtraRegistrationResponse struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      struct.error.write(oprot);
      BitSet optionals = new BitSet();
      if (struct.isSetRegistrationId()) {
        optionals.set(0);
      }
      if (struct.isSetRegistrationCode()) {
        optionals.set(1);
      }
      if (struct.isSetInjectionOrderNumber()) {
        optionals.set(2);
      }
      if (struct.isSetInjectionTime()) {
        optionals.set(3);
      }
      oprot.writeBitSet(optionals, 4);
      if (struct.isSetRegistrationId()) {
        oprot.writeI64(struct.registrationId);
      }
      if (struct.isSetRegistrationCode()) {
        oprot.writeString(struct.registrationCode);
      }
      if (struct.isSetInjectionOrderNumber()) {
        oprot.writeI32(struct.injectionOrderNumber);
      }
      if (struct.isSetInjectionTime()) {
        oprot.writeString(struct.injectionTime);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TRMAddExtraRegistrationResponse struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.error = new TError();
      struct.error.read(iprot);
      struct.setErrorIsSet(true);
      BitSet incoming = iprot.readBitSet(4);
      if (incoming.get(0)) {
        struct.registrationId = iprot.readI64();
        struct.setRegistrationIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.registrationCode = iprot.readString();
        struct.setRegistrationCodeIsSet(true);
      }
      if (incoming.get(2)) {
        struct.injectionOrderNumber = iprot.readI32();
        struct.setInjectionOrderNumberIsSet(true);
      }
      if (incoming.get(3)) {
        struct.injectionTime = iprot.readString();
        struct.setInjectionTimeIsSet(true);
      }
    }
  }

}

