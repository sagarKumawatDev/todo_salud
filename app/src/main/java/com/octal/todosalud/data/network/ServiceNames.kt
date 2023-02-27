package com.octal.todosalud.data.network

/**
 * Created by KP on 1/4/2019.
 */
object ServiceNames {
    //    public static final String DEV_BASE_URL = "https://todosalud.webc.in/testing/api/";
    const val DEV_BASE_URL = "https://mobile-api.todosalud.tech/"

    // public static final String DEV_BASE_URL = "http://mobile-api.octaldev.com/";
    //    public static final String PROD_BASE_URL = "https://todosalud.webc.in/testing/api/";
    const val PROD_BASE_URL = "https://mobile-api.todosalud.tech/"

    //END POINTS
    const val GET_CONFIG = "prayers_reading_cate"
    const val GET_HOME = "prayers_reading_cate"
    const val LOGIN = "login"
    const val LOGOUT = "logout"
    const val IS_EMAIL_EXIST = "check-email-exists"
    const val NOTE_LIST = "note"
    const val CREATE_NOTE = "note"
    const val NOTE_DETAILS = "note/{id}"
    const val UPDATE_NOTE = "note/{id}"
    const val DELETE_NOTE = "note/{id}"
    const val STATE_LIST = "state"
    const val COUNTY_LIST = "county"
    const val ZONE_LIST = "zone"
    const val USER_PRESCRIPTION_LIST = "prescription"
    const val REQUEST_PRESCRIPTION_APPROVAL = "prescription-request-approval"
    const val USER_PRESCRIPTION_DETAILS = "prescription/{id}"
    const val CREATE_USER_PRESCRIPTION = "prescription"
    const val UPDATE_USER_PRESCRIPTION = "prescription/{id}"
    const val DELETE_USER_PRESCRIPTION = "prescription/{id}"
    const val USER_INSURANCE_LIST = "insurance"
    const val CREATE_USER_INSURANCE = "insurance"
    const val UPDATE_USER_INSURANCE = "insurance/{id}"
    const val INSURANCE_PROVIDER_LIST = "insurance_provider"
    const val SEARCH_INSURANCE_PROVIDER = "insurance_provider_search"
    const val CREATE_INSURANCE_PROVIDER = "insurance_provider"
    const val USER_LABORATORY_LIST = "laboratory"
    const val SEARCH_LABORATORY_LIST = "laboratory"
    const val CRATE_LABORATORY = "laboratory"
    const val UPDATE_LABORATORY = "laboratory/{id}"
    const val USER_LAB_RESULT_LIST = "lab_result"
    const val CREATE_USER_LAB_RESULT = "lab_result"
    const val PROFILE_CONDITIONS = "condition"
    const val REGISTER = "register"
    const val FORGOT_PASSWORD = "forgot-password"
    const val CHANGE_PASSWORD = "change-password"
    const val ADD_HEALTH_INDICATOR = "health-indicators"
    const val UPDATE_PROFILE = "profile/update"
    const val RESEND_EMAIL = "email/resend"
    const val SEND_FAMILY_INVITATION = "family"
    const val DEPENDANT = "dependent"
    const val DEPENDANT_EDIT = "dependent/{id}"
    const val USER_DETAILS = "user-details"
    const val RELATIONSHIPS = "relationships"
    const val LAB_APPOINTMENT = "lab_appointment"
    const val LAB_RESULTS = "lab_result"
    const val LAB_RESULTS_EDIT = "lab_result/{id}"
    const val CARE_GIVER_INVITE = "caregiver-invite"
    const val DOCTOR = "doctor"
    const val LAB_APPOINTMENT_EDIT = "lab_appointment/{id}"
    const val MEDICINE_SEARCH = "medicine-search"
    const val MEDICINE_TYPE = "medicine-types"
    const val MEDICINE_UNIT = "medicine-units"
    const val MEDICINE_VENDOR = "vendor"
    const val MEDICINE_CHEST = "medicine-chest"
    const val MEDICINE_CHEST_DELETE = "medicine-chest/{id}"
    const val PRESCRIPTION = "prescription"
    const val PRESCRIPTION_EDIT = "prescription/{id}"
    const val SOCIAL_LOGIN = "social-login"
    const val SOCIAL_LINK = "social-link"
    const val SOCIAL_UNLINK = "social-unlink"
    const val CART = "cart"
    const val ADD_TO_CART = "cart"
    const val CART_UPDATE = "cart/{id}"
    const val USER_ADDRESS = "user-address"
    const val USER_ADDRESS_EDIT = "user-address/{id}"
    const val HEALTH_INDIACTOR_ORDER = "health-indicator-types-ordering"
    const val HEALTH_INDIACTOR_NEW = "latest-health-indicators"
    const val GUEST_LOGIN = "guest-login"
    const val NOTIFICATION_SETTINGS = "notification-settings"
    const val NOTIFICATION_SETTINGS_UPDATE = "notification-settings/{id}"
    const val DOCTOR_APPOINTMENT = "doctor-appointment"
    const val DOCTOR_APPOINTMENT_SHOW = "doctor-appointment/{id}"
    const val AGENDA = "agenda"
    const val ADD_DOCTOR_PROFILE = "doctors/add-to-profile"
    const val USER_DOCTORS = "user-doctors"
    const val DOCTOR_EDIT = "doctor/{id}"
    const val NEWS_LIST = "news"
    const val NEWS_FAV_LIST = "news/favourite"
    const val RECURRING_MEDICINE_LIST = "medicine-chest-recurring"
    const val SWITCH_PROFILE = "switch-profile"
    const val PHARMACY = "pharmacy"
    const val MEDICINE_CATEGORY = "medicine-category/{id}"
    const val MEDICINE_DETAILS = "medicine/{id}"
    const val SYNC_CONDITION = "condition-sync"
    const val TAKE_OR_SKIP = "take-skip-dose/{id}"
    const val HEALTH_INDICATORS_TYPE = "health-indicator-types/{id}"
    const val EDIT_HEALTH_INDICATOR = "health-indicators/{id}"
    const val EDIT_HEALTH_SCHEDULE_TYPE = "health-indicator-schedule-types"
    const val EDIT_HEALTH_SCHEDULE_REMINDER = "health-indicator-reminder"
    const val SNOOZE_DOSE = "dose/{id}/snooze"
    const val DOCTOR_APPOINTMENT_REMINDER = "doctor-appointment/{id}/reminder"
    const val LAB_APPOINTMENT_REMINDER = "lab_appointment/{id}/reminder"
    const val ORDER_LIST = "orders"
    const val SINGLE_NOTE_DETAIL = "note/{id}"
    const val USER_NIT = "user-nit"
    const val USER_NIT_ID = "user-nit/{id}"
    const val DOCTOR_PRESCRIPTION = "doctor-prescriptions"
    const val DOCTOR_PRESCRIPTION_SHOW = "doctor-prescriptions/{id}"
    const val TAKE_DOSE_FROM_MEDICINE_CHEST = "medicine-chest/{id}/take-dose"
    const val ORDER_DETAILS = "orders/{id}"
    const val CREATE_ORDER = "orders"
    const val DOCTOR_PRESCRIPTION_ADD_TO_CART = "doctor-prescriptions/{id}/add-to-cart"
    const val ADD_EXISTING_DEPENDANT = "dependent/{id}/add"
    const val LAST_PURCHASED = "last-purchased-products"
    const val CHANGE_EMAIL = "change-email"
    const val FAMILY_MEMBER_APPROVAL = "family-approval"
    const val NOTIFICATION_LIST = "notifications"
    const val CARE_GIVER_APPROVAL = "caregiver-approval"
    const val PHARMACY_LIST_SEARCH = "medicine-search"
    const val SETTINGS_LIST = "settings"
    const val RECURRING_LIST = "medicine-chest-recurring"
    const val DEL_NOTIFICATION = "notifications/{id}"
    const val RECURRING_DATA_TO_CART = "recurring-purchase/add-to-cart"
    const val RECURRING_SNOOZE = "recurring-purchase/snooze"
    const val PATIENT_INVITATION = "patient-invitation/{id}"
    const val PRODUCT_VARIANT = "medicine/{id}/variations"
    const val MIGRATE_DATA = "migrate-data"
    const val VERIFY_OTP = "validate-otp"
    const val RESEND_OTP = "resend-otp"
    const val RESET_PASSWORD = "reset-password"
    const val AGENDA_LISTING = "agenda-list"
}