package fitnessBlogs.util;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
public class BlogId {
	
	@JsonProperty
	public ObjectId _blogId;
	
	public BlogId(ObjectId _blogId) {
		// TODO Auto-generated constructor stub
		this._blogId = _blogId;
	}
	
	@Override
	public int hashCode() {
		return _blogId.toString().hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof BlogId) {
			System.out.println("Checking:");
			BlogId checked = (BlogId)o;
			System.out.println("Arguement :"+checked._blogId.toString()+" Caller:"+this._blogId.toString());
			String callerId = this._blogId.toString();
			String arg = checked._blogId.toString();		
			if(callerId.equals(arg)) {
				System.out.println("Equal!");
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}	
	}

}
