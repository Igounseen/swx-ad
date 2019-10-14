package com.swx.ad.utils;


import com.swx.ad.exception.AdException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.time.DateUtils;

import java.util.Date;

public class CommonUtils {

    private static String[] datePatterns = {"yyyy-MM-dd", "yyyy/MM/dd", "yyyy.MM.dd"};

    public static String md5(String value) {
        return DigestUtils.md5Hex(value).toUpperCase();
    }

    public static Date parseStringDate(String date) throws AdException {
        try {
            return DateUtils.parseDate(date, datePatterns);
        } catch (Exception ex) {
            throw new AdException(ex.getMessage());
        }
    }
}
