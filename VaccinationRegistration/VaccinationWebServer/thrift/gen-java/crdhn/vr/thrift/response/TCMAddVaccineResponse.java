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

public class TCMAddVaccineResponse implements org.apache.thrift.TBase<TCMAddVaccineResponse, TCMAddVaccineResponse._Fields>, java.io.Serializable, Cloneable, Comparable<TCMAddVaccineResponse> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TCMAddVaccineResponse");

  private static final org.apache.thrift.protocol.TField ERROR_FIELD_DESC = new org.apache.thrift.protocol.TField("error", org.apache.thrift.protocol.TType.STRUCT, (short)1);
  private static final org.apache.thrift.protocol.TField VACCINE_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("vaccineId", org.apache.thrift.protocol.TType.I32, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new TCMAddVaccineResponseStandardSchemeFactory());
    schemes.put(TupleScheme.class, new TCMAddVaccineResponseTupleSchemeFactory());
  }

  public TError error; // required
  public int vaccineId; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ERROR((short)1, "error"),
    VACCINE_ID((short)2, "vaccineId");

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
        case 2: // VACCINE_ID
          return VACCINE_ID;
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
  private static final int __VACCINEID_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private _Fields optionals[] = {_Fields.VACCINE_ID};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ERROR, new org.apache.thrift.meta_data.FieldMetaData("error", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TError.class)));
    tmpMap.put(_Fields.VACCINE_ID, new org.apache.thrift.meta_data.FieldMetaData("vaccineId", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32        , "TVID")));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TCMAddVaccineResponse.class, metaDataMap);
  }

  public TCMAddVaccineResponse() {
  }

  public TCMAddVaccineResponse(
    TError error)
  {
    this();
    this.error = error;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TCMAddVaccineResponse(TCMAddVaccineResponse other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetError()) {
      this.error = new TError(other.error);
    }
    this.vaccineId = other.vaccineId;
  }

  public TCMAddVaccineResponse deepCopy() {
    return new TCMAddVaccineResponse(this);
  }

  @Override
  public void clear() {
    this.error = null;
    setVaccineIdIsSet(false);
    this.vaccineId = 0;
  }

  public TError getError() {
    return this.error;
  }

  public TCMAddVaccineResponse setError(TError error) {
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

  public int getVaccineId() {
    return this.vaccineId;
  }

  public TCMAddVaccineResponse setVaccineId(int vaccineId) {
    this.vaccineId = vaccineId;
    setVaccineIdIsSet(true);
    return this;
  }

  public void unsetVaccineId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __VACCINEID_ISSET_ID);
  }

  /** Returns true if field vaccineId is set (has been assigned a value) and false otherwise */
  public boolean isSetVaccineId() {
    return EncodingUtils.testBit(__isset_bitfield, __VACCINEID_ISSET_ID);
  }

  public void setVaccineIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __VACCINEID_ISSET_ID, value);
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

    case VACCINE_ID:
      if (value == null) {
        unsetVaccineId();
      } else {
        setVaccineId((Integer)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ERROR:
      return getError();

    case VACCINE_ID:
      return Integer.valueOf(getVaccineId());

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
    case VACCINE_ID:
      return isSetVaccineId();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TCMAddVaccineResponse)
      return this.equals((TCMAddVaccineResponse)that);
    return false;
  }

  public boolean equals(TCMAddVaccineResponse that) {
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

    boolean this_present_vaccineId = true && this.isSetVaccineId();
    boolean that_present_vaccineId = true && that.isSetVaccineId();
    if (this_present_vaccineId || that_present_vaccineId) {
      if (!(this_present_vaccineId && that_present_vaccineId))
        return false;
      if (this.vaccineId != that.vaccineId)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(TCMAddVaccineResponse other) {
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
    lastComparison = Boolean.valueOf(isSetVaccineId()).compareTo(other.isSetVaccineId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetVaccineId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.vaccineId, other.vaccineId);
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
    StringBuilder sb = new StringBuilder("TCMAddVaccineResponse(");
    boolean first = true;

    sb.append("error:");
    if (this.error == null) {
      sb.append("null");
    } else {
      sb.append(this.error);
    }
    first = false;
    if (isSetVaccineId()) {
      if (!first) sb.append(", ");
      sb.append("vaccineId:");
      sb.append(this.vaccineId);
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

  private static class TCMAddVaccineResponseStandardSchemeFactory implements SchemeFactory {
    public TCMAddVaccineResponseStandardScheme getScheme() {
      return new TCMAddVaccineResponseStandardScheme();
    }
  }

  private static class TCMAddVaccineResponseStandardScheme extends StandardScheme<TCMAddVaccineResponse> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TCMAddVaccineResponse struct) throws org.apache.thrift.TException {
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
          case 2: // VACCINE_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.vaccineId = iprot.readI32();
              struct.setVaccineIdIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, TCMAddVaccineResponse struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.error != null) {
        oprot.writeFieldBegin(ERROR_FIELD_DESC);
        struct.error.write(oprot);
        oprot.writeFieldEnd();
      }
      if (struct.isSetVaccineId()) {
        oprot.writeFieldBegin(VACCINE_ID_FIELD_DESC);
        oprot.writeI32(struct.vaccineId);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TCMAddVaccineResponseTupleSchemeFactory implements SchemeFactory {
    public TCMAddVaccineResponseTupleScheme getScheme() {
      return new TCMAddVaccineResponseTupleScheme();
    }
  }

  private static class TCMAddVaccineResponseTupleScheme extends TupleScheme<TCMAddVaccineResponse> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TCMAddVaccineResponse struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      struct.error.write(oprot);
      BitSet optionals = new BitSet();
      if (struct.isSetVaccineId()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetVaccineId()) {
        oprot.writeI32(struct.vaccineId);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TCMAddVaccineResponse struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.error = new TError();
      struct.error.read(iprot);
      struct.setErrorIsSet(true);
      BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        struct.vaccineId = iprot.readI32();
        struct.setVaccineIdIsSet(true);
      }
    }
  }

}

