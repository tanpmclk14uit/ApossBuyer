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
        sViewsWithIds.put(R.id.categories, 11);
        sViewsWithIds.put(R.id.ranking, 12);
        sViewsWithIds.put(R.id.cardView2, 13);
        sViewsWithIds.put(R.id.cardView, 14);
        sViewsWithIds.put(R.id.more, 15);
        sViewsWithIds.put(R.id.title, 16);
    }
    // views
    @NonNull
    private final androidx.coordinatorlayout.widget.CoordinatorLayout mboundView0;
    @NonNull
    private final android.widget.TextView mboundView8;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentHomeBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 17, sIncludes, sViewsWithIds));
    }
    private FragmentHomeBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 7
            , (androidx.cardview.widget.CardView) bindings[14]
            , (androidx.cardview.widget.CardView) bindings[13]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[3]
            , (androidx.viewpager2.widget.ViewPager2) bindings[1]
            , (me.relex.circleindicator.CircleIndicator3) bindings[2]
            , (android.widget.TextView) bindings[15]
            , (androidx.recyclerview.widget.RecyclerView) bindings[10]
            , (android.widget.TextView) bindings[12]
            , (me.relex.circleindicator.CircleIndicator3) bindings[9]
            , (androidx.viewpager2.widget.ViewPager2) bindings[7]
            , (android.widget.RatingBar) bindings[6]
            , (android.widget.TextView) bindings[16]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[4]
            );
        this.categoryName.setTag(null);
        this.imageViewPager.setTag(null);
        this.indicator.setTag(null);
        this.mboundView0 = (androidx.coordinatorlayout.widget.CoordinatorLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView8 = (android.widget.TextView) bindings[8];
        this.mboundView8.setTag(null);
        this.products.setTag(null);
        this.rankingIndicator.setTag(null);
        this.rankingViewPager.setTag(null);
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
            mDirtyFlags |= 0x80L;
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
                return onChangeViewModelRankingProducts((androidx.lifecycle.LiveData<java.util.ArrayList<com.example.aposs_buyer.model.RankingProduct>>) object, fieldId);
            case 4 :
                return onChangeViewModelProducts((androidx.lifecycle.LiveData<java.util.ArrayList<com.example.aposs_buyer.model.HomeProduct>>) object, fieldId);
            case 5 :
                return onChangeViewModelDisplayCategoryPurchase((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 6 :
                return onChangeViewModelCurrentProductKind((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
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
    private boolean onChangeViewModelRankingProducts(androidx.lifecycle.LiveData<java.util.ArrayList<com.example.aposs_buyer.model.RankingProduct>> ViewModelRankingProducts, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelProducts(androidx.lifecycle.LiveData<java.util.ArrayList<com.example.aposs_buyer.model.HomeProduct>> ViewModelProducts, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x10L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelDisplayCategoryPurchase(androidx.lifecycle.MutableLiveData<java.lang.String> ViewModelDisplayCategoryPurchase, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x20L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelCurrentProductKind(androidx.lifecycle.LiveData<java.lang.String> ViewModelCurrentProductKind, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x40L;
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
        java.lang.String viewModelCurrentProductKindGetValue = null;
        com.example.aposs_buyer.model.Category viewModelDisplayCategoryGetValue = null;
        java.util.ArrayList<com.example.aposs_buyer.model.Category> viewModelCategoriesGetValue = null;
        int viewModelRankingProductsSize = 0;
        java.util.ArrayList<com.example.aposs_buyer.model.HomeProduct> viewModelProductsGetValue = null;
        java.lang.String viewModelDisplayCategoryPurchaseGetValue = null;
        java.lang.String viewModelDisplayCategoryProductsGetValue = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> viewModelDisplayCategoryProducts = null;
        int viewModelCategoriesSize = 0;
        androidx.lifecycle.LiveData<java.util.ArrayList<com.example.aposs_buyer.model.RankingProduct>> viewModelRankingProducts = null;
        float viewModelDisplayCategoryRating = 0f;
        java.lang.String viewModelDisplayCategoryName = null;
        java.util.ArrayList<com.example.aposs_buyer.model.RankingProduct> viewModelRankingProductsGetValue = null;
        androidx.lifecycle.LiveData<java.util.ArrayList<com.example.aposs_buyer.model.HomeProduct>> viewModelProducts = null;
        com.example.aposs_buyer.viewmodel.HomeViewModel viewModel = mViewModel;
        androidx.lifecycle.MutableLiveData<java.lang.String> viewModelDisplayCategoryPurchase = null;
        androidx.lifecycle.LiveData<java.lang.String> viewModelCurrentProductKind = null;

        if ((dirtyFlags & 0x1ffL) != 0) {


            if ((dirtyFlags & 0x181L) != 0) {

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
            if ((dirtyFlags & 0x182L) != 0) {

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
            if ((dirtyFlags & 0x184L) != 0) {

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
            if ((dirtyFlags & 0x188L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.rankingProducts
                        viewModelRankingProducts = viewModel.getRankingProducts();
                    }
                    updateLiveDataRegistration(3, viewModelRankingProducts);


                    if (viewModelRankingProducts != null) {
                        // read viewModel.rankingProducts.getValue()
                        viewModelRankingProductsGetValue = viewModelRankingProducts.getValue();
                    }


                    if (viewModelRankingProductsGetValue != null) {
                        // read viewModel.rankingProducts.getValue().size()
                        viewModelRankingProductsSize = viewModelRankingProductsGetValue.size();
                    }
            }
            if ((dirtyFlags & 0x190L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.products
                        viewModelProducts = viewModel.getProducts();
                    }
                    updateLiveDataRegistration(4, viewModelProducts);


                    if (viewModelProducts != null) {
                        // read viewModel.products.getValue()
                        viewModelProductsGetValue = viewModelProducts.getValue();
                    }
            }
            if ((dirtyFlags & 0x1a0L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.displayCategoryPurchase
                        viewModelDisplayCategoryPurchase = viewModel.getDisplayCategoryPurchase();
                    }
                    updateLiveDataRegistration(5, viewModelDisplayCategoryPurchase);


                    if (viewModelDisplayCategoryPurchase != null) {
                        // read viewModel.displayCategoryPurchase.getValue()
                        viewModelDisplayCategoryPurchaseGetValue = viewModelDisplayCategoryPurchase.getValue();
                    }
            }
            if ((dirtyFlags & 0x1c0L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.currentProductKind
                        viewModelCurrentProductKind = viewModel.getCurrentProductKind();
                    }
                    updateLiveDataRegistration(6, viewModelCurrentProductKind);


                    if (viewModelCurrentProductKind != null) {
                        // read viewModel.currentProductKind.getValue()
                        viewModelCurrentProductKindGetValue = viewModelCurrentProductKind.getValue();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x182L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.categoryName, viewModelDisplayCategoryName);
            androidx.databinding.adapters.RatingBarBindingAdapter.setRating(this.rating, viewModelDisplayCategoryRating);
        }
        if ((dirtyFlags & 0x181L) != 0) {
            // api target 1

            com.example.aposs_buyer.utils.BindingAdapterKt.bindCategoriesViewPager(this.imageViewPager, viewModelCategoriesGetValue);
            com.example.aposs_buyer.utils.BindingAdapterKt.bindIndicatorSize(this.indicator, viewModelCategoriesSize);
        }
        if ((dirtyFlags & 0x1c0L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView8, viewModelCurrentProductKindGetValue);
        }
        if ((dirtyFlags & 0x190L) != 0) {
            // api target 1

            com.example.aposs_buyer.utils.BindingAdapterKt.bindProductRecyclerView(this.products, viewModelProductsGetValue);
        }
        if ((dirtyFlags & 0x188L) != 0) {
            // api target 1

            com.example.aposs_buyer.utils.BindingAdapterKt.bindIndicatorSize(this.rankingIndicator, viewModelRankingProductsSize);
            com.example.aposs_buyer.utils.BindingAdapterKt.bindRankingViewPager(this.rankingViewPager, viewModelRankingProductsGetValue);
        }
        if ((dirtyFlags & 0x184L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.totalProduct, viewModelDisplayCategoryProductsGetValue);
        }
        if ((dirtyFlags & 0x1a0L) != 0) {
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
        flag 3 (0x4L): viewModel.rankingProducts
        flag 4 (0x5L): viewModel.products
        flag 5 (0x6L): viewModel.displayCategoryPurchase
        flag 6 (0x7L): viewModel.currentProductKind
        flag 7 (0x8L): viewModel
        flag 8 (0x9L): null
    flag mapping end*/
    //end
}