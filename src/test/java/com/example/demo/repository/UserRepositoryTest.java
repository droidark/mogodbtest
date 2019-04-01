package com.example.demo.repository;

import com.example.demo.domain.User;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UserRepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(UserRepositoryTest.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() {
         // given
        User user = new User();
        user.setId("123456");
        user.setUsername("fozz");

        mongoTemplate.save(user);

        // when
        Optional<User> found = userRepository.findById("123456");

        logger.info(found.get().getUsername());

        // then
        assertThat(found.get().getUsername()).isEqualTo(user.getUsername());
    }



}