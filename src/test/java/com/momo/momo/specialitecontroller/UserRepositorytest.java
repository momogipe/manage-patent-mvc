package com.momo.momo.specialitecontroller;

import com.momo.momo.EntitiesUser.User;
import com.momo.momo.userRepository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositorytest {
    @Autowired
    private UserRepository repo;

    @Test
    public void testAddNew() {
        User user1 = new User();
//        given
        user1.setEmail("alainkuate@gmail.com");
        user1.setPassword("alain$2021");
        user1.setFirstname("alain");
        user1.setLastname("kuate");
//        then
        User saveuser = repo.save(user1);

//        assert
        Assertions.assertThat(saveuser).isNotNull();
        Assertions.assertThat(saveuser.getId()).isGreaterThan(0);
    }


    @Test
    public void listUsersFindall() {
        List<User> users = (List<User>) repo.findAll();
        Assertions.assertThat(users.size()).isGreaterThan(0);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testUpdate() {
        //given
        Integer id = 1;
        Optional<User> optionalUser = repo.findById(id);
        User user = optionalUser.get();
        user.setPassword("momo$2022");
        // then
        repo.save(user);
        User userUpdated = repo.findById(id).get();

        //assertions
        Assertions.assertThat(userUpdated.getPassword().equals("momo$2022"));
    }

    @Test
    public void testGet() {
        Integer id = 2;
        Optional<User> optionalUser = repo.findById(id);
        Assertions.assertThat(optionalUser.isPresent());
    }

    @Test
    public void deleteUser() {
        //given
        Integer userid = 2;
        repo.deleteById(userid);

        //then
        Optional<User> userOptional = repo.findById(userid);

        //Assertions
        Assertions.assertThat(userOptional).isNotPresent();
    }
}

