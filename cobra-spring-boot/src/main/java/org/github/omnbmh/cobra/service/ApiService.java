package org.github.omnbmh.cobra.service;

import lombok.extern.slf4j.Slf4j;
import org.github.omnbmh.cobra.entity.Api;
import org.github.omnbmh.cobra.entity.ApiExample;
import org.github.omnbmh.cobra.entity.Role;
import org.github.omnbmh.cobra.mapper.ApiMapper;
import org.github.omnbmh.cobra.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ApiService {

    @Autowired
    ApiMapper apiMapper;

    @Autowired
    RoleMapper roleMapper;

    public List<Api> apiList() {
        return apiMapper.selectByExample(new ApiExample());
    }

    public List<Role> apiRoles(String apiNo) {
        return roleMapper.getApiRolesByApiNo(apiNo);
    }
}
