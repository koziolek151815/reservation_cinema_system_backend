package com.wat.reservation_cinema_system_backend.entities;

import com.wat.reservation_cinema_system_backend.movie.MovieService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import javax.persistence.*;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "images")
public class ImageEntity {

    @Id
    @GeneratedValue
    private long id;
    private String originalFilename;

    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] bytes;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private MovieEntity movieEntity;

}
