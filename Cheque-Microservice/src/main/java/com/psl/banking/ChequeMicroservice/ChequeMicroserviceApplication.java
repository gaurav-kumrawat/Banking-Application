package com.psl.banking.ChequeMicroservice;

import com.psl.banking.ChequeMicroservice.Model.Cheque;
import com.psl.banking.ChequeMicroservice.Model.UserCheque;
import com.psl.banking.ChequeMicroservice.repository.ChequeRepository;
import com.psl.banking.ChequeMicroservice.repository.UserChequeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class ChequeMicroserviceApplication {


	public static void main(String[] args) {
		SpringApplication.run(ChequeMicroserviceApplication.class, args);
	}


}
