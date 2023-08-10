package com.example.notes2text.usecases.FileEditingUseCases;

/**
 * Interface for EditTextInteractor. EditTextInteractor implements the text editor capabilities of
 * the app (i.e., capitalize and lowercase).
 */
public interface EditTextBoundary {
    /**
     * To capitalize the selected portion of the text
     * @param start The index of the beginning of the selected portion of the String representing
     *              the text in the text box
     * @param end The index of the ending of the selected portion of the String representing the
     *            text in the text box
     * @return Returns the entire text of the text file with the selected portions capitalized
     */
    String capitalize(int start, int end);

    /**
     * To lowercase the selected portion of the text
     * @param start The index of the beginning of the selected portion of the String representing
     *              the text in the text box
     * @param end The index of the ending of the selected portion of the String representing the
     *            text in the text box
     * @return Returns the entire text of the text file with the selected portions lowercased
     */
    String lowercase(int start, int end);
}

