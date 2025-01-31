package com.example.userservice.config;


import com.example.userservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//Carga la info de usuario, como su nombre y credenciales
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.userservice.models.User userEntity = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with UserName: " + username));
//        TODO: validate email
//        if (!userEntity.isEmailValidate()) {
//            throw new UsernameNotFoundException("The email is not yet validated");
//        }
        return new User(userEntity.getEmail(), userEntity.getPassword(), AuthorityUtils.createAuthorityList(userEntity.getRole().toString()));
    }
}
