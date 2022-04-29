package com.lyoto.zhonghai.Service.Impl;

import java.util.Date;

import com.lyoto.zhonghai.Bean.ManagerPO;
import com.lyoto.zhonghai.Service.mapper.ManagerMapper;
import org.mybatis.spring.annotation.MapperScan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 @author Lyoto
 @Description
 @Date 2022-01-30 20:39
 @Version
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class FetchDataToFile {
	@Autowired
	ManagerMapper managerMapper;
	public void fetchUserSave(){
		ManagerPO managerPO = new ManagerPO();
		managerPO.setAccount("aaa");
		managerPO.setManagerName("aaa2");
		managerPO.setEmail("145a4sd64as4dass");
		managerPO.setPassword("123456");
		managerPO.setType(1);
		managerPO.setGroupCode("DA95A03A029B41B5822507FFAEF8FADD");
		managerPO.setManagerStatus(true);
		managerPO.setCreateTime(new Date());
		managerPO.setParentId(0);
	//managerMapper.insert(managerPO);
		System.out.println(managerMapper.selectById(198679));
	}

}
