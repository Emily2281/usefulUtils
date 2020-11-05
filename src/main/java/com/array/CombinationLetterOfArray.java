package com.array;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试生成数组组合
 */
public class CombinationLetterOfArray {
    public static List<String> letterCombinations(String digits) {
        String[] buttons = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> result = new ArrayList<>();

        if (digits.length() == 0) {
            return result;
        }

        // 第一个数字所在的按钮上对应的字母
        String firstLetters = buttons[digits.charAt(0) - '0'];

        // 如果长度为1，则将该按钮上的字母分别返回即可
        if (digits.length() == 1) {
            for (int i = 0; i < firstLetters.length(); i++) {
                result.add(firstLetters.substring(i, i + 1));
            }
            return result;
        }

        // 如果长度大于1，则递归求解
        List<String> combinations = letterCombinations(digits.substring(1));
        for (int i = 0; i < firstLetters.length(); i++) {
            String letter = firstLetters.substring(i, i + 1);
            for (String combination : combinations) {
                result.add(letter + combination);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Integer[] digit = {3};
        String digitStr = StringUtils.join(digit);
        System.out.println(letterCombinations(digitStr));
        Integer[] digits = {2,3,4};
        String digitsStr = StringUtils.join(digits);
        System.out.println(letterCombinations(digitsStr));
    }
}
