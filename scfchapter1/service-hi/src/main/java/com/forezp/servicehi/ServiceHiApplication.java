package com.forezp.servicehi;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableHystrix
public class ServiceHiApplication {

    /*
    * 访问地址 Http://localhost:8762/actuator/hystrix.stream
    *
    * */

    public static void main(String[] args)
    {

        SpringApplication.run(ServiceHiApplication.class, args);
    }

    @Value("${server.port}")
    String port;

    @RequestMapping("/hi")
    @HystrixCommand(fallbackMethod = "hiError")
    public String home(@RequestParam(value = "name",defaultValue = "forezp") String name)
    {
        return "hi " + name + " ,i am from port:" + port;
    }

    public String hiError(String name){
        return "hi,"+name+",sorry,error!";
    }

}