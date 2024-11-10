package model;

public class Customer {
    private int seqNo;
    private String name;
    private String parcelId;

    public Customer(int seqNo, String name, String parcelId) {
        this.seqNo = seqNo;
        this.name = name;
        this.parcelId = parcelId;
    }


    public int getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(int seqNo) {
        this.seqNo = seqNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParcelId() {
        return parcelId;
    }

    public void setParcelId(String parcelId) {
        this.parcelId = parcelId;
    }

    @Override
    public String toString() {
        return seqNo + ", " + name + ", " + parcelId;
    }
}
