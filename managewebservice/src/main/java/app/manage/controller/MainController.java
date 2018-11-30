package app.manage.controller;

import app.manage.domain.UserRoles;
import app.manage.domain.Users;
import app.manage.repositories.RoleRepository;
import app.manage.repositories.UserRepository;
import app.manage.utils.Subjects;
import app.manage.utils.Utils;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@RestController
public class MainController {
    public static final String EMAIL_REGEX = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b";
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public MainController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/")
    public ModelAndView getAllUsers(ModelAndView view) {
        ArrayList<Users> users = (ArrayList<Users>) userRepository.findAll();
        view.addObject("total", users.size());
        view.addObject("users", users);
        view.setViewName("home");
        return view;
    }

    @GetMapping("/welcome")
    public ModelAndView welcome(@RequestParam String email){
        RestTemplate restTemplate = new RestTemplate();
        try {
        restTemplate.getForObject(
                "http://localhost:2204/welcome?email=" + email, String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/login");
    }
    @GetMapping("/update")
    public ModelAndView updateUser(@RequestParam String email) {
//        if (!userRepository.exists(email)) {//TODO Flipped boolean to "not" for test purposes
            RestTemplate restTemplate = new RestTemplate();

            String responseString = null;
            try {
                responseString = restTemplate.getForObject(
                        "http://localhost:2204/forgot?email=" + email, String.class);
            } catch (Exception e) {
                e.printStackTrace();
                return new ModelAndView("redirect:/forgot?unavailable");
            }

            /*if (responseString != null) {
                Users userFromMailer = new Gson().fromJson(responseString, Users.class);
                userRepository.save(userFromMailer);
            }*/
            return new ModelAndView("redirect:/login");
        } /*else {
            return new ModelAndView("redirect:/forgot?not_exists");
        }
    }*/

    @GetMapping("/register")
    public ModelAndView registerGet() {
        return new ModelAndView("register");
    }

    @PostMapping("/register")
    public ModelAndView register(@RequestParam String email,
                                 @RequestParam String password,
                                 @RequestParam String confirm_password) throws Exception {
        if (password.equals(confirm_password)) {
            Users newUser = new Users();
            newUser.setUsername(email);
            newUser.setPassword(password);
            newUser.setEnabled(true);
            userRepository.save(newUser);
            roleRepository.save(new UserRoles(email, "ROLE_USER"));
        } else throw new Exception("Passwords doesn't match!");

        return new ModelAndView("redirect:/login");
    }

}
