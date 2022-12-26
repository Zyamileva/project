package ua.goit.dev6.signin;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.goit.dev6.account.UserDAO;
import ua.goit.dev6.account.UserRepository;

import java.util.List;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service

public class UsersServiceImpl implements UsersService {
    private final UserRepository userRepository;
//    private final Converter<UsersDto, UsersDao> converterUser;

    //    @Override
//    public UserDAO saveUser(UserDAO user) {
//        return userRepository.save(user);
//    }
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDAO saveUser(UserDAO user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


    @Override
    public List<UserDAO> findByName(String query) {
        return userRepository.findByUsername("%" + query + "%").stream()
                .collect(Collectors.toList());
    }

}
