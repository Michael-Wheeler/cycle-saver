package com.cycle_saver.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Activity {

        @SerializedName("resource_state")
        @Expose
        private Integer resourceState;
        @SerializedName("athlete")
        @Expose
        private Athlete athlete;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("distance")
        @Expose
        private Double distance;
        @SerializedName("moving_time")
        @Expose
        private Integer movingTime;
        @SerializedName("elapsed_time")
        @Expose
        private Integer elapsedTime;
        @SerializedName("total_elevation_gain")
        @Expose
        private Double totalElevationGain;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("workout_type")
        @Expose
        private Object workoutType;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("external_id")
        @Expose
        private String externalId;
        @SerializedName("upload_id")
        @Expose
        private Integer uploadId;
        @SerializedName("start_date")
        @Expose
        private String startDate;
        @SerializedName("start_date_local")
        @Expose
        private String startDateLocal;
        @SerializedName("timezone")
        @Expose
        private String timezone;
        @SerializedName("utc_offset")
        @Expose
        private Integer utcOffset;
        @SerializedName("start_latlng")
        @Expose
        private List<Double> startLatlng = null;
        @SerializedName("end_latlng")
        @Expose
        private List<Double> endLatlng = null;
        @SerializedName("location_city")
        @Expose
        private Object locationCity;
        @SerializedName("location_state")
        @Expose
        private Object locationState;
        @SerializedName("location_country")
        @Expose
        private String locationCountry;
        @SerializedName("start_latitude")
        @Expose
        private Double startLatitude;
        @SerializedName("start_longitude")
        @Expose
        private Double startLongitude;
        @SerializedName("achievement_count")
        @Expose
        private Integer achievementCount;
        @SerializedName("kudos_count")
        @Expose
        private Integer kudosCount;
        @SerializedName("comment_count")
        @Expose
        private Integer commentCount;
        @SerializedName("athlete_count")
        @Expose
        private Integer athleteCount;
        @SerializedName("photo_count")
        @Expose
        private Integer photoCount;
        @SerializedName("map")
        @Expose
        private Map map;
        @SerializedName("trainer")
        @Expose
        private Boolean trainer;
        @SerializedName("commute")
        @Expose
        private Boolean commute;
        @SerializedName("manual")
        @Expose
        private Boolean manual;
        @SerializedName("private")
        @Expose
        private Boolean _private;
        @SerializedName("visibility")
        @Expose
        private String visibility;
        @SerializedName("flagged")
        @Expose
        private Boolean flagged;
        @SerializedName("gear_id")
        @Expose
        private String gearId;
        @SerializedName("from_accepted_tag")
        @Expose
        private Boolean fromAcceptedTag;
        @SerializedName("average_speed")
        @Expose
        private Double averageSpeed;
        @SerializedName("max_speed")
        @Expose
        private Double maxSpeed;
        @SerializedName("average_watts")
        @Expose
        private Double averageWatts;
        @SerializedName("kilojoules")
        @Expose
        private Double kilojoules;
        @SerializedName("device_watts")
        @Expose
        private Boolean deviceWatts;
        @SerializedName("has_heartrate")
        @Expose
        private Boolean hasHeartrate;
        @SerializedName("heartrate_opt_out")
        @Expose
        private Boolean heartrateOptOut;
        @SerializedName("display_hide_heartrate_option")
        @Expose
        private Boolean displayHideHeartrateOption;
        @SerializedName("elev_high")
        @Expose
        private Double elevHigh;
        @SerializedName("elev_low")
        @Expose
        private Double elevLow;
        @SerializedName("pr_count")
        @Expose
        private Integer prCount;
        @SerializedName("total_photo_count")
        @Expose
        private Integer totalPhotoCount;
        @SerializedName("has_kudoed")
        @Expose
        private Boolean hasKudoed;

        public Integer getResourceState() {
            return resourceState;
        }

        public void setResourceState(Integer resourceState) {
            this.resourceState = resourceState;
        }

        public Athlete getAthlete() {
            return athlete;
        }

        public void setAthlete(Athlete athlete) {
            this.athlete = athlete;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getDistance() {
            return distance;
        }

        public void setDistance(Double distance) {
            this.distance = distance;
        }

        public Integer getMovingTime() {
            return movingTime;
        }

        public void setMovingTime(Integer movingTime) {
            this.movingTime = movingTime;
        }

        public Integer getElapsedTime() {
            return elapsedTime;
        }

        public void setElapsedTime(Integer elapsedTime) {
            this.elapsedTime = elapsedTime;
        }

        public Double getTotalElevationGain() {
            return totalElevationGain;
        }

        public void setTotalElevationGain(Double totalElevationGain) {
            this.totalElevationGain = totalElevationGain;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getWorkoutType() {
            return workoutType;
        }

        public void setWorkoutType(Object workoutType) {
            this.workoutType = workoutType;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getExternalId() {
            return externalId;
        }

        public void setExternalId(String externalId) {
            this.externalId = externalId;
        }

        public Integer getUploadId() {
            return uploadId;
        }

        public void setUploadId(Integer uploadId) {
            this.uploadId = uploadId;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getStartDateLocal() {
            return startDateLocal;
        }

        public void setStartDateLocal(String startDateLocal) {
            this.startDateLocal = startDateLocal;
        }

        public String getTimezone() {
            return timezone;
        }

        public void setTimezone(String timezone) {
            this.timezone = timezone;
        }

        public Integer getUtcOffset() {
            return utcOffset;
        }

        public void setUtcOffset(Integer utcOffset) {
            this.utcOffset = utcOffset;
        }

        public List<Double> getStartLatlng() {
            return startLatlng;
        }

        public void setStartLatlng(List<Double> startLatlng) {
            this.startLatlng = startLatlng;
        }

        public List<Double> getEndLatlng() {
            return endLatlng;
        }

        public void setEndLatlng(List<Double> endLatlng) {
            this.endLatlng = endLatlng;
        }

        public Object getLocationCity() {
            return locationCity;
        }

        public void setLocationCity(Object locationCity) {
            this.locationCity = locationCity;
        }

        public Object getLocationState() {
            return locationState;
        }

        public void setLocationState(Object locationState) {
            this.locationState = locationState;
        }

        public String getLocationCountry() {
            return locationCountry;
        }

        public void setLocationCountry(String locationCountry) {
            this.locationCountry = locationCountry;
        }

        public Double getStartLatitude() {
            return startLatitude;
        }

        public void setStartLatitude(Double startLatitude) {
            this.startLatitude = startLatitude;
        }

        public Double getStartLongitude() {
            return startLongitude;
        }

        public void setStartLongitude(Double startLongitude) {
            this.startLongitude = startLongitude;
        }

        public Integer getAchievementCount() {
            return achievementCount;
        }

        public void setAchievementCount(Integer achievementCount) {
            this.achievementCount = achievementCount;
        }

        public Integer getKudosCount() {
            return kudosCount;
        }

        public void setKudosCount(Integer kudosCount) {
            this.kudosCount = kudosCount;
        }

        public Integer getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(Integer commentCount) {
            this.commentCount = commentCount;
        }

        public Integer getAthleteCount() {
            return athleteCount;
        }

        public void setAthleteCount(Integer athleteCount) {
            this.athleteCount = athleteCount;
        }

        public Integer getPhotoCount() {
            return photoCount;
        }

        public void setPhotoCount(Integer photoCount) {
            this.photoCount = photoCount;
        }

        public Map getMap() {
            return map;
        }

        public void setMap(Map map) {
            this.map = map;
        }

        public Boolean getTrainer() {
            return trainer;
        }

        public void setTrainer(Boolean trainer) {
            this.trainer = trainer;
        }

        public Boolean getCommute() {
            return commute;
        }

        public void setCommute(Boolean commute) {
            this.commute = commute;
        }

        public Boolean getManual() {
            return manual;
        }

        public void setManual(Boolean manual) {
            this.manual = manual;
        }

        public Boolean getPrivate() {
            return _private;
        }

        public void setPrivate(Boolean _private) {
            this._private = _private;
        }

        public String getVisibility() {
            return visibility;
        }

        public void setVisibility(String visibility) {
            this.visibility = visibility;
        }

        public Boolean getFlagged() {
            return flagged;
        }

        public void setFlagged(Boolean flagged) {
            this.flagged = flagged;
        }

        public String getGearId() {
            return gearId;
        }

        public void setGearId(String gearId) {
            this.gearId = gearId;
        }

        public Boolean getFromAcceptedTag() {
            return fromAcceptedTag;
        }

        public void setFromAcceptedTag(Boolean fromAcceptedTag) {
            this.fromAcceptedTag = fromAcceptedTag;
        }

        public Double getAverageSpeed() {
            return averageSpeed;
        }

        public void setAverageSpeed(Double averageSpeed) {
            this.averageSpeed = averageSpeed;
        }

        public Double getMaxSpeed() {
            return maxSpeed;
        }

        public void setMaxSpeed(Double maxSpeed) {
            this.maxSpeed = maxSpeed;
        }

        public Double getAverageWatts() {
            return averageWatts;
        }

        public void setAverageWatts(Double averageWatts) {
            this.averageWatts = averageWatts;
        }

        public Double getKilojoules() {
            return kilojoules;
        }

        public void setKilojoules(Double kilojoules) {
            this.kilojoules = kilojoules;
        }

        public Boolean getDeviceWatts() {
            return deviceWatts;
        }

        public void setDeviceWatts(Boolean deviceWatts) {
            this.deviceWatts = deviceWatts;
        }

        public Boolean getHasHeartrate() {
            return hasHeartrate;
        }

        public void setHasHeartrate(Boolean hasHeartrate) {
            this.hasHeartrate = hasHeartrate;
        }

        public Boolean getHeartrateOptOut() {
            return heartrateOptOut;
        }

        public void setHeartrateOptOut(Boolean heartrateOptOut) {
            this.heartrateOptOut = heartrateOptOut;
        }

        public Boolean getDisplayHideHeartrateOption() {
            return displayHideHeartrateOption;
        }

        public void setDisplayHideHeartrateOption(Boolean displayHideHeartrateOption) {
            this.displayHideHeartrateOption = displayHideHeartrateOption;
        }

        public Double getElevHigh() {
            return elevHigh;
        }

        public void setElevHigh(Double elevHigh) {
            this.elevHigh = elevHigh;
        }

        public Double getElevLow() {
            return elevLow;
        }

        public void setElevLow(Double elevLow) {
            this.elevLow = elevLow;
        }

        public Integer getPrCount() {
            return prCount;
        }

        public void setPrCount(Integer prCount) {
            this.prCount = prCount;
        }

        public Integer getTotalPhotoCount() {
            return totalPhotoCount;
        }

        public void setTotalPhotoCount(Integer totalPhotoCount) {
            this.totalPhotoCount = totalPhotoCount;
        }

        public Boolean getHasKudoed() {
            return hasKudoed;
        }

        public void setHasKudoed(Boolean hasKudoed) {
            this.hasKudoed = hasKudoed;
        }
}
