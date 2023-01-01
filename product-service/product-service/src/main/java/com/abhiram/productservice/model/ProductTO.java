package com.abhiram.productservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Table(name="PRODUCT")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductTO {
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Long autoId;
	
	@Column(name="NAME")
	private String name;
	@Column(name="DESCRP")
	private String descrpition;
	@Column(name="PRICE")
	private Double price;
	

}
