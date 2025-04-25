package net.rml.auto.soap;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Provides methods to perform operations.
 */
public final class OperationUtils {

    private static final String SPLIT_REGEX = ", ?";
    private static final String VALUE_REGEX_URL = "\\[[A-Z_]+]";
    private static final String REPLACE_REGEX_SPACE_POINT_COMMA = "\\s+|,\\s*|\\.\\s*";
    private static final String EMPTY_STRING = "";
    private static final int FIRST_INDEX = 0;
    public static final String RANDOM = "Random";
    public static final String RANDOM_REFUND = "RANDOM_REFUND";
    public static final String IDEMPOTENCY_ID_KEY = "IDEMPOTENCY_ID_KEY";
    public static final String LARGE_RANDOM_STRING = "LargeRandomString";
    private static final int ONE_HUNDRED = 100;
    public static final String PERMANENT = "Permanent";

    /**
     * Initializes an instance of {@link OperationUtils}.
     */
    private OperationUtils() {
        //Default constructor.
    }

    /**
     * Gets enum columns of Database from string list.
     *
     * @param columns columns string.
     * @return list of Database columns.
     */
//    public static List<DBColumns> getDBEnumColumnList(final List<String> columns) {
//        return Arrays.stream(columns.get(0).split(", ?")).map(DBColumns::valueOf).collect(Collectors.toList());
//    }

    /**
     * Gets a string map after converting from <DBColumns, Sting> map.
     *
     * @param dataMap map to convert.
     * @return a string map after converting from <DBColumns, Sting> map
     */
//    public static Map<String, String> getStringMap(final Map<DBColumns, String> dataMap) {
//        return dataMap.entrySet().stream().collect(Collectors.toMap(entry -> entry.getKey().name(),
//                Map.Entry::getValue));
//    }

//    /**
//     * Gets request fields list from formatted String.
//     *
//     * @param fieldsMatch request fields formatted String.
//     * @return list of request fields.
//     */
//    public static List<ServiceFields> getRequestFieldsList(final List<String> fieldsMatch) {
//        return Arrays.stream(fieldsMatch.get(FIRST_INDEX).split(SPLIT_REGEX))
//                .map(ServiceFields::valueOf).collect(Collectors.toList());
//    }

//    /**
//     * Replaces values in the request data map using the values of the storage map.
//     *
//     * @param requestData request data map.
//     * @param storageMap  storage data map
//     * @param <T>         generic type.
//     * @return map with replaced values.
//     */
//    public static <T> Map<T, String> replaceMapValues(final Map<T, String> requestData,
//                                                      final Map<String, String> storageMap) {
//        Map<T, String> replacedMap = new HashMap<>();
//        requestData.forEach((field, value) -> replacedMap.put(field, replaceValue(value, storageMap)));
//        return replacedMap;
//    }

//    /**
//     * Replaces the corresponding value from storage map.
//     *
//     * @param value      value to be evaluated.
//     * @param storageMap storage data map.
//     * @return replaced string value.
//     */
//    public static String replaceValue(final String value, final Map<String, String> storageMap) {
//        Pattern pattern = Pattern.compile(VALUE_REGEX_URL);
//        Matcher matcher = pattern.matcher(value);
//        StringBuffer result = new StringBuffer();
//        while (matcher.find()) {
//            matcher.appendReplacement(result, storageMap.get(matcher.group()
//                    .replaceAll(RegexEnum.SQUARE_BRACKETS_REGEX.value(), EMPTY_STRING)));
//        }
//        matcher.appendTail(result);
//        return result.toString().isEmpty() ? value : result.toString();
//    }

    /**
     * Verifies if the given word contains the given characters.
     *
     * @param word             the word where searching.
     * @param charactersToFind the characters to find.
     * @return true if some of characters are in the given word otherwise false.
     */
    public static boolean containsCharacters(final String word, final String charactersToFind) {
        Pattern pattern = Pattern.compile(charactersToFind);
        Matcher matcher = pattern.matcher(word);
        return matcher.find();
    }


    /**
     * Gets the value between parentheses.
     *
     * @param word the word where get the value.
     * @return the value into parentheses.
     */
    public static String getValueBetweenParentheses(final String word) {
        Matcher m = Pattern.compile("\\((.*?)\\)").matcher(word);
        while (m.find()) {
            return (m.group(1));
        }
        return "";
    }

//    /**
//     * Gets request fields list from formatted String.
//     *
//     * @param fieldsMatch request fields formatted String.
//     * @return list of request fields.
//     */
//    public static List<DBColumns> getDBColumns(final List<String> fieldsMatch) {
//        return Arrays.stream(fieldsMatch.get(FIRST_INDEX).split(SPLIT_REGEX))
//                .map(DBColumns::valueOf).collect(Collectors.toList());
//    }

//    /**
//     * Gets response fields list from formatted String.
//     *
//     * @param fieldsMatch response fields formatted String.
//     * @return list of Service fields.
//     */
//    public static List<ServiceFields> getResponseField(final List<String> fieldsMatch) {
//        return Arrays.stream(fieldsMatch.get(FIRST_INDEX).split(SPLIT_REGEX))
//                .map(ServiceFields::valueOf).collect(Collectors.toList());
//    }

    /**
     * Splits a string list according some given criteria.
     *
     * @param fields          response fields formatted String.
     * @param criteriaToSplit criteria in order to split.
     * @return list of Service fields.
     */
    public static List<String> splitListByRegex(final List<String> fields, final String criteriaToSplit) {
        return Arrays.stream(fields.get(FIRST_INDEX).split(criteriaToSplit))
                .map(String::valueOf).collect(Collectors.toList());
    }

//    /**
//     * Gets unescaped string from a string that contains space, line brake, i.e. \n \r.
//     *
//     * @param commonString long string.
//     * @return unescaped string.
//     */
//    public static String getUnescapeString(final String commonString) {
//        return StringEscapeUtils.unescapeJava(commonString);
//    }

//    /**
//     * Replaces the value into [] of a string by userName of BeastConfig, i.e "any text [valueToReplace] any text".
//     *
//     * @param text text where will replace.
//     * @return text with userName of BeastConfig into [].
//     */
//    public static String replaceValueIntoBracketsByUserNameOfIDTPayConfig(final String text) {
//        String requestorUserName = String.format("[%s]", IDTPayConfig.getInstance().getUserName());
//        return text.replaceAll(RegexEnum.VALUE_INTO_BRACKETS.value(), requestorUserName);
//    }

    /**
     * Replaces the value into [] of a string by userName and serviceId for "any text [valueToReplace] any text".
     *
     * @param text text where will replace.
     * @return text with userName of BeastConfig into [].
     */
//    public static String replaceValueIntoBracketsByUserNameAndServiceIdOfIDTPayConfig(final String text) {
//        String requestorData = String.format("[%s-%s]", IDTPayConfig.getInstance().getUserName(),
//                IDTPayConfig.getInstance().getServiceId());
//        return text.replaceAll(RegexEnum.VALUE_INTO_BRACKETS.value(), requestorData);
//    }

//    /**
//     * Get the last word of a text without considering the following special characters brackets, point and comma.
//     *
//     * @param text                where will replace.
//     * @param squareBracketsRegex {@link RegexEnum}
//     * @return text.
//     */
//    public static String getLastWordOfTextIntoCharacters(final String text, final RegexEnum squareBracketsRegex) {
//        List<String> list = Arrays.asList(text.replaceAll(squareBracketsRegex.value(), "")
//                .split(REPLACE_REGEX_SPACE_POINT_COMMA));
//        return list.get(list.size() - 1).trim();
//    }

//    /**
//     * Get the value between the given tags into a text.
//     *
//     * @param text     key of paymentInfo
//     * @param startTag tag before the value which wants to get.
//     * @param endTag   tag after the value which wants to get.
//     * @return value between tags.
//     */
//    public static String getValueBetweenTags(final String text, final String startTag, final String endTag) {
//        final Pattern pattern = Pattern.compile(String.format("%s(.+?)%s", startTag, endTag), Pattern.DOTALL);
//        final Matcher matcher = pattern.matcher(text);
//        matcher.find();
//        return matcher.group(1);
//    }

    /**
     * Causes the currently executing thread to sleep (temporarily cease execution).
     *
     * @param millis the length of time to sleep in milliseconds
     */
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Counts the occurrences of a word in a string.
     *
     * @param wordToFind the wanted word to find in a text.
     * @param text       The text where the word will be searched
     * @return value between tags.
     */
    public static int countOccurrencesOfWordInString(final String wordToFind, final String text) {
        Pattern p = Pattern.compile(wordToFind);
        Matcher m = p.matcher(text);
        int counter = 0;
        while (m.find()) {
            counter++;
        }
        return counter;
    }

    /**
     * Sorts a list of strings as integer in ascending or descending order.
     *
     * @param dataToSort The list to be sorted
     * @param sortOrder  the sort order value, ascending or descending order
     * @return A list of integer as string sorted by giving order.
     */
//    public static List<String> sortIntegerList(final List<String> dataToSort, final TransFields sortOrder) {
//        List<Integer> dataIntToSort = dataToSort.stream()
//                .map(s -> Integer.parseInt(s))
//                .collect(Collectors.toList());
//
//        if (sortOrder.equals(TransFields.DESC)) {
//            Collections.sort(dataIntToSort, Collections.reverseOrder());
//        } else {
//            Collections.sort(dataIntToSort);
//        }
//
//        List<String> stringListSorted = dataIntToSort.stream()
//                .map(s -> String.valueOf(s))
//                .collect(Collectors.toList());
//        return stringListSorted;
//    }

    /**
     * Sorta a list of strings as string in ascending or descending order.
     *
     * @param dataToSort The list to be sorted
     * @param sortOrder  the sort order value, ascending or descending order
     * @return A list of string sorted by giving order.
     */
//    public static List<String> sortStringList(final List<String> dataToSort, final TransFields sortOrder) {
//        List<String> dataSorted = new ArrayList<>(dataToSort);
//        if (sortOrder.equals(TransFields.DESC)) {
//            Collections.sort(dataSorted, Collections.reverseOrder());
//        } else {
//            Collections.sort(dataSorted);
//        }
//        return dataSorted;
//    }


    /**
     * Gets amount in a cents with a given pattern (format).
     *
     * @param amount  The amount
     * @param pattern format of decimals
     * @return The amount in a cents with two decimals.
     */
    public static String getCentAmountWithTwoDecimals(final String amount, final String pattern) {
        double amountDouble = Double.parseDouble(amount) / ONE_HUNDRED;
        return getDoubleValueWithDecimalFormat(amountDouble, pattern);
    }

    /**
     * Gets double amount with decimal part.
     *
     * @param amountDouble The amount
     * @param pattern      format of decimals
     * @return The amount with decimal part.
     */
    public static String getDoubleValueWithDecimalFormat(final double amountDouble, final String pattern) {
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(amountDouble);
    }

    /**
     * Verify if the scenario will skip or not.
     *
     * @param tags scenario's tags
     * @return true if scenario skips otherwise false
     */
//    public static boolean isSkippedByIdtpayUser(final List<String> tags) {
//        boolean isSkipped = false;
//        String user = IDTPayConfig.getInstance().getUserName().toLowerCase();
//        if (user.equals("qawp") && tags.contains("@skipped_awp")) {
//            isSkipped = true;
//        } else if (user.equals("qastripe") && tags.contains("@skipped_stripe")) {
//            isSkipped = true;
//        } else if (tags.contains("@skipped_idtpay")) {
//            isSkipped = true;
//        }
//        return isSkipped;
//    }

    /**
     * Gets all keys valued of a map in a string.
     *
     * @param map The string map
     * @return all keys valued of a map in a string.
     */
    public static String getAllKeysOfMapAsString(final Map<String, String> map) {
        StringBuffer stringBuffer = new StringBuffer();
        map.forEach((key, value) -> {
            stringBuffer.append(key);
            stringBuffer.append(";");
        });
        return stringBuffer.toString();
    }

    /**
     * Replaces the domain of url by other value.
     *
     * @param originUrl
     * @param newDomain
     * @return url with new domain
     */
    public static String replaceDomainOfUrl(String originUrl, String newDomain) {
        return newDomain.isEmpty() ? originUrl : originUrl.replaceFirst("^(https?://[^:/]+)", newDomain);
    }
}
