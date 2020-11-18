package com.example.siyeon.project;

public class Coin {

    private static int mcoin;

    public void Addcoin(int add_coin)
    {
        mcoin=mcoin+add_coin;
    }
    public void Subcoin(int sub_coin)
    {
        mcoin=mcoin-sub_coin;
    }
    public int getCoin()
    {
        return mcoin;
    }
    public  void setCoin(int coin)
    {
        mcoin=coin;
    }

}

