package org.top.springwithdbexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// в файле application.propeties нужно подключиться к БД
@SpringBootApplication
public class SpringWithDbExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWithDbExampleApplication.class, args);
	}

}
