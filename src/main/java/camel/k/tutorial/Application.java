package camel.k.tutorial;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	@Value("${baeldung.api.path}")
	String contextPath;

	@Bean
	ServletRegistrationBean<CamelHttpTransportServlet> servletRegistrationBean() {
		ServletRegistrationBean<CamelHttpTransportServlet> servlet =
				new ServletRegistrationBean<>(new CamelHttpTransportServlet(), contextPath + "/*");
		servlet.setName("CamelServlet");
		return servlet;
	}
	Type parameter 'org.apache.camel.component.servlet.CamelHttpTransportServlet' is not within its bound; should implement 'jakarta.servlet.Servlet'

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
