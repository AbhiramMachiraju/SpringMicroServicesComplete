package com.abhiram.orderrservice.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="ORDERS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Long autoId;
	
	@Column(name="ORDERNUMBER")
	private String orderNumber;
	
	@OneToMany(mappedBy="orderTO",cascade=CascadeType.ALL)
	private List<OrderLineTO> orderList;
	
	
}
