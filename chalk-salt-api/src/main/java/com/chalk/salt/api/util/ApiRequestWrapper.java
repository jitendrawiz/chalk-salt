package com.chalk.salt.api.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class ApiRequestWrapper extends HttpServletRequestWrapper {

    private final HttpServletRequest original;
    private byte[] reqBytes;
    private boolean firstTime = true;

    public ApiRequestWrapper(final HttpServletRequest request) {
        super(request);
        original = request;
        try {
            firstTime();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BufferedReader getReader() throws IOException {

        if (firstTime) {
            firstTime();
        }

        final InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(reqBytes));
        return new BufferedReader(isr);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {

        if (firstTime) {
            firstTime();
        }

        final ServletInputStream sis = new ServletInputStream() {
            private int i;

            @Override
            public int read() throws IOException {
                byte b;
                if (reqBytes.length > i) {
                    b = reqBytes[i++];
                } else {
                    b = -1;
                }

                return b;
            }
        };

        return sis;
    }

    private void firstTime() throws IOException {
        final StringBuffer buffer = new StringBuffer();
        final BufferedReader reader = original.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        reqBytes = buffer.toString().getBytes();
        firstTime = false;
    }
}
