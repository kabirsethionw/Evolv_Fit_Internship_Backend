package fitnessBlogs.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import fitnessBlogs.entities.Comment;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> { }
