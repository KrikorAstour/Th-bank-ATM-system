/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banksystem;
import java.util.Date;


/**
 *
 * @author Krikor Astour
 */
public class Record {
    private Date date;
    private String operation;
    private double amount;
    private String atmId;

    public Record() {
        this.operation = null;
        this.amount = 0;
        this.atmId = atmId;
        this.date = null;
    }

    public Record(String operation, double amount, String atmId) {
        this.operation = operation;
        this.amount = amount;
        this.atmId = atmId;
        this.date = new Date();
    }
    
    public Record(Record record) {
        this.date = record.date;
        this.atmId = record.atmId;
        this.operation = record.operation;
        this.amount = record.amount;
    }
    
    public boolean equals(Record record) {
        return this.date.equals(record.date) && this.atmId.equals(record.atmId) &&
                this.amount == record.amount && this.operation.equals(record.operation);
    }
    
    public String toString() {
        String str = "";
        
        str += String.format("%-10s: %s\n", "Date", date);
        str += String.format("%-10s: %.2f\n", "Amount", amount);
        str += String.format("%-10s: %s\n", "ATM ID", atmId);
        str += String.format("%-10s: %s\n", "Operation", operation);
        
        return str;
    }

    public Date getDate() {
        return date;
    }

    public String getOperation() {
        return operation;
    }

    public double getAmount() {
        return amount;
    }

    public String getAtmId() {
        return atmId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setAtmId(String atmId) {
        this.atmId = atmId;
    }
    
    
}
