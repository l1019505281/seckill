package org.lds.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.lds.pojo.SeckillUser;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SecKillUserDao {
    @Select("select * from seckilluser where id = #{id}")
    public SeckillUser getById(@Param("id") long id);
}
