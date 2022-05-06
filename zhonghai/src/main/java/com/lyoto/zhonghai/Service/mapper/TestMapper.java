package com.lyoto.zhonghai.Service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyoto.zhonghai.Bean.Annotation.EncryptionMapper;
import com.lyoto.zhonghai.Bean.Test;
import org.apache.ibatis.annotations.Mapper;

/**
 @author Lyoto
 @Description
 @Date 2022-05-05 14:10
 @Version
 **/
@Mapper
@EncryptionMapper
public interface TestMapper extends BaseMapper<Object> {
}
