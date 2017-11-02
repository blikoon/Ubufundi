package com.blikoon.recyclerview.model;

import android.content.ContentValues;

/**
 * Created by gakwaya on 2017/11/1.
 */

public class Contact {

    private String displayName;
    private String jid;
    private ContactType contactType;


    public static final String TABLE_NAME = "contacts";

    public static final class Cols
    {
        public static final String displayName = "displayName";
        public static final String jid = "jid";
        public static final String contactType = "contactType";
    }


    public Contact(String displayName, String jid, ContactType contactType) {
        this.displayName = displayName;
        this.jid = jid;
        this.contactType = contactType;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

    public ContentValues getContentValues()
    {
        ContentValues values = new ContentValues();
        values.put("displayName", displayName);
        values.put("jid", jid);
        values.put("contactType",getTypeStringValue(contactType));

        return values;

    }

    public String getTypeStringValue(ContactType type)
    {
        if(type== ContactType.ONE_ON_ONE)
            return "ONE_ON_ONE";
        else
            return "GROUP";
    }

    public enum ContactType{
        ONE_ON_ONE,GROUP
    }
}
