package com.nightonke.wowoviewpager.Enum;

/**
 * Created by Weiping Huang at 14:24 on 2017/3/30
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 */

public class WoWoTypewriter {

    /**
     * ABC->123:
     * ABC->AB->A->{empty}->1->12->123
     */
    public static final Typewriter DeleteThenType = new Typewriter() {
        @Override
        public String type(String fromText, String toText, float offset) {
            return deleteThenType(fromText, toText, offset);
        }
    };

    /**
     * ABC->123:
     * ABC->1AB->12A->123
     */
    public static final Typewriter InsertFromLeft = new Typewriter() {
        @Override
        public String type(String fromText, String toText, float offset) {
            return insertFromLeft(fromText, toText, offset);
        }
    };

    /**
     * ABC->123:
     * ABC->AB1->A12->123
     */
    public static final Typewriter InsertFromRight = new Typewriter() {
        @Override
        public String type(String fromText, String toText, float offset) {
            return insertFromRight(fromText, toText, offset);
        }
    };

    private static String deleteThenType(String fromText, String toText, float offset) {
        int sum = (fromText.length() + toText.length()) * 2;
        float current = sum * offset;
        if (current < 1) return fromText;
        else if (current < sum - toText.length() * 2 - 1) {
            return fromText.substring(0, fromText.length() - (int) ((current + 1) / 2));
        } else if (current < fromText.length() * 2 + 1) {
            return "";
        } else if (current < sum - 1) {
            return toText.substring(0, (int) ((current - fromText.length() * 2 + 1) / 2));
        } else return toText;
    }

    private static String insertFromLeft(String fromText, String toText, float offset) {
        int sum = Math.max(fromText.length(), toText.length()) * 2;
        float current = sum * offset;
        if (current < 1) return fromText;
        else if (current < sum - 1) {
            int length = (int) ((sum - current + 1) / 2);
            String pre = toText.substring(0, Math.min((int) ((current + 1) / 2), toText.length()));
            String post = fromText.substring(0, Math.min(length, fromText.length()));
            return pre + post;
        } else return toText;
    }

    private static String insertFromRight(String fromText, String toText, float offset) {
        int sum = Math.max(fromText.length(), toText.length()) * 2;
        float current = sum * offset;
        if (current < 1) return fromText;
        else if (current < sum - 1) {
            int length = (int) ((sum - current + 1) / 2);
            String pre = toText.substring(0, Math.min((int) ((current + 1) / 2), toText.length()));
            String post = fromText.substring(0, Math.min(length, fromText.length()));
            return post + pre;
        } else return toText;
    }
}
