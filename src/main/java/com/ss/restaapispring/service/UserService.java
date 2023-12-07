package com.ss.restaapispring.service;

import com.ss.restaapispring.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserEntity saveUser(UserEntity userEntity);

}
