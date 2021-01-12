package edu.bada.samochodex.service;

import edu.bada.samochodex.dao.AddressDao;
import edu.bada.samochodex.model.Address;
import edu.bada.samochodex.model.Post;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
@Disabled
class AddressServiceTest {

    @Autowired
    private AddressService addressService;

    @MockBean
    private AddressDao addressDao;

    static Long id = 1L;

    @Test
    void getById() {
        Post post = new Post("00-001", "Warszawa");
        post.setId(id);

        Address address = new Address("Warszawa", "Nowowiejska", "1", post);
        address.setId(id);

        when(addressDao.findById(id)).thenReturn(java.util.Optional.of(address));
        assertThat(addressService.getById(id)).isEqualTo(address);
    }

    @Test
    void getAll() {
        Post post = new Post("00-001", "Warszawa");
        post.setId(id);

        Address address1 = new Address("Warszawa", "Nowowiejska", "1", post);
        address1.setId(id);

        Address address2 = new Address("Warszawa", "Nowowiejska", "2", post);
        address2.setId(id+1);

        List<Address> addresses = Arrays.asList(address1, address2);

        when(addressDao.findAll()).thenReturn(addresses);
        assertThat(addressService.getAll()).isEqualTo(addresses);
    }

    @Test
    void save() {
        Post post = new Post("00-001", "Warszawa");
        post.setId(id);

        Address address = new Address("Warszawa", "Nowowiejska", "1", post);
        address.setId(id);

        when(addressDao.save(address)).thenReturn(address);
        assertThat(addressService.save(address)).isEqualTo(address);
    }
}
