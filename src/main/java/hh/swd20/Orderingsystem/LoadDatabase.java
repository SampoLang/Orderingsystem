package hh.swd20.Orderingsystem;

import java.text.SimpleDateFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hh.swd20.Orderingsystem.domain.Item;
import hh.swd20.Orderingsystem.domain.ItemRepository;
import hh.swd20.Orderingsystem.domain.Order;
import hh.swd20.Orderingsystem.domain.OrderRepository;
import hh.swd20.Orderingsystem.domain.User;
import hh.swd20.Orderingsystem.domain.UserRepository;
//Configuration class for loading test data into the database

@Configuration
public class LoadDatabase {
/*	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
	SimpleDateFormat fdate = new SimpleDateFormat("dd.MM.yyyy");
	@Bean
	CommandLineRunner initDatabase(ItemRepository itemrepository, OrderRepository orderRepository, UserRepository userRepository) {
		return args -> {
			HashSet<Item> items = new HashSet<Item>();
			Item item1 = new Item("Pen", "Best pen in the world",
			 "https://schneiderpen.com/static/product/4004675080080_1@L3ImqT9gBv10pzygXl10nUIgLz5unJjeZwVjrQVlZPfgM3Wuqzy0rFgwMJ50MKVeYJI4qTIhqPflZwO4ZwVj.jpg", "123-p"
			 , 10.2 );
			Item item2 = new Item("Eraser", "Good eraser", "https://5.imimg.com/data5/BP/YR/MY-16737638/school-rubber-500x500.jpg", "103->", 5 );
			Item item3 = new Item("Cat food", "Tasty salmon catfood", "https://tikipets.com/wp-content/themes/tikipets/img/slide-cat-kibble.png", "654-C", 15);
			Item item4 = new Item("Trampoline", "Huge trampoline", "https://www.flaghouse.com/productImages/image.axd/i.4540/w.1000/h.1000/xm.0/FlagHouse+Jogging+Trampoline_XL.jpg", "112-T", 500);
			items.add(item2);
			items.add(item1);
			User user1 = new User("Sampo", "$2a$10$NY2vapCt1O/KICuoUJUpveGPRXhupXshs2pe8YXkzEdzjmundCo6i", "ADMIN");
			
			log.info("Preloading " + itemrepository.save(item1));
			log.info("Preloading " + itemrepository.save(item2));
			log.info("Preloading " + itemrepository.save(item3));
			log.info("Preloading " + itemrepository.save(item4));
			log.info("Preloading" +userRepository.save(user1));
		};
	}*/
}
