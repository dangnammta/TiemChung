package service.response;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author anhn
 */
public class JSONMapper {
    public static JSONObject toJSON(Object obj) {
        JSONObject json = new JSONObject();
        Class tClass = obj.getClass();
        Field[] fields = tClass.getFields();
        int fCount = fields.length;
        for (int i = 0; i < fCount; i++) {
            Field f = fields[i];
			//Ignore static fields
			if (Modifier.isStatic(f.getModifiers()))
				continue;
            Class fieldType = f.getType();
            String fieldName = f.getName();
            Object value = null;
            try {
                value = f.get(obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            if (value == null || fieldType.isPrimitive() 
                    || Number.class.isAssignableFrom(fieldType) || Number.class.isAssignableFrom(value.getClass()) 
                    || String.class.isAssignableFrom(fieldType) || String.class.isAssignableFrom(value.getClass()) 
                    || Boolean.class.isAssignableFrom(fieldType)) {
                if (char.class.isAssignableFrom(fieldType))
                    json.put(fieldName, value.toString());
                else
                    json.put(fieldName, value);
            } else if (fieldType.isArray()) {
                Object[] array = (Object[])value;
                JSONArray jsonArray = new JSONArray();
                for (Object o : array) {
                    if (value == null || o.getClass().isPrimitive() || Number.class.isAssignableFrom(o.getClass()) || String.class.isAssignableFrom(o.getClass()) || Boolean.class.isAssignableFrom(o.getClass())) {
                        jsonArray.add(o);
                    } else 
                        jsonArray.add(toJSON(o));
                }
                json.put(fieldName, jsonArray);
            } else if (List.class.isAssignableFrom(fieldType)) {
                List array = (List)value;
                JSONArray jsonArray = new JSONArray();
                for (Object o : array) {
                    jsonArray.add(toJSON(o));
                }
                json.put(fieldName, jsonArray);
            } else if (Map.class.isAssignableFrom(fieldType)) {
                JSONObject mapObj = new JSONObject();
				Map map = (Map) value;
                mapObj.putAll(map);
				for (Object key : map.keySet()) {
					mapObj.put(key, JSONMapper.toJSON(map.get(key)));
				}
				json.put(fieldName, mapObj);
            } else {
                json.put(fieldName, JSONMapper.toJSON(value));
            }
        }
        return json;
    }
    public static Object fromJSON(Class tClass, JSONObject json) throws InstantiationException, IllegalAccessException {
         //Create an instance from default no param constructor
        Object obj = tClass.newInstance();
        
        for (Object key : json.keySet()) {
            String name = (String)key;
            Field field = null;
            try {
                field = tClass.getField(name);
            } catch (NoSuchFieldException ex) {
                continue;
            }
            Class fieldType = field.getType();
            
            Object value = json.get(key);
            if (value != null) {
                Class valueType = value.getClass();
                if (fieldType.isAssignableFrom(valueType)) {
                    field.set(obj, value);
                } else if (String.class.isAssignableFrom(valueType)) {
                    String strVal = (String)value;
                    if (String.class.isAssignableFrom(fieldType)) {
                        field.set(obj, value);
                    }
                    if (char.class.isAssignableFrom(fieldType) || Character.class.isAssignableFrom(fieldType)) {
                        if (strVal.length() > 0)
                            field.set(obj, strVal.charAt(0));
                    }
                } else if (Long.class.isAssignableFrom(valueType)) {
                    long val = ((Long)value).longValue();
                    if (byte.class.isAssignableFrom(fieldType) || Byte.class.isAssignableFrom(fieldType)) {
                        byte bVal = (byte)val;
                        field.set(obj, bVal);
                    } else if (short.class.isAssignableFrom(fieldType) || Short.class.isAssignableFrom(fieldType)) {
                        short sVal = (short)val;
                        field.set(obj, sVal);
                    } else if (int.class.isAssignableFrom(fieldType) || Integer.class.isAssignableFrom(fieldType)) {
                        int iVal = (int)val;
                        field.set(obj, iVal);
                    } else if (int.class.isAssignableFrom(fieldType) || Integer.class.isAssignableFrom(fieldType)) {
                        field.set(obj, val);
                    }
                } else if (Double.class.isAssignableFrom(valueType)) {
                    double val = ((Double)value).doubleValue();
                    if (float.class.isAssignableFrom(fieldType) || Float.class.isAssignableFrom(fieldType)) {
                        float fVal = (float)val;
                        field.set(obj, fVal);
                    } else if (double.class.isAssignableFrom(fieldType) || Double.class.isAssignableFrom(fieldType)) {
                        field.set(obj, val);
                    }
                } else if (JSONObject.class.isAssignableFrom(valueType)) {
                    if (!fieldType.isPrimitive()) {
						if (Map.class.isAssignableFrom(fieldType)) {
							Map map = new HashMap();
							Map jsonMap = (Map)value;
							for (Object subkey : jsonMap.keySet()) {
								map.put(subkey, jsonMap.get(subkey));
							}
							field.set(obj, map);
						} else {
							field.set(obj, fromJSON(fieldType, (JSONObject)value));
						}
                    }
                } else if (JSONArray.class.isAssignableFrom(valueType)) {
                    JSONArray array = (JSONArray)value;
                    Class compType = fieldType.getComponentType();
                    int objCount = array.size();
                    
                    if (fieldType.isArray()) {
                        Object fieldArray = Array.newInstance(compType, objCount);
                        for (int i = 0; i < objCount; i++) {
                            if (compType.isPrimitive() || Number.class.isAssignableFrom(compType) || String.class.isAssignableFrom(compType) || Boolean.class.isAssignableFrom(compType)) {
                                if (Long.class.isAssignableFrom(array.get(i).getClass()) && Integer.class.isAssignableFrom(compType)) {
                                    int l = ((Long)array.get(i)).intValue();
                                    Array.set(fieldArray, i, compType.cast(l));
                                } else {
                                    Array.set(fieldArray, i, compType.cast(array.get(i)));
                                }
                            } else {
                                if (array.get(i) != null) {
                                    Object compObj = fromJSON(compType, (JSONObject)array.get(i));
                                    Array.set(fieldArray, i, compType.cast(compObj));
                                } else {
                                    Array.set(fieldArray, i, null);
                                }
                            }
                        }
                        field.set(obj, fieldArray);
                    } else {
                        ArrayList fieldList = new ArrayList();                    
                        for (int i = 0; i < objCount; i++) {
                            //if (compType.isPrimitive())
                            if (Long.class.isAssignableFrom(array.get(i).getClass())) {
                                fieldList.add(array.get(i));
                            } else {
                                
                                Object compObj = fromJSON(compType, (JSONObject)array.get(i));
                                fieldList.add(i, compObj);
                            }
                        }
                        if (List.class.isAssignableFrom(fieldType)) {
                            field.set(obj, fieldList);
                        } else {
                            field.set(obj, null);
                        }
                    }
                }
            } else {
                if (!fieldType.isPrimitive()) {
                    field.set(obj, null);
                }
            }
        }
        return obj;
    }
}