// Generated by view binder compiler. Do not edit!
package com.example.notes2text.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.notes2text.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FileHolderViewModelBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final TextView fileLabelView;

  @NonNull
  public final ImageView iconView;

  private FileHolderViewModelBinding(@NonNull RelativeLayout rootView,
      @NonNull TextView fileLabelView, @NonNull ImageView iconView) {
    this.rootView = rootView;
    this.fileLabelView = fileLabelView;
    this.iconView = iconView;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FileHolderViewModelBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FileHolderViewModelBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.file_holder_view_model, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FileHolderViewModelBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.file_label_view;
      TextView fileLabelView = ViewBindings.findChildViewById(rootView, id);
      if (fileLabelView == null) {
        break missingId;
      }

      id = R.id.icon_view;
      ImageView iconView = ViewBindings.findChildViewById(rootView, id);
      if (iconView == null) {
        break missingId;
      }

      return new FileHolderViewModelBinding((RelativeLayout) rootView, fileLabelView, iconView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
