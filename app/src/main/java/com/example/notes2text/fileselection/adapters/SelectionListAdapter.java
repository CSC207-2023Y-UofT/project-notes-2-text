package com.example.notes2text.fileselection.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.notes2text.R;
import com.example.notes2text.entities.SelectionViewHolder;
import com.example.notes2text.fileselection.usecases.SelectionInputBoundary;
import com.example.notes2text.usecases.DirectoryUseCases.SelectionViewHolderFactory;
import com.example.notes2text.usecases.FileMenuFactory;
import com.example.notes2text.usecases.DirectoryUseCases.ViewHolderAbsFactory;

import java.io.File;
import java.util.ArrayList;

public class SelectionListAdapter extends RecyclerView.Adapter<SelectionViewHolder> {

    protected Context context;
    protected File[] fileList;

    protected SelectionInputBoundary selectionUseCase;

    protected ViewHolderAbsFactory viewHolderFactory = new SelectionViewHolderFactory();


    /**
     * Produces a recyclerview adapter appropriate for displaying files and mapping selection action patterns.
     * @param context Application context.
     * @param fileList List of files to display.
     * @param selectionUseCase The selection interactor implementation to use.
     */
    public SelectionListAdapter(Context context, File[] fileList, SelectionInputBoundary selectionUseCase) {
        super();
        this.fileList = fileList;
        this.context = context;
        this.selectionUseCase = selectionUseCase;
    }
    public SelectionListAdapter(Context context, File[] fileList, SelectionInputBoundary selectionUseCase, ArrayList<File> inputFileList) {
        super();
        this.fileList = fileList;
        this.context = context;
        this.selectionUseCase = selectionUseCase;
        for (File file:inputFileList) {
            this.selectionUseCase.addItem(file);
        }
        //Selected file list is stored within the usecase, not the adapter.
    }

    @NonNull
    @Override
    public SelectionViewHolder onCreateViewHolder(@NonNull ViewGroup source, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.selection_holder_view_model, source, false);
        return (SelectionViewHolder) viewHolderFactory.create(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectionViewHolder holder, int position) {
        File chosenFile = fileList[position];
        //Changes the text element of the holder to match the name of the file.
        holder.textElement.setText(chosenFile.getName());

        if (chosenFile.isDirectory()) {
            // If the file in question is a folder, represent it as a folder.
            holder.imageElement.setImageResource(R.drawable.baseline_folder_24);
        } else {
            // the file is not a folder, represent it as just a regular file.
            holder.imageElement.setImageResource(R.drawable.baseline_insert_drive_file_24);
        }



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            //If item was clicked, add the file to the list of files, and display appropriate changes to the holder object.
            @Override
            public void onClick(View view) {
//                if(chosenFile.isDirectory()){
//                    // If the file is a directory(folder), enter the folder.
//                    //DirectoryActivity for pure directory, DirectoryAccessController for whole app.
//                    Intent intent = new Intent(context, ActivitySwitchController.class);
//                    String path = chosenFile.getAbsolutePath();
//                    intent.putExtra("path",path);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivity(intent);
//
//                }
                //If item is checked, remove it from the list. If not, add it to the list.
                if (holder.selectionCheck.isChecked()) {
                    //visual indication
                    holder.selectionCheck.setChecked(false);
                    holder.viewElement.setBackgroundColor(0x00008080);
                    // remove item from selection list.
                    selectionUseCase.removeItem(chosenFile);
                } else {
                    //visual indication
                    holder.selectionCheck.setChecked(true);
                    holder.viewElement.setBackgroundColor(0xFF008080);
                    //add item to selected list.
                    selectionUseCase.addItem(chosenFile);
                }
            }
        });

        //LongClick brings up the same file menu as in the regular directory access view.
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                SelectionMenuController fileMenuController = new SelectionMenuController(context, view, new FileMenuFactory());
                return fileMenuController.create(chosenFile, getSelectedFiles());

            }
        });
    }

    @Override
    public int getItemCount() {
        return fileList.length;
    }

    //Public API method to access the list of selected files from another class.

    /**
     *
     * @return the list of selected files, stored within the selection usecase.
     */
    public ArrayList<File> getSelectedFiles(){
        return selectionUseCase.getSelectedFiles();
    }


}
