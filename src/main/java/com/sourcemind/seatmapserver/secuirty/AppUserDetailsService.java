package com.sourcemind.seatmapserver.secuirty;

import com.sourcemind.seatmapserver.model.User;
import com.sourcemind.seatmapserver.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmployee_Email(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return new AppUserPrincipal(user);

    }
}
