package com.cibt.springapp.controllers;

import com.cibt.springapp.dao.CustomerAddressBookDAO;
import com.cibt.springapp.dao.StudentDAO;
import com.cibt.springapp.models.AddressBook;
import com.cibt.springapp.models.Customer;
import com.cibt.springapp.models.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/")
public class HomeController {

    /* @Autowired
    CustomerAddressBookDAO customerDAO; */

    @Autowired
    StudentDAO studentDAO;

    /* @GetMapping(value="/")
    public String index(Model model, Customer customer, AddressBook addressbook) {
        // model.addAttribute("name", "Deep Shrestha");
        // model.addAttribute("colors", new String[] {"Red", "Green", "Blue"});
        model.addAttribute("customers", customerDAO.fetchAll());
        return "home";
    }

    @GetMapping(value="/add")
    public String addForm(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "customer_add";
    }

    @PostMapping(value="/save")
    public String save(Customer customer, Model model) {
        AddressBook addressBook = customer.getAddressBook();
        customerDAO.insert(addressBook);
        customerDAO.insert(customer);
        return "redirect:/";
    } */

    /* @PostMapping(value="/save")
    @ResponseBody
    public Customer save(Customer customer, Model model) {
        return customer;
    } */

    @GetMapping(value="/")
    public String index(Model model) {
        model.addAttribute("students", studentDAO.fetchAll());
        return "home";
    }

    @GetMapping(value="/add")
    public String addForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "add";
    }

    @PostMapping(value="/save")
    public String save(@ModelAttribute Student student,  Model model) {
        int result = studentDAO.insert(student);
        System.out.println(student.getId());
        // Student lastInsertedId = studentDAO.getLastInsertedId();
        // System.out.println("Last Inserted ID :" + lastInsertedId.getId());

        if(result > 0) {
            model.addAttribute("students", studentDAO.fetchAll());
        }
            
        return "redirect:/";
    }

    @GetMapping(value="/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        model.addAttribute("student", studentDAO.fetchById(id));
        return "edit";
    }

    @PostMapping(value="/{id}")
    public String update(@PathVariable int id, @ModelAttribute Student student,  Model model) {

        student.setId(id);
        student.setStudentName(student.getStudentName());
        student.setStudentAddress(student.getStudentAddress());
        student.setStudentEmail(student.getStudentEmail());
        studentDAO.update(student);
            
        return "redirect:/";
    }

    @GetMapping(value="/delete/{id}")
    public String delete(@PathVariable int id, Model model) {
        int result = studentDAO.delete(id);

        System.out.println(result);
        
        if(result > 0) {
            model.addAttribute("students", studentDAO.fetchAll());
        }
        return "redirect:/";
    }
    
}