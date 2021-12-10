package com.piggymetrics.auth.config;

import com.piggymetrics.auth.service.security.MongoUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * @author 配置类，注入一些安全配置
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {
    //使用默认的模式，将token存在内存中，直接使用即可，不需要配置
    private TokenStore tokenStore = new InMemoryTokenStore();
    private final String NOOP_PASSWORD_ENCODE = "{noop}";

    //认证管理器模型
    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    private MongoUserDetailsService userDetailsService;

    @Autowired
    private Environment env;

    /**
     * ClientDetailsServiceConfigurer :clientDetailsService注入，决定clientDeatils信息的处理服务。
     * 为不同的服务和场景配置不同的鉴权模式
     * */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        // TODO persist clients details

        // @formatter:off 将客户端信息存储在内存中
        clients.inMemory()
                //clientid
                .withClient("browser")
                //该client允许的授权类型 用于用户授权
                .authorizedGrantTypes("refresh_token", "password")
                //允许的授权范围
                .scopes("ui")
                .and()
                //clientid
                .withClient("account-service")
                //client_secret
//                .secret(env.getProperty("ACCOUNT_SERVICE_PASSWORD"))
                .secret("112233")
                //授权模式：客户端模式 仅需在授权服务器上申请资源服务器的认证信息client_id和client_secret
                //这种模式一般只用在服务端与服务端之间的认证
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .scopes("server")
                .and()
                .withClient("statistics-service")
//                .secret(env.getProperty("STATISTICS_SERVICE_PASSWORD"))
                .secret("112233")
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .scopes("server")
                .and()
                .withClient("notification-service")
//                .secret(env.getProperty("NOTIFICATION_SERVICE_PASSWORD"))
                .secret("112233")
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .scopes("server");
        // @formatter:on
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
                //Spring Security OAuth2会公开了两个端点，用于检查令牌（/oauth/check_token和/oauth/token_key），这些端点默认受保护denyAll()。tokenKeyAccess（）和checkTokenAccess（）方法会打开这些端点以供使用。
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                //设置oauth_client_details中的密码编码器
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

}
