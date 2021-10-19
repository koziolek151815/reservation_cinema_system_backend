package com.wat.reservation_cinema_system_backend.repositories;

import com.wat.reservation_cinema_system_backend.entities.ScreeningEntity;
import com.wat.reservation_cinema_system_backend.screening.ScreeningRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DataJpaTest
public class ExampleTest {

    @Autowired
    private ScreeningRepository screeningRepository;

    @Test
    public void should_find_no_tutorials_if_repository_is_empty() {
        List<ScreeningEntity> screeningEntities = screeningRepository.findAll();
        assertThat(screeningEntities).isEmpty();
    }
    @Test
    public void adfs() {
        ScreeningEntity screeningEntity = new ScreeningEntity();
        LocalDateTime localDateTime = LocalDateTime.now();
        screeningEntity.setStartScreening(localDateTime);
        ScreeningEntity saved = screeningRepository.save(screeningEntity);
        ScreeningEntity found = screeningRepository.findById(saved.getScreeningId()).get();
        assertEquals(localDateTime, found.getStartScreening());
    }

}
