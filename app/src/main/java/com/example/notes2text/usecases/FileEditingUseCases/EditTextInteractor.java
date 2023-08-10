package com.example.notes2text.usecases.FileEditingUseCases;


import android.text.Spannable;


public class EditTextInteractor implements EditTextBoundary{

    private String content;

    /**
     * Constructor for EditTextInteractor
     * @param text The text shown in the text box displayed to the user on the text editor page
     */
    public EditTextInteractor(Spannable text) {
        this.content = text.toString();
    }

    // Capitalize text selected by user
    public String capitalize(int start, int end) {
        String unedited = this.content;
        String textToChange = this.content.substring(start, end);
        String edited = textToChange.toUpperCase();
        String altered = unedited.replace(textToChange, edited);
        this.content = altered;

        return altered;
    }

    // Lowercase text selected by user
    public String lowercase(int start, int end) {
        String unedited = this.content;
        String textToChange = this.content.substring(start, end);
        String edited = textToChange.toLowerCase();
        String altered = unedited.replace(textToChange, edited);
        this.content = altered;

        return altered;
    }
}
