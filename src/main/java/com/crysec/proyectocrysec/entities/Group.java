package com.crysec.proyectocrysec.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="crysec_group")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private String type;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "groups_members",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<CrysecUser> members;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "groups_admins",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<CrysecUser> admins;

    public Group(String name, String type) {
        this.name = name;
        this.type = type;
    }
    // Helpers
    public void addMember(CrysecUser user) {
        if(members == null) {
            members = new ArrayList<CrysecUser>();
        }
        members.add(user);
    }
    public void removeMember(CrysecUser user) {
        members.remove(user);
    }
    public void addAdmin(CrysecUser admin) {
        if(admins == null) {
            admins = new ArrayList<CrysecUser>();
        }
        admins.add(admin);
    }
    public void removeAdmin(CrysecUser admin) {
        admins.remove(admin);
    }
    public void addMembersToGroup(List<CrysecUser> users){
        if(members == null){
            members = new ArrayList<CrysecUser>();
        }
        members.addAll(users);
    }
}
