package com;

import com.model.Owner;
import com.model.Vet;
import com.service.PetClinicService;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(properties ={"spring.profiles.active=dev"})
public class PetClinicIntegrationTest {

    @Autowired
    private PetClinicService petClinicService;

    @Before
    public void setUp(){
        TestingAuthenticationToken auth=new TestingAuthenticationToken("user","secret","ROLE_USER");
        SecurityContextHolder.getContext().setAuthentication(auth);//Her yerden erişebilir
    }

    @After
    public void tearDown(){
        SecurityContextHolder.clearContext();
    }

    @Test
    public void testFindOwners(){
       List<Owner> owners=petClinicService.findOwners();
        MatcherAssert.assertThat(owners.size(), Matchers.equalTo(10));
    }

    @Test
    public void testFindVets(){
        List<Vet> vets=petClinicService.findVets();
        MatcherAssert.assertThat(vets.size(), Matchers.equalTo(3));
    }
}
