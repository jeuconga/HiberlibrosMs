/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.configuracion;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;

import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
//import org.modelmapper.ModelMapper;
//import org.springframework.context.annotation.Bean;

import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.annotation.Configuration;


public class FeignSupportConfig {

//    @Bean
//    public Encoder multipartFormEncoder() {
//        return new SpringFormEncoder(new SpringEncoder(new ObjectFactory<HttpMessageConverters>() {
//            @Override
//            public HttpMessageConverters getObject() throws BeansException {
//                return new HttpMessageConverters(new RestTemplate().getMessageConverters());
//            }
//        }));
//    }
    

}
