package priv.test.submitOrder;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class JSONUtils {

	private static final Log log = LogFactory.getLog(JSONUtils.class);

	@SuppressWarnings("unchecked")
	public static Map<String, Object> toMap(String json) throws HZException {
		ObjectMapper objectMapper = getObjectMapper();
		try {
			objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
			return objectMapper.readValue(json, Map.class);
		} catch (JsonParseException e) {
			log.error(new Date()+"\nJSONת���쳣\n"+json,e);
			throw new HZException(e);
		} catch (JsonMappingException e) {
			log.error(new Date()+"\nJSONת���쳣\n"+json,e);
			throw new HZException(e);
		} catch (IOException e) {
			log.error(new Date()+"\nJSONת���쳣\n"+json,e);
			throw new HZException(e);
		}
	}
	
	public static <T> List<T> toList(String json, Class<T> clazz) throws HZException {
		ObjectMapper objectMapper = getObjectMapper();
		try {
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, clazz); 
			objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
			return objectMapper.readValue(json, javaType);
		} catch (JsonParseException e) {
			log.error(new Date()+"\nJSONת���쳣\n"+json,e);
			throw new HZException(e);
		} catch (JsonMappingException e) {
			log.error(new Date()+"\nJSONת���쳣\n"+json,e);
			throw new HZException(e);
		} catch (IOException e) {
			log.error(new Date()+"\nJSONת���쳣\n"+json,e);
			throw new HZException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> toListMap(String json) throws HZException {
		ObjectMapper objectMapper = getObjectMapper();
		log.info(json);
		try {
			objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
			return objectMapper.readValue(json, List.class);
		} catch (JsonParseException e) {
			log.error(new Date()+"\nJSONת���쳣\n"+json,e);
			throw new HZException(e);
		} catch (JsonMappingException e) {
			log.error(new Date()+"\nJSONת���쳣\n"+json,e);
			throw new HZException(e);
		} catch (IOException e) {
			log.error(new Date()+"\nJSONת���쳣\n"+json,e);
			throw new HZException(e);
		}
	}

	@SuppressWarnings("deprecation")
	public static String toJSONByJackson(Object object) throws HZException {
		StringWriter writer = null;
		try {
			ObjectMapper objectMapper = getObjectMapper();
			writer = new StringWriter();
			try {
				objectMapper.getSerializationConfig().setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
				objectMapper.writeValue(writer, object);
			} catch (JsonGenerationException e) {
				log.error(new Date()+"\nJSONת���쳣",e);
				throw new HZException(e);
			} catch (JsonMappingException e) {
				log.error(new Date()+"\nJSONת���쳣",e);
				throw new HZException(e);
			} catch (IOException e) {
				log.error(new Date()+"\nJSONת���쳣",e);
				throw new HZException(e);
			}
			return writer.toString();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static <T> T toBean(String content, Class<T> targetClass) {
		if (StringUtils.isEmpty(content)) {
			return null;
		} else {
			ObjectMapper objectMapper = getObjectMapper();
			try {
				return objectMapper.readValue(content, targetClass);
			} catch (JsonParseException e) {
				log.error(new Date()+"\nJSON parse to " + targetClass.getSimpleName() + " occur JsonParseExcetion. content:" + content, e);
			} catch (JsonMappingException e) {
				log.error(new Date()+"\nJSON parse to " + targetClass.getSimpleName() + " occur JsonMappingException. content:" + content, e);
			} catch (IOException e) {
				log.error(new Date()+"\nJSON parse to " + targetClass.getSimpleName() + " occur IOException. content:" + content, e);
			}
			return null;
		}
	}
	public static ObjectMapper getObjectMapper(){
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(org.codehaus.jackson.JsonParser.Feature.ALLOW_COMMENTS, true);
        objectMapper.configure(org.codehaus.jackson.JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(org.codehaus.jackson.JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.configure(org.codehaus.jackson.JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.configure(org.codehaus.jackson.JsonParser.Feature.INTERN_FIELD_NAMES, true);
        objectMapper.configure(org.codehaus.jackson.JsonParser.Feature.CANONICALIZE_FIELD_NAMES, true);
        objectMapper.configure(org.codehaus.jackson.JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS, true);
      //  objectMapper.configure(org.codehaus.jackson.map.DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        objectMapper.configure(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
	}
}
