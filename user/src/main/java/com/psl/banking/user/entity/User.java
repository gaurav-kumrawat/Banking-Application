package com.psl.banking.user.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
@Table(name = "userDetails")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long userId;

    @Column(unique = true)
    @NotNull
    private String username;

    private String password;

    private String emailId;

    private Long sessionId;

}
