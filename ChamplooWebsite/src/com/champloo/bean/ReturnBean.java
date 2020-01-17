package com.champloo.bean;

import java.util.Date;

public class ReturnBean
{

	public int getId_return() {
		return id_return;
	}

	public void setId_return(int id_return) {
		this.id_return = id_return;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public int getShipping_address() {
		return shipping_address;
	}

	public void setShipping_address(int shipping_address) {
		this.shipping_address = shipping_address;
	}

	public String getStatus_return() {
		return status_return;
	}

	public void setStatus_return(String status_return) {
		this.status_return = status_return;
	}

	public Date getRequest_date() {
		return request_date;
	}

	public void setRequest_date(Date request_date) {
		this.request_date = request_date;
	}

	public Date getRecepit_date() {
		return recepit_date;
	}

	public void setRecepit_date(Date recepit_date) {
		this.recepit_date = recepit_date;
	}


	private int id_return, order, user, shipping_address;
	private String status_return;
	private Date request_date, recepit_date;

}
