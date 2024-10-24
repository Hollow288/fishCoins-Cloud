package com.pond.build.config;

import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class ConfigChangeListener {

    @ApolloConfigChangeListener
    public void onChange(ConfigChangeEvent changeEvent) {
        System.out.println("Detected changes: " + changeEvent.changedKeys());
    }
}
