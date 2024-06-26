package com.gyunf.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Alex.Sun
 * @created 2021-05-29 20:33
 */
public class Params extends JSONObject {
    private static final long serialVersionUID = 1;

    public Params() {
    }

    public Params(Map<String, Object> map) {
        super(map);
    }

    public Params(boolean ordered) {
        super(ordered);
    }

    public Params(int initialCapacity) {
        super(initialCapacity);
    }

    public Params(int initialCapacity, boolean ordered) {
        super(initialCapacity, ordered);
    }

    public static Params create() {
        return new Params();
    }

    public <T> Params set(String key, T value) {
        put(key, value);
        return this;
    }

    public <T> Params set(String parent, String key, T value) {
        set((StringUtils.isEmpty(parent) ? "" : (parent + ".")) + key, value);
        return this;
    }

    public <T> Params setIfAbsent(String key, T value) {
        putIfAbsent(key, value);
        return this;
    }

    public <T> Params add(String key, T... values) {
        if (ArrayUtils.isEmpty(values)) return this;

        List<T> list = getObject(key, List.class);
        if (list == null) {
            list = Lists.newArrayList();
            put(key, list);
        }
        list.addAll(Arrays.asList(values));
        return this;
    }
}
