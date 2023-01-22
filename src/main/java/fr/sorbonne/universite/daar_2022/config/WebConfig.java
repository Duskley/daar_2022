package fr.sorbonne.universite.daar_2022.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer
{
    public void configureContentNegotiation(final ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(true)
            .favorParameter(true)
            .parameterName("mediaType")
            .ignoreAcceptHeader(false)
            .useRegisteredExtensionsOnly(false)
            .defaultContentType(MediaType.TEXT_PLAIN)
            .mediaType("html", MediaType.TEXT_HTML)
            .mediaType("css", MediaType.TEXT_PLAIN)
        	.mediaType("script", MediaType.TEXT_PLAIN)
        	.mediaType("image", MediaType.IMAGE_PNG)
        	.mediaType("sound", MediaType.APPLICATION_OCTET_STREAM)
        	.mediaType("gif", MediaType.IMAGE_GIF);
    }

    public void addCorsMappings(CorsRegistry registry)
    {
        registry.addMapping("/**").allowedOrigins("*");
    }
}
