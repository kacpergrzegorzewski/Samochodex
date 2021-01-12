package edu.bada.samochodex.dao;

import edu.bada.samochodex.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostDao extends JpaRepository<Post, Long> {

    Post findPostByKodPocztowy(String postCode);
}
