# OnlineMall

## Applied techniques
This is a personal project that is progressive.


- Spring Starter Project
- Spring Security
- Spring AOP
- Servlet by Java Config
- Scheduler by Java Config
- Locale Configure by Java Config
- Java 8 (Stream API, Method Reference, Functional Interfaces ..)
- Spring-Data-Jpa

## Examples 

### Spring Security

``` java
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	public static final String REMEMBER_ME_KEY = "finrirKey";
	@Autowired DataSource dataSource;
	@Autowired UserDetailsService userDetailsService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests() 
			.antMatchers("/members/new", "/home", "/download/**").permitAll()
			.antMatchers("/items/new", "/admin/**").hasRole("ADMIN")
			.antMatchers("/members/**", "/orders/**", "/items/**", "/carts/**").hasRole("USER")
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/")
			.failureUrl("/loginError")	// Url to forword when failed Security Login 
			.usernameParameter("email")
			.passwordParameter("password")
			.permitAll()
			.and()
			.logout()
			.deleteCookies("remember-me")
			.logoutSuccessUrl("/")
			.permitAll()
			.and()
			.rememberMe()
			.rememberMeParameter("remember-me")
			.key(REMEMBER_ME_KEY)
			.rememberMeServices(persistentTokenBasedRememberMeServices());

	}
	
	// TOKEN BASED.
	@Bean
	public TokenBasedRememberMeServices tokenBasedRememberMeServices() {
		TokenBasedRememberMeServices tokenBasedRememberMeServices =
				new TokenBasedRememberMeServices(REMEMBER_ME_KEY, userDetailsService);
		tokenBasedRememberMeServices.setCookieName("arahansaCookie");
		
		return tokenBasedRememberMeServices;
	}
	
	// PERSISTENT BASED
	@Bean
	public PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices() {
		PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices = 
				new PersistentTokenBasedRememberMeServices(REMEMBER_ME_KEY, userDetailsService, jdbcTokenRepositoryImpl());
		
		return persistentTokenBasedRememberMeServices;
	}
	
	@Bean
	JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl() {
		JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl =
				new JdbcTokenRepositoryImpl();
		jdbcTokenRepositoryImpl.setCreateTableOnStartup(false);
		jdbcTokenRepositoryImpl.setDataSource(dataSource);
		
		return jdbcTokenRepositoryImpl;
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
```

### Spring AOP
``` java
@Component
@Aspect
@Order(Ordered.LOWEST_PRECEDENCE)
public class AspectConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(AspectConfig.class);
	
	@Around("execution(* org.juon.jpashop.web.*.*(..))")
	public Object doExcutionTimeLog(ProceedingJoinPoint joinPoint) throws Throwable{
		final long start = System.currentTimeMillis();
		
		final Object proceed = joinPoint.proceed();
		
		long executionTime = System.currentTimeMillis() - start;
		
		logger.info("\n################### doExcutionTimeLog ############################\n");
		logger.info(joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature() + " executeed in " + executionTime + "ms");
		logger.info("\n#############################################################\n");
		
		return proceed;
	}
	
	@Pointcut("execution(* org.juon.jpashop.repository.*.*(..))")
	private void repositoryLevel() {}
	
	@Before("repositoryLevel()")
	public void logDaoParameter(JoinPoint joinPoint) {
		logger.info("\n##################### logDaoAccess ################################\n");
		logger.info(joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature());
		logger.info(joinPoint.getSignature().getDeclaringTypeName());
		Object[] args = joinPoint.getArgs();
		Arrays.asList(args).stream().map(arg -> String.valueOf(arg)).forEach(System.out::println);
		logger.info("\n#############################################################\n");
	}	

}

```

### Locale Configuration (Localization)
``` java
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class LocaleResolverConfig extends WebMvcConfigurerAdapter{
	
	@Bean
	public LocaleResolver localeResolver() {
		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setCookieName("lang");
		
		return resolver;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor localeChangeInterceptor = 
			new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		
		registry.addInterceptor(localeChangeInterceptor);
	}
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = 
			new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:/i18n/messages");
		messageSource.setDefaultEncoding("UTF-8");
		
		return messageSource;
	}
}
```
