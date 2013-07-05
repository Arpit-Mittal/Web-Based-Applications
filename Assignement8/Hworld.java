/* $Id: HelloWorldExample.java,v 1.2 2001/11/29 18:27:25 remm Exp $
 *
 */

import java.io.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import java.io.FileWriter;

import java.io.File;

import org.w3c.dom.*;


import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException; 

/**
 * The simplest possible servlet.
 *
 * @author James Duncan Davidson
 */

public class Hworld extends HttpServlet {


   public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {  

       int failurejsonmessage=0;
       response.setContentType("text/html");
       Enumeration e = request.getParameterNames();
        PrintWriter out = response.getWriter();
        String titleValue= request.getParameter("title");
        String mediaType= request.getParameter("select");
         titleValue=titleValue.replaceAll(" ","%20");
	String url="http://cs-server.usc.edu:39526/cgi-bin/get_movies56789900.pl?title="+titleValue+"&select="+mediaType;
        	
        
       
        //out.println("<html><head>");
        //out.println("<title>Hello World!</title>");

        //out.println("</head><body>");
       // out.println("titleValue" + " = " + titleValue);
       // out.println("Media Type" + " = " + mediaType);
        //out.println("url" + " = " + url);
        

/////////////////URL Connection code/////////////////////////
      /*  URL url1 = new URL(url);

        URLConnection urlConnection = url1.openConnection();
        urlConnection.setAllowUserInteraction(false);
        InputStream urlStream = url1.openStream();
        BufferedReader reader1=new BufferedReader(new InputStreamReader(urlStream));
         String ll1 = "";
        l1="";
    while((l1=reader1.readLine())!=null){
        resp.getWriter().println(l1);
    }*/

String arpit1="";
InputStream urlStream=null;

try
        {
        URL url1 = new URL(url);

        URLConnection urlConnection = url1.openConnection();
        urlConnection.setAllowUserInteraction(false);
        urlStream = url1.openStream();
        BufferedReader reader1=new BufferedReader(new InputStreamReader(urlStream));
 //out.println("url stream"+urlStream);
        String arpit = "";
        
        
    while((arpit=reader1.readLine())!=null){
      //out.println("arpit"+arpit);
      arpit1+=arpit;  
    }
//response.getWriter().println(arpit1);
        }
        catch(Exception e1)
        {
        failurejsonmessage=1;
         //out.println("Exception occured");   
        }
//out.println("arpit1");
//out.println(arpit1);

/////////////////URL Connection code/////////////////////////

/////////////////////Parsing code////////////////////////////////////////////////

 try {
            int i=0;
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
//out.println("docBuilderFactory"+docBuilderFactory);
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
//out.println("docBuilder"+docBuilder);


try{ /* try-catch will be required for compiling successfully */
FileWriter fw = new FileWriter("arpit11111.xml");
fw.write(arpit1);
fw.flush();
fw.close();
}catch(Exception e1){failurejsonmessage=1;}

            Document doc = docBuilder.parse(new File("arpit11111.xml"));
//Document doc = docBuilder.parse(urlStream);
//out.println("doc"+doc);
//out.println("arpit");

            // normalize text representation
            doc.getDocumentElement().normalize();
          
            //out.println("arpit1");
            //out.println ("Root element of the doc is " +    doc.getDocumentElement().getNodeName());
            NodeList listOfResults = doc.getElementsByTagName("result");
            int totalResults = listOfResults.getLength();
           //out.println("Total no of Results : " + totalResults);
            for(i=0;i<totalResults;i++)
            {
            //out.println("records");
            //out.println(listOfResults.item(i).getAttributes().item(0).getNodeValue());
            //out.println(listOfResults.item(i).getAttributes().item(1).getNodeValue());
            //out.println(listOfResults.item(i).getAttributes().item(2).getNodeValue());
            //out.println(listOfResults.item(i).getAttributes().item(3).getNodeValue());
            //out.println(listOfResults.item(i).getAttributes().item(4).getNodeValue());
            //out.println(listOfResults.item(i).getAttributes().item(5).getNodeValue());
            }

///////////////////////////////////////////////////////////Json String Conversion
            //out.println("Json String Conversion");

if(failurejsonmessage==0)
{

            StringBuffer jobs1 = new StringBuffer("{\"results\":{\n\"result\":[\n" ); 
            String value="arpit";

            for( i=0;i<totalResults;i++)
            {
                
                if(i<=(totalResults-2))
                {
jobs1.append(new StringBuffer("{\"cover\":\""+listOfResults.item(i).getAttributes().item(0).getNodeValue()+"\",\"title\":\""+listOfResults.item(i).getAttributes().item(4).getNodeValue()+"\",\"year\":\""+listOfResults.item(i).getAttributes().item(5).getNodeValue()+"\",\"director\":\""+listOfResults.item(i).getAttributes().item(2).getNodeValue()+"\",\"rating\":\""+listOfResults.item(i).getAttributes().item(3).getNodeValue()+"\",\"deatils\":\""+listOfResults.item(i).getAttributes().item(1).getNodeValue()+"\"},\n" ));
                }
                if(i==(totalResults-1))
                {
   jobs1.append(new StringBuffer("{\"cover\":\""+listOfResults.item(i).getAttributes().item(0).getNodeValue()+"\",\"title\":\""+listOfResults.item(i).getAttributes().item(4).getNodeValue()+"\",\"year\":\""+listOfResults.item(i).getAttributes().item(5).getNodeValue()+"\",\"director\":\""+listOfResults.item(i).getAttributes().item(2).getNodeValue()+"\",\"rating\":\""+listOfResults.item(i).getAttributes().item(3).getNodeValue()+"\",\"deatils\":\""+listOfResults.item(i).getAttributes().item(1).getNodeValue()+"\"}]}}" ));                  
                }
            }
            
        // TODO code application logic here
        //System.out.println(jobs.toString());
            //out.println("arpitJsonstringconversion");
        out.println(jobs1.toString());

}
///////////////////////////////////////////////////////////////Json String Conversion


       


        }catch (Exception err) {
           
         failurejsonmessage=1;

        }

if(failurejsonmessage==1)
{

            StringBuffer jobs1 = new StringBuffer("{\"results\":{\n\"result\":[\n" ); 
            String value="arpit";
             String faillllllllllllllll="faillllllllllllllll";
int j=0;

            for( j=0;j<1;j++)
            {
                
                if(j<=(0))
                {
jobs1.append(new StringBuffer("{\"cover\":\""+faillllllllllllllll+"\",\"title\":\""+faillllllllllllllll+"\",\"year\":\""+faillllllllllllllll+"\",\"director\":\""+faillllllllllllllll+"\",\"rating\":\""+faillllllllllllllll+"\",\"deatils\":\""+faillllllllllllllll+"\"}]}}" ));
                }
               
            }
            
        // TODO code application logic here
        //System.out.println(jobs.toString());
            //out.println("arpitJsonstringconversion");
        out.println(jobs1.toString());
}



////////////////////Parsing code////////////////////////////////////////////////



		 //while (e.hasMoreElements()) {
          //String name = (String)e.nextElement();
          //String value = request.getParameter(name);
          //out.println(name + " = " + value);}
        //out.println("<h1>Hello World!</h1>");
        //out.println("</body></html>");
		
    }
}
   



