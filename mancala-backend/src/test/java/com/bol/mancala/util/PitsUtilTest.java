package com.bol.mancala.util;

import com.bol.mancala.util.PitsUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PitsUtilTest {

    List<Integer> sampleList;
    String sampleString;

    @BeforeEach
    public void init() {
        sampleList = new ArrayList<>();
        sampleList.add(1);
        sampleList.add(2);
        sampleList.add(3);
        sampleList.add(4);
        sampleList.add(5);
        sampleString = "[1,2,3,4,5]";
    }

    @Test
    void testToString() {
        Assertions.assertEquals(sampleString, PitsUtil.toString(sampleList));
    }

    @Test
    void testToList() {
        assertEquals(sampleList, PitsUtil.toList(sampleString));
    }
}
