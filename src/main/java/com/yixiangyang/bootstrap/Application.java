package com.yixiangyang.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.baidu.aip.face.AipFace;
@ComponentScan(basePackages= {"com.yixiangyang.controller", "com.yixiangyang.service"})
//@Import(value={AipFace.class})
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
