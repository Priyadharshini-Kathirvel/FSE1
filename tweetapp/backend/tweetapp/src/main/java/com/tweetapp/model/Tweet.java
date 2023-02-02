package com.tweetapp.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Tweet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int tweetId;

	@NotBlank(message = "Tweet cannot be empty.")
	@Size(max = 144)
	private String tweetContent;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Reply> reply = new ArrayList<>();

	@ManyToOne(fetch = FetchType.EAGER)
	private User user;

	private Set<String> likedBy = new HashSet<>();

}
