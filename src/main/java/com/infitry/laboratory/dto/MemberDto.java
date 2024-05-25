package com.infitry.laboratory.dto;

import com.infitry.laboratory.entity.Member;

import java.util.List;

public record MemberDto(
    Long id,
    List<MemberGroupDto> memberGroups,
    String name,
    String nickName,
    String password,
    Integer age
) {
    public static MemberDto from(Member member) {
        var memberGroupDtos = member.getMemberGroups().stream().map(MemberGroupDto::from).toList();
        return new MemberDto(member.getId(), memberGroupDtos,
                member.getName(), member.getNickName(), member.getPassword(), member.getAge());
    }
}