package service.response;

import com.vng.wmb.lib.json.JacksonHelper;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.ObjectMapper;

@JsonPropertyOrder({"error_code", "error_message", "data"})
public class DataResponse {

    @JsonProperty("error_code")
    private final int errorCode;
    @JsonProperty("error_message")
    private final String errorMessage;
    @JsonProperty("data")
    private Object data;
    private DataResponse.DataType dataType = DataResponse.DataType.NORMAL;
    private boolean isEscape = true;

    public DataResponse() {
        errorCode = 0;
        errorMessage = "";
    }

    public DataResponse(int error, String message) {
        this.errorCode = error;
        this.errorMessage = message;
    }

    public DataResponse(int error, String message, String data) {
        this.errorCode = error;
        this.errorMessage = message;
        this.data = data;
    }

    public DataResponse(Object data) {
        this.errorCode = 0;
        this.errorMessage = "Successful.";
        this.data = data;
    }

    public DataResponse(Object data, DataResponse.DataType d, boolean isEscape) {
        this.errorCode = 0;
        this.errorMessage = "Successful.";
        this.data = data;
        this.dataType = d;
        this.isEscape = isEscape;
    }

    @JsonIgnore
    public int getError() {
        return this.errorCode;
    }

    @JsonIgnore
    public String getMessage() {
        return this.errorMessage;
    }

    @JsonIgnore
    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.setData(data, DataResponse.DataType.NORMAL);
    }

    public void setData(Object data, DataResponse.DataType dataType) {
        this.data = data;
        this.dataType = dataType;
    }

    @JsonIgnore
    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    @JsonIgnore
    public boolean isEscape() {
        return isEscape;
    }

    public void setEscape(boolean isEscape) {
        this.isEscape = isEscape;
    }

    @Override
    public String toString() {
        return DataResponse.toJsonString(this);
    }

    /**
     * ********************* STATIC ***************************************
     */
    public enum DataType {

        NORMAL, JSON_STR, UNSURE
    };

    public static DataResponse getSuccessMsg() {
        return new DataResponse(0, "Successful.");
    }

    public static String toJsonString(DataResponse apiMessage) {
        try {
            ObjectMapper mapper = null;

            if (apiMessage.isEscape()) {
                mapper = JacksonHelper.getEscapedInstance();
            } else {
                mapper = JacksonHelper.getUnescapedInstance();
            }

            if (mapper != null) {
                if (apiMessage.getDataType() == DataResponse.DataType.JSON_STR) {
                    return "{\"error_code\":" + apiMessage.getError() + ",\"error_message\":\"" + apiMessage.getMessage() + "\", \"data\":" + apiMessage.getData() + "}";
                } else if (apiMessage.getDataType() == DataResponse.DataType.UNSURE && apiMessage.getData() instanceof String && isJsonString((String) apiMessage.getData())) {
                    return "{\"error_code\":" + apiMessage.getError() + ",\"error_message\":\"" + apiMessage.getMessage() + "\", \"data\":" + apiMessage.getData() + "}";
                }

                // DATA_TYPE_NORMAL
                return mapper.writeValueAsString(apiMessage);
            }
        } catch (Exception e) {
        }

        return "";
    }

    public static boolean isJsonString(String str) {
        try {
            ObjectMapper mapper = JacksonHelper.getUnescapedInstance();
            mapper.readTree(str);

            return true;
        } catch (Exception e) {
        }

        return false;
    }

    public static final DataResponse INVALID_PUBLIC_KEY = new DataResponse(101, "The API key submitted is not associated with any known application");
    public static final DataResponse SESSION_EXPIRE = new DataResponse(102, "The session key was improperly submitted or has reached its timeout. Direct the user to log in again to obtain another key");
    public static final DataResponse INVALID_SIGNATURE = new DataResponse(103, "Incorect signature");
    public static final DataResponse METHOD_NOT_FOUND = new DataResponse(104, "Method not found");
    public static final DataResponse MISSING_PARAM = new DataResponse(105, "One or more required parameter is not present");
    public static final DataResponse UNKNOWN_EXCEPTION = new DataResponse(106, "Unknown exception");
    public static final DataResponse PERMISSION_EXCEPTION = new DataResponse(107, "Your application is not allowed to use this API");
    public static final DataResponse BANNED_EXCEPTION = new DataResponse(108, "User is banned");
    public static final DataResponse PARAM_ERROR = new DataResponse(109, "error param");
    public static final DataResponse API_RESPONSE_EMPTY = new DataResponse(110, "API's Response is empty");
    public static final DataResponse NOT_ADMIN_EXCEPTION = new DataResponse(111, "User is not admin");

}
