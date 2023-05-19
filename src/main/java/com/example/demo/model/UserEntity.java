package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table
public class UserEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String pk;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Builder.Default
    private Integer borrowedBooksCnt = 0;
    @Column(nullable = false)
    @Builder.Default
    private String roles = Role.MEMBER.getValue();

    private UserEntity(String pk, String username, String password, String roles, Integer borrowedBooksCnt){
        this.pk = pk;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.borrowedBooksCnt = borrowedBooksCnt;
    }
}
