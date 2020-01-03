package com.srit.ecs.phone.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.srit.ecs.phone.entity.PermissionsEntity;
import com.srit.ecs.phone.entity.RoleEntity;
import com.srit.ecs.phone.entity.UserEntity;
import com.srit.ecs.phone.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{
	
	 @Override
     public UserEntity getUserByName(String name) {
        //模拟数据库查询，正常情况此处是从数据库或者缓存查询。
        return getMapByName(name);
     }

     /**
      * 模拟数据库查询
      * @param userName
      * @return
      */
     private UserEntity getMapByName(String userName){
    	Map<String ,UserEntity> map = new HashMap<>();
    	 
        PermissionsEntity permissions1 = new PermissionsEntity("1","query");
        PermissionsEntity permissions2 = new PermissionsEntity("2","add");
        Set<PermissionsEntity> permissionsSet = new HashSet<>();
        permissionsSet.add(permissions1);
        permissionsSet.add(permissions2);
        RoleEntity role = new RoleEntity("1","admin",permissionsSet);
        Set<RoleEntity> roleSet = new HashSet<>();
        roleSet.add(role);
        UserEntity user = new UserEntity("1","jellard","123456",roleSet);
        
        map.put(user.getUserName(), user);

        PermissionsEntity permissions3 = new PermissionsEntity("3","query");
        Set<PermissionsEntity> permissionsSet1 = new HashSet<>();
        permissionsSet1.add(permissions3);
        RoleEntity role1 = new RoleEntity("2","query",permissionsSet1);
        Set<RoleEntity> roleSet1 = new HashSet<>();
        roleSet1.add(role1);
        UserEntity user1 = new UserEntity("2","liuqiangqiang","123456",roleSet1);
        map.put(user1.getUserName(), user1);
        
        return map.get(userName);
     }

}
