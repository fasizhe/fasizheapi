package com.faishze.api.fasizheapi.service;

import com.faishze.api.fasizheapi.pojo.do0.Profile;

/**
 * @author masonluo
 * @date 2019/10/27 5:10 PM
 */
public interface ProfileService {
    Profile getByUserID(Integer id);

    int uploadAvatarUrl(String avatarUrl);

    void update(Profile updateProfile);
}
