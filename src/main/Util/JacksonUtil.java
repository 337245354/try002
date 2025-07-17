package Util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JacksonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 将对象转换为 JSON 字符串
     * @param object 要转换的对象
     * @return JSON 字符串
     */
    public static String toJsonString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("转换 JSON 字符串时发生错误", e);
        }
    }

    /**
     * 从 JSON 字符串反序列化为 Java 对象
     * @param jsonString JSON 字符串
     * @param clazz 目标对象的类类型
     * @return 反序列化的 Java 对象
     */
    public static <T> T fromJsonString(String jsonString, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonString, clazz);
        } catch (IOException e) {
            throw new RuntimeException("解析 JSON 字符串时发生错误", e);
        }
    }

    /**
     * 从 JSON 字符串反序列化为复杂的 Java 对象（如泛型类型）
     * @param jsonString JSON 字符串
     * @param typeReference 泛型类型引用
     * @return 反序列化的 Java 对象
     */
    public static <T> T fromJsonString(String jsonString, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(jsonString, typeReference);
        } catch (IOException e) {
            throw new RuntimeException("解析 JSON 字符串时发生错误", e);
        }
    }

    /**
     * 从文件中读取 JSON 并转换为 Java 对象
     * @param file 文件对象
     * @param clazz 目标对象的类类型
     * @return 反序列化的 Java 对象
     */
    public static <T> T readJsonFromFile(File file, Class<T> clazz) {
        try {
            return objectMapper.readValue(file, clazz);
        } catch (IOException e) {
            throw new RuntimeException("从文件读取 JSON 时发生错误", e);
        }
    }

    /**
     * 将 Java 对象写入 JSON 文件
     * @param file 文件对象
     * @param object 要写入的对象
     */
    public static void writeJsonToFile(File file, Object object) {
        try {
            objectMapper.writeValue(file, object);
        } catch (IOException e) {
            throw new RuntimeException("写入 JSON 到文件时发生错误", e);
        }
    }
}

