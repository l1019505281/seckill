package org.lds.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.lds.pojo.User;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {
    @Select("SELECT * FROM user WHERE id = #{id}")
    User getById(@Param("id") int id);
}
