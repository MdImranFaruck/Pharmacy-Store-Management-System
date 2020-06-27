package com.imran.familypharmacy;

import java.io.Serializable;

class Medicine implements Serializable {
    public static final long serialVersionUID = 20161120L;

    private long m_Id;
    private final String mName;
    private final String mCompany;
    private final String mLocation;
    private final String mPrice;
    private final int mCategory;

    public Medicine(long id, String name, String company, String location, String price, int category) {
        this.m_Id = id;
        mName = name;
        mCompany = company;
        mLocation = location;
        mPrice = price;
        mCategory = category;
    }

    public long getId() {
        return m_Id;
    }

    public String getName() {
        return mName;
    }

    public String getCompany() {
        return mCompany;
    }

    public String getLocation() {
        return mLocation;
    }

    public String getPrice() {
        return mPrice;
    }

    public int getCategory() {
        return mCategory;
    }

    public void setId(long id) {
        this.m_Id = id;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "m_Id=" + m_Id +
                ", mName='" + mName + '\'' +
                ", mCompany='" + mCompany + '\'' +
                ", mLocation='" + mLocation + '\'' +
                ", mPrice=" + mPrice +
                ", mCategory=" + mCategory +
                '}';
    }
}
