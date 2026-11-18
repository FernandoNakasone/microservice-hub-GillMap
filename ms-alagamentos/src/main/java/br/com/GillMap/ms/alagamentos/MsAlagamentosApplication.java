package br.com.GillMap.ms.alagamentos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MsAlagamentosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsAlagamentosApplication.class, args);
	}

}
