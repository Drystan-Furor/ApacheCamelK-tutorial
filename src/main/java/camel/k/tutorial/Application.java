@SpringBootApplication
@ComponentScan(basePackages="com.baeldung.camel")
@Value("${baeldung.api.path}")
String contextPath;

@Bean
ServletRegistrationBean servletRegistrationBean() {
		ServletRegistrationBean servlet = new ServletRegistrationBean
		(new CamelHttpTransportServlet(), contextPath+"/*");
		servlet.setName("CamelServlet");
		return servlet;
		}

public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}



