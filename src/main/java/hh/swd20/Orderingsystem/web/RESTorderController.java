package hh.swd20.Orderingsystem.web;

import java.util.List;
import java.util.Optional;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.swd20.Orderingsystem.domain.ItemRepository;
import hh.swd20.Orderingsystem.domain.Order;
import hh.swd20.Orderingsystem.domain.OrderRepository;
@CrossOrigin
@Controller
public class RESTorderController {
	@Autowired
	private OrderRepository repository;
	
	@Autowired 
	private ItemRepository itemrepository;

	// RESTful service to get all orders
    @RequestMapping(value="/orders", method = RequestMethod.GET)
    public @ResponseBody List<Order> getOrdersRest() {	
        return (List<Order>) repository.findAll();
    }    

	// RESTful service to get order by id
    @RequestMapping(value="/orders/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Order> findOrderRest(@PathVariable("id") Long rId) {	
    	return repository.findById(rId);
    } 
    
    // RESTful service to save new order
    @RequestMapping(value="/orders", method = RequestMethod.POST)
    public @ResponseBody Order saveOrderRest(@RequestBody Order order) {	
    	return repository.save(order);
    }
    /*
    @PutMapping("/{orderId}/items/{itemId}")
    public Order addItemToOrder(@PathVariable Long orderId, @PathVariable Long itemId) {
    	Order order = repository.findById(orderId).get();
    	Item item = itemrepository.findById(itemId).get();
    	return repository.save(order);
    } */
}
