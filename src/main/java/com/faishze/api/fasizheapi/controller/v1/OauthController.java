package com.faishze.api.fasizheapi.controller.v1;

import com.faishze.api.fasizheapi.enums.OauthType;
import com.faishze.api.fasizheapi.global.Redis;
import com.faishze.api.fasizheapi.pojo.ao.OauthBindAO;
import com.faishze.api.fasizheapi.pojo.do0.Profile;
import com.faishze.api.fasizheapi.pojo.do0.User;
import com.faishze.api.fasizheapi.pojo.vo.OauthVO;
import com.faishze.api.fasizheapi.result.ErrorCode;
import com.faishze.api.fasizheapi.result.Result;
import com.faishze.api.fasizheapi.service.OauthService;
import com.faishze.api.fasizheapi.service.ProfileService;
import com.faishze.api.fasizheapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author masonluo
 * @date 2019/10/29 12:32 AM
 */
@RestController
public class OauthController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private OauthService oauthService;

    /**
     * 进行用户绑定
     * @param oauthID 用户openID
     * @param username 用户名
     * @param oauthBindAO 绑定所需要的用户信息
     * @return
     */
    @PostMapping("/oauth/{oauth_id}/user/{username}")
    public Result bindUser(@PathVariable("oauth_id") String oauthID,
                           @PathVariable("username") String username,
                           @RequestBody @Valid OauthBindAO oauthBindAO,
                           BindingResult errors){
        if(errors.hasErrors()){
            return Result.invalidParameterIsBlank(errors.getFieldError().getDefaultMessage());
        }
        if(!redisTemplate.hasKey(Redis.OAUTH_USER_BINDING_CODE_PREFIX + oauthBindAO.getCode())){
            return Result.fail(ErrorCode.INVALID_PARAMETER, "code校验失败");
        }
        String redisOauthID = (String) redisTemplate.opsForHash().get(Redis.OAUTH_USER_BINDING_CODE_PREFIX + oauthBindAO.getCode(), "open_id");
        Integer redisOauthTypeID = (Integer) redisTemplate.opsForHash().get(Redis.OAUTH_USER_BINDING_CODE_PREFIX + oauthBindAO.getCode(), "open_type_id");
        // TODO 做手机验证码的验证
        if(!oauthBindAO.getOauthTypeID().equals(redisOauthTypeID) || !redisOauthID.equals(oauthID)){
            return Result.fail(ErrorCode.INVALID_PARAMETER, "第三方用户类型或者是第三方openID不一致");
        }
        User user = userService.getByUsername(username);
        if(user == null){
            return Result.fail(ErrorCode.INVALID_PARAMETER_NOT_FOUND, "要绑定的用户不存在");
        }
        Profile profile = profileService.getByUserID(user.getId());
        // TODO 考虑使用线程进行头像的上传和昵称的更改
        // 查看头像存不存在，不存在上传
        if(oauthBindAO.getAvatarUrl() != null && profile.getAvatarUrl() == null){
            profileService.uploadAvatarUrl(oauthBindAO.getAvatarUrl());
        }
        // 如果绑定的用户没有昵称，且传过来昵称，则进行上传
        if(oauthBindAO.getNickName() != null && profile.getNickName() == null){
            Profile updateProfile = new Profile();
            profile.setNickName(oauthBindAO.getNickName());
            profile.setUserId(profile.getUserId());
            profileService.update(updateProfile);
        }
        OauthType oauthType = OauthType.getByOauthTypeById(redisOauthTypeID);
        if(oauthType == null){
            return Result.fail(ErrorCode.INVALID_PARAMETER, "暂不支持此第三方用户的绑定");
        }
        // 绑定用户
        boolean success = oauthService.bind(user.getId(), redisOauthID, oauthType);
        if(success){
            OauthVO oauthVO = new OauthVO(user.getUsername(), oauthID, oauthType.getOauthType());
            return Result.success(oauthVO);
        }
        // 绑定失败
        return Result.fail(ErrorCode.UNKNOWN_ERROR);
    }
}
