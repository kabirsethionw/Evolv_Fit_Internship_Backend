package fitnessBlogs.repositories;

import java.util.ArrayList;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import fitnessBlogs.entities.Blog;
import fitnessBlogs.entities.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	ArrayList<Blog> findBlogsById(String id);
}
