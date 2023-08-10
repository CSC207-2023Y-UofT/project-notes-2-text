// Generated by view binder compiler. Do not edit!
package com.example.notes2text.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.notes2text.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityDirectoryBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final RecyclerView fileList;

  @NonNull
  public final TextView noFilesText;

  private ActivityDirectoryBinding(@NonNull ConstraintLayout rootView,
      @NonNull RecyclerView fileList, @NonNull TextView noFilesText) {
    this.rootView = rootView;
    this.fileList = fileList;
    this.noFilesText = noFilesText;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityDirectoryBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityDirectoryBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_directory, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityDirectoryBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.file_list;
      RecyclerView fileList = ViewBindings.findChildViewById(rootView, id);
      if (fileList == null) {
        break missingId;
      }

      id = R.id.noFilesText;
      TextView noFilesText = ViewBindings.findChildViewById(rootView, id);
      if (noFilesText == null) {
        break missingId;
      }

      return new ActivityDirectoryBinding((ConstraintLayout) rootView, fileList, noFilesText);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}