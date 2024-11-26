package com.fiap.burguer;

import com.fiap.CoreTestConfiguration;
import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.web.servlet.MockMvc;

@Tag("integration")
@AutoConfigureMockMvc
@ActiveProfiles("h2")
@SpringBootTest
@Sql(scripts = "classpath:data.sql",config = @SqlConfig(encoding = "UTF-8",transactionMode = SqlConfig.TransactionMode.ISOLATED))

public class IntegrationTest {

    @Autowired
    protected MockMvc mvc;
}