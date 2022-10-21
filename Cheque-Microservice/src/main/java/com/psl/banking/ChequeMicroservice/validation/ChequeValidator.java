package com.psl.banking.ChequeMicroservice.validation;


import com.psl.banking.ChequeMicroservice.Model.Cheque;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class ChequeValidator {


    public boolean isBankNameValid(String bname){
         List<String> banks = new ArrayList<String>();
         banks.add("Axis Bank");
         banks.add("ICICI Bank");
         banks.add("HDFC Bank");
         banks.add("SBI Bank");

        for (String bank:banks) {
            if(bname.equalsIgnoreCase(bank)){
                return true;
            }
        }

        return false;

    }


    public boolean isDateValid(String date){

        LocalDate cdate = LocalDate.parse(date).plusMonths(3);

        String chequeDate = cdate.toString();

        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currDate = dateObj.format(formatter);


        if(chequeDate.compareTo(currDate) <0){
            return false;
        }

        return true;

    }

    public  boolean isChequeValid(Cheque cheque){
        return this.isDateValid(cheque.getDate());
    }


}
