package com.srit.ecs.phone;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.srit.ecs.phone.entity.UserEntity;
import com.srit.ecs.phone.service.LoginService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	
	@Autowired
	LoginService loginService;

	@Test
	public void testQueryForPage() {
        Page<UserEntity> page = new Page<>(2,2);
        Map<String, String> params = new HashMap<>();
        JSONObject res = loginService.queryForPage(page,params);
        System.out.println(res);
	}
	
	@Test
	public void testSave() {
		UserEntity userEntity =  new UserEntity();
		userEntity.setCreatetime(new Date());
		Integer res = loginService.save(userEntity);
        System.out.println(res);
	}

}
