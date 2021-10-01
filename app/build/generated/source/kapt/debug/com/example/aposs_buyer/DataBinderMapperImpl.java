package com.example.aposs_buyer;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.example.aposs_buyer.databinding.ActivityMainBindingImpl;
import com.example.aposs_buyer.databinding.FragmentAllFavoriteBindingImpl;
import com.example.aposs_buyer.databinding.FragmentCartBindingImpl;
import com.example.aposs_buyer.databinding.FragmentFavoriteBindingImpl;
import com.example.aposs_buyer.databinding.FragmentHomeBindingImpl;
import com.example.aposs_buyer.databinding.FragmentMessageBindingImpl;
import com.example.aposs_buyer.databinding.FragmentPersonBindingImpl;
import com.example.aposs_buyer.databinding.ItemProductBindingImpl;
import com.example.aposs_buyer.databinding.ItemRakingBindingImpl;
import com.example.aposs_buyer.databinding.ItemViewPaperCategoriesBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYMAIN = 1;

  private static final int LAYOUT_FRAGMENTALLFAVORITE = 2;

  private static final int LAYOUT_FRAGMENTCART = 3;

  private static final int LAYOUT_FRAGMENTFAVORITE = 4;

  private static final int LAYOUT_FRAGMENTHOME = 5;

  private static final int LAYOUT_FRAGMENTMESSAGE = 6;

  private static final int LAYOUT_FRAGMENTPERSON = 7;

  private static final int LAYOUT_ITEMPRODUCT = 8;

  private static final int LAYOUT_ITEMRAKING = 9;

  private static final int LAYOUT_ITEMVIEWPAPERCATEGORIES = 10;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(10);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.activity_main, LAYOUT_ACTIVITYMAIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_all_favorite, LAYOUT_FRAGMENTALLFAVORITE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_cart, LAYOUT_FRAGMENTCART);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_favorite, LAYOUT_FRAGMENTFAVORITE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_home, LAYOUT_FRAGMENTHOME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_message, LAYOUT_FRAGMENTMESSAGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_person, LAYOUT_FRAGMENTPERSON);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_product, LAYOUT_ITEMPRODUCT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_raking, LAYOUT_ITEMRAKING);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_view_paper_categories, LAYOUT_ITEMVIEWPAPERCATEGORIES);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYMAIN: {
          if ("layout/activity_main_0".equals(tag)) {
            return new ActivityMainBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_main is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTALLFAVORITE: {
          if ("layout/fragment_all_favorite_0".equals(tag)) {
            return new FragmentAllFavoriteBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_all_favorite is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTCART: {
          if ("layout/fragment_cart_0".equals(tag)) {
            return new FragmentCartBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_cart is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTFAVORITE: {
          if ("layout/fragment_favorite_0".equals(tag)) {
            return new FragmentFavoriteBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_favorite is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTHOME: {
          if ("layout/fragment_home_0".equals(tag)) {
            return new FragmentHomeBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_home is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTMESSAGE: {
          if ("layout/fragment_message_0".equals(tag)) {
            return new FragmentMessageBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_message is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTPERSON: {
          if ("layout/fragment_person_0".equals(tag)) {
            return new FragmentPersonBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_person is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMPRODUCT: {
          if ("layout/item_product_0".equals(tag)) {
            return new ItemProductBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_product is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMRAKING: {
          if ("layout/item_raking_0".equals(tag)) {
            return new ItemRakingBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_raking is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMVIEWPAPERCATEGORIES: {
          if ("layout/item_view_paper_categories_0".equals(tag)) {
            return new ItemViewPaperCategoriesBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_view_paper_categories is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(5);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "imgCategory");
      sKeys.put(2, "product");
      sKeys.put(3, "rankingProduct");
      sKeys.put(4, "viewModel");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(10);

    static {
      sKeys.put("layout/activity_main_0", com.example.aposs_buyer.R.layout.activity_main);
      sKeys.put("layout/fragment_all_favorite_0", com.example.aposs_buyer.R.layout.fragment_all_favorite);
      sKeys.put("layout/fragment_cart_0", com.example.aposs_buyer.R.layout.fragment_cart);
      sKeys.put("layout/fragment_favorite_0", com.example.aposs_buyer.R.layout.fragment_favorite);
      sKeys.put("layout/fragment_home_0", com.example.aposs_buyer.R.layout.fragment_home);
      sKeys.put("layout/fragment_message_0", com.example.aposs_buyer.R.layout.fragment_message);
      sKeys.put("layout/fragment_person_0", com.example.aposs_buyer.R.layout.fragment_person);
      sKeys.put("layout/item_product_0", com.example.aposs_buyer.R.layout.item_product);
      sKeys.put("layout/item_raking_0", com.example.aposs_buyer.R.layout.item_raking);
      sKeys.put("layout/item_view_paper_categories_0", com.example.aposs_buyer.R.layout.item_view_paper_categories);
    }
  }
}
