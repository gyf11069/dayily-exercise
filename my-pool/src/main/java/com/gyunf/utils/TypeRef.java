package com.gyunf.utils;

import com.gyunf.utils.Params;

import lombok.Data;

import java.io.Serializable;

/**
 * 对象引用
 *
 * @author Alex.Sun
 * @created 2021-06-01 09:23
 */
@Data
//"对象引用"
public class TypeRef<T> implements TitledId<T>, Serializable {
    private static final long serialVersionUID = 1;

    public static <T> TypeRef<T> create() {
        return new TypeRef<T>();
    }


    private T id;

    public TypeRef<T> id(T id) {
        setId(id);
        return this;
    }


    private String name;

    public TypeRef<T> name(String name) {
        setName(name);
        return this;
    }


    private String title;

    public TypeRef<T> title(String title) {
        setTitle(title);
        return this;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    //"其他属性"
    private Params params;

    public Params getParams() {
        return params = (params == null ? Params.create() : params);
    }

    public TypeRef<T> set(String key, Object value) {
        getParams().set(key, value);
        return this;
    }
}
