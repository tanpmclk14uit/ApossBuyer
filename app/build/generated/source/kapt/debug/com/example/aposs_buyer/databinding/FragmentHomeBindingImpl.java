package com.example.aposs_buyer.databinding;
import com.example.aposs_buyer.R;
import com.example.aposs_buyer.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentHomeBindingImpl extends FragmentHomeBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.categories, 6);
        sViewsWithIds.put(R.id.indicator, 7);
        sViewsWithIds.put(R.id.title, 8);
    }
    // views
    @NonNull
    private final androidx.coordinatorlayout.widget.CoordinatorLayout mboundView0;
    @NonNull
    private final android.widget.ImageView mboundView1;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentHomeBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }
    private FragmentHomeBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[2]
            , (me.relex.circleindicator.CircleIndicator2) bindings[7]
            , (android.widget.RatingBar) bindings[5]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[3]
            );
        this.categoryName.setTag(null);
        this.mboundView0 = (androidx.coordinatorlayout.widget.CoordinatorLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.ImageView) bindings[1];
        this.mboundView1.setTag(null);
        this.rating.setTag(null);
        this.totalProduct.setTag(null);
        this.totalPurchase.setTag(null);
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
            setViewModel((com.example.aposs_buyer.viewmodel.HomeViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable com.example.aposs_buyer.viewmodel.HomeViewModel ViewModel) {
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
                return onChangeViewModelCategory((androidx.lifecycle.MutableLiveData<com.example.aposs_buyer.model.Category>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelCategory(androidx.lifecycle.MutableLiveData<com.example.aposs_buyer.model.Category> ViewModelCategory, int fieldId) {
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
        float viewModelCategoryRating = 0f;
        java.lang.String viewModelCategoryName = null;
        android.net.Uri viewModelCategoryMainImageImageUri = null;
        androidx.lifecycle.MutableLiveData<com.example.aposs_buyer.model.Category> viewModelCategory = null;
        com.example.aposs_buyer.model.Category viewModelCategoryGetValue = null;
        com.example.aposs_buyer.viewmodel.HomeViewModel viewModel = mViewModel;
        java.lang.String viewModelDisplayCategoryPurchase = null;
        com.example.aposs_buyer.model.ImageCategory viewModelCategoryMainImage = null;
        java.lang.String viewModelDisplayCategoryProducts = null;

        if ((dirtyFlags & 0x7L) != 0) {



                if (viewModel != null) {
                    // read viewModel.category
                    viewModelCategory = viewModel.getCategory();
                }
                updateLiveDataRegistration(0, viewModelCategory);


                if (viewModelCategory != null) {
                    // read viewModel.category.getValue()
                    viewModelCategoryGetValue = viewModelCategory.getValue();
                }


                if (viewModelCategoryGetValue != null) {
                    // read viewModel.category.getValue().rating
                    viewModelCategoryRating = viewModelCategoryGetValue.getRating();
                    // read viewModel.category.getValue().name
                    viewModelCategoryName = viewModelCategoryGetValue.getName();
                    // read viewModel.category.getValue().mainImage
                    viewModelCategoryMainImage = viewModelCategoryGetValue.getMainImage();
                }


                if (viewModelCategoryMainImage != null) {
                    // read viewModel.category.getValue().mainImage.imageUri
                    viewModelCategoryMainImageImageUri = viewModelCategoryMainImage.getImageUri();
                }
            if ((dirtyFlags & 0x6L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.displayCategoryPurchase
                        viewModelDisplayCategoryPurchase = viewModel.getDisplayCategoryPurchase();
                        // read viewModel.displayCategoryProducts
                        viewModelDisplayCategoryProducts = viewModel.getDisplayCategoryProducts();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x7L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.categoryName, viewModelCategoryName);
            com.example.aposs_buyer.utils.BindingAdapterKt.bindImage(this.mboundView1, viewModelCategoryMainImageImageUri);
            androidx.databinding.adapters.RatingBarBindingAdapter.setRating(this.rating, viewModelCategoryRating);
        }
        if ((dirtyFlags & 0x6L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.totalProduct, viewModelDisplayCategoryProducts);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.totalPurchase, viewModelDisplayCategoryPurchase);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel.category
        flag 1 (0x2L): viewModel
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}