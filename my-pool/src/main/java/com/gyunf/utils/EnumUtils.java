package com.gyunf.utils;

import com.gyunf.utils.Id;
import com.google.common.base.Function;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ClassUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

@Slf4j
@SuppressWarnings({"unchecked", "unsafe"})
public abstract class EnumUtils extends org.apache.commons.lang3.EnumUtils {

    private static Map<String, Class> keyMap = Maps.newConcurrentMap();

    private EnumUtils() {
    }

    public static <V, E extends Id<V>> Map<V, E> createValuedEnumMap(Class<E> enumClass) {
        return createValuedEnumMap(enumClass, input -> input.getId());
    }

    public static <V, E extends Id<V>> Map<V, E> createValuedEnumMap(Class<E> enumClass, Function<E, V> func) {
        registerEnum((Class<? extends Enum>) enumClass);
        return Collections.unmodifiableMap(Maps.uniqueIndex(Arrays.asList(enumClass.getEnumConstants()), func));
    }

    public static void registerEnum(Class<? extends Enum>... enumClasses) {
        for (Class enumClass : enumClasses) {
            String key = StringUtils.lowerCase(ClassUtils.getShortName(enumClass));
            if (keyMap.containsKey(key)) {
                log.warn("'" + key + "' already registered with '" + keyMap.get(key).getName() + "', replaced with : " + enumClass.getName());
            }
            keyMap.put(key, enumClass);
        }
    }

    public static Class<? extends Enum> getEnum(String key) throws ClassNotFoundException {
        Class clz = keyMap.get(StringUtils.lowerCase(key));
        if (clz == null) throw new ClassNotFoundException();
        return clz;
    }
}
