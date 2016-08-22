package test;

import org.junit.Test;
import org.junit.Assert;
import entities.Client;

public class TestClient {
    
    Client client;
    
    @Test
    public void testClient(){
        client = new  Client();
        String name = "Commercial Press";
        client.setName(name);
        String addressLine1 = "7 W Jackson Blvd";
        client.setAddressLine1(addressLine1);
        String city = "Santa Clara";
        client.setCity(city);
        String State = "CA";
        client.setState(State);
        int zip = 95111;
        client.setZip(zip);
        String email = "leota@hotmail.com";
        client.setEmail(email);
        String contact = "Leota Dilliard";
        client.setContact(contact);
        String invoiceFrequency = "Monthly-Cal";
        client.setInvoiceFrequency(invoiceFrequency);
        String billingTerm = "Net 30 Days";
        client.setBillingTerm(billingTerm);
        String invoiceGrouping = "Client";
        client.setInvoiceGrouping(invoiceGrouping);
        
        Assert.assertEquals(name, client.getName());
        Assert.assertEquals(addressLine1, client.getAddressLine1());
        Assert.assertEquals(city, client.getCity());
        Assert.assertEquals(State, client.getState());
        Assert.assertEquals(zip, client.getZip());
        Assert.assertEquals(email, client.getEmail());
        Assert.assertEquals(contact, client.getContact());
        Assert.assertEquals(invoiceFrequency, client.getInvoiceFrequency());
        Assert.assertEquals(billingTerm, client.getBillingTerm());
        Assert.assertEquals(invoiceGrouping, client.getInvoiceGrouping());
    }
}

