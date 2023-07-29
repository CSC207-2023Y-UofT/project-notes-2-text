package com.example.notes2text.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes2text.R;
import com.example.notes2text.adapters.fragments.DirectoryAccessController;
import com.example.notes2text.usecases.FileMenuInputBoundary;
import com.example.notes2text.usecases.FileMenuInteractor;
import com.example.notes2text.usecases.FileOpenInteractor;

import java.io.File;

public class FileListAdaptor extends RecyclerView.Adapter<FileListAdaptor.ViewHolder> {

    Context context;
    File[] fileList;


    FileOpenInteractor fileOpener = new FileOpenInteractor();

    public FileListAdaptor(Context context, File[] fileList){
        super();
        this.context = context;
        this.fileList = fileList;
    }

    //A function that creates the ViewHolder required for the recyclerview for file list.
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup source, int ViewType){

        View view = LayoutInflater.from(context).inflate(R.layout.file_holder_view_model, source, false);
        return new ViewHolder(view);
    }

    //Binds elements to the created viewholder, and attaches actions to them.
    @Override
    public void onBindViewHolder(@NonNull FileListAdaptor.ViewHolder holder, int position) {

        File chosenFile = fileList[position];
        //Changes the text element of the holder to match the name of the file.
        holder.textElement.setText(chosenFile.getName());

        if (chosenFile.isDirectory()){
            // If the file in question is a folder, represent it as a folder.
            holder.imageElement.setImageResource(R.drawable.baseline_folder_24);
        } else{
            // the file is not a folder, represent it as just a regular file.
            holder.imageElement.setImageResource(R.drawable.baseline_insert_drive_file_24);

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
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

//                    // Switch version.
//                    ActivitySwitchController switchControl = new ActivitySwitchController();
//                    String path = chosenFile.getAbsolutePath();
//                    DirectoryAccessController dacFragment = DirectoryAccessController.newInstance(path);
//                    switchControl.replaceFragment(dacFragment);

                } else {
                    //Determine the type of the file in question.
                    try {
                        // The file is a file. Open the file.
                        fileOpener.openFile(context, chosenFile);
                    }catch (Exception e) {
                        Toast.makeText(context.getApplicationContext(), "File not openable.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Sets the holder object to listen for the user long clicking the file/folder
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                PopupMenu fileMenu = new PopupMenu(context, view);
                fileMenu.getMenu().add("OPEN");
                fileMenu.getMenu().add("MOVE");
                fileMenu.getMenu().add("SHARE");
                fileMenu.getMenu().add("RENAME");
                fileMenu.getMenu().add("DELETE");

                // Set the use case interactor for the newly created file menu.
                FileMenuInputBoundary fileMenuUseCase = new FileMenuInteractor(fileMenu, chosenFile);

                fileMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getTitle().equals("OPEN")){
                            //open file or folder.
                            fileMenuUseCase.open(context, view);
                        } else if (menuItem.getTitle().equals("MOVE")){
                            // move item.
                            fileMenuUseCase.move(context, view);
                        }
                        if (menuItem.getTitle().equals("SHARE")){
                            // Redirect to third party share.
                            fileMenuUseCase.share(context, view);
                        }
                        if (menuItem.getTitle().equals("RENAME")){
                            // Bring up a rename menu.
                            fileMenuUseCase.rename(context, view);
                        }
                        if (menuItem.getTitle().equals("DELETE")){
                            // Delete the item.
                            fileMenuUseCase.delete(context, view);
                        }


                        return true;
                    }
                });

                fileMenu.show();
                return true;
            }
        });

    }

    @Override
    public int getItemCount(){
        return fileList.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textElement;
        ImageView imageElement;

        public ViewHolder(View holderView){
            super(holderView);
            // the textElement and imageElement will determine the appearance of each holder item in the RecyclerView.
            textElement = holderView.findViewById(R.id.file_label_view);
            imageElement = holderView.findViewById(R.id.icon_view);
        }
    }
}
