package com.pond.build.utils;

import java.math.BigDecimal;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class CommonUtil {

    //几位随机数
    public static String generateRandomNumericString(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than 0");
        }
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10);  // 生成 0 到 9 之间的随机数字
            sb.append(digit);
        }

        return sb.toString();
    }


    //URL编码转义 minio
    public static String fileUrlEncoderChance(String originalPath) {

        try {
            if (originalPath == null || originalPath.isBlank()) {
                return "";
            }
            URL url = new URL(originalPath);
            String path = url.getPath();

            String fileName = path.substring(path.lastIndexOf('/') + 1);

            String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8)
                    .replaceAll("\\+", "%20")
                    .replaceAll("%21", "!")
                    .replaceAll("%27", "'")
                    .replaceAll("%28", "(")
                    .replaceAll("%29", ")")
                    .replaceAll("%7E", "~");
            String baseUrl = originalPath.substring(0, originalPath.lastIndexOf('/') + 1);

            return baseUrl + encodedFileName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    private static final String[] CN_UPPER_NUMBER = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
    private static final String[] CN_UPPER_MONETRAY_UNIT = {"分", "角", "元", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿",
            "拾", "佰", "仟", "兆", "拾", "佰", "仟"};

    // 金额大写
    public static String toChineseAmount(BigDecimal amount) {
        StringBuilder sb = new StringBuilder();
        int signum = amount.signum();
        if (signum == 0) {
            return CN_UPPER_NUMBER[0] + CN_UPPER_MONETRAY_UNIT[2];
        }
        long number = amount.movePointRight(2).setScale(0, BigDecimal.ROUND_HALF_UP).abs().longValue();
        int numIndex = 0;
        boolean getZero = false;
        if (number <= 0) {
            return "零";
        }
        while (true) {
            int numUnit = (int) (number % 10);
            if (numUnit > 0) {
                if (numIndex == 9 && getZero) {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[6]);
                }
                if (numIndex == 13 && sb.charAt(0) == CN_UPPER_NUMBER[0].charAt(0)) {
                    sb = new StringBuilder(sb.substring(1));
                }
                sb.insert(0, CN_UPPER_NUMBER[numUnit] + CN_UPPER_MONETRAY_UNIT[numIndex]);
                getZero = true;
            } else {
                if (numIndex == 2 || numIndex == 6 || numIndex == 10 || numIndex == 14) {
                    if (number / 10 % 10 > 0) {
                        sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                    }
                }
                if (number == 0) {
                    break;
                }
                if (getZero) {
                    sb.insert(0, CN_UPPER_NUMBER[numUnit]);
                }
                getZero = false;
            }
            number = number / 10;
            numIndex++;
        }
        if (signum == -1) {
            sb.insert(0, "负");
        }
        return sb.toString();
    }
}
