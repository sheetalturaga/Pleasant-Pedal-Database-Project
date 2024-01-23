/**
 * BikeCounter class
 */
package app.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BikeCounter {
  private int entryID;
  private Date date;
  private int trailID;
  private int northBoundCount;
  private int southBoundCount;
  private int eastBoundCount;
  private int westBoundCount;
  private int totalCount;
  String neighborhood;

  public BikeCounter(String date, int trailID, int northBoundCount, int southBoundCount,
      int eastBoundCount, int westBoundCount, int totalCount, String neighborhood) throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy  hh:mm:SS aa");
    this.date = sdf.parse(date);
    this.trailID = trailID;
    this.northBoundCount = northBoundCount;
    this.southBoundCount = southBoundCount;
    this.eastBoundCount = eastBoundCount;
    this.westBoundCount = westBoundCount;
    this.totalCount = totalCount;
    this.neighborhood = neighborhood;
  }

  public BikeCounter(int entyId, String date, int trailID, int northBoundCount, int southBoundCount,
      int eastBoundCount, int westBoundCount, int totalCount, String neighborhood) throws ParseException {
    this.entryID = entyId;
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy  hh:mm:SS aa");
    this.date = sdf.parse(date);
    this.trailID = trailID;
    this.northBoundCount = northBoundCount;
    this.southBoundCount = southBoundCount;
    this.eastBoundCount = eastBoundCount;
    this.westBoundCount = westBoundCount;
    this.totalCount = totalCount;
    this.neighborhood = neighborhood;
  }

  public int getEntryID() {
    return entryID;
  }

  public void setEntryID(int entryID) {
    this.entryID = entryID;
  }

  public String getNeighborhood() {
    return neighborhood;
  }

  public void setNeighborhood(String neighborhood) {
    this.neighborhood = neighborhood;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public int getTrailID() {
    return trailID;
  }

  public void setTrailID(int trailID) {
    this.trailID = trailID;
  }

  public int getNorthBoundCount() {
    return northBoundCount;
  }

  public void setNorthBoundCount(int northBoundCount) {
    this.northBoundCount = northBoundCount;
  }

  public int getSouthBoundCount() {
    return southBoundCount;
  }

  public void setSouthBoundCount(int southBoundCount) {
    this.southBoundCount = southBoundCount;
  }

  public int getEastBoundCount() {
    return eastBoundCount;
  }

  public void setEastBoundCount(int eastBoundCount) {
    this.eastBoundCount = eastBoundCount;
  }

  public int getWestBoundCount() {
    return westBoundCount;
  }

  public void setWestBoundCount(int westBoundCount) {
    this.westBoundCount = westBoundCount;
  }

  public int getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
  }
}
