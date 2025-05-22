package com.example.Bill_Service.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="BILL")
public class BillModel {

     @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
     private Integer  id_bill;

    @Column(name="BILL_DATE")
    private Date date_bill;

    @Column(name = "QUANTITY")
    private int quantity_bill;

    @Column(name = "TOTAL_PRICE")
    private double totalPrice_bill;

    @Column(name = "LIB")
    private String lib_bill;

    public BillModel(Integer id_bill, Date date_bill, int quantity_bill, String lib_bill, double totalPrice_bill) {
        this.id_bill = id_bill;
        this.date_bill = date_bill;
        this.quantity_bill = quantity_bill;
        this.lib_bill = lib_bill;
        this.totalPrice_bill = totalPrice_bill;
    }

    public BillModel() {
    }

    public Integer getId_bill() {
        return id_bill;
    }

    public void setId_bill(Integer id_bill) {
        this.id_bill = id_bill;
    }

    public String getLib_bill() {
        return lib_bill;
    }

    public void setLib_bill(String lib_bill) {
        this.lib_bill = lib_bill;
    }

    public double getTotalPrice_bill() {
        return totalPrice_bill;
    }

    public void setTotalPrice_bill(double totalPrice_bill) {
        this.totalPrice_bill = totalPrice_bill;
    }

    public int getQuantity_bill() {
        return quantity_bill;
    }

    public void setQuantity_bill(int quantity_bill) {
        this.quantity_bill = quantity_bill;
    }

    public Date getDate_bill() {
        return date_bill;
    }

    public void setDate_bill(Date date_bill) {
        this.date_bill = date_bill;
    }
}
