package com.nulabinc.zxcvbn.matchers;

import com.nulabinc.zxcvbn.io.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DictionaryLoader {

    private final String name;

    private final Resource resource;

    public DictionaryLoader(final String name, final Resource resource) {
        this.name = name;
        this.resource = resource;
    }

    public Dictionary load() throws IOException {
        List<String> words = new ArrayList<String>();
        // Reasons for not using StandardCharsets
        // refs: https://github.com/nulab/zxcvbn4j/issues/62

        // The following is a Java-6 equivalent to try-with-resources
        // See: https://romain.vernoux.fr/java/android/2016/04/16/try-with-resources-java6/        
        try {
            final InputStream inputStream = resource.getInputStream();
            Throwable ist = null;
            try {
                final InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                Throwable isrt = null;
                try {
                    final BufferedReader br = new BufferedReader(inputStreamReader);
                    Throwable brt = null;
                    try {
                        String line;
                        while ((line = br.readLine()) != null) {
                            words.add(line);
                        }
                    }
                    catch (IOException brt_1) {
                        brt = brt_1;
                        throw brt_1;
                    }
                    finally {
                        if (br != null) {
                            if (brt != null) {
                                try {
                                    br.close();
                                }
                                catch (Throwable brt_2) {
//                                    brt.addSuppressed(brt_2);
                                }
                            }
                            else {
                                br.close();
                            }
                        }
                    }
                }
                catch (IOException isrt_1) {
                    isrt = isrt_1;
                    throw isrt_1;
                }
                finally
                {
                    if (inputStreamReader != null) {
                        if (isrt != null) {
                            try {
                                inputStreamReader.close();
                            }
                            catch (Throwable isrt_2) {
//                                isrt.addSuppressed(isrt_2);
                            }
                        }
                        else {
                            inputStreamReader.close();
                        }
                    }
                }
            }
            catch (IOException ist_1) {
                ist = ist_1;
                throw ist_1;
            }
            finally {
                if (inputStream != null) {
                    if (ist != null) {
                        try {
                            inputStream.close();
                        }
                        catch (Throwable ist_2) {
//                            ist.addSuppressed(ist_2);
                        }
                    }
                    else {
                        inputStream.close();
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while reading " + name);
        }
        
        return new Dictionary(name, words);
    }
}
