/*
 * It’s nice to have a sanity check like that, but we should also write some tests that assert the behaviour of our application. To do that we could start the application up and listen for a connection like it would do in production, and then send an HTTP request and assert the response.
 *
 * Note the use of webEnvironment=RANDOM_PORT to start the server with a random port (useful to avoid conflicts in test environments), and the injection of the port with @LocalServerPort. Also note that Spring Boot has provided a TestRestTemplate for you automatically, and all you have to do is @Autowired it.
 * 
 */
package com.allstate.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
    	
    	        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
                String.class)).contains("Hello World");
        
      
    }
}