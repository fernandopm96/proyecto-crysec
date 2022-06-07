package com.crysec.proyectocrysec.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CrysecUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private String email;
    @JoinColumn(name = "company_id")
    @ManyToOne
    private Company company;

    @JsonIgnore
    @ManyToMany(mappedBy = "members")
    private List<Group> groups;
    @JsonIgnore
    @ManyToMany(mappedBy = "admins")
    private List<Group> admins;
}
