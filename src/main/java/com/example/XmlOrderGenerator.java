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

public class XmlOrderGenerator {
  
  
  public static void main(String[] args) throws IOException {
    
    if(args.length < 1) {
      throw new IllegalArgumentException("Please provide a directory");
    }
    
    String path = args[0];
    
    Orderbody orderBody = new Orderbody();
    
    Order order = new Order();
    order.setCustomer("DemoCustomer");
    order.setOrderbody(orderBody);
    
    Position position = new Position();
    position.setNumber(BigInteger.ONE);
    position.setOrderNo("22-11");
    position.setPrice(22.11);
    
    orderBody.getPosition().add(position);
    
    try {
  
      Path pathToFile = Paths.get(path);
      Files.createDirectories(pathToFile.getParent());
      File file = Files.createFile(pathToFile).toFile();
      
      JAXBContext jaxbContext = JAXBContext.newInstance(Order.class);
      Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
      jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
      
      jaxbMarshaller.marshal(order, file);
      jaxbMarshaller.marshal(order, System.out);
      
    } catch (JAXBException e) {
      e.printStackTrace();
    }
    
  }
  
}
