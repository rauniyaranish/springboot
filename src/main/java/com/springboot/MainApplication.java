package com.springboot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.utility.TestUtility;

@SpringBootApplication
@EnableTransactionManagement
public class MainApplication {
	
	/*
	 * private TestUtility testUtility;
	 * 
	 * public MainApplication(TestUtility testUtility) { this.testUtility =
	 * testUtility; }
	 */
	 
	/*
	 * @Autowired public void setTestUtility(TestUtility testUtility) {
	 * this.testUtility = testUtility; }
	 */
	
	public static void main(String[] args) {
		/*
		 * ClassPathXmlApplicationContext con = new
		 * ClassPathXmlApplicationContext("config.xml"); TestUtility util =
		 * con.getBean("testUtility", TestUtility.class);
		 * System.out.println("testUtility > " + util.getUtilityName());
		 * ConfigurableApplicationContext context =
		 * SpringApplication.run(MainApplication.class, args);
		 * System.out.println("Enabled spring application"); TestUtility utility =
		 * context.getBean("testUtility", TestUtility.class);
		 * System.out.println("testUtility > " + utility.getUtilityName());
		 */
		SpringApplication.run(MainApplication.class, args);
    }
	
	/*
	 * @RequestMapping("/hello") public String testHello() {
	 * testUtility.setUtilityName("gesgesg"); return testUtility.getUtilityName(); }
	 * 
	 * @RequestMapping("/test") public String test() { return
	 * testUtility.getUtilityName(); }
	 */

	@Bean
	public PlatformTransactionManager transactionManager(MongoDatabaseFactory mongoDbFactory) {
		return new MongoTransactionManager(mongoDbFactory);
	}
}
