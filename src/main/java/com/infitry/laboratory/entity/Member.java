package com.infitry.laboratory.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_MEMBER")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    List<MemberGroup> memberGroups = new ArrayList<>();

    @Column
    String name;

    @Column
    String nickName;

    @Column
    String password;

    @Column
    Integer age;
}
