package com.psl.banking.ChequeMicroservice.repository;


import com.psl.banking.ChequeMicroservice.Model.UserCheque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserChequeRepository extends JpaRepository<UserCheque,Long> {

    public List<UserCheque> findByUserId(long userId);

}
