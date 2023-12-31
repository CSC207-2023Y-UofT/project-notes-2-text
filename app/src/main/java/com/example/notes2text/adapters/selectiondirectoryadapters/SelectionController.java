package com.example.notes2text.adapters.selectiondirectoryadapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notes2text.R;
import com.example.notes2text.adapters.directoryadapters.ActivitySwitchController;
import com.example.notes2text.adapters.directoryadapters.DirectoryAccessOutputBoundary;
import com.example.notes2text.adapters.directoryadapters.DirectoryAccessPresenter;
import com.example.notes2text.adapters.directoryadapters.fragments.DirectoryAccessController;
import com.example.notes2text.usecases.selectiondirectoryusecases.SelectionInputBoundary;
import com.example.notes2text.usecases.selectiondirectoryusecases.SelectionInteractor;

import java.io.File;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SelectionController#newInstance} factory method to
 * create an instance of this fragment.
 * Manages the Selection screen and sets up the file list recyclerview for selection.
 */
public class SelectionController extends Fragment {


    //Required collaborators
    private final DirectoryAccessOutputBoundary directoryPresenter = new DirectoryAccessPresenter();

    //TODO: Implement SelectionOutputBoundary
    private final SelectionPresenter selectionPresenter = new SelectionPresenter();

    private final SelectionInputBoundary selectionUseCase = new SelectionInteractor();

    // Bundle navigation keys to locate and retrieve parameters.
    // the fragment initialization parameters, e.g. ARG_FILEPATH
    private static final String ARG_FILEPATH = "filepath";
    private static final String ARG_FILE_LIST = "initialFileList";

    // Parameter types
    private String filePath;
    private ArrayList<File> fileList;

    public SelectionController() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * the selection fragment using the file path to open to.
     *
     * @param rootPath Parameter 1.
     * @return A new instance of fragment SelectionController.
     */
    public static SelectionController newInstance(String rootPath) {
        SelectionController fragment = new SelectionController();
        Bundle args = new Bundle();
        args.putString(ARG_FILEPATH, rootPath);
        args.putSerializable(ARG_FILE_LIST, new ArrayList<File>());
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Use this factory method to create a new instance of
     * the selection fragment using the file path to open to.
     *
     * @param rootPath File path to open the selection screen to.
     * @param inputFileList File list to be added to the selected list on load up.
     * @return A new instance of fragment SelectionController.
     */
    public static SelectionController newInstance(String rootPath, ArrayList<File> inputFileList) {
        SelectionController fragment = new SelectionController();
        Bundle args = new Bundle();
        args.putString(ARG_FILEPATH, rootPath);
        args.putSerializable(ARG_FILE_LIST, inputFileList);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Sets up the file list and file path.
     * @param savedInstanceState If the fragment is being re-created from
     * a previous saved state, this is the state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            filePath = getArguments().getString(ARG_FILEPATH);
            try {
                //this is type-safe.
                //Bundles can only use Serializable to store lists of files.
                fileList = (ArrayList<File>) getArguments().getSerializable(ARG_FILE_LIST);
                // This cast is required and it is type-safe according to what has been pushed into serializable.
            } catch (ClassCastException classE){
                Log.e("cast", classE.getMessage());

            }
        }
    }

    /**
     * Creates the selection view from the saved data, and using the specified inflater.
     *
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return The selection view.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.selection_view_fragment, container, false);
    }


    /**
     * Sets up and binds actions to the screen.
     *
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     * @throws NullPointerException may be thrown if this fragment is not provided a context from an activity.
     */
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) throws NullPointerException {
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
        }

        //Assign Linear layout to file list.
        //Assign the custom adaptor to the View elements.
        fileListView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        if (fileList != null && fileList.isEmpty()){
            fileListView.setAdapter(new SelectionListAdapter(requireActivity().getApplicationContext(), filesDirectory, selectionUseCase, fileList));
        } else if (fileList != null ) {
            fileListView.setAdapter(new SelectionListAdapter(requireActivity().getApplicationContext(), filesDirectory, selectionUseCase, fileList));
            selectionPresenter.InheritFilesSuccess(getActivity());
        } else{
            fileListView.setAdapter(new SelectionListAdapter(requireActivity().getApplicationContext(), filesDirectory, selectionUseCase));
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
                    ((ActivitySwitchController) requireActivity()).replaceFragment(fragment);
                } else if (menuItem.getItemId() == R.id.back_button) {
                    //attempts to go back a layer while keeping list of selected files.
                    String higherPath;
                    File currLayerFile = new File(filePath);
                    File parentLayerFile = currLayerFile.getParentFile();
                    // This appears to work correctly: selecting a file and going back displays the file inheritance message
                    // and going back without selecting any files does not, so fileList is non-empty and has received the file correctly.
                    try {
                        assert parentLayerFile != null;
                        higherPath = parentLayerFile.getAbsolutePath();
                        //gets the adapter that was set to the selectionView.
                        SelectionListAdapter selectionAdapter =  (SelectionListAdapter) recyclerView.getAdapter();
                        //assumes the adapter is a selectionAdapter. Actually goes back, so this is probably fine.
                        assert selectionAdapter != null;
                        ArrayList<File> goBackFileList = selectionAdapter.getSelectedFiles();
                        String highestPath = Environment.getExternalStorageDirectory().getPath();
                        if (!filePath.equals(highestPath)) {
                            Fragment fragment = SelectionController.newInstance(higherPath, goBackFileList);
                            ((ActivitySwitchController) requireActivity()).replaceFragment(fragment);
                            directoryPresenter.BackLayerSuccess(getActivity());
                            Log.i("selection", "Went back a layer on the selection controller");
                        } else{
                            Toast.makeText(getActivity(), "Highest layer reached", Toast.LENGTH_SHORT).show();
                        }
//                        selectionPresenter.InheritFilesSuccess(getActivity());
                        //If the above line is commented out, and the file inheritance message is
                        // still shown, the files are being passed along correctly.
                    } catch (NullPointerException e){
                        directoryPresenter.BackLayerFailure(getActivity());
                    }
                    directoryPresenter.BackLayerSuccess(getActivity());
                } else if (menuItem.getItemId() == R.id.move_here_button) {
                    //moves the selected files to the current location, provided it is possible to do so.
                    selectionUseCase.move(filePath);
                    //since the view only updates when entering or leaving a folder,
                    // the new addresses of the moved files are also displayed to the user.
                    SelectionListAdapter selectionAdapter =  (SelectionListAdapter) recyclerView.getAdapter();
                    assert selectionAdapter != null;
                    ArrayList<File> printFileList = selectionAdapter.getSelectedFiles();
                    for (File file: printFileList){
                        //Prints the new location of the file to the view.
                        selectionPresenter.showFileAddress(getActivity(), filePath, file.getName());
                    }
                    if(!printFileList.isEmpty()){
                        selectionPresenter.MoveFileSuccess(getActivity());
                    } else {
                        selectionPresenter.MoveFileNoFiles(getActivity());
                    }
                    //Moves the selected files to this location.
                }
                return true;
            }
        }, getViewLifecycleOwner(), Lifecycle.State.RESUMED);
    }
}