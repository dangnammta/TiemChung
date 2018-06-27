/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package crdhn.vr.thrift.data;

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

public class TInjectionOrderInfo implements org.apache.thrift.TBase<TInjectionOrderInfo, TInjectionOrderInfo._Fields>, java.io.Serializable, Cloneable, Comparable<TInjectionOrderInfo> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TInjectionOrderInfo");

  private static final org.apache.thrift.protocol.TField REGISTRATION_CODE_FIELD_DESC = new org.apache.thrift.protocol.TField("registrationCode", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField INJECTION_ORDER_NUMBER_FIELD_DESC = new org.apache.thrift.protocol.TField("injectionOrderNumber", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField INJECTION_DATE_FIELD_DESC = new org.apache.thrift.protocol.TField("injectionDate", org.apache.thrift.protocol.TType.I64, (short)3);
  private static final org.apache.thrift.protocol.TField INJECTION_TIME_FIELD_DESC = new org.apache.thrift.protocol.TField("injectionTime", org.apache.thrift.protocol.TType.STRING, (short)4);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new TInjectionOrderInfoStandardSchemeFactory());
    schemes.put(TupleScheme.class, new TInjectionOrderInfoTupleSchemeFactory());
  }

  public String registrationCode; // required
  public int injectionOrderNumber; // required
  public long injectionDate; // required
  public String injectionTime; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    REGISTRATION_CODE((short)1, "registrationCode"),
    INJECTION_ORDER_NUMBER((short)2, "injectionOrderNumber"),
    INJECTION_DATE((short)3, "injectionDate"),
    INJECTION_TIME((short)4, "injectionTime");

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
        case 1: // REGISTRATION_CODE
          return REGISTRATION_CODE;
        case 2: // INJECTION_ORDER_NUMBER
          return INJECTION_ORDER_NUMBER;
        case 3: // INJECTION_DATE
          return INJECTION_DATE;
        case 4: // INJECTION_TIME
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
  private static final int __INJECTIONORDERNUMBER_ISSET_ID = 0;
  private static final int __INJECTIONDATE_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.REGISTRATION_CODE, new org.apache.thrift.meta_data.FieldMetaData("registrationCode", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.INJECTION_ORDER_NUMBER, new org.apache.thrift.meta_data.FieldMetaData("injectionOrderNumber", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.INJECTION_DATE, new org.apache.thrift.meta_data.FieldMetaData("injectionDate", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64        , "TTIME")));
    tmpMap.put(_Fields.INJECTION_TIME, new org.apache.thrift.meta_data.FieldMetaData("injectionTime", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TInjectionOrderInfo.class, metaDataMap);
  }

  public TInjectionOrderInfo() {
    this.registrationCode = "";

    this.injectionOrderNumber = -1;

    this.injectionDate = -1L;

    this.injectionTime = "";

  }

  public TInjectionOrderInfo(
    String registrationCode,
    int injectionOrderNumber,
    long injectionDate,
    String injectionTime)
  {
    this();
    this.registrationCode = registrationCode;
    this.injectionOrderNumber = injectionOrderNumber;
    setInjectionOrderNumberIsSet(true);
    this.injectionDate = injectionDate;
    setInjectionDateIsSet(true);
    this.injectionTime = injectionTime;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TInjectionOrderInfo(TInjectionOrderInfo other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetRegistrationCode()) {
      this.registrationCode = other.registrationCode;
    }
    this.injectionOrderNumber = other.injectionOrderNumber;
    this.injectionDate = other.injectionDate;
    if (other.isSetInjectionTime()) {
      this.injectionTime = other.injectionTime;
    }
  }

  public TInjectionOrderInfo deepCopy() {
    return new TInjectionOrderInfo(this);
  }

  @Override
  public void clear() {
    this.registrationCode = "";

    this.injectionOrderNumber = -1;

    this.injectionDate = -1L;

    this.injectionTime = "";

  }

  public String getRegistrationCode() {
    return this.registrationCode;
  }

  public TInjectionOrderInfo setRegistrationCode(String registrationCode) {
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

  public TInjectionOrderInfo setInjectionOrderNumber(int injectionOrderNumber) {
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

  public long getInjectionDate() {
    return this.injectionDate;
  }

  public TInjectionOrderInfo setInjectionDate(long injectionDate) {
    this.injectionDate = injectionDate;
    setInjectionDateIsSet(true);
    return this;
  }

  public void unsetInjectionDate() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __INJECTIONDATE_ISSET_ID);
  }

  /** Returns true if field injectionDate is set (has been assigned a value) and false otherwise */
  public boolean isSetInjectionDate() {
    return EncodingUtils.testBit(__isset_bitfield, __INJECTIONDATE_ISSET_ID);
  }

  public void setInjectionDateIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __INJECTIONDATE_ISSET_ID, value);
  }

  public String getInjectionTime() {
    return this.injectionTime;
  }

  public TInjectionOrderInfo setInjectionTime(String injectionTime) {
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

    case INJECTION_DATE:
      if (value == null) {
        unsetInjectionDate();
      } else {
        setInjectionDate((Long)value);
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
    case REGISTRATION_CODE:
      return getRegistrationCode();

    case INJECTION_ORDER_NUMBER:
      return Integer.valueOf(getInjectionOrderNumber());

    case INJECTION_DATE:
      return Long.valueOf(getInjectionDate());

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
    case REGISTRATION_CODE:
      return isSetRegistrationCode();
    case INJECTION_ORDER_NUMBER:
      return isSetInjectionOrderNumber();
    case INJECTION_DATE:
      return isSetInjectionDate();
    case INJECTION_TIME:
      return isSetInjectionTime();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TInjectionOrderInfo)
      return this.equals((TInjectionOrderInfo)that);
    return false;
  }

  public boolean equals(TInjectionOrderInfo that) {
    if (that == null)
      return false;

    boolean this_present_registrationCode = true && this.isSetRegistrationCode();
    boolean that_present_registrationCode = true && that.isSetRegistrationCode();
    if (this_present_registrationCode || that_present_registrationCode) {
      if (!(this_present_registrationCode && that_present_registrationCode))
        return false;
      if (!this.registrationCode.equals(that.registrationCode))
        return false;
    }

    boolean this_present_injectionOrderNumber = true;
    boolean that_present_injectionOrderNumber = true;
    if (this_present_injectionOrderNumber || that_present_injectionOrderNumber) {
      if (!(this_present_injectionOrderNumber && that_present_injectionOrderNumber))
        return false;
      if (this.injectionOrderNumber != that.injectionOrderNumber)
        return false;
    }

    boolean this_present_injectionDate = true;
    boolean that_present_injectionDate = true;
    if (this_present_injectionDate || that_present_injectionDate) {
      if (!(this_present_injectionDate && that_present_injectionDate))
        return false;
      if (this.injectionDate != that.injectionDate)
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
  public int compareTo(TInjectionOrderInfo other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

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
    lastComparison = Boolean.valueOf(isSetInjectionDate()).compareTo(other.isSetInjectionDate());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetInjectionDate()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.injectionDate, other.injectionDate);
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
    StringBuilder sb = new StringBuilder("TInjectionOrderInfo(");
    boolean first = true;

    sb.append("registrationCode:");
    if (this.registrationCode == null) {
      sb.append("null");
    } else {
      sb.append(this.registrationCode);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("injectionOrderNumber:");
    sb.append(this.injectionOrderNumber);
    first = false;
    if (!first) sb.append(", ");
    sb.append("injectionDate:");
    sb.append(this.injectionDate);
    first = false;
    if (!first) sb.append(", ");
    sb.append("injectionTime:");
    if (this.injectionTime == null) {
      sb.append("null");
    } else {
      sb.append(this.injectionTime);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (registrationCode == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'registrationCode' was not present! Struct: " + toString());
    }
    // alas, we cannot check 'injectionOrderNumber' because it's a primitive and you chose the non-beans generator.
    // alas, we cannot check 'injectionDate' because it's a primitive and you chose the non-beans generator.
    if (injectionTime == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'injectionTime' was not present! Struct: " + toString());
    }
    // check for sub-struct validity
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

  private static class TInjectionOrderInfoStandardSchemeFactory implements SchemeFactory {
    public TInjectionOrderInfoStandardScheme getScheme() {
      return new TInjectionOrderInfoStandardScheme();
    }
  }

  private static class TInjectionOrderInfoStandardScheme extends StandardScheme<TInjectionOrderInfo> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TInjectionOrderInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // REGISTRATION_CODE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.registrationCode = iprot.readString();
              struct.setRegistrationCodeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // INJECTION_ORDER_NUMBER
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.injectionOrderNumber = iprot.readI32();
              struct.setInjectionOrderNumberIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // INJECTION_DATE
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.injectionDate = iprot.readI64();
              struct.setInjectionDateIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // INJECTION_TIME
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
      if (!struct.isSetInjectionOrderNumber()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'injectionOrderNumber' was not found in serialized data! Struct: " + toString());
      }
      if (!struct.isSetInjectionDate()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'injectionDate' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, TInjectionOrderInfo struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.registrationCode != null) {
        oprot.writeFieldBegin(REGISTRATION_CODE_FIELD_DESC);
        oprot.writeString(struct.registrationCode);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(INJECTION_ORDER_NUMBER_FIELD_DESC);
      oprot.writeI32(struct.injectionOrderNumber);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(INJECTION_DATE_FIELD_DESC);
      oprot.writeI64(struct.injectionDate);
      oprot.writeFieldEnd();
      if (struct.injectionTime != null) {
        oprot.writeFieldBegin(INJECTION_TIME_FIELD_DESC);
        oprot.writeString(struct.injectionTime);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TInjectionOrderInfoTupleSchemeFactory implements SchemeFactory {
    public TInjectionOrderInfoTupleScheme getScheme() {
      return new TInjectionOrderInfoTupleScheme();
    }
  }

  private static class TInjectionOrderInfoTupleScheme extends TupleScheme<TInjectionOrderInfo> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TInjectionOrderInfo struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeString(struct.registrationCode);
      oprot.writeI32(struct.injectionOrderNumber);
      oprot.writeI64(struct.injectionDate);
      oprot.writeString(struct.injectionTime);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TInjectionOrderInfo struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.registrationCode = iprot.readString();
      struct.setRegistrationCodeIsSet(true);
      struct.injectionOrderNumber = iprot.readI32();
      struct.setInjectionOrderNumberIsSet(true);
      struct.injectionDate = iprot.readI64();
      struct.setInjectionDateIsSet(true);
      struct.injectionTime = iprot.readString();
      struct.setInjectionTimeIsSet(true);
    }
  }

}

