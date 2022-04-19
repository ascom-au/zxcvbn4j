
# zxcvbn4j [![Build](https://github.com/nulab/zxcvbn4j/actions/workflows/build.yml/badge.svg)](https://github.com/nulab/zxcvbn4j/actions/workflows/build.yml) [![Coverage Status](https://coveralls.io/repos/nulab/zxcvbn4j/badge.svg?branch=master&service=github)](https://coveralls.io/github/nulab/zxcvbn4j?branch=master) [![Maven Central](https://img.shields.io/maven-central/v/com.nulab-inc/zxcvbn.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.nulab-inc%22%20AND%20a:%22zxcvbn%22)

This is a java port of [zxcvbn](https://github.com/dropbox/zxcvbn), which is a password strength estimator inspired by password crackers written on JavaScript.
Through pattern matching and conservative estimation, it recognizes and weighs 30k common passwords, common names and surnames according to US census data, popular English words from Wikipedia and US television and movies, and other common patterns like dates, repeats (`aaa`), sequences (`abcd`), keyboard patterns (`qwertyuiop`), and l33t speak.

**Related articles:**

- [Five Algorithms to Measure Real Password Strength](https://nulab-inc.com/blog/nulab/password-strength/)

## Table Contents

* [Update](#update)
* [Special Features](#special-features)
  + [Customize internal dictionaries and keyboards](#customize-internal-dictionaries-and-keyboards)
  + [Localize feedback messages](#localize-feedback-messages)
  + [JIS keyboard layout](#jis-keyboard-layout)
  + [Support some languages by default](#support-some-languages-by-default)
  + [Password args accept CharSequence as well as String](#password-args-accept-charsequence-as-well-as-string)
* [Install](#install)
* [Development](#development)
* [Usage](#usage)
  + [Basic](#basic)
  + [Strength Properties](#strength-properties)
  + [Customize internal dictionaries and keyboards](#customize-internal-dictionaries-and-keyboards-1)
    - [Use resources on the classpath](#use-resources-on-the-classpath)
    - [Use resources get via HTTP](#using-resources-get-via-http)
    - [Use file resources other than classpath](#use-file-resources-other-than-classpath)
    - [Use all default resources](#use-all-default-resources)
    - [Select from and use default resources](#select-from-and-use-default-resources)
  + [Localize feedback messages](#localize-feedback-messages-1)
    - [Localize each feedback](#localize-each-feedback)
    - [Localize each locale](#localize-each-locale)
* [Requires Java](#requires-java)
* [Using this library](#using-this-library)
* [Bugs and Feedback](#bugs-and-feedback)
* [License](#license)

## Update

The following version is a port of [zxcvbn 4.4.2](https://github.com/dropbox/zxcvbn/releases/tag/v4.4.2)

* 2022/04/13 1.7.0 released.
* 2022/04/05 1.6.0 released.
* 2021/06/08 1.5.2 released.
* 2021/06/05 1.5.1 released.
* 2021/04/26 1.5.0 released.
* 2021/03/22 1.4.1 released.
* 2021/02/19 1.4.0 released.
* 2021/02/09 1.3.6 released.
* 2021/02/02 1.3.5 released.
* 2021/01/26 1.3.4 released.
* 2021/01/21 1.3.3 released.
* 2021/01/19 1.3.2 released.
* 2020/10/28 1.3.1 released.
* 2019/10/19 1.3.0 released.
* 2019/07/23 1.2.7 released.
* 2019/07/16 1.2.6 released.
* 2018/03/30 1.2.5 released.
* 2018/02/27 1.2.4 released.
* 2017/03/27 1.2.3 released.

The following version is a port of [zxcvbn 4.4.1](https://github.com/dropbox/zxcvbn/releases/tag/v4.4.1)

* 2016/12/07 1.2.2 released.
* 2016/12/03 1.2.1 released.

The following version is a port of [zxcvbn 4.4.0](https://github.com/dropbox/zxcvbn/releases/tag/v4.4.0)

* 2016/10/29 1.2.0 released.

The following version is a port of [zxcvbn 4.3.0](https://github.com/dropbox/zxcvbn/releases/tag/4.3.0)

* 2016/10/01 1.1.6 released.
* 2016/09/27 1.1.5 released.
* 2016/07/08 1.1.4 released.
* 2016/05/27 1.1.3 released.
* 2016/05/25 1.1.2 released.
* 2016/03/19 1.1.1 released.
* 2016/03/06 1.1.0 released.

The following version is a port of [zxcvbn 4.2.0](https://github.com/dropbox/zxcvbn/releases/tag/4.2.0)

* 2016/01/28 1.0.2 released.
* 2016/01/27 1.0.1 released.
* 2015/12/24 1.0.0 released.

## Special Features

### Customize internal dictionaries and keyboards

* Customize the dictionary and keyboard layout used by the measurement algorithm.

### Localize feedback messages

* The zxcvbn4j can be localized the english feedback message to other languages.

### Support some languages by default

- English ([default](./src/main/resources/com/nulabinc/zxcvbn/messages.properties))
- Japanese ([ja](./src/main/resources/com/nulabinc/zxcvbn/messages_ja.properties))
- Dutch ([nl](./src/main/resources/com/nulabinc/zxcvbn/messages_nl.properties))
- German ([de](./src/main/resources/com/nulabinc/zxcvbn/messages_de.properties))
- French ([fr](./src/main/resources/com/nulabinc/zxcvbn/messages_fr.properties))
- Italian ([it](./src/main/resources/com/nulabinc/zxcvbn/messages_it.properties))

### JIS keyboard layout

* It includes JIS keyboard layout in spatial matching.

### Password args accept CharSequence as well as String

* This gives a lot more flexibility in what format the password can be in.
* Also attempts to avoid using Strings for any sensitive intermediate objects.

## Install

https://mvnrepository.com/artifact/com.nulab-inc/zxcvbn/1.7.0

Gradle:

```
compile 'com.nulab-inc:zxcvbn:1.7.0'
```

Maven:

```
<dependency>
  <groupId>com.nulab-inc</groupId>
  <artifactId>zxcvbn</artifactId>
  <version>1.7.0</version>
</dependency>
```

## Development

``` bash
$ git clone https://github.com/nulab/zxcvbn4j.git
$ cd ./zxcvbn4j
$ ./gradlew build    # build
$ ./gradlew test     # test
$ ./gradlew jmh      # benchmark
```

## Usage

### Basic

This is also available Android.

``` java
Zxcvbn zxcvbn = new Zxcvbn();
Strength strength = zxcvbn.measure("This is password");
```

If you want to add your own dictionary, put the keyword list of List <String> type to the second argument.

``` java
List<String> sanitizedInputs = new ArrayList();
sanitizedInputs.add("nulab");
sanitizedInputs.add("backlog");
sanitizedInputs.add("cacoo");
sanitizedInputs.add("typetalk");

Zxcvbn zxcvbn = new Zxcvbn();
Strength strength = zxcvbn.measure("This is password", sanitizedInputs);
```

### Strength Properties

The return result is "Strength". It's almost the same as [zxcvbn](https://github.com/dropbox/zxcvbn).

```
# estimated guesses needed to crack password
strength.guesses

# order of magnitude of strength.guesses
strength.guessesLog10

# dictionary of back-of-the-envelope crack time
# estimations, in seconds, based on a few scenarios
strength.crackTimeSeconds
{
  # online attack on a service that ratelimits password auth attempts.
  onlineThrottling100PerHour

  # online attack on a service that doesn't ratelimit,
  # or where an attacker has outsmarted ratelimiting.
  onlineNoThrottling10PerSecond

  # offline attack. assumes multiple attackers,
  # proper user-unique salting, and a slow hash function
  # w/ moderate work factor, such as bcrypt, scrypt, PBKDF2.
  offlineSlowHashing1e4PerSecond

  # offline attack with user-unique salting but a fast hash
  # function like SHA-1, SHA-256 or MD5. A wide range of
  # reasonable numbers anywhere from one billion - one trillion
  # guesses per second, depending on number of cores and machines.
  # ballparking at 10B/sec.
  offlineFastHashing1e10PerSecond
}

# same keys as result.crack_time_seconds,
# with friendlier display string values:
# "less than a second", "3 hours", "centuries", etc.
strength.crackTimeDisplay

# Integer from 0-4 (useful for implementing a strength bar)
# 0 Weak        （guesses < 10^3 + 5）
# 1 Fair        （guesses < 10^6 + 5）
# 2 Good        （guesses < 10^8 + 5）
# 3 Strong      （guesses < 10^10 + 5）
# 4 Very strong （guesses >= 10^10 + 5）
strength.score

# verbal feedback to help choose better passwords. set when score <= 2.
strength.feedback
{
  # explains what's wrong, eg. 'this is a top-10 common password'.
  # not always set -- sometimes an empty string
  warning

  # a possibly-empty list of suggestions to help choose a less
  # guessable password. eg. 'Add another word or two'
  suggestions
}

# the list of patterns that zxcvbn based the guess calculation on.
strength.sequence

# how long it took zxcvbn to calculate an answer, in milliseconds.
strength.calc_time
```

### Customize internal dictionaries and keyboards

`Zxcvbn` can build with `ZxcvbnBuilder`.
`ZxcvbnBuilder` can customize dictionaries and keyboards used in measurements.

#### Use resources on the classpath

`ClasspathResource` can get your own dictionary and keyboard file on the classpath.
`DictionaryLoader` load dictionary file.
`SlantedKeyboardLoader` and `AlignedKeyboardLoader` load keyboard file.

``` java
Zxcvbn zxcvbn = new ZxcvbnBuilder()
        .dictionary(new DictionaryLoader("us_tv_and_film", new ClasspathResource("/com/nulabinc/zxcvbn/matchers/dictionarys/us_tv_and_film.txt")).load())
        .keyboard(new SlantedKeyboardLoader("qwerty", new ClasspathResource("/com/nulabinc/zxcvbn/matchers/keyboards/qwerty.txt")).load())
        .keyboard(new AlignedKeyboardLoader("keypad", new ClasspathResource("/com/nulabinc/zxcvbn/matchers/keyboards/keypad.txt")).load())
        .build();
```

#### Use resources get via HTTP

To use dictionary and keyboard files other than the classpath, implement the `Resource interface`.
This code is an example of getting and loading a file via HTTP(s).

``` java
URL dictionaryURL = new URL("https://example.com/foo/dictionary.txt");
Resource myDictionaryResource = new MyResourceOverHTTP(dictionaryURL);

URL keyboardURL = new URL("https://example.com/bar/keyboard.txt");
Resource myKeyboardURLResource = new MyResourceOverHTTP(keyboardURL);

Zxcvbn zxcvbn = new ZxcvbnBuilder()
        .dictionary(new DictionaryLoader("my_dictionary", myDictionaryResource).load())
        .keyboard(new SlantedKeyboardLoader("my_keyboard", myKeyboardURLResource).load())
        .build();

public class MyResourceOverHTTP implements Resource {

    private URL url;

    public MyResourceOverHTTP(URL url) {
        this.url = url;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        HttpURLConnection conn = (HttpURLConnection) this.url.openConnection();
        return conn.getInputStream();
    }
}
```

#### Use file resources other than classpath

This code is an example of using files in other directories than the classpath.

``` java
File dictionaryFile = new File("/home/foo/dictionary.txt");
Resource myDictionaryResource = new MyResourceFromFile(dictionaryFile);

File keyboardFile = new File("/home/bar/keyboard.txt");
Resource myKeyboardURLResource = new MyResourceFromFile(keyboardFile);

Zxcvbn zxcvbn = new ZxcvbnBuilder()
    .dictionary(new DictionaryLoader("my_dictionary", myDictionaryResource).load())
    .keyboard(new SlantedKeyboardLoader("my_keyboard", myKeyboardURLResource).load())
    .build();

public class MyResourceFromFile implements Resource {

    private File file;

    public MyResourceFromFile(File file) {
        this.file = file;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }
}
```

#### Use all default resources

`StandardDictionaries.loadAllDictionaries()` loads all default dictionary files.
`StandardDictionaries.loadAllKeyboards()` loads all default keyboard files.

``` java
Zxcvbn zxcvbn = new Zxcvbn();
```

or

``` java
Zxcvbn zxcvbn = new ZxcvbnBuilder()
    .dictionaries(StandardDictionaries.loadAllDictionaries())
    .keyboards(StandardKeyboards.loadAllKeyboards())
    .build();
```

#### Select from and use default resources

The following code selects some from the default dictionary files and keyboards.

``` java
Zxcvbn zxcvbn = new ZxcvbnBuilder()
    .dictionary(StandardDictionaries.ENGLISH_WIKIPEDIA_LOADER.load())
    .dictionary(StandardDictionaries.PASSWORDS_LOADER.load())
    .keyboard(StandardKeyboards.QWERTY_LOADER.load())
    .keyboard(StandardKeyboards.DVORAK_LOADER.load())
    .build();
```

### Localize feedback messages

The zxcvbn4j can be localized the english feedback message to other languages.

#### Localize each feedback

``` java
// Get the Strength instance.
Zxcvbn zxcvbn = new Zxcvbn();
Strength strength = zxcvbn.measure("This is password");

// Get the ResourceBundle based on the name and locale of the property file(※).
ResourceBundle resourceBundle = ResourceBundle.getBundle("This is bundle name", Locale.JAPAN);

// Feedback to pass the ResourceBundle. And to generate a localized Feedback.
Feedback feedback = strength.getFeedback();
Feedback localizedFeedback = feedback.withResourceBundle(resourceBundle);

// getSuggestions() and getWarning() returns localized feedback message.
List<String> localizedSuggestions = localizedFeedback.getSuggestions();
String localizedWarning = localizedFeedback.getWarning();
```

Defined Key and the message in the properties file. Reference the [messages.properties](https://github.com/nulab/zxcvbn4j/blob/master/src/main/resources/com/nulabinc/zxcvbn/messages.properties).

#### Localize each locale

``` java
Strength strength = zxcvbn.measure(password);
Feedback feedback = strength.getFeedback();

Map<Locale, ResourceBundle> messages = new HashMap<>();
messages.put(Locale.JAPANESE, ResourceBundle.getBundle("This is bundle name", Locale.JAPANESE));
messages.put(Locale.ITALIAN, ResourceBundle.getBundle("This is bundle name", Locale.ITALIAN));
Feedback replacedFeedback = feedback.replaceResourceBundle(messages);
```

## Requires Java

* Java 1.7+

## Using this library

- [Backlog](https://backlog.com/)
- [Cacoo](https://cacoo.com/)
- [Typetalk](https://typetalk.com/)
- [JetBrains Hub](https://www.jetbrains.com/hub/)
- [Cryptomator](https://cryptomator.org/)
- And many Open Source Software
  - https://github.com/search?q=com.nulab-inc+zxcvbn&type=code
  - https://mvnrepository.com/artifact/com.nulab-inc/zxcvbn/usages

## Bugs and Feedback

For bugs, questions and discussions please use the [GitHub Issues](https://github.com/nulab/zxcvbn4j/issues).

## License

MIT License

* http://www.opensource.org/licenses/mit-license.php
