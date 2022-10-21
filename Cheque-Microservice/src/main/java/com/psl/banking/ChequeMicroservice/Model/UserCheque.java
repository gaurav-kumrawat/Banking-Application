package com.psl.banking.ChequeMicroservice.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userCheque")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCheque {


    @Id
    private long chequeId;
    private long userId;


}
