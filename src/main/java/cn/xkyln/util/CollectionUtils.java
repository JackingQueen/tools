package cn.xkyln.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

/**
 * 集合工具
 */
public class CollectionUtils {

    private CollectionUtils() {}

    /**
     * 使用对象序列化实现对象深复制
     * @param source 源对象
     * @param <T> 泛型方法
     * @return
     */
/*    public static <T> T deepCopy(T source) {
        T dest = null;
        try (
                ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ) {
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(source);
            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream in = new ObjectInputStream(byteIn);
            dest = (T) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return dest;
    }*/

    /**
     * 通过json序列化实现对象的深复制
     * @param source 源对象
     * @param genericClasses 如果有泛型传入泛型，没有就不传
     * @param <T> 泛型
     * @return
     */
    public static <T> T deepCopy(T source, Class<?>... genericClasses) {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(source.getClass().getTypeName());
        T data = null;
        try {
            String jsonString = mapper.writeValueAsString(source);
            if (0 == genericClasses.length) {
                data = (T) mapper.readValue(jsonString, source.getClass());
            } else {
                data = mapper.readValue(jsonString, getGenericType(source.getClass(), genericClasses));
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
}
