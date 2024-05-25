package com.infitry.laboratory.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "TB_ORDER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class Order {

    @Id
    @Column(name = "ORDER_ID")
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    @Column
    String orderName;
}
