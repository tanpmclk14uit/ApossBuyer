package com.example.aposs_buyer;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.example.aposs_buyer.databinding.ActivityCategoryBindingImpl;
import com.example.aposs_buyer.databinding.ActivityDetailProductBindingImpl;
import com.example.aposs_buyer.databinding.ActivityMainBindingImpl;
import com.example.aposs_buyer.databinding.FragmentAllFavoriteBindingImpl;
import com.example.aposs_buyer.databinding.FragmentAvailableFavoriteBindingImpl;
import com.example.aposs_buyer.databinding.FragmentCartBindingImpl;
import com.example.aposs_buyer.databinding.FragmentCategoriesBindingImpl;
import com.example.aposs_buyer.databinding.FragmentDetailProductBindingImpl;
import com.example.aposs_buyer.databinding.FragmentFavoriteBindingImpl;
import com.example.aposs_buyer.databinding.FragmentFullScreenImageBindingImpl;
import com.example.aposs_buyer.databinding.FragmentHomeBindingImpl;
import com.example.aposs_buyer.databinding.FragmentMessageBindingImpl;
import com.example.aposs_buyer.databinding.FragmentPersonBindingImpl;
import com.example.aposs_buyer.databinding.FragmentProductDetailDialogListDialogBindingImpl;
import com.example.aposs_buyer.databinding.FragmentProductRatingBindingImpl;
import com.example.aposs_buyer.databinding.ItemCartBindingImpl;
import com.example.aposs_buyer.databinding.ItemColorPropertyBindingImpl;
import com.example.aposs_buyer.databinding.ItemDetailProductImageViewPagerBindingImpl;
import com.example.aposs_buyer.databinding.ItemFavoriteBindingImpl;
import com.example.aposs_buyer.databinding.ItemLeftSideMessageBindingImpl;
import com.example.aposs_buyer.databinding.ItemProductBindingImpl;
import com.example.aposs_buyer.databinding.ItemProductColorPropertyBindingImpl;
import com.example.aposs_buyer.databinding.ItemProductStringPropertyBindingImpl;
import com.example.aposs_buyer.databinding.ItemRakingBindingImpl;
import com.example.aposs_buyer.databinding.ItemRatingBindingImpl;
import com.example.aposs_buyer.databinding.ItemRatingImageBindingImpl;
import com.example.aposs_buyer.databinding.ItemRightSideMessageBindingImpl;
import com.example.aposs_buyer.databinding.ItemStringPropertyBindingImpl;
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
  private static final int LAYOUT_ACTIVITYCATEGORY = 1;

  private static final int LAYOUT_ACTIVITYDETAILPRODUCT = 2;

  private static final int LAYOUT_ACTIVITYMAIN = 3;

  private static final int LAYOUT_FRAGMENTALLFAVORITE = 4;

  private static final int LAYOUT_FRAGMENTAVAILABLEFAVORITE = 5;

  private static final int LAYOUT_FRAGMENTCART = 6;

  private static final int LAYOUT_FRAGMENTCATEGORIES = 7;

  private static final int LAYOUT_FRAGMENTDETAILPRODUCT = 8;

  private static final int LAYOUT_FRAGMENTFAVORITE = 9;

  private static final int LAYOUT_FRAGMENTFULLSCREENIMAGE = 10;

  private static final int LAYOUT_FRAGMENTHOME = 11;

  private static final int LAYOUT_FRAGMENTMESSAGE = 12;

  private static final int LAYOUT_FRAGMENTPERSON = 13;

  private static final int LAYOUT_FRAGMENTPRODUCTDETAILDIALOGLISTDIALOG = 14;

  private static final int LAYOUT_FRAGMENTPRODUCTRATING = 15;

  private static final int LAYOUT_ITEMCART = 16;

  private static final int LAYOUT_ITEMCOLORPROPERTY = 17;

  private static final int LAYOUT_ITEMDETAILPRODUCTIMAGEVIEWPAGER = 18;

  private static final int LAYOUT_ITEMFAVORITE = 19;

  private static final int LAYOUT_ITEMLEFTSIDEMESSAGE = 20;

  private static final int LAYOUT_ITEMPRODUCT = 21;

  private static final int LAYOUT_ITEMPRODUCTCOLORPROPERTY = 22;

  private static final int LAYOUT_ITEMPRODUCTSTRINGPROPERTY = 23;

  private static final int LAYOUT_ITEMRAKING = 24;

  private static final int LAYOUT_ITEMRATING = 25;

  private static final int LAYOUT_ITEMRATINGIMAGE = 26;

  private static final int LAYOUT_ITEMRIGHTSIDEMESSAGE = 27;

  private static final int LAYOUT_ITEMSTRINGPROPERTY = 28;

  private static final int LAYOUT_ITEMVIEWPAPERCATEGORIES = 29;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(29);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.activity_category, LAYOUT_ACTIVITYCATEGORY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.activity_detail_product, LAYOUT_ACTIVITYDETAILPRODUCT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.activity_main, LAYOUT_ACTIVITYMAIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_all_favorite, LAYOUT_FRAGMENTALLFAVORITE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_available_favorite, LAYOUT_FRAGMENTAVAILABLEFAVORITE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_cart, LAYOUT_FRAGMENTCART);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_categories, LAYOUT_FRAGMENTCATEGORIES);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_detail_product, LAYOUT_FRAGMENTDETAILPRODUCT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_favorite, LAYOUT_FRAGMENTFAVORITE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_full_screen_image, LAYOUT_FRAGMENTFULLSCREENIMAGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_home, LAYOUT_FRAGMENTHOME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_message, LAYOUT_FRAGMENTMESSAGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_person, LAYOUT_FRAGMENTPERSON);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_product_detail_dialog_list_dialog, LAYOUT_FRAGMENTPRODUCTDETAILDIALOGLISTDIALOG);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_product_rating, LAYOUT_FRAGMENTPRODUCTRATING);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_cart, LAYOUT_ITEMCART);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_color_property, LAYOUT_ITEMCOLORPROPERTY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_detail_product_image_view_pager, LAYOUT_ITEMDETAILPRODUCTIMAGEVIEWPAGER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_favorite, LAYOUT_ITEMFAVORITE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_left_side_message, LAYOUT_ITEMLEFTSIDEMESSAGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_product, LAYOUT_ITEMPRODUCT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_product_color_property, LAYOUT_ITEMPRODUCTCOLORPROPERTY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_product_string_property, LAYOUT_ITEMPRODUCTSTRINGPROPERTY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_raking, LAYOUT_ITEMRAKING);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_rating, LAYOUT_ITEMRATING);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_rating_image, LAYOUT_ITEMRATINGIMAGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_right_side_message, LAYOUT_ITEMRIGHTSIDEMESSAGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_string_property, LAYOUT_ITEMSTRINGPROPERTY);
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
        case  LAYOUT_ACTIVITYCATEGORY: {
          if ("layout/activity_category_0".equals(tag)) {
            return new ActivityCategoryBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_category is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYDETAILPRODUCT: {
          if ("layout/activity_detail_product_0".equals(tag)) {
            return new ActivityDetailProductBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_detail_product is invalid. Received: " + tag);
        }
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
        case  LAYOUT_FRAGMENTAVAILABLEFAVORITE: {
          if ("layout/fragment_available_favorite_0".equals(tag)) {
            return new FragmentAvailableFavoriteBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_available_favorite is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTCART: {
          if ("layout/fragment_cart_0".equals(tag)) {
            return new FragmentCartBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_cart is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTCATEGORIES: {
          if ("layout/fragment_categories_0".equals(tag)) {
            return new FragmentCategoriesBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_categories is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTDETAILPRODUCT: {
          if ("layout/fragment_detail_product_0".equals(tag)) {
            return new FragmentDetailProductBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_detail_product is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTFAVORITE: {
          if ("layout/fragment_favorite_0".equals(tag)) {
            return new FragmentFavoriteBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_favorite is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTFULLSCREENIMAGE: {
          if ("layout/fragment_full_screen_image_0".equals(tag)) {
            return new FragmentFullScreenImageBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_full_screen_image is invalid. Received: " + tag);
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
        case  LAYOUT_FRAGMENTPRODUCTDETAILDIALOGLISTDIALOG: {
          if ("layout/fragment_product_detail_dialog_list_dialog_0".equals(tag)) {
            return new FragmentProductDetailDialogListDialogBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_product_detail_dialog_list_dialog is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTPRODUCTRATING: {
          if ("layout/fragment_product_rating_0".equals(tag)) {
            return new FragmentProductRatingBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_product_rating is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMCART: {
          if ("layout/item_cart_0".equals(tag)) {
            return new ItemCartBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_cart is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMCOLORPROPERTY: {
          if ("layout/item_color_property_0".equals(tag)) {
            return new ItemColorPropertyBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_color_property is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMDETAILPRODUCTIMAGEVIEWPAGER: {
          if ("layout/item_detail_product_image_view_pager_0".equals(tag)) {
            return new ItemDetailProductImageViewPagerBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_detail_product_image_view_pager is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMFAVORITE: {
          if ("layout/item_favorite_0".equals(tag)) {
            return new ItemFavoriteBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_favorite is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMLEFTSIDEMESSAGE: {
          if ("layout/item_left_side_message_0".equals(tag)) {
            return new ItemLeftSideMessageBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_left_side_message is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMPRODUCT: {
          if ("layout/item_product_0".equals(tag)) {
            return new ItemProductBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_product is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMPRODUCTCOLORPROPERTY: {
          if ("layout/item_product_color_property_0".equals(tag)) {
            return new ItemProductColorPropertyBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_product_color_property is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMPRODUCTSTRINGPROPERTY: {
          if ("layout/item_product_string_property_0".equals(tag)) {
            return new ItemProductStringPropertyBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_product_string_property is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMRAKING: {
          if ("layout/item_raking_0".equals(tag)) {
            return new ItemRakingBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_raking is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMRATING: {
          if ("layout/item_rating_0".equals(tag)) {
            return new ItemRatingBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_rating is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMRATINGIMAGE: {
          if ("layout/item_rating_image_0".equals(tag)) {
            return new ItemRatingImageBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_rating_image is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMRIGHTSIDEMESSAGE: {
          if ("layout/item_right_side_message_0".equals(tag)) {
            return new ItemRightSideMessageBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_right_side_message is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMSTRINGPROPERTY: {
          if ("layout/item_string_property_0".equals(tag)) {
            return new ItemStringPropertyBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_string_property is invalid. Received: " + tag);
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
    static final SparseArray<String> sKeys = new SparseArray<String>(13);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "cartItem");
      sKeys.put(2, "color");
      sKeys.put(3, "favoriteProduct");
      sKeys.put(4, "image");
      sKeys.put(5, "imgCategory");
      sKeys.put(6, "messageItem");
      sKeys.put(7, "product");
      sKeys.put(8, "property");
      sKeys.put(9, "rankingProduct");
      sKeys.put(10, "rating");
      sKeys.put(11, "view");
      sKeys.put(12, "viewModel");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(29);

    static {
      sKeys.put("layout/activity_category_0", com.example.aposs_buyer.R.layout.activity_category);
      sKeys.put("layout/activity_detail_product_0", com.example.aposs_buyer.R.layout.activity_detail_product);
      sKeys.put("layout/activity_main_0", com.example.aposs_buyer.R.layout.activity_main);
      sKeys.put("layout/fragment_all_favorite_0", com.example.aposs_buyer.R.layout.fragment_all_favorite);
      sKeys.put("layout/fragment_available_favorite_0", com.example.aposs_buyer.R.layout.fragment_available_favorite);
      sKeys.put("layout/fragment_cart_0", com.example.aposs_buyer.R.layout.fragment_cart);
      sKeys.put("layout/fragment_categories_0", com.example.aposs_buyer.R.layout.fragment_categories);
      sKeys.put("layout/fragment_detail_product_0", com.example.aposs_buyer.R.layout.fragment_detail_product);
      sKeys.put("layout/fragment_favorite_0", com.example.aposs_buyer.R.layout.fragment_favorite);
      sKeys.put("layout/fragment_full_screen_image_0", com.example.aposs_buyer.R.layout.fragment_full_screen_image);
      sKeys.put("layout/fragment_home_0", com.example.aposs_buyer.R.layout.fragment_home);
      sKeys.put("layout/fragment_message_0", com.example.aposs_buyer.R.layout.fragment_message);
      sKeys.put("layout/fragment_person_0", com.example.aposs_buyer.R.layout.fragment_person);
      sKeys.put("layout/fragment_product_detail_dialog_list_dialog_0", com.example.aposs_buyer.R.layout.fragment_product_detail_dialog_list_dialog);
      sKeys.put("layout/fragment_product_rating_0", com.example.aposs_buyer.R.layout.fragment_product_rating);
      sKeys.put("layout/item_cart_0", com.example.aposs_buyer.R.layout.item_cart);
      sKeys.put("layout/item_color_property_0", com.example.aposs_buyer.R.layout.item_color_property);
      sKeys.put("layout/item_detail_product_image_view_pager_0", com.example.aposs_buyer.R.layout.item_detail_product_image_view_pager);
      sKeys.put("layout/item_favorite_0", com.example.aposs_buyer.R.layout.item_favorite);
      sKeys.put("layout/item_left_side_message_0", com.example.aposs_buyer.R.layout.item_left_side_message);
      sKeys.put("layout/item_product_0", com.example.aposs_buyer.R.layout.item_product);
      sKeys.put("layout/item_product_color_property_0", com.example.aposs_buyer.R.layout.item_product_color_property);
      sKeys.put("layout/item_product_string_property_0", com.example.aposs_buyer.R.layout.item_product_string_property);
      sKeys.put("layout/item_raking_0", com.example.aposs_buyer.R.layout.item_raking);
      sKeys.put("layout/item_rating_0", com.example.aposs_buyer.R.layout.item_rating);
      sKeys.put("layout/item_rating_image_0", com.example.aposs_buyer.R.layout.item_rating_image);
      sKeys.put("layout/item_right_side_message_0", com.example.aposs_buyer.R.layout.item_right_side_message);
      sKeys.put("layout/item_string_property_0", com.example.aposs_buyer.R.layout.item_string_property);
      sKeys.put("layout/item_view_paper_categories_0", com.example.aposs_buyer.R.layout.item_view_paper_categories);
    }
  }
}
