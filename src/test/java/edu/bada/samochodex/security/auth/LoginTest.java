package edu.bada.samochodex.security.auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static edu.bada.samochodex.security.ApplicationUserRole.ADMIN;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Disabled("Bad database server")
class LoginTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    void loginMateuszTest() throws Exception {
        mockMvc.perform(formLogin("/logowanie").user("mateusz").password("admin"))
        .andExpect(authenticated().withUsername("mateusz").withAuthorities(ADMIN.getGrantedAuthorities()));
    }

    @Test
    void loginKacperTest() throws Exception {
        mockMvc.perform(formLogin("/logowanie").user("kacper").password("admin"))
                .andExpect(authenticated().withUsername("kacper").withAuthorities(ADMIN.getGrantedAuthorities()));
    }
}
