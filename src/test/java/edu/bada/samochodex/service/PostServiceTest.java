package edu.bada.samochodex.service;

import edu.bada.samochodex.dao.PostDao;
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
class PostServiceTest {

    @Autowired
    private PostService postService;

    @MockBean
    private PostDao postDao;

    static Long id = 1L;

    @Test
    void getById() {
        Post post = new Post("00-001", "Warszawa");
        post.setId(id);

        when(postDao.findById(id)).thenReturn(java.util.Optional.of(post));
        assertThat(postService.getById(id)).isEqualTo(post);
    }

    @Test
    void getByPostCode() {
        Post post = new Post("00-001", "Warszawa");
        post.setId(id);

        when(postDao.findPostByKodPocztowy("00-001")).thenReturn(post);
        assertThat(postService.getByPostCode("00-001")).isEqualTo(post);
    }

    @Test
    void getAll() {
        Post post1 = new Post("00-001", "Warszawa");
        post1.setId(id);

        Post post2 = new Post("00-002", "Warszawa");
        post2.setId(id+1);

        List<Post> posts = Arrays.asList(post1, post2);

        when(postDao.findAll()).thenReturn(posts);
        assertThat(postService.getAll()).isEqualTo(posts);
    }

    @Test
    void save() {
        Post post = new Post("00-001", "Warszawa");
        post.setId(id);

        when(postDao.save(post)).thenReturn(post);
        assertThat(postService.save(post)).isEqualTo(post);
    }
}
