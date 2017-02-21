package com.ranga.service;

import com.ranga.entities.GithubUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;


@ContextConfiguration("/testConfig.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class JacksonTest {


    @Test
    public void Jackson() {

        RestTemplate restTemplate = new RestTemplate();
        GithubUser user = restTemplate.getForObject("https://api.github.com/users/DmitrySay", GithubUser.class);
        System.out.println(user);

    }

}
