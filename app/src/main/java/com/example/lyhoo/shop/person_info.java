package com.example.lyhoo.shop;

import android.content.ContentValues;

import java.util.ArrayList;
import java.util.List;

public class person_info {
    private static String Name = "";
    private static String Password = "";
    private static int Id;
    private static String Address = "";
    private static String Phone = "";
    private static String Consignee = "";
    private static List<String> GoodName = new ArrayList<String>();
    private static List<Double> GoodPrice = new ArrayList<Double>();
    private static List<Integer> GoodNum = new ArrayList<Integer>();
    private static List<Integer> GoodIcon  = new ArrayList<Integer>();

    private static List<ContentValues> buyGood = new ArrayList<ContentValues>();
    private static List<ContentValues> browsingHistory = new ArrayList<ContentValues>();
    public void addbuygood(ContentValues contentValues){
        buyGood.add(contentValues);
    }
    public void addbrowsingHistory(ContentValues contentValues){
        browsingHistory.add(contentValues);
    }
    public void clearbuygood(){
        buyGood.clear();
    }
    public void clearbrowsingHistory(){
        browsingHistory.clear();
    }
    public List<ContentValues> getbuygood(){
        return buyGood;
    }
    public List<ContentValues> getbrowsingHistory(){
        return browsingHistory;
    }
    public person_info(String name,String password,int id){
        Name = name;
        Password = password;
        Id = id;
    }
    public person_info(){

    }
    public String getName(){
        return Name;
    }
    public String getPassword(){
        return Password;
    }
    public String getAddress(){
        return Address;
    }
    public String getPhone(){
        return Phone;
    }
    public String getConsignee(){
        return Consignee;
    }
    public void addAddress(String address){
        Address = address;
    }
    public void addPhone(String phone){
        Phone = phone;
    }
    public void addConsignee(String consignee){
        Consignee = consignee;
    }
    public List getGoodName(){
        return GoodName;
    }
    public List getGoodPrice(){
        return GoodPrice;
    }
    public List getGoodNum(){
        return GoodNum;
    }
    public List getGoodIcon(){
        return GoodIcon;
    }
    public int getGoodSun(){
        return GoodName.size();
    }
    public int getId(){return  Id;}
    public void addGoodName(String name){
        GoodName.add(0,name);
    }
    public void addGoodPrice(Double price){
        GoodPrice.add(0,price);
    }
    public void addGoodNum(int num){
        GoodNum.add(0,num);
    }
    public void addGoodIcon(int icon){
        GoodIcon.add(0,icon);
    }
    public void addId(int id){
        Id=id;
    }
}
