package com.example.notes2text.adapters.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes2text.R;
import com.example.notes2text.adapters.ActivitySwitchController;
import com.example.notes2text.adapters.CreateFolderController;
import com.example.notes2text.adapters.DirectoryAccessOutputBoundary;
import com.example.notes2text.adapters.DirectoryAccessPresenter;
import com.example.notes2text.adapters.FileListAdaptor;
import com.example.notes2text.fileselection.adapters.SelectionController;

import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DirectoryAccessController#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DirectoryAccessController extends Fragment {

    private boolean selectionToggle = false;

    //Required collaborators
    private DirectoryAccessOutputBoundary directoryPresenter = new DirectoryAccessPresenter();


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_FILE_PATH = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String filePath;
    private String mParam2;

    public DirectoryAccessController() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param rootPath Parameter 1.
     * @return A new instance of fragment DirectoryAccessController.
     */
    // TODO: Rename and change types and number of parameters
    public static DirectoryAccessController newInstance(String rootPath) {
        DirectoryAccessController fragment = new DirectoryAccessController();
        Bundle args = new Bundle();
        args.putString(ARG_FILE_PATH, rootPath);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            filePath = getArguments().getString(ARG_FILE_PATH);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.directory_access_view_fragment, container, false);
    }

    // NullPointerException thrown since it is a runtime error.
    //Called automatically after DirectoryAccessController.onCreateView. If files present in filePath,
    // sets the recyclerview to display the list of lists. Otherwise, shows the "no files" condition.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) throws NullPointerException {
        super.onViewCreated(view, savedInstanceState);

        // Retrieve the file path from the intent.
//        String path = this.getArguments().getString("path");
        //Use the filepath local variable as source.
        // Get the address of the folder currently to be shown.
        File root = new File(filePath);
        // Retrieve the list of files and folders from the path address.
        File[] filesDirectory = root.listFiles();

        TextView noFiles = view.findViewById(R.id.noFilesText);
        RecyclerView fileListView = view.findViewById(R.id.file_list);
        //Depending on the state of the list of files, display either No Files text or the list
        // of files as view holder objects.
        if (filesDirectory == null || filesDirectory.length == 0){
            noFiles.setVisibility(View.VISIBLE);
        } else{
            // set noFiles to Invisible
            noFiles.setVisibility(View.INVISIBLE);
            //Assign Linear layout to file list.
            fileListView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
            //Assign the custom adaptor to the View elements.
            //Fragment Manager needed for transaction of activity for renaming file in FileListAdaptor.
            FragmentManager fragManager = requireActivity().getSupportFragmentManager();
            fileListView.setAdapter(new FileListAdaptor(getActivity().getApplicationContext(), filesDirectory, fragManager));
        }

        //Add top menu and on click listener for top menu buttons.
        // Put functionality of top menu items in separate use case.
        // select listener goes in this class.
//        Toolbar directoryToolbar = view.findViewById(R.id.file_manager_toolbar);
//
//        ((AppCompatActivity) getActivity()).setSupportActionBar(directoryToolbar);

        //call method to
        setFragmentToolbar(view);

        setToolbarMenu();


        }

    private void setToolbarMenu() {
        requireActivity().addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                //Inflate the menu
                menuInflater.inflate(R.menu.directory_toolbar, menu);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.select_button){
                    Toast.makeText(getActivity(), "Switch to Selection Screen", Toast.LENGTH_SHORT).show();
                    Fragment fragment = SelectionController.newInstance(filePath);
                    ((ActivitySwitchController) getActivity()).replaceFragment(fragment);
                } else if (menuItem.getItemId() == R.id.back_button) {
                    directoryPresenter.BackLayerSuccess(getActivity());
                } else if (menuItem.getItemId() == R.id.create_folder_button) {

                    //create a new dialogue using openDialog when button is clicked.
                    menuItem.setOnMenuItemClickListener(menuItem1 -> {
                        openDialog();
                        return false;
                    });
                }
                return true;
            }
        }, getViewLifecycleOwner(), Lifecycle.State.RESUMED);
    }

    /* Helper method that create a new CreateFolderController, which inherits
    AppCompatDialogFragment and can create a new dialogue. */
    public void openDialog() {
        CreateFolderController createFolder = new CreateFolderController(getActivity());
        createFolder.show(requireActivity().getSupportFragmentManager(), "Create Folder Dialogue");
    }

    //helper method that sets the tool bar from view.
    private void setFragmentToolbar(View view) throws NullPointerException {
        Toolbar directoryToolbar = view.findViewById(R.id.file_manager_toolbar);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(directoryToolbar);
    }
}