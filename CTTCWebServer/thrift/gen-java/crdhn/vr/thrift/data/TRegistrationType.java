/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package crdhn.vr.thrift.data;


import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

public enum TRegistrationType implements org.apache.thrift.TEnum {
  TRT_ONLINE(0),
  TRT_MANUAL(1);

  private final int value;

  private TRegistrationType(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  public static TRegistrationType findByValue(int value) { 
    switch (value) {
      case 0:
        return TRT_ONLINE;
      case 1:
        return TRT_MANUAL;
      default:
        return null;
    }
  }
}
