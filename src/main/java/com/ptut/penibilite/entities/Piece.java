package com.ptut.penibilite.entities;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.*;
import net.minidev.json.JSONObject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity
public class Piece {
    
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull @Column(unique = true, nullable = false)
    private String libelle;
    
    @OneToMany(mappedBy="salle")
    private List<Capteur> capteurs;

    /**
     *
     * @return la liste des types de capteur dans la salle
     */
    public List<String> getTypeCapteur(){
        List<String> listType = new ArrayList<>();
        for(Capteur c : this.getCapteurs()){
            if(!listType.contains(c.getType().getLibelle())) {
                listType.add(c.getType().getLibelle());
            }
        }
        return listType;
    }
}
