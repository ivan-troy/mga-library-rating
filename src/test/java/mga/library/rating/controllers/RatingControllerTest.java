package mga.library.rating.controllers;

import mga.library.rating.models.Rating;
import mga.library.rating.repositories.RatingRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RatingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RatingRepository ratingRepository;

    @Test
    public void shouldReturnRatingForBook() throws Exception {
        Long id = 1L;
        int expectedRating = 3;
        when(ratingRepository.findOne(id)).thenReturn(new Rating(id, expectedRating));
        mockMvc.perform(MockMvcRequestBuilders.get("/rating/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().json("{'rating':" + expectedRating + "}"));
    }
}