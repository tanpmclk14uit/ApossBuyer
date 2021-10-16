package com.example.aposs_buyer.databinding;
import com.example.aposs_buyer.R;
import com.example.aposs_buyer.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentMessageBindingImpl extends FragmentMessageBinding implements com.example.aposs_buyer.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.notification, 5);
        sViewsWithIds.put(R.id.title, 6);
        sViewsWithIds.put(R.id.ln_message, 7);
        sViewsWithIds.put(R.id.messageBox, 8);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    private final android.widget.LinearLayout mboundView2;
    @NonNull
    private final android.widget.EditText mboundView3;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback1;
    // values
    // listeners
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener mboundView3androidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of viewModel.newMessage.getValue()
            //         is viewModel.newMessage.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(mboundView3);
            // localize variables for thread safety
            // viewModel.newMessage != null
            boolean viewModelNewMessageJavaLangObjectNull = false;
            // viewModel.newMessage
            androidx.lifecycle.MutableLiveData<java.lang.String> viewModelNewMessage = null;
            // viewModel
            com.example.aposs_buyer.viewmodel.MessageViewModel viewModel = mViewModel;
            // viewModel.newMessage.getValue()
            java.lang.String viewModelNewMessageGetValue = null;
            // viewModel != null
            boolean viewModelJavaLangObjectNull = false;



            viewModelJavaLangObjectNull = (viewModel) != (null);
            if (viewModelJavaLangObjectNull) {


                viewModelNewMessage = viewModel.getNewMessage();

                viewModelNewMessageJavaLangObjectNull = (viewModelNewMessage) != (null);
                if (viewModelNewMessageJavaLangObjectNull) {




                    viewModelNewMessage.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };

    public FragmentMessageBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }
    private FragmentMessageBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2
            , (android.widget.LinearLayout) bindings[7]
            , (android.widget.LinearLayout) bindings[8]
            , (android.widget.ImageView) bindings[5]
            , (androidx.recyclerview.widget.RecyclerView) bindings[1]
            , (android.widget.ImageView) bindings[4]
            , (android.widget.TextView) bindings[6]
            );
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView2 = (android.widget.LinearLayout) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (android.widget.EditText) bindings[3];
        this.mboundView3.setTag(null);
        this.rcMessage.setTag(null);
        this.sentMessange.setTag(null);
        setRootTag(root);
        // listeners
        mCallback1 = new com.example.aposs_buyer.generated.callback.OnClickListener(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x8L;
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
            setViewModel((com.example.aposs_buyer.viewmodel.MessageViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable com.example.aposs_buyer.viewmodel.MessageViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x4L;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeViewModelLstMessageItem((androidx.lifecycle.LiveData<java.util.List<com.example.aposs_buyer.model.MessageItem>>) object, fieldId);
            case 1 :
                return onChangeViewModelNewMessage((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelLstMessageItem(androidx.lifecycle.LiveData<java.util.List<com.example.aposs_buyer.model.MessageItem>> ViewModelLstMessageItem, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelNewMessage(androidx.lifecycle.MutableLiveData<java.lang.String> ViewModelNewMessage, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
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
        java.lang.String viewModelNewMessageGetValue = null;
        androidx.lifecycle.LiveData<java.util.List<com.example.aposs_buyer.model.MessageItem>> viewModelLstMessageItem = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> viewModelNewMessage = null;
        com.example.aposs_buyer.viewmodel.MessageViewModel viewModel = mViewModel;
        java.util.List<com.example.aposs_buyer.model.MessageItem> viewModelLstMessageItemGetValue = null;

        if ((dirtyFlags & 0xfL) != 0) {


            if ((dirtyFlags & 0xdL) != 0) {

                    if (viewModel != null) {
                        // read viewModel.lstMessageItem
                        viewModelLstMessageItem = viewModel.getLstMessageItem();
                    }
                    updateLiveDataRegistration(0, viewModelLstMessageItem);


                    if (viewModelLstMessageItem != null) {
                        // read viewModel.lstMessageItem.getValue()
                        viewModelLstMessageItemGetValue = viewModelLstMessageItem.getValue();
                    }
            }
            if ((dirtyFlags & 0xeL) != 0) {

                    if (viewModel != null) {
                        // read viewModel.newMessage
                        viewModelNewMessage = viewModel.getNewMessage();
                    }
                    updateLiveDataRegistration(1, viewModelNewMessage);


                    if (viewModelNewMessage != null) {
                        // read viewModel.newMessage.getValue()
                        viewModelNewMessageGetValue = viewModelNewMessage.getValue();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0xdL) != 0) {
            // api target 1

            com.example.aposs_buyer.utils.BindingAdapterKt.bindContactCommand(this.mboundView2, viewModelLstMessageItemGetValue);
            com.example.aposs_buyer.utils.BindingAdapterKt.bindRecyclerView(this.rcMessage, viewModelLstMessageItemGetValue);
        }
        if ((dirtyFlags & 0xeL) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView3, viewModelNewMessageGetValue);
        }
        if ((dirtyFlags & 0x8L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.mboundView3, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, mboundView3androidTextAttrChanged);
            this.sentMessange.setOnClickListener(mCallback1);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        // localize variables for thread safety
        // viewModel
        com.example.aposs_buyer.viewmodel.MessageViewModel viewModel = mViewModel;
        // viewModel != null
        boolean viewModelJavaLangObjectNull = false;



        viewModelJavaLangObjectNull = (viewModel) != (null);
        if (viewModelJavaLangObjectNull) {


            viewModel.addMessage();
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel.lstMessageItem
        flag 1 (0x2L): viewModel.newMessage
        flag 2 (0x3L): viewModel
        flag 3 (0x4L): null
    flag mapping end*/
    //end
}