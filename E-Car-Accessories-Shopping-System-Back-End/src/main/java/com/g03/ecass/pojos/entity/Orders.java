package com.g03.ecass.pojos.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Orders extends BaseEntity {

	@Enumerated(EnumType.STRING)
	private OrderStatus orderDeliveryStatus;
	private double totalPrice;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate orderDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate deliveryDate;

	// @OnDelete(action = OnDeleteAction.NO_ACTION)
	@OneToMany(mappedBy = "selectedOrder", cascade = CascadeType.DETACH, orphanRemoval = true)
	List<OrderDetails> orderDetails = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	// @OnDelete(action = OnDeleteAction.NO_ACTION)
	@JoinColumn(name = "customer_id", nullable = true)
	private User selectedCustomer;

	public Orders() {
		System.out.println("in ctor of " + getClass().getName());
	}

	public Orders(OrderStatus orderDeliveryStatus, double totalPrice, LocalDate orderDate, LocalDate deliveryDate) {
		super();
		this.orderDeliveryStatus = orderDeliveryStatus;
		this.totalPrice = totalPrice;
		this.orderDate = orderDate;
		this.deliveryDate = deliveryDate;
	}

	public OrderStatus getOrderDeliveryStatus() {
		return orderDeliveryStatus;
	}

	public void setOrderDeliveryStatus(OrderStatus orderDeliveryStatus) {
		this.orderDeliveryStatus = orderDeliveryStatus;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}

	@JsonIgnore
	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public User getSelectedCustomer() {
		return selectedCustomer;
	}

	@JsonIgnore
	public void setSelectedCustomer(User selectedCustomer) {
		this.selectedCustomer = selectedCustomer;
	}

	/*
	 * public User getSelectedDeliveryBoy() { return selectedDeliveryBoy; }
	 * 
	 * public void setSelectedDeliveryBoy(User selectedDeliveryBoy) {
	 * this.selectedDeliveryBoy = selectedDeliveryBoy; }
	 */
	@Override
	public String toString() {
		return "Orders [orderDeliveryStatus=" + orderDeliveryStatus + ", totalPrice=" + totalPrice + ", orderDate="
				+ orderDate + ", deliveryDate=" + deliveryDate + "]";
	}

}
