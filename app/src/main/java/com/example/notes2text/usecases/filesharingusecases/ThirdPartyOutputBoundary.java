package com.example.notes2text.usecases.filesharingusecases;

public interface ThirdPartyOutputBoundary {

    /**
     * Using the list of content Uri in the given outputModel, create a new Intent
     * to access the share sheet function.
     *
     * @param outputModel A model containing a list of Uri, and a Context.
     */
    void intentShare(ThirdPartyOutputModel outputModel);
}