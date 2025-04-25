package net.rml.auto.core.config;

import com.google.gson.stream.JsonReader;
import net.rml.auto.core.utils.PathUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

/**
        * It is in charge to read the environment variables for Beast automated tests execution.
        */
public final class RmlConfig {
    private static final Logger LOGGER = LogManager.getLogger(RmlConfig.class.getSimpleName());
    private static final String PROP_FILE = "gradle.properties";
    private static final String ENVIRONMENT = "environmentName";
    private static final String USER_TYPE = "userType";
    private static final String USER_NAME = "Username";
    private static final String PASSWORD = "Password";
    private static final String ENVIRONMENT_CONFIG = "environmentConfig";
    private static final String USER_INFO_CONFIG = "userInfoConfig";
    private static final String FORCE_EXECUTION = "forceExecution";
    private static final String FILES_FOLDER_GENERATED = "filesFolderGenerated";
    private static final String FILES_FOLDER_TEST_DATA = "filesFolderTestData";
    private static final String FILES_FOLDER_TEST_DATA_REMOTE = "filesFolderTestDataRemote";
    private static final String SHARED_VANTIV_FOLDER = "sharedVantivFolder";
    private static final String SETTLEMENT_TXN_JSON_FILE_NAME = "settlementTxnJsonFileName";
    private static final String MONEY_APP_TXN_JSON_FILE_NAME = "moneyAppTxnJsonFileName";
    private static final String FILES_FOLDER_RESOURCE = "filesFolderResource";
    private static final String PAYMENT_DATA_JSON_FILE_NAME = "paymentDataJsonFileName";
    private static final String SERVICES = "Services";
    private static final String VAULT_SERVICEHOST = "Vault";
    private static final String TRANS_SERVICEHOST = "Trans";
    private static final String USERS = "Users";
    private static final String REST = "rest";
    private static final String SCHEMA_FOLDER = "schema_folder";
    private static final String CARD_VALIDATOR = "CardValidator";
    private static final String SERVICE_URL = "ServiceUrl";
    private static final String IDT_PAY_URL = "idtpaySiteUrl";
    private static final String GOOGLE_PAY_URL = "googlePayUrl";
    private static final String SECURITY_FOLDER = "securityFolder";
    private static final String TRUST_STORE = "trustStore";
    private static final String TRUST_STORE_PASSWORD = "trustStorePassword";
    private static final String KEY_STORE = "keyStore";
    private static final String KEY_STORE_PASSWORD = "keyStorePassword";
    private static final String SUT = "sut";
    private static final String PAYMENT_DATA_PATH = "paymentDataPath";
    private static final String SIFT = "sift";
    private static final String SIFT_URL = "siftSiteUrl";
    private static final String GENERATE_REPORT = "generateReport";
    private static final String AM_USER_NAME = "am_username";
    private static final String AM_PASSWORD = "am_password";
    private static final String IDT_PAY_BASE_URL = "idtPayBaseUrl";
    private static final String INT_IDT_PAY_TOK_BASE_URL = "internalIdtPayTokenizerBaseUrl";
    private static final String PRIV_IDT_PAY_TOK_BASE_URL = "privateIdtPayTokenizerBaseUrl";
    private static final String PII_IDT_PAY_TOK_BASE_URL = "piiPrivateIdtPayTokenizerBaseUrl";
    private static final String PRIV_TOK_BASE_URL = "privateTokenizationBaseUrl";
    private static final String PUBLI_TOK_BASE_URL = "publicTokenizationBaseUrl";
    private static final String IDT_PAY_WEB_BASE_URL = "idtPayWebBaseUrl";
    private static final String DE_TOK_WEB_BASE_URL = "detokenizerWebBaseUrl";
    private static final String K8_NODE_IP = "k8NodeIp";
    private static final String STRIPE_AUTH_SECRET_KEY = "stripeAuthSecretKey";
    private static final String MT_TERMINAL_AUTH_KEY = "mtTerminalAuthKey";
    private static final String MT_STRIPE_CONNECTED_ACCOUNT = "mtStripeConnectedAccount";

    private static RmlConfig instance;
    private PropertiesReader propertiesReader;
    private JsonReader environmentConfig;

    /**
            * Initializes an instance of {@link RmlConfig}.
            */
    private RmlConfig() {
        propertiesReader = new PropertiesReader(PROP_FILE);
//        environmentConfig = new JsonReader(PathUtils.getRelativePath(getEnvConfigPath()));
    }

    /**
            * Initializes the Singleton {@link RmlConfig} instance.
     *
             * @return singleton instance.
            */
    public static RmlConfig getInstance() {
        if (Objects.isNull(instance)) {
            instance = new RmlConfig();
        }
        return instance;
    }

    /**
            * Gets the Beast tests execution environment name.
     *
             * @return execution environment name.
     */
    public String getEnvironment() {
        return propertiesReader.getEnv(ENVIRONMENT);
    }

    /**
            * Gets the system under test's name.
            *
            * @return the system under test's name.
            */
    public String getSystemUnderTest() {
        return propertiesReader.getEnv(SUT);
    }

    /**
            * Gets the environment json path.
     *
             * @return path of environment.json file.
            */
    public String getEnvConfigPath() {
        return propertiesReader.getEnv(ENVIRONMENT_CONFIG);
    }

//    /**
//            * Gets the user name to execute the requests according to environment and userType.
//     *
//             * @return user name's value
//            */
//    public String getUserName() {
//        return environmentConfig.getValueOfArray(getEnvironment(), USERS, getUserType(), USER_NAME);
//    }

//    /**
//            * Set the user's password to execute the requests according to environment and userType.
//            *
//            * @return password's value
//            */
//    public String getPassword() {
//        return environmentConfig.getValueOfArray(getEnvironment(), USERS, getUserType(), PASSWORD);
//    }

//    /**
//            * Gets serviceId associated to requestor user to execute the request according to environment and userType.
//     *
//             * @return serviceId's value of requestor user
//            */
//    public String getServiceId() {
//        return environmentConfig.getValueOfArray(getEnvironment(), USERS, getUserType(),
//                ServiceFields.SERVICE_ID.getValue());
//    }

//    /**
//            * Gets currencyId associated to requestor user to execute the request according to environment and userType.
//     *
//             * @return currencyId's value of requestor user
//            */
//    public String getCurrencyId() {
//        return environmentConfig.getValueOfArray(getEnvironment(), USERS, getUserType(),
//                ServiceFields.CURRENCY_ID.getValue());
//    }

//    /**
//            * Gets merchantNumber associated to requestor user to execute the request according to environment and userType.
//     *
//             * @return merchantNumber's value of requestor user
//            */
//    public String getMerchantNumber() {
//        return environmentConfig.getValueOfArray(getEnvironment(), USERS, getUserType(),
//                ServiceFields.MERCHANT_NUMBER.getValue());
//    }

//    /**
//            * Gets cvv associated to requestor user to execute the request according to environment and userType.
//     *
//             * @return cvv's value of requestor user
//            */
//    public String getCvv2() {
//        return environmentConfig.getValueOfArray(getEnvironment(), USERS, getUserType(),
//                ServiceFields.CCV2.getValue());
//    }

//    /**
//            * Gets requestor user's data according to environment and userType.
//            *
//            * @param serviceField {@link ServiceFields}
//     * @return requestor user's data from enviroment json i.e. serviceId, currencyId.
//            */
//    public String getDataOfRequestorUser(final ServiceFields serviceField) {
//        return environmentConfig.getValueOfArray(getEnvironment(), USERS, getUserType(), serviceField.getValue());
//    }

    /**
            * @return environment {@link JsonReader}.
            */
    public JsonReader getEnvJsonReader() {
        return environmentConfig;
    }

//    /**
//            * Get vault service host url.
//     *
//             * @return service host url
//     */
//    public String getVaultServicehost() {
//        return environmentConfig.getValueOfKeyIntoTwoJsonObjects(getEnvironment(), SERVICES, VAULT_SERVICEHOST);
//    }

    /**
            * Get user type according to environment.
            *
            * @return user type.
            */
    public String getUserType() {
        return propertiesReader.getEnv(USER_TYPE);
    }
//    /**
//            * Get trans service host url.
//     *
//             * @return tran service host
//     */
//    public String getTransServicehost() {
//        return environmentConfig.getValueOfKeyIntoTwoJsonObjects(getEnvironment(), SERVICES, TRANS_SERVICEHOST);
//    }

//    /**
//            * Get CardValidator service host url.
//     *
//             * @return tran service host
//     */
//    public String getCardValidatorServicehost() {
//        return environmentConfig.getValueOfKeyIntoTwoJsonObjects(getEnvironment(), CARD_VALIDATOR, SERVICE_URL);
//    }

    /**
            * Gets the propertiesReader.
     *
             * @return {@link PropertiesReader}
     */
    public PropertiesReader getPropertiesReader() {
        return propertiesReader;
    }

    /**
            * Gets the force execution status flag value to run skipped test .
     *
             * @return true if the skipped test will run otherwise false.
            */
    public boolean getForceExecution() {
        return Boolean.valueOf(propertiesReader.getEnv(FORCE_EXECUTION));
    }


    /**
            * Gets generated files folder path.
     *
             * @return folder path.
            */
    public String getGeneratedFolder() {
        return propertiesReader.getEnv(FILES_FOLDER_GENERATED);
    }

    /**
            * Gets shared vantiv folder path.
     *
             * @return folder path.
            */
    public String getSharedVantivFolder() {
        return propertiesReader.getEnv(SHARED_VANTIV_FOLDER);
    }

    /**
            * Gets resource folder path.
            *
            * @return folder path.
            */
    public String getResourceFolder() {
        return propertiesReader.getEnv(FILES_FOLDER_RESOURCE);
    }

    /**
            * Gets test data folder path.
     *
             * @return folder path.
            */
    public String getTestDataFolder() {
        return propertiesReader.getEnv(FILES_FOLDER_TEST_DATA);
    }

    /**
            * Gets test data folder path remote.
            *
            * @return folder path.
            */
    public String getTestDataFolderRemote() {
        return propertiesReader.getEnv(FILES_FOLDER_TEST_DATA_REMOTE);
    }

    /**
            * Gets name of settlement transaction json file when the transactions' data is stored.
            *
            * @return name of settlement txns json file.
            */
    public String getSettleTxnJsonFileName() {
        return propertiesReader.getEnv(SETTLEMENT_TXN_JSON_FILE_NAME);
    }

    /**
            * Gets name of money app json file when the transactions' data is stored.
            *
            * @return name of money app json file.
            */
    public String getMoneyAppTxnJsonFileName() {
        return propertiesReader.getEnv(MONEY_APP_TXN_JSON_FILE_NAME);
    }

    /**
            * Gets name of payment data json file.
     *
             * @return name of payment data json file.
            */
    public String getPaymentDataJsonFileName() {
        return propertiesReader.getEnv(PAYMENT_DATA_JSON_FILE_NAME);
    }

    /**
            * Gets security folder for ssl certificates of JVM.
            *
            * @return security folder path.
     */
    public String getSecurityFolder() {
        return propertiesReader.getEnv(SECURITY_FOLDER);
    }

    /**
            * Gets trust store file name for ssl certificates of JVM.
            *
            * @return trust store file name.
            */
    public String getTrustStore() {
        return propertiesReader.getEnv(TRUST_STORE);
    }

    /**
            * Gets trust store password for ssl certificates of JVM.
            *
            * @return trust store password.
     */
    public String getTrustStorePassword() {
        return propertiesReader.getEnv(TRUST_STORE_PASSWORD);
    }

    /**
            * Gets key store file name for ssl certificates of JVM.
            *
            * @return key store file name.
            */
    public String getKeyStore() {
        return propertiesReader.getEnv(KEY_STORE);
    }
    /**
            * Gets key store passord for ssl certificates of JVM.
            *
            * @return key store password.
     */
    public String getKeyStorePassword() {
        return propertiesReader.getEnv(KEY_STORE_PASSWORD);
    }

//    /**
//            * Gets a Json Object from the environment json file.
//     *
//             * @param field Json object identifier.
//            * @return Object with the value.
//            */
//    public JSONObject getConfigObject(final String field) {
//        return (JSONObject) environmentConfig.getJSONMainObject().get(field);
//    }
//
//    /**
//            * Gets the base url for rest.
//     *
//             * @param service service key.
//     * @return base url.
//            */
//    public String getRestBaseUrl(final String service) {
//        String originalUrl = environmentConfig.getValueOfKeyIntoTwoJsonObjects(getEnvironment(), REST, service);
//        String newRestBaseUrl = getNewRestBaseUrl(service);
//        return OperationUtils.replaceDomainOfUrl(originalUrl, newRestBaseUrl);
//    }

    /**
            * Gets stripe auth secret key.
     *
             * @return stripe auth secret key.
            */
    public String getStripeAuthSecretKey() {
        return propertiesReader.getEnv(STRIPE_AUTH_SECRET_KEY);
    }

    /**
            * Gets MT Terminal auth key.
     *
             * @return mt Terminal auth key.
            */
    public String getMtTerminalAuthKey() {
        return propertiesReader.getEnv(MT_TERMINAL_AUTH_KEY);
    }

    /**
            * Gets mt Stripe connected account.
     *
             * @return mt Stripe connected account.
            */
    public String getMtStripeConnectedAccount() {
        return propertiesReader.getEnv(MT_STRIPE_CONNECTED_ACCOUNT);
    }

//    /**
//            * Gets the absolute schema path.
//     *
//             * @return schema path.
//            */
//    public String getSchemaPath() {
//        return PathUtils.getAbsPath(environmentConfig
//                .getValueOfKeyIntoTwoJsonObjects(getEnvironment(), REST, SCHEMA_FOLDER));
//    }

//    /**
//            * Gets url of idtPay site.
//     *
//             * @return url of idtPay site.
//            */
//    public String getIdtPaySiteUrl() {
//        return environmentConfig.getValueOfKeyIntoOneJsonObjects(getEnvironment(), IDT_PAY_URL);
//    }

//    /**
//            * Gets url of Google Pay start page.
//     *
//             * @return url of Google pay page.
//     */
//    public String getGooglePayUrl() {
//        return environmentConfig.getValueOfKeyIntoOneJsonObjects(getEnvironment(), GOOGLE_PAY_URL);
//    }

//    /**
//            * Gets path of payment data by environment.
//     *
//             * @return path of payment data.
//            */
//    public String getPaymentInfoPathByEnv() {
//        return environmentConfig.getValueOfKeyIntoOneJsonObjects(getEnvironment(), PAYMENT_DATA_PATH);
//    }

//    /**
//            * Gets url of Sift site.
//     *
//             * @return url of idtPay site.
//            */
//    public String getSiftSiteUrl() {
//        return environmentConfig.getValueOfKeyIntoTwoJsonObjects(getEnvironment(), "sift", SIFT_URL);
//    }
//
//    /**
//            * Gets admin's username to log in toSift site.
//            *
//            * @return admin's username.
//            */
//    public String getSiftAdminUsername() {
//
//        return environmentConfig.getValueOfKeyIntoTwoJsonObjects(getEnvironment(),
//                SIFT, "admin_username");
//    }
//
//    /**
//            * Gets admin's password to log in toSift site.
//            *
//            * @return admin's password.
//            */
//    public String getSiftAdminPassword() {
//        return environmentConfig.getValueOfKeyIntoTwoJsonObjects(getEnvironment(),
//                SIFT, "admin_password");
//    }

    /**
            * Gets the generate report flag value.
            *
            * @return the generate report flag value.
     */
    public boolean getGenerateReport() {
        return Boolean.valueOf(propertiesReader.getEnv(GENERATE_REPORT));
    }

    /**
            * Gets am user name.
            *
            * @return am's user name.
            */
    public String getAmUsername() {
        return propertiesReader.getEnv(AM_USER_NAME);
    }

    /**
            * Gets am password.
     *
             * @return am's password.
            */
    public String getAmPassword() {
        return propertiesReader.getEnv(AM_PASSWORD);
    }
//    /**
//            * Gets requestor user's data according to environment and userType.
//            *
//            * @param serviceField {@link TransRestFields}
//     * @return requestor user's data from environment json i.e. serviceId, currencyId.
//            */
//    public String getDataOfRequestorUser(final TransRestFields serviceField) {
//        return environmentConfig.getValueOfArray(EnvironmentEnum.IDTPAY_QA.value(),
//                USERS, getUserType(), serviceField.getValue());
//    }

    /**
            * Gets Idt Pay Base Url
     *
             * @return Idt Pay Base Url.
            */
    public String getIdtPayBaseUrl() {
        return propertiesReader.getEnv(IDT_PAY_BASE_URL);
    }

    /**
            * Gets Internal Idt Pay Tokenizer Base Url
     *
             * @return Internal Idt Pay Tokenizer Base Url.
            */
    public String getInternalIdtPayTokenizerBaseUrl() {
        return propertiesReader.getEnv(INT_IDT_PAY_TOK_BASE_URL);
    }

    /**
            * Gets Private Idt Pay Tokenizer Base Url
     *
             * @return Private Idt Pay Tokenizer Base Url.
            */
    public String getPrivateIdtPayTokenizerBaseUrl() {
        return propertiesReader.getEnv(PRIV_IDT_PAY_TOK_BASE_URL);
    }

    /**
            * Gets Pii Private Idt Pay Tokenizer Base Url
     *
             * @return Pii Private Idt Pay Tokenizer Base Url.
     */
    public String getPiiPrivateIdtPayTokenizerBaseUrl() {
        return propertiesReader.getEnv(PII_IDT_PAY_TOK_BASE_URL);
    }

    /**
            * Gets Private Tokenization Base Url
     *
             * @return Private Tokenization Base Url.
            */
    public String getPrivateTokenizationBaseUrl() {
        return propertiesReader.getEnv(PRIV_TOK_BASE_URL);
    }

    /**
            * Gets Public Tokenization Base Url
     *
             * @return Public Tokenization Base Url.
            */
    public String getPublicTokenizationBaseUrl() {
        return propertiesReader.getEnv(PUBLI_TOK_BASE_URL);
    }

    /**
            * Gets Idt Pay Web BaseUrl
     *
             * @return Idt Pay Web BaseUrl.
            */
    public String getIdtPayWebBaseUrl() {
        return propertiesReader.getEnv(IDT_PAY_WEB_BASE_URL);
    }

    /**
            * Gets K8 Node IP
     *
             * @return K8 Node IP
     */
    public String getK8NodeIP() {
        return propertiesReader.getEnv(K8_NODE_IP);
    }

    /**
            * Gets Detokenizer Web Base Url
     *
             * @return Detokenizer Web Base Url.
            */
    public String getDetokenizerWebBaseUrl() {
        return propertiesReader.getEnv(DE_TOK_WEB_BASE_URL);
    }
    /**
            * Gets new rest base url when sending this value through command
     *
             * @param service
     * @return new rest base url when sending this value through command.
     */
    private String getNewRestBaseUrl(String service) {
        String restBaseUrl;
        switch (service) {
            case "ipayaws":
            case "idt_authentication":
            case "idt_settle":
            case "ipay_arn":
            case "ipay_change_status":
            case "ipay_switch_processor":
            case "ipay_trans_track":
            case "ipay_track_transaction":
            case "ipay_transaction":
            case "ipay_trans_search":
            case "ipay_ach_transaction_reverse":
            case "ipay_estimate_deposit_date":
            case "ipay_trans_processCCpayment":
            case "reverse_transaction":
            case "ipay_trans_processCreditCard":
            case "ipay_vault_by_handle":
            case "ipay_bin_by_handle":
            case "blacklist_put":
            case "blacklist_search":
            case "stripe_webhook_event_update":
            case "ipay_process_wallet_pay":
            case "tabapay_cardinfo":
            case "tabapay_payout_payment":
            case "tabapay_payout":
            case "lookups_merchant":
            case "reset_password":
            case "process_ach_payment":
            case "process_ach":
            case "ipay_process_refund":
            case "efw_stripe_webhook":
            case "process_sofort":
            case "cancel_non_settled_trans":
            case "CHECKOUT.ORDER.APPROVED":
            case "ach_settlement_pick_up_pending":
            case "unlock_test_login_attempts_api_user":
            case "track_transactions":
            case "ipay_audit":
                restBaseUrl = getIdtPayBaseUrl();
                break;
            case "tokenizerPublic":
            case "idtpay_tokenizer_health":
                restBaseUrl = getInternalIdtPayTokenizerBaseUrl();
                break;
            case "tokenizerPrivate":
                restBaseUrl = getPrivateIdtPayTokenizerBaseUrl();
                break;
            case "tokenizerPIIPrivate":
                restBaseUrl = getPiiPrivateIdtPayTokenizerBaseUrl();
                break;
            case "idt_tokenizationPrivSecret":
            case "idt_tokenizationPrivate":
                restBaseUrl = getPrivateTokenizationBaseUrl();
                break;
            case "idt_tokenizationPublic":
                restBaseUrl = getPublicTokenizationBaseUrl();
                break;
            case "ipayweb_manage_lookup_tables":
            case "payclient_switch_prc":
            case "payclient_change_status":
            case "idtpaySiteUrl":
                restBaseUrl = getIdtPayWebBaseUrl();
                break;
            case "yp_detokenizer_health":
                restBaseUrl = getDetokenizerWebBaseUrl();
                break;
            default:
                restBaseUrl = "";
                break;
        }
        return restBaseUrl;
    }

//    /**
//            * Gets user name by user type from environment json
//     *
//             * @param userType
//     * @return user name
//     */
//    public String getUserNameByTypeFromEnvironmentJson(String userType) {
//        return environmentConfig.getValueOfArray(getEnvironment(), USERS, userType, USER_NAME); }
//
//    /**
//     * Gets user password by user type from environment json
//     *
//     * @param userType
//     * @return user password
//     */
//    public String getUserPasswordByTypeFromEnvironmentJson(String userType) {
//        return environmentConfig.getValueOfArray(getEnvironment(), USERS, userType, PASSWORD); }
}