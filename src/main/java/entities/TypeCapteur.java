package entities;


@Getter @Setter @NoArgsConstructor @ToString
@Entity
public class TypeCapteur {
    private int id;
    private String libelle;
    private float limiteMax;
    private float limiteMin;
    
    @OneToMany(mappedBy="type")
    private Liste<Capteur> capteurs;
}
