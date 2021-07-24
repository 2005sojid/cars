package com.example.cars.controller;

import com.example.cars.dto.CarDto;
import com.example.cars.dto.UserDto;
import com.example.cars.model.Car;
import com.example.cars.security.SecurityUser;
import com.example.cars.service.CarService;
import com.example.cars.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MainController {
    private final UserService userService;
    private final CarService carService;

    @Autowired
    public MainController(UserService userService, CarService carService) {
        this.userService = userService;
        this.carService = carService;
    }

    @GetMapping
    public String entryPoint(Model model){
        SecurityUser securityUser;
        try {
            securityUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (securityUser != null)
                model.addAttribute("id", securityUser.getId());
        }
        catch (Exception e){
            model.addAttribute("id", null);
        }

        return "index";
    }
    @GetMapping("/register")
    public ModelAndView register(){
        Map<String, UserDto> user = new HashMap<>();
        user.put("user", new UserDto());
        return new ModelAndView("register", user);
    }

    @GetMapping("/user/{id}")
    public Object getUserPage(@PathVariable("id") Long id, Model model){
        SecurityUser securityUser =  (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!securityUser.getId().equals(id)){
            return new ResponseEntity<>(HttpStatus.LOCKED);
        }
        model.addAttribute("user", userService.findUserById(id));
        return "user";
    }

    @GetMapping("/user/{id}/cars")
    public Object getCarsPage(@PathVariable Long id, Model model){
        SecurityUser securityUser =  (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!securityUser.getId().equals(id)){
            return new ResponseEntity<>(HttpStatus.LOCKED);
        }
        model.addAttribute("user", userService.findUserById(id));
        model.addAttribute("cars", carService.findAllByUserId(id));
        model.addAttribute("car", new CarDto());
        return "cars";
    }

    @PostMapping("/user/{id}/cars")
    public Object addCar(@PathVariable Long id, CarDto carDto){
        SecurityUser securityUser =  (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!securityUser.getId().equals(id)){
            return new ResponseEntity<>(HttpStatus.LOCKED);
        }
        Car car = CarDto.toCar(carDto, userService.findUserById(id));
        carService.saveCar(car);
        return "redirect:/user/" + id + "/cars";
    }

}
