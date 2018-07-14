package com.mayard.booksearch.book.category;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mayard.booksearch.book.model.properties.BookCategory;
import com.mayard.booksearch.book.util.BookCategoryUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryTest {

    @Autowired
    private BookCategoryUtil bookCategoryUtil;


    @Test
    public void jsonCategoryReadTest() {

        ObjectMapper objectMapper = new ObjectMapper();

        try {

//            ClassLoader classLoader = getClass().getClassLoader();
//            File file = new File(classLoader.getResource("classpath:bookcategories/small/korean.category.json").getFile());

            InputStream inputStream =
                    getClass().getClassLoader().getResourceAsStream("bookcategories/small/korean.category.json");
            List<BookCategory> list = Arrays.asList(objectMapper.readValue(inputStream, BookCategory[].class));

            System.out.println(list.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void smallCategoryLoadTest() {

        List<BookCategory> largeList = bookCategoryUtil.getLargeCategories();

        List<BookCategory> kList = bookCategoryUtil.getKoreanCategories();
        List<BookCategory> eList = bookCategoryUtil.getEbookCategories();
        List<BookCategory> enList = bookCategoryUtil.getEnglishCategories();
        List<BookCategory> jList = bookCategoryUtil.getJapaneseCategories();
        List<BookCategory> fList = bookCategoryUtil.getFrenchCategories();
        List<BookCategory> gList = bookCategoryUtil.getGermanCategories();
        List<BookCategory> sList = bookCategoryUtil.getSpanishCategories();

        assertNotNull(largeList);
        assertNotNull(kList);
        assertNotNull(eList);
        assertNotNull(enList);
        assertNotNull(jList);
        assertNotNull(fList);
        assertNotNull(gList);
        assertNotNull(sList);

        System.out.println(largeList.toString());
        System.out.println(kList.toString());
        System.out.println(eList.toString());
        System.out.println(enList.toString());
        System.out.println(jList.toString());
        System.out.println(fList.toString());
        System.out.println(gList.toString());
        System.out.println(sList.toString());

    }
}
