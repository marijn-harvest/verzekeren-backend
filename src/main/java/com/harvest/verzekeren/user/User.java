package com.harvest.verzekeren.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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

	private @NonNull String password;
}
