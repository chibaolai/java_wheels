package com.bolly.filter;


import com.bolly.support.utils.JacksonUtils;

import java.io.Serializable;

public class JSONFormat implements IFilter {
    @Override
    public Object doFilter(Object value) {
        if (value == null)
            return null;
        else if (!(value instanceof Serializable)) {
            return null;
        }
        return JacksonUtils.marshal(value);
    }
}
