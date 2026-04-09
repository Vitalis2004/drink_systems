@Entity @Data @NoArgsConstructor
public class Branch {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;       // NAIROBI, NAKURU, MOMBASA, KISUMU
    private String location;
}