package com.vms.server.exception;

public enum ErrorCode {
    INVALID_USER("001", "The account is not authorized to use this feature."),
    INVALID_EXTEND("002", "Undefined extension."),
    INVALID_FILENAME("003", "Invalid filename."),
    INVALID_FILENAME_PROCESS("004", "Invalid filename. (Please check the PROCESS name.)"),
    INVALID_FILENAME_DATE("005", "Invalid filename. (Please confirm that the date is in the YYYYMMDDHHMM format.)"),
    OPERATION_NOT_EXISTS("006", "Operation information does not exist."),
    SUBCONTRACT_NOT_EXITS("007", "Subcontractor information does not exist."),
    DEVICE_NOT_EXITS("008", "Device information (DEVICE, PRODUCT) does not exist."),
    FILE_MOVE_ERROR("009", "Cannot move the file."),
    FILE_NOT_FOUND("010", "The specified file could not be found."),
    CELL_NUMBER_CAST_ERROR("011", "(Another type is entered in row %d column %s, not a number.)"),
    CELL_STRING_CAST_ERROR("012", "(Another type is entered in row %d column %s, not a string.)"),
    CELL_CAST_ERROR("013", "(The type in row %d column %s is incorrect.)"),
    SQL_ERROR("014", "SQL did not execute correctly."),
    DUPLICATE_WAFER("015", "WAFER already exists in WIP."),
    DUPLICATE_LOT("016", "LOT already exists in WIP."),
    OPERATION_VAILIAD("017", "Invalid filename. (Please check the OPERATION name.)"),
    FILE_SIZE_ERROR("018", "File size exceeds the allowed limit."),
    DETTECT_FAIL("019", "File detection failed"),
    RESTRICTED_CONTENT("020", "File contains restricted content"),
    UNREGISTED_HOLDLIMIT("021", "HOLD LIMIT not registered"),
    UNREGISTED_BINIMIT("022", "BIN LIMIT not registered"),
    ERROR_SAVINGFILE("023","Error occurred while moving the file or saving the file"),
    MAIL_SEND_ERROR("024","Failed to send the email"),
    DATE_FORMAT_ERROR("025","Invalid date format in row %d column %s"),
    DEVICE_NOT_FOUND("026","DEVICE IS NOT REGISTERED"),
    DUPLICATE_ROLE_GROUP_ERROR("027","ROLE GROUP already exists"),
    INVAILD_FILE_FORMAT("028", "The filename format does not match. error: %s "),
    RFA_DOCUMENT_NOT_FOUND("029","There are no documents issued from the F/A requests System. Document number:  %s " ),
    RFA_NOT_MATCH_DEVICE("030","There is a mismatch between the product in the document issued from the F/A requests System and the product in the transmitted document. Document number:  %s "),
    RFA_NOT_MATCH_SUBCONTRACTOR("031","The analysis company in the document issued from the F/A requests System does not match the analysis company in the transmitted document. Document number: %s "),
    RFA_NOT_MATCH_REQUEST_NUMBER("032", "The request number in the document issued from the F/A requests System does not match the request number in the transmitted document. Request number: %s");

    private String errorCode;
    private String errorMessage;

    public String getErrorCode(){
        return errorCode;
    }
    public String getErrorMessage(){
        return errorMessage;
    }

    public String getMessage(){
        return errorCode +" : "+ errorMessage;
    }
     ErrorCode(String errorCode, String errorMessage){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
