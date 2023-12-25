/**
 * 
 */
package com.mtbs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@Entity
@EntityListeners(value = { AuditingEntityListener.class })
@Table(name = "tickets")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class TicketEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "alloted_seats", nullable = false)
	private String allottedSeats;

	@Column(name = "amount", nullable = false)
	private double amount;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "booked_at", nullable = false)
	private Date bookedAt;

	@ManyToOne
	@JsonIgnore
	private UserEntity user;

	@ManyToOne
	@JsonIgnore
	private ShowEntity show;

	@OneToMany(mappedBy = "seatNumber", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<ShowSeatsEntity> seats;
}