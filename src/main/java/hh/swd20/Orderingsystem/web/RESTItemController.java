package hh.swd20.Orderingsystem.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.swd20.Orderingsystem.domain.Item;
import hh.swd20.Orderingsystem.domain.ItemRepository;
import hh.swd20.Orderingsystem.domain.Order;
import hh.swd20.Orderingsystem.domain.OrderRepository;
@CrossOrigin
@Controller
public class RESTItemController {
	@Autowired
	private ItemRepository repository; 
	

	// RESTful service to get all departments
    @RequestMapping(value="/items", method = RequestMethod.GET)
    public @ResponseBody List<Item> getDepartmentsRest() {	
        return (List<Item>) repository.findAll();
    }    

	// RESTful service to get department by id
    @RequestMapping(value="/items/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Item> findItemRest(@PathVariable("id") Long itemId) {	
    	return repository.findById(itemId);
    } 
    
    // RESTful service to save new department
    @RequestMapping(value="/items", method = RequestMethod.POST)
    public @ResponseBody Item saveItemRest(@RequestBody Item item) {	
    	return repository.save(item);
    }
}
