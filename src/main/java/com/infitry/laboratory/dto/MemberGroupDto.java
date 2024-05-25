package com.infitry.laboratory.dto;

import com.infitry.laboratory.entity.MemberGroup;

public record MemberGroupDto(
    Long id,
    MemberDto member,
    String name,
    String code
) {
    public static MemberGroupDto from(MemberGroup memberGroup) {
        return new MemberGroupDto(memberGroup.getId(), MemberDto.from(memberGroup.getMember()),
                memberGroup.getName(), memberGroup.getCode());
    }
}
