package ua.goit.dev6.signin;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.goit.dev6.account.UserDAO;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UsersService userService;

    private final SecurityServiceImpl securityService;

    private final UserValidator userValidator;

    @GetMapping("/registration")
    public String registration(Model model) {
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }

        model.addAttribute("userForm", new UserDAO());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") UserDAO userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.saveUser(userForm);
        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean authenticated = authentication.isAuthenticated();
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }

        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean authenticated = authentication.isAuthenticated();

        return "login";
    }


//    @GetMapping({ "/welcome"})
//    public String welcome(Model model) {
//        return "welcome";
//    }
}
