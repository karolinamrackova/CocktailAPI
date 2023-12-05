package project.week0project.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table (name = "cocktails")
public class Cocktail {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String alcoholic;
    private String instructions;


}
