package com.psl.banking.user;

import com.psl.banking.user.entity.User;
import com.psl.banking.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@EnableFeignClients

public class UserApplication {

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args)
	{
		SpringApplication.run(UserApplication.class, args);
	}

	@PostConstruct
	public void initUsers() {

		List<User> users = Stream.of(new User(101L, "user1", "$2a$12$5DuHkUQxp3YuSt.gFTv8Te2P.Xkq5LXPnhS51RX5Bub1Yp9fZ18n.", "user1@gmail.com",null),
				new User(102L, "user2", "$2a$12$K6z8ZSOlauNxOHu/oel/Le2Hn3Y3Ia2JnPr6jX83Vd45MmtVeY1Ia", "user2@gmail.com",null),

			 	new User(103L, "user3", "$2a$12$K6z8ZSOlauNxOHu/oel/Le2Hn3Y3Ia2JnPr6jX83Vd45MmtVeY1Ia", "user3@gmail.com",null),

				new User(104L, "user4", "$2a$12$D8hgnIb7CaISJsLt/HXa.e/zG0BsCuQRfnxb.A69hx/lhOgEFR5FG", "user4@gmail.com",null)

		).collect(Collectors.toList());
		userRepository.saveAll(users);

	}

}
