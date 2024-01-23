package app.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CrimeCounter {
  private String offenseID;
  private String ReportNumber;
  private Date reportDate;
  public enum offenseGroup{
    SOCIETY,
    PROPERTY,
    PERSON
  }
  private offenseGroup group;
  private String offense;
  private String neighborhood;
  private float latitude;
  private float longitude;

   public CrimeCounter(String reportNumber, String offenseID, String reportDate,
      offenseGroup group, String offense, String neighborhood, float latitude, float longitude) throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy  hh:mm:SS aa");
    this.offenseID = offenseID;
    this.ReportNumber = reportNumber;
    this.reportDate = sdf.parse(reportDate);
    this.group = group;
    this.offense = offense;
    this.neighborhood = neighborhood;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public String getReportNumber() {
    return ReportNumber;
  }

  public void setReportNumber(String reportNumber) {
    ReportNumber = reportNumber;
  }

  public String getOffenseID() {
    return offenseID;
  }

  public void setOffenseID(String offenseID) {
    this.offenseID = offenseID;
  }

  public Date getReportDate() {
    return reportDate;
  }

  public void setReportDate(Date reportDate) {
    this.reportDate = reportDate;
  }

  public offenseGroup getGroup() {
    return group;
  }

  public void setGroup(offenseGroup group) {
    this.group = group;
  }

  public String getOffense() {
    return offense;
  }

  public void setOffense(String offense) {
    this.offense = offense;
  }

  public String getNeighborhood() {
    return neighborhood;
  }

  public void setNeighborhood(String neighborhood) {
    this.neighborhood = neighborhood;
  }

  public float getLatitude() {
    return latitude;
  }

  public void setLatitude(float latitude) {
    this.latitude = latitude;
  }

  public float getLongitude() {
    return longitude;
  }

  public void setLongitude(float longitude) {
    this.longitude = longitude;
  }
}
