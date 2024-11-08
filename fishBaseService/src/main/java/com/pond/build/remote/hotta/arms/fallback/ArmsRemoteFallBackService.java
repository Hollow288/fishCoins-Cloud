package com.pond.build.remote.hotta.arms.fallback;

import com.pond.build.remote.hotta.arms.ArmsRemote;
import feign.FeignException;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ArmsRemoteFallBackService implements ArmsRemote {
    @Override
    public Map<String,Object> addArms(Object arms) {
        throw FeignException.errorStatus("addArms", null);
    }

    @Override
    public Map<String,Object> editArms(Object arms) {
        throw FeignException.errorStatus("editArms", null);
    }

    @Override
    public Map<String, Object> deleteArms(Object armsIds) {
        throw FeignException.errorStatus("deleteArms", null);
    }

    @Override
    public Map<String, Object> armsByPage( Integer page, Integer pageSize, String attributeType) {
        throw FeignException.errorStatus("armsByPage", null);
    }

    @Override
    public Map<String, Object> armsById(Integer armsId) {
        throw FeignException.errorStatus("armsById", null);
    }
}
