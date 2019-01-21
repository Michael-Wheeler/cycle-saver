package com.cycle_saver.model;

import java.util.List;

public class Activity {

        private Integer resourceState;

        private Athlete athlete;

        private String name;

        private Double distance;

        private Integer movingTime;

        private Integer elapsedTime;

        private Double totalElevationGain;

        private String type;

        private String workoutType;

        private Integer id;

        private String externalId;

        private Integer uploadId;

        private String startDate;

        private String startDateLocal;

        private String timezone;

        private Integer utcOffset;

        private List<Double> startLatlng = null;

        private List<Double> endLatlng = null;

        private String locationCity;

        private String locationState;

        private String locationCountry;

        private Double startLatitude;

        private Double startLongitude;

        private Integer achievementCount;

        private Integer kudosCount;

        private Integer commentCount;

        private Integer athleteCount;

        private Integer photoCount;

        private Map map;

        private Boolean trainer;

        private Boolean commute;

        private Boolean manual;

        private Boolean _private;

        private String visibility;

        private Boolean flagged;

        private String gearId;

        private Boolean fromAcceptedTag;

        private Double averageSpeed;

        private Double maxSpeed;

        private Double averageWatts;

        private Double kilojoules;

        private Boolean deviceWatts;

        private Boolean hasHeartrate;

        private Boolean heartrateOptOut;

        private Boolean displayHideHeartrateOption;

        private Double elevHigh;

        private Double elevLow;

        private Integer prCount;

        private Integer totalPhotoCount;

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

        public String getWorkoutType() {
            return workoutType;
        }

        public void setWorkoutType(String workoutType) {
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

        public String getLocationCity() {
            return locationCity;
        }

        public void setLocationCity(String locationCity) {
            this.locationCity = locationCity;
        }

        public String getLocationState() {
            return locationState;
        }

        public void setLocationState(String locationState) {
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
