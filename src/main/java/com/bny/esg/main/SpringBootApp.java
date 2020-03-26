package com.bny.esg.main;

import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bny.esg.controllers.NotFoundException;
import com.bny.esg.filter.JWTVerifyingFilter;
import com.bny.esg.model.ErrorMessage;
import com.bny.esg.realm.CustomRealm;
import com.bny.esg.repository.UserRepository;
import com.bny.esg.service.UserService;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.bny.esg")
@EntityScan("com.bny.esg.entity")
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class SpringBootApp {

    private static Logger log = LoggerFactory.getLogger(SpringBootApp.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApp.class, args);
    }

    @ExceptionHandler(UnauthenticatedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public void handleException(UnauthenticatedException e) {
        log.debug("{} was thrown", e.getClass(), e);
    }

    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void handleException(AuthorizationException e) {
        log.debug("{} was thrown", e.getClass(), e);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody
    ErrorMessage handleException(NotFoundException e) {
        String id = e.getMessage();
        return new ErrorMessage("User Not Found: " + id + ", why aren't you at your post? " + id + ", do you copy?");
    }

    @Bean
    public Realm realm() {

        CustomRealm realm = new CustomRealm();
        //realm.setCachingEnabled(true);
        return realm;
    }   
   
    @Bean
    public Filter jwtv() {
        return new JWTVerifyingFilter();
    }

    @Bean
    public FilterRegistrationBean jwtvFilterRegBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(jwtv());
        filterRegistrationBean.setEnabled(false);
        return filterRegistrationBean;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Value("#{ @environment['shiro.loginUrl'] ?: '/login.html' }") String loginUrl,
            @Value("#{ @environment['shiro.successUrl'] ?: '/' }") String successUrl,
            @Value("#{ @environment['shiro.unauthorizedUrl'] ?: null }") String unauthorizedUrl,
            org.apache.shiro.mgt.SecurityManager securityManager,
            ShiroFilterChainDefinition shiroFilterChainDefinition,
            Map<String, Filter> filterMap) {

        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();

        filterFactoryBean.setLoginUrl(loginUrl);
        filterFactoryBean.setSuccessUrl(successUrl);
        filterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);

        filterFactoryBean.setSecurityManager(securityManager);
        filterFactoryBean.setFilterChainDefinitionMap(shiroFilterChainDefinition.getFilterChainMap());
        filterFactoryBean.setFilters(filterMap);

        return filterFactoryBean;
    }

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        chainDefinition.addPathDefinition("/**", "authcBasic[permissive]");
        chainDefinition.addPathDefinition("/api/**", "jwtv");
        chainDefinition.addPathDefinition("/api/token", "anon");
        chainDefinition.addPathDefinition("/test/**", "anon");
        return chainDefinition;
    }

    @Bean
    public CacheManager cacheManager() {
        return new MemoryConstrainedCacheManager();
    }

}
