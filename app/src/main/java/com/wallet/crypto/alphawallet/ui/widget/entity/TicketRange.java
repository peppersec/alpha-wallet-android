package com.wallet.crypto.alphawallet.ui.widget.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.wallet.crypto.alphawallet.entity.Ticket;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by James on 10/02/2018.
 */

public class TicketRange implements Parcelable
{
    //public final int seatStart;
    //public int seatCount;
    public boolean isChecked;
    public String contractAddress; // Should this be address or actual token?

    //public final int tokenId;

    public List<Integer> tokenIds;

    public TicketRange(int tokenId, String contractAddress)
    {
        this.contractAddress = contractAddress;
        tokenIds = new ArrayList<>();
        tokenIds.add(tokenId);
//        this.tokenId = tokenId;
//        this.seatStart = seatStart;
//        this.seatCount = 1;
        this.isChecked = false;
    }

    private TicketRange(Parcel in)
    {
        Object[] readObjArray = in.readArray(Object.class.getClassLoader());
        this.tokenIds = new ArrayList<>();
        for (Object o : readObjArray)
        {
            this.tokenIds.add((Integer)o);
        }
//        this.tokenId = in.readInt();
//        this.seatStart = in.readInt();
//        this.seatCount = in.readInt();
        this.isChecked = (in.readInt() == 1) ? true : false;
        this.contractAddress = in.readString();
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeArray(tokenIds.toArray());
//        dest.writeInt(this.tokenId);
//        dest.writeInt(this.seatStart);
//        dest.writeInt(this.seatCount);
        dest.writeInt(this.isChecked ? 1:0);
        dest.writeString(this.contractAddress);
    }

    public static final Creator<TicketRange> CREATOR = new Creator<TicketRange>() {
        @Override
        public TicketRange createFromParcel(Parcel in) {
            return new TicketRange(in);
        }

        @Override
        public TicketRange[] newArray(int size) {
            return new TicketRange[size];
        }
    };
}