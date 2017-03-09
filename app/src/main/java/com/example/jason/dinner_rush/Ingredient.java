package com.example.jason.dinner_rush;

/**
 * Created by byronc on 3/8/17.
 */

public class Ingredient {

    // contains name of processed version of ingredient
    private String mName;
    private boolean mProcessed;
    private boolean m_isMine;
    private boolean m_haveIt;

    public Ingredient(String name, boolean p, boolean mine){
        mName = name;
        mProcessed = p;
        mine = m_isMine;
        m_haveIt = m_isMine;
    }

    public boolean getProcessed() { return mProcessed; }
    public void setProcessed(boolean p) { mProcessed = p; }

    public String getName() { return mName; }

    public boolean useIngredient() {
        if (m_isMine) return true;
        if (m_haveIt) {
            m_haveIt = false;
            return true;
        }

        return false;
    }

    public boolean getIngredient() { m_haveIt = true; }
    public boolean haveIngredient() { return m_haveIt; }

}
