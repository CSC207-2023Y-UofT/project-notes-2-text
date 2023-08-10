// Generated by view binder compiler. Do not edit!
package com.example.notes2text.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.notes2text.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityPopUpEmailViewBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView CurrentEmail;

  @NonNull
  public final Button Submit;

  @NonNull
  public final EditText editTextEmail;

  @NonNull
  public final ConstraintLayout linearLayout;

  @NonNull
  public final ConstraintLayout settings;

  @NonNull
  public final TextView textView2;

  @NonNull
  public final TextView textView3;

  @NonNull
  public final TextView textView5;

  @NonNull
  public final TextView textView6;

  private ActivityPopUpEmailViewBinding(@NonNull LinearLayout rootView,
      @NonNull TextView CurrentEmail, @NonNull Button Submit, @NonNull EditText editTextEmail,
      @NonNull ConstraintLayout linearLayout, @NonNull ConstraintLayout settings,
      @NonNull TextView textView2, @NonNull TextView textView3, @NonNull TextView textView5,
      @NonNull TextView textView6) {
    this.rootView = rootView;
    this.CurrentEmail = CurrentEmail;
    this.Submit = Submit;
    this.editTextEmail = editTextEmail;
    this.linearLayout = linearLayout;
    this.settings = settings;
    this.textView2 = textView2;
    this.textView3 = textView3;
    this.textView5 = textView5;
    this.textView6 = textView6;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityPopUpEmailViewBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityPopUpEmailViewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_pop_up_email_view, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityPopUpEmailViewBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.CurrentEmail;
      TextView CurrentEmail = ViewBindings.findChildViewById(rootView, id);
      if (CurrentEmail == null) {
        break missingId;
      }

      id = R.id.Submit;
      Button Submit = ViewBindings.findChildViewById(rootView, id);
      if (Submit == null) {
        break missingId;
      }

      id = R.id.editTextEmail;
      EditText editTextEmail = ViewBindings.findChildViewById(rootView, id);
      if (editTextEmail == null) {
        break missingId;
      }

      id = R.id.linearLayout;
      ConstraintLayout linearLayout = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout == null) {
        break missingId;
      }

      id = R.id.settings;
      ConstraintLayout settings = ViewBindings.findChildViewById(rootView, id);
      if (settings == null) {
        break missingId;
      }

      id = R.id.textView2;
      TextView textView2 = ViewBindings.findChildViewById(rootView, id);
      if (textView2 == null) {
        break missingId;
      }

      id = R.id.textView3;
      TextView textView3 = ViewBindings.findChildViewById(rootView, id);
      if (textView3 == null) {
        break missingId;
      }

      id = R.id.textView5;
      TextView textView5 = ViewBindings.findChildViewById(rootView, id);
      if (textView5 == null) {
        break missingId;
      }

      id = R.id.textView6;
      TextView textView6 = ViewBindings.findChildViewById(rootView, id);
      if (textView6 == null) {
        break missingId;
      }

      return new ActivityPopUpEmailViewBinding((LinearLayout) rootView, CurrentEmail, Submit,
          editTextEmail, linearLayout, settings, textView2, textView3, textView5, textView6);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
