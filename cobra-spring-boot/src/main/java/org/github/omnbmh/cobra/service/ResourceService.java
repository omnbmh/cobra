package org.github.omnbmh.cobra.service;

import lombok.extern.slf4j.Slf4j;
import org.github.omnbmh.cobra.entity.Resource;
import org.github.omnbmh.cobra.entity.ResourceExample;
import org.github.omnbmh.cobra.entity.Role;
import org.github.omnbmh.cobra.mapper.ResourceMapper;
import org.github.omnbmh.cobra.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ResourceService {

    @Autowired
    ResourceMapper resourceMapper;

    @Autowired
    RoleMapper roleMapper;

    public List<Resource> resourceList() {
        return resourceMapper.selectByExample(new ResourceExample());
    }

    public List<Role> resourceRoles(String resourceNo) {
        return roleMapper.getResourceRolesByResourceNo(resourceNo);
    }
}
