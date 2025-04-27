package com.hsryuuu.flashsale.application.type;

import com.hsryuuu.flashsale.application.aop.exception.ErrorMessage;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public enum YNFlag {
    Y(true),
    N(false);
    private final boolean value;

    public static YNFlag fromBoolean(boolean value) {
        return value ? YNFlag.Y : YNFlag.N;
    }

    public static YNFlag fromString(String value) {
        for (YNFlag flag : YNFlag.values()) {
            if (flag.name().equalsIgnoreCase(value)) {
                return flag;
            }
        }
        throw new IllegalArgumentException(ErrorMessage.BAD_REQUEST_ENUM);
    }

    public static List<YNFlag> getYesFlags() {
        return Collections.singletonList(YNFlag.Y);
    }

    public static List<YNFlag> getNoFlags() {
        return Collections.singletonList(YNFlag.N);
    }

    public boolean getValue() {
        return this.value;
    }
}
