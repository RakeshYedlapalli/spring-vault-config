package com.javatechie.vault.example;

import com.javatechie.vault.example.repository.modelRepo1.Model1;
import com.javatechie.vault.example.repository.modelRepo1.Model1Repository;
import com.javatechie.vault.example.repository.modelRepo2.Model2;
import com.javatechie.vault.example.repository.modelRepo2.Model2Repository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(Credential.class)
@Slf4j
public class SpringVaultConfigExampleApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(SpringVaultConfigExampleApplication.class);

    @Autowired
    private Model1Repository model1Repository;

    @Autowired
    private Model2Repository model2Repository;

    private Credential credential;

    public SpringVaultConfigExampleApplication(Credential credential) {
        this.credential = credential;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringVaultConfigExampleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//		logger.info("------------properties---------");
//		logger.info("Username : "+credential.getUsername());
//		logger.info("Password : "+credential.getPassword());

        log.info("************************************************************");
        log.info("Start creating and printing mongo objects");
        log.info("************************************************************");

        this.model1Repository.save(new Model1(null, "Model 1 obj"));
        this.model2Repository.save(new Model2(null, "Model 2 Obj"));

        List<Model1> model1s = this.model1Repository.findAll();
        for (Model1 model1obj : model1s) {
            log.info(model1obj.toString());
        }

    }
}
