package com.pond.build.remote.hotta.arms.fallback;

import com.pond.build.model.CommonResult;
import com.pond.build.remote.TestRemote;
import com.pond.build.remote.hotta.arms.ArmsRemote;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ArmsRemoteFallBackService implements ArmsRemote {
    @Override
    public Map<String,Object> addArms(Object arms) {
        return null;
    }

    @Override
    public Map<String, Object> armsByPage( Integer page, Integer pageSize, String attributeType) {
        return null;
    }

    @Override
    public Map<String, Object> armsById(Integer armsId) {
        return null;
    }
}
