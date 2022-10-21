package com.psl.banking.ChequeMicroservice.repository;


import com.psl.banking.ChequeMicroservice.Model.Cheque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChequeRepository extends JpaRepository<Cheque,Long> {

    @Query(value="select * from cheque i where i.date between ?1 and ?2",nativeQuery = true)
    List<Cheque> getCheques(String prev, String curr);

}
