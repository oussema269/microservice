package microservice;

import jakarta.ws.rs.HttpMethod;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

@EnableDiscoveryClient
@SpringBootApplication
@CrossOrigin("*")
public class ApiGateway {
    public static void main(String[] args) {
        SpringApplication.run(ApiGateway.class, args);
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(
                        "blog-service",
                        r -> r.path("/blog/getAllBlogs")
                                .and().method(HttpMethod.GET)
                                .filters(GatewayFilterSpec::preserveHostHeader)
                                .uri("lb://blog-service/blog/getAllBlogs")
                )
                .route(
                        "blog-service",
                        r -> r.path("/getDetailsBlog/{segment}")
                                .and().method(HttpMethod.GET)
                                .uri("lb://blog-service/blog")
                )
                .route(
                        "blog-service",
                        r -> r.path("/blog/addBlog")
                                .and().method(HttpMethod.POST)
                                .uri("lb://blog-service/blog/addBlog")
                )

                .build();
    }
}