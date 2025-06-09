package com.wavey.waveyspringbootmaven;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;


@Configuration
@EnableWebSecurity // Enables Spring Security's web security support
public class SecurityConfig {

    // This bean provides the BCryptPasswordEncoder for hashing passwords
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Defines a UserDetailsService bean to load user-specific data during authentication.
     * This implementation fetches user details from the UsersRepository based on the email (username).
     *
     * @param usersRepository The repository to fetch user data from.
     * @return A UserDetailsService implementation.
     */
    @Bean
    public UserDetailsService userDetailsService(UsersRepository usersRepository) {
        return email -> { // Lambda for loadUserByUsername, which takes a username (here, email)
            // Find the user by email from your repository
            Users user = usersRepository.findFirstByEmail(email);
            if (user == null) {
                // If user is not found, throw UsernameNotFoundException
                throw new UsernameNotFoundException("User not found with email: " + email);
            }
            // Build Spring Security User object with email, encoded password, and roles.
            // Spring Security will use this to compare the provided password with the stored one.
            // For simplicity, we are granting a default "USER" role. You can extend this for actual roles
            // based on your Users entity if you add a roles field.
            return User.withUsername(user.getEmail())
                    .password(user.getPassword()) // Already BCrypted
                    .roles("USER") // Assign a default role for authenticated users
                    .build();
        };
    }

    /**
     * Defines an AuthenticationProvider that uses the UserDetailsService and passwordEncoder.
     * This provider is responsible for authenticating users based on their username/email and password.
     *
     * @param userDetailsService The UserDetailsService to load user details.
     * @param passwordEncoder The password encoder to compare raw passwords with encoded passwords.
     * @return A DaoAuthenticationProvider instance.
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserDetailsService userDetailsService, BCryptPasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService); // Set the UserDetailsService
        authProvider.setPasswordEncoder(passwordEncoder);       // Set the password encoder
        return authProvider;
    }

    /**
     * Configures the security filter chain, defining security rules for HTTP requests.
     * This includes CSRF protection, URL authorization, form login, and logout.
     *
     * @param http The HttpSecurity object to configure.
     * @return A configured SecurityFilterChain.
     * @throws Exception if an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Explicitly disable CSRF protection. This is crucial for non-browser clients
                // like Postman making POST requests without a CSRF token.
                // RE-ENABLE FOR PRODUCTION ENVIRONMENTS and handle CSRF tokens properly.
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        // Allow unauthenticated access to the user registration endpoint
                        .requestMatchers("/api/users/register").permitAll()
                        // Allow unauthenticated access to retrieve all waves
                        .requestMatchers("/api/waves/getWaves").permitAll()
                        // Configure the login processing URL and allow all to access it
                        // Spring Security handles POST to this URL
                        .requestMatchers("/api/login").permitAll()
                        // Allow direct access to login success, login failure, and logout success URLs
                        .requestMatchers("/api/users/loginSuccess", "/api/users/loginFailure", "/api/users/loggedOut").permitAll()
                        // All other requests require authentication
                        .anyRequest().authenticated()
                )
                // By not setting SessionCreationPolicy.STATELESS, Spring Security will manage sessions
                // (typically IF_REQUIRED), which allows session cookies to be used.
                .formLogin(formLogin -> formLogin
                        .loginProcessingUrl("/api/login") // The URL to which the login form submits the username and password (POST request)
                        .usernameParameter("email") // Specify the parameter name for the username (email in your case)
                        .passwordParameter("password") // Specify the parameter name for the password
                        .defaultSuccessUrl("/api/users/loginSuccess", true) // Redirect on successful login (GET request)
                        .failureUrl("/api/users/loginFailure") // Redirect on failed login (GET request)
                        .permitAll() // Ensure all related form login URLs are public
                )
                .logout(logout -> logout
                        .logoutUrl("/api/logout") // URL to trigger logout (typically POST)
                        .logoutSuccessUrl("/api/users/loggedOut") // URL to redirect after successful logout (GET request)
                        .permitAll() // Ensure all related logout URLs are public
                );

        return http.build();
    }
}
