package com.example.thiago.findjob.extras;

/**
 * Created by Silvério Neto on 02/09/2015.
 */
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class SessionManager {
    // LogCat tag
    private static String TAG = SessionManager.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref;

    Editor editor;
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "FindJob";

    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public Context getContext(){
        return this._context;
    }

    public void putUser(String user){
        editor.putString("user", user);
        editor.putBoolean(KEY_IS_LOGGEDIN, true);
        Log.d(TAG, "Usuário inserido na sessão!");
    }

    public void logout(){
        editor.clear();
        editor.commit();
    }
    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

    public String getUser(){
        return pref.getString("user", "0");
    }

    public void putUserType(String type){
        editor.putString("UserType", type);
        editor.commit();
    }

    public String getUserType(){
        return pref.getString("UserType","");
    }

}