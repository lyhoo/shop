package com.example.lyhoo.shop;

public class GoodsInfo {
    private int id;
    private String name;
    private boolean isChoosed;
    private String imageUrl;
    private String desc;
    private double price;
    private double prime_price;
    private int postion;
    private int count;
    private String color;
    private String size;
    private int goodsImg;
    private int shopcartid;

    public GoodsInfo(int id, String name, String desc,double price, double prime_price,
                     String color, String size, int goodsImg,int count) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.prime_price = prime_price;
        this.count = count;
        this.color = color;
        this.size = size;
        this.goodsImg = goodsImg;
    }
    public void setShopcartid(int Shopcartid){
        shopcartid = Shopcartid;
    }
    public int getShopcartid(){
        return shopcartid;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChoosed() {
        return isChoosed;
    }

    public void setChoosed(boolean choosed) {
        isChoosed = choosed;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrime_price() {
        return prime_price;
    }

    public void setPrime_price(double prime_price) {
        this.prime_price = prime_price;
    }

    public int getPostion() {
        return postion;
    }

    public void setPostion(int postion) {
        this.postion = postion;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(int goodsImg) {
        this.goodsImg = goodsImg;
    }
}
