package com.example.mapper;

import com.example.entity.Comment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CommentMapper {

    /**
      * 新增
    */
    int insert(Comment comment);

    /**
      * 删除
    */
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(Comment comment);

    /**
      * 根据ID查询
    */
    Comment selectById(Integer id);

    /**
      * 查询所有
    */
    List<Comment> selectAll(Comment comment);

    /**
      * 查询对应房型的评论数
     */
    @Select("SELECT COUNT(*) FROM comment WHERE type_id = #{typeId} and deleted = #{deleted}")
    int getCountByTypeId(@Param("typeId") Integer typeId, @Param("deleted") Integer deleted);

    @Select("select * from comment where type_id = #{typeId} and parent_id = #{parentId} and deleted = 0 order by id")
    List<Comment> selectByTypeIdAndParentId(@Param("typeId") Integer typeId, @Param("parentId") Integer parentId);

    /**
     * 递归查询回复及子回复
     */
    List<Comment> selectRepliesByParentId(@Param("parentId") Integer parentId,@Param("deleted") Integer deleted);

    @Select("select * from comment where parent_id = #{parentId} order by id")
    List<Comment> selectByParentId(Integer parentId);

    /**
     * 分页查询评论回复
     */

    @Select("select * from comment where parent_id = #{parentId} order by comment.time limit #{offset}, #{limit}")
    List<Comment> selectByParentIdLimit(@Param("parentId") Integer parentId, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from comment where type_id = #{typeId} and parent_id = #{parentId} order by comment.time limit #{offset}, #{limit}")
    List<Comment> selectByTypeIdAndParentIdLimit(@Param("typeId") Integer typeId, @Param("parentId") Integer parentId, @Param("offset") Integer offset, @Param("limit") Integer limit);
    @Select("select count(*) from comment where parent_id = #{parentId}")
    int countByParentId(Integer parentId);

}

