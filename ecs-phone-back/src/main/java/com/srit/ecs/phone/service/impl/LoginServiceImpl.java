package com.srit.ecs.phone.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.srit.ecs.phone.entity.UserEntity;
import com.srit.ecs.phone.mapper.UserMapper;
import com.srit.ecs.phone.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{
	
	 @Autowired
	 UserMapper mapper;
	
	 @Override
     public UserEntity getUserByName(String name) {
		EntityWrapper<UserEntity> queryWrapper = new EntityWrapper<UserEntity>();
        queryWrapper.where(" state = 1 ")
        			.eq("user_name", name);
        List<UserEntity> users = mapper.selectList(queryWrapper);
        if (users != null && users.size() >= 1) {
			return users.get(0);
		}
        return null;
     }

     /**
      * 模拟数据库查询
      * @param userName
      * @return
      */
     private UserEntity getMapByName(String userName){
    	Map<String ,UserEntity> map = new HashMap<>();
    	 
//        PermissionsEntity permissions1 = new PermissionsEntity("1","query");
//        PermissionsEntity permissions2 = new PermissionsEntity("2","add");
//        Set<PermissionsEntity> permissionsSet = new HashSet<>();
//        permissionsSet.add(permissions1);
//        permissionsSet.add(permissions2);
//        RoleEntity role = new RoleEntity("1","admin");
//        Set<RoleEntity> roleSet = new HashSet<>();
//        roleSet.add(role);
        UserEntity user = new UserEntity("1","jellard","123456","1");
        map.put(user.getUserName(), user);

//        PermissionsEntity permissions3 = new PermissionsEntity("3","query");
//        Set<PermissionsEntity> permissionsSet1 = new HashSet<>();
//        permissionsSet1.add(permissions3);
//        RoleEntity role1 = new RoleEntity("2","normal");
//        Set<RoleEntity> roleSet1 = new HashSet<>();
//        roleSet1.add(role1);
        UserEntity user1 = new UserEntity("2","qiangqiang","123456","2");
        map.put(user1.getUserName(), user1);
        
        return map.get(userName);
     }

}
