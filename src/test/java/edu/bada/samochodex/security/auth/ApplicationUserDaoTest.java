package edu.bada.samochodex.security.auth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import static edu.bada.samochodex.security.ApplicationUserRole.ADMIN;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
@Rollback(false)
class ApplicationUserDaoTest {

    @Autowired
    private ApplicationUserDao applicationUserDao;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void findByUsernameTest() {
        applicationUserDao.save(new ApplicationUser("test", "test", ADMIN.name(), true));
        ApplicationUser user = applicationUserDao.findByUsername("test").orElseThrow(() -> new UsernameNotFoundException("Username test not found"));

        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo("test");
        assertThat(user.getPassword()).isEqualTo("test");
        assertThat(user.getRole()).isEqualTo("ADMIN");
        assertThat(user.isEnabled()).isEqualTo(true);
    }

    @Test
    void findByUsernameTest_notFoundShouldThrowException() {
        // Given
        applicationUserDao.save(new ApplicationUser("test1", "test", ADMIN.name(), true));

        // When
        Throwable thrown = catchThrowable(() -> {
            applicationUserDao.findByUsername("test12").orElseThrow(() -> new UsernameNotFoundException("Username test12 not found"));
        });

        // Then
        assertThat(thrown)
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessageContaining("test12", "not found");
    }

    @Test
    void createUserAndSaveToDatabaseTest() {
        ApplicationUser user = new ApplicationUser("test123", "test", ADMIN.name(), true);
        ApplicationUser savedUser = applicationUserDao.save(user);

        ApplicationUser existUser = entityManager.find(ApplicationUser.class, savedUser.getId());

        assertThat(existUser).isNotNull();
        assertThat(existUser.getUsername()).isEqualTo(user.getUsername());
        assertThat(existUser.getPassword()).isEqualTo(user.getPassword());
        assertThat(existUser.getRole()).isEqualTo(user.getRole());
        assertThat(existUser.isEnabled()).isEqualTo(user.isEnabled());
    }

    // TODO: Testing constraints (password, role)
}