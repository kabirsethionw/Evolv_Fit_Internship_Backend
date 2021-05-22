package fitnessBlogs.entities;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection="blogs")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Blog {
	
	@Id
	private String id;
	
	@NotNull
	@Size(max=50, min=1, message="Title length should be atleast 1 character long and atmost 50 character long.")
	@Field(name="title")
	private String title;
	
	@NotNull
	@Field(name="text")
	private String text;
	
	@NotNull
	@CreatedDate
	@Field(name="createdAt")
	private Date createdAt;
	
	@LastModifiedDate
	@Field(name="modifiedAt")
	private Date modifiedAt;
	
	
//	public String getId() { return id;} 
//	
//	public String getTitle() { return title;}
//	
//	public String getText() { return text;}
//	
//	public Date getCreatedAt() { return createdAt;}
//	
//	public Date getModifiedAt() { return modifiedAt;}
//	
//	public void setTitle(String title) { this.title = title;}
//	
//	public void setText(String text) { this.text = text;}
//	
//	public void setCreatedAt(Date createdAt) { this.createdAt = createdAt;}
//	
//	public void setModifiedAt(Date modifiedAt) { this.modifiedAt = modifiedAt;}
//	
	
	public Blog(String title, String text) {
		// TODO Auto-generated constructor stub
		this.title=title;
		this.text=text;
		this.createdAt= new Date();
		this.modifiedAt= null;
	}

}
