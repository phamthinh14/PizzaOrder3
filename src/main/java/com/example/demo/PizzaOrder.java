package com.example.demo;

import javax.persistence.*;
import java.util.Calendar;

@Entity
public class PizzaOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private boolean smallSize;

    private boolean mediumSize;

    private boolean largeSize;

    private boolean normalDough;

    private boolean glutenFreeDough;

    private boolean redSauce;

    private boolean whiteSauce;

    private boolean noSauce;

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

    //merger security here
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

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

    public boolean isSmallSize() {
        return smallSize;
    }

    public void setSmallSize(boolean smallSize) {
        this.smallSize = smallSize;
    }

    public boolean isMediumSize() {
        return mediumSize;
    }

    public void setMediumSize(boolean mediumSize) {
        this.mediumSize = mediumSize;
    }

    public boolean isLargeSize() {
        return largeSize;
    }

    public void setLargeSize(boolean largeSize) {
        this.largeSize = largeSize;
    }

    public boolean isNoSauce() {
        return noSauce;
    }

    public void setNoSauce(boolean noSauce) {
        this.noSauce = noSauce;
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

    //merger security here
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double addUpTotal() {
        return getPrice() + 1;
    }

    @Override
    public String toString() {
        String result = "";
        if (isGlutenFreeDough()) {
            result += " Gluten-Free Dough ";
        }
        if (isNormalDough()) {
            result += " Normal Dough ";
        }
        if (isRedSauce()) {
            result += " Red Sauce ";
        }
        if (isWhiteSauce()) {
            result += " White Sauce ";
        }
        if (isCheese()) {
            result += " Cheese Included ";
        }
        if (isMushroom()) {
            result += " Mushroom ";
        }
        if (isOnions()) {
            result += " Onions ";
        }
        if (isGreenPepper()) {
            result += " Green Pepper ";
        }
        if (isBacon()) {
            result += " Bacon ";
        }
        if (isPepperoni()) {
            result += " Pepperoni ";
        }
        if (isSausage()) {
            result += " Sausage ";
        }
        if (isSmallSize()) {
            result += " Size Small ";
        }
        if (isMediumSize()) {
            result += " Size Medium ";
        }
        if (isLargeSize()) {
            result += " Size Large ";
        }
        result += " " + getPrice() + " ";
        return result;
    }
}
