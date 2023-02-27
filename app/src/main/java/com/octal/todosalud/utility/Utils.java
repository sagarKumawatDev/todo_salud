package com.octal.todosalud.utility;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;

import androidx.core.content.FileProvider;

import com.octal.todosalud.BuildConfig;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created By Sibin On 1/28/2020.
 */
public class Utils {


    public static Date convertStingToDate(String dateString) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("es"));
        try {
            Date date = format.parse(dateString);

            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }
    public static Date convertStingToDateOnly(String dateString) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", new Locale("es"));
        try {
            Date date = format.parse(dateString);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }


    public static String[] getDateAndTimeFromDate(Date createdAt) {

        String[] datearray = new String[2];


        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy", new Locale("es"));
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a", new Locale("es"));

        datearray[0] = dateFormat.format(createdAt);
        datearray[1] = timeFormat.format(createdAt);


        return datearray;

    }

    public static String getDateFromDateOnly(Date createdAt) {

        String newDate = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy", new Locale("es"));
        newDate = dateFormat.format(createdAt);
        return newDate;

    }


    public static String[] getDateAndTimeFromDateNew(Date createdAt) {

        String[] datearray = new String[2];


        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", new Locale("es"));
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a", new Locale("es"));

        datearray[0] = dateFormat.format(createdAt);
        datearray[1] = timeFormat.format(createdAt);


        return datearray;

    }

    public static String convert24hrsTo12Hrs(int hour, int minute) {
        System.out.println("@houre" + hour + " " + minute);
        int hourData;
        String minuteData;
        String finalString = null;
        String AM_OR_PM = null;
        //  ((Integer.parseInt(times[0]) > 12) ? Integer.parseInt(times[0]) % 12 : Integer.parseInt(times[0])) + ":" + (Integer.parseInt(times[1]) < 10 ? ("0" + Integer.parseInt(times[1])) : Integer.parseInt(times[1])) + " " + ((Integer.parseInt(times[0]) >= 12) ? "PM" : "AM");
        if (hour > 12) {
            hourData = hour % 12;
        } else if (hour < 12) {
            hourData = hour;
        } else {
            hourData = 12;
        }

        if (minute < 10) {
            minuteData = "0" + minute;
        } else {
            minuteData = "" + minute;
        }
        if (hour >= 12) {
            AM_OR_PM = "PM";
        } else {
            AM_OR_PM = "AM";
        }
        if (hourData == 0) {
            hourData = 12;
        }
        if (hourData < 10) {
            finalString = "0" + hourData + ":" + minuteData + ":" + AM_OR_PM;
        } else {
            finalString = hourData + ":" + minuteData + ":" + AM_OR_PM;
        }
        return finalString;
    }

    public static String convert24hrsTo12HrsNew(int hour, int minute) {
        System.out.println("@houre" + hour + " " + minute);
        int hourData;
        String minuteData;
        String finalString = null;
        String AM_OR_PM = null;
        //  ((Integer.parseInt(times[0]) > 12) ? Integer.parseInt(times[0]) % 12 : Integer.parseInt(times[0])) + ":" + (Integer.parseInt(times[1]) < 10 ? ("0" + Integer.parseInt(times[1])) : Integer.parseInt(times[1])) + " " + ((Integer.parseInt(times[0]) >= 12) ? "PM" : "AM");
        if (hour > 12) {
            hourData = hour % 12;
        } else if (hour < 12) {
            hourData = hour;
        } else {
            hourData = 12;
        }

        if (minute < 10) {
            minuteData = "0" + minute;
        } else {
            minuteData = "" + minute;
        }
        if (hour >= 12) {
            AM_OR_PM = "PM";
        } else {
            AM_OR_PM = "AM";
        }
        if (hourData == 0) {
            hourData = 12;
        }
        if (hourData < 10) {
            finalString = "0" + hourData + ":" + minuteData + " " + AM_OR_PM;
        } else {
            finalString = hourData + ":" + minuteData + " " + AM_OR_PM;
        }
        return finalString;
    }

    public static String[] splitBySpace(String str) {
        String[] splitStr = str.split("\\s+");
        return splitStr;
    }

    public static String[] splitByCharacter(String sourceString, String splitCharacter) {
        String[] resultString = null;
        if (!TextUtils.isEmpty(sourceString) && !TextUtils.isEmpty(splitCharacter)) {
            resultString = sourceString.split(splitCharacter);
        }
        return resultString;
    }

    public static int compareDate(String sourceDate, String desDate, String format) {
        System.out.println("@date" + sourceDate + " " + desDate);
        Date date1 = null;
        Date date2 = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date1 = sdf.parse(sourceDate);
            date2 = sdf.parse(desDate);
            if (date1.compareTo(date2) > 0) {
                return 2;
            } else if (date1.compareTo(date2) < 0) {
                return 1;
            } else if (date1.compareTo(date2) == 0) {
                return 0;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    public static Bitmap getBitmapFromView(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    public static File store(Context context, Bitmap bm, String fileName) {
        File file = null;
        if (context.getExternalFilesDir(null) != null) {

            final String dirPath = context.getExternalFilesDir(null).getAbsolutePath() + "/PDF";
            File dir = new File(dirPath);
            if (!dir.exists())
                dir.mkdirs();
            file = new File(dirPath, fileName);
            try {
                FileOutputStream fOut = new FileOutputStream(file);
                bm.compress(Bitmap.CompressFormat.PNG, 85, fOut);
                fOut.flush();
                fOut.close();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file == null ? null : file;
    }

    public static Intent shareIntent(Context context, File file) {
        if (Build.VERSION.SDK_INT >= 24) {
            Uri uri = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", file);
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("application/pdf");

            intent.putExtra(Intent.EXTRA_SUBJECT, "");
            intent.putExtra(Intent.EXTRA_TEXT, "");
            intent.putExtra(Intent.EXTRA_STREAM, uri);

            return intent;
        } else {
            Uri uri = Uri.fromFile(file);
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("application/pdf");

            intent.putExtra(Intent.EXTRA_SUBJECT, "");
            intent.putExtra(Intent.EXTRA_TEXT, "");
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            return intent;
        }
    }

    public static Bitmap getBitmapFromViews(View view, int height, int width) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.WHITE);
        view.draw(canvas);
        return bitmap;
    }


    public static Intent pickFromGallery() {

        //Create an Intent with action as ACTION_PICK
        Intent intent = new Intent(Intent.ACTION_PICK);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/*");
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        // Launching the Intent
        return intent;
    }


    /*    private void captureFromCamera(Context context,File file) {
            try {
                Uri uri;
                if (Build.VERSION.SDK_INT >= 24) {
                     uri = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", file);
                }else

                {
                    uri = Uri.fromFile(file);
                }
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                *//*intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(getBaseActivity(), BuildConfig.APPLICATION_ID + ".provider", createImageFile()));
            startActivityForResult(intent, 112);*//*
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }*/
    public static Uri getUri(Context context, File file) {
        Uri uriData;
        if (Build.VERSION.SDK_INT >= 24) {
            uriData = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", file);
        } else {
            uriData = Uri.fromFile(file);
        }
        return uriData;
    }


    public static boolean validate(String emailData) {
        boolean is_valid = true;
        {
            String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
            if (!emailData.matches(emailPattern)) {
                is_valid = false;
                return is_valid;
            }
        }
        return is_valid;
    }

    public static String dateConverter(String sourceFormat, String destFormat, String dateData) throws ParseException {
        String resultDate = null;
        if (!TextUtils.isEmpty(dateData)) {
            String dateStr = dateData;
            DateFormat srcFormat = new SimpleDateFormat(sourceFormat,new Locale("es"));
            Date date = srcFormat.parse(dateStr);
            DateFormat destFormats = new SimpleDateFormat(destFormat,new Locale("es"));
            resultDate = destFormats.format(date);
        }
        return resultDate;
    }

    public static   boolean checktimings(String time, String endtime) {
        String pattern = "hh:mm a";

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            Date date1 = sdf.parse(time);
            Date date2 = sdf.parse(endtime);
            if (date1.after(date2)) {

                return true;
            } else {

                return false;
            }
        } catch (ParseException e) {
            System.out.println("@DATA"+e.getMessage());

            e.printStackTrace();
        }
        return false;
    }

}
