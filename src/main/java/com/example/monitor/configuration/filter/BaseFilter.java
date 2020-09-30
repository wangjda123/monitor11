package com.example.monitor.configuration.filter;


import com.example.monitor.core.cache.IDoCache;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.Filter;

public abstract class BaseFilter implements Filter {
    @Autowired
    IDoCache cache;
}
