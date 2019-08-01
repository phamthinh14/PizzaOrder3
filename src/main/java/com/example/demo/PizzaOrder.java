package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Calendar;

@Entity
public class PizzaOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private boolean normalDough;

    private boolean glutenFreeDough;

    private boolean redSauce;

    private boolean whiteSauce;

    private double price;

    private boolean cheese;

    private boolean noCheese;

    private boolean mushroom;

    private boolean onions;

    private boolean greenPepper;

    private boolean bacon;

    private boolean pepperoni;

    private boolean sausage;

    private String time;

    private Calendar calendar;

    public PizzaOrder() {
        calendar = Calendar.getInstance();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isNormalDough() {
        return normalDough;
    }

    public void setNormalDough(boolean normalDough) {
        this.normalDough = normalDough;
    }

    public boolean isGlutenFreeDough() {
        return glutenFreeDough;
    }

    public void setGlutenFreeDough(boolean glutenFreeDough) {
        this.glutenFreeDough = glutenFreeDough;
    }

    public boolean isRedSauce() {
        return redSauce;
    }

    public void setRedSauce(boolean redSauce) {
        this.redSauce = redSauce;
    }

    public boolean isWhiteSauce() {
        return whiteSauce;
    }

    public void setWhiteSauce(boolean whiteSauce) {
        this.whiteSauce = whiteSauce;
    }

    public boolean isCheese() {
        return cheese;
    }

    public void setCheese(boolean cheese) {
        this.cheese = cheese;
    }

    public boolean isMushroom() {
        return mushroom;
    }

    public void setMushroom(boolean mushroom) {
        this.mushroom = mushroom;
    }

    public boolean isOnions() {
        return onions;
    }

    public void setOnions(boolean onions) {
        this.onions = onions;
    }

    public boolean isGreenPepper() {
        return greenPepper;
    }

    public void setGreenPepper(boolean greenPepper) {
        this.greenPepper = greenPepper;
    }

    public boolean isBacon() {
        return bacon;
    }

    public void setBacon(boolean bacon) {
        this.bacon = bacon;
    }

    public boolean isPepperoni() {
        return pepperoni;
    }

    public void setPepperoni(boolean pepperoni) {
        this.pepperoni = pepperoni;
    }

    public boolean isSausage() {
        return sausage;
    }

    public void setSausage(boolean sausage) {
        this.sausage = sausage;
    }

    public boolean isNoCheese() {
        return noCheese;
    }

    public void setNoCheese(boolean noCheese) {
        this.noCheese = noCheese;
    }

    public double getPrice() {

        return price;
    }

    public void setPrice(double price) {
        this.price = price;

    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        calendar.add(Calendar.MINUTE, 50);
        time = String.valueOf(calendar.getTime());
        this.time = time;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public double addUpTotal() {

        return getPrice() + 1;
    }


}
