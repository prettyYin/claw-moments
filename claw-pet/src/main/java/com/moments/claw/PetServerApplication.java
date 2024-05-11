package com.moments.claw;

import com.github.jeffreyning.mybatisplus.conf.EnableMPP;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableMPP
@SpringBootApplication
@EnableScheduling //开启基于注解的任务调度器
@EnableAsync //开启异步
@MapperScan("com.moments.claw.**.mapper")
@Slf4j
public class PetServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(PetServerApplication.class,args);
		log.info("(♥◠‿◠)ﾉﾞ  爪友圈启动成功   ლ(´ڡ`ლ)ﾞ  \n");
	}
}
