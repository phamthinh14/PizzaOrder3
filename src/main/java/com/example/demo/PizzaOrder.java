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

    //    @NotNull
//    @Size(min = 3)
    private boolean normalDough;
    //    @NotNull
//    @Size(min = 3)
    private boolean glutenFreeDough;
    //    @NotNull
//    @Size(min = 3)
    private boolean redSauce;

    private double price;

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

    public double getPrice() {
//        if (isNormalDough()) {
//            this.price += 1;
//        }
//        if (isGlutenFreeDough()) {
//            this.price += 1;
//        }
//        if (isRedSauce()) {
//            this.price += 1;
//        }
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
