package fitnessBlogs.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import fitnessBlogs.entities.Blog;

@Repository
public interface BlogRepository extends MongoRepository<Blog, String> { 
	Blog findByTitle(String title);

}
