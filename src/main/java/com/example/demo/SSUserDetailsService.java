package com.example.demo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Transactional
@Service
public class SSUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public SSUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        try{
            User user=userRepository.findByUsername(username);
            if(user == null){
                System.out.println("User is not found with the username" + user.toString());
                return null;
            }
            System.out.println(" User is found with the username " + user.toString());
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    getAuthorities(user)
            );
        } catch (Exception e){
            throw new UsernameNotFoundException("User is not found");
        }

        }
        private Set<GrantedAuthority> getAuthorities(User user){
        Set<GrantedAuthority> authorities=new HashSet<>();
        for(Role role : user.getRoles()){
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRole());
            authorities.add(grantedAuthority);
        }
        System.out.println("Those authorited users are " + authorities.toString());
        return authorities;
        }
}

