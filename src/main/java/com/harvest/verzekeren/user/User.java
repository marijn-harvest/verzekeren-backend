package com.harvest.verzekeren.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.harvest.verzekeren.auto.AutoVerzekering;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User
{
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false, unique = true)
	private @NonNull String username;

	@Column(nullable = false)
	private String password;

	private String voornaam;

	private String achternaam;

	@OneToOne(mappedBy = "user")
	private AutoVerzekering autoVerzekering;
}
