package com.pond.build.remote.hotta.willpower.fallback;

import com.pond.build.remote.hotta.willpower.WillpowerRemote;
import feign.FeignException;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class WillpowerRemoteFallBackService implements WillpowerRemote {

    @Override
    public Map<String,Object> addWillpower(Object willpower) {
        throw FeignException.errorStatus("addWillpower", null);
    }

    @Override
    public Map<String,Object> editWillpower(Object willpower) {
        throw FeignException.errorStatus("editWillpower", null);
    }

    @Override
    public Map<String, Object> deleteWillpower(Object willpowerIds) {
        throw FeignException.errorStatus("deleteWillpower", null);
    }


    @Override
    public Map<String, Object> willpowerByPage(Integer page, Integer pageSize, String searchName) {
        throw FeignException.errorStatus("willpowerByPage", null);
    }


    @Override
    public Map<String, Object> willpowerById(Integer willpowerId) {
        throw FeignException.errorStatus("willpowerById", null);
    }
}
