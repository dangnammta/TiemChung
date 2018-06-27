/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.utils;

import com.vng.wmb.lib.json.JacksonHelper;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 *
 * @author namdv
 */
public class JsonUtils {
	private static final ObjectMapper objMapper = JacksonHelper.getInstance(false);
	private static final Logger logger = Logger.getLogger(JsonUtils.class);

	public static <T> List<T> getListObject(TypeReference<List<T>> type , String json){
		try {
			return objMapper.readValue(json, type);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	public static <T> List<T> getList(String json){
		try {
			return objMapper.readValue(json, new TypeReference<List<T>>(){});
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	public static Map<String, Object> getMap(String json){
		try {
			return objMapper.readValue(json, new TypeReference<Map<String, Object>>(){});
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

//	public static <T> T getObject(TypeReference<T> type ,String json){
//		try {
//			return objMapper.readValue(json, type);
//		} catch (Exception e) {
//			logger.error(e.getMessage());
//			return null;
//		}
//	}
	
	public static String toStringJson(Object obj){
		try {
			return objMapper.writeValueAsString(obj);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return "";
		}
	}
}
