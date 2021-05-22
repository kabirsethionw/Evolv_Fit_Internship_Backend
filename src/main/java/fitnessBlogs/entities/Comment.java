package fitnessBlogs.entities;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection="comments")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Comment {

	@Id
	private String id;
	
	@NotNull
	@Field(name="commenterId")
	private ObjectId commenterId;
	
	@NotNull
	@Field(name="blogId")
	private ObjectId blogId;
	
	@NotNull
	@Field(name="text")
	private String text;
	
	@NotNull
	@Field(name="createdAt")
	private Date createdAt;
	
	
//	public ObjectId getCommenterId() { return commenterId;}
//	
//	public ObjectId getBlogId() { return blogId;}
//	
//	public String getText() { return text;}
	
//	public Date getCreatedAt() { return createdAt;}
//	
//	public void setCommenterId(ObjectId commenterId) { this.commenterId = commenterId;}
//	
//	public void setBlogId(ObjectId blogId) { this.blogId = blogId;}
	
//	public void setText(String text) { this.text = text;}
	
//	public void setCreatedAt(Date createdAt) { this.createdAt = createdAt;}
	
	public Comment(String text, ObjectId commenterId, ObjectId blogId) {
		this.commenterId  = commenterId;
		this.blogId = blogId;
		this.text = text;
		this.createdAt = new Date();
	}

}
