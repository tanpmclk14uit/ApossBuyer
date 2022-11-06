package com.example.aposs_buyer.databinding;
import com.example.aposs_buyer.R;
import com.example.aposs_buyer.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentCartBindingImpl extends FragmentCartBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.appBarLayout, 6);
        sViewsWithIds.put(R.id.toolBox, 7);
        sViewsWithIds.put(R.id.search_bar, 8);
        sViewsWithIds.put(R.id.search, 9);
        sViewsWithIds.put(R.id.title, 10);
        sViewsWithIds.put(R.id.checkout_layout, 11);
    }
    // views
    @NonNull
    private final android.widget.FrameLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentCartBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds));
    }
    private FragmentCartBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 4
            , (com.google.android.material.appbar.AppBarLayout) bindings[6]
            , (android.widget.Button) bindings[5]
            , (android.widget.LinearLayout) bindings[11]
            , (android.widget.TextView) bindings[1]
            , (android.widget.ProgressBar) bindings[2]
            , (androidx.recyclerview.widget.RecyclerView) bindings[3]
            , (com.google.android.material.textfield.TextInputEditText) bindings[9]
            , (com.google.android.material.textfield.TextInputLayout) bindings[8]
            , (android.widget.TextView) bindings[10]
            , (android.widget.RelativeLayout) bindings[7]
            , (android.widget.TextView) bindings[4]
            );
        this.btnGoToCheckOut.setTag(null);
        this.loadingMessage.setTag(null);
        this.loadingProgress.setTag(null);
        this.mboundView0 = (android.widget.FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rcCart.setTag(null);
        this.totalPrice.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x100L;
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
        if (BR.loadingStatus == variableId) {
            setLoadingStatus((com.example.aposs_buyer.utils.LoadingStatus) variable);
        }
        else if (BR.converter == variableId) {
            setConverter((com.example.aposs_buyer.utils.Converter) variable);
        }
        else if (BR.view == variableId) {
            setView((android.view.View) variable);
        }
        else if (BR.viewModel == variableId) {
            setViewModel((com.example.aposs_buyer.viewmodel.CartViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setLoadingStatus(@Nullable com.example.aposs_buyer.utils.LoadingStatus LoadingStatus) {
        this.mLoadingStatus = LoadingStatus;
    }
    public void setConverter(@Nullable com.example.aposs_buyer.utils.Converter Converter) {
        this.mConverter = Converter;
    }
    public void setView(@Nullable android.view.View View) {
        this.mView = View;
    }
    public void setViewModel(@Nullable com.example.aposs_buyer.viewmodel.CartViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x80L;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeViewModelLstCartItem((androidx.lifecycle.LiveData<java.util.ArrayList<com.example.aposs_buyer.model.CartItem>>) object, fieldId);
            case 1 :
                return onChangeViewModelLoadingStatus((androidx.lifecycle.MutableLiveData<com.example.aposs_buyer.utils.LoadingStatus>) object, fieldId);
            case 2 :
                return onChangeViewModelTotalCurrency((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 3 :
                return onChangeViewModelCheckOutEnable((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelLstCartItem(androidx.lifecycle.LiveData<java.util.ArrayList<com.example.aposs_buyer.model.CartItem>> ViewModelLstCartItem, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelLoadingStatus(androidx.lifecycle.MutableLiveData<com.example.aposs_buyer.utils.LoadingStatus> ViewModelLoadingStatus, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelTotalCurrency(androidx.lifecycle.MutableLiveData<java.lang.String> ViewModelTotalCurrency, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelCheckOutEnable(androidx.lifecycle.MutableLiveData<java.lang.Boolean> ViewModelCheckOutEnable, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
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
        androidx.lifecycle.LiveData<java.util.ArrayList<com.example.aposs_buyer.model.CartItem>> viewModelLstCartItem = null;
        java.lang.String viewModelTotalCurrencyGetValue = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxViewModelCheckOutEnableGetValue = false;
        boolean viewModelLoadingStatusLoadingStatusSuccessViewModelLstCartItemSizeInt0BooleanFalse = false;
        boolean viewModelLstCartItemSizeInt0 = false;
        boolean ViewModelLoadingStatusLoadingStatusSuccessViewModelLstCartItemSizeInt0BooleanFalse1 = false;
        androidx.lifecycle.MutableLiveData<com.example.aposs_buyer.utils.LoadingStatus> viewModelLoadingStatus = null;
        boolean viewModelLoadingStatusLoadingStatusSuccess = false;
        boolean viewModelLoadingStatusLoadingStatusLoading = false;
        androidx.lifecycle.MutableLiveData<java.lang.String> viewModelTotalCurrency = null;
        java.lang.Boolean viewModelCheckOutEnableGetValue = null;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> viewModelCheckOutEnable = null;
        com.example.aposs_buyer.utils.LoadingStatus viewModelLoadingStatusGetValue = null;
        java.util.ArrayList<com.example.aposs_buyer.model.CartItem> viewModelLstCartItemGetValue = null;
        int viewModelLoadingStatusLoadingStatusSuccessViewModelLstCartItemSizeInt0BooleanFalseViewVISIBLEViewGONE = 0;
        int ViewModelLoadingStatusLoadingStatusSuccessViewModelLstCartItemSizeInt0BooleanFalseViewVISIBLEViewGONE1 = 0;
        boolean ViewModelLstCartItemSizeInt01 = false;
        com.example.aposs_buyer.viewmodel.CartViewModel viewModel = mViewModel;
        int viewModelLoadingStatusLoadingStatusLoadingViewVISIBLEViewGONE = 0;
        int viewModelLstCartItemSize = 0;

        if ((dirtyFlags & 0x18fL) != 0) {


            if ((dirtyFlags & 0x181L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.lstCartItem
                        viewModelLstCartItem = viewModel.getLstCartItem();
                    }
                    updateLiveDataRegistration(0, viewModelLstCartItem);


                    if (viewModelLstCartItem != null) {
                        // read viewModel.lstCartItem.getValue()
                        viewModelLstCartItemGetValue = viewModelLstCartItem.getValue();
                    }
            }
            if ((dirtyFlags & 0x183L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.loadingStatus
                        viewModelLoadingStatus = viewModel.getLoadingStatus();
                    }
                    updateLiveDataRegistration(1, viewModelLoadingStatus);


                    if (viewModelLoadingStatus != null) {
                        // read viewModel.loadingStatus.getValue()
                        viewModelLoadingStatusGetValue = viewModelLoadingStatus.getValue();
                    }


                    // read viewModel.loadingStatus.getValue() == LoadingStatus.Success
                    viewModelLoadingStatusLoadingStatusSuccess = (viewModelLoadingStatusGetValue) == (com.example.aposs_buyer.utils.LoadingStatus.Success);
                if((dirtyFlags & 0x183L) != 0) {
                    if(viewModelLoadingStatusLoadingStatusSuccess) {
                            dirtyFlags |= 0x400L;
                            dirtyFlags |= 0x1000L;
                    }
                    else {
                            dirtyFlags |= 0x200L;
                            dirtyFlags |= 0x800L;
                    }
                }
                if ((dirtyFlags & 0x182L) != 0) {

                        // read viewModel.loadingStatus.getValue() == LoadingStatus.Loading
                        viewModelLoadingStatusLoadingStatusLoading = (viewModelLoadingStatusGetValue) == (com.example.aposs_buyer.utils.LoadingStatus.Loading);
                    if((dirtyFlags & 0x182L) != 0) {
                        if(viewModelLoadingStatusLoadingStatusLoading) {
                                dirtyFlags |= 0x40000L;
                        }
                        else {
                                dirtyFlags |= 0x20000L;
                        }
                    }


                        // read viewModel.loadingStatus.getValue() == LoadingStatus.Loading ? View.VISIBLE : View.GONE
                        viewModelLoadingStatusLoadingStatusLoadingViewVISIBLEViewGONE = ((viewModelLoadingStatusLoadingStatusLoading) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
                }
            }
            if ((dirtyFlags & 0x184L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.totalCurrency
                        viewModelTotalCurrency = viewModel.getTotalCurrency();
                    }
                    updateLiveDataRegistration(2, viewModelTotalCurrency);


                    if (viewModelTotalCurrency != null) {
                        // read viewModel.totalCurrency.getValue()
                        viewModelTotalCurrencyGetValue = viewModelTotalCurrency.getValue();
                    }
            }
            if ((dirtyFlags & 0x188L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.checkOutEnable
                        viewModelCheckOutEnable = viewModel.getCheckOutEnable();
                    }
                    updateLiveDataRegistration(3, viewModelCheckOutEnable);


                    if (viewModelCheckOutEnable != null) {
                        // read viewModel.checkOutEnable.getValue()
                        viewModelCheckOutEnableGetValue = viewModelCheckOutEnable.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.checkOutEnable.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelCheckOutEnableGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelCheckOutEnableGetValue);
            }
        }
        // batch finished

        if ((dirtyFlags & 0x1400L) != 0) {

                if (viewModel != null) {
                    // read viewModel.lstCartItem
                    viewModelLstCartItem = viewModel.getLstCartItem();
                }
                updateLiveDataRegistration(0, viewModelLstCartItem);


                if (viewModelLstCartItem != null) {
                    // read viewModel.lstCartItem.getValue()
                    viewModelLstCartItemGetValue = viewModelLstCartItem.getValue();
                }


                if (viewModelLstCartItemGetValue != null) {
                    // read viewModel.lstCartItem.getValue().size()
                    viewModelLstCartItemSize = viewModelLstCartItemGetValue.size();
                }

            if ((dirtyFlags & 0x1000L) != 0) {

                    // read viewModel.lstCartItem.getValue().size() > 0
                    viewModelLstCartItemSizeInt0 = (viewModelLstCartItemSize) > (0);
            }
            if ((dirtyFlags & 0x400L) != 0) {

                    // read viewModel.lstCartItem.getValue().size() == 0
                    ViewModelLstCartItemSizeInt01 = (viewModelLstCartItemSize) == (0);
            }
        }

        if ((dirtyFlags & 0x183L) != 0) {

                // read viewModel.loadingStatus.getValue() == LoadingStatus.Success ? viewModel.lstCartItem.getValue().size() == 0 : false
                viewModelLoadingStatusLoadingStatusSuccessViewModelLstCartItemSizeInt0BooleanFalse = ((viewModelLoadingStatusLoadingStatusSuccess) ? (ViewModelLstCartItemSizeInt01) : (false));
                // read viewModel.loadingStatus.getValue() == LoadingStatus.Success ? viewModel.lstCartItem.getValue().size() > 0 : false
                ViewModelLoadingStatusLoadingStatusSuccessViewModelLstCartItemSizeInt0BooleanFalse1 = ((viewModelLoadingStatusLoadingStatusSuccess) ? (viewModelLstCartItemSizeInt0) : (false));
            if((dirtyFlags & 0x183L) != 0) {
                if(viewModelLoadingStatusLoadingStatusSuccessViewModelLstCartItemSizeInt0BooleanFalse) {
                        dirtyFlags |= 0x4000L;
                }
                else {
                        dirtyFlags |= 0x2000L;
                }
            }
            if((dirtyFlags & 0x183L) != 0) {
                if(ViewModelLoadingStatusLoadingStatusSuccessViewModelLstCartItemSizeInt0BooleanFalse1) {
                        dirtyFlags |= 0x10000L;
                }
                else {
                        dirtyFlags |= 0x8000L;
                }
            }


                // read viewModel.loadingStatus.getValue() == LoadingStatus.Success ? viewModel.lstCartItem.getValue().size() == 0 : false ? View.VISIBLE : View.GONE
                viewModelLoadingStatusLoadingStatusSuccessViewModelLstCartItemSizeInt0BooleanFalseViewVISIBLEViewGONE = ((viewModelLoadingStatusLoadingStatusSuccessViewModelLstCartItemSizeInt0BooleanFalse) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
                // read viewModel.loadingStatus.getValue() == LoadingStatus.Success ? viewModel.lstCartItem.getValue().size() > 0 : false ? View.VISIBLE : View.GONE
                ViewModelLoadingStatusLoadingStatusSuccessViewModelLstCartItemSizeInt0BooleanFalseViewVISIBLEViewGONE1 = ((ViewModelLoadingStatusLoadingStatusSuccessViewModelLstCartItemSizeInt0BooleanFalse1) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
        }
        // batch finished
        if ((dirtyFlags & 0x188L) != 0) {
            // api target 1

            this.btnGoToCheckOut.setEnabled(androidxDatabindingViewDataBindingSafeUnboxViewModelCheckOutEnableGetValue);
        }
        if ((dirtyFlags & 0x183L) != 0) {
            // api target 1

            this.loadingMessage.setVisibility(viewModelLoadingStatusLoadingStatusSuccessViewModelLstCartItemSizeInt0BooleanFalseViewVISIBLEViewGONE);
            this.rcCart.setVisibility(ViewModelLoadingStatusLoadingStatusSuccessViewModelLstCartItemSizeInt0BooleanFalseViewVISIBLEViewGONE1);
        }
        if ((dirtyFlags & 0x182L) != 0) {
            // api target 1

            this.loadingProgress.setVisibility(viewModelLoadingStatusLoadingStatusLoadingViewVISIBLEViewGONE);
        }
        if ((dirtyFlags & 0x181L) != 0) {
            // api target 1

            com.example.aposs_buyer.utils.BindingAdapterKt.bindCardRecycleView(this.rcCart, viewModelLstCartItemGetValue);
        }
        if ((dirtyFlags & 0x184L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.totalPrice, viewModelTotalCurrencyGetValue);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel.lstCartItem
        flag 1 (0x2L): viewModel.loadingStatus
        flag 2 (0x3L): viewModel.totalCurrency
        flag 3 (0x4L): viewModel.checkOutEnable
        flag 4 (0x5L): loadingStatus
        flag 5 (0x6L): converter
        flag 6 (0x7L): view
        flag 7 (0x8L): viewModel
        flag 8 (0x9L): null
        flag 9 (0xaL): viewModel.loadingStatus.getValue() == LoadingStatus.Success ? viewModel.lstCartItem.getValue().size() == 0 : false
        flag 10 (0xbL): viewModel.loadingStatus.getValue() == LoadingStatus.Success ? viewModel.lstCartItem.getValue().size() == 0 : false
        flag 11 (0xcL): viewModel.loadingStatus.getValue() == LoadingStatus.Success ? viewModel.lstCartItem.getValue().size() > 0 : false
        flag 12 (0xdL): viewModel.loadingStatus.getValue() == LoadingStatus.Success ? viewModel.lstCartItem.getValue().size() > 0 : false
        flag 13 (0xeL): viewModel.loadingStatus.getValue() == LoadingStatus.Success ? viewModel.lstCartItem.getValue().size() == 0 : false ? View.VISIBLE : View.GONE
        flag 14 (0xfL): viewModel.loadingStatus.getValue() == LoadingStatus.Success ? viewModel.lstCartItem.getValue().size() == 0 : false ? View.VISIBLE : View.GONE
        flag 15 (0x10L): viewModel.loadingStatus.getValue() == LoadingStatus.Success ? viewModel.lstCartItem.getValue().size() > 0 : false ? View.VISIBLE : View.GONE
        flag 16 (0x11L): viewModel.loadingStatus.getValue() == LoadingStatus.Success ? viewModel.lstCartItem.getValue().size() > 0 : false ? View.VISIBLE : View.GONE
        flag 17 (0x12L): viewModel.loadingStatus.getValue() == LoadingStatus.Loading ? View.VISIBLE : View.GONE
        flag 18 (0x13L): viewModel.loadingStatus.getValue() == LoadingStatus.Loading ? View.VISIBLE : View.GONE
    flag mapping end*/
    //end
}