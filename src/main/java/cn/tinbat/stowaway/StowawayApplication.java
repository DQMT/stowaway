package cn.tinbat.stowaway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StowawayApplication {


	public static void main(String[] args) {
		SpringApplication.run(StowawayApplication.class, args);
	}

	/**
	 *jetty: Unable to start EmbeddedWebApplicationContext due to missing EmbeddedServletContainerFactory bean.
	 * @return
	 */
	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		JettyEmbeddedServletContainerFactory factory =
				new JettyEmbeddedServletContainerFactory();
		return factory;
	}
}
