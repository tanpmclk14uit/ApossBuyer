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
        sViewsWithIds.put(R.id.img_notification, 10);
        sViewsWithIds.put(R.id.title, 11);
        sViewsWithIds.put(R.id.lnAboutUs, 12);
        sViewsWithIds.put(R.id.checkout_layout, 13);
    }
    // views
    @NonNull
    private final android.widget.FrameLayout mboundView0;
    @NonNull
    private final android.widget.TextView mboundView4;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentCartBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds));
    }
    private FragmentCartBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 4
            , (com.google.android.material.appbar.AppBarLayout) bindings[6]
            , (android.widget.Button) bindings[5]
            , (android.widget.LinearLayout) bindings[13]
            , (android.widget.ImageView) bindings[10]
            , (android.widget.ImageView) bindings[12]
            , (android.widget.TextView) bindings[1]
            , (android.widget.ProgressBar) bindings[2]
            , (androidx.recyclerview.widget.RecyclerView) bindings[3]
            , (com.google.android.material.textfield.TextInputEditText) bindings[9]
            , (com.google.android.material.textfield.TextInputLayout) bindings[8]
            , (android.widget.TextView) bindings[11]
            , (android.widget.RelativeLayout) bindings[7]
            );
        this.btnGoToCheckOut.setTag(null);
        this.loadingMessage.setTag(null);
        this.loadingProgress.setTag(null);
        this.mboundView0 = (android.widget.FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView4 = (android.widget.TextView) bindings[4];
        this.mboundView4.setTag(null);
        this.rcCart.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x80L;
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
    public void setView(@Nullable android.view.View View) {
        this.mView = View;
    }
    public void setViewModel(@Nullable com.example.aposs_buyer.viewmodel.CartViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x40L;
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
                return onChangeViewModelTotal((androidx.lifecycle.MutableLiveData<java.lang.Integer>) object, fieldId);
            case 3 :
                return onChangeViewModelChoseList((androidx.lifecycle.LiveData<java.util.ArrayList<com.example.aposs_buyer.model.CartItem>>) object, fieldId);
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
    private boolean onChangeViewModelTotal(androidx.lifecycle.MutableLiveData<java.lang.Integer> ViewModelTotal, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelChoseList(androidx.lifecycle.LiveData<java.util.ArrayList<com.example.aposs_buyer.model.CartItem>> ViewModelChoseList, int fieldId) {
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
        int androidxDatabindingViewDataBindingSafeUnboxViewModelTotalGetValue = 0;
        androidx.lifecycle.LiveData<java.util.ArrayList<com.example.aposs_buyer.model.CartItem>> viewModelLstCartItem = null;
        boolean viewModelLoadingStatusLoadingStatusSuccessViewModelLstCartItemSizeInt0BooleanFalse = false;
        int viewModelChoseListSize = 0;
        boolean viewModelLstCartItemSizeInt0 = false;
        boolean ViewModelLoadingStatusLoadingStatusSuccessViewModelLstCartItemSizeInt0BooleanFalse1 = false;
        androidx.lifecycle.MutableLiveData<com.example.aposs_buyer.utils.LoadingStatus> viewModelLoadingStatus = null;
        boolean viewModelChoseListSizeInt0 = false;
        boolean viewModelLoadingStatusLoadingStatusSuccess = false;
        boolean viewModelLoadingStatusLoadingStatusLoading = false;
        java.util.ArrayList<com.example.aposs_buyer.model.CartItem> viewModelChoseListGetValue = null;
        com.example.aposs_buyer.utils.LoadingStatus viewModelLoadingStatusGetValue = null;
        java.util.ArrayList<com.example.aposs_buyer.model.CartItem> viewModelLstCartItemGetValue = null;
        androidx.lifecycle.MutableLiveData<java.lang.Integer> viewModelTotal = null;
        androidx.lifecycle.LiveData<java.util.ArrayList<com.example.aposs_buyer.model.CartItem>> viewModelChoseList = null;
        int viewModelLoadingStatusLoadingStatusSuccessViewModelLstCartItemSizeInt0BooleanFalseViewVISIBLEViewGONE = 0;
        int ViewModelLoadingStatusLoadingStatusSuccessViewModelLstCartItemSizeInt0BooleanFalseViewVISIBLEViewGONE1 = 0;
        boolean ViewModelLstCartItemSizeInt01 = false;
        java.lang.Integer viewModelTotalGetValue = null;
        com.example.aposs_buyer.viewmodel.CartViewModel viewModel = mViewModel;
        int viewModelLoadingStatusLoadingStatusLoadingViewVISIBLEViewGONE = 0;
        int viewModelLstCartItemSize = 0;

        if ((dirtyFlags & 0xcfL) != 0) {


            if ((dirtyFlags & 0xc1L) != 0) {

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
            if ((dirtyFlags & 0xc3L) != 0) {

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
                if((dirtyFlags & 0xc3L) != 0) {
                    if(viewModelLoadingStatusLoadingStatusSuccess) {
                            dirtyFlags |= 0x200L;
                            dirtyFlags |= 0x800L;
                    }
                    else {
                            dirtyFlags |= 0x100L;
                            dirtyFlags |= 0x400L;
                    }
                }
                if ((dirtyFlags & 0xc2L) != 0) {

                        // read viewModel.loadingStatus.getValue() == LoadingStatus.Loading
                        viewModelLoadingStatusLoadingStatusLoading = (viewModelLoadingStatusGetValue) == (com.example.aposs_buyer.utils.LoadingStatus.Loading);
                    if((dirtyFlags & 0xc2L) != 0) {
                        if(viewModelLoadingStatusLoadingStatusLoading) {
                                dirtyFlags |= 0x20000L;
                        }
                        else {
                                dirtyFlags |= 0x10000L;
                        }
                    }


                        // read viewModel.loadingStatus.getValue() == LoadingStatus.Loading ? View.VISIBLE : View.GONE
                        viewModelLoadingStatusLoadingStatusLoadingViewVISIBLEViewGONE = ((viewModelLoadingStatusLoadingStatusLoading) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
                }
            }
            if ((dirtyFlags & 0xc4L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.total
                        viewModelTotal = viewModel.getTotal();
                    }
                    updateLiveDataRegistration(2, viewModelTotal);


                    if (viewModelTotal != null) {
                        // read viewModel.total.getValue()
                        viewModelTotalGetValue = viewModelTotal.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.total.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelTotalGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelTotalGetValue);
            }
            if ((dirtyFlags & 0xc8L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.choseList
                        viewModelChoseList = viewModel.getChoseList();
                    }
                    updateLiveDataRegistration(3, viewModelChoseList);


                    if (viewModelChoseList != null) {
                        // read viewModel.choseList.getValue()
                        viewModelChoseListGetValue = viewModelChoseList.getValue();
                    }


                    if (viewModelChoseListGetValue != null) {
                        // read viewModel.choseList.getValue().size()
                        viewModelChoseListSize = viewModelChoseListGetValue.size();
                    }


                    // read viewModel.choseList.getValue().size() > 0
                    viewModelChoseListSizeInt0 = (viewModelChoseListSize) > (0);
            }
        }
        // batch finished

        if ((dirtyFlags & 0xa00L) != 0) {

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

            if ((dirtyFlags & 0x800L) != 0) {

                    // read viewModel.lstCartItem.getValue().size() > 0
                    viewModelLstCartItemSizeInt0 = (viewModelLstCartItemSize) > (0);
            }
            if ((dirtyFlags & 0x200L) != 0) {

                    // read viewModel.lstCartItem.getValue().size() == 0
                    ViewModelLstCartItemSizeInt01 = (viewModelLstCartItemSize) == (0);
            }
        }

        if ((dirtyFlags & 0xc3L) != 0) {

                // read viewModel.loadingStatus.getValue() == LoadingStatus.Success ? viewModel.lstCartItem.getValue().size() == 0 : false
                viewModelLoadingStatusLoadingStatusSuccessViewModelLstCartItemSizeInt0BooleanFalse = ((viewModelLoadingStatusLoadingStatusSuccess) ? (ViewModelLstCartItemSizeInt01) : (false));
                // read viewModel.loadingStatus.getValue() == LoadingStatus.Success ? viewModel.lstCartItem.getValue().size() > 0 : false
                ViewModelLoadingStatusLoadingStatusSuccessViewModelLstCartItemSizeInt0BooleanFalse1 = ((viewModelLoadingStatusLoadingStatusSuccess) ? (viewModelLstCartItemSizeInt0) : (false));
            if((dirtyFlags & 0xc3L) != 0) {
                if(viewModelLoadingStatusLoadingStatusSuccessViewModelLstCartItemSizeInt0BooleanFalse) {
                        dirtyFlags |= 0x2000L;
                }
                else {
                        dirtyFlags |= 0x1000L;
                }
            }
            if((dirtyFlags & 0xc3L) != 0) {
                if(ViewModelLoadingStatusLoadingStatusSuccessViewModelLstCartItemSizeInt0BooleanFalse1) {
                        dirtyFlags |= 0x8000L;
                }
                else {
                        dirtyFlags |= 0x4000L;
                }
            }


                // read viewModel.loadingStatus.getValue() == LoadingStatus.Success ? viewModel.lstCartItem.getValue().size() == 0 : false ? View.VISIBLE : View.GONE
                viewModelLoadingStatusLoadingStatusSuccessViewModelLstCartItemSizeInt0BooleanFalseViewVISIBLEViewGONE = ((viewModelLoadingStatusLoadingStatusSuccessViewModelLstCartItemSizeInt0BooleanFalse) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
                // read viewModel.loadingStatus.getValue() == LoadingStatus.Success ? viewModel.lstCartItem.getValue().size() > 0 : false ? View.VISIBLE : View.GONE
                ViewModelLoadingStatusLoadingStatusSuccessViewModelLstCartItemSizeInt0BooleanFalseViewVISIBLEViewGONE1 = ((ViewModelLoadingStatusLoadingStatusSuccessViewModelLstCartItemSizeInt0BooleanFalse1) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
        }
        // batch finished
        if ((dirtyFlags & 0xc8L) != 0) {
            // api target 1

            this.btnGoToCheckOut.setEnabled(viewModelChoseListSizeInt0);
        }
        if ((dirtyFlags & 0xc3L) != 0) {
            // api target 1

            this.loadingMessage.setVisibility(viewModelLoadingStatusLoadingStatusSuccessViewModelLstCartItemSizeInt0BooleanFalseViewVISIBLEViewGONE);
            this.rcCart.setVisibility(ViewModelLoadingStatusLoadingStatusSuccessViewModelLstCartItemSizeInt0BooleanFalseViewVISIBLEViewGONE1);
        }
        if ((dirtyFlags & 0xc2L) != 0) {
            // api target 1

            this.loadingProgress.setVisibility(viewModelLoadingStatusLoadingStatusLoadingViewVISIBLEViewGONE);
        }
        if ((dirtyFlags & 0xc4L) != 0) {
            // api target 1

            com.example.aposs_buyer.utils.BindingAdapterKt.bindCurrencyTextView(this.mboundView4, androidxDatabindingViewDataBindingSafeUnboxViewModelTotalGetValue);
        }
        if ((dirtyFlags & 0xc1L) != 0) {
            // api target 1

            com.example.aposs_buyer.utils.BindingAdapterKt.bindCardRecycleView(this.rcCart, viewModelLstCartItemGetValue);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel.lstCartItem
        flag 1 (0x2L): viewModel.loadingStatus
        flag 2 (0x3L): viewModel.total
        flag 3 (0x4L): viewModel.choseList
        flag 4 (0x5L): loadingStatus
        flag 5 (0x6L): view
        flag 6 (0x7L): viewModel
        flag 7 (0x8L): null
        flag 8 (0x9L): viewModel.loadingStatus.getValue() == LoadingStatus.Success ? viewModel.lstCartItem.getValue().size() == 0 : false
        flag 9 (0xaL): viewModel.loadingStatus.getValue() == LoadingStatus.Success ? viewModel.lstCartItem.getValue().size() == 0 : false
        flag 10 (0xbL): viewModel.loadingStatus.getValue() == LoadingStatus.Success ? viewModel.lstCartItem.getValue().size() > 0 : false
        flag 11 (0xcL): viewModel.loadingStatus.getValue() == LoadingStatus.Success ? viewModel.lstCartItem.getValue().size() > 0 : false
        flag 12 (0xdL): viewModel.loadingStatus.getValue() == LoadingStatus.Success ? viewModel.lstCartItem.getValue().size() == 0 : false ? View.VISIBLE : View.GONE
        flag 13 (0xeL): viewModel.loadingStatus.getValue() == LoadingStatus.Success ? viewModel.lstCartItem.getValue().size() == 0 : false ? View.VISIBLE : View.GONE
        flag 14 (0xfL): viewModel.loadingStatus.getValue() == LoadingStatus.Success ? viewModel.lstCartItem.getValue().size() > 0 : false ? View.VISIBLE : View.GONE
        flag 15 (0x10L): viewModel.loadingStatus.getValue() == LoadingStatus.Success ? viewModel.lstCartItem.getValue().size() > 0 : false ? View.VISIBLE : View.GONE
        flag 16 (0x11L): viewModel.loadingStatus.getValue() == LoadingStatus.Loading ? View.VISIBLE : View.GONE
        flag 17 (0x12L): viewModel.loadingStatus.getValue() == LoadingStatus.Loading ? View.VISIBLE : View.GONE
    flag mapping end*/
    //end
}