package hh.swd20.Orderingsystem.web;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.swd20.Orderingsystem.domain.Item;
import hh.swd20.Orderingsystem.domain.ItemRepository;
import hh.swd20.Orderingsystem.domain.OrderRepository;
//Controller for handling ITEM crud operations
@Controller
public class ItemController {
	@Autowired
	private ItemRepository itemrepository;
	
	@Autowired
	private OrderRepository orderrepository;
	//Session for storing itemlist info
	@Autowired
	private HttpSession session;
	
	//Get all items
	@GetMapping(value="/itemlistadmin")
	public String itemsList(Model model) {
		model.addAttribute("items", itemrepository.findAll());
		model.addAttribute("neworder", session.getAttribute("neworder"));
		return "itemlistadmin";
	}
	//add item to the model
	//@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value="/additemadmin")
	public String addToItems(Model model) {
		model.addAttribute("item", new Item());
		return "additemadmin";
	}
	//delete item
	@GetMapping(value="delete/{id}")
	//@PreAuthorize("hasRole('ADMIN')")
	public String deleteItem(@PathVariable("id") Long itemId, Model model) {
		itemrepository.deleteById(itemId);
		return "redirect:../itemlistadmin";
	}
	//Add chosen item by id to the model for edit
	@GetMapping(value="/edititem/{id}")
	//@PreAuthorize("hasRole('ADMIN')")
	public String editItem(@PathVariable("id") Long itemId, Model model) {
		model.addAttribute("item", itemrepository.findById(itemId));
		return "edititem";
	}
	//save new item to the catalog
	@PostMapping(value="/saveitem")
	//@PreAuthorize("hasRole('ADMIN')")
	public String saveItem(@Valid Item item, BindingResult bindingresult) {
		if(bindingresult.hasErrors()) {
			System.out.println("ERROR");
			return "/additemadmin";
		}
		else {
		itemrepository.save(item);
		return "redirect:/itemlistadmin";
		}		
	}
	

	
}
