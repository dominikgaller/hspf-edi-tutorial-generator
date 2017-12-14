package com.example;

import com.example.model.Order;
import com.example.model.Orderbody;
import com.example.model.Position;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class XmlOrderGenerator {
  
  
  public static void main(String[] args) throws IOException {
    
    if(args.length < 1) {
      throw new IllegalArgumentException("Please provide a directory");
    }
    
    String path = args[0];
    
    
    
    try {
      JAXBContext jaxbContext = JAXBContext.newInstance(Order.class);
      Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
      jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
  
      Random r = new Random();
      
      
      for (int i = 0; i < 7; i++) {
        double randomValue = 12 + (123 - 12) * r.nextDouble();
        
        
        
        
        
        int randomCountry = ThreadLocalRandom.current().nextInt(0, 6);
        
        Orderbody orderBody = new Orderbody();
  
        Order order = new Order();
        order.setCustomer("DemoCustomer_" + i);
        order.setOrderbody(orderBody);
        Country c = Country.values()[randomCountry];
        order.setCountry(c.name());
  
        if(c.equals(Country.DACH)) {
          int randomRegion = ThreadLocalRandom.current().nextInt(0, 4);
          order.setRegion(Region.values()[randomRegion].name());
        }
  
        Position position = new Position();
        position.setNumber(BigInteger.ONE);
        position.setOrderNo(i + "-" + (i + 12));
        position.setPrice(randomValue);
  
        orderBody.getPosition().add(position);
        
        
        
        
        
        Path pathToFile = Paths.get(path + "/" + "file_" + i + ".xml");
        Files.createDirectories(pathToFile.getParent());
        File file = Files.createFile(pathToFile).toFile();
    
        jaxbMarshaller.marshal(order, file);
        jaxbMarshaller.marshal(order, System.out);
      }
      
      for(int i = 8; i < 12;i++) {
        double randomValue = 12 + (123 - 12) * r.nextDouble();
  
  
  
  
  
        int randomCountry = ThreadLocalRandom.current().nextInt(0, 6);
  
        Orderbody orderBody = new Orderbody();
  
        Order order = new Order();
        order.setCustomer("DemoCustomer_" + i);
        order.setOrderbody(orderBody);
        Country c = Country.DACH;
        order.setCountry(c.name());
  
        if(c.equals(Country.DACH)) {
          int randomRegion = ThreadLocalRandom.current().nextInt(0, 3);
          order.setRegion(Region.values()[randomRegion].name());
        }
  
        Position position = new Position();
        position.setNumber(BigInteger.ONE);
        position.setOrderNo(i + "-" + (i + 12));
        position.setPrice(randomValue);
  
        orderBody.getPosition().add(position);
  
  
  
  
  
        Path pathToFile = Paths.get(path + "/" + "file_" + i + ".xml");
        Files.createDirectories(pathToFile.getParent());
        File file = Files.createFile(pathToFile).toFile();
  
        jaxbMarshaller.marshal(order, file);
        jaxbMarshaller.marshal(order, System.out);
      }
  
    } catch (JAXBException e) {
      e.printStackTrace();
    }
    
  }
  
}
