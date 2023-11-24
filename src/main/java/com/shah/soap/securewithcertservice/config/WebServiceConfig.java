package com.shah.soap.securewithcertservice.config;


import com.shah.soap.securewithcertservice.service.impl.CalculatorServiceImpl;
import jakarta.xml.ws.Endpoint;
import org.apache.cxf.Bus;

import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.common.ConfigurationConstants;
import org.apache.wss4j.dom.WSConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class WebServiceConfig {
    @Autowired
    private Bus bus;
    @Bean
    public Endpoint endpoint(){
        EndpointImpl endpoint = new EndpointImpl(bus, new CalculatorServiceImpl());
        Map<String, Object> inProps =  new HashMap<>();
        inProps.put(ConfigurationConstants.ACTION, "UsernameToken Encrypt Signature Timestamp");
        inProps.put(ConfigurationConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
        inProps.put(ConfigurationConstants.PW_CALLBACK_CLASS, UTPasswordCallbackHandler.class.getCanonicalName());
        inProps.put(ConfigurationConstants.DEC_PROP_FILE, "etc/servicekeystore.properties");
        inProps.put(ConfigurationConstants.SIG_PROP_FILE, "etc/servicekeystore.properties");
        WSS4JInInterceptor wss4JInInterceptor = new WSS4JInInterceptor(inProps);
        endpoint.getInInterceptors().add(wss4JInInterceptor);

        Map<String, Object> outProps = new HashMap<>();
        outProps.put(ConfigurationConstants.ACTION, "Encrypt Signature Timestamp");
        outProps.put(ConfigurationConstants.PW_CALLBACK_CLASS, UTPasswordCallbackHandler.class.getCanonicalName());
        outProps.put(ConfigurationConstants.ENC_PROP_FILE, "etc/servicekeystore.properties");
        outProps.put(ConfigurationConstants.ENCRYPTION_USER, "myclientkey");
        outProps.put(ConfigurationConstants.SIG_PROP_FILE, "etc/servicekeystore.properties");
        outProps.put(ConfigurationConstants.SIGNATURE_USER, "myservicekey");
        WSS4JOutInterceptor wss4JOutInterceptor = new WSS4JOutInterceptor(outProps);
        endpoint.getOutInterceptors().add(wss4JOutInterceptor);
        endpoint.publish("/calculatorservice");
        return endpoint;
    }
}
