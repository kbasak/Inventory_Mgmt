package com.ims.model;

//@Entity
//@Table(name = "`order`")
public class Order {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderId;
	private String locationNumber;
	private long materialId;
	private int orderQty;
	private String orderSatus;
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(long orderId, String locationNumber, long materialId, int orderQty, String orderSatus) {
		super();
		this.orderId = orderId;
		this.locationNumber = locationNumber;
		this.materialId = materialId;
		this.orderQty = orderQty;
		this.orderSatus = orderSatus;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public String getLocationNumber() {
		return locationNumber;
	}
	public void setLocationNumber(String locationNumber) {
		this.locationNumber = locationNumber;
	}
	public long getMaterialId() {
		return materialId;
	}
	public void setMaterialId(long materialId) {
		this.materialId = materialId;
	}
	public int getOrderQty() {
		return orderQty;
	}
	public void setOrderQty(int orderQty) {
		this.orderQty = orderQty;
	}
	public String getOrderSatus() {
		return orderSatus;
	}
	public void setOrderSatus(String orderSatus) {
		this.orderSatus = orderSatus;
	}
}
