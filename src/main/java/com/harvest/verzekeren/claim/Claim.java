package com.harvest.verzekeren.claim;

import com.harvest.verzekeren.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "claim")
@Data
@NoArgsConstructor
public class Claim
{
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @Column
    private String kenteken;
}
