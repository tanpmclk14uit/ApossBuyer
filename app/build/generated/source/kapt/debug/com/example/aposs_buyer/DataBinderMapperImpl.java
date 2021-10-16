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
import com.example.aposs_buyer.databinding.ActivityUserDetailBindingImpl;
import com.example.aposs_buyer.databinding.FragmentAddressBindingImpl;
import com.example.aposs_buyer.databinding.FragmentAddressDialogBindingImpl;
import com.example.aposs_buyer.databinding.FragmentAllFavoriteBindingImpl;
import com.example.aposs_buyer.databinding.FragmentAvailableFavoriteBindingImpl;
import com.example.aposs_buyer.databinding.FragmentCartBindingImpl;
import com.example.aposs_buyer.databinding.FragmentCategoriesBindingImpl;
import com.example.aposs_buyer.databinding.FragmentCheckOutBindingImpl;
import com.example.aposs_buyer.databinding.FragmentCheckOutDialogBindingImpl;
import com.example.aposs_buyer.databinding.FragmentDetailProductBindingImpl;
import com.example.aposs_buyer.databinding.FragmentFavoriteBindingImpl;
import com.example.aposs_buyer.databinding.FragmentFinishCheckOutBindingImpl;
import com.example.aposs_buyer.databinding.FragmentFullScreenImageBindingImpl;
import com.example.aposs_buyer.databinding.FragmentHomeBindingImpl;
import com.example.aposs_buyer.databinding.FragmentKindBindingImpl;
import com.example.aposs_buyer.databinding.FragmentMessageBindingImpl;
import com.example.aposs_buyer.databinding.FragmentPersonBindingImpl;
import com.example.aposs_buyer.databinding.FragmentProductDetailDialogListDialogBindingImpl;
import com.example.aposs_buyer.databinding.FragmentProductOfKindBindingImpl;
import com.example.aposs_buyer.databinding.FragmentProductRatingBindingImpl;
import com.example.aposs_buyer.databinding.FragmentSearchBindingImpl;
import com.example.aposs_buyer.databinding.FragmentUserDetailBindingImpl;
import com.example.aposs_buyer.databinding.ItemAddressBindingImpl;
import com.example.aposs_buyer.databinding.ItemCartBindingImpl;
import com.example.aposs_buyer.databinding.ItemCategoryBindingImpl;
import com.example.aposs_buyer.databinding.ItemCheckOutBindingImpl;
import com.example.aposs_buyer.databinding.ItemCheckOutConfirmBindingImpl;
import com.example.aposs_buyer.databinding.ItemColorPropertyBindingImpl;
import com.example.aposs_buyer.databinding.ItemDetailProductImageViewPagerBindingImpl;
import com.example.aposs_buyer.databinding.ItemFavoriteBindingImpl;
import com.example.aposs_buyer.databinding.ItemKindBindingImpl;
import com.example.aposs_buyer.databinding.ItemLeftSideMessageBindingImpl;
import com.example.aposs_buyer.databinding.ItemProductBindingImpl;
import com.example.aposs_buyer.databinding.ItemProductColorPropertyBindingImpl;
import com.example.aposs_buyer.databinding.ItemProductStringPropertyBindingImpl;
import com.example.aposs_buyer.databinding.ItemRakingBindingImpl;
import com.example.aposs_buyer.databinding.ItemRatingBindingImpl;
import com.example.aposs_buyer.databinding.ItemRatingImageBindingImpl;
import com.example.aposs_buyer.databinding.ItemRightSideMessageBindingImpl;
import com.example.aposs_buyer.databinding.ItemStringPropertyBindingImpl;
import com.example.aposs_buyer.databinding.ItemViewPagerDetailCategoryBindingImpl;
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

  private static final int LAYOUT_ACTIVITYUSERDETAIL = 4;

  private static final int LAYOUT_FRAGMENTADDRESS = 5;

  private static final int LAYOUT_FRAGMENTADDRESSDIALOG = 6;

  private static final int LAYOUT_FRAGMENTALLFAVORITE = 7;

  private static final int LAYOUT_FRAGMENTAVAILABLEFAVORITE = 8;

  private static final int LAYOUT_FRAGMENTCART = 9;

  private static final int LAYOUT_FRAGMENTCATEGORIES = 10;

  private static final int LAYOUT_FRAGMENTCHECKOUT = 11;

  private static final int LAYOUT_FRAGMENTCHECKOUTDIALOG = 12;

  private static final int LAYOUT_FRAGMENTDETAILPRODUCT = 13;

  private static final int LAYOUT_FRAGMENTFAVORITE = 14;

  private static final int LAYOUT_FRAGMENTFINISHCHECKOUT = 15;

  private static final int LAYOUT_FRAGMENTFULLSCREENIMAGE = 16;

  private static final int LAYOUT_FRAGMENTHOME = 17;

  private static final int LAYOUT_FRAGMENTKIND = 18;

  private static final int LAYOUT_FRAGMENTMESSAGE = 19;

  private static final int LAYOUT_FRAGMENTPERSON = 20;

  private static final int LAYOUT_FRAGMENTPRODUCTDETAILDIALOGLISTDIALOG = 21;

  private static final int LAYOUT_FRAGMENTPRODUCTOFKIND = 22;

  private static final int LAYOUT_FRAGMENTPRODUCTRATING = 23;

  private static final int LAYOUT_FRAGMENTSEARCH = 24;

  private static final int LAYOUT_FRAGMENTUSERDETAIL = 25;

  private static final int LAYOUT_ITEMADDRESS = 26;

  private static final int LAYOUT_ITEMCART = 27;

  private static final int LAYOUT_ITEMCATEGORY = 28;

  private static final int LAYOUT_ITEMCHECKOUT = 29;

  private static final int LAYOUT_ITEMCHECKOUTCONFIRM = 30;

  private static final int LAYOUT_ITEMCOLORPROPERTY = 31;

  private static final int LAYOUT_ITEMDETAILPRODUCTIMAGEVIEWPAGER = 32;

  private static final int LAYOUT_ITEMFAVORITE = 33;

  private static final int LAYOUT_ITEMKIND = 34;

  private static final int LAYOUT_ITEMLEFTSIDEMESSAGE = 35;

  private static final int LAYOUT_ITEMPRODUCT = 36;

  private static final int LAYOUT_ITEMPRODUCTCOLORPROPERTY = 37;

  private static final int LAYOUT_ITEMPRODUCTSTRINGPROPERTY = 38;

  private static final int LAYOUT_ITEMRAKING = 39;

  private static final int LAYOUT_ITEMRATING = 40;

  private static final int LAYOUT_ITEMRATINGIMAGE = 41;

  private static final int LAYOUT_ITEMRIGHTSIDEMESSAGE = 42;

  private static final int LAYOUT_ITEMSTRINGPROPERTY = 43;

  private static final int LAYOUT_ITEMVIEWPAGERDETAILCATEGORY = 44;

  private static final int LAYOUT_ITEMVIEWPAPERCATEGORIES = 45;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(45);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.activity_category, LAYOUT_ACTIVITYCATEGORY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.activity_detail_product, LAYOUT_ACTIVITYDETAILPRODUCT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.activity_main, LAYOUT_ACTIVITYMAIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.activity_user_detail, LAYOUT_ACTIVITYUSERDETAIL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_address, LAYOUT_FRAGMENTADDRESS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_address_dialog, LAYOUT_FRAGMENTADDRESSDIALOG);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_all_favorite, LAYOUT_FRAGMENTALLFAVORITE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_available_favorite, LAYOUT_FRAGMENTAVAILABLEFAVORITE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_cart, LAYOUT_FRAGMENTCART);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_categories, LAYOUT_FRAGMENTCATEGORIES);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_check_out, LAYOUT_FRAGMENTCHECKOUT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_check_out_dialog, LAYOUT_FRAGMENTCHECKOUTDIALOG);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_detail_product, LAYOUT_FRAGMENTDETAILPRODUCT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_favorite, LAYOUT_FRAGMENTFAVORITE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_finish_check_out, LAYOUT_FRAGMENTFINISHCHECKOUT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_full_screen_image, LAYOUT_FRAGMENTFULLSCREENIMAGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_home, LAYOUT_FRAGMENTHOME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_kind, LAYOUT_FRAGMENTKIND);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_message, LAYOUT_FRAGMENTMESSAGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_person, LAYOUT_FRAGMENTPERSON);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_product_detail_dialog_list_dialog, LAYOUT_FRAGMENTPRODUCTDETAILDIALOGLISTDIALOG);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_product_of_kind, LAYOUT_FRAGMENTPRODUCTOFKIND);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_product_rating, LAYOUT_FRAGMENTPRODUCTRATING);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_search, LAYOUT_FRAGMENTSEARCH);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_user_detail, LAYOUT_FRAGMENTUSERDETAIL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_address, LAYOUT_ITEMADDRESS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_cart, LAYOUT_ITEMCART);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_category, LAYOUT_ITEMCATEGORY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_check_out, LAYOUT_ITEMCHECKOUT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_check_out_confirm, LAYOUT_ITEMCHECKOUTCONFIRM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_color_property, LAYOUT_ITEMCOLORPROPERTY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_detail_product_image_view_pager, LAYOUT_ITEMDETAILPRODUCTIMAGEVIEWPAGER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_favorite, LAYOUT_ITEMFAVORITE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_kind, LAYOUT_ITEMKIND);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_left_side_message, LAYOUT_ITEMLEFTSIDEMESSAGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_product, LAYOUT_ITEMPRODUCT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_product_color_property, LAYOUT_ITEMPRODUCTCOLORPROPERTY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_product_string_property, LAYOUT_ITEMPRODUCTSTRINGPROPERTY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_raking, LAYOUT_ITEMRAKING);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_rating, LAYOUT_ITEMRATING);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_rating_image, LAYOUT_ITEMRATINGIMAGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_right_side_message, LAYOUT_ITEMRIGHTSIDEMESSAGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_string_property, LAYOUT_ITEMSTRINGPROPERTY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_view_pager_detail_category, LAYOUT_ITEMVIEWPAGERDETAILCATEGORY);
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
        case  LAYOUT_ACTIVITYUSERDETAIL: {
          if ("layout/activity_user_detail_0".equals(tag)) {
            return new ActivityUserDetailBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_user_detail is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTADDRESS: {
          if ("layout/fragment_address_0".equals(tag)) {
            return new FragmentAddressBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_address is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTADDRESSDIALOG: {
          if ("layout/fragment_address_dialog_0".equals(tag)) {
            return new FragmentAddressDialogBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_address_dialog is invalid. Received: " + tag);
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
        case  LAYOUT_FRAGMENTCHECKOUT: {
          if ("layout/fragment_check_out_0".equals(tag)) {
            return new FragmentCheckOutBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_check_out is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTCHECKOUTDIALOG: {
          if ("layout/fragment_check_out_dialog_0".equals(tag)) {
            return new FragmentCheckOutDialogBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_check_out_dialog is invalid. Received: " + tag);
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
        case  LAYOUT_FRAGMENTFINISHCHECKOUT: {
          if ("layout/fragment_finish_check_out_0".equals(tag)) {
            return new FragmentFinishCheckOutBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_finish_check_out is invalid. Received: " + tag);
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
        case  LAYOUT_FRAGMENTKIND: {
          if ("layout/fragment_kind_0".equals(tag)) {
            return new FragmentKindBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_kind is invalid. Received: " + tag);
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
        case  LAYOUT_FRAGMENTPRODUCTOFKIND: {
          if ("layout/fragment_product_of_kind_0".equals(tag)) {
            return new FragmentProductOfKindBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_product_of_kind is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTPRODUCTRATING: {
          if ("layout/fragment_product_rating_0".equals(tag)) {
            return new FragmentProductRatingBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_product_rating is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTSEARCH: {
          if ("layout/fragment_search_0".equals(tag)) {
            return new FragmentSearchBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_search is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTUSERDETAIL: {
          if ("layout/fragment_user_detail_0".equals(tag)) {
            return new FragmentUserDetailBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_user_detail is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMADDRESS: {
          if ("layout/item_address_0".equals(tag)) {
            return new ItemAddressBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_address is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMCART: {
          if ("layout/item_cart_0".equals(tag)) {
            return new ItemCartBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_cart is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMCATEGORY: {
          if ("layout/item_category_0".equals(tag)) {
            return new ItemCategoryBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_category is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMCHECKOUT: {
          if ("layout/item_check_out_0".equals(tag)) {
            return new ItemCheckOutBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_check_out is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMCHECKOUTCONFIRM: {
          if ("layout/item_check_out_confirm_0".equals(tag)) {
            return new ItemCheckOutConfirmBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_check_out_confirm is invalid. Received: " + tag);
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
        case  LAYOUT_ITEMKIND: {
          if ("layout/item_kind_0".equals(tag)) {
            return new ItemKindBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_kind is invalid. Received: " + tag);
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
        case  LAYOUT_ITEMVIEWPAGERDETAILCATEGORY: {
          if ("layout/item_view_pager_detail_category_0".equals(tag)) {
            return new ItemViewPagerDetailCategoryBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_view_pager_detail_category is invalid. Received: " + tag);
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
    static final SparseArray<String> sKeys = new SparseArray<String>(16);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "address");
      sKeys.put(2, "cartItem");
      sKeys.put(3, "category");
      sKeys.put(4, "color");
      sKeys.put(5, "favoriteProduct");
      sKeys.put(6, "image");
      sKeys.put(7, "imgCategory");
      sKeys.put(8, "kind");
      sKeys.put(9, "messageItem");
      sKeys.put(10, "product");
      sKeys.put(11, "property");
      sKeys.put(12, "rankingProduct");
      sKeys.put(13, "rating");
      sKeys.put(14, "view");
      sKeys.put(15, "viewModel");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(45);

    static {
      sKeys.put("layout/activity_category_0", com.example.aposs_buyer.R.layout.activity_category);
      sKeys.put("layout/activity_detail_product_0", com.example.aposs_buyer.R.layout.activity_detail_product);
      sKeys.put("layout/activity_main_0", com.example.aposs_buyer.R.layout.activity_main);
      sKeys.put("layout/activity_user_detail_0", com.example.aposs_buyer.R.layout.activity_user_detail);
      sKeys.put("layout/fragment_address_0", com.example.aposs_buyer.R.layout.fragment_address);
      sKeys.put("layout/fragment_address_dialog_0", com.example.aposs_buyer.R.layout.fragment_address_dialog);
      sKeys.put("layout/fragment_all_favorite_0", com.example.aposs_buyer.R.layout.fragment_all_favorite);
      sKeys.put("layout/fragment_available_favorite_0", com.example.aposs_buyer.R.layout.fragment_available_favorite);
      sKeys.put("layout/fragment_cart_0", com.example.aposs_buyer.R.layout.fragment_cart);
      sKeys.put("layout/fragment_categories_0", com.example.aposs_buyer.R.layout.fragment_categories);
      sKeys.put("layout/fragment_check_out_0", com.example.aposs_buyer.R.layout.fragment_check_out);
      sKeys.put("layout/fragment_check_out_dialog_0", com.example.aposs_buyer.R.layout.fragment_check_out_dialog);
      sKeys.put("layout/fragment_detail_product_0", com.example.aposs_buyer.R.layout.fragment_detail_product);
      sKeys.put("layout/fragment_favorite_0", com.example.aposs_buyer.R.layout.fragment_favorite);
      sKeys.put("layout/fragment_finish_check_out_0", com.example.aposs_buyer.R.layout.fragment_finish_check_out);
      sKeys.put("layout/fragment_full_screen_image_0", com.example.aposs_buyer.R.layout.fragment_full_screen_image);
      sKeys.put("layout/fragment_home_0", com.example.aposs_buyer.R.layout.fragment_home);
      sKeys.put("layout/fragment_kind_0", com.example.aposs_buyer.R.layout.fragment_kind);
      sKeys.put("layout/fragment_message_0", com.example.aposs_buyer.R.layout.fragment_message);
      sKeys.put("layout/fragment_person_0", com.example.aposs_buyer.R.layout.fragment_person);
      sKeys.put("layout/fragment_product_detail_dialog_list_dialog_0", com.example.aposs_buyer.R.layout.fragment_product_detail_dialog_list_dialog);
      sKeys.put("layout/fragment_product_of_kind_0", com.example.aposs_buyer.R.layout.fragment_product_of_kind);
      sKeys.put("layout/fragment_product_rating_0", com.example.aposs_buyer.R.layout.fragment_product_rating);
      sKeys.put("layout/fragment_search_0", com.example.aposs_buyer.R.layout.fragment_search);
      sKeys.put("layout/fragment_user_detail_0", com.example.aposs_buyer.R.layout.fragment_user_detail);
      sKeys.put("layout/item_address_0", com.example.aposs_buyer.R.layout.item_address);
      sKeys.put("layout/item_cart_0", com.example.aposs_buyer.R.layout.item_cart);
      sKeys.put("layout/item_category_0", com.example.aposs_buyer.R.layout.item_category);
      sKeys.put("layout/item_check_out_0", com.example.aposs_buyer.R.layout.item_check_out);
      sKeys.put("layout/item_check_out_confirm_0", com.example.aposs_buyer.R.layout.item_check_out_confirm);
      sKeys.put("layout/item_color_property_0", com.example.aposs_buyer.R.layout.item_color_property);
      sKeys.put("layout/item_detail_product_image_view_pager_0", com.example.aposs_buyer.R.layout.item_detail_product_image_view_pager);
      sKeys.put("layout/item_favorite_0", com.example.aposs_buyer.R.layout.item_favorite);
      sKeys.put("layout/item_kind_0", com.example.aposs_buyer.R.layout.item_kind);
      sKeys.put("layout/item_left_side_message_0", com.example.aposs_buyer.R.layout.item_left_side_message);
      sKeys.put("layout/item_product_0", com.example.aposs_buyer.R.layout.item_product);
      sKeys.put("layout/item_product_color_property_0", com.example.aposs_buyer.R.layout.item_product_color_property);
      sKeys.put("layout/item_product_string_property_0", com.example.aposs_buyer.R.layout.item_product_string_property);
      sKeys.put("layout/item_raking_0", com.example.aposs_buyer.R.layout.item_raking);
      sKeys.put("layout/item_rating_0", com.example.aposs_buyer.R.layout.item_rating);
      sKeys.put("layout/item_rating_image_0", com.example.aposs_buyer.R.layout.item_rating_image);
      sKeys.put("layout/item_right_side_message_0", com.example.aposs_buyer.R.layout.item_right_side_message);
      sKeys.put("layout/item_string_property_0", com.example.aposs_buyer.R.layout.item_string_property);
      sKeys.put("layout/item_view_pager_detail_category_0", com.example.aposs_buyer.R.layout.item_view_pager_detail_category);
      sKeys.put("layout/item_view_paper_categories_0", com.example.aposs_buyer.R.layout.item_view_paper_categories);
    }
  }
}
