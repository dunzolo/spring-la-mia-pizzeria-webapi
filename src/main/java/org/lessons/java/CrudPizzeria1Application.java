package org.lessons.java;

import java.time.LocalDate;

import org.lessons.java.auth.pojo.Role;
import org.lessons.java.auth.pojo.User;
import org.lessons.java.auth.serv.RoleService;
import org.lessons.java.auth.serv.UserService;
import org.lessons.java.pojo.Ingrediente;
import org.lessons.java.pojo.OffertaSpeciale;
import org.lessons.java.pojo.Pizza;
import org.lessons.java.serv.IngredienteService;
import org.lessons.java.serv.OffertaSpecialeService;
import org.lessons.java.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class CrudPizzeria1Application implements CommandLineRunner {
	
	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private OffertaSpecialeService offertaSpecialeService;
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(CrudPizzeria1Application.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Role roleUser = new Role("USER");
		Role roleAdmin = new Role("ADMIN");
		
		roleService.save(roleUser);
		roleService.save(roleAdmin);
		
		final String pws = new BCryptPasswordEncoder().encode("pws");
		
		User userUser = new User("user", pws, roleUser);
		User userAdmin = new User("admin", pws, roleAdmin);
				
		userService.save(userUser);
		userService.save(userAdmin);
		
		Ingrediente i1 = new Ingrediente("ing1", "ingrediente numero 1");
		Ingrediente i2 = new Ingrediente("ing2", "ingrediente numero 2");
		Ingrediente i3 = new Ingrediente("ing3", "ingrediente numero 3");
		
		ingredienteService.saveIngrediente(i1);
		ingredienteService.saveIngrediente(i2);
		ingredienteService.saveIngrediente(i3);
		
		Pizza p1 = new Pizza("Margherita", 
				"Pizza Margherita", 
				"https://primochef.it/wp-content/uploads/2019/08/SH_pizza_fatta_in_casa-1200x800.jpg.webp",
				8);
		
		Pizza p2 = new Pizza("Cotto", 
				"Pizza col Cotto", 
				"https://efru.it/efru-upload/2020/03/pizza-prosciutto-cotto.jpg",
				9);
		
		Pizza p3 = new Pizza("Crudo", 
				"Pizza col Crudo", 
				"https://wips.plug.it/cips/buonissimo.org/cms/2011/12/pizza-al-prosciutto-crudo.jpg?w=712&a=c&h=406",
				10);
		
		pizzaService.savePizza(p1);
		pizzaService.savePizza(p2);
		pizzaService.savePizza(p3);
		
		OffertaSpeciale s1 = new OffertaSpeciale(LocalDate.now(), LocalDate.parse("2023-06-10"), "sconto1", 10, p1);
		OffertaSpeciale s2 = new OffertaSpeciale(LocalDate.now(), LocalDate.parse("2023-06-10"), "sconto2", 20, p2);
		OffertaSpeciale s3 = new OffertaSpeciale(LocalDate.now(), LocalDate.parse("2023-06-10"), "sconto3", 30, p3);
		
		offertaSpecialeService.save(s1);
		offertaSpecialeService.save(s2);
		offertaSpecialeService.save(s3);
	}
}

