package com.green.hoteldog;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@Import(CharEncodingConfig.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//통합테스트 어노테이션
@AutoConfigureMockMvc
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
public class BaseIntegrationTest {
    @Autowired
    protected MockMvc mvc;
    //통신을 할 수있는 상태를 만들어주는 객체
    @Autowired
    protected ObjectMapper objectMapper;
    //ObjectMapper는 객체를 제이슨으로 제이슨을 객체로 바뀌줄 수 있다.
    //protected : 다른패키지에 있더라도 상속 관계에서는 호출이 가능하다.
}
