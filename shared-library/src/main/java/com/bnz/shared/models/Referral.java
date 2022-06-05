package com.bnz.shared.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "referrals")
public class Referral {
    @Id
    private String id;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String userId;
    private String referral;
    @DBRef
    private List<User> referredUsers;

    public String getReferral() {
        return referral;
    }

    public void setReferral(String referral) {
        this.referral = referral;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<User> getReferredUsers() {
        this.referredUsers.forEach(user -> {
            user.setPassword(null);
            user.setRole(0);
            user.setScore(0);
            user.setRole(0);
            user.setId(null);
        });
        return referredUsers;
    }


    public void setReferredUsers(List<User> referredUsers) {
        this.referredUsers = referredUsers;
    }

    public void addNewReferredUser(User user) {
        if(referredUsers  == null) {
            referredUsers = new ArrayList<>();
        }

        referredUsers.add(user);
    }

    @Override
    public String toString() {
        return "ReferralModel{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", referredUsers=" + referredUsers +
                '}';
    }
}
