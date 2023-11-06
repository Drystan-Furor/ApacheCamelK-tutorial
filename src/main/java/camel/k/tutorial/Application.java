@SpringBootApplication
@ComponentScan(basePackages="com.baeldung.camel")

public class Application {

	@Value("${baeldung.api.path}")
	String contextPath;

	@Bean
	ServletRegistrationBean servletRegistrationBean() {
		ServletRegistrationBean servlet = new ServletRegistrationBean
				(new CamelHttpTransportServlet(), contextPath+"/*");
		servlet.setName("CamelServlet");
		return servlet;
	}


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}

/*
With this structure, you declare contextPath as a member variable of the Application class,
which will be populated with the value from the application.yml when the application starts.
The servletRegistrationBean method defines a bean that is part of the application context,
and it can make use of the contextPath variable because it's within the same class.
 */

