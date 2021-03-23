package entities;


@Getter @Setter @NoArgsConstructor @ToString
@Entity
public class Piece {
    private int id;
    private String libelle;
    
    @OneToMany(mappedBy="salle")
    private List<Capteur> Capteurs;
}
