package com.example.honghanh.hci_wiki.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.honghanh.hci_wiki.storage.model.Data;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

public class PreferManager {

    private static PreferManager preferManager;

    public static PreferManager getInstance(Context context) {
        if(preferManager == null) {
            preferManager = new PreferManager(context);
        }
        return preferManager;
    }

    private SharedPreferences mPreferences;

    private PreferManager(Context context) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    /////////////History/////////////////////////////
    private static String KEY_LIST_HISTORY = "key_list_history";

    public void saveListHistory(List<String> historyList) {
        SharedPreferences.Editor editor = mPreferences.edit();
        if(historyList == null || historyList.size() == 0) {
            editor.remove(KEY_LIST_HISTORY);
        } else {
            editor.putString(KEY_LIST_HISTORY, new Gson().toJson(historyList));
        }
        editor.apply();
    }

    public List<String> getListHistory() {
        ArrayList<String> list = new ArrayList<>();
        String info = mPreferences.getString(KEY_LIST_HISTORY, null);
        if(info != null) {
            JSONArray jData;
            try {
                jData = new JSONArray(info);
                for (int i = 0; i <jData.length(); i++) {
                    list.add(jData.getString(i));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    /////////////////////////Data//////////////////////////////////
    private static String KEY_LIST_DATA = "key_list_data";

    public void saveListData(List<Data> list) {
        SharedPreferences.Editor editor = mPreferences.edit();
        if(list == null || list.size() == 0) {
            editor.remove(KEY_LIST_DATA);
        } else {
            editor.putString(KEY_LIST_DATA, new Gson().toJson(list));
        }
        editor.apply();
    }

    public List<Data> getListData() {
        ArrayList<Data>list = new ArrayList<>();
        String info = mPreferences.getString(KEY_LIST_DATA, null);
        if(info != null) {
            JSONArray jData;
            try {
                jData = new JSONArray(info);
                for (int i = 0; i < jData.length(); i++) {
                    list.add(new Gson().fromJson(jData.getString(i), Data.class));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
