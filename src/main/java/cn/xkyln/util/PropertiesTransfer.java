package cn.xkyln.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Map;

/***
 * 转换java bean 对象的属性
 */
public class PropertiesTransfer {
    private PropertiesTransfer() {}

    /**
     * java 对象转 Map
     * @return
     */
    public static <T> Map<String, Object> beanToMap(Object data) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = null;
        try {
            String jsonString = mapper.writeValueAsString(data);
            map = mapper.readValue(jsonString, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * map 转 java
     * @return
     */
    public static <T> T mapToBean(Map<String, Object> map, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        T data = null;
        try {
            String jsonString = mapper.writeValueAsString(map);
            data = mapper.readValue(jsonString, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * Jackson 实现
     * 将 SnakeCase 风格的对象的属性复制到 UpperCamelCase 风格的对象中
     *
     * @param vo      SnakeCase 风格的对象
     * @param poClass UpperCamelCase 风格的对象字节码对象
     * @param <T>
     * @return 返回转换后的对象
     * @throws IOException
     */
    public static <T> T voToPo(Object vo, Class<T> poClass, Class<?>... genericClasses) {
        return caseToCase(vo, poClass, genericClasses);
    }

    /**
     * 将 UpperCamelCase 风格的对象转换成 SnakeCase 风格的对象
     *
     * @param po             UpperCamelCase 风格的对象
     * @param voClass        SnakeCase 风格的对象
     * @param <T>
     * @param genericClasses 泛型对象
     * @return 转换后的对象
     * @throws IOException
     */
    public static <T> T poToVo(Object po, Class<T> voClass, Class<?>... genericClasses) {
        return caseToCase(po, voClass, genericClasses);
    }

    /**
     * 将一种风格的对象转换成另一种风格的对象
     *
     * @param obj            要转换的对象
     * @param clzz           目标对象class
     * @param genericClasses 泛型对象
     * @param <T>
     * @return 返回转换后的对象
     */
    private static <T> T caseToCase(Object obj, Class<T> clzz, Class<?>... genericClasses) {
        ObjectMapper mapper = new ObjectMapper()
                .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        T data = null;
        try {
            String json = mapper.writeValueAsString(obj);
            if (0 == genericClasses.length) {
                data = mapper.readValue(json, clzz);
            } else {
                data = mapper.readValue(json, getGenericType(clzz, genericClasses));
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return data;
    }

    /**
     * 获取集合的类型，包含泛型
     *
     * @param collectionClass 集合类对象
     * @param elementClasses  泛型的类对象
     * @return java类型
     */
    private static JavaType getGenericType(Class<?> collectionClass, Class<?>... elementClasses) {
        return new ObjectMapper().getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }



    public static<T> T snakeCaseToUpperCamelCase1(Object snakeCaseObj, Class<T> upperCamelCaseClass) throws IOException {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        String json = gson.toJson(snakeCaseObj);
        return gson.fromJson(json, upperCamelCaseClass);
    }
    public static<T> T upperCamelCaseToSnakeCase1(Object upperCamelCaseObj, Class<T> snakeCaseClass) throws IOException {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        String json = gson.toJson(upperCamelCaseObj);
        return gson.fromJson(json, snakeCaseClass);
    }
}
