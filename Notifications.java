package com.imran.familypharmacy;

import java.io.Serializable;

public class Notifications implements Serializable {
    public static final long serialVersionUID = 20161120L;

    private long m_Id;
    private final String mSenderName;
    private final String mSubject;
    private final String mContent;
    private final int mPharmacyID;

    public Notifications(long m_Id, String senderName, String subject, String content, int pharmacyID) {
        this.m_Id = m_Id;
        mSenderName = senderName;
        mSubject = subject;
        mContent = content;
        mPharmacyID = pharmacyID;
    }

    public long getId() {
        return m_Id;
    }

    public String getSenderName() {
        return mSenderName;
    }

    public String getSubject() {
        return mSubject;
    }

    public String getContent() {
        return mContent;
    }

    public int getPharmacyID() {
        return mPharmacyID;
    }

    public void setId(long id) {
        this.m_Id = id;
    }

    @Override
    public String toString() {
        return "Notifications{" +
                "m_Id=" + m_Id +
                ", mSenderName='" + mSenderName + '\'' +
                ", mSubject='" + mSubject + '\'' +
                ", mContent='" + mContent + '\'' +
                ", mPharmacyID='" + mPharmacyID + '\'' +
                '}';
    }
}