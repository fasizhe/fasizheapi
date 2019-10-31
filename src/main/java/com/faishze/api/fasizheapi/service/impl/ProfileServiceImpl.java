package com.faishze.api.fasizheapi.service.impl;

import com.faishze.api.fasizheapi.dao.ProfileMapper;
import com.faishze.api.fasizheapi.pojo.do0.Profile;
import com.faishze.api.fasizheapi.service.FileService;
import com.faishze.api.fasizheapi.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;

/**
 * @author masonluo
 * @date 2019/10/27 11:28 PM
 */
@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileMapper profileMapper;

    @Autowired
    private FileService fileService;

    @Override
    public Profile getByUserID(Integer id) {
        return profileMapper.selectByUserID(id);
    }

    @Override
    public int uploadAvatarUrl(String avatarUrl) {

        return 0;
    }

    @Override
    public void update(Profile updateProfile) {

    }
}
