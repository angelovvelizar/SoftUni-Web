package softuni.bg.pathfinder.web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import softuni.bg.pathfinder.model.entity.UserEntity;
import softuni.bg.pathfinder.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;


@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    private static final String TEST_USERNAME = "pesho";
    private static final Integer TEST_AGE = 18;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;


    @AfterEach
    void tearDown(){
        userRepository.deleteAll();
    }

    @Test
    void testOpenRegisterForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/register"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("register"));
    }


    @Test
    void testRegisterUser() throws Exception {

        //params are the input fields in the register form
        mockMvc.perform(MockMvcRequestBuilders.post("/users/register")
                .param("username",TEST_USERNAME)
                .param("fullName","Pesho Petrov")
                .param("email","pesho@abv.bg")
                .param("age",String.valueOf(TEST_AGE))
                .param("password","12345")
                .param("confirmPassword","12345")
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(MockMvcResultMatchers.status().is3xxRedirection());

        Assertions.assertEquals(1, userRepository.count());

        Optional<UserEntity> newUserOpt = userRepository.findByUsername(TEST_USERNAME);

        Assertions.assertTrue(newUserOpt.isPresent());

        UserEntity newUser = newUserOpt.get();

        Assertions.assertEquals(TEST_AGE, newUser.getAge());
        //TODO: check remaining properties of the binding model

    }

}