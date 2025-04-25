//package net.rml.auto.core.utils;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.Iterator;
//
///**
// * Handles Json file.
// */
//public class JsonReader {
//    private static final Logger LOGGER = LogManager.getLogger(JsonReader.class.getSimpleName());
////    private JSONObject jsonObjectMain;
//
//    /**
//     * Initializes an instance of {@link JsonReader}.
//     *
//     * @param filePath path of json file.
//     */
//    public JsonReader(final String filePath) {
//        LOGGER.info(String.format("Reading json: %s", filePath));
//        parseJSON(filePath);
//    }
//
//    /**
//     * Parses the json file into a JSONObject.
//     *
//     * @param filePath path of file.
//     */
//    private void parseJSON(final String filePath) {
//        try {
//            FileReader reader = new FileReader(filePath);
//            JSONParser jsonParser = new JSONParser();
//            jsonObjectMain = (JSONObject) jsonParser.parse(reader);
//        } catch (FileNotFoundException ex) {
//            LOGGER.error("FileNotFoundException when reading the configuration file ", ex);
//        } catch (ParseException ex) {
//            LOGGER.error("ParseException when reading the configuration file ", ex);
//        } catch (IOException ex) {
//            LOGGER.error("IOException when reading the configuration file ", ex);
//        } catch (NullPointerException ex) {
//            LOGGER.error("NullPointerException when reading the configuration file ", ex);
//        }
//    }
//
//    /**
//     * Gets the jsonObject value given the key from the main json object.
//     *
//     * @param key the key of json object.
//     * @return jsonObject {@link JSONObject}.
//     */
//    private JSONObject getJSONMainObject(final String key) {
//        return (JSONObject) jsonObjectMain.get(key);
//    }
//
//    /**
//     * Gets the value of key which is into jsonObject and it is into other jsonObject.
//     *
//     * @param objectName name of object.
//     * @param objectKey  key of object
//     * @param key        key of json object.
//     * @return value of key according to the given object name and object key.
//     */
//    public String getValueOfKeyIntoTwoJsonObjects(final String objectName, final String objectKey, final String key) {
//        JSONObject envObject = getJSONMainObject(objectName);
//        JSONObject object = (JSONObject) envObject.get(objectKey);
//        return (String) object.get(key);
//    }
//
//    /**
//     * Gets the value of key which is into jsonObject.
//     *
//     * @param objectKey key of object
//     * @param key       key of json object.
//     * @return value of key according to the given object name and object key.
//     */
//    public String getValueOfKeyIntoOneJsonObjects(final String objectKey, final String key) {
//
//        JSONObject envObject = getJSONMainObject(objectKey);
//        if (envObject == null) {
//            LOGGER.warn(String.format("Requested Key doesn't exist! please make sure key "
//                    + "named %s is properly configured!", key));
//            return "";
//        }
//        return envObject.get(key) == null ? "null" : String.valueOf(envObject.get(key));
//    }
//
//    /**
//     * Gets the main json object.
//     *
//     * @return jsonObject {@link JSONObject}.
//     */
//    public JSONObject getJSONMainObject() {
//        return jsonObjectMain;
//    }
//
//    /**
//     * Gets a value from jsonArray.
//     *
//     * @param objectName     name of object.
//     * @param objectKey      key of object
//     * @param arrayObjectKey key of array object.
//     * @param parameter      value of parameter into arrayObject's row.
//     * @return value of jsonArray.
//     */
//    public String getValueOfArray(final String objectName, final String objectKey, final String arrayObjectKey,
//                                  final String parameter) {
//        JSONObject envObject = getJSONMainObject(objectName);
//        JSONArray jsonArray = (JSONArray) envObject.get(objectKey);
//        Iterator iterator = jsonArray.iterator();
//        while (iterator.hasNext()) {
//            JSONObject object = ((JSONObject) iterator.next());
//            if (object.containsKey(arrayObjectKey)) {
//                JSONObject object1 = (JSONObject) object.get(arrayObjectKey);
//                return (String) object1.get(parameter);
//            }
//        }
//        return "";
//    }
//}
