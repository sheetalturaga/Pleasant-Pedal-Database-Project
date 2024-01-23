package app.model;

import java.sql.Date;

public class PedestrianCounter {
    private int entryId;
    private Date date;
    private int trailID;
    private int northBoundCount;
    private int southBoundCount;
    private int eastBoundCount;
    private int westBoundCount;
    private int totalCount;
    private String neighborhood;

    public PedestrianCounter(int entryId, Date date, int trailID, int northBoundCount, int southBoundCount,
        int eastBoundCount, int westBoundCount, int totalCount, String neighborhood) {
      this.entryId = entryId;
      this.date = date;
      this.trailID = trailID;
      this.northBoundCount = northBoundCount;
      this.southBoundCount = southBoundCount;
      this.eastBoundCount = eastBoundCount;
      this.westBoundCount = westBoundCount;
      this.totalCount = totalCount;
      this.neighborhood = neighborhood;
    }

  public PedestrianCounter(Date date, int trailID, int northBoundCount, int southBoundCount,
      int eastBoundCount, int westBoundCount, int totalCount, String neighborhood) {
    this.date = date;
    this.trailID = trailID;
    this.northBoundCount = northBoundCount;
    this.southBoundCount = southBoundCount;
    this.eastBoundCount = eastBoundCount;
    this.westBoundCount = westBoundCount;
    this.totalCount = totalCount;
    this.neighborhood = neighborhood;
  }

  public int getEntryId() {
    return entryId;
  }

  public void setEntryId(int entryId) {
    this.entryId = entryId;
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
