package ua.goit.dev6.signin;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.goit.dev6.account.UserDAO;

import java.util.List;

@RequiredArgsConstructor
@Component
public class UserValidator implements Validator {
    private final UsersService userService;

    public boolean supports(Class<?> aClass) {
        return UserDAO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDAO user = (UserDAO) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() < 4 || user.getUsername().length() > 50) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        List<UserDAO> byName = userService.findByName(user.getUsername());
        if (!userService.findByName(user.getUsername()).isEmpty()) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 4 || user.getPassword().length() > 100) {
            errors.rejectValue("password", "Size.userForm.password");
        }

            if (!user.getPasswordConfirm().equals(user.getPassword())) {
                errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
            }
    }
}