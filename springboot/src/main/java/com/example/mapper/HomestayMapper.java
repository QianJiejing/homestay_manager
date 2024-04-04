package com.example.mapper;

import com.example.entity.Homestay;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作homestay相关数据接口
 */
public interface HomestayMapper {


    void insert(Homestay homestay);

    @Select("select * from homestay where username = #{username}")
    Homestay selectByUsername(String username);

    List<Homestay> selectAll(Homestay homestay);

    void updateById(Homestay homestay);

    @Delete("delete from homestay where id = #{id}")
    void deleteById(Integer id);

    @Select("select * from homestay where id = #{id}")
    Homestay selectById(Integer id);

    List<Homestay> selectAll();

    @Select("select * from homestay where status = '审核通过'")
    List<Homestay> selectAllOK();

}