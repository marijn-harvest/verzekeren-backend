package com.harvest.verzekeren.auto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.harvest.verzekeren.user.User;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "auto_verzekering")
@Data
@NoArgsConstructor
public class AutoVerzekering
{
	@Id
	@Column(name = "user_id")
	private Long userId;

	@OneToOne
	@PrimaryKeyJoinColumn
	private @NonNull User user;

	private @NonNull String type;
}
