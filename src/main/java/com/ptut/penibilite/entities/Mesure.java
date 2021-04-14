package com.ptut.penibilite.entities;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity
public class Mesure {
    
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NonNull
    private LocalDateTime date;
    
    @NonNull
    private float valeur;
    
    @ManyToOne @NonNull
    Capteur capteur;
}
