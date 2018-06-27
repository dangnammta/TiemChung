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

public class TVaccineInfo implements org.apache.thrift.TBase<TVaccineInfo, TVaccineInfo._Fields>, java.io.Serializable, Cloneable, Comparable<TVaccineInfo> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TVaccineInfo");

  private static final org.apache.thrift.protocol.TField VACCINE_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("vaccineId", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("name", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField DESCRIPTION_FIELD_DESC = new org.apache.thrift.protocol.TField("description", org.apache.thrift.protocol.TType.STRING, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new TVaccineInfoStandardSchemeFactory());
    schemes.put(TupleScheme.class, new TVaccineInfoTupleSchemeFactory());
  }

  public int vaccineId; // required
  public String name; // required
  public String description; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    VACCINE_ID((short)1, "vaccineId"),
    NAME((short)2, "name"),
    DESCRIPTION((short)3, "description");

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
        case 1: // VACCINE_ID
          return VACCINE_ID;
        case 2: // NAME
          return NAME;
        case 3: // DESCRIPTION
          return DESCRIPTION;
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
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.VACCINE_ID, new org.apache.thrift.meta_data.FieldMetaData("vaccineId", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32        , "TVID")));
    tmpMap.put(_Fields.NAME, new org.apache.thrift.meta_data.FieldMetaData("name", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.DESCRIPTION, new org.apache.thrift.meta_data.FieldMetaData("description", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TVaccineInfo.class, metaDataMap);
  }

  public TVaccineInfo() {
    this.vaccineId = -1;

    this.name = "";

    this.description = "";

  }

  public TVaccineInfo(
    int vaccineId,
    String name,
    String description)
  {
    this();
    this.vaccineId = vaccineId;
    setVaccineIdIsSet(true);
    this.name = name;
    this.description = description;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TVaccineInfo(TVaccineInfo other) {
    __isset_bitfield = other.__isset_bitfield;
    this.vaccineId = other.vaccineId;
    if (other.isSetName()) {
      this.name = other.name;
    }
    if (other.isSetDescription()) {
      this.description = other.description;
    }
  }

  public TVaccineInfo deepCopy() {
    return new TVaccineInfo(this);
  }

  @Override
  public void clear() {
    this.vaccineId = -1;

    this.name = "";

    this.description = "";

  }

  public int getVaccineId() {
    return this.vaccineId;
  }

  public TVaccineInfo setVaccineId(int vaccineId) {
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

  public String getName() {
    return this.name;
  }

  public TVaccineInfo setName(String name) {
    this.name = name;
    return this;
  }

  public void unsetName() {
    this.name = null;
  }

  /** Returns true if field name is set (has been assigned a value) and false otherwise */
  public boolean isSetName() {
    return this.name != null;
  }

  public void setNameIsSet(boolean value) {
    if (!value) {
      this.name = null;
    }
  }

  public String getDescription() {
    return this.description;
  }

  public TVaccineInfo setDescription(String description) {
    this.description = description;
    return this;
  }

  public void unsetDescription() {
    this.description = null;
  }

  /** Returns true if field description is set (has been assigned a value) and false otherwise */
  public boolean isSetDescription() {
    return this.description != null;
  }

  public void setDescriptionIsSet(boolean value) {
    if (!value) {
      this.description = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case VACCINE_ID:
      if (value == null) {
        unsetVaccineId();
      } else {
        setVaccineId((Integer)value);
      }
      break;

    case NAME:
      if (value == null) {
        unsetName();
      } else {
        setName((String)value);
      }
      break;

    case DESCRIPTION:
      if (value == null) {
        unsetDescription();
      } else {
        setDescription((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case VACCINE_ID:
      return Integer.valueOf(getVaccineId());

    case NAME:
      return getName();

    case DESCRIPTION:
      return getDescription();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case VACCINE_ID:
      return isSetVaccineId();
    case NAME:
      return isSetName();
    case DESCRIPTION:
      return isSetDescription();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TVaccineInfo)
      return this.equals((TVaccineInfo)that);
    return false;
  }

  public boolean equals(TVaccineInfo that) {
    if (that == null)
      return false;

    boolean this_present_vaccineId = true;
    boolean that_present_vaccineId = true;
    if (this_present_vaccineId || that_present_vaccineId) {
      if (!(this_present_vaccineId && that_present_vaccineId))
        return false;
      if (this.vaccineId != that.vaccineId)
        return false;
    }

    boolean this_present_name = true && this.isSetName();
    boolean that_present_name = true && that.isSetName();
    if (this_present_name || that_present_name) {
      if (!(this_present_name && that_present_name))
        return false;
      if (!this.name.equals(that.name))
        return false;
    }

    boolean this_present_description = true && this.isSetDescription();
    boolean that_present_description = true && that.isSetDescription();
    if (this_present_description || that_present_description) {
      if (!(this_present_description && that_present_description))
        return false;
      if (!this.description.equals(that.description))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(TVaccineInfo other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

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
    lastComparison = Boolean.valueOf(isSetName()).compareTo(other.isSetName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.name, other.name);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetDescription()).compareTo(other.isSetDescription());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDescription()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.description, other.description);
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
    StringBuilder sb = new StringBuilder("TVaccineInfo(");
    boolean first = true;

    sb.append("vaccineId:");
    sb.append(this.vaccineId);
    first = false;
    if (!first) sb.append(", ");
    sb.append("name:");
    if (this.name == null) {
      sb.append("null");
    } else {
      sb.append(this.name);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("description:");
    if (this.description == null) {
      sb.append("null");
    } else {
      sb.append(this.description);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // alas, we cannot check 'vaccineId' because it's a primitive and you chose the non-beans generator.
    if (name == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'name' was not present! Struct: " + toString());
    }
    if (description == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'description' was not present! Struct: " + toString());
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

  private static class TVaccineInfoStandardSchemeFactory implements SchemeFactory {
    public TVaccineInfoStandardScheme getScheme() {
      return new TVaccineInfoStandardScheme();
    }
  }

  private static class TVaccineInfoStandardScheme extends StandardScheme<TVaccineInfo> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TVaccineInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // VACCINE_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.vaccineId = iprot.readI32();
              struct.setVaccineIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.name = iprot.readString();
              struct.setNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // DESCRIPTION
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.description = iprot.readString();
              struct.setDescriptionIsSet(true);
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
      if (!struct.isSetVaccineId()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'vaccineId' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, TVaccineInfo struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(VACCINE_ID_FIELD_DESC);
      oprot.writeI32(struct.vaccineId);
      oprot.writeFieldEnd();
      if (struct.name != null) {
        oprot.writeFieldBegin(NAME_FIELD_DESC);
        oprot.writeString(struct.name);
        oprot.writeFieldEnd();
      }
      if (struct.description != null) {
        oprot.writeFieldBegin(DESCRIPTION_FIELD_DESC);
        oprot.writeString(struct.description);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TVaccineInfoTupleSchemeFactory implements SchemeFactory {
    public TVaccineInfoTupleScheme getScheme() {
      return new TVaccineInfoTupleScheme();
    }
  }

  private static class TVaccineInfoTupleScheme extends TupleScheme<TVaccineInfo> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TVaccineInfo struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeI32(struct.vaccineId);
      oprot.writeString(struct.name);
      oprot.writeString(struct.description);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TVaccineInfo struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.vaccineId = iprot.readI32();
      struct.setVaccineIdIsSet(true);
      struct.name = iprot.readString();
      struct.setNameIsSet(true);
      struct.description = iprot.readString();
      struct.setDescriptionIsSet(true);
    }
  }

}

