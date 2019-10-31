package com.faishze.api.fasizheapi.service;

import com.faishze.api.fasizheapi.pojo.do0.Profile;
import org.apache.tika.mime.MimeTypeException;

import java.io.IOException;

/**
 * @author masonluo
 * @date 2019/10/27 5:10 PM
 */
public interface ProfileService {
    Profile getByUserID(Integer id);

    /**
     * 上传头像
     * @param avatarUrl 头像url
     * @param userID 用户ID
     * @return 上传成功，返回1，否则，返回0
     * @throws IOException
     * @throws MimeTypeException
     */
    int uploadAvatarUrl(String avatarUrl, Integer userID) throws IOException, MimeTypeException;

    /**
     * 根据主键ID进行更新
     * @param updateProfile 要更新的记录
     * @return 更新成功，返回更新之后的记录，否则，返回null
     */
    Profile updateByID(Profile updateProfile);
}
