package com.compressibleflowcalculator.shopping_api.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import com.compressibleflowcalculator.shopping_api.Entity.User;
import com.compressibleflowcalculator.shopping_api.Repository.UserRepository;

@EnableCaching
@Service
@CacheConfig(cacheNames = "dataCache", cacheManager = "cacheManagerWithDataTTL")
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Cacheable(value = "users", key = "#keyid")
    public User getUserById(String id) {
        return userRepository.getReferenceById(id);
    }
}
