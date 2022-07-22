package softuni.bg.pathfinder.web;


import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import softuni.bg.pathfinder.model.entity.CommentEntity;
import softuni.bg.pathfinder.model.entity.RouteEntity;
import softuni.bg.pathfinder.model.entity.UserEntity;
import softuni.bg.pathfinder.repository.CommentRepository;
import softuni.bg.pathfinder.repository.RouteRepository;
import softuni.bg.pathfinder.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@WithMockUser("pesho")
@SpringBootTest
@AutoConfigureMockMvc
class CommentRestControllerTest {



    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private  RouteRepository routeRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    private UserEntity testUser;

    @BeforeEach
    void setUp(){
        this.testUser = new UserEntity();
        this.testUser.setPassword("test");
        this.testUser.setUsername("pesho");
        this.testUser.setEmail("pesho@abv.bg");

        testUser = this.userRepository.save(testUser);
    }

    @AfterEach
    void tearDown(){
        this.routeRepository.deleteAll();
        this.userRepository.deleteAll();
    }


    @Test
    void testGetComments() throws Exception {
        Long routeId = initRoute();
        mockMvc.perform(MockMvcRequestBuilders.get("/api/" + routeId + "/comments"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));

    }

    private Long initRoute(){
        RouteEntity testRoute = new RouteEntity();
        testRoute.setName("Testing route");

        testRoute = routeRepository.save(testRoute);

        CommentEntity comment1 = new CommentEntity();
        comment1.setCreated(LocalDateTime.now());
        comment1.setAuthor(testUser);
        comment1.setTextContext("Comment 1");
        comment1.setApproved(true);
        comment1.setRoute(testRoute);

        CommentEntity comment2 = new CommentEntity();
        comment2.setCreated(LocalDateTime.now());
        comment2.setAuthor(testUser);
        comment2.setTextContext("Comment 2");
        comment2.setApproved(true);
        comment2.setRoute(testRoute);

        testRoute.setComments(List.of(comment1, comment2));
        return routeRepository.save(testRoute).getId();
    }

}
