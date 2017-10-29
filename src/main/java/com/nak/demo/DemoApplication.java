package com.nak.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}

@RestController
class GreetingController {

	@RequestMapping("/hello/{name}")
	String hello(@PathVariable String name) {
		return "Hello, " + name + "!";
	}
}

@Controller
@EnableAutoConfiguration
class DatabaseController {

	@Autowired
	ConfigurationsRepository configurationsRepository;

	@RequestMapping("/")
	public String home() {
		System.out.println("[START] データベースに接続してデータを取得します。");
		Page<Configurations> configs = configurationsRepository.findAll(new PageRequest(0, 10));
		for (Configurations config : configs) {
			System.out.println(config.getName() + " = " + config.getValue());
		}
		System.out.println("[END  ] データベースに接続してデータを取得します。");
		return "index";
	}

}