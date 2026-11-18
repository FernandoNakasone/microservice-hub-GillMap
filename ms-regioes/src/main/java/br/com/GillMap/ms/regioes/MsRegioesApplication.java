package br.com.GillMap.ms.regioes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsRegioesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsRegioesApplication.class, args);
	}

}
