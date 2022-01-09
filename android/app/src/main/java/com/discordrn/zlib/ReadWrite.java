package com.discordrn.zlib;

import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

public class ReadWrite {
    public static long copyTo(Reader reader, Writer writer, int i) throws IOException {
        char[] cArr = new char[i];
        int read = reader.read(cArr);
        long j = 0;
        while (read >= 0) {
            writer.write(cArr, 0, read);
            j += (long) read;
            read = reader.read(cArr);
        }
        return j;
    }

    public static long copyTo$default(Reader reader, Writer writer, int i, int i2, Object obj) throws IOException {
        if ((i2 & 2) != 0) {
            i = 8192;
        }
        return copyTo(reader, writer, i);
    }

    public static String readText(Reader reader) throws IOException {
        StringWriter stringWriter = new StringWriter();
        copyTo$default(reader, stringWriter, 0, 2, null);
        return stringWriter.toString();
    }
}
