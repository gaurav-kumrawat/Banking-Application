package com.psl.banking.ChequeMicroservice.service;


import com.psl.banking.ChequeMicroservice.Exception.NoChequeFoundException;
import com.psl.banking.ChequeMicroservice.Model.Cheque;
import com.psl.banking.ChequeMicroservice.Model.UserCheque;
import com.psl.banking.ChequeMicroservice.repository.ChequeRepository;
import com.psl.banking.ChequeMicroservice.repository.UserChequeRepository;
import com.psl.banking.ChequeMicroservice.validation.ChequeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class ChequeService {

    @Autowired
    private ChequeRepository chequeRepository;

    @Autowired
    private UserChequeRepository userChequeRepository;

    @Autowired
    ChequeValidator chequeValidator;

    public Cheque getChequeById(long chequeId) throws NoChequeFoundException {
        Cheque cheque = chequeRepository.findById(chequeId).orElseThrow(() -> new NoChequeFoundException("No cheque found with chequeId :" + chequeId));
        return cheque;
    }

    public  List<Cheque> getCheques() {
        return chequeRepository.findAll();
    }


    public Cheque addNewCheque(Cheque cheque,long userid) {
        if (chequeValidator.isChequeValid(cheque)) {
            cheque.setChequeValid(true);
        }
        chequeRepository.save(cheque);
        this.newUserCheque(cheque.getChequeId(), userid);

        return cheque;
    }

    public void newUserCheque(long chequeId, long userId){
          UserCheque userCheque = new UserCheque(chequeId,userId);
          userChequeRepository.save(userCheque);
    }



    public List<Cheque> getInvalidChequesByUserId(long userId) {

        List<Cheque> invalidCheques = new ArrayList<Cheque>();
        List<UserCheque>  userCheques = userChequeRepository.findByUserId(userId);

        for (UserCheque cheque: userCheques) {
            Cheque temp = chequeRepository.findById(cheque.getChequeId()).get();
            if(!temp.isChequeValid()){
                invalidCheques.add(temp);
            }
        }
        return invalidCheques;
    }



//  logic for getting invalid cheques for each bank for past month
    public List<Cheque> getInvalidCheques() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currDate =  LocalDate.now().format(formatter);
        String prevDate =  LocalDate.now().minusMonths(1).format(formatter);
        System.out.println(currDate+"  "+prevDate);
        List<Cheque> allCheques = chequeRepository.getCheques(prevDate,currDate);
        List<Cheque> invalidCheques = new ArrayList<>();
        for(Cheque temp:allCheques){
            if(!temp.isChequeValid())
                invalidCheques.add(temp);
        }
        return  invalidCheques;
    }

    public void initUsers() {
        List<Cheque> cheques = Stream.of(new Cheque(1L,"SBI Bank","SBIN0000371","411229008","123456",5000.0,"2022-06-10",true,true),
                new Cheque(2L,"HDFC","HDFC0200211","411229008","123457",2000.0,"2022-06-10",true,false),
                new Cheque(3L,"Axis Bank","UTIB0002927","411229008","123458",3000.0,"2022-06-10",false,true),
                new Cheque(4L,"ICICI Bank","ICIC0000321","411229008","123459",10000.0,"2022-06-10",false,false)
        ).collect(Collectors.toList());
        chequeRepository.saveAll(cheques);

        List<UserCheque> users =Stream.of(new UserCheque(1,1),new UserCheque(2,1),new UserCheque(3,2),new UserCheque(4,2)).collect(Collectors.toList());
        userChequeRepository.saveAll(users);
    }
}
