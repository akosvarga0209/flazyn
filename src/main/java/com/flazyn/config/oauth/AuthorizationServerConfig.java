package com.flazyn.config.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    static final String CLIEN_ID = "devglan-client";
    static final String CLIENT_SECRET = "$2a$04$AtImbGZAhxtj5Ohh/0jw/u7b0GBe7kSM74MybtN/YReK8qtQ1BL/2"; //Bcrypt encrypted (password)
    static final String GRANT_TYPE = "password";
    static final String GRANT_TYPE_PASSWORD = "password";
    static final String AUTHORIZATION_CODE = "authorization_code";
    static final String REFRESH_TOKEN = "refresh_token";
    static final String IMPLICIT = "implicit";
    static final String SCOPE_READ = "read";
    static final String SCOPE_WRITE = "write";
    static final String TRUST = "trust";
    static final int ACCESS_TOKEN_VALIDITY_SECONDS = 6*60*60;
    static final int FREFRESH_TOKEN_VALIDITY_SECONDS = 120*60*60;


    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {

        configurer
                .inMemory()
                .withClient(CLIEN_ID)
                .secret(CLIENT_SECRET)
                .authorizedGrantTypes(GRANT_TYPE_PASSWORD, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT )
                .scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
                .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS).
                refreshTokenValiditySeconds(FREFRESH_TOKEN_VALIDITY_SECONDS);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore()).tokenServices(tokenServices())
                .authenticationManager(authenticationManager);
    }

    @Bean("myDefault")
    @Primary
    public DefaultTokenServices tokenServices() {
        MyTokenService tokenService = new MyTokenService();
        tokenService.setTokenStore(tokenStore());
        tokenService.setSupportRefreshToken(true);
        tokenService.setAccessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS);
        tokenService.setRefreshTokenValiditySeconds(FREFRESH_TOKEN_VALIDITY_SECONDS);
        return tokenService;
    }

    class MyTokenService extends DefaultTokenServices {

        public MyTokenService() {
        }

        @Override
        public OAuth2AccessToken readAccessToken(String accessToken) {
            return super.readAccessToken(accessToken);
        }

        @Override
        public OAuth2AccessToken createAccessToken(OAuth2Authentication authentication) {
            OAuth2AccessToken token = super.createAccessToken(authentication);
            org.springframework.security.core.userdetails.User userPrincipal = (org.springframework.security.core.userdetails.User)authentication.getPrincipal();
            return token;
        }

        @Override
        public OAuth2AccessToken refreshAccessToken(String refreshTokenValue, TokenRequest tokenRequest) {
            OAuth2AccessToken token = super.refreshAccessToken(refreshTokenValue, tokenRequest);
            return token;
        }
    }

    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }
}