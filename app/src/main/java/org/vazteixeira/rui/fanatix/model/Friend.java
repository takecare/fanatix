package org.vazteixeira.rui.fanatix.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rmvt on 14/12/14.
 */
public class Friend implements Parcelable {

    public static final String TEAM_ALL = "all";
    public static final String TEAM_OTHER = "other";

    public String id;
    public String name;
    public String image;
    public String team;
    public String facebookId;
    public boolean primary;
    public boolean chat;
    private boolean selected;

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

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isRecommended() {

        return team != null && team.length() > 0 && !team.equals(TEAM_ALL) && !team.equals(TEAM_OTHER);
    }

    public boolean isOther() {

        return team != null && team.equals(TEAM_OTHER);
    }

    public boolean isAll() {

        return team != null && team.equals(TEAM_ALL);
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

    protected Friend(Parcel in) {
        id = in.readString();
        name = in.readString();
        image = in.readString();
        team = in.readString();
        facebookId = in.readString();
        primary = in.readByte() != 0x00;
        chat = in.readByte() != 0x00;
        selected = in.readByte() != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(image);
        dest.writeString(team);
        dest.writeString(facebookId);
        dest.writeByte((byte) (primary ? 0x01 : 0x00));
        dest.writeByte((byte) (chat ? 0x01 : 0x00));
        dest.writeByte((byte) (selected ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Friend> CREATOR = new Parcelable.Creator<Friend>() {
        @Override
        public Friend createFromParcel(Parcel in) {
            return new Friend(in);
        }

        @Override
        public Friend[] newArray(int size) {
            return new Friend[size];
        }
    };
}