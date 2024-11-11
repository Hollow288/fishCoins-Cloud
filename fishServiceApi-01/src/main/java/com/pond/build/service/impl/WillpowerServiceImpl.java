package com.pond.build.service.impl;

import com.pond.build.enums.HttpStatusCode;
import com.pond.build.mapper.willpower.WillpowerMapper;
import com.pond.build.model.CommonResult;
import com.pond.build.model.arms.Arms;
import com.pond.build.model.willpower.Willpower;
import com.pond.build.service.WillpowerService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class WillpowerServiceImpl implements WillpowerService {


    @Autowired
    private WillpowerMapper willpowerMapper;

    @Override
    public CommonResult<Map<String,Object>> willpowerByPage(Integer page, Integer pageSize, String searchName) {
        int offset = (page - 1) * pageSize;
        int limit = pageSize;
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", willpowerMapper.getCountWillpower(searchName));
        List<Willpower> allWillpowerInfoByPage = willpowerMapper.getWillpowerInfoByPage(offset,limit,searchName);
        resultMap.put("data", allWillpowerInfoByPage);
        resultMap.put("page", page);
        resultMap.put("pageSize", pageSize);

        System.out.println(allWillpowerInfoByPage);
        return new CommonResult<>(HttpStatusCode.OK.getCode(),"查询成功",resultMap);
    }
}
