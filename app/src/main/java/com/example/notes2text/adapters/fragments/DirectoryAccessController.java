package com.example.notes2text.adapters.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes2text.R;
import com.example.notes2text.adapters.FileListAdaptor;

import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DirectoryAccessController#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DirectoryAccessController extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
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
        args.putString(ARG_PARAM1, rootPath);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            filePath = getArguments().getString(ARG_PARAM1);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.directory_access_view_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
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
            fileListView.setAdapter(new FileListAdaptor(getActivity().getApplicationContext(), filesDirectory));
        }
    }
}