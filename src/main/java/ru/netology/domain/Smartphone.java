package ru.netology.domain;

public class Smartphone extends Product {
  private String mfr;

  public String getMfr() {
    return mfr;
  }

  public Smartphone() {
    super();
  }

  public Smartphone(int id, String name, int price, String mfr) {
    super(id, name, price);
    this.mfr = mfr;
  }

}