package com.faishze.api.fasizheapi.controller.v1;

import com.faishze.api.fasizheapi.dao.UserMapper;
import com.faishze.api.fasizheapi.pojo.do0.User;
import com.faishze.api.fasizheapi.pojo.vo.UserVO;
import com.faishze.api.fasizheapi.service.FileService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 描述:
 *
 * @author xhsf
 * @email 827032783@qq.com
 * @create 2019-10-26
 */
@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FileService fileService;

    @Autowired
    private Mapper mapper;

    @RequestMapping("/test")
    public Object save(User user) {
        userMapper.insert(user);
        return mapper.map(user, UserVO.class);
    }

    @RequestMapping("/test2")
    public Object saveFile(MultipartFile file) {
        return fileService.save(file, "/user/avatar");
    }

}
