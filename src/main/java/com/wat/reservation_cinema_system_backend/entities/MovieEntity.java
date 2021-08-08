package com.wat.reservation_cinema_system_backend.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "movies")
public class MovieEntity {
    @Id
    @GeneratedValue
    private Long movieId;
    private String title;
    private String director;
    private String description;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ImageEntity moviePhoto;
    @OneToMany(mappedBy="movie")
    private List<ScreeningEntity> screenings=new ArrayList<>();


}
