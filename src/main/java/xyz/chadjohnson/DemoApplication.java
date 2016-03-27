package xyz.chadjohnson;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.sleuth.sampler.PercentageBasedSampler;
import org.springframework.cloud.sleuth.sampler.SamplerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@EnableHystrix
@EnableHystrixDashboard // Disable this in a real application, unless you want hystrix dashboards on every instance...
//@EnableEurekaClient
public class DemoApplication {

	@Value("${spring.sleuth.sampler.percentage}")
	private float samplePercentage;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public PercentageBasedSampler defaultSampler() {
		SamplerProperties samplerProperties = new SamplerProperties();
		samplerProperties.setPercentage(samplePercentage);
		return new PercentageBasedSampler(samplerProperties);
	}
}
