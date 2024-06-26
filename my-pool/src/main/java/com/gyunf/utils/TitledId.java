package com.gyunf.utils;


/**
 * @author Alex.Sun
 * @created 2021-05-30 17:17
 */
public interface TitledId<T> extends Id<T>, Titled {

    String getName();

    default TypeRef<T> toRef() {
        return TypeRef.<T>create().id(getId()).name(getName()).title(getTitle());
    }
}
