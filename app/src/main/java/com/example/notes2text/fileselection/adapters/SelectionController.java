package com.example.notes2text.fileselection.adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notes2text.R;
import com.example.notes2text.adapters.DirectoryAccessOutputBoundary;
import com.example.notes2text.adapters.DirectoryAccessPresenter;
import com.example.notes2text.adapters.FileListAdaptor;

import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SelectionController#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectionController extends Fragment {

    private boolean selectionToggle = false;

    //Required collaborators
    private DirectoryAccessOutputBoundary directoryPresenter = new DirectoryAccessPresenter();
    private SelectionPresenter selectionPresenter = new SelectionPresenter();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_FILEPATH = "filepath";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String filePath;
    private String mParam2;

    public SelectionController() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param rootPath Parameter 1.
     * @return A new instance of fragment SelectionController.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectionController newInstance(String rootPath) {
        SelectionController fragment = new SelectionController();
        Bundle args = new Bundle();
        args.putString(ARG_FILEPATH, rootPath);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            filePath = getArguments().getString(ARG_FILEPATH);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.selection_view_fragment, container, false);
    }


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
            fileListView.setAdapter(new SelectionListAdapter(getActivity().getApplicationContext(), filesDirectory));
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

    //helper method to set toolbar menu.
    private void setFragmentToolbar(View view) throws NullPointerException {
        Toolbar selectionToolbar = view.findViewById(R.id.selection_manager_toolbar);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(selectionToolbar);
    }

    private void setToolbarMenu() {
        requireActivity().addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                //Inflate the menu
                menuInflater.inflate(R.menu.selection_toolbar, menu);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.cancel_selection_button){
                    Toast.makeText(getActivity(), "Switch to Directory Access Screen", Toast.LENGTH_SHORT).show();
                } else if (menuItem.getItemId() == R.id.back_button) {
                    directoryPresenter.BackLayerSuccess(getActivity());
                } else if (menuItem.getItemId() == R.id.move_here_button) {
                    selectionPresenter.MoveFileSuccess(getActivity());
                }
                return true;
            }
        }, getViewLifecycleOwner(), Lifecycle.State.RESUMED);
    }
}