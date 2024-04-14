package at.htlkaindorf.keskustelebackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Project: keskusteleBackend
 * Created by: Thomas Jaritz
 * Date: 14/04/2024
 * Time: 16:08
 **/

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(final MessageBrokerRegistry config) {
        //scoreboard
        config.enableSimpleBroker("/chatroom");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(final StompEndpointRegistry registry) {
        //data
        registry.addEndpoint("/messages").setAllowedOriginPatterns("*");
        registry.addEndpoint("/messages").setAllowedOriginPatterns("*").withSockJS();
        registry.addEndpoint("/error").setAllowedOriginPatterns("*");
        registry.addEndpoint("/error").setAllowedOriginPatterns("*").withSockJS();
    }
}