package com.gyunf.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface TitledIdInt extends TitledId<Integer> {
    @JsonIgnore
    default int getIdInt() {
        return getId() == null ? 0 : getId().intValue();
    }
}
