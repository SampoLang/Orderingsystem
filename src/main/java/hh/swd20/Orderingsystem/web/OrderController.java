package hh.swd20.Orderingsystem.web;


import java.security.Principal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashSet;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hh.swd20.Orderingsystem.domain.Item;
import hh.swd20.Orderingsystem.domain.ItemRepository;
import hh.swd20.Orderingsystem.domain.Order;
import hh.swd20.Orderingsystem.domain.OrderRepository;
import hh.swd20.Orderingsystem.domain.User;
import hh.swd20.Orderingsystem.domain.UserRepository;
//Controller for handling ORDER crud operations for admin
@Controller
public class OrderController {
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date now;
	NumberFormat twodecimal = new DecimalFormat("#0.00");
	@Autowired
	private OrderRepository orderrepository;
	@Autowired
	private ItemRepository itemrepository;
	
	@Autowired
	private HttpSession session;
	@Autowired
	private UserRepository userRepository;
	double total = 0;
	// Get all orders
	@GetMapping(value="/orderlistadmin")
	public String listOrders(Model model) {
		model.addAttribute("orders", orderrepository.findAll());
		return "orderlistadmin";
	}

	@GetMapping(value="/deleteorder/{id}")
	public String deleteOrder(@PathVariable("id")Long orderId, Model model) {
		orderrepository.deleteById(orderId);
		return "redirect:../orderlistadmin";
	}
	//Add chosen order by id to the model
	@GetMapping(value="/editorder/{id}")
	public String editItem(@PathVariable("id") Long orderId, Model model) {
		model.addAttribute("item", orderrepository.findById(orderId));
		return "editorderadmin";
	}
 
	// adds item to order
	@GetMapping(value="buyitem/{id}")
	public String buy(@PathVariable("id") Long itemId) {
		//Create list of items in session to be later added to order
		Set<Item> cart = (Set<Item>)session.getAttribute("cart");
		if(cart == null) {
			System.out.println("Creating new itemlist");
			cart = new HashSet<>();
			session.setAttribute("cart", cart);
		}
		//find added items cost and add it to the session total
		Item item = itemrepository.findById(itemId).get();
		total += item.getPrice();
		cart.add(item);
		session.setAttribute("total", twodecimal.format(total));
		return "redirect:/itemlistadmin";
	}
	//Reset cart items
	@GetMapping(value="/resetitems")
	public String resetItems(){
		Set<Item> cart = (Set<Item>)session.getAttribute("cart");
		if(cart != null){
			cart.clear();
			total = 0;
			session.setAttribute("total", total);
		}
		return "redirect:/itemlistadmin";
	}
	//save order to the database
	@PostMapping(value="/saveorder")
	public String saveOrder(Model model, HttpServletRequest request) {
		//get connected users name
		Principal principal = request.getUserPrincipal();
		User user = userRepository.findByUsername(principal.getName());
		Order neworder = new Order();
		//get items to be added to order
		Set<Item> newItems = (Set<Item>)session.getAttribute("cart");
		neworder.setOrderDate(now);
		neworder.setItems(newItems);
		//Total from session
		neworder.setTotal(total);
		neworder.setUser(user);
		orderrepository.save(neworder);
		//clear cart and total
		Set<Item> cart = (Set<Item>)session.getAttribute("cart");
		cart.clear();
		total = 0;
		session.setAttribute("total", total);
		return "redirect:/itemlistadmin";
		
	}
	
}
