package kz.madina.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 3 рөл: Мұғалім, Күзет, Қауіп, Басшылық

// Мұғалім: аты, тегі, телефон, құпиясөз, кабинет нөмірі, сынып
// Күзет: аты, тегі, деңгейі, телефон, құпиясөз
// Директор
/**
 * завуч
 * электрик
 * психолог
 * дәрігер
 *
 *
 */

@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

}
