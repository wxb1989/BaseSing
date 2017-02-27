package com.cx.sin.utils.base;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.codehaus.jackson.map.annotate.JsonFilter;
import org.codehaus.jackson.map.ser.impl.SimpleBeanPropertyFilter;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.springframework.core.annotation.AnnotationUtils;

/**
 * 通用helper方法
 * @author XuXu
 * @version ：2015年1月8日  上午10:31:46
 */
public final class JsonUtils {
	
	private static final Logger LOGGER = Logger.getLogger(JsonUtils.class);

	private static ObjectMapper TO_MAPPER = new ObjectMapper();

	private static ObjectMapper TO_MAPPER_IGNORE = new ObjectMapper();

	private static ObjectMapper TO_MAPPER_INCLUDE = new ObjectMapper();

	private static ObjectMapper TO_MAPPER_EXCLUDE = new ObjectMapper();

	static {
		TO_MAPPER_IGNORE.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	}

	public static String toJson(Object obj) {
		try {
			TO_MAPPER.configure(Feature.USE_ANNOTATIONS, false);
			String json = TO_MAPPER.writeValueAsString(obj);
			return json;
		} catch (JsonGenerationException e) {
			LOGGER.error(e);
		} catch (JsonMappingException e) {
			LOGGER.error(e);
		} catch (IOException e) {
			LOGGER.error(e);
		}
		return null;
	}

	public static String toJson(Object obj, boolean ignore) {
		try {
			String json = TO_MAPPER_IGNORE.writeValueAsString(obj);
			return json;
		} catch (JsonGenerationException e) {
			LOGGER.error(e);
		} catch (JsonMappingException e) {
			LOGGER.error(e);
		} catch (IOException e) {
			LOGGER.error(e);
		}
		return null;
	}

	public static String toJsonIncludes(Object obj, String[] properties2Include) {
		try {
			String json = TO_MAPPER_INCLUDE.writer(
					new SimpleFilterProvider().addFilter(
							AnnotationUtils.getValue(
									AnnotationUtils.findAnnotation(
											obj.getClass(), JsonFilter.class))
									.toString(), SimpleBeanPropertyFilter
									.filterOutAllExcept(properties2Include)))
					.writeValueAsString(obj);
			return json;
		} catch (JsonGenerationException e) {
			LOGGER.error(e);
		} catch (JsonMappingException e) {
			LOGGER.error(e);
		} catch (IOException e) {
			LOGGER.error(e);
		}
		return null;
	}

	public static String toJsonExcludes(Object obj, String[] properties2Exclude) {
		try {
			String json = TO_MAPPER_EXCLUDE.writer(
					new SimpleFilterProvider().addFilter(
							AnnotationUtils.getValue(
									AnnotationUtils.findAnnotation(
											obj.getClass(), JsonFilter.class))
									.toString(), SimpleBeanPropertyFilter
									.serializeAllExcept(properties2Exclude)))
					.writeValueAsString(obj);
			return json;
		} catch (JsonGenerationException e) {
			LOGGER.error(e);
		} catch (JsonMappingException e) {
			LOGGER.error(e);
		} catch (IOException e) {
			LOGGER.error(e);
		}
		return null;
	}

	public static <T> T fromJson(String json, Class<T> valueType) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.configure(
					org.codehaus.jackson.map.DeserializationConfig.Feature.USE_ANNOTATIONS,
					false);
			T obj = mapper.readValue(json, valueType);
			return obj;
		} catch (JsonParseException e) {
			LOGGER.error(e);
		} catch (JsonMappingException e) {
			LOGGER.error(e);
		} catch (IOException e) {
			LOGGER.error(e);
		}
		return null;
	}
}
