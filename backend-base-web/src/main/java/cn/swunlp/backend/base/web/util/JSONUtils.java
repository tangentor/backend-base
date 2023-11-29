package cn.swunlp.backend.base.web.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 功能描述:
 *	JSON操作
 * @author TangXi
 * @date 2021/10/29 下午10:38
 */
public class JSONUtils {

	private static final ObjectMapper OP = new ObjectMapper();

	private JSONUtils(){}

	@SneakyThrows
	public static String toJson(Object object){
		return OP.writeValueAsString(object);
	}

	@SneakyThrows
	public static<T> T toObject(String json, Class<T> valueType){
		return OP.readValue(json, valueType);
	}

	@SneakyThrows
	public static<T> List<T> toArray(String json, Class<T> valueType){
		List<?> list = OP.readValue(json, List.class);
		return list.stream().map(o -> OP.convertValue(o, valueType)).collect(Collectors.toList());
	}

	@SneakyThrows
	public static<T> T toObject(Object obj, Class<T> valueType){
		// 此时Object为LinkedHashMap
		if(obj instanceof String){
			return toObject(obj.toString(), valueType);
		} else {
			return toObject(toJson(obj),valueType);
		}
	}
}
