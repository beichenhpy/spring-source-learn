package cn.beichenhpy.bean.scope.controller;

import cn.beichenhpy.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Array;
import java.util.Arrays;

@RequestMapping
@Controller
public class BeanScopeController {


//    @Autowired
//    @Qualifier(value = "requestUser")
//    private User requestUser;
//
    @Autowired
    @Qualifier(value = "sessionUser")
    private User sessionUser;
//
//    @Autowired
//    @Qualifier(value = "applicationUser")
//    private User applicationUser;

//    @Autowired
//    private User singletonUser;

    @GetMapping({"/index", ""})
    public String index(Model model) {
//        model.addAttribute("requestUser", requestUser);
//        model.addAttribute("sessionUser", sessionUser);
//        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
//        System.out.println(Arrays.toString(beanDefinitionNames));
        return "index";
    }
}
