package com.example.mapper;

import com.example.entity.ImSingle;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作ImSingle相关数据接口
*/
public interface ImSingleMapper {

    /**
      * 新增
    */
    int insert(ImSingle imSingle);

    /**
      * 删除
    */
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(ImSingle imSingle);

    /**
      * 根据ID查询
    */
    ImSingle selectById(Integer id);

    /**
      * 查询所有
    */
    List<ImSingle> selectAll(ImSingle imSingle);

    @Select("select * from imsingle where (fromuser = #{fromUser} and touser = #{toUser}) or (fromuser = #{toUser} and touser = #{fromUser})")
    List<ImSingle> findByUsername(String fromUser, String toUser);

    @Select("select * from imsingle where touser = #{toUser} and readed = 0")
    List<ImSingle> findByToUsername(String toUser);

}