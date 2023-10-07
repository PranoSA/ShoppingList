package com.compressibleflowcalculator.shopping_api.Config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.authorization.AuthenticatedAuthorizationManager;
import org.springframework.security.authorization.AuthorizationEventPublisher;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.authorization.SpringAuthorizationEventPublisher;
import org.springframework.security.config.annotation.web.socket.EnableWebSocketSecurity;
import org.springframework.security.messaging.access.intercept.AuthorizationChannelInterceptor;
import org.springframework.security.messaging.access.intercept.MessageMatcherDelegatingAuthorizationManager;
import org.springframework.security.messaging.context.AuthenticationPrincipalArgumentResolver;
import org.springframework.security.messaging.context.SecurityContextChannelInterceptor;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketSecurity
public class WebSocketSecurityConfig /* implements WebSocketMessageBrokerConfigurer */ {

    /*
     * @Override
     * public void addArgumentResolvers(List<HandlerMethodArgumentResolver>
     * argumentResolvers) {
     * argumentResolvers.add(new AuthenticationPrincipalArgumentResolver());
     * }
     * 
     * @Override
     * public void configureClientInboundChannel(ChannelRegistration registration) {
     * AuthorizationManager<Message<?>> myAuthorizationRules =
     * AuthenticatedAuthorizationManager.authenticated();
     * AuthorizationChannelInterceptor authz = new
     * AuthorizationChannelInterceptor(myAuthorizationRules);
     * AuthorizationEventPublisher publisher = new
     * SpringAuthorizationEventPublisher(this.context);
     * authz.setAuthorizationEventPublisher(publisher);
     * registration.interceptors(new SecurityContextChannelInterceptor(), authz);
     * }s
     */

    @Bean
    AuthorizationManager<Message<?>> messageAuthorizationManager(
            MessageMatcherDelegatingAuthorizationManager.Builder messages) {
        messages
                .simpDestMatchers("*").permitAll()
                .simpDestMatchers("/*").permitAll()
                .simpMessageDestMatchers("*").permitAll()
                .simpMessageDestMatchers("/*").permitAll()
                .simpMessageDestMatchers("/*/**").permitAll()
                .simpSubscribeDestMatchers("*").permitAll()
                .simpSubscribeDestMatchers("/*").permitAll()
                .simpSubscribeDestMatchers("/*/**").permitAll()
                .simpDestMatchers("/*/**").permitAll();
        return messages.build();
    }

}