package com.infitry.laboratory.persistence.mybatis;

import com.infitry.laboratory.entity.MemberGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface GroupMapper {

    @Update("UPDATE member_group SET code = #{code} WHERE id = #{id}")
    void updateGroup(MemberGroup memberGroup);

    @Select("SELECT * FROM member_group WHERE id = #{id}")
    MemberGroup findById(Long id);

    @Select("SELECT * FROM member_group")
    List<MemberGroup> findAllGroup();
}
