package tn.aminesafi.estore.productservice.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("amine")
public class Controller {

    @GetMapping
    public String a() {
        return "aaaa";
    }
}
