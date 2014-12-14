package org.vazteixeira.rui.fanatix.model;

/**
 * Created by rmvt on 14/12/14.
 */
public class Friend {

    public String id;
    public String name;
    public String image;
    public String team;
    public String facebookId;
    public boolean primary;
    public boolean chat;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookid) {
        this.facebookId = facebookid;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    public boolean isChat() {
        return chat;
    }

    public void setChat(boolean chat) {
        this.chat = chat;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", team='" + team + '\'' +
                ", facebookId='" + facebookId + '\'' +
                ", primary=" + primary +
                ", chat=" + chat +
                '}';
    }
}