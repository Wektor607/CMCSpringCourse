package ru.mikhelson.springcourse.SpringApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mikhelson.springcourse.SpringApp.models.Product;
import ru.mikhelson.springcourse.SpringApp.repositories.ProductsRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ClientController
{
    private ProductsRepository productsRepository;
    @Autowired
    public ClientController(ProductsRepository productsRepository)
    {
        this.productsRepository = productsRepository;
    }

    @GetMapping("/table")
    @ResponseBody
    public List<Product> show()
    {
        return productsRepository.findAll();
    }

    @GetMapping("/client")
    public String getGood()
    {
        return "client";
    }

    @PostMapping("/client")
    public String performGetGoods(@ModelAttribute("product_name") @Valid String product_name,
                                  @ModelAttribute("amount") @Valid int amount,Model model)
    {
        boolean bool_param = true;
        Product searchProduct = productsRepository.findProductByName(product_name);

        if(searchProduct != null)
        {
            int new_count = searchProduct.getCount() - amount;
            if (new_count >= 0)
            {
                searchProduct.setCount(new_count);
                productsRepository.save(searchProduct);
            }
            else { bool_param = false; }
        }
        else { bool_param = false; }

        System.out.println(bool_param);
        model.addAttribute("resp", bool_param);
        return "client";
    }

}
