package com.b2chat;

import com.b2chat.entity.User;
import com.b2chat.service.UserService;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class B2ChatApplicationTests {

    @Autowired
    UserService userService;





    @Test
    void createUser() {
        User user = new User();
        user.setUserName("Felipe");
        user.setEmail("Felipe@hotmail.com");
        user.setPassword("F1234");

        userService.createUser(user);

        User existUser = userService.retrieveById(user.getId());

        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());

    }

    @Test
    void updateUser() {
        User user = new User();
        user.setId(8);
        user.setUserName("Felipe");
        user.setEmail("Felipe@hotmail.com");
        user.setPassword("F123455");

        userService.update(user);

        User existUser = userService.retrieveById(user.getId());

        assertThat(user.getPassword()).isEqualTo(existUser.getPassword());

    }

}
