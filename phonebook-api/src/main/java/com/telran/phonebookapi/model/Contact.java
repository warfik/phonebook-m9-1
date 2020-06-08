package com.telran.phonebookapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    private String name;
    @Setter
    private String lastName;
    @Setter
    private String description;
    @ElementCollection
    private List<String> emails;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    User user;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.REMOVE)
    private List<PhoneNumber> numbers = new ArrayList<>();

    @OneToMany(mappedBy = "contact", cascade = CascadeType.REMOVE)
    private List<Address> addresses = new ArrayList<>();

    public Contact(String name, String lastName, String description, List<String> emails, User user) {
        this.name = name;
        this.lastName = lastName;
        this.description = description;
        this.user = user;
        this.emails = emails;
    }
}
