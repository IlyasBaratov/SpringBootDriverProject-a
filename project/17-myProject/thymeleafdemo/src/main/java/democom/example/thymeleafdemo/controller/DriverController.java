package democom.example.thymeleafdemo.controller;

import democom.example.thymeleafdemo.dao.DriverRepository;
import democom.example.thymeleafdemo.entity.Driver;
import democom.example.thymeleafdemo.service.DriverService;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/home")
public class DriverController {

    DriverService driverService;
    DriverRepository driverRepository;

    public DriverController(DriverService driverService,
                            DriverRepository driverRepository) {
        this.driverService = driverService;
        this.driverRepository = driverRepository;
    }

    @GetMapping
    public String mainPage() {
        return "driver/home";
    }

    @GetMapping("/sign-up")
    public String confirming(Model model) {
        Driver driver = new Driver();

        model.addAttribute("driver", driver);
        return "driver/action";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("driver") Driver driver,
                       BindingResult bindingResult, Model model) {
        System.out.println("Received Date: " + driver.getDob());
        if (bindingResult.hasErrors()) {
            model.addAttribute("driver", driver);
            return "driver/action";

        }
        driverService.save(driver);

        return "driver/saving";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("firstName", "");
        model.addAttribute("lastName", "");
        model.addAttribute("email", "");
//        model.addAttribute("driver", new Driver());
        return "driver/login";
    }

    //@PostMapping("/login-process")
//    public String loginProcess(@Valid @ModelAttribute("firstName") String firstName,
//                               @ModelAttribute("lastName") String lastName,
//                               @ModelAttribute("email") String email,
//                               Model model){
//        List<Driver> drivers= driverRepository.findAllByEmail(email);
//        System.out.println(drivers);
////        if (driverRepository.findByEmail(email).isEmpty()){
////            model.addAttribute("errorMessage", "Email not found. Please sign up first.");
////            return "driver/login";
////        }
////        else {
////            return "driver/saving";
////        }
//        if (drivers.isEmpty()){
//            model.addAttribute("errorMessage", "Email not found. Please sign up first.");
//            return "driver/login";
//        }
//        Driver driver = drivers.get(0);
//        if (driver.getFirstName().equalsIgnoreCase(firstName) && driver.getLastName().equalsIgnoreCase(lastName)){
//            return "driver/saving";
//        }
//        else {
//
//            model.addAttribute("errorMessage", "Email not found. Please sign up first.");
//            return "driver/login";
//        }
//    }
    @RequestMapping("/login-process")
    public String loginProcess(@RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName,
                               @RequestParam("email") String email,
                               Model model) {
        Optional<Driver> optionalDriver = driverRepository.findByEmail(email);
        System.out.println(optionalDriver.toString());
        if (optionalDriver.isEmpty()) {
            model.addAttribute("errorMessage", "Email not found. Please sign up first.");
            return "driver/login";
        }
        Driver driver = optionalDriver.get();
        if (driver.getFirstName().equalsIgnoreCase(firstName) &&
                driver.getLastName().equalsIgnoreCase(lastName)) {
            return "driver/saving";
        }
        else {
            model.addAttribute("errorMessage", "Email not found. Please sign up first.");
            return "driver/login";
        }
//        Driver driver1 = drivers.get(0);
//
//        if (driver1.getFirstName().equalsIgnoreCase(firstName)
//        && driver1.getLastName().equalsIgnoreCase(lastName)){
//            return "driver/saving";
//        }
//        else {
//            model.addAttribute("errorMessage", "Email not found. Please sign up first.");
//            return "driver/login";
//        }

    }


    @GetMapping("/reserve")
    public String Reserve(){
        return "driver/reserve";
    }



    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }

}
