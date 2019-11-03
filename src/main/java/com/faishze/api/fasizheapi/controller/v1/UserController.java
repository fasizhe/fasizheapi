package com.faishze.api.fasizheapi.controller.v1;

import com.faishze.api.fasizheapi.exception.ValidateParamException;
import com.faishze.api.fasizheapi.pojo.ao.RegisterAO;
import com.faishze.api.fasizheapi.pojo.dto.UserDTO;
import com.faishze.api.fasizheapi.pojo.vo.UserVO;
import com.faishze.api.fasizheapi.result.Result;
import com.faishze.api.fasizheapi.service.UserService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author masonluo
 * @date 2019/10/23 11:22 PM
 */
@RestController
public class UserController {

    private final UserService userService;

    private final Mapper dozerBeanMapper;

    @Autowired
    public UserController(Mapper dozerBeanMapper, UserService userService) {
        this.dozerBeanMapper = dozerBeanMapper;
        this.userService = userService;
    }

    /**
     * @param registerAO registerAO, 具体看类
     * @param errors     参数校验Error
     */
    @PostMapping("/user")
    public Object register(@RequestBody @Valid RegisterAO registerAO, Errors errors) {
        if (errors.hasErrors()) {
            throw new ValidateParamException(errors.getFieldErrors());
        }
        // TODO 进行手机号验证码的验证
        Result result = userService.register(registerAO);
        if (result.isSuccess()) {
            UserDTO userDTO = (UserDTO) result.getData();
            UserVO userVO = dozerBeanMapper.map(userDTO, UserVO.class);
            return Result.success(userVO);
        }
        return result;
    }
}
