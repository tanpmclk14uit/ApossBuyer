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
        sViewsWithIds.put(R.id.scrollView, 11);
        sViewsWithIds.put(R.id.categories, 12);
        sViewsWithIds.put(R.id.tv_showAllCategory, 13);
        sViewsWithIds.put(R.id.cl_category, 14);
        sViewsWithIds.put(R.id.ranking, 15);
        sViewsWithIds.put(R.id.cardView2, 16);
        sViewsWithIds.put(R.id.cardView, 17);
        sViewsWithIds.put(R.id.more, 18);
        sViewsWithIds.put(R.id.title, 19);
        sViewsWithIds.put(R.id.lnAboutUs, 20);
        sViewsWithIds.put(R.id.toolBox, 21);
        sViewsWithIds.put(R.id.search_bar, 22);
        sViewsWithIds.put(R.id.search, 23);
        sViewsWithIds.put(R.id.notification, 24);
    }
    // views
    @NonNull
    private final androidx.coordinatorlayout.widget.CoordinatorLayout mboundView0;
    @NonNull
    private final android.widget.ProgressBar mboundView10;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentHomeBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 25, sIncludes, sViewsWithIds));
    }
    private FragmentHomeBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 7
            , (androidx.cardview.widget.CardView) bindings[17]
            , (androidx.cardview.widget.CardView) bindings[16]
            , (android.widget.TextView) bindings[12]
            , (androidx.viewpager2.widget.ViewPager2) bindings[1]
            , (android.widget.TextView) bindings[3]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[14]
            , (me.relex.circleindicator.CircleIndicator3) bindings[2]
            , (android.widget.ImageView) bindings[20]
            , (android.widget.TextView) bindings[18]
            , (android.widget.ImageView) bindings[24]
            , (androidx.recyclerview.widget.RecyclerView) bindings[9]
            , (android.widget.TextView) bindings[15]
            , (me.relex.circleindicator.CircleIndicator3) bindings[8]
            , (androidx.viewpager2.widget.ViewPager2) bindings[7]
            , (android.widget.RatingBar) bindings[6]
            , (androidx.core.widget.NestedScrollView) bindings[11]
            , (com.google.android.material.textfield.TextInputEditText) bindings[23]
            , (com.google.android.material.textfield.TextInputLayout) bindings[22]
            , (android.widget.TextView) bindings[19]
            , (android.widget.RelativeLayout) bindings[21]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[13]
            );
        this.categoriesViewPager.setTag(null);
        this.categoryName.setTag(null);
        this.indicator.setTag(null);
        this.mboundView0 = (androidx.coordinatorlayout.widget.CoordinatorLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView10 = (android.widget.ProgressBar) bindings[10];
        this.mboundView10.setTag(null);
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
                mDirtyFlags = 0x400L;
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
        if (BR.status == variableId) {
            setStatus((com.example.aposs_buyer.utils.LoadingStatus) variable);
        }
        else if (BR.view == variableId) {
            setView((android.view.View) variable);
        }
        else if (BR.viewModel == variableId) {
            setViewModel((com.example.aposs_buyer.viewmodel.HomeViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setStatus(@Nullable com.example.aposs_buyer.utils.LoadingStatus Status) {
        this.mStatus = Status;
    }
    public void setView(@Nullable android.view.View View) {
        this.mView = View;
    }
    public void setViewModel(@Nullable com.example.aposs_buyer.viewmodel.HomeViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x200L;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeViewModelDisplayCategory((androidx.lifecycle.LiveData<com.example.aposs_buyer.model.Category>) object, fieldId);
            case 1 :
                return onChangeViewModelRankingProducts((androidx.lifecycle.LiveData<java.util.List<com.example.aposs_buyer.model.RankingProduct>>) object, fieldId);
            case 2 :
                return onChangeViewModelProducts((androidx.lifecycle.LiveData<java.util.List<com.example.aposs_buyer.model.HomeProduct>>) object, fieldId);
            case 3 :
                return onChangeViewModelDisplayCategoryPurchase((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 4 :
                return onChangeViewModelCategories((androidx.lifecycle.LiveData<java.util.ArrayList<com.example.aposs_buyer.model.Category>>) object, fieldId);
            case 5 :
                return onChangeViewModelDisplayCategoryProducts((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 6 :
                return onChangeViewModelStatus((androidx.lifecycle.LiveData<com.example.aposs_buyer.utils.LoadingStatus>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelDisplayCategory(androidx.lifecycle.LiveData<com.example.aposs_buyer.model.Category> ViewModelDisplayCategory, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelRankingProducts(androidx.lifecycle.LiveData<java.util.List<com.example.aposs_buyer.model.RankingProduct>> ViewModelRankingProducts, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelProducts(androidx.lifecycle.LiveData<java.util.List<com.example.aposs_buyer.model.HomeProduct>> ViewModelProducts, int fieldId) {
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
    private boolean onChangeViewModelCategories(androidx.lifecycle.LiveData<java.util.ArrayList<com.example.aposs_buyer.model.Category>> ViewModelCategories, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x10L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelDisplayCategoryProducts(androidx.lifecycle.MutableLiveData<java.lang.String> ViewModelDisplayCategoryProducts, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x20L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelStatus(androidx.lifecycle.LiveData<com.example.aposs_buyer.utils.LoadingStatus> ViewModelStatus, int fieldId) {
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
        androidx.lifecycle.LiveData<com.example.aposs_buyer.model.Category> viewModelDisplayCategory = null;
        com.example.aposs_buyer.model.Category viewModelDisplayCategoryGetValue = null;
        java.util.ArrayList<com.example.aposs_buyer.model.Category> viewModelCategoriesGetValue = null;
        int viewModelStatusStatusLoadingViewVISIBLEViewGONE = 0;
        boolean viewModelStatusStatusLoading = false;
        int viewModelCategoriesSize = 0;
        androidx.lifecycle.LiveData<java.util.List<com.example.aposs_buyer.model.RankingProduct>> viewModelRankingProducts = null;
        float viewModelDisplayCategoryRating = 0f;
        java.util.List<com.example.aposs_buyer.model.RankingProduct> viewModelRankingProductsGetValue = null;
        androidx.lifecycle.LiveData<java.util.List<com.example.aposs_buyer.model.HomeProduct>> viewModelProducts = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> viewModelDisplayCategoryPurchase = null;
        androidx.lifecycle.LiveData<java.util.ArrayList<com.example.aposs_buyer.model.Category>> viewModelCategories = null;
        int viewModelRankingProductsSize = 0;
        java.util.List<com.example.aposs_buyer.model.HomeProduct> viewModelProductsGetValue = null;
        java.lang.String viewModelDisplayCategoryPurchaseGetValue = null;
        java.lang.String viewModelDisplayCategoryProductsGetValue = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> viewModelDisplayCategoryProducts = null;
        androidx.lifecycle.LiveData<com.example.aposs_buyer.utils.LoadingStatus> viewModelStatus = null;
        java.lang.String viewModelDisplayCategoryName = null;
        com.example.aposs_buyer.utils.LoadingStatus viewModelStatusGetValue = null;
        com.example.aposs_buyer.viewmodel.HomeViewModel viewModel = mViewModel;

        if ((dirtyFlags & 0x67fL) != 0) {


            if ((dirtyFlags & 0x601L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.displayCategory
                        viewModelDisplayCategory = viewModel.getDisplayCategory();
                    }
                    updateLiveDataRegistration(0, viewModelDisplayCategory);


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
            if ((dirtyFlags & 0x602L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.rankingProducts
                        viewModelRankingProducts = viewModel.getRankingProducts();
                    }
                    updateLiveDataRegistration(1, viewModelRankingProducts);


                    if (viewModelRankingProducts != null) {
                        // read viewModel.rankingProducts.getValue()
                        viewModelRankingProductsGetValue = viewModelRankingProducts.getValue();
                    }


                    if (viewModelRankingProductsGetValue != null) {
                        // read viewModel.rankingProducts.getValue().size()
                        viewModelRankingProductsSize = viewModelRankingProductsGetValue.size();
                    }
            }
            if ((dirtyFlags & 0x604L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.products
                        viewModelProducts = viewModel.getProducts();
                    }
                    updateLiveDataRegistration(2, viewModelProducts);


                    if (viewModelProducts != null) {
                        // read viewModel.products.getValue()
                        viewModelProductsGetValue = viewModelProducts.getValue();
                    }
            }
            if ((dirtyFlags & 0x608L) != 0) {

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
            if ((dirtyFlags & 0x610L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.categories
                        viewModelCategories = viewModel.getCategories();
                    }
                    updateLiveDataRegistration(4, viewModelCategories);


                    if (viewModelCategories != null) {
                        // read viewModel.categories.getValue()
                        viewModelCategoriesGetValue = viewModelCategories.getValue();
                    }


                    if (viewModelCategoriesGetValue != null) {
                        // read viewModel.categories.getValue().size()
                        viewModelCategoriesSize = viewModelCategoriesGetValue.size();
                    }
            }
            if ((dirtyFlags & 0x620L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.displayCategoryProducts
                        viewModelDisplayCategoryProducts = viewModel.getDisplayCategoryProducts();
                    }
                    updateLiveDataRegistration(5, viewModelDisplayCategoryProducts);


                    if (viewModelDisplayCategoryProducts != null) {
                        // read viewModel.displayCategoryProducts.getValue()
                        viewModelDisplayCategoryProductsGetValue = viewModelDisplayCategoryProducts.getValue();
                    }
            }
            if ((dirtyFlags & 0x640L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.status
                        viewModelStatus = viewModel.getStatus();
                    }
                    updateLiveDataRegistration(6, viewModelStatus);


                    if (viewModelStatus != null) {
                        // read viewModel.status.getValue()
                        viewModelStatusGetValue = viewModelStatus.getValue();
                    }


                    // read viewModel.status.getValue() == LoadingStatus.Loading
                    viewModelStatusStatusLoading = (viewModelStatusGetValue) == (com.example.aposs_buyer.utils.LoadingStatus.Loading);
                if((dirtyFlags & 0x640L) != 0) {
                    if(viewModelStatusStatusLoading) {
                            dirtyFlags |= 0x1000L;
                    }
                    else {
                            dirtyFlags |= 0x800L;
                    }
                }


                    // read viewModel.status.getValue() == LoadingStatus.Loading ? View.VISIBLE : View.GONE
                    viewModelStatusStatusLoadingViewVISIBLEViewGONE = ((viewModelStatusStatusLoading) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
            }
        }
        // batch finished
        if ((dirtyFlags & 0x610L) != 0) {
            // api target 1

            com.example.aposs_buyer.utils.BindingAdapterKt.bindCategoriesViewPager(this.categoriesViewPager, viewModelCategoriesGetValue);
            com.example.aposs_buyer.utils.BindingAdapterKt.bindIndicatorSize(this.indicator, viewModelCategoriesSize);
        }
        if ((dirtyFlags & 0x601L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.categoryName, viewModelDisplayCategoryName);
            androidx.databinding.adapters.RatingBarBindingAdapter.setRating(this.rating, viewModelDisplayCategoryRating);
        }
        if ((dirtyFlags & 0x640L) != 0) {
            // api target 1

            this.mboundView10.setVisibility(viewModelStatusStatusLoadingViewVISIBLEViewGONE);
        }
        if ((dirtyFlags & 0x604L) != 0) {
            // api target 1

            com.example.aposs_buyer.utils.BindingAdapterKt.bindProductRecyclerView(this.products, viewModelProductsGetValue);
        }
        if ((dirtyFlags & 0x602L) != 0) {
            // api target 1

            com.example.aposs_buyer.utils.BindingAdapterKt.bindIndicatorSize(this.rankingIndicator, viewModelRankingProductsSize);
            com.example.aposs_buyer.utils.BindingAdapterKt.bindRankingViewPager(this.rankingViewPager, viewModelRankingProductsGetValue);
        }
        if ((dirtyFlags & 0x620L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.totalProduct, viewModelDisplayCategoryProductsGetValue);
        }
        if ((dirtyFlags & 0x608L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.totalPurchase, viewModelDisplayCategoryPurchaseGetValue);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel.displayCategory
        flag 1 (0x2L): viewModel.rankingProducts
        flag 2 (0x3L): viewModel.products
        flag 3 (0x4L): viewModel.displayCategoryPurchase
        flag 4 (0x5L): viewModel.categories
        flag 5 (0x6L): viewModel.displayCategoryProducts
        flag 6 (0x7L): viewModel.status
        flag 7 (0x8L): status
        flag 8 (0x9L): view
        flag 9 (0xaL): viewModel
        flag 10 (0xbL): null
        flag 11 (0xcL): viewModel.status.getValue() == LoadingStatus.Loading ? View.VISIBLE : View.GONE
        flag 12 (0xdL): viewModel.status.getValue() == LoadingStatus.Loading ? View.VISIBLE : View.GONE
    flag mapping end*/
    //end
}