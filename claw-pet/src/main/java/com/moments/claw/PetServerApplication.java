package com.moments.claw;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.moments.claw.**.mapper")
@Slf4j
public class PetServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(PetServerApplication.class,args);
		log.info("(♥◠‿◠)ﾉﾞ  爪友圈启动成功   ლ(´ڡ`ლ)ﾞ  \n");
	}
}
