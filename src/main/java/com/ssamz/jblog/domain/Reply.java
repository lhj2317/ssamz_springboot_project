package com.ssamz.jblog.domain;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reply {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 200)
	private String content;
	
	@CreationTimestamp
	private Timestamp createDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userid")
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="postid")
	private Post post; 
}
