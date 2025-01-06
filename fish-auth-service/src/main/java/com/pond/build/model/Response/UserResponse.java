package com.pond.build.model.Response;

import com.pond.build.utils.CommonUtil;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
public class UserResponse {
    //js中最长数字17位数字 改string吧 隔壁的User实体类一般用去接数据,java的long比较长,就不改了
    private String userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String phoneNumber;
    /**
     * 用户性别（0女，1男，2未知）
     */
    private String gender;
    /**
     * 头像
     */
    private String avatarUrl;
    /**
     * 简介
     */
    private String biography;
    /**
     *
     */
    private String githubId;
    /**
     *
     */
    private String githubUrl;

    /**
     * 角色
     */
    private List<String> roles;

    public String getAvatarUrl() {
        return CommonUtil.fileUrlEncoderChance(avatarUrl);
    }
}
