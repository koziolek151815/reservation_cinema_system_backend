package com.wat.reservation_cinema_system_backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "movies")
public class MovieEntity {
    @Id
    private Long movieId;
    private String title;
    private String director;
    private String description;
    @OneToMany(mappedBy="movie")
    private List<ScreeningEntity> screenings=new ArrayList<>();


}
