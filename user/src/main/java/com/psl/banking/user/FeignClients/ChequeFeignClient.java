package com.psl.banking.user.FeignClients;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "CHEQUE-MICROSERVICE")
public interface ChequeFeignClient {

    @PostMapping("/cheque/add/{userid}")
    public ChequeProxy addCheque(@RequestBody ChequeProxy cheque, @PathVariable long userid) ;

    @GetMapping("/cheque/invalid-cheques/{userid}")
    public List<ChequeProxy> getInvalidChequesByUserId(@PathVariable long userid);

    @GetMapping("/cheque/invalid-cheques-last-month")
    public List<ChequeProxy> getInvalidCheques();
}
