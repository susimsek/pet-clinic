package com.web;

import com.model.Owner;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PetClinicRestControllerTests {

    private RestTemplate restTemplate;

    @Before
    public void setUp(){
        restTemplate=new RestTemplate();
        BasicAuthorizationInterceptor basicAuthorizationInterceptor=new BasicAuthorizationInterceptor("user","secret");//Otomatik base64 encode yapıyor.
        restTemplate.setInterceptors(Arrays.asList(basicAuthorizationInterceptor));
    }


    @Test
    public void testCreateOwner() {
        Owner owner = new Owner();
        owner.setId((long) 4);
        owner.setFirstName("Metehan");
        owner.setLastName("Yucel");

        URI location = restTemplate.postForLocation("http://localhost:8080/rest/owner", owner);
        Owner owner2 = restTemplate.getForObject(location, Owner.class);
        MatcherAssert.assertThat(owner2.getFirstName(), Matchers.equalTo(owner.getFirstName()));
        MatcherAssert.assertThat(owner2.getLastName(), Matchers.equalTo(owner.getLastName()));

    }


    @Test
    public void testOwnerDelete(){
        restTemplate.delete("http://localhost:8080/rest/owner/3");
        try {
            restTemplate.getForEntity("http://localhost:8080/rest/owner/3",Owner.class);
            Assert.fail("should have not returned owner");
        } catch (HttpClientErrorException e) {
            MatcherAssert.assertThat(e.getStatusCode().value(),Matchers.equalTo(404));
        }

    }




    @Test
    public void testOwnerUpdate(){
        Owner owner=restTemplate.getForObject("http://localhost:8080/rest/owner/2",Owner.class);
        MatcherAssert.assertThat(owner.getFirstName(), Matchers.equalTo("Şuayb"));

        owner.setLastName("Güray");
        restTemplate.put("http://localhost:8080/rest/owner/2",owner);
        owner=restTemplate.getForObject("http://localhost:8080/rest/owner/2",Owner.class);
        MatcherAssert.assertThat(owner.getLastName(), Matchers.equalTo("Güray"));
    }





    @Test
    public void testGetOwnerById(){
       ResponseEntity<Owner> response=restTemplate.getForEntity("http://localhost:8085/rest/owner/1", Owner.class);
        MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
        //MatcherAssert.assertThat(response.getBody().getFirstName(),Matchers.equalTo("Kenan"));

    }

    @Test
    public void testGetOwnerByLastName(){
        ResponseEntity<List> response=restTemplate.getForEntity("http://localhost:8080/rest/owner?ln=Sevindik", List.class);
        MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
        List<Map<String,String>> body=response.getBody();
        List<String> firstNames=body.stream().map(e->e.get("firstName")).collect(Collectors.toList());

        MatcherAssert.assertThat(firstNames, Matchers.containsInAnyOrder("Kenan"));
    }

    @Test
    public void testGetOwners(){
        ResponseEntity<List> response=restTemplate.getForEntity("http://localhost:8080/rest/owners", List.class);
        MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
        List<Map<String,String>> body=response.getBody();
        List<String> firstNames=body.stream().map(e->e.get("firstName")).collect(Collectors.toList());

        MatcherAssert.assertThat(firstNames, Matchers.containsInAnyOrder("Kenan","Şuayb","Metehan"));
    }







}
