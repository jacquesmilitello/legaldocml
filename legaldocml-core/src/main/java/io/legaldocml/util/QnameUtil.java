package io.legaldocml.util;

import io.legaldocml.io.CharArray;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class QnameUtil {

    private QnameUtil(){
    }

    public static String localName(CharArray array) {
        int i = 0;
        for (; i < array.length(); i++) {
            if (':' == array.charAt(i)) {
                break;
            }
        }
        if (array.length() == i) {
            return array.toString();
        }
        i++;
        return new String(array.value(),i,  array.length() - i);
    }

}
