package com.harvest.verzekeren.auto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "auto_verzekering")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class AutoVerzekering
{
	@Id
	@GeneratedValue
	private Long id;

	private @NonNull String voornaam;

	private @NonNull String achternaam;

	private @NonNull String type;
}
