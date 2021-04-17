package com.ptut.penibilite;

import io.netty.handler.codec.http.HttpResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Log4j2
@DataJpaTest
public class APICapteurTest {



    /*public void ajoutMesure(){


        HttpUriRequest request = new HttpGet( "http://localhost:8080/APIcapteur/ajout?id=1&valeur=99");


        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );

        assertEquals(HttpStatus.OK, httpResponse.getStatus());
    }*/
}
