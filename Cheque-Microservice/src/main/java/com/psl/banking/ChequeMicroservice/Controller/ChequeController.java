package com.psl.banking.ChequeMicroservice.Controller;



import com.psl.banking.ChequeMicroservice.Model.Cheque;
import com.psl.banking.ChequeMicroservice.service.ChequeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cheque")
public class ChequeController {

    @Autowired
    private ChequeService chequeService;

    private static final Logger log = LogManager.getLogger(ChequeController.class);

    //method to return the cheque details of given chequeId ,or warn that chequeId is invalid
    @GetMapping("/{chequeId}")
    public Cheque getCheque(@Valid @PathVariable("chequeId") long chequeId) throws Exception{
        return chequeService.getChequeById(chequeId);
    }

    //Method returns the list of all cheques
    @GetMapping("/all")
    public List<Cheque> getAllCheques() {
        return chequeService.getCheques();
    }

    //Method to add cheque in database for given user
    @PostMapping("/add/{userid}")
    public Cheque addCheque(@Valid @RequestBody Cheque cheque, @PathVariable long userid) {
        return chequeService.addNewCheque(cheque, userid);
    }


    //Method to return all List of all invalid cheques for given user
    @GetMapping("/invalid-cheques/{userid}")
    public List<Cheque> getInvalidChequesByUserId(@PathVariable long userid) {
        return chequeService.getInvalidChequesByUserId(userid);

    }

    //Method to return all List of all invalid cheques for each Bank over last month
    @GetMapping("/invalid-cheques-last-month")
    public List<Cheque> getInvalidCheques() {
        return chequeService.getInvalidCheques();
    }


    @GetMapping("/init")
    public String init() {
        chequeService.initUsers();
        return "OK";
    }

}
