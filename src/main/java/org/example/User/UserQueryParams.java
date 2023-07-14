package org.example.User;

import org.example.QueryParams;

public class UserQueryParams extends QueryParams{
    private int userID = 0;

    public boolean hasName() {
        return name != null;
    }

    public boolean hasID(){
        return userID != 0;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setID(int ID){
        this.userID = ID;
    }

    public String getName(){
        return this.name;
    }

    public int getID(){
        return this.userID;
    }
}
