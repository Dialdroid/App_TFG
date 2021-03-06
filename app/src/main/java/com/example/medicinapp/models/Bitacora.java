package com.example.medicinapp.models;

public class Bitacora {


    private String id;
    private String emotion;
    private String activity;
    private String note;
    private String idUser;
    private long timestamp;

    public Bitacora(){

    }

    public Bitacora(String id, String emotion, String activity, String note, String idUser, long timestamp){
        this.id = id;
        this.emotion = emotion;
        this.activity = activity;
        this.note = note;
        this.idUser = idUser;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

}
