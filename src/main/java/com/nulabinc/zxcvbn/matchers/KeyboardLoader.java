package com.nulabinc.zxcvbn.matchers;

import com.nulabinc.zxcvbn.io.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public abstract class KeyboardLoader {

    private final String name;

    private final Resource resource;

    public KeyboardLoader(final String name, final Resource resource) {
        this.name = name;
        this.resource = resource;
    }

    public Keyboard load() throws IOException {
        InputStream inputStream = resource.getInputStream();
        String layout = loadAsString(inputStream);
        return new Keyboard(name, buildAdjacentGraphBuilder(layout));
    }

    protected abstract Keyboard.AdjacentGraphBuilder buildAdjacentGraphBuilder(final String layout);

    private static String loadAsString(final InputStream input) {
        // The following is a Java-6 equivalent to try-with-resources
        // See: https://romain.vernoux.fr/java/android/2016/04/16/try-with-resources-java6/
        try {
            InputStreamReader streamReader = new InputStreamReader(input, "UTF-8");
            Throwable obj1 = null;
            try {
                final BufferedReader reader = new BufferedReader(streamReader);
                Throwable obj2 = null;
                try {
                    final StringBuilder sb = new StringBuilder(1024 * 4);
                    String str;
                    while ((str = reader.readLine()) != null) {
                        sb.append(str);
                        sb.append('\n');
                    }
                    return sb.toString();
                }
                catch (IOException th2_1) {
                    obj2 = th2_1;
                    throw th2_1;
                }
                finally {
                    if (reader != null) {
                        if (obj2 != null) {
                            try {
                                reader.close();
                            }
                            catch (Throwable th2_2) {
//                                obj2.addSuppressed(th2_2);
                            }
                        }
                        else {
                            reader.close();
                        }
                    }
                }
            }
            catch (IOException th1_1) {
                obj1 = th1_1;
                throw th1_1;
            }
            finally {
                if (streamReader != null) {
                    if (obj1 != null) {
                        try {
                            streamReader.close();
                        }
                        catch (Throwable th1_2) {
//                            obj1.addSuppressed(th1_2);
                        }
                    }
                    else {
                        streamReader.close();
                    }
                }
            }
        }
        catch (IOException uee) {
            throw new IllegalArgumentException(uee);
        }
    }

}
