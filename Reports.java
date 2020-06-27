package com.imran.familypharmacy;

import java.io.Serializable;

public class Reports implements Serializable {

    public static final long serialVersionUID = 20161120L;

    private long m_Id;
    private final String mPharmacy_ID;
    private final String mPharmacy_Location;
    private final String mPharmacist_name;
    private final String mEnail;
    private final String mEmergency_Note;
    private final String mSubject;
    private final String mReports_Details;

    public Reports(long m_Id, String pharmacy_ID, String pharmacy_Location, String pharmacist_name, String enail, String emergency_Note, String subject, String reports_Details) {
        this.m_Id = m_Id;
        mPharmacy_ID = pharmacy_ID;
        mPharmacy_Location = pharmacy_Location;
        mPharmacist_name = pharmacist_name;
        mEnail = enail;
        mEmergency_Note = emergency_Note;
        mSubject = subject;
        mReports_Details = reports_Details;
    }

    public long getId() {
        return m_Id;
    }

    public String getPharmacy_ID() {
        return mPharmacy_ID;
    }

    public String getPharmacy_Location() {
        return mPharmacy_Location;
    }

    public String getPharmacist_name() {
        return mPharmacist_name;
    }

    public String getEnail() {
        return mEnail;
    }

    public String getEmergency_Note() {
        return mEmergency_Note;
    }

    public String getSubject() {
        return mSubject;
    }

    public String getReports_Details() {
        return mReports_Details;
    }

    public void setId(long Id) {
        this.m_Id = Id;
    }

    @Override
    public String toString() {
        return "Reports{" +
                "m_Id=" + m_Id +
                ", mPharmacy_ID='" + mPharmacy_ID + '\'' +
                ", mPharmacy_Location='" + mPharmacy_Location + '\'' +
                ", mPharmacist_name='" + mPharmacist_name + '\'' +
                ", mEnail='" + mEnail + '\'' +
                ", mEmergency_Note='" + mEmergency_Note + '\'' +
                ", mSubject='" + mSubject + '\'' +
                ", mReports_Details='" + mReports_Details + '\'' +
                '}';
    }
}
