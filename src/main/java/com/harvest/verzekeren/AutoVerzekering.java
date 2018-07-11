package com.harvest.verzekeren;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NonNull;

@Entity
@Table(name = "auto_verzekering")
@Data
public class AutoVerzekering
{
	@Id
	@GeneratedValue
	private Long id;

	private @NonNull String voornaam;

	private @NonNull String achternaam;

	private @NonNull String type;
}