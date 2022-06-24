package com.liyongquan.weeklycontest.wc296;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class TextEditorTest {

    @Test
    public void addText() {
        TextEditor editor = new TextEditor();
        editor.addText("leetcode");
        editor.deleteText(4);
        String s = editor.cursorLeft(0);
        Assert.assertEquals("leet", s);
    }
}