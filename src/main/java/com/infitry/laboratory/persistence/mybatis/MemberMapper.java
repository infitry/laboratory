package com.infitry.laboratory.persistence.mybatis;

import com.infitry.laboratory.entity.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MemberMapper {

    @Select("SELECT * FROM member")
    List<Member> findAllMember();

    @Select("SELECT * FROM member WHERE id = #{id}")
    Member findById(Long id);

    @Insert("INSERT member(id, age, name, nick_name, password) values(max(SELECT id FROM member) + 1, #{age}, #{name}, #{nickName}, #{password})")
    void save(Member member);
}
