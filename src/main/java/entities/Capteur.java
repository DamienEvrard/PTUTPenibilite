package entities;


@Getter @Setter @NoArgsConstructor @ToString
@Entity
public class Capteur {
    private int id;
    private String libelle;
    private int frequenceMeusure;
    
    @ManyToOne
    private Salle salle;
    
    @ManyToOne
    private TypeCapteur type;
    
    @OneToMany(mappedBy="capteur")
    List<Mesure> mesures;
    
}
