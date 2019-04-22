package definition.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "$id",
        "currentDateTime",
        "utcOffset",
        "isDayLightSavingsTime",
        "dayOfTheWeek",
        "timeZoneName",
        "currentFileTime",
        "ordinalDate",
        "serviceResponse"
})
public class CurrentDateTimeResponse {

    @JsonProperty("$id")
    private String $id;
    @JsonProperty("currentDateTime")
    private String currentDateTime;
    @JsonProperty("utcOffset")
    private String utcOffset;
    @JsonProperty("isDayLightSavingsTime")
    private Boolean isDayLightSavingsTime;
    @JsonProperty("dayOfTheWeek")
    private String dayOfTheWeek;
    @JsonProperty("timeZoneName")
    private String timeZoneName;
    @JsonProperty("currentFileTime")
    private Long currentFileTime;
    @JsonProperty("ordinalDate")
    private String ordinalDate;
    @JsonProperty("serviceResponse")
    private Object serviceResponse;

    @JsonProperty("$id")
    public String get$id() {
        return $id;
    }

    @JsonProperty("$id")
    public void set$id(String $id) {
        this.$id = $id;
    }

    @JsonProperty("currentDateTime")
    public String getCurrentDateTime() {
        return currentDateTime;
    }

    @JsonProperty("currentDateTime")
    public void setCurrentDateTime(String currentDateTime) {
        this.currentDateTime = currentDateTime;
    }

    @JsonProperty("utcOffset")
    public String getUtcOffset() {
        return utcOffset;
    }

    @JsonProperty("utcOffset")
    public void setUtcOffset(String utcOffset) {
        this.utcOffset = utcOffset;
    }

    @JsonProperty("isDayLightSavingsTime")
    public Boolean getIsDayLightSavingsTime() {
        return isDayLightSavingsTime;
    }

    @JsonProperty("isDayLightSavingsTime")
    public void setIsDayLightSavingsTime(Boolean isDayLightSavingsTime) {
        this.isDayLightSavingsTime = isDayLightSavingsTime;
    }

    @JsonProperty("dayOfTheWeek")
    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    @JsonProperty("dayOfTheWeek")
    public void setDayOfTheWeek(String dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    @JsonProperty("timeZoneName")
    public String getTimeZoneName() {
        return timeZoneName;
    }

    @JsonProperty("timeZoneName")
    public void setTimeZoneName(String timeZoneName) {
        this.timeZoneName = timeZoneName;
    }

    @JsonProperty("currentFileTime")
    public Long getCurrentFileTime() {
        return currentFileTime;
    }

    @JsonProperty("currentFileTime")
    public void setCurrentFileTime(Long currentFileTime) {
        this.currentFileTime = currentFileTime;
    }

    @JsonProperty("ordinalDate")
    public String getOrdinalDate() {
        return ordinalDate;
    }

    @JsonProperty("ordinalDate")
    public void setOrdinalDate(String ordinalDate) {
        this.ordinalDate = ordinalDate;
    }

    @JsonProperty("serviceResponse")
    public Object getServiceResponse() {
        return serviceResponse;
    }

    @JsonProperty("serviceResponse")
    public void setServiceResponse(Object serviceResponse) {
        this.serviceResponse = serviceResponse;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("$id", $id).append("currentDateTime", currentDateTime).append("utcOffset", utcOffset).append("isDayLightSavingsTime", isDayLightSavingsTime).append("dayOfTheWeek", dayOfTheWeek).append("timeZoneName", timeZoneName).append("currentFileTime", currentFileTime).append("ordinalDate", ordinalDate).append("serviceResponse", serviceResponse).toString();
    }
}