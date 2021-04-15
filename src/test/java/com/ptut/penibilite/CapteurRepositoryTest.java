package com.ptut.penibilite;

import com.ptut.penibilite.daos.CapteurRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@Log4j2
@DataJpaTest
public class CapteurRepositoryTest {

    @Autowired
    private CapteurRepository dao;



}
