package com.example.miniproject01.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
public class Rv_List implements Parcelable {
    private List<Notes> notes;

    public Rv_List() {
        this.notes = new ArrayList<>();
    }

    protected Rv_List(Parcel in) {
        notes = in.createTypedArrayList(Notes.CREATOR);
    }

    public static final Creator<Rv_List> CREATOR = new Creator<Rv_List>() {
        @Override
        public Rv_List createFromParcel(Parcel in) {
            return new Rv_List(in);
        }

        @Override
        public Rv_List[] newArray(int size) {
            return new Rv_List[size];
        }
    };

    public List<Notes> getNotes() {
        return notes;
    }

    public void addNotes(Notes note) {
        this.notes.add(note);
    }

    public void removeNotes(int index) {
        Notes note = notes.get(index);
        this.notes.remove(note);
    }

    public void updateNotes(int index, Notes note) {
        this.notes.set(index, note);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(notes);
    }
}