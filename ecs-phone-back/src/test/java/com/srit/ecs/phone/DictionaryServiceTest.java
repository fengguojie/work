package com.srit.ecs.phone;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.srit.ecs.phone.entity.DictionaryEntity;
import com.srit.ecs.phone.service.DictionaryService;
import com.srit.ecs.phone.util.PageDetails;
import com.srit.ecs.phone.util.PageDetailsUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DictionaryServiceTest {
	
	@Autowired
	DictionaryService dictionaryService;

	@Test
	public void testQueryForPage() {
        String name = "信";
        Page<DictionaryEntity> page = new Page<>(1,5);
        Map<String, Object> res = dictionaryService.queryForPage(page,name);
        System.out.println(res.size());
        System.out.println(JSONObject.toJSON(res));
	}
	
	@Test
	public void testQueryForMapPage() {
        String name = "信";
        Page<DictionaryEntity> page = new Page<>(1,5);
        List<Map<String, Object>> pageList = dictionaryService.queryForMapPage(page,name);
        System.out.println(pageList.size());
        System.out.println(JSONObject.toJSON(pageList));
	}

}
