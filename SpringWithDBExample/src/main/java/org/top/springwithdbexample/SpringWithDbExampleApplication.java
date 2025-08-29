package org.top.springwithdbexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringWithDbExampleApplication {

	public static void main(String[] args) {
		// ЗАДАЧИ:
		// Добавить получение всех заказов (вообще всех)
		// Добавить получение заказов за определенный период времени (задавать датами)
		// Добавить начисление (прибавить а не перезаписать) discountPoints
		// 	для заданного по id пользователя в кол-ве его заказов
		// т.е. если вызвали метод для user(55) и он имеет 23 заказа в БД
		// то ему начисляется 23 discountPoints

		SpringApplication.run(SpringWithDbExampleApplication.class, args);
	}

}
