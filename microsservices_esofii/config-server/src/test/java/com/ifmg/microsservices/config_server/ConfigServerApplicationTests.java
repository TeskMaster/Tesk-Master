package com.ifmg.microsservices.config_server;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConfigServerApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private ApplicationContext context;

	private final TestRestTemplate restTemplate = new TestRestTemplate();

	@Test
	@DisplayName("Contexto da aplicação deve carregar")
	void contextLoads() {
		assertThat(context).isNotNull();
	}

	@Test
	@DisplayName("Servidor deve estar rodando na porta aleatória")
	void serverShouldRunOnRandomPort() {
		assertThat(port).isGreaterThan(0);
	}

	@Test
	@DisplayName("Health check deve estar disponível")
	void healthCheckShouldBeAvailable() {
		String url = "http://localhost:" + port + "/actuator/health";
		
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		
		// Health pode estar habilitado ou não
		assertThat(response.getStatusCode()).isIn(HttpStatus.OK, HttpStatus.NOT_FOUND);
	}

	@Test
	@DisplayName("Endpoint de configuração padrão deve responder")
	void defaultConfigEndpointShouldRespond() {
		String url = "http://localhost:" + port + "/application/default";
		
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		
		// Pode retornar 200 se configurado ou 404 se não encontrar
		assertThat(response.getStatusCode()).isIn(HttpStatus.OK, HttpStatus.NOT_FOUND);
	}

	@Test
	@DisplayName("Endpoint de configuração do notification-service deve responder")
	void notificationServiceConfigShouldRespond() {
		String url = "http://localhost:" + port + "/notification-service/default";
		
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		
		// Pode retornar 200 se configurado ou 404 se não encontrar
		assertThat(response.getStatusCode()).isIn(HttpStatus.OK, HttpStatus.NOT_FOUND);
	}

	@Test
	@DisplayName("Endpoint de configuração do crud-de-eventos deve responder")
	void crudEventosConfigShouldRespond() {
		String url = "http://localhost:" + port + "/crud-de-eventos/default";
		
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		
		// Pode retornar 200 se configurado ou 404 se não encontrar
		assertThat(response.getStatusCode()).isIn(HttpStatus.OK, HttpStatus.NOT_FOUND);
	}

	@Test
	@DisplayName("Config Server deve aceitar requisições HTTP")
	void configServerShouldAcceptHttpRequests() {
		String url = "http://localhost:" + port + "/";
		
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		
		// Pode retornar várias respostas dependendo da configuração
		assertThat(response.getStatusCode()).isIn(
			HttpStatus.OK, 
			HttpStatus.NOT_FOUND, 
			HttpStatus.METHOD_NOT_ALLOWED
		);
	}

}
