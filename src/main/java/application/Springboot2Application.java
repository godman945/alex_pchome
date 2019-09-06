package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@ComponentScan(basePackages= {"controller","config","service"})
public class Springboot2Application extends SpringBootServletInitializer{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder springApplicationBuilder) {
        return springApplicationBuilder.sources(Springboot2Application.class);
    }
	
	public static void main(String[] args) {
    	SpringApplication.run(Springboot2Application.class, args);
	}
    
    @EventListener(Springboot2Application.class)
    public void doSomethingAfterStartup() {
        System.out.println("hello world, I have just started up");
    }
    
}