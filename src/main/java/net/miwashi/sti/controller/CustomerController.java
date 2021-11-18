package net.miwashi.sti.controller;

import net.miwashi.sti.model.Artist;
import net.miwashi.sti.model.Customer;
import net.miwashi.sti.model.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/customer")
public class CustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger( CustomerController.class );

    Model model;

    @Autowired
    public void setModel(Model model) {
        this.model = model;
    }

    @GetMapping(path = "/{id}", produces = { "application/json"})
    public Customer findCustomer(@PathVariable(value = "id", required = true)  Integer id, HttpServletResponse response) {
        return model.getCustomerById(id);
    }

    /**
     *
     * @return
     */
    @GetMapping(path = "/list", produces = { "application/json"})
    public Collection<Customer> listCustomers() {
        return model.getCustomers();
    }
}