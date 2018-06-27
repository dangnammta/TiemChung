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

public class TVaccinationEventBasicInfo implements org.apache.thrift.TBase<TVaccinationEventBasicInfo, TVaccinationEventBasicInfo._Fields>, java.io.Serializable, Cloneable, Comparable<TVaccinationEventBasicInfo> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TVaccinationEventBasicInfo");

  private static final org.apache.thrift.protocol.TField EVENT_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("eventId", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField EVENT_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("eventName", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField EVENT_STATUS_FIELD_DESC = new org.apache.thrift.protocol.TField("eventStatus", org.apache.thrift.protocol.TType.I32, (short)3);
  private static final org.apache.thrift.protocol.TField START_TIME_REGISTRATION_FIELD_DESC = new org.apache.thrift.protocol.TField("startTimeRegistration", org.apache.thrift.protocol.TType.I64, (short)4);
  private static final org.apache.thrift.protocol.TField VACCINE_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("vaccineId", org.apache.thrift.protocol.TType.I32, (short)5);
  private static final org.apache.thrift.protocol.TField VACCINE_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("vaccineName", org.apache.thrift.protocol.TType.STRING, (short)6);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new TVaccinationEventBasicInfoStandardSchemeFactory());
    schemes.put(TupleScheme.class, new TVaccinationEventBasicInfoTupleSchemeFactory());
  }

  public int eventId; // required
  public String eventName; // required
  public int eventStatus; // required
  public long startTimeRegistration; // required
  public int vaccineId; // required
  public String vaccineName; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    EVENT_ID((short)1, "eventId"),
    EVENT_NAME((short)2, "eventName"),
    EVENT_STATUS((short)3, "eventStatus"),
    START_TIME_REGISTRATION((short)4, "startTimeRegistration"),
    VACCINE_ID((short)5, "vaccineId"),
    VACCINE_NAME((short)6, "vaccineName");

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
        case 1: // EVENT_ID
          return EVENT_ID;
        case 2: // EVENT_NAME
          return EVENT_NAME;
        case 3: // EVENT_STATUS
          return EVENT_STATUS;
        case 4: // START_TIME_REGISTRATION
          return START_TIME_REGISTRATION;
        case 5: // VACCINE_ID
          return VACCINE_ID;
        case 6: // VACCINE_NAME
          return VACCINE_NAME;
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
  private static final int __EVENTID_ISSET_ID = 0;
  private static final int __EVENTSTATUS_ISSET_ID = 1;
  private static final int __STARTTIMEREGISTRATION_ISSET_ID = 2;
  private static final int __VACCINEID_ISSET_ID = 3;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.EVENT_ID, new org.apache.thrift.meta_data.FieldMetaData("eventId", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32        , "TVEID")));
    tmpMap.put(_Fields.EVENT_NAME, new org.apache.thrift.meta_data.FieldMetaData("eventName", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.EVENT_STATUS, new org.apache.thrift.meta_data.FieldMetaData("eventStatus", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.START_TIME_REGISTRATION, new org.apache.thrift.meta_data.FieldMetaData("startTimeRegistration", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64        , "TTIME")));
    tmpMap.put(_Fields.VACCINE_ID, new org.apache.thrift.meta_data.FieldMetaData("vaccineId", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32        , "TVID")));
    tmpMap.put(_Fields.VACCINE_NAME, new org.apache.thrift.meta_data.FieldMetaData("vaccineName", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TVaccinationEventBasicInfo.class, metaDataMap);
  }

  public TVaccinationEventBasicInfo() {
    this.eventId = -1;

    this.eventName = "";

    this.eventStatus = 2;

    this.startTimeRegistration = -1L;

    this.vaccineId = -1;

    this.vaccineName = "";

  }

  public TVaccinationEventBasicInfo(
    int eventId,
    String eventName,
    int eventStatus,
    long startTimeRegistration,
    int vaccineId,
    String vaccineName)
  {
    this();
    this.eventId = eventId;
    setEventIdIsSet(true);
    this.eventName = eventName;
    this.eventStatus = eventStatus;
    setEventStatusIsSet(true);
    this.startTimeRegistration = startTimeRegistration;
    setStartTimeRegistrationIsSet(true);
    this.vaccineId = vaccineId;
    setVaccineIdIsSet(true);
    this.vaccineName = vaccineName;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TVaccinationEventBasicInfo(TVaccinationEventBasicInfo other) {
    __isset_bitfield = other.__isset_bitfield;
    this.eventId = other.eventId;
    if (other.isSetEventName()) {
      this.eventName = other.eventName;
    }
    this.eventStatus = other.eventStatus;
    this.startTimeRegistration = other.startTimeRegistration;
    this.vaccineId = other.vaccineId;
    if (other.isSetVaccineName()) {
      this.vaccineName = other.vaccineName;
    }
  }

  public TVaccinationEventBasicInfo deepCopy() {
    return new TVaccinationEventBasicInfo(this);
  }

  @Override
  public void clear() {
    this.eventId = -1;

    this.eventName = "";

    this.eventStatus = 2;

    this.startTimeRegistration = -1L;

    this.vaccineId = -1;

    this.vaccineName = "";

  }

  public int getEventId() {
    return this.eventId;
  }

  public TVaccinationEventBasicInfo setEventId(int eventId) {
    this.eventId = eventId;
    setEventIdIsSet(true);
    return this;
  }

  public void unsetEventId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __EVENTID_ISSET_ID);
  }

  /** Returns true if field eventId is set (has been assigned a value) and false otherwise */
  public boolean isSetEventId() {
    return EncodingUtils.testBit(__isset_bitfield, __EVENTID_ISSET_ID);
  }

  public void setEventIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __EVENTID_ISSET_ID, value);
  }

  public String getEventName() {
    return this.eventName;
  }

  public TVaccinationEventBasicInfo setEventName(String eventName) {
    this.eventName = eventName;
    return this;
  }

  public void unsetEventName() {
    this.eventName = null;
  }

  /** Returns true if field eventName is set (has been assigned a value) and false otherwise */
  public boolean isSetEventName() {
    return this.eventName != null;
  }

  public void setEventNameIsSet(boolean value) {
    if (!value) {
      this.eventName = null;
    }
  }

  public int getEventStatus() {
    return this.eventStatus;
  }

  public TVaccinationEventBasicInfo setEventStatus(int eventStatus) {
    this.eventStatus = eventStatus;
    setEventStatusIsSet(true);
    return this;
  }

  public void unsetEventStatus() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __EVENTSTATUS_ISSET_ID);
  }

  /** Returns true if field eventStatus is set (has been assigned a value) and false otherwise */
  public boolean isSetEventStatus() {
    return EncodingUtils.testBit(__isset_bitfield, __EVENTSTATUS_ISSET_ID);
  }

  public void setEventStatusIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __EVENTSTATUS_ISSET_ID, value);
  }

  public long getStartTimeRegistration() {
    return this.startTimeRegistration;
  }

  public TVaccinationEventBasicInfo setStartTimeRegistration(long startTimeRegistration) {
    this.startTimeRegistration = startTimeRegistration;
    setStartTimeRegistrationIsSet(true);
    return this;
  }

  public void unsetStartTimeRegistration() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __STARTTIMEREGISTRATION_ISSET_ID);
  }

  /** Returns true if field startTimeRegistration is set (has been assigned a value) and false otherwise */
  public boolean isSetStartTimeRegistration() {
    return EncodingUtils.testBit(__isset_bitfield, __STARTTIMEREGISTRATION_ISSET_ID);
  }

  public void setStartTimeRegistrationIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __STARTTIMEREGISTRATION_ISSET_ID, value);
  }

  public int getVaccineId() {
    return this.vaccineId;
  }

  public TVaccinationEventBasicInfo setVaccineId(int vaccineId) {
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

  public String getVaccineName() {
    return this.vaccineName;
  }

  public TVaccinationEventBasicInfo setVaccineName(String vaccineName) {
    this.vaccineName = vaccineName;
    return this;
  }

  public void unsetVaccineName() {
    this.vaccineName = null;
  }

  /** Returns true if field vaccineName is set (has been assigned a value) and false otherwise */
  public boolean isSetVaccineName() {
    return this.vaccineName != null;
  }

  public void setVaccineNameIsSet(boolean value) {
    if (!value) {
      this.vaccineName = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case EVENT_ID:
      if (value == null) {
        unsetEventId();
      } else {
        setEventId((Integer)value);
      }
      break;

    case EVENT_NAME:
      if (value == null) {
        unsetEventName();
      } else {
        setEventName((String)value);
      }
      break;

    case EVENT_STATUS:
      if (value == null) {
        unsetEventStatus();
      } else {
        setEventStatus((Integer)value);
      }
      break;

    case START_TIME_REGISTRATION:
      if (value == null) {
        unsetStartTimeRegistration();
      } else {
        setStartTimeRegistration((Long)value);
      }
      break;

    case VACCINE_ID:
      if (value == null) {
        unsetVaccineId();
      } else {
        setVaccineId((Integer)value);
      }
      break;

    case VACCINE_NAME:
      if (value == null) {
        unsetVaccineName();
      } else {
        setVaccineName((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case EVENT_ID:
      return Integer.valueOf(getEventId());

    case EVENT_NAME:
      return getEventName();

    case EVENT_STATUS:
      return Integer.valueOf(getEventStatus());

    case START_TIME_REGISTRATION:
      return Long.valueOf(getStartTimeRegistration());

    case VACCINE_ID:
      return Integer.valueOf(getVaccineId());

    case VACCINE_NAME:
      return getVaccineName();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case EVENT_ID:
      return isSetEventId();
    case EVENT_NAME:
      return isSetEventName();
    case EVENT_STATUS:
      return isSetEventStatus();
    case START_TIME_REGISTRATION:
      return isSetStartTimeRegistration();
    case VACCINE_ID:
      return isSetVaccineId();
    case VACCINE_NAME:
      return isSetVaccineName();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TVaccinationEventBasicInfo)
      return this.equals((TVaccinationEventBasicInfo)that);
    return false;
  }

  public boolean equals(TVaccinationEventBasicInfo that) {
    if (that == null)
      return false;

    boolean this_present_eventId = true;
    boolean that_present_eventId = true;
    if (this_present_eventId || that_present_eventId) {
      if (!(this_present_eventId && that_present_eventId))
        return false;
      if (this.eventId != that.eventId)
        return false;
    }

    boolean this_present_eventName = true && this.isSetEventName();
    boolean that_present_eventName = true && that.isSetEventName();
    if (this_present_eventName || that_present_eventName) {
      if (!(this_present_eventName && that_present_eventName))
        return false;
      if (!this.eventName.equals(that.eventName))
        return false;
    }

    boolean this_present_eventStatus = true;
    boolean that_present_eventStatus = true;
    if (this_present_eventStatus || that_present_eventStatus) {
      if (!(this_present_eventStatus && that_present_eventStatus))
        return false;
      if (this.eventStatus != that.eventStatus)
        return false;
    }

    boolean this_present_startTimeRegistration = true;
    boolean that_present_startTimeRegistration = true;
    if (this_present_startTimeRegistration || that_present_startTimeRegistration) {
      if (!(this_present_startTimeRegistration && that_present_startTimeRegistration))
        return false;
      if (this.startTimeRegistration != that.startTimeRegistration)
        return false;
    }

    boolean this_present_vaccineId = true;
    boolean that_present_vaccineId = true;
    if (this_present_vaccineId || that_present_vaccineId) {
      if (!(this_present_vaccineId && that_present_vaccineId))
        return false;
      if (this.vaccineId != that.vaccineId)
        return false;
    }

    boolean this_present_vaccineName = true && this.isSetVaccineName();
    boolean that_present_vaccineName = true && that.isSetVaccineName();
    if (this_present_vaccineName || that_present_vaccineName) {
      if (!(this_present_vaccineName && that_present_vaccineName))
        return false;
      if (!this.vaccineName.equals(that.vaccineName))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(TVaccinationEventBasicInfo other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetEventId()).compareTo(other.isSetEventId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetEventId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.eventId, other.eventId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetEventName()).compareTo(other.isSetEventName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetEventName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.eventName, other.eventName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetEventStatus()).compareTo(other.isSetEventStatus());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetEventStatus()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.eventStatus, other.eventStatus);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetStartTimeRegistration()).compareTo(other.isSetStartTimeRegistration());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetStartTimeRegistration()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.startTimeRegistration, other.startTimeRegistration);
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
    lastComparison = Boolean.valueOf(isSetVaccineName()).compareTo(other.isSetVaccineName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetVaccineName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.vaccineName, other.vaccineName);
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
    StringBuilder sb = new StringBuilder("TVaccinationEventBasicInfo(");
    boolean first = true;

    sb.append("eventId:");
    sb.append(this.eventId);
    first = false;
    if (!first) sb.append(", ");
    sb.append("eventName:");
    if (this.eventName == null) {
      sb.append("null");
    } else {
      sb.append(this.eventName);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("eventStatus:");
    sb.append(this.eventStatus);
    first = false;
    if (!first) sb.append(", ");
    sb.append("startTimeRegistration:");
    sb.append(this.startTimeRegistration);
    first = false;
    if (!first) sb.append(", ");
    sb.append("vaccineId:");
    sb.append(this.vaccineId);
    first = false;
    if (!first) sb.append(", ");
    sb.append("vaccineName:");
    if (this.vaccineName == null) {
      sb.append("null");
    } else {
      sb.append(this.vaccineName);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // alas, we cannot check 'eventId' because it's a primitive and you chose the non-beans generator.
    if (eventName == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'eventName' was not present! Struct: " + toString());
    }
    // alas, we cannot check 'eventStatus' because it's a primitive and you chose the non-beans generator.
    // alas, we cannot check 'startTimeRegistration' because it's a primitive and you chose the non-beans generator.
    // alas, we cannot check 'vaccineId' because it's a primitive and you chose the non-beans generator.
    if (vaccineName == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'vaccineName' was not present! Struct: " + toString());
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

  private static class TVaccinationEventBasicInfoStandardSchemeFactory implements SchemeFactory {
    public TVaccinationEventBasicInfoStandardScheme getScheme() {
      return new TVaccinationEventBasicInfoStandardScheme();
    }
  }

  private static class TVaccinationEventBasicInfoStandardScheme extends StandardScheme<TVaccinationEventBasicInfo> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TVaccinationEventBasicInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // EVENT_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.eventId = iprot.readI32();
              struct.setEventIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // EVENT_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.eventName = iprot.readString();
              struct.setEventNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // EVENT_STATUS
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.eventStatus = iprot.readI32();
              struct.setEventStatusIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // START_TIME_REGISTRATION
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.startTimeRegistration = iprot.readI64();
              struct.setStartTimeRegistrationIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // VACCINE_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.vaccineId = iprot.readI32();
              struct.setVaccineIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // VACCINE_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.vaccineName = iprot.readString();
              struct.setVaccineNameIsSet(true);
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
      if (!struct.isSetEventId()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'eventId' was not found in serialized data! Struct: " + toString());
      }
      if (!struct.isSetEventStatus()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'eventStatus' was not found in serialized data! Struct: " + toString());
      }
      if (!struct.isSetStartTimeRegistration()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'startTimeRegistration' was not found in serialized data! Struct: " + toString());
      }
      if (!struct.isSetVaccineId()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'vaccineId' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, TVaccinationEventBasicInfo struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(EVENT_ID_FIELD_DESC);
      oprot.writeI32(struct.eventId);
      oprot.writeFieldEnd();
      if (struct.eventName != null) {
        oprot.writeFieldBegin(EVENT_NAME_FIELD_DESC);
        oprot.writeString(struct.eventName);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(EVENT_STATUS_FIELD_DESC);
      oprot.writeI32(struct.eventStatus);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(START_TIME_REGISTRATION_FIELD_DESC);
      oprot.writeI64(struct.startTimeRegistration);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(VACCINE_ID_FIELD_DESC);
      oprot.writeI32(struct.vaccineId);
      oprot.writeFieldEnd();
      if (struct.vaccineName != null) {
        oprot.writeFieldBegin(VACCINE_NAME_FIELD_DESC);
        oprot.writeString(struct.vaccineName);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TVaccinationEventBasicInfoTupleSchemeFactory implements SchemeFactory {
    public TVaccinationEventBasicInfoTupleScheme getScheme() {
      return new TVaccinationEventBasicInfoTupleScheme();
    }
  }

  private static class TVaccinationEventBasicInfoTupleScheme extends TupleScheme<TVaccinationEventBasicInfo> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TVaccinationEventBasicInfo struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeI32(struct.eventId);
      oprot.writeString(struct.eventName);
      oprot.writeI32(struct.eventStatus);
      oprot.writeI64(struct.startTimeRegistration);
      oprot.writeI32(struct.vaccineId);
      oprot.writeString(struct.vaccineName);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TVaccinationEventBasicInfo struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.eventId = iprot.readI32();
      struct.setEventIdIsSet(true);
      struct.eventName = iprot.readString();
      struct.setEventNameIsSet(true);
      struct.eventStatus = iprot.readI32();
      struct.setEventStatusIsSet(true);
      struct.startTimeRegistration = iprot.readI64();
      struct.setStartTimeRegistrationIsSet(true);
      struct.vaccineId = iprot.readI32();
      struct.setVaccineIdIsSet(true);
      struct.vaccineName = iprot.readString();
      struct.setVaccineNameIsSet(true);
    }
  }

}

