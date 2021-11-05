package com.sdp.project.util;

import com.sdp.project.model.User;
import com.sdp.project.repository.UserRepository;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class BlogUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public BlogUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User currentUser = userRepository.findByUsername(s);
        return new org.springframework.security.core.userdetails.User(currentUser.getUsername(), currentUser.getPassword()
                , true, true, true, true, AuthorityUtils.createAuthorityList(currentUser.getRole())); }
}