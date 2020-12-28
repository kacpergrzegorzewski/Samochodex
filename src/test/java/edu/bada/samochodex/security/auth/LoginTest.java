package edu.bada.samochodex.security.auth;

import edu.bada.samochodex.security.ApplicationUserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;

@SpringBootTest
@AutoConfigureMockMvc
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
        mockMvc.perform(formLogin().user("mateusz").password("admin"))
                .andExpect(authenticated().withUsername("mateusz").withAuthorities(ApplicationUserRole.valueOf("ADMIN").getGrantedAuthorities()));
    }

    @Test
    void loginKacperTest() throws Exception {
        mockMvc.perform(formLogin().user("kacper").password("admin"))
                .andExpect(authenticated().withUsername("kacper").withAuthorities(ApplicationUserRole.valueOf("ADMIN").getGrantedAuthorities()));
    }

    @Test
    void loginMonikaTest() throws Exception {
        mockMvc.perform(formLogin().user("monika").password("12345"))
                .andExpect(authenticated().withUsername("monika").withAuthorities(ApplicationUserRole.valueOf("EMPLOYEE").getGrantedAuthorities()));
    }

    @Test
    void loginZbyszekTest() throws Exception {
        mockMvc.perform(formLogin().user("zbyszek").password("123"))
                .andExpect(authenticated().withUsername("zbyszek").withAuthorities(ApplicationUserRole.valueOf("CLIENT").getGrantedAuthorities()));
    }
}