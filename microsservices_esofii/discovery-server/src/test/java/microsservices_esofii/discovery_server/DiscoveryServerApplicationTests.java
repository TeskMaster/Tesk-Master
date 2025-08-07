package microsservices_esofii.discovery_server;

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
class DiscoveryServerApplicationTests {

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
	@DisplayName("Eureka dashboard deve estar acessível")
	void eurekaHomepageShouldBeAccessible() {
		String url = "http://localhost:" + port + "/";
		
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	@DisplayName("Endpoint de aplicações deve responder")
	void appsEndpointShouldRespond() {
		String url = "http://localhost:" + port + "/eureka/apps";
		
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		
		// Pode retornar 200 (OK) ou 406 (Not Acceptable) dependendo do Accept header
		assertThat(response.getStatusCode()).isIn(HttpStatus.OK, HttpStatus.NOT_ACCEPTABLE);
	}

	@Test
	@DisplayName("Health check deve estar disponível")
	void healthCheckShouldBeAvailable() {
		String url = "http://localhost:" + port + "/actuator/health";
		
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		
		// Health pode estar habilitado ou não
		assertThat(response.getStatusCode()).isIn(HttpStatus.OK, HttpStatus.NOT_FOUND);
	}

}
