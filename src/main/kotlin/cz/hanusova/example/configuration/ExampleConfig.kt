package cz.hanusova.example.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoRepositories(basePackages = ["cz.hanusova.example.repository"])
class ExampleConfig {
}