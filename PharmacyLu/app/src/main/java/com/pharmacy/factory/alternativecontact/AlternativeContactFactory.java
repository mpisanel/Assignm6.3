package com.pharmacy.factory.alternativecontact;
import com.pharmacy.domain.alternativecontact.impl.AlternativeContactImpl;
/**
 * Created by SONY on 2016-04-16.
 */
public class AlternativeContactFactory
{
    public  static AlternativeContactImpl getAlternativeDetails(String alternNum, String alternNm)
    {
        AlternativeContactImpl myAltern = new AlternativeContactImpl.Builder()
                .bAlternNumber(alternNum)
                .bAlternName(alternNm)
                .build();
        return myAltern;
    }
}

