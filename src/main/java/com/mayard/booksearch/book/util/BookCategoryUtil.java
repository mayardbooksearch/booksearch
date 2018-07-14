package com.mayard.booksearch.book.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mayard.booksearch.book.model.properties.BookCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Component
public class BookCategoryUtil {

    private Logger logger = LoggerFactory.getLogger(BookCategoryUtil.class);

    @Value("${category.large.file.name}")
    private String largeCategoryFileName;

    @Value("${category.korean.file.name}")
    private String koreanCategoryFileName;

    @Value("${category.ebook.file.name}")
    private String ebookCategoryFileName;

    @Value("${category.english.file.name}")
    private String englishCategoryFileName;

    @Value("${category.japanese.file.name}")
    private String japaneseCategoryFileName;

    @Value("${category.french.file.name}")
    private String frenchCategoryFileName;

    @Value("${category.german.file.name}")
    private String germanCategoryFileName;

    @Value("${category.spanish.file.name}")
    private String spanishCategoryFileName;

    private List<BookCategory> largeCategories;

    private List<BookCategory> koreanCategories;

    private List<BookCategory> ebookCategories;

    private List<BookCategory> englishCategories;

    private List<BookCategory> japaneseCategories;

    private List<BookCategory> frenchCategories;

    private List<BookCategory> germanCategories;

    private List<BookCategory> spanishCategories;

    @PostConstruct
    public void init() {

        ObjectMapper objectMapper = new ObjectMapper();

        try {

//            ClassLoader classLoader = getClass().getClassLoader();

            setLargeCategories(Arrays.asList(objectMapper.readValue(getClass().getClassLoader().getResourceAsStream(largeCategoryFileName), BookCategory[].class)));
            setKoreanCategories(Arrays.asList(objectMapper.readValue(getClass().getClassLoader().getResourceAsStream(koreanCategoryFileName), BookCategory[].class)));
            setEbookCategories(Arrays.asList(objectMapper.readValue(getClass().getClassLoader().getResourceAsStream(ebookCategoryFileName), BookCategory[].class)));
            setEnglishCategories(Arrays.asList(objectMapper.readValue(getClass().getClassLoader().getResourceAsStream(englishCategoryFileName), BookCategory[].class)));
            setJapaneseCategories(Arrays.asList(objectMapper.readValue(getClass().getClassLoader().getResourceAsStream(japaneseCategoryFileName), BookCategory[].class)));
            setFrenchCategories(Arrays.asList(objectMapper.readValue(getClass().getClassLoader().getResourceAsStream(frenchCategoryFileName), BookCategory[].class)));
            setGermanCategories(Arrays.asList(objectMapper.readValue(getClass().getClassLoader().getResourceAsStream(germanCategoryFileName), BookCategory[].class)));
            setSpanishCategories(Arrays.asList(objectMapper.readValue(getClass().getClassLoader().getResourceAsStream(spanishCategoryFileName), BookCategory[].class)));

//            File largeFile = new File(classLoader.getResource(largeCategoryFileName).getFile());
//            setLargeCategories(Arrays.asList(objectMapper.readValue(largeFile, BookCategory[].class)));
//
//            File koreanFile = new File(classLoader.getResource(koreanCategoryFileName).getFile());
//            setKoreanCategories(Arrays.asList(objectMapper.readValue(koreanFile, BookCategory[].class)));
//
//            File ebookFile = new File(classLoader.getResource(ebookCategoryFileName).getFile());
//            setEbookCategories(Arrays.asList(objectMapper.readValue(ebookFile, BookCategory[].class)));
//
//            File englishFile = new File(classLoader.getResource(englishCategoryFileName).getFile());
//            setEnglishCategories(Arrays.asList(objectMapper.readValue(englishFile, BookCategory[].class)));
//
//            File japaneseFile = new File(classLoader.getResource(japaneseCategoryFileName).getFile());
//            setJapaneseCategories(Arrays.asList(objectMapper.readValue(japaneseFile, BookCategory[].class)));
//
//            File frenchFile = new File(classLoader.getResource(frenchCategoryFileName).getFile());
//            setFrenchCategories(Arrays.asList(objectMapper.readValue(frenchFile, BookCategory[].class)));
//
//            File germanFile = new File(classLoader.getResource(germanCategoryFileName).getFile());
//            setGermanCategories(Arrays.asList(objectMapper.readValue(germanFile, BookCategory[].class)));
//
//            File spanishFile = new File(classLoader.getResource(spanishCategoryFileName).getFile());
//            setSpanishCategories(Arrays.asList(objectMapper.readValue(spanishFile, BookCategory[].class)));

        } catch (Exception e) {
            logger.error(e.getMessage());
        }

    }

    public List<BookCategory> getSmallCategories(String categoryId) {

        switch (categoryId) {
            case "1":
                return getKoreanCategories();
            case "900":
                return getEbookCategories();
            case "100":
                return getEnglishCategories();
            case "200":
                return getJapaneseCategories();
            case "400":
                return getFrenchCategories();
            case "500":
                return getGermanCategories();
            case "600":
                return getSpanishCategories();
            default:
                return null;
        }
    }

    public List<BookCategory> getLargeCategories() {
        return largeCategories;
    }

    public void setLargeCategories(List<BookCategory> largeCategories) {
        this.largeCategories = largeCategories;
    }

    public List<BookCategory> getKoreanCategories() {
        return koreanCategories;
    }

    public void setKoreanCategories(List<BookCategory> koreanCategories) {
        this.koreanCategories = koreanCategories;
    }

    public List<BookCategory> getEbookCategories() {
        return ebookCategories;
    }

    public void setEbookCategories(List<BookCategory> ebookCategories) {
        this.ebookCategories = ebookCategories;
    }

    public List<BookCategory> getEnglishCategories() {
        return englishCategories;
    }

    public void setEnglishCategories(List<BookCategory> englishCategories) {
        this.englishCategories = englishCategories;
    }

    public List<BookCategory> getJapaneseCategories() {
        return japaneseCategories;
    }

    public void setJapaneseCategories(List<BookCategory> japaneseCategories) {
        this.japaneseCategories = japaneseCategories;
    }

    public List<BookCategory> getFrenchCategories() {
        return frenchCategories;
    }

    public void setFrenchCategories(List<BookCategory> frenchCategories) {
        this.frenchCategories = frenchCategories;
    }

    public List<BookCategory> getGermanCategories() {
        return germanCategories;
    }

    public void setGermanCategories(List<BookCategory> germanCategories) {
        this.germanCategories = germanCategories;
    }

    public List<BookCategory> getSpanishCategories() {
        return spanishCategories;
    }

    public void setSpanishCategories(List<BookCategory> spanishCategories) {
        this.spanishCategories = spanishCategories;
    }
}
