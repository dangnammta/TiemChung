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

public enum TInjectionStatus implements org.apache.thrift.TEnum {
  TIS_REGISTED(0),
  TIS_VACCINATED(1),
  TIS_CANCELED(2);

  private final int value;

  private TInjectionStatus(int value) {
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
  public static TInjectionStatus findByValue(int value) { 
    switch (value) {
      case 0:
        return TIS_REGISTED;
      case 1:
        return TIS_VACCINATED;
      case 2:
        return TIS_CANCELED;
      default:
        return null;
    }
  }
}
