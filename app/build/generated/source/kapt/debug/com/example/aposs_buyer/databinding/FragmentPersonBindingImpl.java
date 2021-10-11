package com.example.aposs_buyer.databinding;
import com.example.aposs_buyer.R;
import com.example.aposs_buyer.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentPersonBindingImpl extends FragmentPersonBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.title, 4);
        sViewsWithIds.put(R.id.ln_havingAccount, 5);
        sViewsWithIds.put(R.id.linearLayout, 6);
        sViewsWithIds.put(R.id.linearLayout2, 7);
        sViewsWithIds.put(R.id.btn_Address, 8);
        sViewsWithIds.put(R.id.ln_noAccount, 9);
    }
    // views
    @NonNull
    private final android.widget.FrameLayout mboundView0;
    @NonNull
    private final android.widget.TextView mboundView1;
    @NonNull
    private final android.widget.TextView mboundView2;
    @NonNull
    private final com.google.android.material.imageview.ShapeableImageView mboundView3;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentPersonBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }
    private FragmentPersonBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (androidx.appcompat.widget.AppCompatButton) bindings[8]
            , (android.widget.LinearLayout) bindings[6]
            , (android.widget.LinearLayout) bindings[7]
            , (android.widget.LinearLayout) bindings[5]
            , (android.widget.LinearLayout) bindings[9]
            , (android.widget.TextView) bindings[4]
            );
        this.mboundView0 = (android.widget.FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.TextView) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView2 = (android.widget.TextView) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (com.google.android.material.imageview.ShapeableImageView) bindings[3];
        this.mboundView3.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.viewModel == variableId) {
            setViewModel((com.example.aposs_buyer.viewmodel.PersonViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable com.example.aposs_buyer.viewmodel.PersonViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeViewModelPerson((androidx.lifecycle.LiveData<com.example.aposs_buyer.model.Person>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelPerson(androidx.lifecycle.LiveData<com.example.aposs_buyer.model.Person> ViewModelPerson, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        com.example.aposs_buyer.model.Person viewModelPersonGetValue = null;
        com.example.aposs_buyer.model.Image viewModelPersonImage = null;
        java.lang.String viewModelPersonEmail = null;
        androidx.lifecycle.LiveData<com.example.aposs_buyer.model.Person> viewModelPerson = null;
        android.net.Uri viewModelPersonImageImageUri = null;
        com.example.aposs_buyer.viewmodel.PersonViewModel viewModel = mViewModel;
        java.lang.String viewModelPersonFullName = null;

        if ((dirtyFlags & 0x7L) != 0) {



                if (viewModel != null) {
                    // read viewModel.person
                    viewModelPerson = viewModel.getPerson();
                }
                updateLiveDataRegistration(0, viewModelPerson);


                if (viewModelPerson != null) {
                    // read viewModel.person.getValue()
                    viewModelPersonGetValue = viewModelPerson.getValue();
                }


                if (viewModelPersonGetValue != null) {
                    // read viewModel.person.getValue().image
                    viewModelPersonImage = viewModelPersonGetValue.getImage();
                    // read viewModel.person.getValue().email
                    viewModelPersonEmail = viewModelPersonGetValue.getEmail();
                    // read viewModel.person.getValue().fullName
                    viewModelPersonFullName = viewModelPersonGetValue.getFullName();
                }


                if (viewModelPersonImage != null) {
                    // read viewModel.person.getValue().image.imageUri
                    viewModelPersonImageImageUri = viewModelPersonImage.getImageUri();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x7L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView1, viewModelPersonFullName);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView2, viewModelPersonEmail);
            com.example.aposs_buyer.utils.BindingAdapterKt.bindImage(this.mboundView3, viewModelPersonImageImageUri);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel.person
        flag 1 (0x2L): viewModel
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}