package com.faishze.api.fasizheapi.controller.v1;

import com.faishze.api.fasizheapi.pojo.ao.UserAO;
import com.faishze.api.fasizheapi.pojo.dto.UserDTO;
import com.faishze.api.fasizheapi.pojo.vo.UserVO;
import com.faishze.api.fasizheapi.result.ErrorCode;
import com.faishze.api.fasizheapi.result.ErrorResponse;
import com.faishze.api.fasizheapi.result.Result;
import com.faishze.api.fasizheapi.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

/**
 * 描述:
 *
 * @author xhsf
 * @email 827032783@qq.com
 * @create 2019-10-9
 */
@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 创建一个用户
     *
     * @param userAO UserAO
     * @return UserVO
     */
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Object post(@RequestBody @Validated UserAO userAO) {
        Result<UserDTO> result = userService.saveUser(userAO);
        if (!result.isSuccess()) {
            ErrorResponse errorResponse =
                    new ErrorResponse(ErrorCode.UNKNOWN_ERROR.getError(), ErrorCode.UNKNOWN_ERROR.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        UserDTO userDTO = result.getData();
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userDTO, userVO);
        return userVO;
    }

    /**
     * 获取一个用户
     *
     * @param id 用户id
     * @return UserVO
     */
    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Object get(
            @PathVariable
            @Min(message = "INVALID_PARAMETER_VALUE_BELOW: The name of id below, min: 0.", value = 0) Integer id) {
        Result<UserDTO> result = userService.getUser(id);
        if (!result.isSuccess()) {
            ErrorResponse errorResponse =
                    new ErrorResponse(ErrorCode.UNKNOWN_ERROR.getError(), ErrorCode.UNKNOWN_ERROR.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        UserDTO userDTO = result.getData();
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userDTO, userVO);
        return userVO;
    }

}
