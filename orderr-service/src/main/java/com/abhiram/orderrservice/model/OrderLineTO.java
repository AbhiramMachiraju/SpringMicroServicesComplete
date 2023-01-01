package com.abhiram.orderrservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ORDER_LINE")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long autoId;
	@Column(name = "SKUCODE")
	private String skuCode;
	@Column(name = "PRICE")
	private Double price;
	@Column(name = "QUANTITY")
	private int quantity;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "ORDER_ID")
	private OrderTO orderTO;

}
