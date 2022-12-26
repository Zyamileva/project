package ua.goit.dev6.signin;

import ua.goit.dev6.account.UserDAO;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface UsersService {

    UserDAO saveUser(UserDAO usersDto);


    List<UserDAO> findByName(String name);

}