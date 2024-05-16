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
 * Time: 09:13
 **/

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(final MessageBrokerRegistry config) {
        config.enableSimpleBroker("/chatroom");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(final StompEndpointRegistry registry) {
        registry.addEndpoint("/messages").setAllowedOriginPatterns("*");
        registry.addEndpoint("/messages").setAllowedOriginPatterns("*").withSockJS();
    }
}