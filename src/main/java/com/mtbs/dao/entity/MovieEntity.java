/**
 * 
 */
package com.mtbs.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mtbs.enums.CertificateType;
import com.mtbs.enums.MovieLanguage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "movies")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class MovieEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(name = "language", nullable = false)
	private MovieLanguage language;

	@Enumerated(EnumType.STRING)
	@Column(name = "certificate_type", nullable = false)
	private CertificateType certificateType;

	@Column(name = "release_date", columnDefinition = "DATE", nullable = false)
	private LocalDate releaseDate;

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
	@JsonIgnore
	@Builder.Default
	private List<ShowEntity> shows = new ArrayList<>();
}