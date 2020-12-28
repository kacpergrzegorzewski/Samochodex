package edu.bada.samochodex.security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService {

    private final ApplicationUserDao applicationUserDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationUserService(ApplicationUserDao applicationUserDao, PasswordEncoder passwordEncoder) {
        this.applicationUserDao = applicationUserDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = applicationUserDao.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));
        return new ApplicationUser(user);
    }

    public void registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        applicationUserDao.save(user);
    }
}
