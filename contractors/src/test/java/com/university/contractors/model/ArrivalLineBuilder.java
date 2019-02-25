package com.university.contractors.model;

public final class ArrivalLineBuilder {
    private ArrivalLine arrivalLine;

    private ArrivalLineBuilder() {
        arrivalLine = new ArrivalLine();
    }

    public static ArrivalLineBuilder anArrivalLine() {
        return new ArrivalLineBuilder();
    }

    public ArrivalLineBuilder id(Long id) {
        arrivalLine.setId(id);
        return this;
    }

    public ArrivalLineBuilder arrivalCenter(String arrivalCenter) {
        arrivalLine.setArrivalCenter(arrivalCenter);
        return this;
    }

    public ArrivalLineBuilder arrivalCenterName(String arrival_center_name) {
        arrivalLine.setArrivalCenterName(arrival_center_name);
        return this;
    }

    public ArrivalLine build() {
        return arrivalLine;
    }
}
