package com.example.notes2text.adapters.directoryadapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes2text.R;
import com.example.notes2text.entities.directoryentities.FileViewHolder;
import com.example.notes2text.usecases.directoryusecases.FileViewHolderFactory;
import com.example.notes2text.usecases.directoryusecases.ViewHolderAbsFactory;
import com.example.notes2text.usecases.directoryusecases.FileMenuFactory;
import com.example.notes2text.usecases.directoryusecases.FileOpenInteractor;

import java.io.File;

/**
 * A recycler view adapter to load lists of files in directory format.
 */
public class FileListAdaptor extends RecyclerView.Adapter<FileViewHolder> {
    //Adjusted to use FileViewHolder rather than inner class viewholder.

    Context context;
    protected File[] fileList;

    protected ViewHolderAbsFactory viewHolderCreator = new FileViewHolderFactory();


    //TODO: Create interface to segregate this from the file opener.
    FileOpenInteractor fileOpener = new FileOpenInteractor();


    //Required for transaction of Activity.
    private final FragmentManager fragManager;


    /**
     * Creates a recyclerview.adapter for file display.
     *
     * @param context The application context. Required to interact with Android screen elements.
     * @param fileList The list of files to display in the recycler view.
     */
    public FileListAdaptor(Context context, File[] fileList, FragmentManager fragManager){
        super();
        this.context = context;
        this.fileList = fileList;
        this.fragManager = fragManager;
    }

    //A function that creates the ViewHolder required for the recyclerview for file list.

    /**
     *
     * @param source The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param ViewType The view type of the new View.
     *
     * @return FileViewHolder, the file holder.
     */
    @NonNull
    @Override
    public FileViewHolder onCreateViewHolder(@NonNull ViewGroup source, int ViewType){

        View view = LayoutInflater.from(context).inflate(R.layout.file_holder_view_model, source, false);
        return (FileViewHolder) viewHolderCreator.create(view);
    }

    /**
     * Binds file type view decisions, as well as implements on-click and hold behaviours to the
     * view holder object.
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    //Binds elements to the created view holder, and attaches actions to them.
    @Override
    public void onBindViewHolder(@NonNull FileViewHolder holder, int position) {

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
                    try {
                        context.startActivity(intent);
                        Log.i("activity started", "Folder entry activity");
                    } catch (Exception e){
                        Log.e("Intent exception", "Folder entry intent unsuccessful");
                    }

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

                FileMenuController fileMenuController = new FileMenuController(context, view, new FileMenuFactory(), fragManager);
                return fileMenuController.create(chosenFile);
            }
        });

    }

    /**
     *The number of items in the recycler view.
     * @return the number of items in the recycler view.
     */
    @Override
    public int getItemCount(){
        return fileList.length;
    }

}
