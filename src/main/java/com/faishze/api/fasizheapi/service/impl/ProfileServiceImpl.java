package com.faishze.api.fasizheapi.service.impl;

import com.faishze.api.fasizheapi.dao.ProfileMapper;
import com.faishze.api.fasizheapi.pojo.do0.Profile;
import com.faishze.api.fasizheapi.service.FileService;
import com.faishze.api.fasizheapi.service.ProfileService;
import com.faishze.api.fasizheapi.service.constant.FileConstant;
import org.apache.tika.mime.MimeTypeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;

/**
 * @author masonluo
 * @date 2019/10/27 11:28 PM
 */
@Service
public class ProfileServiceImpl implements ProfileService {

    private Logger log = LoggerFactory.getLogger(ProfileServiceImpl.class);

    private final ProfileMapper profileMapper;

    private final FileService fileService;

    @Autowired
    public ProfileServiceImpl(FileService fileService, ProfileMapper profileMapper) {
        this.fileService = fileService;
        this.profileMapper = profileMapper;
    }

    @Override
    public Profile getByUserID(Integer id) {
        return profileMapper.selectByUserID(id);
    }

    @Override
    public int uploadAvatarUrl(String avatarUrl, Integer userID) throws IOException, MimeTypeException {
        String bufferPath = fileService.saveToBuffer(avatarUrl);
        Assert.notNull(bufferPath, "bufferPath should not be null");
        log.info("URL - {} - is store in {}", avatarUrl, bufferPath);
        // 将文件上传至FTP服务器
        File file = new File(bufferPath);
        String filePath = fileService.saveAndGetUrl(file, FileConstant.AVATAR_PATH, true);
        if(filePath != null){
            Profile profile = new Profile();
            profile.setUserId(userID);
            profile.setAvatarUrl(filePath);
            return profileMapper.updateByUserID(profile);
        }
        return 0;
    }

    @Override
    public Profile updateByID(Profile updateProfile) {
        int result = profileMapper.updateByPrimaryKey(updateProfile);
        if(result == 0){
            return null;
        }
        return updateProfile;
    }
}
