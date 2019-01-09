package com.demo.cache.dao;

import com.demo.cache.vo.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
import java.util.Optional;
//import java.util.Optional;

/**
 * Created on 2018-12-19
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
@Mapper
public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer seq);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer seq);

    int updateByPrimaryKeySelective(UserInfo record);

    List<UserInfo> selectAll();

    int updateByPrimaryKey(UserInfo record);

    @Results(id = "test1", value = {
            @Result(property = "seq", column = "seq", id = true, jdbcType = JdbcType.INTEGER, javaType = Integer.class),
            @Result(property = "name", column = "name", jdbcType = JdbcType.VARCHAR, javaType = String.class),
            @Result(property = "age", column = "age", jdbcType = JdbcType.INTEGER, javaType = Integer.class)
    })
    @Select("select name, age from user_info where seq = #{seq}")
    Optional<UserInfo> getOptionalUser(int seq);
}