package com.example.aposs_buyer;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.example.aposs_buyer.databinding.ActivityAboutUsBindingImpl;
import com.example.aposs_buyer.databinding.ActivityCartBindingImpl;
import com.example.aposs_buyer.databinding.ActivityCategoriesBindingImpl;
import com.example.aposs_buyer.databinding.ActivityCategoryBindingImpl;
import com.example.aposs_buyer.databinding.ActivityDetailProductBindingImpl;
import com.example.aposs_buyer.databinding.ActivityMainBindingImpl;
import com.example.aposs_buyer.databinding.ActivityRatingBindingImpl;
import com.example.aposs_buyer.databinding.ActivityUserDetailBindingImpl;
import com.example.aposs_buyer.databinding.DialogYesNoOrderSuccessStatusBindingImpl;
import com.example.aposs_buyer.databinding.DistrictListItemBindingImpl;
import com.example.aposs_buyer.databinding.FragmentAboutUsBindingImpl;
import com.example.aposs_buyer.databinding.FragmentAddressBindingImpl;
import com.example.aposs_buyer.databinding.FragmentAddressDialogBindingImpl;
import com.example.aposs_buyer.databinding.FragmentAllFavoriteBindingImpl;
import com.example.aposs_buyer.databinding.FragmentAvailableFavoriteBindingImpl;
import com.example.aposs_buyer.databinding.FragmentCancelOrderBindingImpl;
import com.example.aposs_buyer.databinding.FragmentCancelOrderConfirmBindingImpl;
import com.example.aposs_buyer.databinding.FragmentCartBindingImpl;
import com.example.aposs_buyer.databinding.FragmentCategoriesBindingImpl;
import com.example.aposs_buyer.databinding.FragmentCategoryBindingImpl;
import com.example.aposs_buyer.databinding.FragmentCheckOutBindingImpl;
import com.example.aposs_buyer.databinding.FragmentCheckOutDialogBindingImpl;
import com.example.aposs_buyer.databinding.FragmentDetailOrderBindingImpl;
import com.example.aposs_buyer.databinding.FragmentDetailProductBindingImpl;
import com.example.aposs_buyer.databinding.FragmentFavoriteBindingImpl;
import com.example.aposs_buyer.databinding.FragmentFinishCheckOutBindingImpl;
import com.example.aposs_buyer.databinding.FragmentFullScreenImageBindingImpl;
import com.example.aposs_buyer.databinding.FragmentHomeBindingImpl;
import com.example.aposs_buyer.databinding.FragmentKindBindingImpl;
import com.example.aposs_buyer.databinding.FragmentLoginBindingImpl;
import com.example.aposs_buyer.databinding.FragmentMessageBindingImpl;
import com.example.aposs_buyer.databinding.FragmentNotificationBindingImpl;
import com.example.aposs_buyer.databinding.FragmentOrderBindingImpl;
import com.example.aposs_buyer.databinding.FragmentPersonBindingImpl;
import com.example.aposs_buyer.databinding.FragmentProductDetailDialogListDialogBindingImpl;
import com.example.aposs_buyer.databinding.FragmentProductRatingBindingImpl;
import com.example.aposs_buyer.databinding.FragmentRateNowBindingImpl;
import com.example.aposs_buyer.databinding.FragmentRatedBindingImpl;
import com.example.aposs_buyer.databinding.FragmentRatingBindingImpl;
import com.example.aposs_buyer.databinding.FragmentSearchBindingImpl;
import com.example.aposs_buyer.databinding.FragmentSignUpBindingImpl;
import com.example.aposs_buyer.databinding.FragmentUserDetailBindingImpl;
import com.example.aposs_buyer.databinding.FragmentVerifyBindingImpl;
import com.example.aposs_buyer.databinding.ItemAddressBindingImpl;
import com.example.aposs_buyer.databinding.ItemCartBindingImpl;
import com.example.aposs_buyer.databinding.ItemCategoryBindingImpl;
import com.example.aposs_buyer.databinding.ItemCheckOutBindingImpl;
import com.example.aposs_buyer.databinding.ItemCheckOutConfirmBindingImpl;
import com.example.aposs_buyer.databinding.ItemColorPropertyBindingImpl;
import com.example.aposs_buyer.databinding.ItemDetailProductImageViewPagerBindingImpl;
import com.example.aposs_buyer.databinding.ItemFavoriteBindingImpl;
import com.example.aposs_buyer.databinding.ItemImgAddRatingBindingImpl;
import com.example.aposs_buyer.databinding.ItemImgRatingBindingImpl;
import com.example.aposs_buyer.databinding.ItemKindBindingImpl;
import com.example.aposs_buyer.databinding.ItemLeftSideMessageBindingImpl;
import com.example.aposs_buyer.databinding.ItemNeedRatingBindingImpl;
import com.example.aposs_buyer.databinding.ItemNotificationBindingImpl;
import com.example.aposs_buyer.databinding.ItemOrderBilingBindingImpl;
import com.example.aposs_buyer.databinding.ItemOrderBindingImpl;
import com.example.aposs_buyer.databinding.ItemOrderDeliveringStateBindingImpl;
import com.example.aposs_buyer.databinding.ItemProductBindingImpl;
import com.example.aposs_buyer.databinding.ItemProductColorPropertyBindingImpl;
import com.example.aposs_buyer.databinding.ItemProductStringPropertyBindingImpl;
import com.example.aposs_buyer.databinding.ItemRakingBindingImpl;
import com.example.aposs_buyer.databinding.ItemRatedBindingImpl;
import com.example.aposs_buyer.databinding.ItemRatingBindingImpl;
import com.example.aposs_buyer.databinding.ItemRatingImageBindingImpl;
import com.example.aposs_buyer.databinding.ItemRightSideMessageBindingImpl;
import com.example.aposs_buyer.databinding.ItemStringPropertyBindingImpl;
import com.example.aposs_buyer.databinding.ItemViewPagerDetailCategoryBindingImpl;
import com.example.aposs_buyer.databinding.ItemViewPaperCategoriesBindingImpl;
import com.example.aposs_buyer.databinding.ProvinceListItemBindingImpl;
import com.example.aposs_buyer.databinding.WardListItemBindingImpl;
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
  private static final int LAYOUT_ACTIVITYABOUTUS = 1;

  private static final int LAYOUT_ACTIVITYCART = 2;

  private static final int LAYOUT_ACTIVITYCATEGORIES = 3;

  private static final int LAYOUT_ACTIVITYCATEGORY = 4;

  private static final int LAYOUT_ACTIVITYDETAILPRODUCT = 5;

  private static final int LAYOUT_ACTIVITYMAIN = 6;

  private static final int LAYOUT_ACTIVITYRATING = 7;

  private static final int LAYOUT_ACTIVITYUSERDETAIL = 8;

  private static final int LAYOUT_DIALOGYESNOORDERSUCCESSSTATUS = 9;

  private static final int LAYOUT_DISTRICTLISTITEM = 10;

  private static final int LAYOUT_FRAGMENTABOUTUS = 11;

  private static final int LAYOUT_FRAGMENTADDRESS = 12;

  private static final int LAYOUT_FRAGMENTADDRESSDIALOG = 13;

  private static final int LAYOUT_FRAGMENTALLFAVORITE = 14;

  private static final int LAYOUT_FRAGMENTAVAILABLEFAVORITE = 15;

  private static final int LAYOUT_FRAGMENTCANCELORDER = 16;

  private static final int LAYOUT_FRAGMENTCANCELORDERCONFIRM = 17;

  private static final int LAYOUT_FRAGMENTCART = 18;

  private static final int LAYOUT_FRAGMENTCATEGORIES = 19;

  private static final int LAYOUT_FRAGMENTCATEGORY = 20;

  private static final int LAYOUT_FRAGMENTCHECKOUT = 21;

  private static final int LAYOUT_FRAGMENTCHECKOUTDIALOG = 22;

  private static final int LAYOUT_FRAGMENTDETAILORDER = 23;

  private static final int LAYOUT_FRAGMENTDETAILPRODUCT = 24;

  private static final int LAYOUT_FRAGMENTFAVORITE = 25;

  private static final int LAYOUT_FRAGMENTFINISHCHECKOUT = 26;

  private static final int LAYOUT_FRAGMENTFULLSCREENIMAGE = 27;

  private static final int LAYOUT_FRAGMENTHOME = 28;

  private static final int LAYOUT_FRAGMENTKIND = 29;

  private static final int LAYOUT_FRAGMENTLOGIN = 30;

  private static final int LAYOUT_FRAGMENTMESSAGE = 31;

  private static final int LAYOUT_FRAGMENTNOTIFICATION = 32;

  private static final int LAYOUT_FRAGMENTORDER = 33;

  private static final int LAYOUT_FRAGMENTPERSON = 34;

  private static final int LAYOUT_FRAGMENTPRODUCTDETAILDIALOGLISTDIALOG = 35;

  private static final int LAYOUT_FRAGMENTPRODUCTRATING = 36;

  private static final int LAYOUT_FRAGMENTRATENOW = 37;

  private static final int LAYOUT_FRAGMENTRATED = 38;

  private static final int LAYOUT_FRAGMENTRATING = 39;

  private static final int LAYOUT_FRAGMENTSEARCH = 40;

  private static final int LAYOUT_FRAGMENTSIGNUP = 41;

  private static final int LAYOUT_FRAGMENTUSERDETAIL = 42;

  private static final int LAYOUT_FRAGMENTVERIFY = 43;

  private static final int LAYOUT_ITEMADDRESS = 44;

  private static final int LAYOUT_ITEMCART = 45;

  private static final int LAYOUT_ITEMCATEGORY = 46;

  private static final int LAYOUT_ITEMCHECKOUT = 47;

  private static final int LAYOUT_ITEMCHECKOUTCONFIRM = 48;

  private static final int LAYOUT_ITEMCOLORPROPERTY = 49;

  private static final int LAYOUT_ITEMDETAILPRODUCTIMAGEVIEWPAGER = 50;

  private static final int LAYOUT_ITEMFAVORITE = 51;

  private static final int LAYOUT_ITEMIMGADDRATING = 52;

  private static final int LAYOUT_ITEMIMGRATING = 53;

  private static final int LAYOUT_ITEMKIND = 54;

  private static final int LAYOUT_ITEMLEFTSIDEMESSAGE = 55;

  private static final int LAYOUT_ITEMNEEDRATING = 56;

  private static final int LAYOUT_ITEMNOTIFICATION = 57;

  private static final int LAYOUT_ITEMORDER = 58;

  private static final int LAYOUT_ITEMORDERBILING = 59;

  private static final int LAYOUT_ITEMORDERDELIVERINGSTATE = 60;

  private static final int LAYOUT_ITEMPRODUCT = 61;

  private static final int LAYOUT_ITEMPRODUCTCOLORPROPERTY = 62;

  private static final int LAYOUT_ITEMPRODUCTSTRINGPROPERTY = 63;

  private static final int LAYOUT_ITEMRAKING = 64;

  private static final int LAYOUT_ITEMRATED = 65;

  private static final int LAYOUT_ITEMRATING = 66;

  private static final int LAYOUT_ITEMRATINGIMAGE = 67;

  private static final int LAYOUT_ITEMRIGHTSIDEMESSAGE = 68;

  private static final int LAYOUT_ITEMSTRINGPROPERTY = 69;

  private static final int LAYOUT_ITEMVIEWPAGERDETAILCATEGORY = 70;

  private static final int LAYOUT_ITEMVIEWPAPERCATEGORIES = 71;

  private static final int LAYOUT_PROVINCELISTITEM = 72;

  private static final int LAYOUT_WARDLISTITEM = 73;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(73);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.activity_about_us, LAYOUT_ACTIVITYABOUTUS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.activity_cart, LAYOUT_ACTIVITYCART);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.activity_categories, LAYOUT_ACTIVITYCATEGORIES);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.activity_category, LAYOUT_ACTIVITYCATEGORY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.activity_detail_product, LAYOUT_ACTIVITYDETAILPRODUCT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.activity_main, LAYOUT_ACTIVITYMAIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.activity_rating, LAYOUT_ACTIVITYRATING);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.activity_user_detail, LAYOUT_ACTIVITYUSERDETAIL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.dialog_yes_no_order_success_status, LAYOUT_DIALOGYESNOORDERSUCCESSSTATUS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.district_list_item, LAYOUT_DISTRICTLISTITEM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_about_us, LAYOUT_FRAGMENTABOUTUS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_address, LAYOUT_FRAGMENTADDRESS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_address_dialog, LAYOUT_FRAGMENTADDRESSDIALOG);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_all_favorite, LAYOUT_FRAGMENTALLFAVORITE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_available_favorite, LAYOUT_FRAGMENTAVAILABLEFAVORITE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_cancel_order, LAYOUT_FRAGMENTCANCELORDER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_cancel_order_confirm, LAYOUT_FRAGMENTCANCELORDERCONFIRM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_cart, LAYOUT_FRAGMENTCART);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_categories, LAYOUT_FRAGMENTCATEGORIES);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_category, LAYOUT_FRAGMENTCATEGORY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_check_out, LAYOUT_FRAGMENTCHECKOUT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_check_out_dialog, LAYOUT_FRAGMENTCHECKOUTDIALOG);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_detail_order, LAYOUT_FRAGMENTDETAILORDER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_detail_product, LAYOUT_FRAGMENTDETAILPRODUCT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_favorite, LAYOUT_FRAGMENTFAVORITE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_finish_check_out, LAYOUT_FRAGMENTFINISHCHECKOUT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_full_screen_image, LAYOUT_FRAGMENTFULLSCREENIMAGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_home, LAYOUT_FRAGMENTHOME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_kind, LAYOUT_FRAGMENTKIND);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_login, LAYOUT_FRAGMENTLOGIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_message, LAYOUT_FRAGMENTMESSAGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_notification, LAYOUT_FRAGMENTNOTIFICATION);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_order, LAYOUT_FRAGMENTORDER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_person, LAYOUT_FRAGMENTPERSON);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_product_detail_dialog_list_dialog, LAYOUT_FRAGMENTPRODUCTDETAILDIALOGLISTDIALOG);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_product_rating, LAYOUT_FRAGMENTPRODUCTRATING);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_rate_now, LAYOUT_FRAGMENTRATENOW);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_rated, LAYOUT_FRAGMENTRATED);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_rating, LAYOUT_FRAGMENTRATING);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_search, LAYOUT_FRAGMENTSEARCH);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_sign_up, LAYOUT_FRAGMENTSIGNUP);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_user_detail, LAYOUT_FRAGMENTUSERDETAIL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.fragment_verify, LAYOUT_FRAGMENTVERIFY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_address, LAYOUT_ITEMADDRESS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_cart, LAYOUT_ITEMCART);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_category, LAYOUT_ITEMCATEGORY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_check_out, LAYOUT_ITEMCHECKOUT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_check_out_confirm, LAYOUT_ITEMCHECKOUTCONFIRM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_color_property, LAYOUT_ITEMCOLORPROPERTY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_detail_product_image_view_pager, LAYOUT_ITEMDETAILPRODUCTIMAGEVIEWPAGER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_favorite, LAYOUT_ITEMFAVORITE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_img_add_rating, LAYOUT_ITEMIMGADDRATING);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_img_rating, LAYOUT_ITEMIMGRATING);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_kind, LAYOUT_ITEMKIND);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_left_side_message, LAYOUT_ITEMLEFTSIDEMESSAGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_need_rating, LAYOUT_ITEMNEEDRATING);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_notification, LAYOUT_ITEMNOTIFICATION);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_order, LAYOUT_ITEMORDER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_order_biling, LAYOUT_ITEMORDERBILING);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_order_delivering_state, LAYOUT_ITEMORDERDELIVERINGSTATE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_product, LAYOUT_ITEMPRODUCT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_product_color_property, LAYOUT_ITEMPRODUCTCOLORPROPERTY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_product_string_property, LAYOUT_ITEMPRODUCTSTRINGPROPERTY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_raking, LAYOUT_ITEMRAKING);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_rated, LAYOUT_ITEMRATED);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_rating, LAYOUT_ITEMRATING);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_rating_image, LAYOUT_ITEMRATINGIMAGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_right_side_message, LAYOUT_ITEMRIGHTSIDEMESSAGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_string_property, LAYOUT_ITEMSTRINGPROPERTY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_view_pager_detail_category, LAYOUT_ITEMVIEWPAGERDETAILCATEGORY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.item_view_paper_categories, LAYOUT_ITEMVIEWPAPERCATEGORIES);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.province_list_item, LAYOUT_PROVINCELISTITEM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.aposs_buyer.R.layout.ward_list_item, LAYOUT_WARDLISTITEM);
  }

  private final ViewDataBinding internalGetViewDataBinding0(DataBindingComponent component,
      View view, int internalId, Object tag) {
    switch(internalId) {
      case  LAYOUT_ACTIVITYABOUTUS: {
        if ("layout/activity_about_us_0".equals(tag)) {
          return new ActivityAboutUsBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_about_us is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYCART: {
        if ("layout/activity_cart_0".equals(tag)) {
          return new ActivityCartBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_cart is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYCATEGORIES: {
        if ("layout/activity_categories_0".equals(tag)) {
          return new ActivityCategoriesBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_categories is invalid. Received: " + tag);
      }
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
      case  LAYOUT_ACTIVITYRATING: {
        if ("layout/activity_rating_0".equals(tag)) {
          return new ActivityRatingBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_rating is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYUSERDETAIL: {
        if ("layout/activity_user_detail_0".equals(tag)) {
          return new ActivityUserDetailBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_user_detail is invalid. Received: " + tag);
      }
      case  LAYOUT_DIALOGYESNOORDERSUCCESSSTATUS: {
        if ("layout/dialog_yes_no_order_success_status_0".equals(tag)) {
          return new DialogYesNoOrderSuccessStatusBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for dialog_yes_no_order_success_status is invalid. Received: " + tag);
      }
      case  LAYOUT_DISTRICTLISTITEM: {
        if ("layout/district_list_item_0".equals(tag)) {
          return new DistrictListItemBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for district_list_item is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTABOUTUS: {
        if ("layout/fragment_about_us_0".equals(tag)) {
          return new FragmentAboutUsBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_about_us is invalid. Received: " + tag);
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
      case  LAYOUT_FRAGMENTCANCELORDER: {
        if ("layout/fragment_cancel_order_0".equals(tag)) {
          return new FragmentCancelOrderBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_cancel_order is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTCANCELORDERCONFIRM: {
        if ("layout/fragment_cancel_order_confirm_0".equals(tag)) {
          return new FragmentCancelOrderConfirmBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_cancel_order_confirm is invalid. Received: " + tag);
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
      case  LAYOUT_FRAGMENTCATEGORY: {
        if ("layout/fragment_category_0".equals(tag)) {
          return new FragmentCategoryBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_category is invalid. Received: " + tag);
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
      case  LAYOUT_FRAGMENTDETAILORDER: {
        if ("layout/fragment_detail_order_0".equals(tag)) {
          return new FragmentDetailOrderBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_detail_order is invalid. Received: " + tag);
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
      case  LAYOUT_FRAGMENTLOGIN: {
        if ("layout/fragment_login_0".equals(tag)) {
          return new FragmentLoginBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_login is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTMESSAGE: {
        if ("layout/fragment_message_0".equals(tag)) {
          return new FragmentMessageBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_message is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTNOTIFICATION: {
        if ("layout/fragment_notification_0".equals(tag)) {
          return new FragmentNotificationBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_notification is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTORDER: {
        if ("layout/fragment_order_0".equals(tag)) {
          return new FragmentOrderBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_order is invalid. Received: " + tag);
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
      case  LAYOUT_FRAGMENTRATENOW: {
        if ("layout/fragment_rate_now_0".equals(tag)) {
          return new FragmentRateNowBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_rate_now is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTRATED: {
        if ("layout/fragment_rated_0".equals(tag)) {
          return new FragmentRatedBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_rated is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTRATING: {
        if ("layout/fragment_rating_0".equals(tag)) {
          return new FragmentRatingBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_rating is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTSEARCH: {
        if ("layout/fragment_search_0".equals(tag)) {
          return new FragmentSearchBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_search is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTSIGNUP: {
        if ("layout/fragment_sign_up_0".equals(tag)) {
          return new FragmentSignUpBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_sign_up is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTUSERDETAIL: {
        if ("layout/fragment_user_detail_0".equals(tag)) {
          return new FragmentUserDetailBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_user_detail is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTVERIFY: {
        if ("layout/fragment_verify_0".equals(tag)) {
          return new FragmentVerifyBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_verify is invalid. Received: " + tag);
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
    }
    return null;
  }

  private final ViewDataBinding internalGetViewDataBinding1(DataBindingComponent component,
      View view, int internalId, Object tag) {
    switch(internalId) {
      case  LAYOUT_ITEMFAVORITE: {
        if ("layout/item_favorite_0".equals(tag)) {
          return new ItemFavoriteBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_favorite is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMIMGADDRATING: {
        if ("layout/item_img_add_rating_0".equals(tag)) {
          return new ItemImgAddRatingBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_img_add_rating is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMIMGRATING: {
        if ("layout/item_img_rating_0".equals(tag)) {
          return new ItemImgRatingBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_img_rating is invalid. Received: " + tag);
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
      case  LAYOUT_ITEMNEEDRATING: {
        if ("layout/item_need_rating_0".equals(tag)) {
          return new ItemNeedRatingBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_need_rating is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMNOTIFICATION: {
        if ("layout/item_notification_0".equals(tag)) {
          return new ItemNotificationBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_notification is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMORDER: {
        if ("layout/item_order_0".equals(tag)) {
          return new ItemOrderBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_order is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMORDERBILING: {
        if ("layout/item_order_biling_0".equals(tag)) {
          return new ItemOrderBilingBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_order_biling is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMORDERDELIVERINGSTATE: {
        if ("layout/item_order_delivering_state_0".equals(tag)) {
          return new ItemOrderDeliveringStateBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_order_delivering_state is invalid. Received: " + tag);
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
      case  LAYOUT_ITEMRATED: {
        if ("layout/item_rated_0".equals(tag)) {
          return new ItemRatedBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_rated is invalid. Received: " + tag);
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
      case  LAYOUT_PROVINCELISTITEM: {
        if ("layout/province_list_item_0".equals(tag)) {
          return new ProvinceListItemBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for province_list_item is invalid. Received: " + tag);
      }
      case  LAYOUT_WARDLISTITEM: {
        if ("layout/ward_list_item_0".equals(tag)) {
          return new WardListItemBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for ward_list_item is invalid. Received: " + tag);
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      // find which method will have it. -1 is necessary becausefirst id starts with 1;
      int methodIndex = (localizedLayoutId - 1) / 50;
      switch(methodIndex) {
        case 0: {
          return internalGetViewDataBinding0(component, view, localizedLayoutId, tag);
        }
        case 1: {
          return internalGetViewDataBinding1(component, view, localizedLayoutId, tag);
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
    static final SparseArray<String> sKeys = new SparseArray<String>(26);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "address");
      sKeys.put(2, "billingItem");
      sKeys.put(3, "cartItem");
      sKeys.put(4, "category");
      sKeys.put(5, "color");
      sKeys.put(6, "converter");
      sKeys.put(7, "favoriteProduct");
      sKeys.put(8, "image");
      sKeys.put(9, "imgCategory");
      sKeys.put(10, "kind");
      sKeys.put(11, "loadingStatus");
      sKeys.put(12, "messageItem");
      sKeys.put(13, "notification");
      sKeys.put(14, "order");
      sKeys.put(15, "orderDeliveringState");
      sKeys.put(16, "product");
      sKeys.put(17, "property");
      sKeys.put(18, "rankingProduct");
      sKeys.put(19, "rateImage");
      sKeys.put(20, "rateNowItem");
      sKeys.put(21, "ratedItem");
      sKeys.put(22, "rating");
      sKeys.put(23, "status");
      sKeys.put(24, "view");
      sKeys.put(25, "viewModel");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(73);

    static {
      sKeys.put("layout/activity_about_us_0", com.example.aposs_buyer.R.layout.activity_about_us);
      sKeys.put("layout/activity_cart_0", com.example.aposs_buyer.R.layout.activity_cart);
      sKeys.put("layout/activity_categories_0", com.example.aposs_buyer.R.layout.activity_categories);
      sKeys.put("layout/activity_category_0", com.example.aposs_buyer.R.layout.activity_category);
      sKeys.put("layout/activity_detail_product_0", com.example.aposs_buyer.R.layout.activity_detail_product);
      sKeys.put("layout/activity_main_0", com.example.aposs_buyer.R.layout.activity_main);
      sKeys.put("layout/activity_rating_0", com.example.aposs_buyer.R.layout.activity_rating);
      sKeys.put("layout/activity_user_detail_0", com.example.aposs_buyer.R.layout.activity_user_detail);
      sKeys.put("layout/dialog_yes_no_order_success_status_0", com.example.aposs_buyer.R.layout.dialog_yes_no_order_success_status);
      sKeys.put("layout/district_list_item_0", com.example.aposs_buyer.R.layout.district_list_item);
      sKeys.put("layout/fragment_about_us_0", com.example.aposs_buyer.R.layout.fragment_about_us);
      sKeys.put("layout/fragment_address_0", com.example.aposs_buyer.R.layout.fragment_address);
      sKeys.put("layout/fragment_address_dialog_0", com.example.aposs_buyer.R.layout.fragment_address_dialog);
      sKeys.put("layout/fragment_all_favorite_0", com.example.aposs_buyer.R.layout.fragment_all_favorite);
      sKeys.put("layout/fragment_available_favorite_0", com.example.aposs_buyer.R.layout.fragment_available_favorite);
      sKeys.put("layout/fragment_cancel_order_0", com.example.aposs_buyer.R.layout.fragment_cancel_order);
      sKeys.put("layout/fragment_cancel_order_confirm_0", com.example.aposs_buyer.R.layout.fragment_cancel_order_confirm);
      sKeys.put("layout/fragment_cart_0", com.example.aposs_buyer.R.layout.fragment_cart);
      sKeys.put("layout/fragment_categories_0", com.example.aposs_buyer.R.layout.fragment_categories);
      sKeys.put("layout/fragment_category_0", com.example.aposs_buyer.R.layout.fragment_category);
      sKeys.put("layout/fragment_check_out_0", com.example.aposs_buyer.R.layout.fragment_check_out);
      sKeys.put("layout/fragment_check_out_dialog_0", com.example.aposs_buyer.R.layout.fragment_check_out_dialog);
      sKeys.put("layout/fragment_detail_order_0", com.example.aposs_buyer.R.layout.fragment_detail_order);
      sKeys.put("layout/fragment_detail_product_0", com.example.aposs_buyer.R.layout.fragment_detail_product);
      sKeys.put("layout/fragment_favorite_0", com.example.aposs_buyer.R.layout.fragment_favorite);
      sKeys.put("layout/fragment_finish_check_out_0", com.example.aposs_buyer.R.layout.fragment_finish_check_out);
      sKeys.put("layout/fragment_full_screen_image_0", com.example.aposs_buyer.R.layout.fragment_full_screen_image);
      sKeys.put("layout/fragment_home_0", com.example.aposs_buyer.R.layout.fragment_home);
      sKeys.put("layout/fragment_kind_0", com.example.aposs_buyer.R.layout.fragment_kind);
      sKeys.put("layout/fragment_login_0", com.example.aposs_buyer.R.layout.fragment_login);
      sKeys.put("layout/fragment_message_0", com.example.aposs_buyer.R.layout.fragment_message);
      sKeys.put("layout/fragment_notification_0", com.example.aposs_buyer.R.layout.fragment_notification);
      sKeys.put("layout/fragment_order_0", com.example.aposs_buyer.R.layout.fragment_order);
      sKeys.put("layout/fragment_person_0", com.example.aposs_buyer.R.layout.fragment_person);
      sKeys.put("layout/fragment_product_detail_dialog_list_dialog_0", com.example.aposs_buyer.R.layout.fragment_product_detail_dialog_list_dialog);
      sKeys.put("layout/fragment_product_rating_0", com.example.aposs_buyer.R.layout.fragment_product_rating);
      sKeys.put("layout/fragment_rate_now_0", com.example.aposs_buyer.R.layout.fragment_rate_now);
      sKeys.put("layout/fragment_rated_0", com.example.aposs_buyer.R.layout.fragment_rated);
      sKeys.put("layout/fragment_rating_0", com.example.aposs_buyer.R.layout.fragment_rating);
      sKeys.put("layout/fragment_search_0", com.example.aposs_buyer.R.layout.fragment_search);
      sKeys.put("layout/fragment_sign_up_0", com.example.aposs_buyer.R.layout.fragment_sign_up);
      sKeys.put("layout/fragment_user_detail_0", com.example.aposs_buyer.R.layout.fragment_user_detail);
      sKeys.put("layout/fragment_verify_0", com.example.aposs_buyer.R.layout.fragment_verify);
      sKeys.put("layout/item_address_0", com.example.aposs_buyer.R.layout.item_address);
      sKeys.put("layout/item_cart_0", com.example.aposs_buyer.R.layout.item_cart);
      sKeys.put("layout/item_category_0", com.example.aposs_buyer.R.layout.item_category);
      sKeys.put("layout/item_check_out_0", com.example.aposs_buyer.R.layout.item_check_out);
      sKeys.put("layout/item_check_out_confirm_0", com.example.aposs_buyer.R.layout.item_check_out_confirm);
      sKeys.put("layout/item_color_property_0", com.example.aposs_buyer.R.layout.item_color_property);
      sKeys.put("layout/item_detail_product_image_view_pager_0", com.example.aposs_buyer.R.layout.item_detail_product_image_view_pager);
      sKeys.put("layout/item_favorite_0", com.example.aposs_buyer.R.layout.item_favorite);
      sKeys.put("layout/item_img_add_rating_0", com.example.aposs_buyer.R.layout.item_img_add_rating);
      sKeys.put("layout/item_img_rating_0", com.example.aposs_buyer.R.layout.item_img_rating);
      sKeys.put("layout/item_kind_0", com.example.aposs_buyer.R.layout.item_kind);
      sKeys.put("layout/item_left_side_message_0", com.example.aposs_buyer.R.layout.item_left_side_message);
      sKeys.put("layout/item_need_rating_0", com.example.aposs_buyer.R.layout.item_need_rating);
      sKeys.put("layout/item_notification_0", com.example.aposs_buyer.R.layout.item_notification);
      sKeys.put("layout/item_order_0", com.example.aposs_buyer.R.layout.item_order);
      sKeys.put("layout/item_order_biling_0", com.example.aposs_buyer.R.layout.item_order_biling);
      sKeys.put("layout/item_order_delivering_state_0", com.example.aposs_buyer.R.layout.item_order_delivering_state);
      sKeys.put("layout/item_product_0", com.example.aposs_buyer.R.layout.item_product);
      sKeys.put("layout/item_product_color_property_0", com.example.aposs_buyer.R.layout.item_product_color_property);
      sKeys.put("layout/item_product_string_property_0", com.example.aposs_buyer.R.layout.item_product_string_property);
      sKeys.put("layout/item_raking_0", com.example.aposs_buyer.R.layout.item_raking);
      sKeys.put("layout/item_rated_0", com.example.aposs_buyer.R.layout.item_rated);
      sKeys.put("layout/item_rating_0", com.example.aposs_buyer.R.layout.item_rating);
      sKeys.put("layout/item_rating_image_0", com.example.aposs_buyer.R.layout.item_rating_image);
      sKeys.put("layout/item_right_side_message_0", com.example.aposs_buyer.R.layout.item_right_side_message);
      sKeys.put("layout/item_string_property_0", com.example.aposs_buyer.R.layout.item_string_property);
      sKeys.put("layout/item_view_pager_detail_category_0", com.example.aposs_buyer.R.layout.item_view_pager_detail_category);
      sKeys.put("layout/item_view_paper_categories_0", com.example.aposs_buyer.R.layout.item_view_paper_categories);
      sKeys.put("layout/province_list_item_0", com.example.aposs_buyer.R.layout.province_list_item);
      sKeys.put("layout/ward_list_item_0", com.example.aposs_buyer.R.layout.ward_list_item);
    }
  }
}
