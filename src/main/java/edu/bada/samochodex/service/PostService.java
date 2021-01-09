package edu.bada.samochodex.service;

import edu.bada.samochodex.dao.PostDao;
import edu.bada.samochodex.model.Client;
import edu.bada.samochodex.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostDao postDao;

    @Autowired
    public PostService(PostDao postDao) {
        this.postDao = postDao;
    }

    public Post getById(Long id) {
        return postDao.findById(id).orElse(null);
    }

    public Post getByPostCode(String code) {
        return postDao.findPostByKodPocztowy(code);
//        List<Post> posts = postDao.findAll();
//
//        return posts.stream()
//                .filter(post -> post.getKodPocztowy().equals(code))
//                .findFirst()
//                .orElse(null);
    }

    public List<Post> getAll() {
        return postDao.findAll();
    }

    public Post save(Post post) {
        Post postToFind = getByPostCode(post.getKodPocztowy());

        if (postToFind != null ) {
            return postToFind;
        } else {
            return postDao.save(post);
        }
    }

    public void deleteById(Long id) {
        postDao.deleteById(id);
    }
}
