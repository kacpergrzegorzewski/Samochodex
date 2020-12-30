package edu.bada.samochodex.service;

import edu.bada.samochodex.dao.PostDao;
import edu.bada.samochodex.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostDao postDao;

    @Autowired
    public PostService(PostDao postDao) {
        this.postDao = postDao;
    }

    public Post getById(long id) {
        return postDao.findById(id).orElse(null);
    }

    public List<Post> getAll() {
        return postDao.findAll();
    }

    public void save(Post post) {
        postDao.save(post);
    }

    public void update(Post post) {}

    public void deleteById(long id) {
        postDao.deleteById(id);
    }
}
