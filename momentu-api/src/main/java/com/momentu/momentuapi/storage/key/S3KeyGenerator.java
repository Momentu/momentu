package com.momentu.momentuapi.storage.key;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class S3KeyGenerator {

    public String getUniqueKey(String appendText) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyMMddssSSS");
        String datetime = simpleDateFormat.format(date);
        String uniqueId = RandomStringUtils.randomAlphabetic(12);

        String completeId = datetime.concat(uniqueId);
        if(appendText != null) {
            completeId.concat(appendText);
        }
        return completeId;
    }

    public String getUniqueKey() {
        return this.getUniqueKey(null);
    }
}
