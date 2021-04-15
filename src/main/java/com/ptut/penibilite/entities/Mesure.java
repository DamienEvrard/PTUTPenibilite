package com.ptut.penibilite.entities;
import java.util.Date;
import javax.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity
public class Mesure {
    
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) @NonNull
    private int id;

    @NonNull
    private Date date;

    @NonNull
    private float valeur;
    
    @ManyToOne
    Capteur capteur;


}
