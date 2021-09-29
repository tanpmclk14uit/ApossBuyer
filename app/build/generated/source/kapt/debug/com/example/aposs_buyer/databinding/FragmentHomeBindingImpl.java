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
        sViewsWithIds.put(R.id.categories, 7);
        sViewsWithIds.put(R.id.ranking, 8);
        sViewsWithIds.put(R.id.cardView2, 9);
        sViewsWithIds.put(R.id.cardView, 10);
        sViewsWithIds.put(R.id.rankingIndicator, 11);
        sViewsWithIds.put(R.id.title, 12);
    }
    // views
    @NonNull
    private final androidx.coordinatorlayout.widget.CoordinatorLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentHomeBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds));
    }
    private FragmentHomeBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 4
            , (androidx.cardview.widget.CardView) bindings[10]
            , (androidx.cardview.widget.CardView) bindings[9]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[3]
            , (androidx.viewpager2.widget.ViewPager2) bindings[1]
            , (me.relex.circleindicator.CircleIndicator3) bindings[2]
            , (android.widget.TextView) bindings[8]
            , (me.relex.circleindicator.CircleIndicator3) bindings[11]
            , (android.widget.RatingBar) bindings[6]
            , (android.widget.TextView) bindings[12]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[4]
            );
        this.categoryName.setTag(null);
        this.imageViewPager.setTag(null);
        this.indicator.setTag(null);
        this.mboundView0 = (androidx.coordinatorlayout.widget.CoordinatorLayout) bindings[0];
        this.mboundView0.setTag(null);
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
                mDirtyFlags = 0x20L;
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
            mDirtyFlags |= 0x10L;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeViewModelCategories((androidx.lifecycle.LiveData<java.util.ArrayList<com.example.aposs_buyer.model.Category>>) object, fieldId);
            case 1 :
                return onChangeViewModelDisplayCategory((androidx.lifecycle.LiveData<com.example.aposs_buyer.model.Category>) object, fieldId);
            case 2 :
                return onChangeViewModelDisplayCategoryProducts((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 3 :
                return onChangeViewModelDisplayCategoryPurchase((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelCategories(androidx.lifecycle.LiveData<java.util.ArrayList<com.example.aposs_buyer.model.Category>> ViewModelCategories, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelDisplayCategory(androidx.lifecycle.LiveData<com.example.aposs_buyer.model.Category> ViewModelDisplayCategory, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelDisplayCategoryProducts(androidx.lifecycle.MutableLiveData<java.lang.String> ViewModelDisplayCategoryProducts, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelDisplayCategoryPurchase(androidx.lifecycle.MutableLiveData<java.lang.String> ViewModelDisplayCategoryPurchase, int fieldId) {
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
        androidx.lifecycle.LiveData<java.util.ArrayList<com.example.aposs_buyer.model.Category>> viewModelCategories = null;
        androidx.lifecycle.LiveData<com.example.aposs_buyer.model.Category> viewModelDisplayCategory = null;
        com.example.aposs_buyer.model.Category viewModelDisplayCategoryGetValue = null;
        java.util.ArrayList<com.example.aposs_buyer.model.Category> viewModelCategoriesGetValue = null;
        java.lang.String viewModelDisplayCategoryPurchaseGetValue = null;
        java.lang.String viewModelDisplayCategoryProductsGetValue = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> viewModelDisplayCategoryProducts = null;
        int viewModelCategoriesSize = 0;
        float viewModelDisplayCategoryRating = 0f;
        java.lang.String viewModelDisplayCategoryName = null;
        com.example.aposs_buyer.viewmodel.HomeViewModel viewModel = mViewModel;
        androidx.lifecycle.MutableLiveData<java.lang.String> viewModelDisplayCategoryPurchase = null;

        if ((dirtyFlags & 0x3fL) != 0) {


            if ((dirtyFlags & 0x31L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.categories
                        viewModelCategories = viewModel.getCategories();
                    }
                    updateLiveDataRegistration(0, viewModelCategories);


                    if (viewModelCategories != null) {
                        // read viewModel.categories.getValue()
                        viewModelCategoriesGetValue = viewModelCategories.getValue();
                    }


                    if (viewModelCategoriesGetValue != null) {
                        // read viewModel.categories.getValue().size()
                        viewModelCategoriesSize = viewModelCategoriesGetValue.size();
                    }
            }
            if ((dirtyFlags & 0x32L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.displayCategory
                        viewModelDisplayCategory = viewModel.getDisplayCategory();
                    }
                    updateLiveDataRegistration(1, viewModelDisplayCategory);


                    if (viewModelDisplayCategory != null) {
                        // read viewModel.displayCategory.getValue()
                        viewModelDisplayCategoryGetValue = viewModelDisplayCategory.getValue();
                    }


                    if (viewModelDisplayCategoryGetValue != null) {
                        // read viewModel.displayCategory.getValue().rating
                        viewModelDisplayCategoryRating = viewModelDisplayCategoryGetValue.getRating();
                        // read viewModel.displayCategory.getValue().name
                        viewModelDisplayCategoryName = viewModelDisplayCategoryGetValue.getName();
                    }
            }
            if ((dirtyFlags & 0x34L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.displayCategoryProducts
                        viewModelDisplayCategoryProducts = viewModel.getDisplayCategoryProducts();
                    }
                    updateLiveDataRegistration(2, viewModelDisplayCategoryProducts);


                    if (viewModelDisplayCategoryProducts != null) {
                        // read viewModel.displayCategoryProducts.getValue()
                        viewModelDisplayCategoryProductsGetValue = viewModelDisplayCategoryProducts.getValue();
                    }
            }
            if ((dirtyFlags & 0x38L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.displayCategoryPurchase
                        viewModelDisplayCategoryPurchase = viewModel.getDisplayCategoryPurchase();
                    }
                    updateLiveDataRegistration(3, viewModelDisplayCategoryPurchase);


                    if (viewModelDisplayCategoryPurchase != null) {
                        // read viewModel.displayCategoryPurchase.getValue()
                        viewModelDisplayCategoryPurchaseGetValue = viewModelDisplayCategoryPurchase.getValue();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x32L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.categoryName, viewModelDisplayCategoryName);
            androidx.databinding.adapters.RatingBarBindingAdapter.setRating(this.rating, viewModelDisplayCategoryRating);
        }
        if ((dirtyFlags & 0x31L) != 0) {
            // api target 1

            com.example.aposs_buyer.utils.BindingAdapterKt.bindCategoriesViewPager(this.imageViewPager, viewModelCategoriesGetValue);
            com.example.aposs_buyer.utils.BindingAdapterKt.bindIndicatorSize(this.indicator, viewModelCategoriesSize);
        }
        if ((dirtyFlags & 0x34L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.totalProduct, viewModelDisplayCategoryProductsGetValue);
        }
        if ((dirtyFlags & 0x38L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.totalPurchase, viewModelDisplayCategoryPurchaseGetValue);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel.categories
        flag 1 (0x2L): viewModel.displayCategory
        flag 2 (0x3L): viewModel.displayCategoryProducts
        flag 3 (0x4L): viewModel.displayCategoryPurchase
        flag 4 (0x5L): viewModel
        flag 5 (0x6L): null
    flag mapping end*/
    //end
}