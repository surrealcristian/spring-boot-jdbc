package hello;

import hello.dao.CustomerRepository;
import hello.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.sql.DataSource;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("DATASOURCE: " + this.dataSource);
		
		if (args.length <= 0) {
			System.err.println("usage: java xxx.jar {insert name email | display}");
			System.exit(1);
		}

		if (args[0].equalsIgnoreCase("insert")) {
			String name = args[1];
			String email = args[2];
			this.customerRepository.addCustomer(name, email);
			
			System.out.println("Customer added.");
			System.exit(0);
		}
		
		if (args[0].equalsIgnoreCase("display")) {
			List<Customer> customers = this.customerRepository.findAll();

			customers.forEach(x -> System.out.println(x));

			System.exit(0);
		}
	}
}