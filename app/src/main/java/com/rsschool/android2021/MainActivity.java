package com.rsschool.android2021;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity  implements ActionPerformedListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            openFirstFragment(0);
        }
    }

    protected void openFirstFragment(int previousNumber) {
        final Fragment firstFragment = FirstFragment.newInstance(previousNumber);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, firstFragment);
        // TODO: invoke function which apply changes of the transaction
        transaction.commit();
    }

    protected void openSecondFragment(int min, int max) {
        // TODO: implement it
        final Fragment secondFragment = SecondFragment.newInstance(min, max);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, secondFragment);
        transaction.commit();
    }

    @Override
    public void openFirst(int prevNumber)
    {
        openFirstFragment(prevNumber);
    }

    @Override
    public void openSecond(int min, int max)
    {
        openSecondFragment(min, max);
    }

    @Override
    public void onBackPressed() {

        //Обработка нажатия кнопки назад для фрагментов
        final FragmentManager fm = getSupportFragmentManager();
        OnBackPressedListener onBackPressedListener = null;

        for (Fragment fragment: fm.getFragments()) {
            if (fragment instanceof  OnBackPressedListener) {
                onBackPressedListener = (OnBackPressedListener) fragment;
                break;
            }
        }
        if (onBackPressedListener!=null){
            onBackPressedListener.onBackPressed();
        }
        else {
            super.onBackPressed();
        }

    }
}
