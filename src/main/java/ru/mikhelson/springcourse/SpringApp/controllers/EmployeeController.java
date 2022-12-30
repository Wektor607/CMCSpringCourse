package ru.mikhelson.springcourse.SpringApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.mikhelson.springcourse.SpringApp.models.Product;
import ru.mikhelson.springcourse.SpringApp.repositories.ProductsRepository;
import ru.mikhelson.springcourse.SpringApp.services.AdminService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class EmployeeController
{
    private final AdminService adminService;

    private ProductsRepository productsRepository;
    @Autowired
    public EmployeeController(AdminService adminService,
                              ProductsRepository productsRepository)
    {
        this.adminService = adminService;
        this.productsRepository = productsRepository;
    }

    @GetMapping("/employee")
    public String addGood()
    {
        adminService.doAdminStuff();
        return "employee";
    }

    @PostMapping("/employee")
    public String performAddGoods(@ModelAttribute("product_name") @Valid String product_name,
                                  @ModelAttribute("amount") @Valid int amount, Model model)
    {
        Product product = new Product();
        product.setName(product_name);
        product.setCount(amount);

        Product searchProduct = productsRepository.findProductByName(product.getName());
        if(searchProduct == null)
        {
            productsRepository.save(product);
        }
        else
        {
            int new_count = product.getCount() + searchProduct.getCount();
            searchProduct.setCount(new_count);
            productsRepository.save(searchProduct);
        }
        model.addAttribute("resp", true);
        return "employee";
    }
}
