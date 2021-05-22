package fitnessBlogs.entities;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import fitnessBlogs.util.BlogId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Document(collection="users")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class User {
	
	public enum accountStatus{ //We don't delete user permanently, only soft delete.
		ACTIVATED, DEACTIVATED 
	}
	

	
	@Id
	private String id;
	
	@NotNull
	@Size(min=3, max=30, message="Name field must be atleast 3 characters long and atmost 30 characters long.")
	@Field(name="name")
	private String name;
	
	@NotNull
	@Email(message="Invalid Email Id.")
	@Field(name="email")
	private String email;
	
//	@NotNull
	@Field(name="status")
	private accountStatus status;
	
	@Field(name="blogs")
	private Set<BlogId> blogs = new HashSet<BlogId>(); //arrayList of Ids
	
//	public String getId() { return id;}
//	
//	public String getName() { return name;}
//	
//	public String getEmail() { return email;}
//	
//	public String getStatus() { return status.toString();}
//	
//	public Set<BlogId> getBlogs() { return blogs;}
//
//	public void setName(String name) { this.name = name;}
	
	public void setStatus(String status) { this.status = Enum.valueOf(accountStatus.class, status);}
	
//	public void setEmail(String email) { this.email = email;}
		
	public void addBlog(BlogId blog) { blogs.add(blog);} // Only one blog at a time can be added
	
	public void removeBlog(BlogId blog) { blogs.remove(blog);}
	
	
	public User(String name, String email, accountStatus status) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.email = email;
		this.status = status;
		
	}

}
