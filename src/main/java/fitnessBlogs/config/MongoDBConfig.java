package fitnessBlogs.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collection;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableMongoRepositories(basePackages = "fitnessblogs.repositories")
public class MongoDBConfig extends AbstractMongoClientConfiguration {

	public MongoDBConfig() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected String getDatabaseName() {
		return "blogger";
	}
	
	@Override
	public MongoClient mongoClient() {
		ConnectionString connectionString = new ConnectionString("mongodb+srv://Kabir_Sethi:mongokabirdb@cluster0.sh5xs.mongodb.net/myFirstDatabase?authSource=admin&replicaSet=Cluster0-shard-0&w=majority&readPreference=primary&appname=MongoDB%20Compass&retryWrites=true&ssl=true");
		MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
				.applyConnectionString(connectionString)
	            .build();
	        
	        return MongoClients.create(mongoClientSettings);
	}
	
	 
	@Override
	    public Collection<String> getMappingBasePackages() {
	        return Collections.singleton("fitnessblogs");
	    }
	
	@Bean
	public MongoTemplate mongoTemplate() {
		MongoTemplate template = new MongoTemplate(mongoDbFactory());
		return template;
	}

}
