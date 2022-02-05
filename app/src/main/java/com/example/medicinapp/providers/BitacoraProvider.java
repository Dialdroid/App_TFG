package com.example.medicinapp.providers;

import com.example.medicinapp.models.Bitacora;
import com.example.medicinapp.models.Comment;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class BitacoraProvider {

    CollectionReference mCollection;

    public BitacoraProvider() {
        mCollection = FirebaseFirestore.getInstance().collection("Bitacora"); }

    public Task<Void> save(Bitacora bitacora) { return mCollection.document().set(bitacora); }

    public Query getAll(){ return mCollection.orderBy("timestamp", Query.Direction.DESCENDING); }

    public Task<DocumentSnapshot> getBitacoraById(String id){ return mCollection.document(id).get();}

    public Query getBitacoraByUser(String id){return mCollection.whereEqualTo("idUser", id).orderBy("timestamp", Query.Direction.DESCENDING); }

    public Query getEmotion(String emotion){ return mCollection.whereEqualTo("Emotion", emotion); }

    public Task<Void> delete(String id) { return mCollection.document(id).delete();}


}
