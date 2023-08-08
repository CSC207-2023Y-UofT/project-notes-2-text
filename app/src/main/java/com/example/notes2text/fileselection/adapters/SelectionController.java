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
import com.example.notes2text.adapters.ActivitySwitchController;
import com.example.notes2text.adapters.DirectoryAccessOutputBoundary;
import com.example.notes2text.adapters.DirectoryAccessPresenter;
import com.example.notes2text.adapters.fragments.DirectoryAccessController;
import com.example.notes2text.fileselection.usecases.SelectionInputBoundary;
import com.example.notes2text.fileselection.usecases.SelectionInteractor;

import java.io.File;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SelectionController#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectionController extends Fragment {


    //Required collaborators
    private DirectoryAccessOutputBoundary directoryPresenter = new DirectoryAccessPresenter();
    private SelectionPresenter selectionPresenter = new SelectionPresenter();

    private SelectionInputBoundary selectionUseCase = new SelectionInteractor();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_FILEPATH = "filepath";
    private static final String ARG_FILELIST = "initialfileList";

    // TODO: Rename and change types of parameters
    private String filePath;
    private ArrayList<File> fileList;

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
        args.putSerializable(ARG_FILELIST, new ArrayList<File>());
        fragment.setArguments(args);
        return fragment;
    }

    public static SelectionController newInstance(String rootPath, ArrayList<File> inputfileList) {
        SelectionController fragment = new SelectionController();
        Bundle args = new Bundle();
        args.putString(ARG_FILEPATH, rootPath);
        args.putSerializable(ARG_FILELIST, inputfileList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            filePath = getArguments().getString(ARG_FILEPATH);
            fileList = (ArrayList<File>) getArguments().getSerializable(ARG_FILELIST);
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
            if (fileList != null && fileList.isEmpty()){
                fileListView.setAdapter(new SelectionListAdapter(getActivity().getApplicationContext(), filesDirectory, selectionUseCase, fileList));
            } else if (fileList != null ) {
                fileListView.setAdapter(new SelectionListAdapter(getActivity().getApplicationContext(), filesDirectory, selectionUseCase, fileList));
                selectionPresenter.InheritFilesSuccess(getActivity());
            } else{
                fileListView.setAdapter(new SelectionListAdapter(getActivity().getApplicationContext(), filesDirectory, selectionUseCase));
            }
        }

        //Add top menu and on click listener for top menu buttons.
        // Put functionality of top menu items in separate use case.
        // select listener goes in this class.
//        Toolbar directoryToolbar = view.findViewById(R.id.file_manager_toolbar);
//
//        ((AppCompatActivity) getActivity()).setSupportActionBar(directoryToolbar);

        //call method to
        setFragmentToolbar(view);

        setToolbarMenu(fileListView);


    }

    //helper method to set toolbar menu.
    private void setFragmentToolbar(View view) throws NullPointerException {
        Toolbar selectionToolbar = view.findViewById(R.id.selection_manager_toolbar);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(selectionToolbar);
    }

    //Adjusted to take in RecyclerView input.
    private void setToolbarMenu(RecyclerView recyclerView) {
        requireActivity().addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                //Inflate the menu
                menuInflater.inflate(R.menu.selection_toolbar, menu);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.cancel_selection_button){
                    selectionUseCase.cancel();
                    Toast.makeText(getActivity(), "Switch to Directory Access Screen", Toast.LENGTH_SHORT).show();
                    Fragment fragment = DirectoryAccessController.newInstance(filePath);
                    ((ActivitySwitchController) getActivity()).replaceFragment(fragment);
                } else if (menuItem.getItemId() == R.id.back_button) {
                    //attempts to go back a layer while keeping list of selected files.
                    String higherPath = filePath;
                    File currLayerFile = new File(filePath);
                    File parentLayerFile = currLayerFile.getParentFile();
                    // This appears to work correctly: selecting a file and going back displays the file inheritance message
                    // and going back without selecting any files does not, so fileList is non-empty and has received the file correctly.
                    try {
                        higherPath = parentLayerFile.getAbsolutePath();
                        //gets the adapter that was set to the selectionView.
                        SelectionListAdapter selectionAdapter =  (SelectionListAdapter) recyclerView.getAdapter();
                        //assumes the adapter is a selectionAdapter. Actually goes back, so this is probably fine.
                        ArrayList<File> goBackFileList = selectionAdapter.getSelectedFiles();
                        Fragment fragment = SelectionController.newInstance(higherPath, goBackFileList);
                        ((ActivitySwitchController) getActivity()).replaceFragment(fragment);
                        directoryPresenter.BackLayerSuccess(getActivity());
//                        selectionPresenter.InheritFilesSuccess(getActivity());
                        //If the above line is commented out, and the file inheritence message is
                        // still shown, the files are being passed along correctly.
                    } catch (NullPointerException e){
                        directoryPresenter.BackLayerFailure(getActivity());
                    }
                    directoryPresenter.BackLayerSuccess(getActivity());
                } else if (menuItem.getItemId() == R.id.move_here_button) {
                    selectionPresenter.MoveFileSuccess(getActivity());
                }
                return true;
            }
        }, getViewLifecycleOwner(), Lifecycle.State.RESUMED);
    }
}