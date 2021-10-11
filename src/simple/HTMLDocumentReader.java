package simple;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public final class HTMLDocumentReader {

    private static String filterTag(String textLine) {
        if (textLine.contains("&nbsp")) textLine = textLine.replace("&nbsp", " ");
        if (textLine.contains("&mdash")) textLine = textLine.replace("&mdash", "");
        textLine = textLine.toLowerCase().replaceAll("\\<.*?>", "");
        return textLine;
    }
    private static String filterPunctuation(String textLine) {
        char[] charLine = textLine.toLowerCase().toCharArray();
        for (char c : charLine) {
            switch (c) {
                case '!':
                    textLine = textLine.replace("!", "");
                    break;
                case '.':
                    textLine = textLine.replace(".", "");
                    break;
                case ',':
                    textLine = textLine.replace(",", "");
                    break;
                case '?':
                    textLine = textLine.replace("?", "");
                    break;
                case ':':
                    textLine = textLine.replace(":", "");
                    break;
                case ';':
                    textLine = textLine.replace(";", "");
                    break;
                default:
                    break;
            }
        }
        return textLine;
    }
    public static String perform(String line){
        line = filterTag(line);
        line = filterPunctuation(line);
        return line;
    }
}
