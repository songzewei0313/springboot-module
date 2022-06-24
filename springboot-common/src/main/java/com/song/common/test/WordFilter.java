package com.song.common.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author szw
 * @date 2022/6/17 15:24
 */
@Slf4j
@Component
public class WordFilter {

    public final static String WORDS = "WORDS";
    private final static String REPLACE_CHAR = "";
    private static HashMap sensitiveWordMap;

    /**
     * 最小匹配规则
     */
    private static int minMatchTYpe = 1;
    /**
     * 最大匹配规则
     */
    private static int maxMatchType = 2;

    /**
     * 敏感词汇过滤替换为*     *
     *
     * @param text 待检测文字
     * @return 替换后文字
     */
    public static String replaceWords(String text) {
        if (StringUtils.isBlank(text)) {
            return text;
        }
        //缓存获取敏感词汇原记录
        List<String> words = Cache.get(WORDS);
        if (CollectionUtils.isEmpty(words)) {
            //读取敏感词汇文件，存入缓存
            words = readWordsFile();
            Cache.put(WORDS, words);
        }
        if (CollectionUtils.isEmpty(words)) {
            return text;
        }
        //屏蔽敏感词汇
        return WordFilter.replaceSensitiveWord(words, text, minMatchTYpe);
    }

    /**
     * 读取敏感词汇文件
     */
    public static List<String> readWordsFile() {
        List<String> list = new ArrayList<>();
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
//            Resource resource = new DefaultResourceLoader().getResource("/tysrceah-center/src/main/resources/sensitiveWords.txt");
//            inputStream = resource.getInputStream();
            File file = new File("sensitiveWords.txt");
            inputStream = new FileInputStream(file);
            inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            bufferedReader = new BufferedReader(inputStreamReader);
            String txt = "";
            while (StringUtils.isNotBlank(txt = bufferedReader.readLine())) {
                list.addAll(
                        Arrays.asList(
                                StringUtils.split(
                                        StringUtils.deleteWhitespace(StringUtils.replace(txt, "，", ",")),
                                        ","
                                )
                        )
                );
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
        } catch (Exception e) {
            log.error("读取敏感词汇文件出错", e);
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                log.error("读取敏感词汇文件出错", e);
            }
        }
        return list;
    }

    /**
     * 替换敏感字字符
     *
     * @param data      敏感字集合
     * @param text       待检查文本
     * @param matchType 匹配规则
     */
    private static String replaceSensitiveWord(List<String> data, String text, int matchType) {
        if (sensitiveWordMap == null) {
            addSensitiveWord(data);
        }
        String resultTxt = text;
        //获取所有的敏感词
        List<String> set = getSensitiveWord(text, matchType);
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            resultTxt = resultTxt.replaceAll(iterator.next(), REPLACE_CHAR);
        }
        return resultTxt;
    }


    /**
     * 读取敏感词库，将敏感词放入HashSet中，构建一个DFA算法模型：
     * 说明：该方法来源于互联网
     */
    private static void addSensitiveWord(List<String> datas) {
        sensitiveWordMap = new HashMap(datas.size());
        Iterator<String> iterator = datas.iterator();
        Map<String, Object> now;
        Map now2;
        while (iterator.hasNext()) {
            now2 = sensitiveWordMap;
            //敏感词
            String word = iterator.next().trim();
            for (int i = 0; i < word.length(); i++) {
                char key_word = word.charAt(i);
                Object obj = now2.get(key_word);
                //存在
                if (obj != null && obj instanceof Map) {
                    now2 = (Map) obj;
                } else { //不存在
                    now = new HashMap<>(16);
                    now.put("isEnd", "0");
                    now2.put(key_word, now);
                    now2 = now;
                }
                if (i == word.length() - 1) {
                    now2.put("isEnd", "1");
                }
            }
        }
    }

    /**
     * 获取内容中的敏感词
     * 说明：该方法来源于互联网
     *
     * @param text      内容
     * @param matchType 匹配规则 1=不最佳匹配，2=最佳匹配
     * @return
     */
    private static List<String> getSensitiveWord(String text, int matchType) {
        List<String> words = new ArrayList<>();
        Map now = sensitiveWordMap;
        //初始化敏感词长度
        int count = 0;
        //标志敏感词开始的下标
        int start = 0;
        for (int i = 0; i < text.length(); i++) {
            char key = text.charAt(i);
            now = (Map) now.get(key);
            //存在
            if (now != null) {
                count++;
                if (count == 1) {
                    start = i;
                }
                //敏感词结束
                if ("1".equals(now.get("isEnd"))) {
                    //重新获取敏感词库
                    now = sensitiveWordMap;
                    //取出敏感词，添加到集合
                    words.add(text.substring(start, start + count));
                    //初始化敏感词长度
                    count = 0;
                }
            } else { //不存在
                //重新获取敏感词库
                now = sensitiveWordMap;
                //不最佳匹配
                if (count == 1 && matchType == 1) {
                    count = 0;
                    //最佳匹配
                } else if (count == 1 && matchType == 2) {
                    words.add(text.substring(start, start + count));
                    count = 0;
                }
            }
        }
        return words;
    }

    public WordFilter() {
        super();
    }

    public static void main(String[] args) {
        String s = "东方航空,。,英雄,纪念,登机牌,疫,，,2020年,致,null,3月,抗,中国";
        String s1 = WordFilter.replaceWords(s);
        System.out.println(s1);
    }

    public static void writeTxt(String text, boolean isAppend) {
        /*if (StringUtils.isBlank(text)) {
            return;
        }*/

        try {

            File file = new File("sensitiveWords.txt");

            if (!file.exists()) {
                file.createNewFile();
            }
            if (isAppend){
                text = file.length() == 0 ? text : "," + text;
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), isAppend);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(text);
            bw.close();

            log.info("suc");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
