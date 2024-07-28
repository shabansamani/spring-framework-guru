package guru.springframework.spring_6_reactive_mongo.config;

import static java.util.Collections.singletonList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.lang.NonNull;

import com.mongodb.MongoClientSettings.Builder;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

@Configuration
public class MongoConfig extends AbstractReactiveMongoConfiguration {
    
    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create();
    }
    
    @Override
    protected @NonNull String getDatabaseName() {
        return "sfg";
    }

    @Override
    protected void configureClientSettings(@NonNull Builder builder) {
        builder.credential(MongoCredential.createCredential("root", "admin", "example".toCharArray()))
        .applyToClusterSettings(settings -> {
            settings.hosts((singletonList(
                new ServerAddress("127.0.0.1", 27017)
            )));
        });
    }

    
}
