package com.example.notes2text.fileselection.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;;import com.example.notes2text.R;
import com.example.notes2text.adapters.ActivitySwitchController;
import com.example.notes2text.adapters.FileListAdaptor;
import com.example.notes2text.adapters.FileMenuController;
import com.example.notes2text.entities.FileViewHolder;
import com.example.notes2text.entities.SelectionViewHolder;
import com.example.notes2text.usecases.FileMenuFactory;

import java.io.File;
import java.util.ArrayList;

public class SelectionListAdapter extends RecyclerView.Adapter<SelectionViewHolder> {

    private ArrayList<File> selectedFiles;
    private Context context;
    protected File[] fileList;

    public SelectionListAdapter(Context context, File[] fileList) {
        super();
        this.fileList = fileList;
        this.context = context;
        selectedFiles = new ArrayList<>();
    }

    @NonNull
    @Override
    public SelectionViewHolder onCreateViewHolder(@NonNull ViewGroup source, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.selection_holder_view_model, source, false);
        return new SelectionViewHolder(view);
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
                if(chosenFile.isDirectory()){
                    // If the file is a directory(folder), enter the folder.
                    //DirectoryActivity for pure directory, DirectoryAccessController for whole app.
                    Intent intent = new Intent(context, ActivitySwitchController.class);
                    String path = chosenFile.getAbsolutePath();
                    intent.putExtra("path",path);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }
            }
        });

        //LongClick brings up the same file menu as in the regular directory access view.
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                FileMenuController fileMenuController = new FileMenuController(context, view, new FileMenuFactory());
                return fileMenuController.create(chosenFile);

            }
        });
    }

    @Override
    public int getItemCount() {
        return fileList.length;
    }

    //Public API method to access the list of selected files from another class.
    public ArrayList<File> getSelectedFiles(){
        return selectedFiles;
    }


}
