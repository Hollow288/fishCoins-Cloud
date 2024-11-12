package com.pond.build.controller.hotta;

import com.pond.build.aop.InjectUserDetails;
import com.pond.build.model.CommonResult;
import com.pond.build.model.TokenUser;
import com.pond.build.model.arms.Arms;
import com.pond.build.model.willpower.Willpower;
import com.pond.build.service.WillpowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/willpower")
public class WillpowerController {

    @Autowired
    private WillpowerService willpowerService;


    @PostMapping("/add-willpower")
    @PreAuthorize("hasRole('ADMIN')")
    @InjectUserDetails
    public CommonResult<Willpower> addWillpower(@RequestBody Willpower willpower, TokenUser user){
        // 这里遇到一个问题:我在前端传了一个 willpower的子表‘WillpowerClassification’,给它的主键附上值而且是字符串,这导致controller一直接不到这个请求,所以要么不赋值,要么传可以转成数字的值
        return willpowerService.addWillpower(willpower,user);
    }


    @GetMapping("/page-willpower")
    @PreAuthorize("hasRole('ADMIN')")
    public CommonResult<Map<String,Object>> willpowerByPage(@RequestParam(value = "page") Integer page,
                                                       @RequestParam(value = "page_size") Integer pageSize,
                                                       @RequestParam(value = "search_name", defaultValue = "") String searchName){
        return willpowerService.willpowerByPage(page,pageSize,searchName);
    }
}
