package com.ptut.entities;
import java.util.Date;
import javax.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @ToString
@Entity
public class Mesure {
    
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private Date date;
    
    private float valeur;
    
    @ManyToOne
    Capteur capteur;

    public Mesure(int id, Date date, float valeur) {
        this.id=id;
        this.date=date;
        this.valeur=valeur;
    }
}
